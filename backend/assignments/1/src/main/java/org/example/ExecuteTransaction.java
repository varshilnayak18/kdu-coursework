package org.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable{
    JsonNode node;
    CountDownLatch countDownLatch;
    String coinErrorMsg = "Coin symbol is not valid ";
    String traderErrorMsg = "Trader wallet address is not valid ";
    Random rnd;
    public ExecuteTransaction(JsonNode jsonNode,CountDownLatch countDownLatch){
        this.node = jsonNode;
        this.countDownLatch = countDownLatch;
    }
    public ExecuteTransaction(){

    }

    @Override
    public void run() {
        String type = node.get("type").asText();
        JsonNode data = node.get("data");
        String transactionHash = getBlockHash();

        try {
            switch (type) {
                case "BUY" -> buyCoin(data);

                case "SELL" -> sellCoin(data);

                case "ADD_VOLUME" -> addVolume(data);

                case "UPDATE_PRICE" -> updatePrice(data);

                default -> Main.logger.logInfo("Invalid type");
            }
        }
        finally {
            transactionHash = transactionHash+" ";
            countDownLatch.countDown();
        }
    }

    private void buyCoin(JsonNode data){
        String coinName = data.get("coin").asText();
        long quantity = data.get("quantity").asLong();
        String walletAddress = data.get("wallet_address").asText();
        Traders trader = Main.getTraderbyWallet.get(walletAddress);
        if (trader == null) {
            Main.logger.logWarn(traderErrorMsg + walletAddress);
            return;
        }
        Coins coin = Main.getCoinbySymbol.get(coinName);

        if(coin == null) {
            Main.logger.logWarn(coinErrorMsg + coinName);
            return;
        }
        synchronized (coin) {
            Portfolio portfolio = trader.getPortfolio();
            long volume = coin.getVolume();
            while (volume < quantity){
                try {
                    coin.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                volume = coin.getVolume();
            }
            portfolio.buyCoin(coinName,quantity,coin.getPrice());
            coin.setVolumeBuy(quantity);
            coin.notifyAll();
        }
    }

    private void sellCoin(JsonNode data){
        String coinName = data.get("coin").asText();
        long quantity = data.get("quantity").asLong();
        String walletAddress = data.get("wallet_address").asText();
        Traders trader = Main.getTraderbyWallet.get(walletAddress);
        if (trader == null) {
            Main.logger.logWarn(traderErrorMsg + walletAddress);
            return;
        }
        Coins coin = Main.getCoinbySymbol.get(coinName);
        if(coin == null) {
            Main.logger.logWarn(coinErrorMsg + coinName);
            return;
        }
        synchronized (coin) {
            Portfolio portfolio = trader.getPortfolio();
            Long initialQuantity = null;
            try {
                initialQuantity = portfolio.getCoinVolume(coinName);
            }
            catch (Exception e){
                Thread.currentThread().interrupt();
            }
            if (initialQuantity == null){
                initialQuantity = 0L;
            }
            while (initialQuantity < quantity){
                try {
                    coin.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                initialQuantity = portfolio.getCoinVolume(coinName);
            }
            portfolio.sellCoin(coinName,quantity,coin.getPrice());
            coin.setVolumeBuy(quantity);
        }
    }

    private void addVolume(JsonNode data){
        String coinName = data.get("coin").asText();
        long volume = data.get("volume").asLong();
        Coins coin = Main.getCoinbySymbol.get(coinName);
        if(coin == null) {
            Main.logger.logWarn("Error: Coin symbol is not valid -> " + coinName);
            throw new IllegalArgumentException("Invalid coin symbol");
        }
        synchronized (coin) {
            coin.setVolume(volume);
            coin.notifyAll();
        }
    }

    private void updatePrice(JsonNode data){
        String coinName = data.get("coin").asText();
        double price = data.get("price").asDouble();
        Coins coin = Main.getCoinbySymbol.get(coinName);
        if(coin == null) {
            Main.logger.logWarn("Error: Coin symbol is not valid -> " + coinName);
            throw new IllegalArgumentException("Invalid coin symbol");
        }
        synchronized (coin) {
            coin.setPrice(price);
        }
    }
    private String getBlockHash() {
        String saltchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        rnd = new Random();
        /*
          Introducing delay mimicking complex
          calculation being performed.
         */
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * saltchars.length());
            transactionHash.append(saltchars.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
