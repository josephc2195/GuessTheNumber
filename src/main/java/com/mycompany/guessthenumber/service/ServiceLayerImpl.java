/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessthenumber.service;

import com.mycompany.guessthenumber.data.Dao;
import com.mycompany.guessthenumber.data.DatabaseDao;
import com.mycompany.guessthenumber.model.Game;
import com.mycompany.guessthenumber.model.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chica
 */
@Repository
public class ServiceLayerImpl implements ServiceLayer {

    private final Dao dao;

    @Autowired
    public ServiceLayerImpl(JdbcTemplate jdbcTemplate) {
        dao = new DatabaseDao(jdbcTemplate);

    }

    @Override
    public List<Game> getAllGames() {

        List<Game> games = dao.getAllGames();
        games.forEach((g) -> {
            if (!g.isFinished()) {
                g.setAnswer("####");
            }
        });

        return games;
    }

    @Override
    public Game getGameById(int id) {

        Game game = dao.gameById(id);

        if (!game.isFinished()) {
            game.setAnswer("####");
        }

        return game;

    }

    @Override
    public void startGame(Game game) {
        dao.addGame(game);
    }

    @Override
    public String guess(int gameId, String guess) {

        Game game = dao.gameById(gameId);

        StringBuilder answer = new StringBuilder(game.getAnswer());
        StringBuilder guessBuilder = new StringBuilder(guess);

        int exact = 0;
        int partial = 0;

        // check the position looking for exact matches
        for (int i = 0; i < answer.length(); i++) {
            if (guessBuilder.charAt(i) == answer.charAt(i)) {

                //increment a counter for exact matches and remove matched numbers
                answer.deleteCharAt(i);
                guessBuilder.deleteCharAt(i);
                exact++;
            }
        }

        for (int i = 0; i < answer.length(); i++) {

            if (answer.indexOf(guessBuilder.charAt(i) + "") != -1) {

                //increment a counter for exact matches and remove matched numbers
                answer.deleteCharAt(answer.indexOf(guessBuilder.charAt(i) + ""));
                guessBuilder.deleteCharAt(i);
                partial++;
            }
        }

        return "e: " + exact + " p: " + partial;
    }

    @Override
    public List<Round> getRounds(int id) {
        return dao.roundsById(id);
    }

}
