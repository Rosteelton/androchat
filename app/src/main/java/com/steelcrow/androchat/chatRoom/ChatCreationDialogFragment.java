package com.steelcrow.androchat.chatRoom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import com.steelcrow.androchat.R;


public class ChatCreationDialogFragment extends DialogFragment {


    public interface ChatCreationDialogListener {
        public void onDialogPositiveClick(ChatCreationDialogFragment dialog, CharSequence chatName, CharSequence chatDescription);
    }

    ChatCreationDialogListener listener;
    TextView chatName;
    TextView chatDescription;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ChatCreationDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle("Введите название чата");
        builder.setView(inflater.inflate(R.layout.layout_new_chat_dialog, null))
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chatName = (TextView) getDialog().findViewById(R.id.chatName);
                        chatDescription = (TextView) getDialog().findViewById(R.id.chatDescription);
                        CharSequence chatName = ChatCreationDialogFragment.this.chatName.getText();
                        if (!(chatName == null || chatName.length() == 0)) {
                            listener.onDialogPositiveClick(ChatCreationDialogFragment.this, chatName, chatDescription.getText());
                        }
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChatCreationDialogFragment.this.getDialog().cancel();
                    }
                });

        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setEnabled(false);
                chatName = (TextView) getDialog().findViewById(R.id.chatName);
                chatName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() >= 1) {
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                        } else {
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });
        return alertDialog;
    }
}