package com.vlad.lesson4.adapter;

<<<<<<< HEAD

=======
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
>>>>>>> e25e3b9aa008e37f189e16ff5aa129c4dcddd7b3
import android.view.View;
import android.widget.TextView;

import com.vlad.lesson4.R;
<<<<<<< HEAD
import com.vlad.lesson4.data.model.ItemForChooseCategoryHelp;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

=======
import com.vlad.lesson4.model.ItemForChooseCategoryHelp;

import java.util.ArrayList;

>>>>>>> e25e3b9aa008e37f189e16ff5aa129c4dcddd7b3
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
