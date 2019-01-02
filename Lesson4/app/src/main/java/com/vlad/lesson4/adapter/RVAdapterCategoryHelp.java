package com.vlad.lesson4.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad.lesson4.R;
import com.vlad.lesson4.model.ItemForChooseCategoryHelp;

import java.util.ArrayList;

public class RVAdapterCategoryHelp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ViewHolder viewHolder;
    private ArrayList<ItemForChooseCategoryHelp> itemsForChooseCategory;
    private OnItemClickListener itemListener;

    public interface OnItemClickListener {
        void onClickItem(ItemForChooseCategoryHelp item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemListener = listener;
    }

    public RVAdapterCategoryHelp(ArrayList<ItemForChooseCategoryHelp> itemForChooseCategoryHelps) {
        itemsForChooseCategory = itemForChooseCategoryHelps;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        viewHolder = new ViewHolder(view, itemListener, itemsForChooseCategory);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vHolder, int position) {

        viewHolder = (ViewHolder) vHolder;
        viewHolder.getAppCompatImageView().setBackgroundResource(itemsForChooseCategory.get(position).getImageCategory());
        viewHolder.getTextViewItemCategory().setText(itemsForChooseCategory.get(position).getNameCategory());
    }

    @Override
    public int getItemCount() {
        return itemsForChooseCategory.size();
    }
}
