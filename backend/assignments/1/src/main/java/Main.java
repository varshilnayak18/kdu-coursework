import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    static ArrayList<Traders> traders = new ArrayList<>();
    static ArrayList<Coins> coins = new ArrayList<>();
    static Map<String, Traders> getTraderbyWallet = new ConcurrentHashMap<>();
    static Map<String, Coins> getCoinbySymbol = new ConcurrentHashMap<>();
    static Logging logger = new Logging();
    static Scanner scanner = new Scanner(System.in);
    static ExecutorService executorService;
    static CountDownLatch countDownLatch;
    static JsonNode jsonNode;

    public static void main(String[] args) {

        //loading csv files
        Path traderPath = Path.of("/home/lenovo/Desktop/KDU/backend/assignment/javaAssignment1/javaAssignment1/src/main/resources/traders.csv");
        parseCSV(traderPath);

        for (Traders t : traders) {
            getTraderbyWallet.put(t.getWalletAddress(), t);
        }

        Path coinPath = Path.of("/home/lenovo/Desktop/KDU/backend/assignment/javaAssignment1/javaAssignment1/src/main/resources/coins.csv");
        parseCSV(coinPath);

        for (Coins c : coins) {
            getCoinbySymbol.put(c.getCoinSymbol(), c);
        }

        //loading json
        String jsonString = "/home/lenovo/Desktop/KDU/backend/assignment/javaAssignment1/javaAssignment1/src/main/resources/small_transaction.json";
        parseJsonFile(jsonString);
        countDownLatch = new CountDownLatch(jsonNode.size());
        Thread thread = new Thread(() -> executeTransactions(jsonNode,countDownLatch));
        thread.start();

        //menu for user input
        int choice = 0;
        boolean running = true;

        while(running){
            loadMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> retrieveCoinDetails();

                case 2 -> displayTopNCoins();

                case 3 -> showTraderPortfolio();

                case 4 -> displayTraderProfit();

                case 5 -> showTop5andBottom5Traders();

                case 6 -> running = false;

                default -> logger.logInfo("Invalid choice!");
            }
        }
        try {
            thread.join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            logger.logWarn(e.getMessage());
        }
    }

    /**
     * Executes a group of transactions concurrently using a fixed number of threads
     * Each transaction is described by a JsonNode, and the execution is handled by instances of the ExecuteTransaction clas
     * @param jsonTransactions Collection of transactions to be executed denoted as JsonNode
     * @param latch A CountDownLatch for completion of all transactions.
     */
    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) {
        executorService = Executors.newFixedThreadPool(jsonTransactions.size());
        for (JsonNode jsonNode: jsonTransactions){
            executorService.execute(new ExecuteTransaction(jsonNode,latch));
        }
        try {
            latch.await();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            logger.logWarn(e.getMessage());
        }
        finally {
            executorService.shutdown();
        }
    }

    /**
     * Displays a menu with various options for user input.
     * Users are prompted to select an option by entering a corresponding number.
     */
    public static void loadMenu(){
        logger.logInfo("");
        logger.logInfo("1. Display details of coin ");
        logger.logInfo("2. Display top N coins ");
        logger.logInfo("3. Display trader portfolio ");
        logger.logInfo("4. Display trader net profit ");
        logger.logInfo("5. Display top 5 and bottom 5 traders ");
        logger.logInfo("6. Exit ");
        logger.logInfo("Please select one from the above: ");
    }

    /**
     * Retrieve and prints coin details based on user input
     */
    public static void retrieveCoinDetails(){
        logger.logInfo("Enter name/symbol of coin: ");
        String coinName = scanner.nextLine();
        if(getCoinbySymbol.containsKey(coinName)){
            getCoinbySymbol.get(coinName).printCoinDetails();
        }
        else {
            for(Coins c: coins){
                if(c.getCoinName().equals(coinName)){
                    c.printCoinDetails();
                    break;
                }
            }
        }
    }

    /**
     * Retrieve and prints top N coin based on their current prices
     */
    public static void displayTopNCoins() {
        logger.logInfo("Enter number of coins you want: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        List<Coins> topCoins = coins.stream().sorted((coin, t1) -> (int) (t1.getPrice() - coin.getPrice())).limit(n).toList();
        logger.logInfo("Top " + n + " coins in the market currently: ");
        for(Coins c: topCoins){
            logger.logInfo(c.getCoinName() + ": " + c.getPrice());
        }
    }

    /**
     * Retrieve and prints trader portfolio based on user input
     */
    public static void showTraderPortfolio() {
        logger.logInfo("Enter wallet address of trader: ");
        String walletAddress = scanner.nextLine();
        getTraderbyWallet.get(walletAddress).getPortfolio().printPortfolio();
    }

    /**
     * Retrieve and prints trader profit based on user input
     */
    public static void displayTraderProfit(){
        logger.logInfo("Enter wallet address of trader: ");
        String walletAddress = scanner.nextLine();
        logger.logInfo("" + getTraderbyWallet.get(walletAddress).getPortfolio().getNetProfit());
    }

    /**
     * Retrieve and prints top 5 and bottom t traders currently based on user input
     */
    public static void showTop5andBottom5Traders() {
        List<Traders> topTraders = traders.stream().sorted((trader, t1) -> (int) (t1.getPortfolio().getNetProfit() - trader.getPortfolio().getNetProfit())).limit(5).toList();
        logger.logInfo("Top 5 traders:- ");
        for(Traders t: topTraders){
            logger.logInfo(t.getFirstName());
        }
        List<Traders> bottomTraders = traders.stream().sorted((trader, t1) -> (int) (trader.getPortfolio().getNetProfit() - t1.getPortfolio().getNetProfit())).limit(5).toList();
        logger.logInfo("Bottom 5 traders:- ");
        for(Traders t: bottomTraders){
            logger.logInfo(t.getFirstName());
        }
    }

    /**
     * parses through CSV file and loads details about coins and traders
     * @param path path for CSV file
     * @return ArrayList<String[]> for testing
     */
    public static ArrayList<String[]> parseCSV(Path path) {
        ArrayList<String[]> ret = new ArrayList<>();
        BufferedReader reader = null;
        String line = "";
        String filePath = path.toString();
        try {
            reader =new BufferedReader(new FileReader(filePath));
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length == 5){
                    Traders trader = new Traders();
                    trader.setFirstName(data[1]);
                    trader.setLastName(data[2]);
                    trader.setPhoneNo(data[3]);
                    trader.setWalletAddress(data[4]);
                    traders.add(trader);
                    ret.add(new String[]{trader.getFirstName()});
                }
                else if(data.length == 6){
                    Coins coin = new Coins();
                    coin.setRank(Integer.parseInt(data[1]));
                    coin.setName(data[2]);
                    coin.setSymbol(data[3]);
                    coin.setPrice(Double.parseDouble(data[4]));
                    coin.setVolume(Long.parseLong(data[5]));
                    coins.add(coin);
                    ret.add(new String[]{coin.getCoinName()});
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
        }
        return ret;
    }

    /**
     * parses through JSON file and stores transaction array
     * @param jsonString path for JSON file
     * @return JsonNode for testing
     */
    public static JsonNode parseJsonFile(String jsonString){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(new File(jsonString));
        } catch (Exception e) {
            logger.logInfo(e.getMessage());
        }
        return jsonNode;
    }
}