package com.steelcrow.androchat.conversation;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.steelcrow.androchat.dto.ConversationItem;
import com.steelcrow.androchat.dto.ConversationItem_Table;

import java.util.List;

public class MyLoader extends AsyncTaskLoader<List<ConversationItem>> {

    private List<ConversationItem> cache;
    private long chatId;

    public static List<ConversationItem> getMessages(long chatId) {
        return SQLite
                .select()
                .from(ConversationItem.class)
                .where(ConversationItem_Table.chatId.is(chatId))
                .orderBy(ConversationItem_Table.timestamp, false)
                .queryList();
    }

    public MyLoader(Context context, long chatId) {
        super(context);
        this.chatId = chatId;
    }

    @Override
    protected void onStartLoading() {
        if (cache != null) {
            deliverResult(cache);
        } else {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(List<ConversationItem> data) {
        cache = data;
        super.deliverResult(data);
    }

    @Override
    public List<ConversationItem> loadInBackground() {
        return getMessages(chatId);
    }
}
