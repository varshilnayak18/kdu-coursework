import java.util.HashMap;

public class Portfolio {
    private final HashMap<String,Long> coinQuantity;
    private final HashMap<String,Double> coinPrice;
    private double netProfit;

    public Portfolio() {
        this.coinQuantity = new HashMap<>();
        this.coinPrice = new HashMap<>();
        this.netProfit = 0;
    }

    public Long getCoinVolume(String coin){
        Long ret = coinQuantity.get(coin);
        if(ret == null){
            ret = 0L;
        }
        return ret;
    }

    public Double getCoinPrice(String coin) {
        Double ret = coinPrice.get(coin);
        if (ret == null){
            ret = 0.0;
        }
        return ret;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public void buyCoin(String coinName, long quantity, double price) {
        Long initialQuantity = getCoinVolume(coinName);
        Double initialPrice = getCoinPrice(coinName);
        if (initialQuantity == null) {
            initialQuantity = 0L;
        }
        if (initialPrice == null) {
            initialPrice = 0.0;
        }
        netProfit -= quantity*price;
        coinPrice.put(coinName,(initialPrice+price)*(initialQuantity+quantity)/(initialQuantity+quantity));
        coinQuantity.put(coinName,initialQuantity+quantity);
    }

    public void sellCoin(String coinName,long quantity, double currentPrice){
        long initialQuantity = getCoinVolume(coinName);
        coinQuantity.put(coinName,initialQuantity-quantity);
        netProfit += (quantity)*(currentPrice-getCoinPrice(coinName)) - ((quantity)*(currentPrice-getCoinPrice(coinName)));
        netProfit += quantity*currentPrice;
        if(initialQuantity-quantity == 0){
            coinPrice.put(coinName,0.0);
        }
    }

    public void printPortfolio(){
        Main.logger.logInfo("Portfolio details:- ");
        coinQuantity.forEach((key, value) ->{
            if(value != 0){
                Main.logger.logInfo("Coin: " + key + ", Price: " + coinPrice.get(key) + ", Quantity: " + value);
            }
        });
    }
}
