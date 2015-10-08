package com.szmelter.max.instantmemegenerator.meme;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szmelter.max.instantmemegenerator.R;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.meme_item_view)
public class MemeItemView extends RelativeLayout implements OnClickListener {

    @ViewById(R.id.meme_imageview)
    ImageView memeImageView;

    @ViewById(R.id.meme_title)
    TextView titleTextView;

    private MemePresenter memePresenter;

    private Meme meme;

    public MemeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.memePresenter = ((MemeActivity) context).getMemePresenter();
        this.setOnClickListener(this);
    }

    public void updateView(Meme meme) {
        this.meme = meme;
        this.memePresenter.loadMemeImage(this.meme, this.memeImageView);
        this.titleTextView.setText(this.meme.getName());
    }

    @Override
    public void onClick(View v) {
        this.memePresenter.onMemeItemViewClicked((MemeActivity) getContext(), meme);
    }
}
