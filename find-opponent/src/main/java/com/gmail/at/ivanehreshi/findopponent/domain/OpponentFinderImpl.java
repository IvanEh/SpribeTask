package com.gmail.at.ivanehreshi.findopponent.domain;

import java.util.List;

public class OpponentFinderImpl implements OpponentFinder {
    private List<Player> readyPlayers;

    @Override
    public void addReadyPlayer(Player player) {
        readyPlayers.add(player);
    }

    @Override
    public Player findOpponent(Player player) throws IllegalArgumentException{
        return null;
    }

    @Override
    public int countReady() {
        return 0;
    }

    @Override
    public void clearReadyPlayers() {

    }

    @Override
    public int removePlayer(Player player) {
        return 0;
    }

    @Override
    public List<Player> playersAsList() {
        return null;
    }

    public List<Player> getReadyPlayers() {
        return readyPlayers;
    }

    public void setReadyPlayers(List<Player> readyPlayers) {
        this.readyPlayers = readyPlayers;
    }




}
