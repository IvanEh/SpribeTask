package com.gmail.at.ivanehreshi.findopponent.domain;

import java.util.List;

public interface OpponentFinder {
    void addReadyPlayer(Player player);

    default void addReadyPlayers(Player... players) {
        for(Player p: players) {
            addReadyPlayer(p);
        }
    }

    Player findOpponent(Player player) throws IllegalArgumentException;

    int countReady();
    void clearReadyPlayers();
    boolean removePlayer(Player player);
    List<Player> playersAsList();
}
