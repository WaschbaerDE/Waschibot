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

        String guildId = recentMessage.getGuild().getId();
        String guildName = recentMessage.getGuild().getName();
        String channelID = recentMessage.getChannel().getId();
        String channelName = recentMessage.getChannel().getName();
        String memberId = recentMessage.getMember().getId();
        String memberName = recentMessage.getMember().getEffectiveName();
        String messageId = recentMessage.getId();
        String messageContent = recentMessage.getContentRaw();

        DatabaseConnector sqlConnector = new DatabaseConnector();
        try {
            Connection connection = sqlConnector.getSqlConnection();
            Statement statement = connection.createStatement();
            String sqlStatement ="INSERT INTO t_guild_messages( guildId, guildName, channelId, channelName, memberId, memberName, messageId, messageContent) VALUES ('"+guildId+"','"+guildName+"','"+channelID+"','"+channelName+"','"+memberId+"','"+memberName+"','"+messageId+"','"+messageContent+"');";
            statement.execute(sqlStatement);
            connection.close();
        }catch(Exception e){
            System.err.println("logger couldn't write");
            e.printStackTrace();
        }
    }
}
