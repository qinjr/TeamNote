package model.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
@Document(collection = "User")
public class User {
    private int userId;
    private String username;
    private String personalStatus;
    private ArrayList<Integer> notebooks;
    private ArrayList<Integer> followers;
    private ArrayList<Integer> followings;
    private ArrayList<Integer> tags;
    private String avatar;
    private ArrayList<Integer> collections;
    private int valid;
    private int deleteCount;
    private int reputation;
    private String reward;

    public User() {}

    public User(String username, String personalStatus, ArrayList<Integer> notebooks,
                ArrayList<Integer> followers, ArrayList<Integer> followings, ArrayList<Integer> tags,
                String avatar, ArrayList<Integer> collections, int valid, int deleteCount, int reputation,
                String reward) {
        this.username = username;
        this.personalStatus = personalStatus;
        this.notebooks = notebooks;
        this.followers = followers;
        this.followings = followings;
        this.tags = tags;
        this.avatar = avatar;
        this.collections = collections;
        this.valid = valid;
        this.deleteCount = deleteCount;
        this.reputation = reputation;
        this.reward = reward;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonalStatus() {
        return personalStatus;
    }

    public void setPersonalStatus(String personalStatus) {
        this.personalStatus = personalStatus;
    }

    public ArrayList<Integer> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(ArrayList<Integer> notebooks) {
        this.notebooks = notebooks;
    }

    public ArrayList<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Integer> followers) {
        this.followers = followers;
    }

    public ArrayList<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(ArrayList<Integer> followings) {
        this.followings = followings;
    }

    public ArrayList<Integer> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Integer> tags) {
        this.tags = tags;
    }

    public String getavatar() {
        return avatar;
    }

    public void setavatar(String avatar) {
        this.avatar = avatar;
    }

    public int getDeleteCount() {
        return deleteCount;
    }

    public void setDeleteCount(int deleteCount) {
        this.deleteCount = deleteCount;
    }

    public ArrayList<Integer> getCollections() {
        return collections;
    }

    public void setCollections(ArrayList<Integer> collections) {
        this.collections = collections;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getDeleteTime() {
        return deleteCount;
    }

    public void setDeleteTime(int deleteCount) {
        this.deleteCount = deleteCount;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
