package service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.mongodbDao.*;
import dao.mysqlDao.AuthDao;
import dao.mysqlDao.UserInfoDao;
import model.mongodb.*;
import model.mysql.Auth;
import model.mysql.UserInfo;
import service.AdminService;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/7/18.
 * -------------------------------------------
 * Delete note,notebook or user won't really delete them in database,
 * only set valid as 0
 */

public class AdminServiceImpl implements AdminService {
    private AuthDao authDao;
    private CommentDao commentDao;
    private GroupChatDao groupChatDao;
    private LetterDao letterDao;
    private NoteDao noteDao;
    private NotebookDao notebookDao;
    private NoticeDao noticeDao;
    private RuleDao ruleDao;
    private SuggestionDao suggestionDao;
    private TagDao tagDao;
    private UserDao userDao;
    private UserInfoDao userInfoDao;
    private VerifyDao verifyDao;

    public void setAuthDao(AuthDao authDao) {
        this.authDao = authDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void setGroupChatDao(GroupChatDao groupChatDao) {
        this.groupChatDao = groupChatDao;
    }

    public void setLetterDao(LetterDao letterDao) {
        this.letterDao = letterDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void setNotebookDao(NotebookDao notebookDao) {
        this.notebookDao = notebookDao;
    }

    public void setNoticeDao(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    public void setRuleDao(RuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }

    public void setSuggestionDao(SuggestionDao suggestionDao) {
        this.suggestionDao = suggestionDao;
    }

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    public void setVerifyDao(VerifyDao verifyDao) {
        this.verifyDao = verifyDao;
    }

    public int CUDAuth(int authId, String op, int userId, int notebookId, String role){
        if(op.equals("create")) {
            authDao.addAuth(new Auth(userId, notebookId, role));
        } else if(op.equals("update")) {
            Auth auth = authDao.getAuthById(authId);
            auth.setUserId(userId); //
            auth.setNotebookId(notebookId); //
            auth.setAuth(role);
            authDao.updateAuth(auth);
        } else if(op.equals("delete")) {
            authDao.deleteAuth(authDao.getAuthById(authId));
        }
        return 1;
    }

    public ArrayList<Auth> RAuth() {
        return authDao.getAllAuths();
    }

    public int CUDComment(int commentId, String op, int userId, Date datetime, String content, int report, int valid){
        if(op.equals("create")) {
            commentDao.addComment(new Comment(userId, datetime, content, report, valid));
        } else if(op.equals("update")) {
            Comment comment = commentDao.getCommentById(commentId);
            comment.setUserId(userId);
            comment.setSentTime(datetime);
            comment.setContent(content);
            comment.setReportCount(report);
            comment.setValid(valid);
            commentDao.updateComment(comment);
        } else if(op.equals("delete")) {
           commentDao.deleteComment(commentDao.getCommentById(commentId));
        }
        return 1;
    }

    public List<Comment> RComment(){
        return commentDao.getAllComments();
    }

    public int CUDGroupChat(int notebookId, String op, ArrayList<String> contents) {
        if(op.equals("create")) {
            GroupChat groupChat = groupChatDao.getGroupChatById(notebookId);
            if(groupChat == null) {
                groupChatDao.addGroupChat(new GroupChat(notebookId, contents));
            }
        } else if(op.equals("update")) {
            GroupChat groupChat = groupChatDao.getGroupChatById(notebookId);
            groupChat.setContents(contents);
            groupChatDao.updateGroupChat(groupChat);
        } else if(op.equals("delete")) {
            groupChatDao.deleteGroupChat(groupChatDao.getGroupChatById(notebookId));
        }
        return 1;
    }

    public List<GroupChat> RGroupChat(){
        return groupChatDao.getAllGroupChats();
    }

    public int CUDLetter(int letterId, String op, int senderId, int receiverId, String content, Date datetime, int read){
        if(op.equals("create")) {
            letterDao.addLetter(new Letter(senderId, receiverId, content, datetime, read));
        } else if(op.equals("update")) {
            Letter letter = letterDao.getLetterById(letterId);
            letter.setSenderId(senderId);
            letter.setReceiverId(receiverId);
            letter.setContent(content);
            letter.setSentTime(datetime);
            letter.setRead(read);
            letterDao.updateLetter(letter);
        } else if(op.equals("delete")) {
            letterDao.deleteLetter(letterDao.getLetterById(letterId));
        }
        return 1;
    }

    public List<Letter> RLetter(){
        return letterDao.getAllLetters();
    }

    public int CUDNote(int noteId, String op, int notebookId, String title, ArrayList<String> history,
                ArrayList<Integer> comments, ArrayList<Integer> upvoters, ArrayList<Integer> downvoters, int report, int valid, int versionPointer){
        if(op.equals("create")) {
            noteDao.addNote(new Note(notebookId, title, history, comments, upvoters, downvoters, report, valid, versionPointer));
            //TODO
        } else if(op.equals("update")) {
            Note note = noteDao.getNoteById(noteId);
            note.setNotebookId(notebookId); //x
            note.setTitle(title);
            note.setHistory(history);
            note.setComments(comments); //
            note.setUpvoters(upvoters);
            note.setDownvoters(downvoters);
            note.setReportCount(report);
            note.setValid(valid);
            note.setVersionPointer(versionPointer);
            noteDao.updateNote(note);
        } else if(op.equals("delete")) {
            Note note = noteDao.getNoteById(noteId);
            comments = note.getComments();
            for(int commentId : comments) {
                commentDao.deleteComment(commentDao.getCommentById(commentId));
            }
            notebookId = note.getNotebookId();
            Notebook notebook = notebookDao.getNotebookById(notebookId);
            ArrayList<Integer> notes = notebook.getNotes();
            for(int i = 0;i < notes.size();i++){
                if(notes.get(i) == noteId){
                    notes.remove(i);
                    break;
                }
            }
            notebook.setNotes(notes);
            notebookDao.updateNotebook(notebook);
            note.setValid(0);
            noteDao.updateNote(note);
        }
        return 1;
    }

    public ArrayList<Note> RNoteOfNotebook(int notebookId){
        ArrayList<Integer> notes = notebookDao.getNotebookById(notebookId).getNotes();
        ArrayList<Note> result = new ArrayList<Note>();
        for(int note : notes) {
            result.add(noteDao.getNoteById(note));
        }
        return result;
    }

    public int CUDNotebook(int notebookId, String op, String title, String description, int creator, int owner, int star, int collected,
                    int count, ArrayList<Integer> collobrators, ArrayList<Integer> contributors, ArrayList<Integer> notes, Date createTime, String cover, ArrayList<Integer> tags, ArrayList<Integer> starers){
        if(op.equals("create")) {
            notebookDao.addNotebook(new Notebook(title, description, creator, owner, star, collected, count, collobrators, contributors, notes, createTime, cover, tags, starers));
        } else if(op.equals("update")) {
            Notebook notebook = notebookDao.getNotebookById(notebookId);
            notebook.setTitle(title);
            notebook.setDescription(description);
            notebook.setCreator(creator); //
            notebook.setOwner(owner); //
            notebook.setStar(star);
            notebook.setCollected(collected);
            notebook.setClickCount(count);
            notebook.setCollaborators(collobrators); //
            notebook.setContributors(contributors); //
            notebook.setNotes(notes); //
            notebook.setCreateTime(createTime);
            notebook.setCover(cover);
            notebook.setTags(tags); //
            notebook.setStarers(starers);
            notebookDao.updateNotebook(notebook);
        } else if(op.equals("delete")) {
            Notebook notebook = notebookDao.getNotebookById(notebookId);
            //notes
            notes = notebook.getNotes();
            for(int noteId : notes){
                CUDNote(noteId,"delete",notebookId,"",null,null,null,null,0,0,0);
            }
            //groupchat
            CUDGroupChat(notebookId,"delete",null);
            //collobrators including owner
            collobrators = notebook.getCollaborators();
            for(int userId : collobrators) {
                User user = userDao.getUserById(userId);
                ArrayList<Integer>notebooks = user.getNotebooks();
                for(int i = 0;i < notebooks.size();i++){
                    if(notebooks.get(i) == notebookId){
                        notebooks.remove(i);
                        break;
                    }
                }
                user.setNotebooks(notebooks);
                userDao.updateUser(user);
            }
            //tags
            tags = notebook.getTags();
            for(int tagId : tags){
                Tag tag = tagDao.getTagById(tagId);
                ArrayList<Integer> booksOfTag = tag.getBooksOfTag();
                for(int i = 0;i < booksOfTag.size();i++){
                    if(booksOfTag.get(i) == notebookId){
                        booksOfTag.remove(i);
                        break;
                    }
                }
                tag.setBooksOfTag(booksOfTag);
                tagDao.updateTag(tag);
            }
            //TODO collected users
            //TODO verify
            //notebookDao.deleteNotebook(notebook);
        }
        return 1;
    }

    public List<Notebook> RNotebooks(){
        return notebookDao.getAllNotebooks();
    }

    public int CUDNotice(int userId, String op, ArrayList<String> notices){
        if(op.equals("create")) {
            if(noticeDao.getNoticeById(userId) == null){
                noticeDao.addNotice(new Notice(userId, notices));
            }
        } else if(op.equals("update")) {
            Notice notice = noticeDao.getNoticeById(userId);
            notice.setNotices(notices);
            noticeDao.updateNotice(notice);
        } else if(op.equals("delete")) {
            noticeDao.deleteNotice(noticeDao.getNoticeById(userId));
        }
        return 1;
    }

    public List<Notice> RNotice(){
        return noticeDao.getAllNotices();
    }

    public int CUDRule(int ruleId, String op, String rules){
        if(op.equals("create")) {
            ruleDao.addRule(new Rule(rules));
        } else if(op.equals("update")) {
            Rule rule = ruleDao.getRuleById(ruleId);
            rule.setRule(rules);
            ruleDao.updateRule(rule);
        } else if(op.equals("delete")) {
            ruleDao.deleteRule(ruleDao.getRuleById(ruleId));
        }
        return 1;
    }

    public List<Rule> RRule(){
        return ruleDao.getAllRules();
    }

    public int CUDSuggestion(int suggestionId, String op, int noteId, int notebookId, int userId, String username, String content, String issue, Date raiseTime, String status){
        if(op.equals("create")) {
            suggestionDao.addSuggestion(new Suggestion(userId, noteId, notebookId, content, issue, raiseTime, status, username));
        } else if(op.equals("update")) {
            Suggestion suggestion = suggestionDao.getSuggestionById(suggestionId);
            suggestion.setNoteId(noteId);
            suggestion.setNotebookId(notebookId);
            suggestion.setUserId(userId);
            suggestion.setUsername(username);
            suggestion.setContent(content);
            suggestion.setIssue(issue);
            suggestion.setRaiseTime(raiseTime);
            suggestion.setStatus(status);
            suggestionDao.updateSuggestion(suggestion);
        } else if(op.equals("delete")) {
            suggestionDao.deleteSuggestion(suggestionDao.getSuggestionById(suggestionId));
        }
        return 1;
    }

    public List<Suggestion> RSuggestion(){
        return suggestionDao.getAllSuggestions();
    }

    public int CUDTag(int tagId, String op, String tagName, ArrayList<Integer> booksOfTag){
        if(op.equals("create")) {
            tagDao.addTag(new Tag(tagName, booksOfTag));   //add 只能是空值
        } else if(op.equals("update")) {
            Tag tag = tagDao.getTagById(tagId);
            tag.setTagName(tagName);
            tag.setBooksOfTag(booksOfTag);  //该属性不能在这里修改
            tagDao.updateTag(tag);
        } else if(op.equals("delete")) {
            tagDao.deleteTag(tagDao.getTagById(tagId));
            for(int bookId : booksOfTag) {
                Notebook notebook = notebookDao.getNotebookById(bookId);
                ArrayList<Integer> tags = notebook.getTags();
                for(int i = 0;i < tags.size();i++) {
                    if(tags.get(i) == tagId){
                        tags.remove(i);
                        break;
                    }
                }
                notebook.setTags(tags);
                notebookDao.updateNotebook(notebook);
            }
        }
        return 1;
    }

    public List<Tag> RTag(){
        return tagDao.getAllTags();
    }

    public int CUDUser(int userId, String op, String username, String personalStatus, ArrayList<Integer> notebooks,
                       ArrayList<Integer> followers, ArrayList<Integer> followings, ArrayList<Integer> tags, String avatar, ArrayList<Integer> collections, int valid, int deleteCount,
                       int reputation, String qrcode){
        if(op.equals("create")) {
            userDao.addUser(new User(username, personalStatus, notebooks, followers, followings, tags, avatar, collections, valid, deleteCount, reputation, qrcode));
        } else if(op.equals("update")) {
            User user = userDao.getUserById(userId);
            user.setUsername(username);
            user.setPersonalStatus(personalStatus);
            user.setNotebooks(notebooks); //
            user.setFollowers(followers); //
            user.setFollowings(followings); //
            user.setTags(tags);
            user.setAvatar(avatar);
            user.setCollections(collections); //
            user.setValid(valid);
            user.setDeleteCount(deleteCount);
            user.setReputation(reputation);
            user.setQrcode(qrcode);
            userDao.updateUser(user);
        } else if(op.equals("delete")) {
            // avoid deleting other objects by foreign key
            User user = userDao.getUserById(userId);
            //notebooks
            notebooks = user.getNotebooks();
            for(int notebookId : notebooks) {
                Notebook notebook = notebookDao.getNotebookById(notebookId);
                if(notebook.getOwner() == userId){
                    notebook.setOwner(-1);
                }
                ArrayList<Integer> collaborators = notebook.getCollaborators();
                for(int i = 0; i < collaborators.size();i++){
                    if(collaborators.get(i) == userId){
                        collaborators.remove(i);
                        break;
                    }
                }
                ArrayList<Integer> contributors = notebook.getContributors();
                for(int i = 0; i < contributors.size(); i++) {
                    if(contributors.get(i) == userId) {
                        contributors.remove(i);
                        break;
                    }
                }
                //TODO starers
                //TODO contributors but not collaborators
                notebookDao.updateNotebook(notebook);
            }
            //followers
            followers = user.getFollowers();
            for(int followerId : followers) {
                User follower = userDao.getUserById(followerId);
                ArrayList<Integer> hisFollowings = follower.getFollowings();
                for(int i = 0;i < hisFollowings.size();i++) {
                    if(hisFollowings.get(i) == userId) {
                        hisFollowings.remove(i);
                        break;
                    }
                }
                userDao.updateUser(follower);
            }
            //following
            followings = user.getFollowings();
            for(int followingId : followings) {
                User following = userDao.getUserById(followingId);
                ArrayList<Integer>hisFollowers = following.getFollowers();
                for(int i = 0;i < hisFollowers.size();i++) {
                    if(hisFollowers.get(i) == userId){
                        hisFollowers.remove(i);
                        break;
                    }
                }
                userDao.updateUser(following);
            }

            //userInfo
            //userInfoDao.deleteUserInfo(userInfoDao.getUserInfoById(userId));
            user.setValid(0);
            userDao.updateUser(user);
        }
        return 1;
    }

    public List<User> RUser(){
        return userDao.getAllUsers();
    }

    /* add 和 delete 操作与 User 对应的操作同时执行 */
    public int CUDUserInfo(int userId, String op, String username, String password, String phone, String email, String role){
        if(op.equals("create")) {
            userInfoDao.addUserInfo(new UserInfo(username, password, phone, email, role));
        } else if(op.equals("update")) {
            UserInfo userInfo = userInfoDao.getUserInfoById(userId);
            userInfo.setUsername(username);  //unique controller确保
            userInfo.setPassword(password);  //encoded controller加密
            userInfo.setPhone(phone);
            userInfo.setEmail(email);
            userInfo.setRole(role);
            userInfoDao.updateUserInfo(userInfo);
        } else if(op.equals("delete")) {
            userInfoDao.deleteUserInfo(userInfoDao.getUserInfoById(userId));
        }
        return 1;
    }

    public ArrayList<UserInfo> RUserInfo(){
        return userInfoDao.getAllUserInfos();
    }

    public ArrayList<JsonObject> verifyNoteList(){
        List<Verify> verifies = verifyDao.getAllVerifies();
        ArrayList<JsonObject> result = new ArrayList<JsonObject>();
        for(Verify verify : verifies) {
            if(verify.getType() == 1 && verify.getChecked() == 0) {
                JsonObject obj = new JsonObject();
                Note note = noteDao.getNoteById(verify.getTargetId());
                obj.addProperty("verifyId", verify.getVerifyId());
                obj.addProperty("noteId", note.getNoteId());
                obj.addProperty("title", note.getTitle());
                obj.addProperty("content", note.getHistory().get(note.getVersionPointer()));
                obj.addProperty("reporterId", verify.getReporterId());
                obj.addProperty("reason", verify.getReason());
                obj.addProperty("date", verify.getDate().toString());
                obj.addProperty("checked", verify.getChecked());
                result.add(obj);
            }
        }
        return result;
    }

    public ArrayList<Comment> verifyCommentList(){
        List<Verify> verifies = verifyDao.getAllVerifies();
        ArrayList<Comment> result = new ArrayList<Comment>();
        for(Verify verify : verifies) {
            if(verify.getType() == 0) {
                result.add(commentDao.getCommentById(verify.getTargetId()));
            }
        }
        return result;
    }

    public ArrayList<Notebook> verifyNotebookList() {
        List<Verify> verifies = verifyDao.getAllVerifies();
        ArrayList<Notebook> result = new ArrayList<Notebook>();
        for(Verify verify : verifies) {
            if(verify.getType() == 2) {
                result.add(notebookDao.getNotebookById(verify.getTargetId()));
            }
        }
        return result;
    }

    public void banNote(int verifyId) {
        Verify verify = verifyDao.getVerifyById(verifyId);
        verify.setChecked(1);
        verifyDao.updateVerify(verify);
        int noteId = verify.getTargetId();
        CUDNote(noteId, "delete", 0,"",null,null,null,null,0,0,0 );
    }
}
