package com.vlad.lesson4.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vlad.lesson4.R;
import com.vlad.lesson4.model.ItemForChooseCategoryHelp;

import java.util.ArrayList;

public class ViewHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView appCompatImageView;
    private TextView textViewItemCategory;

    public AppCompatImageView getAppCompatImageView() {
        return appCompatImageView;
    }

    public TextView getTextViewItemCategory() {
        return textViewItemCategory;
    }

    public ViewHolder(@NonNull View itemView, final RVAdapterCategoryHelp.OnItemClickListener listener,
                      ArrayList<ItemForChooseCategoryHelp> itemsForChooseCategory) {
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
