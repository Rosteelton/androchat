package com.steelcrow.androchat;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.steelcrow.androchat.navigation.NavigationActivity;
import com.steelcrow.androchat.service.LoginService;
import com.steelcrow.androchat.widgets.ProgressButton;

public class LoginActivity extends AppCompatActivity {


    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String PENDING_INTENT = "pi";


    public static final int LOGIN_REQUEST_CODE = 1;

    private TextView password;
    private TextView login;
    private ProgressButton button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_login);
        login = (TextView) findViewById(R.id.edit_text_login);
        password = (TextView) findViewById(R.id.edit_text_password);
        button = (ProgressButton) findViewById(R.id.btn_enter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent pi = createPendingResult(LOGIN_REQUEST_CODE, new Intent(), 0);
                button.showProgress();
                startService(new Intent(LoginActivity.this, LoginService.class)
                        .putExtra(PENDING_INTENT, pi)
                        .putExtra(LOGIN, login.getText().toString())
                        .putExtra(PASSWORD, password.getText().toString()));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE) {
            button.hideProgress();
            if (resultCode == RESULT_OK) {
                if (!data.getBooleanExtra(LoginService.RESULT, false)) {
                    new LoginActivity.MyDialogFragment().show(this.getSupportFragmentManager(), "AlertDialog");
                } else {
                    Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                    intent.putExtra("Login", login.getText().toString());
                    startActivity(intent);
                }
            }
        }
    }

    public static class MyDialogFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Ошибка авторизации!");
            builder.setMessage("Неправильная пара логин/пароль");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });
            return builder.create();
        }
    }
}