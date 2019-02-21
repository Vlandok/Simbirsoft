package com.vlad.lesson4.presentation.ui.authorization;

import android.widget.Button;
import android.widget.EditText;

public class AuthorizationViewHolder {
    public final EditText editTextEmail;
    public final EditText editTextPassword;
    public final Button buttonEntry;

    public AuthorizationViewHolder(EditText editTextEmail,
                                   EditText editTextPassword, Button buttonEntry) {
        this.editTextEmail = editTextEmail;
        this.editTextPassword = editTextPassword;
        this.buttonEntry = buttonEntry;
    }
}
