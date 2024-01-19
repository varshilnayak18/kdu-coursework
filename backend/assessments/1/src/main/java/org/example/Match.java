package org.example;

import java.util.Date;
import java.util.List;

public class Match {

    String date;
    String time;
    Team team1;
    Team team2;

    public Match(Team team1, Team team2, String date, String time) {
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
