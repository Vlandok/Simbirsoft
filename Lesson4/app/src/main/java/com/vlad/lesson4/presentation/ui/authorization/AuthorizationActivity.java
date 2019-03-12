package com.vlad.lesson4.presentation.ui.authorization;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.vlad.lesson4.R;
import com.vlad.lesson4.presentation.ui.base.BaseActivity;
import com.vlad.lesson4.presentation.ui.main.MainActivity;

import java.util.Objects;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vlad.lesson4.presentation.ui.main.MainActivity.EMPTY;

public class AuthorizationActivity extends BaseActivity implements AuthorizationMvpView {
    @BindView(R.id.toolbarAuthorization)
    Toolbar toolbar;
    @BindView(R.id.textViewToolbar)
    TextView titleToolbar;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.buttonLogin)
    Button buttonLogin;
    @BindView(R.id.progressBarAuthorization)
    ProgressBar progressBar;
    @BindView(R.id.imageButtonChangeVisiblePassword)
    ImageButton imageButtonChangeVisiblePassword;

    @InjectPresenter
    AuthorizationPresenter authorizationPresenter;

    public static Intent createStartIntent(Context context) {
        return new Intent(context, AuthorizationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        ButterKnife.bind(this);
        setSettingsToolbar();
        authorizationPresenter.initEmailObservable(RxTextView.textChanges(editTextEmail));
        authorizationPresenter
                .initPasswordObservable(RxTextView.textChanges(editTextPassword));
        authorizationPresenter.clickToButtonEntry(RxView.clicks(buttonLogin));
        authorizationPresenter
                .clickChangeVisibilityPassword(RxView.clicks(imageButtonChangeVisiblePassword));
        authorizationPresenter.checkValidateEmailAndPass();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setEntryButtonActive() {
        buttonLogin.setClickable(true);
        buttonLogin.setTextColor(getResources().getColor(R.color.white));
        buttonLogin.setBackground(getResources().getDrawable(R.drawable.rectangle_green_account));
    }

    @Override
    public void clickEntryButton() {
        if (this.isTaskRoot()) {
            Intent intent = MainActivity.createStartIntent(this);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        finish();
    }

    @Override
    public void showAlertError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AuthorizationActivity.this);
        builder.setTitle(getString(R.string.error))
                .setMessage(R.string.error_sign_up)
                .setCancelable(true)
                .setPositiveButton(getString(R.string.ok),
                        (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void showProgressView() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setEntryButtonInactive() {
        buttonLogin.setClickable(false);
        buttonLogin.setTextColor(getResources().getColor(R.color.grey_three));
        buttonLogin.setBackground(getResources().getDrawable(R.drawable.rectangle_grey_account));
    }

    private void setSettingsToolbar() {
        toolbar.setTitle(EMPTY);
        titleToolbar.setText(R.string.authorization);
        if (!this.isTaskRoot()) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null && !this.isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void clickChangeVisibilityPassword() {
        int start = editTextPassword.getSelectionStart();
        int end = editTextPassword.getSelectionEnd();
        Drawable drawableNow = imageButtonChangeVisiblePassword.getDrawable();
        Drawable drawableClose = getResources().getDrawable(R.drawable.ic_close);
        Drawable drawableOpen = getResources().getDrawable(R.drawable.ic_open);
        if (Objects.requireNonNull(drawableNow.getConstantState())
                .equals(drawableClose.getConstantState())) {
            imageButtonChangeVisiblePassword.setImageDrawable(drawableOpen);
            editTextPassword.setTransformationMethod(null);
        }
        if (Objects.requireNonNull(drawableNow.getConstantState())
                .equals(drawableOpen.getConstantState())) {
            imageButtonChangeVisiblePassword.setImageDrawable(drawableClose);
            editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
        }
        editTextPassword.setSelection(start, end);
    }
}
