package com.vlad.lesson4.presentation.ui.searchresultnko;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.SearchResultsNko;

import java.util.ArrayList;

public class SearchResultNkoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SearchResultNkoViewHolder searchResultViewHolder;
    private ArrayList<SearchResultsNko> itemsSearchResults;
    private SearchResultNkoAdapter.OnItemClickListener itemListener;

    public interface OnItemClickListener {
        void onClickItem(SearchResultsNko item);
    }

    public void setOnItemClickListener(SearchResultNkoAdapter.OnItemClickListener listener) {
        itemListener = listener;
    }

    public void setItemsSearchResultsNko(ArrayList<SearchResultsNko> searchResultsNko) {
        this.itemsSearchResults = searchResultsNko;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result_nko, parent, false);
        searchResultViewHolder = new SearchResultNkoViewHolder(view, itemListener, itemsSearchResults);
        return searchResultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vHolder, int position) {
        searchResultViewHolder = (SearchResultNkoViewHolder) vHolder;
        searchResultViewHolder.getTextViewTitleSearchNko().setText(itemsSearchResults.get(position).getTitle());
        if (position == itemsSearchResults.size() - 1) {
            searchResultViewHolder.getImageViewGreyLineNko().setVisibility(View.GONE);
            searchResultViewHolder.getImageViewFullGreyLineNko().setVisibility(View.VISIBLE);
        } else {
            searchResultViewHolder.getImageViewGreyLineNko().setVisibility(View.VISIBLE);
            searchResultViewHolder.getImageViewFullGreyLineNko().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemsSearchResults.size();
    }


}