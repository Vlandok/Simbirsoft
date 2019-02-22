package com.vlad.lesson4.presentation.ui.authorization;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AuthorizationViewHolder {
    public final EditText editTextEmail;
    public final EditText editTextPassword;
    public final Button buttonEntry;
    public final ImageButton imageButtonChangeVisiblePassword;

    public AuthorizationViewHolder(EditText editTextEmail, EditText editTextPassword,
                                   Button buttonEntry, ImageButton imageButtonChangeVisiblePassword) {
        this.editTextEmail = editTextEmail;
        this.editTextPassword = editTextPassword;
        this.buttonEntry = buttonEntry;
        this.imageButtonChangeVisiblePassword = imageButtonChangeVisiblePassword;
    }
}
