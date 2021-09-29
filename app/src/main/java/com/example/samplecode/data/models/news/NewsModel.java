package com.example.samplecode.data.models.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel {
    @SerializedName("status")
    private String status;
    @SerializedName("totlaResults")
    private int totalResults;
    @SerializedName("articles")
    private List<NewsMainContent> contents;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsMainContent> getContents() {
        return contents;
    }

    public void setContents(List<NewsMainContent> contents) {
        this.contents = contents;
    }
}



