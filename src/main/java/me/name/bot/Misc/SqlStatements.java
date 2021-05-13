package me.name.bot.Misc;

import me.name.bot.Misc.SqlUtil;
import net.dv8tion.jda.api.entities.Message;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlStatements {

    private final String guildId;
    private String guildName;
    private String channelID;
    private String channelName;
    private String memberId;
    private String memberName;
    private String messageId;
    private String messageContent;
    private String currentDateTime;

    public SqlStatements(Message recentMessage){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.guildId = recentMessage.getGuild().getId();
        this.guildName = recentMessage.getGuild().getName();
        this.channelID = recentMessage.getChannel().getId();
        this.channelName = recentMessage.getChannel().getName();
        this.memberId = recentMessage.getMember().getId();
        this.memberName = recentMessage.getMember().getEffectiveName();
        this.messageId = recentMessage.getId();
        this.messageContent = recentMessage.getContentRaw();
        currentDateTime = simpleDateFormat.format(new Date());}

//logMessageInDataBase
    public void writeMessageToDatabase() {
        writeMessageToDatabase_guild_message();
        writeMessageToDatabase_guild();
        writeMessageToDatabase_channel();
        writeMessageToDatabase_user();
    }

    private void writeMessageToDatabase_guild_message() {
        SqlUtil sqlUtil = new SqlUtil();
        sqlUtil.executeSqlCommand("INSERT INTO t_guild_messages( guildId, channelId, memberId,  messageId, messageContent, datetime) VALUES ('" + this.guildId + "','" + this.channelID + "','" + this.memberId + "','" + this.messageId + "','" + this.messageContent + "','" + this.currentDateTime + "');");
    }

    private void writeMessageToDatabase_guild() {
        SqlUtil sqlUtil = new SqlUtil();
        sqlUtil.executeSqlCommand("INSERT INTO t_guild(guildId, guildName, datetime_start) VALUES('" + this.guildId + "','" + this.guildName + "','" + this.currentDateTime + "');");
    }

    private void writeMessageToDatabase_channel() {
        SqlUtil sqlUtil = new SqlUtil();
        sqlUtil.executeSqlCommand("INSERT INTO t_channel(channelId, channelName, datetime_start) VALUES('" + this.channelID + "','" + this.channelName + "','" + this.currentDateTime + "');");
    }

    private void writeMessageToDatabase_user() {
        SqlUtil sqlUtil = new SqlUtil();
        sqlUtil.executeSqlCommand("INSERT INTO t_user(guildId, userId, userName, datetime_start) VALUES('" + this.guildId + "','" + this.memberId + "','" + this.memberName + "','" + this.currentDateTime + "');");
    }


}
