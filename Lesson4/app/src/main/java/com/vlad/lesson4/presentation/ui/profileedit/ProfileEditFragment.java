package com.vlad.lesson4.presentation.ui.profileedit;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.main.MainActivity;

import java.util.Objects;

public class ProfileEditFragment extends BaseFragment implements ProfileEditMvpView {

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private View rootView;
    private ViewFlipper viewFlipper;
    private ImageView imageViewUser;
    private TextView textViewFullName;
    private TextInputEditText textInputEditTextBirthData;
    private TextInputEditText textInputEditTextFieldActivity;
    private ProfileEditPresenter profileEditPresenter;
    private AlertDialog dialog;

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
        profileEditPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        profileEditPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        imageViewUser = rootView.findViewById(R.id.imageViewUser);
        textViewFullName = rootView.findViewById(R.id.textViewFullName);
        textInputEditTextBirthData = rootView.findViewById(R.id.textInputEditTextBirthData);
        textInputEditTextFieldActivity = rootView.findViewById(R.id.textInputEditTextFieldActivity);
        viewFlipper = rootView.findViewById(R.id.viewFlipper);

        dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(inflater.inflate(R.layout.dialog_profile_edit, null)).create();

        profileEditPresenter.onCreate();
        return rootView;
    }

    @Override
    public void clickOnImageUser() {
        imageViewUser.setOnClickListener(view -> dialog.show());
    }

    @Override
    public void showInfoUser(User user) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        String fullName = user.getLastName() + MainActivity.EMPTY + user.getName();
        textViewFullName.setText(fullName);
        String fullDataBirth = user.getBirthDay() + MainActivity.EMPTY + user.getBirthMonth()
                + MainActivity.EMPTY + user.getBirthYears();
        imageViewUser.setImageResource(R.drawable.image_man);
        textInputEditTextBirthData.setText(fullDataBirth);
        textInputEditTextFieldActivity.setText(user.getFieldActivity());
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
