/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessthenumber.data;

import com.mycompany.guessthenumber.model.Game;
import com.mycompany.guessthenumber.model.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chica
 */
@Repository
public class DatabaseDao implements Dao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    DatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> getAllGames() {
        final String cmd = "SELECT gameid, answer, finnished, attempts FROM game";
        return jdbcTemplate.query(cmd, new GameMapper());
    }

    @Override
    public List<Round> getAllRounds() {
        final String cmd = "SELECT roundid, gameid, roundtime, result";
        return jdbcTemplate.query(cmd, new RoundMapper());

    }

    @Override
    public Game findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int totalRounds(Round round) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game g1 = new Game();
            g1.setGameId(rs.getInt("gameid"));
            g1.setAnswer(rs.getString("answer"));
            g1.setFinished(rs.getBoolean("finnished"));
            g1.setAttempts(rs.getInt("attempts"));
            return g1;
        }
    }
    
    private static final class RoundMapper implements RowMapper<Round> {
        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            Round r1 = new Round();
            r1.setGameId(rs.getInt("gameId"));
            r1.setResult(rs.getString("result"));
            r1.setRoundId(rs.getInt("roundid"));
            r1.setRoundtime(LocalDate.now());
            return r1;
        }
        
    }
}
