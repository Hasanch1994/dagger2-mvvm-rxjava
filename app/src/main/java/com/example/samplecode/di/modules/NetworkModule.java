package com.example.samplecode.di.modules;

import com.example.samplecode.data.rest.NewsApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModules.class)
public class NetworkModule {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final int REQUEST_TIMEOUT = 60;

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Request-Type", "Android")
                        .addHeader("Content-Type", "application/json")
                        .build();

                return chain.proceed(newRequest);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        return client;

    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static NewsApiService provideRetrofitService(Retrofit retrofit){
        return retrofit.create(NewsApiService.class);
    }

}
