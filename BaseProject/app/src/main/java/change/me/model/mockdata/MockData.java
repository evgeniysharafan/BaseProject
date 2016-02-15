package change.me.model.mockdata;


import com.evgeniysharafan.utils.RandomUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import change.me.model.Account;
import change.me.model.Chat;
import change.me.model.Message;

public final class MockData {

    private MockData() {
    }

    public static List<Chat> getChats(int chatsCount, int minMessagesPerChatCount,
                                      int maxMessagesPerChatCount) {
        List<Chat> chats = new ArrayList<>();
        List<Message> messages = new ArrayList<>();

        for (int i = 0; i < chatsCount; i++) {
            messages.clear();
            int size = minMessagesPerChatCount
                    + RandomUtils.getInt(maxMessagesPerChatCount - minMessagesPerChatCount);
            for (int j = 0; j < size; j++) {
                messages.add(new Message(RandomUtils.getLoremString(400, false), RandomUtils.getDateInMillis(365),
                        RandomUtils.getBoolean(), RandomUtils.getBoolean()));
            }

            Collections.sort(messages);

            chats.add(new Chat(RandomUtils.getInt(3) % 2 == 0
                    ? RandomUtils.getLoremString(30, false)
                    : RandomUtils.getPhoneNumber(), new ArrayList<>(messages)));

            Collections.sort(chats);
        }

        return chats;
    }

    public static List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("SMS", "", true));
        accounts.add(new Account("Google Talk", "name@gmail.com", true));
        accounts.add(new Account("Google Talk", "longnamelongname@gmail.com", false));

        return accounts;
    }

}
