package com.szmelter.max.instantmemegenerator.meme;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.szmelter.max.instantmemegenerator.meme.data.Meme;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MemeActivityStateFragment extends Fragment {

    public static String TAG = MemeActivityStateFragment.class.getSimpleName();

    private MemePresenter memePresenter;

    private Meme meme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
