package com.vlad.lesson4.presentation.ui.help;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Category;
import com.vlad.lesson4.utils.MyGlide;

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

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vHolder, int position) {
        helpViewHolder = (HelpViewHolder) vHolder;
        Context context = helpViewHolder.itemView.getContext();
        MyGlide.loadImage(context, itemsForChooseCategory.get(position).getImage(),
                helpViewHolder.getAppCompatImageView());
        helpViewHolder.getTextViewItemCategory().setText(itemsForChooseCategory.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (itemsForChooseCategory == null) {
            return 0;
        } else {
            return itemsForChooseCategory.size();
        }
    }


}
