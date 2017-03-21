package com.steelcrow.androchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.steelcrow.androchat.chatRoom.ChatsActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView login;
    private Button aboutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_window);
        login = (TextView) findViewById(R.id.edit_text_login);
        aboutButton = (Button) findViewById(R.id.about_application);

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    public void logIn(View view) {
        Intent intent = new Intent(this, ChatsActivity.class);
        intent.putExtra("Login", login.getText().toString());
        startActivity(intent);
    }
}
