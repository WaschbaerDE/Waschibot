package me.name.bot.Misc;

import net.dv8tion.jda.api.entities.Message;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlStatements {

    private final String guildId;
    private final String guildName;
    private final String channelID;
    private final String channelName;
    private final String userId;
    private final String userName;
    private final String messageId;
    private final String messageContent;
    private final String currentDateTime;

    private ResultSet rs;


    public SqlStatements(Message recentMessage){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.guildId = recentMessage.getGuild().getId();
        this.guildName = recentMessage.getGuild().getName();
        this.channelID = recentMessage.getChannel().getId();
        this.channelName = recentMessage.getChannel().getName();
        this.userId = recentMessage.getMember().getId();
        this.userName = recentMessage.getMember().getEffectiveName();
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
        sqlUtil.executeSqlCommand("INSERT INTO t_guild_messages( guildId, channelId, memberId,  messageId, messageContent, datetime) VALUES ('" + this.guildId + "','" + this.channelID + "','" + this.userId + "','" + this.messageId + "','" + this.messageContent + "','" + this.currentDateTime + "');");
    }

    private void writeMessageToDatabase_guild() {
        SqlUtil sqlUtil = new SqlUtil();
        this.rs = sqlUtil.executeSelectSqlCommand("SELECT id FROM t_guild where guildID = '"+this.channelID+"' AND guildName <> '"+this.channelName +"' AND datetime_end = '999-12-31 23:59:59';");
        try {
            if(rs.isBeforeFirst()){
                sqlUtil.executeSqlCommand("INSERT INTO t_guild(guildId, guildName, datetime_start) VALUES('" + this.guildId + "','" + this.guildName + "','" + this.currentDateTime + "');");
            }
            while (rs.next()){
                sqlUtil.executeSqlCommand("UPDATE t_guild SET datetime_end = '"+currentDateTime+"' WHERE id = '"+ rs.getInt(1)+"';");
            }
        }catch (Exception ignored){}
    }

    private void writeMessageToDatabase_channel() {
        SqlUtil sqlUtil = new SqlUtil();
        this.rs = sqlUtil.executeSelectSqlCommand("SELECT id FROM t_channel where channelID = '"+this.channelID+"' AND channelName <> '"+this.channelName +"' AND datetime_end = '999-12-31 23:59:59';");
        try {
            if(rs.isBeforeFirst()){
                sqlUtil.executeSqlCommand("INSERT INTO t_channel(channelId, channelName, datetime_start) VALUES('" + this.channelID + "','" + this.channelName + "','" + this.currentDateTime + "');");
            }
            while (rs.next()){
                sqlUtil.executeSqlCommand("UPDATE t_channel SET datetime_end = '"+currentDateTime+"' WHERE id = '"+ rs.getInt(1)+"';");
            }
        }catch (Exception ignored){}
    }

    private void writeMessageToDatabase_user() {
        SqlUtil sqlUtil = new SqlUtil();
        this.rs = sqlUtil.executeSelectSqlCommand("SELECT id FROM t_user where guildId = '"+this.guildId+"' AND userId = '"+this.userId +"' AND userName <> '"+this.userName +"' AND datetime_end = '999-12-31 23:59:59';");
        try {
            if(rs.isBeforeFirst()){
                sqlUtil.executeSqlCommand("INSERT INTO t_user(guildId, userId, userName, datetime_start) VALUES('" + this.guildId + "','" + this.userId + "','" + this.userName + "','" + this.currentDateTime + "');");
            }
            while (rs.next()){
                sqlUtil.executeSqlCommand("UPDATE t_user SET datetime_end = '"+currentDateTime+"' WHERE id = '"+ rs.getInt(1)+"';");
            }
        }catch (Exception ignored){}
    }
}
