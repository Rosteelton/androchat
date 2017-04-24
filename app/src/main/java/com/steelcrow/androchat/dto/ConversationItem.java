package com.steelcrow.androchat.dto;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.database.AndroidDatabase;

@Table(database = AndroidDatabase.class)
public class ConversationItem {

    @PrimaryKey(autoincrement = true)
    private String conversationItemId;

    @Column
    private String sender;

    @Column
    private String message;

    @Column
    private String timestamp;

    public ConversationItem(String sender, String message, String timestamp) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getConversationItemId() {
        return conversationItemId;
    }

    public void setConversationItemId(String conversationItemId) {
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
