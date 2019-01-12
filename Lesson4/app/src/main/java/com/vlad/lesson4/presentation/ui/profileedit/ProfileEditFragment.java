package com.vlad.lesson4.presentation.ui.profileedit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Friend;
import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.vlad.lesson4.presentation.ui.splashscreen.SplashScreenActivity.SECONDS_SLEEP;

public class ProfileEditFragment extends BaseFragment implements ProfileEditMvpView {

    public final static String FRAGMENT_TAG_PROFILE = "fragment_tag_profile";

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private View rootView;
    private ViewFlipper viewFlipper;
    private ImageView imageViewUser;
    private TextView textViewFullName;
    private TextView textViewBirthData;
    private TextView textViewFieldActivity;
    private ProfileEditPresenter profileEditPresenter;
    private AlertDialog dialog;
    private MenuItem menuItem;
    private RecyclerView recyclerView;
    private ProfileEditAdapter profileEditAdapter;

    public static ProfileEditFragment getInstance() {
        return new ProfileEditFragment();
    }

    public ProfileEditFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        profileEditPresenter = getApplicationComponents().provideProfileEditPresenter();
        profileEditAdapter = getApplicationComponents().provideProfileEditAdapter();
        profileEditPresenter.attachView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        BottomNavigationViewEx bottomNavigationView = Objects.requireNonNull(getActivity())
                .findViewById(R.id.bottomNavigationMenu);
        menuItem = bottomNavigationView.getMenu().findItem(R.id.i_profile);
        imageViewUser = rootView.findViewById(R.id.imageViewUser);
        textViewFullName = rootView.findViewById(R.id.textViewFullName);
        textViewBirthData = rootView.findViewById(R.id.textViewBirthData);
        textViewFieldActivity = rootView.findViewById(R.id.textViewFieldActivity);
        recyclerView = rootView.findViewById(R.id.recyclerViewFriends);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(profileEditAdapter);
        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(inflater.inflate(R.layout.dialog_profile_edit, null)).create();
        profileEditPresenter.onCreate();
        return rootView;
    }

    @Override
    public void onPause() {
        menuItem.setEnabled(true);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        profileEditPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void clickOnImageUser() {
        imageViewUser.setOnClickListener(view -> dialog.show());
    }

    @Override
    public void showInfoUser(User user, ArrayList<Friend> arrayListFriends) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        String fullName = user.getLastName() + MainActivity.EMPTY + user.getName();
        textViewFullName.setText(fullName);
        String fullDataBirth = user.getBirthDay() + MainActivity.EMPTY + user.getBirthMonth()
                + MainActivity.EMPTY + user.getBirthYears();
        imageViewUser.setImageResource(R.drawable.image_man);
        textViewBirthData.setText(fullDataBirth);
        textViewFieldActivity.setText(user.getFieldActivity());
        profileEditAdapter.setArrayListFriends(arrayListFriends);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.edit_profile).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void showLoadingError() {
        viewFlipper.setDisplayedChild(VIEW_ERROR);
    }

    @Override
    public void showProgressView() {
        viewFlipper.setDisplayedChild(VIEW_LOADING);
    }
}
