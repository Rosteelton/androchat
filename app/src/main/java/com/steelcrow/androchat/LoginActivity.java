package com.steelcrow.androchat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.steelcrow.androchat.navigation.NavigationActivity;
import com.steelcrow.androchat.widgets.ProgressButton;

public class LoginActivity extends AppCompatActivity {

    private TextView login;
    private ProgressButton button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_login);
        login = (TextView) findViewById(R.id.edit_text_login);
        button = (ProgressButton) findViewById(R.id.btn_enter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginTask().execute();
            }
        });
    }


    private class LoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            button.showProgress();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            button.hideProgress();

            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
            intent.putExtra("Login", login.getText().toString());
            startActivity(intent);
        }
    }
}
