/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.guessthenumber.service;

import com.mycompany.guessthenumber.model.Game;
import com.mycompany.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author chica
 */
public interface ServiceLayer {
    
    List<Game> getAllGames();
    
    Game getGameById(int id);
    
    void startGame(Game game);
    
    String guess(int gameId, String guess); 
    
    List<Round> getRounds(int id);
}
