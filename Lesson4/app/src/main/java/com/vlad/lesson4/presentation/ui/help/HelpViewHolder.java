package com.vlad.lesson4.presentation.ui.help;


import android.view.View;
import android.widget.TextView;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Category;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class HelpViewHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView appCompatImageView;
    private TextView textViewItemCategory;

    public AppCompatImageView getAppCompatImageView() {
        return appCompatImageView;
    }

    public TextView getTextViewItemCategory() {
        return textViewItemCategory;
    }

    public HelpViewHolder(@NonNull View itemView, final HelpAdapter.OnItemClickListener listener,
                          List<Category> itemsForChooseCategory) {
        super(itemView);
        appCompatImageView = itemView.findViewById(R.id.appCompactImageViewItem);
        textViewItemCategory = itemView.findViewById(R.id.textViewTitleItem);
        itemView.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClickItem(itemsForChooseCategory.get(position));
                }
            }
        });
    }
}
