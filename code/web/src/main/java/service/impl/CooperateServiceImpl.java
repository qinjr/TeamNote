package service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.mongodbDao.*;
import dao.mysqlDao.UserInfoDao;
import handler.WebsocketHandler;
import model.mongodb.GroupChat;
import model.mongodb.Note;
import model.mongodb.Notebook;
import model.mongodb.Suggestion;
import model.mysql.UserInfo;
import model.temp.Message;
import org.springframework.web.socket.TextMessage;
import service.CooperateService;
import util.NoticeUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/7/3.
 */
public class CooperateServiceImpl implements CooperateService {
    private NoticeUtil noticeUtil;
    private NotebookDao notebookDao;
    private NoteDao noteDao;
    private GroupChatDao groupChatDao;
    private SuggestionDao suggestionDao;
    private UserInfoDao userInfoDao;
    private UserDao userDao;
    private WebsocketHandler websocketHandler;

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    public void setSuggestionDao(SuggestionDao suggestionDao) {
        this.suggestionDao = suggestionDao;
    }

    public void setNoticeUtil(NoticeUtil noticeUtil) {
        this.noticeUtil = noticeUtil;
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void setGroupChatDao(GroupChatDao groupChatDao) {
        this.groupChatDao = groupChatDao;
    }

    public void setWebsocketHandler(WebsocketHandler websocketHandler) {
        this.websocketHandler = websocketHandler;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int inviteCooperator(int inviterId, int targetId, int notebookId, String description) {
        String content = "/viewInvitation?inviterId=" + Integer.toString(inviterId) +
                "&notebookId=" + Integer.toString(notebookId) + "&description=" + description;
        noticeUtil.sendNotice(targetId, content, new Date());
        return 1;
    }

    public String viewInvitation(int inviterId, int notebookId, String description) {
        JsonObject json = new JsonObject();
        json.addProperty("inviterId", inviterId);
        json.addProperty("notebookId", notebookId);
        json.addProperty("description", description);
        return json.toString();
    }

    public int takeInvitation(int userId, int decision, int notebookId) {
        UserInfo userInfo = userInfoDao.getUserInfoById(userId);
        Notebook notebook = notebookDao.getNotebookById(notebookId);
        Date datetime = new Date();

        if (decision == 1) {
            //notify every body in the working group to know that userId has take the invitation
            ArrayList<Integer> collaborators = notebook.getCollaborators();
            for (int collaborator : collaborators) {
                noticeUtil.sendNotice(collaborator, "User" + userInfo.getUsername() + "decided to attend our work group",
                        datetime);
            }
            //add userId to collaborators
            collaborators.add(userId);
            notebook.setCollaborators(collaborators);
            notebookDao.updateNotebook(notebook);
        } else {
            //notify every body in the working group to know that userId don't want to take the invitation
            ArrayList<Integer> collaborators = notebook.getCollaborators();
            for (int collaborator : collaborators) {
                noticeUtil.sendNotice(collaborator, "User" + userInfo.getUsername() + "decided not to attend to our work group",
                        datetime);
            }
        }
        return 0;
    }

    public ArrayList<String> getGroupChat(int notebookId) {
        ArrayList<String> result = new ArrayList<String>();
        GroupChat groupChat = groupChatDao.getGroupChatById(notebookId);
        if(groupChat != null) {
            result = groupChat.getContents();
        }
        return result;
    }

    public int giveOwnership(int oldOwnerId, int newOwnerId, int notebookId) {
        Notebook notebook = notebookDao.getNotebookById(notebookId);
        if (notebook.getOwner() == oldOwnerId) {
            notebook.setOwner(newOwnerId);
            notebookDao.updateNotebook(notebook);
            return 1;
        } else {
            return 0;
        }
    }

    public int mergeSuggestion(int managerId, int noteId, int suggestionId) {
        Note note = noteDao.getNoteById(noteId);
        UserInfo managerInfo = userInfoDao.getUserInfoById(managerId);

        //add to note new history version
        Suggestion suggestion = suggestionDao.getSuggestionById(suggestionId);
        JsonObject newVersion = new JsonObject();
        newVersion.addProperty("editTime", new Date().toString());
        newVersion.addProperty("Message", "merge a contributor's suggestion:" + suggestion.getIssue());
        newVersion.addProperty("editor", managerInfo.getUsername());
        newVersion.addProperty("content", suggestion.getContent());
        ArrayList<String> history = note.getHistory();

        //delete conflict versions
        if (note.getVersionPointer() + 1 < history.size()) {
            for (int i = note.getVersionPointer() + 1; i < history.size(); ++ i) {
                history.remove(i);
            }
        }
        history.add(newVersion.toString());
        note.setHistory(history);
        note.setVersionPointer(note.getVersionPointer() + 1);
        noteDao.updateNote(note);

        //set suggestion status to accepted
        suggestion.setStatus("accepted");
        suggestionDao.updateSuggestion(suggestion);

        //add contributor info to the notebook to which the note belong
        Notebook notebook = notebookDao.getNotebookById(note.getNotebookId());
        ArrayList<Integer> contributors = notebook.getContributors();
        contributors.add(suggestion.getUserId());
        notebook.setContributors(contributors);
        notebookDao.updateNotebook(notebook);

        return 1;
    }

    public int raiseAdvice(int userId, int noteId, String content, String issue, Date datetime, String username) {
        int notebookId = noteDao.getNoteById(noteId).getNotebookId();
        Suggestion suggestion = new Suggestion(userId, noteId, notebookId, content, issue, datetime, "not accepted", username);
        int suggestionId = suggestionDao.addSuggestion(suggestion);

        ArrayList<Integer> collaborators = notebookDao.getNotebookById(notebookId).getCollaborators();
        for (int collaborator : collaborators) {
            noticeUtil.sendNotice(collaborator, "viewSuggestion?suggestionId=" + Integer.toString(suggestionId),
                    datetime);
        }
        return 1;
    }

    public String getSuggestion(int suggestionId) {
        Suggestion suggestion = suggestionDao.getSuggestionById(suggestionId);
        JsonObject json = new JsonObject();
        json.addProperty("suggestionId", suggestion.getSuggestionId());
        json.addProperty("advicer", suggestion.getUserId());
        json.addProperty("content", suggestion.getContent());
        json.addProperty("noteId", suggestion.getNoteId());
        json.addProperty("notebookId", suggestion.getNotebookId());
        json.addProperty("issue", suggestion.getIssue());
        json.addProperty("raiseTime", suggestion.getRaiseTime().toString());
        return json.toString();
    }


    public int sendGroupChat(int userId, int notebookId, Date datetime, String content) {

        //receive
        String username = userInfoDao.getUserInfoById(userId).getUsername();
        String avatar = userDao.getUserById(userId).getAvatar();
        GroupChat groupChat = groupChatDao.getGroupChatById(notebookId);
        Message msg = new Message(userId, username, avatar, notebookId, content, new Date());
        //store
        if(groupChat == null){
            groupChatDao.addGroupChat(new GroupChat(notebookId, new ArrayList<String>()));
            groupChat = groupChatDao.getGroupChatById(notebookId);
        }
        ArrayList<String> contents = groupChat.getContents();
        JsonObject json = new JsonObject();
        json.addProperty("uid", userId);
        json.addProperty("datetime", datetime.toString());
        json.addProperty("content", content);
        contents.add(json.toString());
        groupChat.setContents(contents);
        groupChatDao.updateGroupChat(groupChat);
        //broadcast
        websocketHandler.sendMessageToGroup(userId, notebookId, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
        return 0;
    }

    public String getSuggestions(int noteId) {
        ArrayList<Suggestion> suggestions = (ArrayList<Suggestion>)suggestionDao.getPendingSuggestionsByNoteId(noteId);
        String suggestionsString = new Gson().toJson(suggestions);
        JsonObject json = new JsonObject();
        json.addProperty("suggestions", suggestionsString);
        return json.toString();
    }

    public void ignoreSuggestion(int suggestionId) {
        Suggestion suggestion = suggestionDao.getSuggestionById(suggestionId);
        suggestion.setStatus("ignored");
        suggestionDao.updateSuggestion(suggestion);
    }
}