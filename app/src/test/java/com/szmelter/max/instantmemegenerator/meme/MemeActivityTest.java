package com.szmelter.max.instantmemegenerator.meme;

import android.os.Bundle;

import com.szmelter.max.instantmemegenerator.BuildConfig;
import com.szmelter.max.instantmemegenerator.R;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.assertj.android.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class MemeActivityTest {

    MemeActivity activity;
    ActivityController<MemeActivity_> activityController;

    @Mock
    MemePresenter mockMemePresenter;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        runActivity();
    }

    private void runActivity() {
        runActivity(null);
    }

    private void runActivity(Bundle bundle) {
        this.activityController = Robolectric.buildActivity(MemeActivity_.class).create();
        this.activity = this.activityController.get();
        this.activity.memePresenter = this.mockMemePresenter;
        this.activityController.postCreate(bundle).start().resume();
    }

    @After
    public void after() throws Exception {
        killActivityIfPresent();
    }

    private void killActivityIfPresent() {
        if (this.activityController != null) {
            this.activityController.pause().stop().destroy();
        }
    }

    @Test
    public void loadingMessageVisible_whenActivityStarts() throws Exception {
        String loadingMessage = this.activity.getString(R.string.message_loading);
        assertThat(this.activity.messageTextView.getText()).isEqualTo(loadingMessage);
        assertThat(this.activity.messageTextView).isVisible();
    }

    @Test
    public void recyclerViewGone_whenActivityStarts() throws Exception {
        assertThat(this.activity.recyclerView).isGone();
    }


    @Test
    public void startsLoadingMemes_whenActivityCreated() throws Exception {
        verify(this.mockMemePresenter, times(1)).loadMemes(this.activity);
    }

    @Test
    public void createsMemeDialogFragment_whenShowMemeDialogCalled() throws Exception {
        //When
        this.activity.showMemeDialog();

        //Then
        this.activity.memeDialog.isVisible();
    }

    @Test
    public void stateFragmentHoldsData_whenActivityIsRecreated() throws Exception {
        //Given
        Meme meme = new Meme();
        this.activity.saveMemeInStateFragment(meme);

        //when
        this.activityController.restart();

        //Then
        assertThat(this.activity.getMemeFromStateFragment()).isEqualTo(meme);
    }
}
