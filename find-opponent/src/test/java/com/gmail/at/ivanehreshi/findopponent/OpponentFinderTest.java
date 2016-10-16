package com.gmail.at.ivanehreshi.findopponent;

import com.gmail.at.ivanehreshi.findopponent.domain.OpponentFinder;
import com.gmail.at.ivanehreshi.findopponent.domain.OpponentFinderImpl;
import com.gmail.at.ivanehreshi.findopponent.domain.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class OpponentFinderTest {
    private OpponentFinder opponentFinder;

    @Before
    public void setUp(){
        opponentFinder = new OpponentFinderImpl();
    }

    @After
    public void tearDown(){
        opponentFinder = null;
    }

    @Test
    public void countReady_NoPlayers() {
        assertEquals(0, opponentFinder.countReady());
    }

    @Test
    public void countReady_TwoPlayers() {
        opponentFinder.addReadyPlayers(new Player(), new Player());
        assertEquals(2, opponentFinder.countReady());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOpponent_NoPlayers(){
        opponentFinder.findOpponent(new Player("name", 1));
    }

    @Test
    public void findOpponent_OnePlayer() {
        Player player = new Player("name", 1);
        opponentFinder.addReadyPlayer(player);

        assertNull(opponentFinder.findOpponent(player));
        assertEquals(1, opponentFinder.countReady());
    }

    @Test
    public void addReadyPlayer_Null() {
        opponentFinder.addReadyPlayer(null);
        opponentFinder.addReadyPlayers(null, null);
        assertEquals(0, opponentFinder.countReady());
    }

    @Test
    public void addReadyPlayer_CountOne() {
        Player player = new Player("name", 1);
        opponentFinder.addReadyPlayer(player);

        assertEquals(1, opponentFinder.countReady());
    }

    @Test
    public void addReadyPlayers_CountOne() {
        Player player = new Player("name", 1);
        opponentFinder.addReadyPlayers(player);

        assertEquals(1, opponentFinder.countReady());
    }

    @Test
    public void addReadyPlayers_CountTwo() {
        opponentFinder.addReadyPlayers(new Player("p1", 1), new Player("p2", 1));

        assertEquals(2, opponentFinder.countReady());
    }

    @Test
    public void findOpponent_TwoPlayer_Ok() {
        Player p1 = new Player("player 1", 1);
        Player p2 = new Player("player 2", 1);

        opponentFinder.addReadyPlayers(p1, p2);

        Player opponent = opponentFinder.findOpponent(p2);

        assertSame(p1, opponent);
        assertEquals(0, opponentFinder.countReady());
    }

    @Test
    public void clear_NoPlayers_ExpectNoException() {
        opponentFinder.clearReadyPlayers();
    }

    @Test
    public void clear_SizeZero() {
        opponentFinder.addReadyPlayers(new Player(), new Player(), new Player());

        assertEquals(3, opponentFinder.countReady());

        opponentFinder.clearReadyPlayers();

        assertEquals(0, opponentFinder.countReady());
    }

    @Test
    public void findOpponent_Max() {
        Player[] players = {
                new Player("p0", 0), new Player("p1", 1), new Player("p2", 10),
                new Player("p5", 1000), new Player("p6", 7.1), new Player("p7", 7.3)
        };
        opponentFinder.addReadyPlayers(players);
        Player opponent = opponentFinder.findOpponent(players[3]);

        assertSame(players[2], opponent);
    }

    @Test
    public void findOpponent_Min() {
        Player[] players = {
                new Player("p0", 0), new Player("p1", 1), new Player("p2", 10),
                new Player("p5", 1000), new Player("p6", 7.1), new Player("p7", 7.3)
        };
        opponentFinder.addReadyPlayers(players);
        Player opponent = opponentFinder.findOpponent(players[0]);

        assertSame(players[1], opponent);
    }

    @Test
    public void findOpponent_EqualDistanceFirstBigger() {
        Player[] players = {
                new Player("p0", 0), new Player("p1", 7.2), new Player("p2", 10),
                new Player("p5", 1000), new Player("p6", 7.3), new Player("p7", 7.1)
        };
        opponentFinder.addReadyPlayers(players);
        Player opponent = opponentFinder.findOpponent(players[1]);

        assertTrue(players[4] == opponent || players[5] == opponent);
    }

    @Test
    public void findOpponent_EqualDistanceFirstLess() {
        Player[] players = {
                new Player("p0", 0), new Player("p1", 7.2), new Player("p2", 10),
                new Player("p5", 1000), new Player("p6", 7.1), new Player("p7", 7.3)
        };
        opponentFinder.addReadyPlayers(players);
        Player opponent = opponentFinder.findOpponent(players[1]);

        assertTrue(players[4] == opponent || players[5] == opponent);
    }

    @Test
    public void asList_NoPlayers() {
        List<Player> players = opponentFinder.playersAsList();
        assertEquals(Collections.EMPTY_LIST, players);
    }

    @Test
    public void asList_TwoPlayers() {
        Player p1 = new Player("p1", 1);
        Player p2 = new Player("p2", 42);
        opponentFinder.addReadyPlayers(p1, p2);
        List<Player> players = opponentFinder.playersAsList();
        assertTrue(players.contains(p1));
        assertTrue(players.contains(p2));
        assertEquals(2, players.size());
    }
}
