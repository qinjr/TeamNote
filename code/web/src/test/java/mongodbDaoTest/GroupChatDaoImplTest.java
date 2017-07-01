package mongodbDaoTest;

import com.google.gson.JsonObject;
import dao.mongodbDao.GroupChatDao;
import model.mongodb.GroupChat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by qjr on 2017/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class GroupChatDaoImplTest {
    @Autowired
    private GroupChatDao groupChatDao;

    @Test
    public void testAddGroupChat() {
        JsonObject json = new JsonObject();
        json.addProperty("userId", 1);
        json.addProperty("datetime", new Date().toString());
        json.addProperty("content", "haha");
        ArrayList<String> chats = new ArrayList<String>();
        chats.add(json.toString());
        GroupChat groupChat = new GroupChat(1, chats);

        groupChatDao.addGroupChat(groupChat);
        Assert.assertNotNull(groupChatDao.getAllGroupChats().get(0));
        Assert.assertEquals(groupChatDao.getAllGroupChats().get(0).getContents(), chats);

        groupChatDao.deleteGroupChat(groupChat);
    }

    @Test
    public void testDeleteGroupChat() {
        JsonObject json = new JsonObject();
        json.addProperty("userId", 1);
        json.addProperty("datetime", new Date().toString());
        json.addProperty("content", "haha");
        ArrayList<String> chats = new ArrayList<String>();
        chats.add(json.toString());
        GroupChat groupChat = new GroupChat(1, chats);

        groupChatDao.addGroupChat(groupChat);
        groupChat = groupChatDao.getAllGroupChats().get(0);
        groupChatDao.deleteGroupChat(groupChat);

        Assert.assertEquals(groupChatDao.getAllGroupChats().size(), 0);
    }

    @Test
    public void testUpdateGroupChat() {
        JsonObject json = new JsonObject();
        json.addProperty("userId", 1);
        json.addProperty("datetime", new Date().toString());
        json.addProperty("content", "haha");
        ArrayList<String> chats = new ArrayList<String>();
        chats.add(json.toString());
        GroupChat groupChat = new GroupChat(1, chats);

        groupChatDao.addGroupChat(groupChat);
        groupChat = groupChatDao.getAllGroupChats().get(0);
        ArrayList<String> newList = new ArrayList<String>();
        newList.add("hhh");
        groupChat.setContents(newList);
        groupChatDao.updateGroupChat(groupChat);

        Assert.assertEquals(groupChatDao.getAllGroupChats().get(0).getContents(), newList);

        groupChatDao.deleteGroupChat(groupChat);
    }

    @Test
    public void testGetGroupChatById() {
        JsonObject json = new JsonObject();
        json.addProperty("userId", 1);
        json.addProperty("datetime", new Date().toString());
        json.addProperty("content", "haha");
        ArrayList<String> chats = new ArrayList<String>();
        chats.add(json.toString());
        GroupChat groupChat = new GroupChat(1, chats);

        groupChatDao.addGroupChat(groupChat);
        groupChat = groupChatDao.getAllGroupChats().get(0);
        int notebookId = groupChat.getNotebookId();

        Assert.assertEquals(groupChatDao.getGroupChatById(notebookId).getContents(), groupChat.getContents());

        groupChatDao.deleteGroupChat(groupChat);
    }
}
