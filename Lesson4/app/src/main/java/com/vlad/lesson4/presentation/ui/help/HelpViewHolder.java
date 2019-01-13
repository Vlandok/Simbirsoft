package com.vlad.lesson4.presentation.ui.help;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.ItemForChooseCategoryHelp;

import java.util.List;

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
                          List<ItemForChooseCategoryHelp> itemsForChooseCategory) {
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
