package com.vlad.lesson4.presentation.ui.profileedit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Friend;

import java.util.List;

import static com.vlad.lesson4.presentation.ui.main.MainActivity.EMPTY;


public class ProfileEditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private ProfileEditViewHolder profileEditViewHolder;
    private OnItemClickListener itemListener;
    private List<Friend> arrayListFriends;

    public interface OnItemClickListener {
        void onClickItem(Friend item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemListener = listener;
    }

    public void setArrayListFriends (List<Friend> arrayListFriends ) {
    this.arrayListFriends = arrayListFriends;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend_profile, viewGroup, false);
        profileEditViewHolder = new ProfileEditViewHolder(view, itemListener, arrayListFriends);
        return profileEditViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d("bindViewHolder", "profile");

        profileEditViewHolder = (ProfileEditViewHolder) viewHolder;
        String fullName = arrayListFriends.get(i).getFirstName() + EMPTY +
                arrayListFriends.get(i).getLastName();
        profileEditViewHolder.getTextViewFriend().setText(fullName);
        profileEditViewHolder.getTextViewFriend()
                .setCompoundDrawablesWithIntrinsicBounds(arrayListFriends.get(i).getImageFriend(), 0,0,0);
    }

    @Override
    public int getItemCount() {
        return arrayListFriends.size();
    }
}
