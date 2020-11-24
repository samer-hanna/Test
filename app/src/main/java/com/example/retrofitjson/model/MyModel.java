package com.example.retrofitjson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyModel {
    @SerializedName("feed")
    @Expose
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public MyModel(Feed feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "Model{" +
                "feed=" + feed +
                '}';
    }
}
