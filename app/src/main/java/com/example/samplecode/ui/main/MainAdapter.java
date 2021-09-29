package com.example.samplecode.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplecode.R;
import com.example.samplecode.data.models.news.NewsModel;
import com.example.samplecode.data.models.news.NewsMainContent;
import com.example.samplecode.databinding.NewsLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder>{

    private final List<NewsMainContent> list = new ArrayList<>();

    public MainAdapter(MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getNews()
                .observe(lifecycleOwner, new Observer<NewsModel>() {
                    @Override
                    public void onChanged(NewsModel newsModel) {
                        list.clear();
                        if(newsModel !=null){
                            list.addAll(newsModel.getContents());
                            notifyDataSetChanged();
                        }
                    }
                });
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NewsLayoutBinding binding = DataBindingUtil.inflate(inflater,R.layout.news_layout,parent,false);
        return new MainHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {

        NewsMainContent content = list.get(position);
        holder.binding.setItem(content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static final class MainHolder extends RecyclerView.ViewHolder{
        NewsLayoutBinding binding;
        public MainHolder(@NonNull NewsLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

        }
    }


}
