package com.steelcrow.androchat.conversation;


import com.steelcrow.androchat.dto.ConversationItem;

import java.util.ArrayList;
import java.util.List;

public class MessageStorage {
    private static final MessageStorage ourInstance = new MessageStorage();
    private static List<ConversationItem> messages;


    public static MessageStorage getInstance() {
        return ourInstance;
    }

    public MessageStorage() {
        messages = createTestList();
    }

    public static void addMessage(ConversationItem message) {
        messages.add(0, message);
    }

    public static List<ConversationItem> getMessages() {
        return MessageStorage.messages;
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
