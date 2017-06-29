package dao.mongodbDao;

import model.mongodb.GroupChat;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public interface GroupChatDao {
    void addGroupChat(GroupChat groupChat);
    void deleteGroupChat(GroupChat groupChat);
    void updateGroupChat(GroupChat groupChat);
    GroupChat getGroupChatById(int notebookId);
    ArrayList<GroupChat> getAllGroupChats();
}
