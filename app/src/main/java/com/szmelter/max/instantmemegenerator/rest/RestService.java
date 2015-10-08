package com.szmelter.max.instantmemegenerator.rest;

import android.content.Context;

import com.szmelter.max.instantmemegenerator.R;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import retrofit.RestAdapter;
import rx.Observable;

@EBean
public class RestService {

    private ApiService apiService;

    @RootContext
    protected Context context;

    @AfterInject
    public void buildApiService() {

        String restEndpoint = this.context.getString(R.string.rest_endpoint);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(restEndpoint)
                .build();

        this.apiService = restAdapter.create(ApiService.class);
    }

    public Observable<List<Meme>> getMemes() {
        return this.apiService.getMemes()
                .map(memeResponse -> memeResponse.getMemes());
    }

    public Observable<String> createMeme(String memeId, String topText, String bottomText) {
        String username = this.context.getString(R.string.rest_username);
        String password = this.context.getString(R.string.rest_password);

        return this.apiService.buildMeme(memeId, username, password, topText, bottomText)
                .map(memeBuilderResponse -> memeBuilderResponse.getData().getUrl());
    }
}
