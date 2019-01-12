package com.vlad.lesson4.presentation.ui.profileedit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Friend;

import java.util.List;

public class ProfileEditViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewFriend;

    public TextView getTextViewFriend() {
        return textViewFriend;
    }

    public void setTextViewFriend(TextView textViewFriend) {
        this.textViewFriend = textViewFriend;
    }

    public ProfileEditViewHolder(@NonNull View itemView, final ProfileEditAdapter.OnItemClickListener listener,
                                 List<Friend> listFriends) {
        super(itemView);

        textViewFriend = itemView.findViewById(R.id.textViewFriend);

        textViewFriend.setOnClickListener(view -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClickItem(listFriends.get(position));
                }
            }
        });

    }
}
