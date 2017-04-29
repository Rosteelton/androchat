package com.steelcrow.androchat.chatRoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.steelcrow.androchat.R;
import com.steelcrow.androchat.conversation.ConversationActivity;
import com.steelcrow.androchat.dto.ChatItem;
import com.steelcrow.androchat.navigation.NavigationActivity;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomFragment extends Fragment implements ChatCreationDialogFragment.ChatCreationDialogListener {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private String title;
    ChatsAdapter adapter;

    public static ChatRoomFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        ChatRoomFragment fragment = new ChatRoomFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chats, container, false);

        NavigationActivity activity = (NavigationActivity) getActivity();
        activity.getSupportActionBar().setTitle(title);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new ChatsAdapter(getPreviousChatItems(), new OnChatItemClickListener() {
            @Override
            public void onItemClick(CharSequence chatTitle, long chatItemId) {
                Intent intent = new Intent(getActivity(), ConversationActivity.class);
                intent.putExtra("chatName", chatTitle);
                intent.putExtra("chatId", chatItemId);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(activity, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatCreationDialogFragment dialog = new ChatCreationDialogFragment();
                dialog.setTargetFragment(ChatRoomFragment.this, 0);
                dialog.show(getActivity().getSupportFragmentManager(), "ChatCreationDialogFragment");
            }
        });

        return view;
    }

    @Override
    public void onDialogPositiveClick(ChatCreationDialogFragment dialog, CharSequence chatName) {
        addNewChat(chatName);
    }

    private void addNewChat(CharSequence chatName) {
        ChatItem newItem = new ChatItem(chatName.toString(), "Клевый чат");
        FlowManager.getModelAdapter(ChatItem.class).save(newItem);
        adapter.addDialog(newItem);
    }

    @NonNull
    private List<ChatItem> getPreviousChatItems() {
        return SQLite.select().from(ChatItem.class).queryList();
    }

}