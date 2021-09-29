package com.example.samplecode.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samplecode.R;
import com.example.samplecode.data.models.news.NewsModel;
import com.example.samplecode.ui.main.MainAdapter;
import com.example.samplecode.ui.main.MainViewModel;

import javax.inject.Inject;

public class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void SetImageUrl(ImageView img,String url){
        Glide.with(img.getContext())
                .load(url)
                .placeholder(R.drawable.default_img)
                .error(R.drawable.default_img)
                .into(img);
    }

}
