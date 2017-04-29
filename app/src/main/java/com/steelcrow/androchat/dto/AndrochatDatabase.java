package com.steelcrow.androchat.dto;


import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

import java.util.List;


@Database(name = "chat_db", version = 1)
public final class AndrochatDatabase {

    @Migration(version = 2, database = AndrochatDatabase.class)
    public static class Migration2 extends AlterTableMigration<ChatItem> {
        public Migration2(Class<ChatItem> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.TEXT, "lastMessageText");
        }

        @Override
        public void onPostMigrate() {
            super.onPostMigrate();

            List<ChatItem> chats = SQLite.select(ChatItem_Table.chatItemId).from(ChatItem.class).queryList();
            for (ChatItem item : chats) {
                ConversationItem lastMessage = SQLite.select()
                        .from(ConversationItem.class)
                        .where(ConversationItem_Table.chatId.is(item.chatItemId))
                        .orderBy(ConversationItem_Table.timestamp, false)
                        .querySingle();

                if (lastMessage != null) {
                    //Todo update column
                }
            }
        }
    }
}
