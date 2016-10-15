package com.gmail.at.ivanehreshi.findopponent.domain;

import java.util.*;
import java.util.stream.Collectors;

public class OpponentFinderImpl implements OpponentFinder {
    private TreeMap<Player, List<Player>> readyPlayers;  // Actually MultiSet
    private int size;

    public OpponentFinderImpl() {
        readyPlayers = new TreeMap<>(new PlayerComparator());
        size = 0;
    }

    @Override
    public void addReadyPlayer(Player player) {
        if(player == null)
            return;

        List<Player> players = readyPlayers.get(player);

        if(players != null) {
            players.add(player);
        } else {
            players = new ArrayList<>();
            players.add(player);
            readyPlayers.put(player, players);
        }

        ++this.size;
    }

    @Override
    public Player findOpponent(Player player) throws IllegalArgumentException{
        boolean removed = this.removePlayer(player);


        if(!removed) {
            throw new IllegalArgumentException("Cannot find opponent for busy Player");
        }

        if(readyPlayers.isEmpty()) {
            addReadyPlayer(player);
            return null;
        }

        Player lowerPlayer = anyPlayer(readyPlayers.floorEntry(player));
        Player higherPlayer = anyPlayer(readyPlayers.ceilingEntry(player));

        Player nearestPlayer = findNearest(player, lowerPlayer, higherPlayer);
        removePlayer(nearestPlayer);
        return nearestPlayer;
    }

    private Player findNearest(Player player, Player lowerPlayer, Player higherPlayer) {
        if(lowerPlayer == null) {
            return higherPlayer;
        }

        if(higherPlayer == null) {
            return lowerPlayer;
        }

        double lowDelta = Math.abs(player.getRating() - lowerPlayer.getRating());
        double hiDelta = Math.abs(player.getRating() - higherPlayer.getRating());

        if(lowDelta >= hiDelta) {
            return lowerPlayer;
        } else {
            return higherPlayer;
        }
    }

    @Override
    public int countReady() {
        return size;
    }

    @Override
    public void clearReadyPlayers() {
        readyPlayers.clear();
        size = 0;
    }

    @Override
    public boolean removePlayer(Player player) {
        List<Player> players = readyPlayers.get(player);

        if(players == null) {
            return false;
        }

        boolean removed = players.remove(player);
        if(removed) {
            this.size--;

            if(players.isEmpty()) {
                readyPlayers.remove(player);
            }
        }

        return removed;
    }

    @Override
    public List<Player> playersAsList() {
        List<Player> players = readyPlayers.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((playerList) -> playerList.stream())
                .collect(Collectors.toList());

        return players;
    }

    private static class PlayerComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            double delta = p1.getRating() - p2.getRating();
            if(delta == 0 && p1.getName() != null) {
                return p1.getName().compareTo(p2.getName());
            }

            return (int) Math.signum(delta);
        }
    }

    private static Player anyPlayer(Map.Entry<Player, List<Player>> entry) {
        if (entry == null) {
            return null;
        }

        return entry.getValue().get(0);
    }


}
