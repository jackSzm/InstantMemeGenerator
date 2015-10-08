package com.szmelter.max.instantmemegenerator.meme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szmelter.max.instantmemegenerator.R;
import com.szmelter.max.instantmemegenerator.meme.data.Meme;

import java.util.ArrayList;
import java.util.List;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.ViewHolder> {

    private List<Meme> memeList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        MemeItemView memeItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.memeItemView = (MemeItemView) itemView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MemeItemView memeItemView = (MemeItemView)
                LayoutInflater.from(parent.getContext()).inflate(R.layout.meme_item, parent, false);
        return new ViewHolder(memeItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meme meme = this.memeList.get(position);
        holder.memeItemView.updateView(meme);
    }

    @Override
    public int getItemCount() {
        return this.memeList.size();
    }

    public void updateMemes(List<Meme> memeList) {
        this.memeList = memeList;
        notifyDataSetChanged();
    }
}
