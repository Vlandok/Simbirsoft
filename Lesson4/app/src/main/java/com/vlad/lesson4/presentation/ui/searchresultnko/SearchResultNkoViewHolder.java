package com.vlad.lesson4.presentation.ui.searchresultnko;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.SearchResultsNko;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultNkoViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewTitleSearchNko;
    private ImageView imageViewGreyLineNko;
    private ImageView imageViewFullGreyLineNko;

    public ImageView getImageViewFullGreyLineNko() {
        return imageViewFullGreyLineNko;
    }

    public ImageView getImageViewGreyLineNko() {
        return imageViewGreyLineNko;
    }

    public TextView getTextViewTitleSearchNko() {
        return textViewTitleSearchNko;
    }

    public SearchResultNkoViewHolder(@NonNull View itemView, final SearchResultNkoAdapter.OnItemClickListener listener,
                                     ArrayList<SearchResultsNko> items) {
        super(itemView);
        textViewTitleSearchNko = itemView.findViewById(R.id.textViewTitleSearchNko);
        imageViewGreyLineNko = itemView.findViewById(R.id.imageViewGreyLineNko);
        imageViewFullGreyLineNko = itemView.findViewById(R.id.imageViewFullGreyLineNko);

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
