package com.szmelter.max.instantmemegenerator.meme;

import com.squareup.picasso.Picasso;
import com.szmelter.max.instantmemegenerator.BuildConfig;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class MemeItemViewTest {


    MemeActivity memeActivity;
    MemeItemView memeItemView;

    @Mock
    MemeActivity mockMemeActivity;

    @Mock
    MemePresenter mockMemePresenter;

    @Mock
    Meme mockMeme;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.memeActivity = Robolectric.buildActivity(MemeActivity_.class).create().start().resume().get();
        this.memeActivity.memePresenter = this.mockMemePresenter;

        this.memeItemView = MemeItemView_.build(this.memeActivity, null);
    }

    @Test
    public void loadsMemeImage_whenMemeIsSet() throws Exception {
        //Given
        String mockUrl = "mockurl";
        when(this.mockMeme.getUrl()).thenReturn(mockUrl);

        //When
        this.memeItemView.updateView(this.mockMeme);

        //Then
        verify(this.mockMemePresenter, times(1)).loadMemeImage(this.mockMeme, this.memeItemView.memeImageView);
    }

    @Test
    public void setsMemeNameIntoTitleTextView_whenMemeIsSet() throws Exception {
        //Given
        String mockName = "MOCK_NAME";
        when(this.mockMeme.getName()).thenReturn(mockName);

        //When
        this.memeItemView.updateView(this.mockMeme);

        //Then
        assertThat(this.memeItemView.titleTextView.getText()).isEqualTo(mockName);
    }

    @Test
    public void firesMemePresenterOnClickMethod_whenClickIsPerformed() throws Exception {
        //Given
        this.memeItemView.updateView(this.mockMeme);

        //When
        this.memeItemView.performClick();

        //Then
        verify(this.mockMemePresenter, times(1)).onMemeItemViewClicked(this.memeActivity, this.mockMeme);
    }

}