package com.example.pizzaselector;

// this class lets us make an object with all of our info for a single DB entry
// it is just constructors, getters, and setters

public class DBItem {

    private String sauce;
    private String top1;
    private String top2;
    private String top3;
    private int _id;

    public DBItem(int _id, String sauce, String top1, String top2, String top3) {
        this.sauce = sauce;
        this.top1 = top1;
        this.top2 = top2;
        this.top3 = top3;
        this._id = _id;
    }

    public DBItem(String sauce, String top1, String top2, String top3) {
        this.sauce = sauce;
        this.top1 = top1;
        this.top2 = top2;
        this.top3 = top3;
    }

    public DBItem() {

    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getTop1() {
        return top1;
    }

    public void setTop1(String top1) {
        this.top1 = top1;
    }

    public String getTop2() {
        return top2;
    }

    public void setTop2(String top2) {
        this.top2 = top2;
    }

    public String getTop3() {
        return top3;
    }

    public void setTop3(String top3) {
        this.top3 = top3;
    }

    public int get_id() {
        return _id;
    }


}


