package com.gmail.at.ivanehreshi.findopponent.domain;

public interface OpponentFinder {
    void addReadyPlayer(Player player);

    default void addReadyPlayers(Player... players) {
        for(Player p: players) {
            addReadyPlayer(p);
        }
    }

    Player findOpponent(Player player);

}
