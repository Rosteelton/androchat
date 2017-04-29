package com.steelcrow.androchat.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.steelcrow.androchat.LoginActivity;

import static android.app.Activity.RESULT_OK;


public class LoginService extends IntentService {


    public static final String RESULT = "result";

    public LoginService() {
        super("LoginService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(1000); // long authorize action

            //mock
            assert intent != null;

            PendingIntent pendingIntent = intent.getParcelableExtra(LoginActivity.PENDING_INTENT);
            String login = intent.getStringExtra(LoginActivity.LOGIN);
            String password = intent.getStringExtra(LoginActivity.PASSWORD);
            if (!login.equals("a") && !password.equals("a")) {
                Intent result = new Intent().putExtra(LoginService.RESULT, true);
                try {
                    pendingIntent.send(this, RESULT_OK, result);
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            } else {
                Intent result = new Intent().putExtra(LoginService.RESULT, false);
                try {
                    pendingIntent.send(this, RESULT_OK, result);
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
            stopSelf();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
