package com.steelcrow.androchat.dto;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.database.AndroidDatabase;

@Table(database = AndroidDatabase.class)
public class ChatItem {

    @PrimaryKey(autoincrement = true)
    private long chatItem;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private long timestamp;

    public long getChatItem() {
        return chatItem;
    }

    public void setChatItem(long chatItem) {
        this.chatItem = chatItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChatItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    public ChatItem(String title, String description, long timestamp) {
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
    }
}
