package com.vlad.lesson4.presentation.ui.сharityevents;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Event;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class CharityEventsViewHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView imageViewEvent;
    private TextView textViewTitleEvent;
    private TextView textViewDescriptionEvent;
    private TextView textViewTimeEvent;

    public AppCompatImageView getImageViewEvent() {
        return imageViewEvent;
    }

    public TextView getTextViewDescriptionEvent() {
        return textViewDescriptionEvent;
    }

    public TextView getTextViewTimeEvent() {
        return textViewTimeEvent;
    }

    public TextView getTextViewTitleEvent() {
        return textViewTitleEvent;
    }

    public CharityEventsViewHolder(@NonNull View itemView,
                                   final CharityEventsAdapter.OnEventClickListener listener,
                                   List<Event> events) {
        super(itemView);

        imageViewEvent = itemView.findViewById(R.id.imageViewEvent);
        textViewTitleEvent = itemView.findViewById(R.id.textViewTitleEvent);
        textViewDescriptionEvent = itemView.findViewById(R.id.textViewDescriptionEvent);
        textViewTimeEvent = itemView.findViewById(R.id.textViewTimeEvent);

        itemView.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClickEvent(events.get(position));
                }
            }
        });
    }
}
