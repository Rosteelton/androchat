package com.steelcrow.androchat.conversation;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.steelcrow.androchat.dto.ConversationItem;

import java.util.List;

public class MyLoader extends AsyncTaskLoader<List<ConversationItem>> {
    private List<ConversationItem> cache;

    public MyLoader(Context context) {
        super(context);
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
        return MessageStorage.getInstance().getMessages();
    }
}
