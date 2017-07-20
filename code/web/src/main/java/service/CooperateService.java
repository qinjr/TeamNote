package service;

import com.google.gson.JsonObject;
import model.mongodb.GroupChat;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public interface CooperateService {
    /**
     * inviteCooperator
     * @param inviterId 邀请者Id
     * @param targetId 受邀者Id
     * @param notebookId 笔记本Id
     * @param description 邀请描述
     * @return 1为请求发送成功，0为失败
     */
    int inviteCooperator(int inviterId, int targetId, int notebookId, String description);


    /**
     *
     * @param inviterId 邀请者Id
     * @param notebookId 笔记本Id，抽取笔记本详细信息
     * @param description 邀请者要对受邀者说的话
     * @return 邀请详细信息
     */
    String viewInvitation(int inviterId, int notebookId, String description);


    /**
     * takeInvitation
     * @param userId 受邀者Id
     * @param decision 是否接受邀请，1为接受，0不接受
     * @param notebookId 笔记本
     * @return 1为接受，0为拒绝
     */
    int takeInvitation(int userId, int decision, int notebookId);


    /**
     * raiseSuggestion
     * @param userId 提意见者Id
     * @param noteId 提出意见的noteId
     * @param content 修改后的笔记内容
     * @param issue 解释修改的原因
     * @param datetime 提交修改建议的时间
     * @return 1为提交成功，0为提交失败
     */
    int raiseSuggestion(int userId, int noteId, String content, String issue, Date datetime, String username);


    /**
     * mergeAdvice
     * @param managerId 笔记管理者的Id
     * @param noteId 被提出意见的note的Id
     * @param suggestionId 建议Id
     * @return 1为接受意见merge成功，0为未merge
     */
    int mergeSuggestion(int managerId, int noteId, int suggestionId);

    /**
     * ignoreSuggestion
     * @param suggestionId id
     * @return 1为成功
     */
    void ignoreSuggestion(int suggestionId);


    /**
     * getSuggestion
     * @param suggestionId suggestion
     * @return suggestion的具体内容
     */
    String getSuggestion(int suggestionId);


    /**
     * sendGroupChat
     * @param userId 发送小组群聊消息的用户id
     * @param notebookId 所在的notebook的id
     * @param datetime 发送时间
     * @param content 消息内容
     * @return 1为发送成功，0为失败
     */
    int sendGroupChat(int userId, int  notebookId, Date datetime, String content);


    /**
     * getGroupChat
     * @param notebookId 合作者所在的笔记本
     * @return 这个工作组所有的群聊内容
     */
    ArrayList<String> getGroupChat(int notebookId);

    /**
     *
     * @param notebookId 笔记本id
     * @param lastChat 需要的最后一条聊天记录的index
     * @return  由index向前得到的10条聊天记录的JSONArray String
     */
    String getGroupChatChunk(int notebookId, int lastChat);

    /**
     * giveOwnership
     * @param oldOwnerId 原来的owner
     * @param newOwnerId 新owner
     * @param notebookId 指定notebook
     * @return 1为成功发送转让请求，0为失败
     */
    int giveOwnership(int oldOwnerId, int newOwnerId, int notebookId);

    String getSuggestions(int noteId);

}