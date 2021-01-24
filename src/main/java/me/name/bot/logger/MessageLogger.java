package me.name.bot.logger;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageLogger extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        Message recentMessage = event.getMessage();

       SqlWriter sqlWriter = new SqlWriter(recentMessage);
       sqlWriter.writeMessageToDatabase();
    }
}
