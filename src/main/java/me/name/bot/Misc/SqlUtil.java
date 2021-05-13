package me.name.bot.Misc;

import me.name.bot.secrets.DatabaseSecrets;

import java.sql.*;

public class SqlUtil {
    private String sqlStatement;

    public void executeSqlCommand(String sqlStatement) {
        this.sqlStatement = sqlStatement;
        try {
            Util util = new Util();

            Connection connection = getSqlConnection();
            Statement statement = connection.createStatement();
            statement.execute(this.sqlStatement);
            connection.close();
        } catch (SQLIntegrityConstraintViolationException ea) {
            //Verhindert das Schreiben doppelter Einträge
        } catch (Exception e) {
            System.err.println("Sql-Command couldn't get executed: " + this.sqlStatement);
            e.printStackTrace();
        }
    }

    public ResultSet executeSelectSqlCommand(String sqlStatement) {
        this.sqlStatement = sqlStatement;
        ResultSet rs = null;
        try {
            Util util = new Util();

            Connection connection = getSqlConnection();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(this.sqlStatement);
            connection.close();
        } catch (Exception e) {
            System.err.println("Sql-Command couldn't get executed: " + this.sqlStatement);
            e.printStackTrace();
        }
        return rs;

    }

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
