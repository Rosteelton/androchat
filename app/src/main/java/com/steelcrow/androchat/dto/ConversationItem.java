package com.steelcrow.androchat.dto;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AndrochatDatabase.class)
public class ConversationItem {

    @PrimaryKey(autoincrement = true)
    private long conversationItemId;

    @Column
    private String sender;

    @Column
    private String message;

    @Column
    private String timestamp;

    @Column
    private long chatId;

    public ConversationItem(String sender, String message, String timestamp, long chatId) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
        this.chatId = chatId;
    }

    public ConversationItem() {
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getConversationItemId() {
        return conversationItemId;
    }

    public void setConversationItemId(long conversationItemId) {
        this.conversationItemId = conversationItemId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
