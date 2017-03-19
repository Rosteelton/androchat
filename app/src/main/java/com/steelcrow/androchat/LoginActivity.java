package com.steelcrow.androchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_window);
        login = (TextView) findViewById(R.id.edit_text_login);
    }

    public void logIn(View view) {
        Intent intent = new Intent(this, ChatsActivity.class);
        intent.putExtra("Login", login.getText().toString());
        startActivity(intent);
    }
}
