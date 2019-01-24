package com.vlad.lesson4.presentation.ui.searchresultevents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.SearchResults;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultEventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SearchResultEventsViewHolder searchResultViewHolder;
    private ArrayList<SearchResults> itemsSearchResults;
    private SearchResultEventsAdapter.OnItemClickListener itemListener;

    public interface OnItemClickListener {
        void onClickItem(SearchResults item);
    }

    public void setOnItemClickListener(SearchResultEventsAdapter.OnItemClickListener listener) {
        itemListener = listener;
    }

    public void setItemsSearchResults(ArrayList<SearchResults> searchResults) {
        this.itemsSearchResults = searchResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result_events, parent, false);
        searchResultViewHolder = new SearchResultEventsViewHolder(view, itemListener, itemsSearchResults);
        return searchResultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vHolder, int position) {
        searchResultViewHolder = (SearchResultEventsViewHolder) vHolder;
        searchResultViewHolder.getTextViewTitleSearch().setText(itemsSearchResults.get(position).getTitle());
        if (position == itemsSearchResults.size() - 1) {
            searchResultViewHolder.getImageViewGreyLine().setVisibility(View.GONE);
            searchResultViewHolder.getImageViewFullGreyLine().setVisibility(View.VISIBLE);
        } else {
            searchResultViewHolder.getImageViewGreyLine().setVisibility(View.VISIBLE);
            searchResultViewHolder.getImageViewFullGreyLine().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemsSearchResults.size();
    }


}