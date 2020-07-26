package com.sharifdev.torobche.backUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class AuthUtils {
    public static void signUpUser(String username, String password, String email, SignUpCallback callback) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(callback);
    }

    public static class UserSignUpCallback implements SignUpCallback {
        TextView result;
        ProgressBar progressBar;
        FragmentManager fragmentManager;

        public UserSignUpCallback(TextView result, ProgressBar progressBar, FragmentManager fragmentManager) {
            this.result = result;
            this.progressBar = progressBar;
            this.fragmentManager = fragmentManager;
        }

        @Override
        public void done(ParseException e) {
            progressBar.setVisibility(View.GONE);
            if (e == null) {
                EmailVerificationDialog verificationDialog = new EmailVerificationDialog();
                verificationDialog.show(fragmentManager, "Verify Email");
            } else {
                result.setText(e.getMessage());
            }
        }
    }

    public static class EmailVerificationDialog extends DialogFragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Nullable
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // sign in the user ...
                    // todo sign up complete
                }
            }).setTitle("Verify Your Email Address")
                    .setMessage("Please Check you email address for verification link.");
            return builder.create();
        }
    }

}
