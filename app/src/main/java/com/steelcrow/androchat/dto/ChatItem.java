package com.steelcrow.androchat.dto;


public class ChatItem {
    private String title;
    private String description;

    public ChatItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

}
