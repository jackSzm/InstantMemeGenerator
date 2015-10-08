package com.szmelter.max.instantmemegenerator.meme.data;

import lombok.Getter;

public class Meme {

    @Getter
    private String id;
    @Getter
    private String name;
    @Getter
    private String url;

    private int width;
    private int height;
}