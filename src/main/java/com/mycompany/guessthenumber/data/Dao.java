/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.guessthenumber.data;

import com.mycompany.guessthenumber.model.Game;
import com.mycompany.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author chica
 */
public interface Dao {
    List<Round> getAll();
    
    Game findById(int id);
    
    int totalRounds(Round round);    
}
