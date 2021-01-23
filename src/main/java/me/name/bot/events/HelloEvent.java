package me.name.bot.events;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class HelloEvent extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event)
    {
        System.out.println("I am ready to go!");
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        System.out.println("random message: " + e.getMessage().getContentRaw());
    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
        System.out.println("private message: "+e.getMessage().getContentRaw());
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        System.out.println("guild message: "+e.getMessage().getContentRaw());
    }

//    @Override
//    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
//        System.out.println(e.getGuild().getName()+" || " + e.getChannel().getName()+" || "+e.getMember().getEffectiveName()+" || "+e.getMessage().getContentRaw());
//    }
}
