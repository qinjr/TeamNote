package dao.mongodbDao;

import model.mongodb.UserBehavior;

import java.util.List;

/**
 * Created by qjr on 2017/7/20.
 */
public interface UserBehaviorDao {
    public void addUserBehavior(UserBehavior userBehavior);
    public void deleteUserBehavior(UserBehavior userBehavior);
    public void updateUserBehavior(UserBehavior userBehavior);
    public UserBehavior getUserBehaviorById(int userId);
    public List<UserBehavior> getUserBehaviors();
}
