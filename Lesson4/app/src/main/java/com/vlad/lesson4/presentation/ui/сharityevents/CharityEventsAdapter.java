package com.vlad.lesson4.presentation.ui.сharityevents;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.utils.Date;
import com.vlad.lesson4.utils.MyGlide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.vlad.lesson4.presentation.ui.charityeventdetail.CharityEventDetailActivity.getStatus;

public class CharityEventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnEventClickListener listener;
    private List<Event> arrayListCharityEvents;
    private CharityEventsViewHolder charityEventsViewHolder;

    public interface OnEventClickListener {
        void onClickEvent(Event event);
    }

    public void setOnEventClickListener(OnEventClickListener eventClickListener) {
        listener = eventClickListener;
    }

    public void setArrayListCharityEvents(List<Event> arrayListCharityEvents) {
        this.arrayListCharityEvents = arrayListCharityEvents;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_charity_event, parent, false);
        charityEventsViewHolder = new CharityEventsViewHolder(view, listener, arrayListCharityEvents);
        return charityEventsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        charityEventsViewHolder = (CharityEventsViewHolder) holder;
        Resources res = charityEventsViewHolder.itemView.getContext().getResources();
        Context context = charityEventsViewHolder.itemView.getContext();
        charityEventsViewHolder.getTextViewTitleEvent().
                setText(arrayListCharityEvents.get(position).getName());
        MyGlide.loadImageWithGetDrawable(context, arrayListCharityEvents.get(position).getImageMain(),
                charityEventsViewHolder.getImageViewEvent());
        charityEventsViewHolder.getTextViewDescriptionEvent()
                .setText(arrayListCharityEvents.get(position).getDescription());
        long days = Date.getDays(arrayListCharityEvents.get(position).getTimeStart(),
                arrayListCharityEvents.get(position).getTimeFinish());
        charityEventsViewHolder.getTextViewTimeEvent()
                .setText(getStatus(days, arrayListCharityEvents.get(position), res));
    }

    @Override
    public int getItemCount() {
        if (arrayListCharityEvents == null) {
            return 0;
        } else {
            return arrayListCharityEvents.size();
        }
    }
}
