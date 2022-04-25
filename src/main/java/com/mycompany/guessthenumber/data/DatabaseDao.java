/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessthenumber.data;

import com.mycompany.guessthenumber.model.Game;
import com.mycompany.guessthenumber.model.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chica
 */
@Repository
public class DatabaseDao implements Dao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> getAllGames() {
        final String cmd = "SELECT gameid, answer, finnished, attempts FROM game";
        return jdbcTemplate.query(cmd, new GameMapper());
    }

    @Override
    public Game gameById(int id) {
        final String cmd = "SELECT gameid, answer, finnished, attempts FROM game " +
                "WHERE gameId = ?";
        return jdbcTemplate.queryForObject(cmd, new GameMapper(), id);
    }

    @Override
    public List<Round> roundsById(int id) {
        final String cmd = "SELECT roundid, roundtime, result FROM round "+
                "WHERE gameId = ?";
        return jdbcTemplate.query(cmd, new RoundMapper(), id);
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
    
    @Override
    public Game addGame(Game game) {
        final String cmd = "INSERT INTO game(answer, finnished, attempts) VALUES(?,?,0);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                cmd, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.isFinished());
            return statement;

        }, keyHolder);
        
        game.setGameId(keyHolder.getKey().intValue());
        return game;
    }
    
    
}
