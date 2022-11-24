package me.luminousllama.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import me.luminousllama.modles.PlayerStats;

public class Database {
    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }
        String url = "jdbc:mysql://localhost/stat_tracker";
        String username = "root";
        String password = "";

        this.connection = DriverManager.getConnection(url, username, password);
        System.out.println("connection successful");

        return connection;
    }

    public void initializeDatabase() throws SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS player_stats(uuid varchar(36) primary key, deaths int,kills int, blocks_broken long, balance double, last_login DATE, last_logout DATE)";
        statement.execute(sql);

        System.out.println("created table");
        statement.close();
    }

    public PlayerStats getPlayerStatsByUUID(String uuid) throws SQLException{
        Statement statement = getConnection().createStatement();
        
        String sql = "SELECT * FROM player_stats WHERE uuid = " + uuid;
        
        ResultSet results = statement.executeQuery(sql);

        if(results.next()){
            int deaths = results.getInt("deaths");
            int kills = results.getInt("kills");
            long blocksBroken = results.getLong("blocks_broken");
            double balance = results.getDouble("balance");
            Date lastLogin = results.getDate("last_login");
            Date lastLogout = results.getDate("last_logout");

            PlayerStats stats = new PlayerStats(uuid, deaths, kills, blocksBroken, balance, lastLogin, lastLogout);

            statement.close();

            return stats;
        }

        statement.close();

        return null;
    }
}
