package com.sharifdev.torobche.backUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.sharifdev.torobche.R;

import java.util.HashMap;

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
        TextInputEditText code;

        public UserSignUpCallback(TextView result, ProgressBar progressBar, FragmentManager fragmentManager, TextInputEditText code) {
            this.result = result;
            this.progressBar = progressBar;
            this.fragmentManager = fragmentManager;
            this.code = code;
        }

        @Override
        public void done(ParseException e) {
            progressBar.setVisibility(View.GONE);
            if (e == null) {
                HashMap<String, Object> params = new HashMap<>();
                ParseCloud.callFunctionInBackground("sendcode", params, new FunctionCallback<String>() {
                    @Override
                    public void done(String object, ParseException e) {
                        if (e == null) {
                        } else {
                            Log.d("verify_error", "Parse Error");
                            result.setText(e.getMessage());
                        }
                    }
                });
                EmailVerificationDialog verificationDialog = new EmailVerificationDialog(code);
                verificationDialog.show(fragmentManager, "Verify Email");
                //
            } else {
                result.setText(e.getMessage());
            }
        }
    }

    public static class EmailVerificationDialog extends DialogFragment {
        TextInputEditText code;

        public EmailVerificationDialog(TextInputEditText code) {
            this.code = code;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Nullable
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
            LayoutInflater inflater = requireActivity().getLayoutInflater();
            final EditText input = new EditText(getActivity());
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            input.setHint("Verification Code");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // sign in the user ...
                    // todo sign up complete
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("code", input.getText().toString());
                    ParseCloud.callFunctionInBackground("verify_code", params, new FunctionCallback<Boolean>() {
                        @Override
                        public void done(Boolean object, ParseException e) {
                            if (e == null) {
                                if (object) {
                                    // todo : verified
                                } else {
                                    // todo : not verified
                                }
                            } else {
                                Log.d("verify_error", e.getMessage());
                            }
                        }
                    });
                }
            }).setView(input);
            return builder.create();
        }
    }

}
