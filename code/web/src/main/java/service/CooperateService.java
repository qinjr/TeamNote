package service;

import java.util.Date;

/**
 * Created by qjr on 2017/6/27.
 */
public interface CooperateService {
    /**
     * inviteCooperator
     * @param userId 邀请者Id
     * @param notebookId 笔记本Id
     * @return 1为成功发送邀请，0为失败
     */
    public int inviteCooperator(int userId, int notebookId);


    /**
     * takeInvitation
     * @param userId 受邀者Id
     * @param notebookId 笔记本Id
     * @return 1为接受，0为拒绝
     */
    public int takeInvitation(int userId, int notebookId);


    /**
     * pushUpdate
     * @param userId 更新者Id
     * @param content 更新后笔记内容
     * @param datetime 更新时间
     * @return 1为更新成功，0为失败
     */
    public int pushUpdate(int userId, String content, Date datetime);


    /**
     * reset（笔记版本回滚）
     * @param userId 回滚者Id
     * @param noteId 笔记Id
     * @return 1为回滚成功，0为失败
     */
    public int reset(int userId, int noteId);
}
