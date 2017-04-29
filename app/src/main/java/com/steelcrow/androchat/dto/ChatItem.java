package com.steelcrow.androchat.dto;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AndrochatDatabase.class)
public class ChatItem {

    @PrimaryKey(autoincrement = true)
    long chatItemId;

    @Column
    String title;

    @Column
    String description;

    @Column
    long timestamp;


    public long getChatItemId() {
        return chatItemId;
    }

    public void setChatItemId(long chatItemId) {
        this.chatItemId = chatItemId;
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

    public ChatItem() {
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
