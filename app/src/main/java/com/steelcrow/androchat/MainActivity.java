package com.steelcrow.androchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.steelcrow.androchat.R.id.textView2;
import static com.steelcrow.androchat.R.string.push_the_button;


public class MainActivity extends AppCompatActivity {

    private TextView mButton;
    private int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (TextView)findViewById(textView2);
    }

    public void onClick(View view) {
        mCounter++;
        mButton.setText(String.format("Pushed %d times", mCounter));
    }

    public void refreshCounter(View view) {
        mCounter = 0;
        mButton.setText(push_the_button);
    }
}
