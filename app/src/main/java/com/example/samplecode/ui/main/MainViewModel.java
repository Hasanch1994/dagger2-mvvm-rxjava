package com.example.samplecode.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.samplecode.data.models.news.NewsModel;
import com.example.samplecode.data.rest.NewsRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private final NewsRepository newsRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<NewsModel> news = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public MainViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        disposable = new CompositeDisposable();
        fetchNews();
    }

    LiveData<NewsModel> getNews() {
        return this.news;
    }

    LiveData<Boolean> getError() {
        return this.repoLoadError;
    }

    LiveData<Boolean> getLoading() {
        return this.loading;
    }

    private void fetchNews() {
        loading.setValue(true);
        disposable.add(
                newsRepository
                        .getNews()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<NewsModel>() {
                            @Override
                            public void onSuccess(NewsModel value) {
                                repoLoadError.setValue(false);
                                loading.setValue(false);
                                news.setValue(value);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("main","error is " + e.getLocalizedMessage());
                                loading.setValue(false);
                                repoLoadError.setValue(true);
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
