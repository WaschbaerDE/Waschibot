package me.name.bot.logger;

import me.name.bot.database.DatabaseConnector;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.Connection;
import java.sql.Statement;

public class MessageLogger extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        Message recentMessage = event.getMessage();

       SqlWriter sqlWriter = new SqlWriter(recentMessage);
       sqlWriter.writeMessageToDatabase();
    }
}
