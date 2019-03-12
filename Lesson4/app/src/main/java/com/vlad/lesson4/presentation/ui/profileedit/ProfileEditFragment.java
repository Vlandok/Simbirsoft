package com.vlad.lesson4.presentation.ui.profileedit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.vlad.lesson4.MyApplication;
import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.Friend;
import com.vlad.lesson4.data.model.User;
import com.vlad.lesson4.presentation.ui.authorization.AuthorizationActivity;
import com.vlad.lesson4.presentation.ui.base.BaseFragment;
import com.vlad.lesson4.presentation.ui.main.MainActivity;
import com.vlad.lesson4.utils.MyGlide;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileEditFragment extends BaseFragment implements ProfileEditMvpView {

    public final static String FRAGMENT_TAG_PROFILE = "FRAGMENT_TAG_PROFILE";

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private View rootView;
    private ViewFlipper viewFlipper;
    private ImageView imageViewUser;
    private TextView textViewFullName;
    private TextView textViewBirthData;
    private TextView textViewFieldActivity;
    @Inject
    @InjectPresenter
    ProfileEditPresenter profileEditPresenter;
    private AlertDialog dialog;
    private MenuItem menuItem;
    private RecyclerView recyclerView;
    private ProfileEditAdapter profileEditAdapter;
    private Unbinder unbinder;
    private TextView textViewTitleToolbar;

    @BindView(R.id.buttonExitAccount)
    Button buttonExitAccount;
    @BindView(R.id.switchNotify)
    Switch switchNotify;

    @ProvidePresenter
    ProfileEditPresenter provideProfileEditPresenter() {
        return profileEditPresenter;
    }

    public static ProfileEditFragment getInstance() {
        return new ProfileEditFragment();
    }

    public ProfileEditFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MyApplication.getInstance(getActivity())
                .plusActivityComponent((AppCompatActivity) getActivity()).inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        BottomNavigationViewEx bottomNavigationView = Objects.requireNonNull(getActivity())
                .findViewById(R.id.bottomNavigationMenu);
        textViewTitleToolbar = Objects.requireNonNull(getActivity())
                .findViewById(R.id.textViewToolbar);
        menuItem = bottomNavigationView.getMenu().findItem(R.id.i_profile);
        imageViewUser = rootView.findViewById(R.id.imageViewUser);
        textViewFullName = rootView.findViewById(R.id.textViewFullName);
        textViewBirthData = rootView.findViewById(R.id.textViewBirthData);
        textViewFieldActivity = rootView.findViewById(R.id.textViewFieldActivity);
        recyclerView = rootView.findViewById(R.id.recyclerViewFriends);
        profileEditAdapter = new ProfileEditAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(profileEditAdapter);
        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setView(inflater.inflate(R.layout.dialog_profile_edit, null)).create();
        profileEditPresenter.initObservableSwitch(RxCompoundButton.checkedChanges(switchNotify));
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        textViewTitleToolbar.setText(R.string.profil_bottom_nav);
        menuItem.setEnabled(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        menuItem.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance(getActivity()).clearActivityComponent();
        unbinder.unbind();
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
        switchNotify.setChecked(user.getIsPushNotifications());
        String fullDataBirth = user.getBirthDay() + MainActivity.EMPTY + user.getBirthMonth()
                + MainActivity.EMPTY + user.getBirthYears();
        textViewBirthData.setText(fullDataBirth);
        textViewFieldActivity.setText(user.getFieldActivity());
        profileEditAdapter.setArrayListFriends(arrayListFriends);
    }

    @Override
    public void setImageUser(String urlImage) {
        if (getActivity() != null) {
            MyGlide.loadImage(this, urlImage, imageViewUser);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.edit_profile).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void clickToExitAccount() {
        buttonExitAccount.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = AuthorizationActivity.createStartIntent(getActivity());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
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
