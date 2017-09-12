package service;

import com.google.gson.JsonObject;
import model.mongodb.*;
import model.mysql.Auth;
import model.mysql.UserInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.mysql.Auth;
import model.mysql.UserInfo;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by qjr on 2017/6/27.
 */
public interface AdminService {
    /**
     * CUDAuth(Create,Update,Delete)
     * @param authId 操作对象
     * @param op 操作类型
     * @param userId 数据表field
     * @param notebookId 数据表field
     * @param role 数据表field
     * @return 1为成功，0为失败
     */
    int CUDAuth(int authId, String op, int userId, int notebookId, String role);
    /**
     * RAuth(Read Auth table)
     * @return 所有权限信息
     */
    ArrayList<Auth> RAuth();


    /**
     * CUDComment
     * @param commentId 操作对象
     * @param op 操作类型
     * @param userId 数据表field
     * @param datetime 数据表field
     * @param content 数据表field
     * @param report 数据表field
     * @param valid 数据表field
     * @return 1为操作成功，0为失败
     */
    int CUDComment(int commentId, String op, int userId, Date datetime, String content, int report, int valid);
    /**
     * RComment
     * @return 所有评论
     */
    List<Comment> RComment();


    /**
     * CUDGroupChat
     * @param notebookId 操作对象
     * @param op 操作类型
     * @param contents table field
     * @return 1/0
     */
    int CUDGroupChat(int notebookId, String op, ArrayList<String> contents);
    /**
     * RGroupChat
     * @return 所有group chat
     */
    List<GroupChat> RGroupChat();


    /**
     * CUDLetter
     * @param letterId 操作对象
     * @param op 操作类型
     * @param senderId table field
     * @param receiverId table field
     * @param content table field
     * @param datetime table field
     * @return 1/0
     */
    int CUDLetter(int letterId, String op, int senderId, int receiverId, String content, Date datetime, int read);
    /**
     * RLetter
     * @return 所有Letter
     */
    List<Letter> RLetter();


    /**
     * CUDNote
     * @param noteId 操作对象
     * @param op 操作类型
     * @param notebookId table field
     * @param title table field
     * @param history table field
     * @param comments table field
     * @param upvoters table field
     * @param downvoters table field
     * @param report table field
     * @param valid table field
     * @param versionPointer table field
     * @return 1/0
     */
    int CUDNote(int noteId, String op, int notebookId, String title, ArrayList<String> history,
                ArrayList<Integer> comments, ArrayList<Integer> upvoters, ArrayList<Integer> downvoters, int report, int valid, int versionPointer);
    //TODO
    /*需要粒度更细的Service，如对某个historyVersion的操作*/

    /**
     * RNoteOfNotebook
     * @param notebookId notebook id
     * @return 该笔记本所有的note
     */
    ArrayList<Note> RNoteOfNotebook(int notebookId);


    /**
     * CUDNotebook
     * @param notebookId 操作对象
     * @param op 操作类型
     * @param title table field
     * @param description table field
     * @param creator table field
     * @param owner table field
     * @param star table field
     * @param collected table field
     * @param count table field
     * @param collobrators table field
     * @param contributors table field
     * @param notes table field
     * @param createTime table field
     * @return 1/0
     */
    int CUDNotebook(int notebookId, String op, String title, String description, int creator, int owner, int star, int collected,
                    int count, ArrayList<Integer> collobrators, ArrayList<Integer> contributors, ArrayList<Integer> notes, Date createTime, String cover, ArrayList<Integer> tags, ArrayList<Integer> starers);
    /**
     * Rnotebooks
     * @return 所有notebook
     */
    List<Notebook> RNotebooks();


    /**
     * CUDNotice
     * @param op 操作类型
     * @param userId table field
     * @param notices table field
     * @return 1/0
     */
    int CUDNotice(int userId, String op, ArrayList<String> notices);
    /**
     * RNotice
     * @return 所有用户的通知
     */
    List<Notice> RNotice();


    /**
     * CUDRule
     * @param ruleId 操作对象
     * @param op 操作类型
     * @param rules table field
     * @return 1/0
     */
    int CUDRule(int ruleId, String op, String rules);
    /**
     * RRule
     * @return 所有rules
     */
    List<Rule> RRule();


    /**
     * CUDSuggestion
     * @param suggestionId 操作对象
     * @param op 操作类型
     * @param noteId table field
     * @param notebookId table field
     * @param content table field
     * @param issue table field
     * @param raiseTime table field
     * @param status table field
     * @param userId table field
     * @param username table field
     * @return 1/0
     */
    int CUDSuggestion(int suggestionId, String op, int noteId, int notebookId, int userId, String username, String content, String issue, Date raiseTime, String status);
    /**
     * RSuggestion
     * @return 所有suggestion
     */
    List<Suggestion> RSuggestion();


    /**
     * CUDTag
     * @param tagId 操作对象
     * @param op 操作类型
     * @param tagName table field
     * @param booksOfTag table field
     * @return 1/0
     */
    int CUDTag(int tagId, String op, String tagName, ArrayList<Integer> booksOfTag);

    /**
     * RTag
     * @return 所有tags
     */
    List<Tag> RTag();


    /**
     * CUDUser
     * @param userId 操作对象
     * @param op 操作类型
     * @param username table field
     * @param personalStatus table field
     * @param notebooks table field
     * @param followers table field
     * @param followings table field
     * @param tags table field
     * @param avatar table field
     * @param collections table field
     * @param valid table field
     * @param deleteCount table field
     * @param reputation table field
     * @param qrcode table field
     * @return 1/0
     */
    int CUDUser(int userId, String op, String username, String personalStatus, ArrayList<Integer> notebooks,
                ArrayList<Integer> followers, ArrayList<Integer> followings, ArrayList<Integer> tags, String avatar, ArrayList<Integer> collections, int valid, int deleteCount,
                int reputation, String qrcode);
    /**
     * RUser
     * @return 所有User
     */
    ArrayList<JsonObject> RUser();


    /**
     * CUDUserInfo
     * @param userId 操作对象
     * @param op 操作类型
     * @param username table field
     * @param password table field
     * @param phone table field
     * @param email table field
     * @param role table field
     * @return 1/0
     */
    int CUDUserInfo(int userId, String op, String username, String password, String phone, String email, String role);
    /**
     * RUserInfo
     * @return 所有UserInfo
     */
    ArrayList<UserInfo> RUserInfo();


    /**
     * verifyNoteList
     * @return 所有需要被管理员审核的Note
     */
    ArrayList<JsonObject> verifyNoteList();

    /**
     * verifyCommentList
     * @return 所有需要被管理员审核的Comment
     */
    ArrayList<JsonObject> verifyCommentList();
    void banComment(int verifyId);
    void ignoreComment(int verifyId);

    /**
     * verifyNotebookList
     * @return 所有需要被管理员审核的Notebook
     */
    ArrayList<Notebook> verifyNotebookList();

    void banNote(int verifyId);
    void ignoreNote(int verifyId);

    int changeUserRole(int userId);
}
