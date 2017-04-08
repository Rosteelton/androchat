package com.steelcrow.androchat.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.steelcrow.androchat.R;

public class SendMessageView extends LinearLayout {

    private EditText editText;
    private ImageButton imageButton;

    public SendMessageView(Context context) {
        super(context);
    }

    public SendMessageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SendMessageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_send_message, this);
        editText = (EditText) findViewById(R.id.edit_text_send_message);
        imageButton = (ImageButton) findViewById(R.id.button_send_message);

        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SendMessageView);

        if (typedArray != null) {
            setEnabled(typedArray.getBoolean(R.styleable.SendMessageView_buttonEnabled, false));
            typedArray.recycle();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    imageButton.setEnabled(false);
                    imageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                } else {
                    imageButton.setEnabled(true);
                    imageButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setOnClickButtonHandler(View.OnClickListener listener) {
        imageButton.setOnClickListener(listener);
    }

    public String getTextMessage() {
        return editText.getText().toString();
    }

    public void setTextMessage(String s) {
        editText.setText(s);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        imageButton.setEnabled(enabled);
    }
}
