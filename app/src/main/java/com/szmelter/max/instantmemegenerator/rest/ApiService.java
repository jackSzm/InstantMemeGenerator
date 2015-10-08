package com.szmelter.max.instantmemegenerator.rest;


import com.szmelter.max.instantmemegenerator.rest.data.MemeBuilderResponse;
import com.szmelter.max.instantmemegenerator.rest.data.MemeResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

public interface ApiService {

    @GET("/get_memes")
    Observable<MemeResponse> getMemes();

    @FormUrlEncoded
    @POST("/caption_image")
    Observable<MemeBuilderResponse> buildMeme(
            @Field("template_id") String id,
            @Field("username") String username,
            @Field("password") String password,
            @Field("text0") String topText,
            @Field("text1") String bottomText);

}
