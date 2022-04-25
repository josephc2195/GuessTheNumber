/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessthenumber.controller;

import com.mycompany.guessthenumber.data.Dao;
import com.mycompany.guessthenumber.model.Game;
import com.mycompany.guessthenumber.model.Round;
import com.mycompany.guessthenumber.service.ServiceLayer;
import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author chica
 */
@RestController
@RequestMapping("/")
public class Controller {

    private final Dao dao;
    private final ServiceLayer service;

    public Controller(Dao dao, ServiceLayer service) {
        this.dao = dao;
        this.service = service;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public void startGame() {
        Random rand = new Random();
        Game game = new Game();
        game.setAnswer(String.valueOf(rand.nextInt(9999)));
        game.setFinished(false);
        game.setAttempts(0);
        service.startGame(game);
    }

    @PostMapping("/guess")
    public Round userGuess(@RequestBody Round round) {
//        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
//        if (round.getGameId() != round.getGameId()) {
//            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
//        } else if (dao.addRound(round)) {
//            response = new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
        String result = service.guess(round.getGameId(), round.getGuess());
        if (result.equals("e:e:e:e")) {
            Game game = service.getGameById(round.getGameId());
            game.setFinished(true);
        }
        //dao.addRound(round);
        return round;
    }

    @GetMapping("/game")
    public List<Game> allGames() {
        return service.getAllGames();
    }

    @GetMapping("/game/{id}")
    public Game gameById(@PathVariable int id) {
        return service.getGameById(id);
    }

    @GetMapping("/rounds/{id}")
    public List<Round> roundsById(@PathVariable int id) {
        return service.getRounds(id);
    }
}
