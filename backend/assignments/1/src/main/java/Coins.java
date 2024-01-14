public class Coins {
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private long volume;

    public Coins(int rank, String name, String symbol, double price, long volume) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
    }

    public Coins(){

    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCoinName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoinSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume += volume;
    }
    public void setVolumeBuy(long volume){
        this.volume -= volume;
    }

    public void printCoinDetails(){
        Main.logger.logInfo("Coin details:- ");
        Main.logger.logInfo("Rank: " + this.getRank());
        Main.logger.logInfo("Name: " + this.getCoinName());
        Main.logger.logInfo("Symbol: " + this.getCoinSymbol());
        Main.logger.logInfo("Price: " + this.getPrice());
        Main.logger.logInfo("Volume: " + this.getVolume());
    }
}
