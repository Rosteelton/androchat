package com.steelcrow.androchat.chatRoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.steelcrow.androchat.R;
import com.steelcrow.androchat.conversation.ConversationActivity;
import com.steelcrow.androchat.dto.ChatItem;
import com.steelcrow.androchat.navigation.NavigationActivity;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomFragment extends Fragment {

    private RecyclerView recyclerView;
    private String title;

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

        RecyclerView.Adapter adapter = new ChatsAdapter(getChatList(), new OnChatItemClickListener() {
            @Override
            public void onItemClick(CharSequence title) {
                Intent intent = new Intent(getActivity(), ConversationActivity.class);
                intent.putExtra("chatName", title);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(activity, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }

    private List<ChatItem> getChatList() {
        List<ChatItem> list = new ArrayList<>();

        list.add(new ChatItem("1 чат", "1 сообщение"));
        list.add(new ChatItem("2 чат", "2 сообщение"));
        list.add(new ChatItem("3 чат", "3 сообщение"));
        list.add(new ChatItem("4 чат", "4 сообщение"));
        list.add(new ChatItem("5 чат", "5 сообщение"));
        list.add(new ChatItem("6 чат", "6 сообщение"));
        list.add(new ChatItem("7 чат", "7 сообщение"));
        list.add(new ChatItem("8 чат", "8 сообщение"));
        list.add(new ChatItem("9 чат", "9 сообщение"));
        list.add(new ChatItem("10 чат", "10 сообщение"));
        list.add(new ChatItem("11 чат", "11 сообщение"));
        list.add(new ChatItem("12 чат", "12 сообщение"));
        list.add(new ChatItem("13 чат", "13 сообщение"));
        list.add(new ChatItem("14 чат", "14 сообщение"));
        list.add(new ChatItem("15 чат", "15 сообщение"));
        list.add(new ChatItem("16 чат", "16 сообщение"));
        list.add(new ChatItem("17 чат", "17 сообщение"));
        list.add(new ChatItem("18 чат", "18 сообщение"));
        list.add(new ChatItem("19 чат", "19 сообщение"));
        list.add(new ChatItem("20 чат", "20 сообщение"));
        return list;
    }


}