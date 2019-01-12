package com.vlad.lesson4.presentation.ui.help;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.ItemForChooseCategoryHelp;

import java.util.ArrayList;
import java.util.List;

public class HelpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HelpViewHolder helpViewHolder;
    private List<ItemForChooseCategoryHelp> itemsForChooseCategory = new ArrayList<>();
    private OnItemClickListener itemListener;

    public interface OnItemClickListener {
        void onClickItem(ItemForChooseCategoryHelp item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemListener = listener;
    }

    public void setHelpCategory(ArrayList<ItemForChooseCategoryHelp> itemsForChooseCategory) {
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

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vHolder, int position) {
        helpViewHolder = (HelpViewHolder) vHolder;
        helpViewHolder.getAppCompatImageView().setBackgroundResource(itemsForChooseCategory.get(position).getImageCategory());
        helpViewHolder.getTextViewItemCategory().setText(itemsForChooseCategory.get(position).getNameCategory());
    }

    @Override
    public int getItemCount() {
        return itemsForChooseCategory.size();
    }


}
