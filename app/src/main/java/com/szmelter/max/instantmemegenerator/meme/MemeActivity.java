package com.szmelter.max.instantmemegenerator.meme;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.szmelter.max.instantmemegenerator.R;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;
import com.szmelter.max.instantmemegenerator.rest.RestService;
import com.szmelter.max.instantmemegenerator.service.ImageService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import lombok.Getter;


@EActivity(R.layout.meme_activity)
public class MemeActivity extends AppCompatActivity {

    @ViewById(R.id.main_recyclerview)
    RecyclerView recyclerView;

    @ViewById(R.id.main_message_textview)
    TextView messageTextView;

    @Bean
    ImageService imageService;

    @Bean
    RestService restService;

    @Getter
    protected MemePresenter memePresenter;
    private MemeAdapter memeAdapter;
    private MemeActivityStateFragment stateFragment;

    MemeDialog memeDialog;

    @AfterViews
    protected void setupActivity() {
        setupRecyclerView();
        setupStateFragment();
    }

    private void setupRecyclerView() {
        this.recyclerView.setHasFixedSize(true);
        this.memeAdapter = new MemeAdapter();
        this.recyclerView.setAdapter(this.memeAdapter);
    }

    private void setupStateFragment() {
        this.stateFragment = getStateFragment();
        if (this.stateFragment == null) {
            buildStateFragment();
        } else {
            this.memePresenter = this.stateFragment.getMemePresenter();
        }
    }

    private MemeActivityStateFragment getStateFragment() {
        FragmentManager fm = getSupportFragmentManager();
        return (MemeActivityStateFragment) fm.findFragmentByTag(MemeActivityStateFragment.TAG);
    }

    private void buildStateFragment() {
        this.stateFragment = new MemeActivityStateFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(this.stateFragment, MemeActivityStateFragment.TAG).commit();

        this.memePresenter = new MemePresenter(this.restService, this.imageService);
        stateFragment.setMemePresenter(this.memePresenter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        showMessage(R.string.message_loading);
        this.memePresenter.loadMemes(this);
    }

    public void showMessage(int messageResId) {
        this.messageTextView.setText(getString(messageResId));
        this.messageTextView.setVisibility(View.VISIBLE);
        this.recyclerView.setVisibility(View.GONE);
    }

    public void showMemes(List<Meme> memeList) {
        this.memeAdapter.updateMemes(memeList);
        this.messageTextView.setVisibility(View.GONE);
        this.recyclerView.setVisibility(View.VISIBLE);
    }

    public void saveMemeInStateFragment(Meme meme) {
        this.stateFragment.setMeme(meme);
    }

    public void showMemeDialog() {
        memeDialog = MemeDialog_.builder().build();
        memeDialog.show(getFragmentManager(), MemeDialog.class.getSimpleName());
    }

    public Meme getMemeFromStateFragment() {
        return this.stateFragment.getMeme();
    }
}
