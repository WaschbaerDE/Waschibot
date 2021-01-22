package me.name.bot.events;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class HelloEvent extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        System.out.println("guild message");
    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
        System.out.println("private message");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        System.out.println("message");
    }
}
//sd