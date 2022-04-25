/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessthenumber.controller;

import com.mycompany.guessthenumber.data.Dao;
import com.mycompany.guessthenumber.model.Game;
import com.mycompany.guessthenumber.model.Round;
import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
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

    public Controller(Dao dao) {
        this.dao = dao;
    }
    
    @PostMapping("/begin") 
    @ResponseStatus(HttpStatus.CREATED)
    public Game startGame() {
        Random rand = new Random();
        Game game = new Game();
        game.setAnswer(String.valueOf(rand.nextInt(9999)));
        game.setFinished(false);
        game.setAttempts(0);
        return dao.addGame(game);
    }

    @PostMapping("/guess") 
    public void userGuess(@RequestBody String gameId, @RequestBody String guess) {
    }
    
    @GetMapping("/game")
    public List<Game> allGames() {
        return dao.getAllGames();
    }

    @GetMapping("/rounds")
    public List<Round> allRounds() {
        return dao.getAllRounds();
    }

    @GetMapping("/game/{id}")
    public Game gameById(int id) {
        return dao.gameById(id);
    }

    @GetMapping("/rounds/{id}")
    public List<Round> roundsById(int id) {
        return dao.roundsById(id);
    }
}
