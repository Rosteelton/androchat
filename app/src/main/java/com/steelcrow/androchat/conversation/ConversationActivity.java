package com.steelcrow.androchat.conversation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.steelcrow.androchat.R;
import com.steelcrow.androchat.common.SpacesItemDecoration;
import com.steelcrow.androchat.dto.ConversationItem;

import java.util.ArrayList;
import java.util.List;

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        initRecyclearView();
    }

    private void initRecyclearView() {
        RecyclerView recyclearView = (RecyclerView) findViewById(R.id.conversation_recycler_view);
        recyclearView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclearView.setLayoutManager(linearLayoutManager);

        RecyclerView.Adapter adapter = new ConversationAdapter(createTestList());
        recyclearView.setAdapter(adapter);

        recyclearView.addItemDecoration(new SpacesItemDecoration(30));
    }


    private List<ConversationItem> createTestList() {
        List<ConversationItem> list = new ArrayList<>();
        list.add(new ConversationItem("Anton Solovyev", "Проверка связи 11", "21.03.17 22:30"));
        list.add(new ConversationItem("Petr Petrovich", "Проверка связи 10", "21.03.17 22:29"));
        list.add(new ConversationItem("Anton Solovyev", "Проверка связи 9", "21.03.17 22:28"));
        list.add(new ConversationItem("Petr Petrovich", "Проверка связи 8", "21.03.17 22:27"));
        list.add(new ConversationItem("Anton Solovyev", "Проверка связи 7", "21.03.17 22:26"));
        list.add(new ConversationItem("Petr Petrovich", "Проверка связи 6", "21.03.17 22:25"));
        list.add(new ConversationItem("Anton Solovyev", "Проверка связи 5", "21.03.17 22:24"));
        list.add(new ConversationItem("Petr Petrovich", "Проверка связи 4", "21.03.17 22:23"));
        list.add(new ConversationItem("Anton Solovyev", "Проверка связи 3", "21.03.17 22:22"));
        list.add(new ConversationItem("Petr Petrovich", "Проверка связи 2", "21.03.17 22:21"));
        list.add(new ConversationItem("Anton Solovyev", "Проверка связи 1", "21.03.17 22:20"));
        return list;
    }
}
