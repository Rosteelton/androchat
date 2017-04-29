package com.steelcrow.androchat.conversation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.steelcrow.androchat.R;
import com.steelcrow.androchat.common.SpacesItemDecoration;
import com.steelcrow.androchat.dto.ConversationItem;
import com.steelcrow.androchat.widgets.SendMessageView;

import java.util.Date;
import java.util.List;

public class ConversationActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ConversationItem>> {

    private ConversationAdapter adapter;
    public MyLoader loader;
    private SendMessageView sendMessageView;
    private RecyclerView recyclerView;
    private long chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        sendMessageView = (SendMessageView) findViewById(R.id.send_message_view);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("chatName"));
        chatId = intent.getLongExtra("chatId", 0);

        loader = (MyLoader) getSupportLoaderManager().initLoader(0, null, this);
        initRecyclearView();
    }

    @Override
    public Loader<List<ConversationItem>> onCreateLoader(int id, Bundle args) {
        return new MyLoader(this, chatId);
    }

    @Override
    public void onLoadFinished(Loader<List<ConversationItem>> loader, List<ConversationItem> data) {
        adapter.setItems(data);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onLoaderReset(Loader<List<ConversationItem>> loader) {
        recyclerView.setAdapter(null);
    }

    private void initRecyclearView() {

        recyclerView = (RecyclerView) findViewById(R.id.conversation_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ConversationAdapter(MyLoader.getMessages(chatId));
        recyclerView.setAdapter(adapter);

        sendMessageView.setOnClickButtonHandler(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConversationItem item = createNewMessage(sendMessageView.getTextMessage(), chatId);
                saveMessage(item);
                loader = (MyLoader) getSupportLoaderManager().restartLoader(0, null, ConversationActivity.this);
                loader.onContentChanged();
                sendMessageView.setTextMessage("");
            }
        });
        recyclerView.addItemDecoration(new SpacesItemDecoration(30));
    }

    private ConversationItem createNewMessage(String text, long chatId) {
        CharSequence s = DateFormat.format("dd.MM.yy kk:mm:ss", new Date());
        return new ConversationItem("Anton Solovyev", text, s.toString(), chatId);
    }

    private void saveMessage(ConversationItem item) {
        FlowManager.getModelAdapter(ConversationItem.class).save(item);
    }

}
