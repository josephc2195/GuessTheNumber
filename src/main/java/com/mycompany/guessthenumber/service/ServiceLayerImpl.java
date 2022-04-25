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
public class ServiceLayerImpl implements ServiceLayer{
    

    private final Dao dao;
    
    @Autowired
    public ServiceLayerImpl(JdbcTemplate jdbcTemplate){
        dao = new DatabaseDao(jdbcTemplate);
       
    }
  
    
    @Override
    public List<Game> getAllGames() {
        
       List<Game> games = dao.getAllGames();
               games.forEach((g)-> {
                if(!g.isFinished()) g.setAnswer("####");
        });
              
        return games;
    }

    @Override
    public Game getGameById(int id) {
        
        Game game = dao.gameById(id);
        
        if(!game.isFinished()) game.setAnswer("####");
        
        return game;
        
    }

    @Override
    public void startGame(Game game) {
        dao.addGame(game);
    }

    @Override
    public String guess(int gameId, String guess ) {
        
        return "blank";
    }

    @Override
    public List<Round> getRounds(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
