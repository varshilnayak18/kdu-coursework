package org.example;

public class Main {

    public static void main(String[] args){
        MessageQueue msgApp = new MessageQueue();
        Thread[] senders = new Thread[3];
        Thread[] receivers = new Thread[3];
        for(int i=0;i<3;i++){
            senders[i] = new Thread(new MessageSender(msgApp));
            senders[i].setName("Sender " + (i + 1));
            receivers[i] = new Thread(new MessageReceiver(msgApp));
            receivers[i].setName("Receiever " + (i + 1));
        }
        for(int i=0;i<3;i++){
            senders[i].start();
            receivers[i].start();
        }
    }
}
