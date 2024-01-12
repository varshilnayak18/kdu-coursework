package org.example;

public class MessageSender implements Runnable{
    MessageQueue msgQueue;
    Logging logger = new Logging();
    public MessageSender(MessageQueue msgApp) {
        this.msgQueue = msgApp;
    }

    @Override
    public void run() {
        logger.logInfo("Message sent by: " + Thread.currentThread().getName());
        msgQueue.addMessage("Message from " + Thread.currentThread().getName());
    }
}
