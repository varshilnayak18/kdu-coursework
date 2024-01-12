package org.example;

import java.util.LinkedList;

public class MessageQueue {
    private final LinkedList<String> msgQueue = new LinkedList<>();

    public synchronized void addMessage(String msg){
        msgQueue.add(msg);
        notifyAll();
    }

    public synchronized String receieveMessage() throws InterruptedException {
        while (msgQueue.isEmpty()){
            wait();
        }
        return msgQueue.poll();
    }
}
