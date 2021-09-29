package com.example.samplecode.data.rest;

import com.example.samplecode.data.models.news.NewsModel;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NewsApiService {

    @GET("top-headlines?country=us&category=business&apiKey=6023711f14d24b77a442378e8ee954f5")
    Single<NewsModel> getnews();

}
