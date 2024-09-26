package org.example;

public class MessageReceiver implements Runnable{
    MessageQueue msgQueue;
    Logging logger = new Logging();
    public MessageReceiver(MessageQueue msgApp) {
        this.msgQueue = msgApp;
    }

    @Override
    public void run() {
        try {
             logger.logInfo("Received: " + msgQueue.receieveMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
