package com.steelcrow.androchat.conversation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.steelcrow.androchat.R;
import com.steelcrow.androchat.dto.ConversationItem;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int MY_MESSAGE = 0;
    public static final int FOREIGN_MESSAGE = 1;

    private List<ConversationItem> items;

    public ConversationAdapter(List<ConversationItem> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        String sender = items.get(position).getSender();
        if (sender.equals("Anton Solovyev")) return 0;
        else return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MY_MESSAGE:
                View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_element_mine, parent, false);
                return new MyViewHolder(myView);
            case FOREIGN_MESSAGE:
                View foreignView = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_element_foreign, parent, false);
                return new ForeignViewHolder(foreignView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case MY_MESSAGE:
                MyViewHolder meMyViewHolder = (MyViewHolder) holder;
                meMyViewHolder.sender.setText(items.get(position).getSender());
                meMyViewHolder.date.setText(items.get(position).getTimestamp());
                meMyViewHolder.message.setText(items.get(position).getMessage());
                break;
            case FOREIGN_MESSAGE:
                ForeignViewHolder foreignViewHolder = (ForeignViewHolder) holder;
                foreignViewHolder.sender.setText(items.get(position).getSender());
                foreignViewHolder.date.setText(items.get(position).getTimestamp());
                foreignViewHolder.message.setText(items.get(position).getMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView sender;
        private TextView date;
        private TextView message;

        public MyViewHolder(View view) {
            super(view);

            sender = (TextView) view.findViewById(R.id.my_name);
            date = (TextView) view.findViewById(R.id.my_date);
            message = (TextView) view.findViewById(R.id.my_message);
        }
    }

    public static class ForeignViewHolder extends RecyclerView.ViewHolder {
        private TextView sender;
        private TextView date;
        private TextView message;

        public ForeignViewHolder(View view) {
            super(view);
            sender = (TextView) view.findViewById(R.id.foreign_sender);
            date = (TextView) view.findViewById(R.id.foreign_date);
            message = (TextView) view.findViewById(R.id.foreign_message);
        }
    }

    public void addItem(ConversationItem item) {
        items.add(0, item);
        notifyItemInserted(0);
    }
}
