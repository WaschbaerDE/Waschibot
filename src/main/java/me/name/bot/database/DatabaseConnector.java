package me.name.bot.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {

    public Connection getSqlConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            DatabaseSecrets secrets = new DatabaseSecrets();
            return DriverManager.getConnection(secrets.getUrl() + secrets.getDatabase(), secrets.getUser(), secrets.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}