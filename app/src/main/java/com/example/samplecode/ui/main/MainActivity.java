package com.example.samplecode.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.samplecode.Base.BaseActivity;
import com.example.samplecode.R;
import com.example.samplecode.data.models.news.NewsModel;
import com.example.samplecode.databinding.ActivityMainBinding;
import com.example.samplecode.utils.ViewModelFactory;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;
    private MainViewModel mainViewModel;

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this,viewModelFactory).get(MainViewModel.class);
        binding.setViewmodel(mainViewModel);
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setAdapter(new MainAdapter(mainViewModel,this));
        observerViewModel();

    }

    private void observerViewModel() {
        mainViewModel.getNews().observe(this, new Observer<NewsModel>() {
            @Override
            public void onChanged(NewsModel newsModel) {
                if(newsModel !=null) binding.recycler.setVisibility(View.VISIBLE);
            }
        });

        mainViewModel.getError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if(isError !=null && isError)
                    Toast.makeText(getApplicationContext(), "خطایی رخ داده است", Toast.LENGTH_SHORT).show();
            }
        });

        mainViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading){
                    binding.progress.setVisibility(View.VISIBLE);
                    binding.recycler.setVisibility(View.GONE);
                } else{
                    binding.progress.setVisibility(View.INVISIBLE);
                    binding.recycler.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}