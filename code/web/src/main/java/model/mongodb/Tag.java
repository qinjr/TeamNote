package model.mongodb;

import java.util.ArrayList;

/**
 * Created by qjr on 2017/6/27.
 */
public class Tag {
    private int tagId;
    private String tagName;
    private ArrayList<Integer> booksOfTag;

    public Tag() {}

    public Tag(String tagName, ArrayList<Integer> booksOfTag) {
        this.tagName = tagName;
        this.booksOfTag = booksOfTag;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ArrayList<Integer> getBooksOfTag() {
        return booksOfTag;
    }

    public void setBooksOfTag(ArrayList<Integer> booksOfTag) {
        this.booksOfTag = booksOfTag;
    }
}
