package com.szmelter.max.instantmemegenerator.service;


import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageServiceTest {

    @Mock
    Picasso mockPicasso;

    @Mock
    Context mockContext;

    ImageService imageService;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        Picasso.setSingletonInstance(this.mockPicasso);

        this.imageService = ImageService_.getInstance_(this.mockContext);
    }

    @Test
    public void loadImagesUsingPicasso() throws Exception {
        //Given
        RequestCreator requestCreator = mock(RequestCreator.class);
        when(this.mockPicasso.load(anyString())).thenReturn(requestCreator);
        when(requestCreator.placeholder(anyInt())).thenReturn(requestCreator);

        ImageView imageView = mock(ImageView.class);
        String mockUri = "MOCK_URI";

        //When
        this.imageService.uploadImage(mockUri, imageView);

        //Then
        verify(this.mockPicasso, times(1)).load(mockUri);
    }
}