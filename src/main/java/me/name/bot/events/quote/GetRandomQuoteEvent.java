package me.name.bot.events.quote;

import me.name.bot.Misc.SqlUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class GetRandomQuoteEvent {
    private String guildId;
    private ResultSet rs;
    private int positionquote;

    private String quotedId;
    private String quoteContent;
    private String datetime;
    private String currentquotedName;


    public void randomQuoteEvent(MessageReceivedEvent e) throws SQLException {

        SqlUtil sqlUtil = new SqlUtil();

        this.guildId = e.getGuild().getId();
        this.rs = sqlUtil.executeSelectSqlCommand("SELECT COUNT(*) AS countQuotes FROM t_quotes WHERE guildId = '"+this.guildId+"';");

        rs.next();
        this.positionquote = (int)(Math.random() * rs.getInt("countQuotes"));

        this.rs = sqlUtil.executeSelectSqlCommand("SELECT commiterId, quotedId, quoteContent, datetime FROM t_quotes WHERE guildId = '"+this.guildId+"'ORDER BY id LIMIT "+positionquote+",1;");

        rs.next();
        this.quotedId = rs.getString("quotedId");
        this.quoteContent = rs.getString("quoteContent");
        this.datetime = rs.getString("datetime");

        this.rs = sqlUtil.executeSelectSqlCommand("SELECT userName from t_user WHERE guildId = '"+this.guildId+"' AND userId = '"+this.quotedId+"' ORDER by datetime_start desc LIMIT 0,1;");
        rs.next();
        this.currentquotedName =rs.getString("userName");

        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle("Random-Quote");
        embedBuilder.setDescription("\""+this.quoteContent+"\"");
        embedBuilder.setFooter("said by "+this.currentquotedName);
        this.datetime = this.datetime.replace(" ","T")+"Z";
        embedBuilder.setTimestamp(Instant.parse(this.datetime));

        e.getChannel().sendMessage(embedBuilder.build()).queue();

    }
}
