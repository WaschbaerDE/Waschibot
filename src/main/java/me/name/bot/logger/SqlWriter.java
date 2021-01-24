package me.name.bot.logger;

import net.dv8tion.jda.api.entities.Message;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlWriter {

    private String guildId;
    private String guildName;
    private String channelID;
    private String channelName;
    private String memberId;
    private String memberName;
    private String messageId;
    private String messageContent;
    private String currentDateTime;

    public SqlWriter(Message recentMessage){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        guildId = recentMessage.getGuild().getId();
        guildName = recentMessage.getGuild().getName();
        channelID = recentMessage.getChannel().getId();
        channelName = recentMessage.getChannel().getName();
        memberId = recentMessage.getMember().getId();
        memberName = recentMessage.getMember().getEffectiveName();
        messageId = recentMessage.getId();
        messageContent = recentMessage.getContentRaw();
        currentDateTime = simpleDateFormat.format(new Date());   }

    public void writeMessageToDatabase() {
        writeMessageToDatabase_guild_message();
        writeMessageToDatabase_guild();
        writeMessageToDatabase_channel();
        writeMessageToDatabase_user();
    }

    private void writeMessageToDatabase_guild_message() {
        ExecuteSqlCommand executeSqlCommand = new ExecuteSqlCommand();
        executeSqlCommand.executeSqlCommand("INSERT INTO t_guild_messages( guildId, channelId, memberId,  messageId, messageContent, datetime) VALUES ('" + this.guildId + "','" + this.channelID + "','" + this.memberId + "','" + this.messageId + "','" + this.messageContent + "','" + this.currentDateTime + "');");
    }

    private void writeMessageToDatabase_guild() {
        ExecuteSqlCommand executeSqlCommand = new ExecuteSqlCommand();
        executeSqlCommand.executeSqlCommand("INSERT INTO t_guild(guildId, guildName, datetime_start) VALUES('" + this.guildId + "','" + this.guildName + "','" + this.currentDateTime + "');");
    }

    private void writeMessageToDatabase_channel() {
        ExecuteSqlCommand executeSqlCommand = new ExecuteSqlCommand();
        executeSqlCommand.executeSqlCommand("INSERT INTO t_channel(channelId, channelName, datetime_start) VALUES('" + this.channelID + "','" + this.channelName + "','" + this.currentDateTime + "');");
    }

    private void writeMessageToDatabase_user() {
        ExecuteSqlCommand executeSqlCommand = new ExecuteSqlCommand();
        executeSqlCommand.executeSqlCommand("INSERT INTO t_user(userId, userName, datetime_start) VALUES('" + this.memberId + "','" + this.memberName + "','" + this.currentDateTime + "');");
    }
    //
}
