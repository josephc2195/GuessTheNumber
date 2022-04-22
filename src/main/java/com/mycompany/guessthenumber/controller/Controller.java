/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessthenumber.controller;

import com.mycompany.guessthenumber.data.Dao;
import com.mycompany.guessthenumber.model.Game;
import com.mycompany.guessthenumber.model.Round;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chica
 */
@RestController
@RequestMapping("/game")
public class Controller {

    private final Dao dao;

    public Controller(Dao dao) {
        this.dao = dao;
    }

    @GetMapping("/game")
    public List<Game> allGames() {
        return dao.getAllGames();
    }

    @GetMapping("/round")
    public List<Round> allRounds() {
        return dao.getAllRounds();
    }

    @GetMapping("/game/{id}")
    public Game gameById(int id) {
        return dao.findById(id);
    }

}
