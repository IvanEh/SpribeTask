package com.gmail.at.ivanehreshi.findopponent.domain;

import java.util.List;

public interface OpponentFinder {
    void addReadyPlayer(Player player);

    default void addReadyPlayers(Player... players) {
        for(Player p: players) {
            addReadyPlayer(p);
        }
    }

    /**
     * Find a ready player with the closest rating to the current player. Both players
     * will be removed from the ready players list
     * @throws IllegalArgumentException when player is not a ready player
     */
    Player findOpponent(Player player) throws IllegalArgumentException;

    int countReady();
    void clearReadyPlayers();
    boolean removePlayer(Player player);
    List<Player> playersAsList();
}
