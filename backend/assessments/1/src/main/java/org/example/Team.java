package org.example;

import java.util.ArrayList;
import java.util.List;

public class Team {
    String teamName;
    List<Player> teamPlayers;

    List<Team> homeMatches;

    public Team(String teamName) {
        this.teamName = teamName;
        this.teamPlayers = new ArrayList<>();
        this.homeMatches = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(List<Player> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public List<Team> getHomeMatches() {
        return homeMatches;
    }

    public void setHomeMatches(List<Team> homeMatches) {
        this.homeMatches = homeMatches;
    }
}

