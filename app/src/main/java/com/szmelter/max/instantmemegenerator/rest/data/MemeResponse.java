package com.szmelter.max.instantmemegenerator.rest.data;


import com.szmelter.max.instantmemegenerator.meme.data.Meme;

import java.util.ArrayList;
import java.util.List;

public class MemeResponse {

    private boolean success;
    private Data data;

    public class Data {
        private List<Meme> memes = new ArrayList<>();

    }

    public List<Meme> getMemes() {
        return this.data.memes;
    }
}
