package service;

import model.mongodb.*;
import model.mysql.Auth;
import model.mysql.UserInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import model.mysql.Auth;
import model.mysql.UserInfo;

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
    ArrayList<Comment> RComment();


    /**
     * CUDGroupChat
     * @param notebookId 操作对象
     * @param op 操作类型
     * @param userId table field
     * @param content table field
     * @param datetime table field
     * @return 1/0
     */
    int CUDGroupChat(int notebookId, String op, int userId, String content, Date datetime);
    /**
     * RGroupChat
     * @return 所有group chat
     */
    ArrayList<GroupChat> RGroupChat();


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
    int CUDLetter(int letterId, String op, int senderId, int receiverId, String content, Date datetime);
    /**
     * RLetter
     * @return 所有Letter
     */
    ArrayList<Letter> RLetter();


    /**
     * CUDNote
     * @param noteId 操作对象
     * @param op 操作类型
     * @param notebookId table field
     * @param title table field
     * @param editTime table field
     * @param message table field
     * @param content table field
     * @param editerId table field
     * @param comment table field
     * @param upvoteUserId table field
     * @param downvoteUserId table field
     * @param report table field
     * @param valid table field
     * @return 1/0
     */
    int CUDNote(int noteId, String op, int notebookId, String title, Date editTime, String message, String content, int editerId,
                String comment, int upvoteUserId, int downvoteUserId, int report, int valid);
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
     * @param collobrator table field
     * @param contributor table field
     * @param noteId table field
     * @param createTime table field
     * @return 1/0
     */
    int CUDNotebook(int notebookId, String op, String title, String description, int creator, int owner, int star, int collected,
                    int count, int collobrator, int contributor, int noteId, Date createTime);
    /**
     * Rnotebooks
     * @return 所有notebook
     */
    ArrayList<Notebook> RNotebooks();


    /**
     * CUDNotice
     * @param noticeId 操作对象
     * @param op 操作类型
     * @param userId table field
     * @param read table field
     * @param content table field
     * @return 1/0
     */
    int CUDNotice(int noticeId, String op, int userId, int read, String content);
    /**
     * RNotice
     * @return 所有用户的通知
     */
    ArrayList<Notice> RNotice();


    /**
     * CUDRule
     * @param ruleId 操作对象
     * @param op 操作类型
     * @param rule table field
     * @param datetime table field
     * @return 1/0
     */
    int CUDRule(int ruleId, String op, String rule, Date datetime);
    /**
     * RRule
     * @return 所有rules
     */
    ArrayList<Rule> RRule();


    /**
     * CUDSuggestion
     * @param suggestionId 操作对象
     * @param op 操作类型
     * @param noteId table field
     * @param notebookId table field
     * @param content table field
     * @param issue table field
     * @param datetime table field
     * @param status table field
     * @return 1/0
     */
    int CUDSuggestion(int suggestionId, String op, int noteId, int notebookId, String content, String issue, Date datetime, String status);
    /**
     * RSuggestion
     * @return 所有suggestion
     */
    ArrayList<Suggestion> RSuggestion();


    /**
     * CUDTag
     * @param tagId 操作对象
     * @param op 操作类型
     * @param tagName table field
     * @param notebookId table field
     * @return 1/0
     */
    int CUDTag(int tagId, String op, String tagName, int notebookId);

    /**
     * RTag
     * @return 所有tags
     */
    ArrayList<Tag> RTag();


    /**
     * CUDUser
     * @param userId 操作对象
     * @param op 操作类型
     * @param username table field
     * @param personalStatus table field
     * @param notebookId table field
     * @param followerId table field
     * @param followeeId table field
     * @param tagId table field
     * @param avator table field
     * @param collectionId table field
     * @param valid table field
     * @param deleteTime table field
     * @param honor table field
     * @param reward table field
     * @param qrcode table field
     * @return 1/0
     */
    int CUDUser(int userId, String op, String username, String personalStatus, int notebookId,
                int followerId, int followeeId, int tagId, File avator, int collectionId, int valid, Date deleteTime,
                int honor, int reward, File qrcode);
    /**
     * RUser
     * @return 所有User
     */
    ArrayList<User> RUser();


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
    ArrayList<Note> verifyNoteList();

    /**
     * verifyCommentList
     * @return 所有需要被管理员审核的Comment
     */
    ArrayList<Comment> verifyCommentList();





}
