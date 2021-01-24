package me.name.bot.logger;

import me.name.bot.database.DatabaseConnector;
import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class ExecuteSqlCommand {
    private String sqlStatement;

    public void executeSqlCommand(String sqlStatement) {
        this.sqlStatement = sqlStatement;
        try {
            DatabaseConnector sqlConnector = new DatabaseConnector();

            Connection connection = sqlConnector.getSqlConnection();
            Statement statement = connection.createStatement();
            statement.execute(this.sqlStatement);
            connection.close();
        }
        catch (SQLIntegrityConstraintViolationException ea){
            //Verhindert das Schreiben doppelter Einträge
        }
        catch (Exception e){
            System.err.println("Sql-Command couldn't get executed: "+ this.sqlStatement);
            e.printStackTrace();
        }
    }
}
