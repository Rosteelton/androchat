package com.steelcrow.androchat.conversation;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.steelcrow.androchat.dto.ConversationItem;

import java.util.List;

public class MyLoader extends AsyncTaskLoader<List<ConversationItem>> {

    public StaticStorage storage = new StaticStorage();

    public MyLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<ConversationItem> loadInBackground() {
        return storage.getMessages();
    }
}
