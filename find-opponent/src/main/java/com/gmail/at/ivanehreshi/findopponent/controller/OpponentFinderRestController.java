package com.gmail.at.ivanehreshi.findopponent.controller;

import com.gmail.at.ivanehreshi.findopponent.domain.OpponentFinder;
import com.gmail.at.ivanehreshi.findopponent.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OpponentFinderRestController {
    @Autowired
    private OpponentFinder opponentFinder;

    @GetMapping("/ready-players")
    public List<Player> readyUsers() {
        return opponentFinder.playersAsList();
    }

    @PostMapping("/ready-players")
    public void addReadyUser(@RequestBody Player player) {
        opponentFinder.addReadyPlayer(player);
    }

    @DeleteMapping("/ready-players")
    public void removeUsers(@RequestParam(defaultValue = "false") boolean filter,
                            @RequestBody Player player) {
        if(filter) {
            opponentFinder.removePlayer(player);
        } else {
            opponentFinder.clearReadyPlayers();
        }
    }

    @DeleteMapping("/ready-players/opponent")
    public Player findOpponent(@RequestBody Player player) {
        return opponentFinder.findOpponent(player);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public void handleIllegalArgumentException() {
    }

}
