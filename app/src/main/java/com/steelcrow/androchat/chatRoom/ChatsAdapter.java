package com.steelcrow.androchat.chatRoom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.steelcrow.androchat.OnChatItemClickListener;
import com.steelcrow.androchat.R;
import com.steelcrow.androchat.dto.ChatItem;

import java.util.List;


public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {
    private List<ChatItem> items;
    private OnChatItemClickListener listener;

    public ChatsAdapter(List<ChatItem> items, OnChatItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_element, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.description.setText(items.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;

        public ViewHolder(View view, final OnChatItemClickListener listener) {
            super(view);
            title = (TextView) view.findViewById(R.id.text_view_title);
            description = (TextView) view.findViewById(R.id.text_view_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
