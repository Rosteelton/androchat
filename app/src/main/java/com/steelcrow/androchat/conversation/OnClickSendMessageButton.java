package com.steelcrow.androchat.conversation;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;

import com.steelcrow.androchat.dto.ConversationItem;
import com.steelcrow.androchat.widgets.SendMessageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OnClickSendMessageButton implements View.OnClickListener {

    private ConversationAdapter adapter;
    private RecyclerView recyclerView;
    private SendMessageView view;

    public OnClickSendMessageButton(ConversationAdapter adapter, RecyclerView recyclerView, SendMessageView view) {
        this.adapter = adapter;
        this.recyclerView = recyclerView;
        this.view = view;
    }

    @Override
    public void onClick(View v) {
        CharSequence s  = DateFormat.format("dd.MM.yy kk:mm", new Date());
        adapter.addItem(new ConversationItem("Anton Solovyev", view.getTextMessage(), s.toString()));
        recyclerView.scrollToPosition(0);
        view.setTextMessage("");
    }
}
