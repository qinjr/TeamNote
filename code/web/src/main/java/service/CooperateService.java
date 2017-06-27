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
     * @return 接受请求页面的链接（之后由通知服务向指定用户发送通知）
     */
    public String inviteCooperator(int userId, int notebookId);


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
     * @param noteId 更新笔记Id
     * @param content 更新后笔记内容
     * @param datetime 更新时间
     * @return 1为更新成功，0为失败
     */
    public int pushUpdate(int userId, int noteId, String content, Date datetime);


    /**
     * reset（笔记版本回滚）
     * @param userId 回滚者Id
     * @param noteId 笔记Id
     * @return 1为回滚成功，0为失败
     */
    public int reset(int userId, int noteId);


    /**
     * raiseAdvice
     * @param userId 提意见者Id
     * @param noteId 提出意见的noteId
     * @param content 修改后的笔记内容
     * @param description 解释修改的原因
     * @return 审核contributor修改的链接
     */
    public String raiseAdvice(int userId, int noteId, String content, String description);


    /**
     *
     * @param userId 提出意见者的Id
     * @param noteId 被提出意见的note的Id
     * @param datetime 提出意见的时间（在Suggestion表中寻找到要merge的版本）（做为审核contributor修改的链接的一部分传入）
     * @return 1为接受意见merge成功，0为未merge
     */
    public int mergeAdvice(int userId, int noteId, Date datetime);
}
