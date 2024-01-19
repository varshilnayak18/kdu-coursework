package org.example;

import java.util.List;

public class Player {
    String name;
    String role;
    long matches;

    long runs;

    double average;

    double strikeRate;

    long wickets;

    public Player(String name, String role, long matches, long runs, double average, double strikeRate, long wickets) {
        this.name = name;
        this.role = role;
        this.matches = matches;
        this.runs = runs;
        this.average = average;
        this.strikeRate = strikeRate;
        this.wickets = wickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getMatches() {
        return matches;
    }

    public void setMatches(long matches) {
        this.matches = matches;
    }

    public long getRuns() {
        return runs;
    }

    public void setRuns(long runs) {
        this.runs = runs;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public long getWickets() {
        return wickets;
    }

    public void setWickets(long wickets) {
        this.wickets = wickets;
    }
}
