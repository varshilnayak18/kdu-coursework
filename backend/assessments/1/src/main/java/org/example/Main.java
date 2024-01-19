package org.example;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Main {
    static List<Player> players = new ArrayList<>();
    static List<Team> teams = new ArrayList<>();
    static Map<String, Team> teamFromName = new HashMap<>();
    static Scanner scanner = new Scanner(System.in) ;
    static Logging logger = new Logging();
    static List<Match> matches = new ArrayList<>();
    static String time1 = "06:30";
    static String time2 = "09:30";
    static String date = "2024-01-19";

    static String nameString = "Name: ";
    static String matchString = "Matches: ";
    static String runString = "Runs: ";
    static String wicketString = "Wickets: ";

    public static void main(String[] args) throws IOException {
        Path csvPath = Path.of("/home/lenovo/Desktop/KDU/backend/java/assessment1/src/main/resources/IPL_2021-data.csv");
        parseCSV(csvPath);

        int choice = 0;
        boolean running = true;

        while(running){
            loadMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> displayBowler();

                case 2 -> displayLeaderboard();

                case 3 -> displayTop3();

                case 4 -> generateSchedule(1);

                case 5 -> generateSchedule(2);

                case 6 -> {
                    exitApp();
                    running = false;
                }

                default -> logger.logInfo("Invalid choice!");
            }
        }

    }

    public static void loadMenu(){
        logger.logInfo("");
        logger.logInfo("1. Display bowlers with 40 wickets for a team");
        logger.logInfo("2. Display highest wicket-taker and highest run-scorer for a team ");
        logger.logInfo("3. Display top 3 run-scorers and top 3 wicket-takers ");
        logger.logInfo("4. Generate one match for today ");
        logger.logInfo("5. Generate two matches for today ");
        logger.logInfo("6. Exit ");
        logger.logInfo("Please select one from the above: ");
    }

    public static void parseCSV(Path path) {
        String line = "";
        String filePath = path.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String name = data[0];
                String teamName = data[1];
                String role = data[2];
                long matches = (Long.parseLong(data[3]));
                long runs = (Long.parseLong(data[4]));
                double average = (Double.parseDouble(data[5]));
                double strikeRate = Double.parseDouble(data[6]);
                long wickets = Long.parseLong(data[7]);
                Player player = new Player(name,role,matches,runs,average,strikeRate,wickets);
                Team team;
                if(teamFromName.containsKey(teamName)) {
                    team = teamFromName.get(teamName);
                }
                else {
                    team = new Team(teamName);
                    teamFromName.put(teamName,team);
                    teams.add(team);
                }
                team.getTeamPlayers().add(player);
                players.add(player);

            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void displayBowler(){
        logger.logInfo("Enter team name: ");
        String teamName = scanner.nextLine();
        Team team = teamFromName.get(teamName);
        List<Player> topBowlers = team.getTeamPlayers().stream().filter(player -> Objects.equals(player.getRole(), "BOWLER") && player.getWickets()>=40).toList();
        logger.logInfo("Bowlers with 40+ wickets for " + teamName + ":");
        for(Player p: topBowlers){
            logger.logInfo("");
            logger.logInfo(nameString + p.getName());
            logger.logInfo(matchString + p.getMatches());
            logger.logInfo(wicketString + p.getWickets());
        }
    }

    public static void displayLeaderboard(){
        logger.logInfo("Enter team name: ");
        String teamName = scanner.nextLine();
        Team team = teamFromName.get(teamName);
        List<Player> topRunScorers = team.getTeamPlayers().stream().sorted((player, t1) -> (int) (t1.getRuns() - player.getRuns())).limit(1).toList();
        logger.logInfo("Top run scorer for " + teamName +": ");
        for(Player p: topRunScorers){
            logger.logInfo("");
            logger.logInfo(nameString + p.getName());
            logger.logInfo(matchString + p.getMatches());
            logger.logInfo(runString + p.getRuns());
            logger.logInfo("Average: " + p.getAverage());
            logger.logInfo("Strike Rate: " + p.getStrikeRate());
        }
        List<Player> topWicketTakers = team.getTeamPlayers().stream().sorted((player, t1) -> (int) (t1.getWickets() - player.getWickets())).limit(1).toList();
        logger.logInfo("");
        logger.logInfo("Top wicket taker for " + teamName +": ");
        for(Player p: topWicketTakers){
            logger.logInfo("");
            logger.logInfo(nameString + p.getName());
            logger.logInfo(matchString + p.getMatches());
            logger.logInfo(wicketString + p.getWickets());
        }
    }

    public static void displayTop3(){
        List<Player> topRunScorers = players.stream().sorted((player, t1) -> (int) (t1.getRuns() - player.getRuns())).limit(3).toList();


        logger.logInfo("Top 3 run scorers of the season: ");
        for(Player p: topRunScorers){
            logger.logInfo("");
            logger.logInfo(nameString + p.getName());
            logger.logInfo(matchString + p.getMatches());
            logger.logInfo(runString + p.getRuns());
            logger.logInfo("Average: " + p.getAverage());
            logger.logInfo("Strike Rate: " + p.getStrikeRate());
        }
        List<Player> topWicketTakers = players.stream().sorted((player, t1) -> (int) (t1.getWickets() - player.getWickets())).limit(3).toList();
        logger.logInfo("");
        logger.logInfo("Top 3 wicket takers of the season: ");
        for(Player p: topWicketTakers){
            logger.logInfo("");
            logger.logInfo(nameString + p.getName());
            logger.logInfo(matchString + p.getMatches());
            logger.logInfo(wicketString + p.getWickets());
        }
    }

    public static void generateSchedule(int matchCount){
        if(matchCount == 1){
            makeMatch(date,time2);
        }
        else {
            makeMatch(date,time1);
            makeMatch(date,time2);
        }
    }

    public static void makeMatch(String date, String time){
        logger.logInfo("Enter home team name: ");
        String homeTeamName = scanner.nextLine();
        logger.logInfo("Enter away team name: ");
        String awayTeamName = scanner.nextLine();
        Team homeTeam = teamFromName.get(homeTeamName);
        Team awayTeam = teamFromName.get(awayTeamName);
        if(homeTeam.getHomeMatches().contains(awayTeam)){
            logger.logInfo("Cannot schedule this match");
        }
        else{
            Match match = new Match(homeTeam,awayTeam,date,time);
            matches.add(match);
            homeTeam.getHomeMatches().add(awayTeam);
            date = DateIncrementer.addOneDay(date);
        }
    }

    public static void exitApp() throws IOException {
        File csvOutputFile = new File("/home/lenovo/Desktop/KDU/backend/java/assessment1/src/main/resources/schedule.csv");
        FileWriter fileWriter = new FileWriter(csvOutputFile);
        List<String[]> dataLines = new ArrayList<>();
        for(Match m: matches){
            String[] data = new String[5];
            data[0] = m.getDate();
            data[1] = m.getTime();
            data[2] = m.getTeam1().getTeamName();
            data[3] = m.getTeam2().getTeamName();
            dataLines.add(data);
        }
        for (String[] data : dataLines) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                line.append(data[i]);
                if (i != data.length - 1) {
                    line.append(',');
                }
            }
            line.append("\n");
            fileWriter.write(line.toString());
        }
        fileWriter.close();
    }
}
