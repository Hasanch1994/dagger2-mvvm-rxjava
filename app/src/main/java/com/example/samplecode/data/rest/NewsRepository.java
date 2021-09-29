package com.example.samplecode.data.rest;

import com.example.samplecode.data.models.news.NewsModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class NewsRepository {
    private final NewsApiService newsApiService;

    @Inject
    public NewsRepository(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    public Single<NewsModel> getNews(){
        return this.newsApiService.getnews();
    }

}
