package com.szmelter.max.instantmemegenerator.service;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.szmelter.max.instantmemegenerator.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ImageService {

    @RootContext
    Context context;

    public void uploadImage(String uri, ImageView imageView) {
        Picasso.with(this.context)
                .load(uri)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }
}
