package com.vlad.lesson4.presentation.ui.searchresultevents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.SearchResults;

import java.util.ArrayList;

public class SearchResultEventsViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewTitleSearch;
    private ImageView imageViewGreyLine;
    private ImageView imageViewFullGreyLine;

    public ImageView getImageViewFullGreyLine() {
        return imageViewFullGreyLine;
    }

    public ImageView getImageViewGreyLine() {
        return imageViewGreyLine;
    }

    public TextView getTextViewTitleSearch() {
        return textViewTitleSearch;
    }

    public SearchResultEventsViewHolder(@NonNull View itemView, final SearchResultEventsAdapter.OnItemClickListener listener,
                                        ArrayList<SearchResults> items) {
        super(itemView);
        textViewTitleSearch = itemView.findViewById(R.id.textViewTitleSearchEvents);
        imageViewGreyLine = itemView.findViewById(R.id.imageViewGreyLineEvents);
        imageViewFullGreyLine = itemView.findViewById(R.id.imageViewFullGreyLineEvents);

        itemView.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClickItem(items.get(position));
                }
            }
        });
    }
}
