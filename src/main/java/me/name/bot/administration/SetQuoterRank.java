package me.name.bot.administration;

import me.name.bot.logger.ExecuteSqlCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.SQLException;

public class SetQuoterRank {
    private String guildId;
    private String permittedRankdId;

    public void setQuoterRank(MessageReceivedEvent e, String quoterrank) throws SQLException {
        ExecuteSqlCommand executeSqlCommand = new ExecuteSqlCommand();

        this.guildId = e.getGuild().getId();
        this.permittedRankdId = quoterrank;

        executeSqlCommand.executeSqlCommand("INSERT INTO t_permission(guildID, rankId, permissionId) VALUES('"+ this.guildId +"','"+ this.permittedRankdId +"','3');");
        e.getChannel().sendMessage("Rank added!").queue();
    }
}
