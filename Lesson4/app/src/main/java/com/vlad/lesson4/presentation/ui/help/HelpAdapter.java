package com.vlad.lesson4.presentation.ui.help;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Category;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HelpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HelpViewHolder helpViewHolder;
    private List<Category> itemsForChooseCategory = new ArrayList<>();
    private OnItemClickListener itemListener;

    public interface OnItemClickListener {
        void onClickItem(Category item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemListener = listener;
    }

    public void setHelpCategory(List<Category> itemsForChooseCategory) {
        this.itemsForChooseCategory = itemsForChooseCategory;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        helpViewHolder = new HelpViewHolder(view, itemListener, itemsForChooseCategory);
        return helpViewHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vHolder, int position) {
        helpViewHolder = (HelpViewHolder) vHolder;
        Context context = helpViewHolder.itemView.getContext();
        Glide.with(context)
                .load(itemsForChooseCategory.get(position).getImage())
                .into(helpViewHolder.getAppCompatImageView());
        helpViewHolder.getTextViewItemCategory().setText(itemsForChooseCategory.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemsForChooseCategory.size();
    }


}
