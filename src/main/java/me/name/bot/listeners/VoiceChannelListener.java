package me.name.bot.listeners;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceChannelListener extends ListenerAdapter {

//    @Override
//    public void onGuildVoiceUpdate (GuildVoiceUpdateEvent event){
//        System.out.println(event.getEntity().getNickname());
//    }

    @Override
    public void onGuildVoiceJoin (GuildVoiceJoinEvent event){
        System.out.println(event.getMember().getEffectiveName()+" joined "+ event.getChannelJoined().getName()+" on "+event.getGuild().getName());
    }

    @Override
    public void onGuildVoiceLeave (GuildVoiceLeaveEvent event){
        System.out.println(event.getMember().getEffectiveName()+" left "+ event.getChannelLeft().getName()+" on "+event.getGuild().getName());
    }

}
