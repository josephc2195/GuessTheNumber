/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessthenumber.model;

import java.time.LocalDate;

/**
 *
 * @author chica
 */
public class Round {
    private int roundId;
    private int gameId;
    private LocalDate roundtime;
    private String result;
    private String guess;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
 
    public LocalDate getRoundtime() {
        return roundtime;
    }

    public void setRoundtime(LocalDate roundtime) {
        this.roundtime = roundtime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public String getGuess() {
        return this.guess;
    }
    
    public void setGuess(String guess) {
        this.guess = guess;
    }
    
}
