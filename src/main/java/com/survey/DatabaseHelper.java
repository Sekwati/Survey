/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sekwa
 */
public class DatabaseHelper {
    
    private static final String DB_URL = "jdbc:sqlite:survey.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTables() {
        String sql = """
            CREATE TABLE IF NOT EXISTS survey (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                full_name TEXT NOT NULL,
                email TEXT NOT NULL,
                dob TEXT,
                contact TEXT,
                pizza INTEGER,
                pasta INTEGER,
                pap_wors INTEGER,
                other INTEGER,
                movie_rating INTEGER,
                radio_rating INTEGER,
                eatout_rating INTEGER,
                tv_rating INTEGER
            );
        """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static Map<String, Object> getSurveyStatistics() {
    Map<String, Object> stats = new HashMap<>();

    String sql = """
        SELECT
            COUNT(*) AS total,
            AVG(CAST(strftime('%Y', 'now') - strftime('%Y', dob) AS INTEGER)) AS average_age,
            MIN(CAST(strftime('%Y', 'now') - strftime('%Y', dob) AS INTEGER)) AS youngest,
            MAX(CAST(strftime('%Y', 'now') - strftime('%Y', dob) AS INTEGER)) AS oldest,
            AVG(movie_rating) AS avg_movie,
            AVG(radio_rating) AS avg_radio,
            AVG(eatout_rating) AS avg_eatout,
            AVG(tv_rating) AS avg_tv,
            AVG(pizza * 100.0) AS percent_pizza,
            AVG(pasta * 100.0) AS percent_pasta,
            AVG(pap_wors * 100.0) AS percent_papwors
        FROM survey
    """;

    try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
        if (rs.next()) {
            stats.put("total", rs.getInt("total"));
            stats.put("average_age", rs.getDouble("average_age"));
            stats.put("youngest", rs.getInt("youngest"));
            stats.put("oldest", rs.getInt("oldest"));

            stats.put("avg_movie", rs.getDouble("avg_movie"));
            stats.put("avg_radio", rs.getDouble("avg_radio"));
            stats.put("avg_eatout", rs.getDouble("avg_eatout"));
            stats.put("avg_tv", rs.getDouble("avg_tv"));

            stats.put("percent_pizza", rs.getDouble("percent_pizza"));
            stats.put("percent_pasta", rs.getDouble("percent_pasta"));
            stats.put("percent_papwors", rs.getDouble("percent_papwors"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return stats;
   }

    
}
