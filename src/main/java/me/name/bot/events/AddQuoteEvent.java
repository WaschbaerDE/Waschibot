package me.name.bot.events;

import me.name.bot.logger.ExecuteSqlCommand;
import me.name.bot.util.CheckUserHasRole;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddQuoteEvent {

    private String commiterId;
    private String quotedID;
    private String quoteContent;
    private String currentDateTime;
    private String guildId;
    private ResultSet rs;
    private String permittedRankId;

    public void executeAddQuoteEvent(MessageReceivedEvent e,String quotedID,int startpointQuote){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ExecuteSqlCommand executeSqlCommand = new ExecuteSqlCommand();

        currentDateTime = simpleDateFormat.format(new Date());
        this.guildId = e.getGuild().getId();
        this.rs = executeSqlCommand.executeSelectSqlCommand("SELECT rankId FROM t_permission WHERE guildID = '"+guildId+"' AND permissionID = 3;");
        this.permittedRankId = "";

        try {
            this.rs.next();
            this.permittedRankId = this.rs.getString("rankId");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CheckUserHasRole checkUserHasRole = new CheckUserHasRole();
        if(checkUserHasRole.checkUserHasRole(e,this.permittedRankId)){
            this.commiterId = e.getMember().getId();
            this.quotedID = quotedID;
            this.quoteContent = e.getMessage().getContentRaw().substring(startpointQuote);

            executeSqlCommand.executeSqlCommand("INSERT INTO t_quotes(guildID, commiterId, quotedId, quoteContent, datetime) VALUES('"+this.guildId+"','" + this.commiterId + "','" + this.quotedID + "','" + this.quoteContent + "','"+this.currentDateTime+"');");
            e.getChannel().sendMessage("Quote added!").queue();

        }
    }
}
