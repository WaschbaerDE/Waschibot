package me.name.bot.listeners;

import me.name.bot.Misc.SqlUtil;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VoiceChannelListener extends ListenerAdapter {



    @Override
    public void onGuildVoiceJoin (GuildVoiceJoinEvent event){
        writeVoiceJoinToConsole(event);
        writeVoiceJoinToDatabase_voice(event);
    }

    @Override
    public void onGuildVoiceLeave (GuildVoiceLeaveEvent event){
        writeVoiceLeaveToConsole(event);
        writeVoiceLeaveToDatabase_voice(event);
    }
//Join
    private void writeVoiceJoinToDatabase_voice(GuildVoiceJoinEvent event) {
        SqlUtil sqlUtil = new SqlUtil();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sqlUtil.executeSqlCommand("INSERT INTO t_guild_voice( guildId, channelId, memberId, datetime_join) VALUES ('" + event.getGuild().getId() + "','" + event.getChannelJoined().getId() + "','" + event.getMember().getId() + "','" + simpleDateFormat.format(new Date()) + "');");
    }
    private void writeVoiceJoinToConsole(GuildVoiceJoinEvent event){
        System.out.println(event.getMember().getEffectiveName()+" left "+ event.getChannelLeft().getName()+" on "+event.getGuild().getName());

    }

//Leave
    private void writeVoiceLeaveToDatabase_voice(GuildVoiceLeaveEvent event) {
        SqlUtil sqlUtil = new SqlUtil();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sqlUtil.executeSqlCommand("UPDATE t_guild_voice SET datetime_leave = '"+simpleDateFormat.format(new Date())+"' WHERE guildId = '"+event.getGuild().getId()+ "' AND channelId = '"+event.getChannelLeft().getId()+"' AND memberId = '"+event.getMember().getId()+"' AND datetime_leave = '9999-12-31 23:59:59';");
    }

    private void writeVoiceLeaveToConsole(GuildVoiceLeaveEvent event){
        System.out.println(event.getMember().getEffectiveName()+" left "+ event.getChannelLeft().getName()+" on "+event.getGuild().getName());

    }

}
