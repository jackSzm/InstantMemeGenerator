package com.szmelter.max.instantmemegenerator.meme;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.szmelter.max.instantmemegenerator.R;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.meme_dialog)
public class MemeDialog extends DialogFragment {

    private Meme meme;

    @ViewById(R.id.meme_dialog_imageview)
    protected ImageView imageView;

    @ViewById(R.id.meme_dialog_cancel_button)
    protected Button cancelButton;

    @ViewById(R.id.meme_dialog_generate_button)
    protected Button generateButton;

    @ViewById(R.id.meme_dialog_top_edittext)
    protected EditText topEditText;

    @ViewById(R.id.meme_dialog_bottom_edittext)
    protected EditText bottomEditText;

    public MemeDialog() {
    }


    @AfterViews
    public void loadMemeImage() {
        MemePresenter memePresenter = ((MemeActivity) getActivity()).getMemePresenter();
        memePresenter.loadMemeImage(this.meme, this.imageView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.meme = ((MemeActivity) getActivity()).getMemeFromStateFragment();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return null;
    }

    @Click(R.id.meme_dialog_generate_button)
    protected void generateMeme() {
        String topText = this.topEditText.getText().toString();
        String bottomText = this.bottomEditText.getText().toString();

        MemePresenter memePresenter = ((MemeActivity) getActivity()).getMemePresenter();
        memePresenter.createMeme(this, this.meme, topText, bottomText);
    }

    @Click(R.id.meme_dialog_cancel_button)
    protected void closeDialog() {
        this.dismiss();
    }
}
