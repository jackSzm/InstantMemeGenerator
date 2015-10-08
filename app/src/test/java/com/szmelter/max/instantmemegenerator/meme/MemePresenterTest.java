package com.szmelter.max.instantmemegenerator.meme;

import android.widget.ImageView;

import com.szmelter.max.instantmemegenerator.R;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;
import com.szmelter.max.instantmemegenerator.rest.RestService;
import com.szmelter.max.instantmemegenerator.service.ImageService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemePresenterTest {

    MemePresenter memePresenter;

    @Mock
    RestService mockRestService;

    @Mock
    ImageService mockImageService;

    @Mock
    MemeActivity mockActivity;

    @Mock
    Meme mockMeme;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.memePresenter = new MemePresenter(this.mockRestService, this.mockImageService);
    }

    @Test
    public void showsMemsInTheActivity_whenReturnedListIsNotEmpty() throws Exception {
        //Given
        List<Meme> memeList = new ArrayList<>();
        memeList.add(this.mockMeme);

        //When
        this.memePresenter.onMemesLoaded(this.mockActivity, memeList);

        //Then
        verify(this.mockActivity, times(1)).showMemes(memeList);
    }

    @Test
    public void showsNoItemsMessage_whenReturnedMemeListIsEmpty() throws Exception {
        //Given
        List<Meme> memeList = new ArrayList<>();

        //When
        this.memePresenter.onMemesLoaded(this.mockActivity, memeList);

        //Then
        verify(this.mockActivity, times(1)).showMessage(R.string.message_empty_list);
    }

    @Test
    public void savesMemeInStateFragment_whenMemeItemViewClicked() throws Exception {
        //When
        this.memePresenter.onMemeItemViewClicked(this.mockActivity, this.mockMeme);

        //Then
        verify(this.mockActivity, times(1)).saveMemeInStateFragment(this.mockMeme);
    }

    @Test
    public void showsMemeDialog_whenMemeItemViewClicked() throws Exception {
        //When
        this.memePresenter.onMemeItemViewClicked(this.mockActivity, this.mockMeme);

        //Then
        verify(this.mockActivity, times(1)).showMemeDialog();
    }

    @Test
    public void loadsMemeImageFromUrl_whenLoadMemeImageCalled() throws Exception {
        //Given
        String mockUrl = "mockUrl";
        when(this.mockMeme.getUrl()).thenReturn(mockUrl);
        ImageView mockImageView = mock(ImageView.class);

        //When
        this.memePresenter.loadMemeImage(this.mockMeme, mockImageView);

        //Then
        verify(this.mockImageService, times(1)).uploadImage(mockUrl, mockImageView);
    }

    @Test
    public void dismissesDialog_whenMemeIsCreated() throws Exception {
        //Given
        MemeDialog mockDialog = mock(MemeDialog.class);
        when(mockDialog.getActivity()).thenReturn(this.mockActivity);

        //When
        this.memePresenter.onMemeCreated(mockDialog, "");

        //Then
        verify(mockDialog, times(1)).dismiss();
    }

    @Test
    public void firesViewIntent_whenMemeIsCreated() throws Exception {
        //Given
        MemeDialog mockDialog = mock(MemeDialog.class);
        when(mockDialog.getActivity()).thenReturn(this.mockActivity);

        //When
        this.memePresenter.onMemeCreated(mockDialog, "");

        //Then
        verify(this.mockActivity, times(1)).startActivity(any());

    }
}