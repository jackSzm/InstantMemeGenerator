package com.szmelter.max.instantmemegenerator.meme;

import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.szmelter.max.instantmemegenerator.R;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;
import com.szmelter.max.instantmemegenerator.rest.RestService;
import com.szmelter.max.instantmemegenerator.service.ImageService;

import java.util.List;

import rx.Observable;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MemePresenter {

    private RestService restService;
    private ImageService imageService;

    private Observable<List<Meme>> memeListRequest;

    public MemePresenter(RestService restService, ImageService imageService) {
        this.restService = restService;
        this.imageService = imageService;
    }

    public void loadMemes(MemeActivity activity) {
        if (this.memeListRequest == null) {
            this.memeListRequest = AndroidObservable
                    .bindActivity(activity, this.restService.getMemes())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .cache();
        }

        this.memeListRequest.subscribe(
                l -> onMemesLoaded(activity, l),
                e -> activity.showMessage(R.string.message_empty_list));
    }

    public void onMemesLoaded(MemeActivity activity, List<Meme> memeList) {
        if (memeList.isEmpty()) {
            activity.showMessage(R.string.message_empty_list);
        } else {
            activity.showMemes(memeList);
        }
    }

    public void onMemeItemViewClicked(MemeActivity activity, Meme meme) {
        activity.saveMemeInStateFragment(meme);
        activity.showMemeDialog();
    }


    public void loadMemeImage(Meme meme, ImageView imageView) {
        this.imageService.uploadImage(meme.getUrl(), imageView);
    }

    public void createMeme(MemeDialog memeDialog, Meme meme, String topText, String bottomText) {
        AndroidObservable
                .bindFragment(memeDialog, this.restService.createMeme(meme.getId(), topText, bottomText))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(url -> onMemeCreated(memeDialog, url));
    }

    protected void onMemeCreated(MemeDialog memeDialog, String memeUrl) {
        memeDialog.dismiss();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(memeUrl));
        memeDialog.getActivity().startActivity(i);
    }
}
