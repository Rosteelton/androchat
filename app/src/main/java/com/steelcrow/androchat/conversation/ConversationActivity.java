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

import com.steelcrow.androchat.R;
import com.steelcrow.androchat.common.SpacesItemDecoration;
import com.steelcrow.androchat.dto.ConversationItem;
import com.steelcrow.androchat.widgets.SendMessageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConversationActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ConversationItem>> {

    private ConversationAdapter adapter;
    public MyLoader loader;
    private SendMessageView sendMessageView;
    private RecyclerView recyclearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        sendMessageView = (SendMessageView) findViewById(R.id.send_message_view);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("chatName"));

        loader = (MyLoader) getSupportLoaderManager().initLoader(0, null, this);
        initRecyclearView();
    }

    @Override
    public Loader<List<ConversationItem>> onCreateLoader(int id, Bundle args) {
        return new MyLoader(this);
    }


    @Override
    public void onLoadFinished(Loader<List<ConversationItem>> loader, List<ConversationItem> data) {
        adapter.setItems(data);
        recyclearView.getAdapter().notifyDataSetChanged();
        recyclearView.scrollToPosition(0);
    }

    @Override
    public void onLoaderReset(Loader<List<ConversationItem>> loader) {
        recyclearView.setAdapter(null);
    }

    private void initRecyclearView() {

        recyclearView = (RecyclerView) findViewById(R.id.conversation_recycler_view);
        recyclearView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclearView.setLayoutManager(linearLayoutManager);

        adapter = new ConversationAdapter(new ArrayList<ConversationItem>());
        recyclearView.setAdapter(adapter);

        sendMessageView.setOnClickButtonHandler(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence s  = DateFormat.format("dd.MM.yy kk:mm", new Date());
                MessageStorage.getInstance().addMessage(new ConversationItem("Anton Solovyev", sendMessageView.getTextMessage(), s.toString()));
                loader = (MyLoader) getSupportLoaderManager().restartLoader(0, null, ConversationActivity.this);
                loader.onContentChanged();
                sendMessageView.setTextMessage("");
            }
        });
        recyclearView.addItemDecoration(new SpacesItemDecoration(30));
    }


}
