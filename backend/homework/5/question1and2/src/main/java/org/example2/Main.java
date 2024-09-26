package org.example2;

import org.example.MessageQueue;
import org.example.MessageReceiver;
import org.example.MessageSender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MessageQueue msgApp = new MessageQueue();
        ExecutorService senders = Executors.newFixedThreadPool(3);
        ExecutorService receivers = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            senders.execute(new MessageSender(msgApp));
            receivers.execute(new MessageReceiver(msgApp));
        }
        senders.shutdown();
        receivers.shutdown();
    }
}
