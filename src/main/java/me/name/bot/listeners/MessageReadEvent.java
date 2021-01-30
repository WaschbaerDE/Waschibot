package me.name.bot.listeners;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReadEvent extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("I am ready to go!");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        System.out.println("__Message: " +e.getGuild().getName()+" || "+e.getChannel().getName()+" || "+e.getMember().getEffectiveName()+" || "+ e.getMessage().getContentRaw());
    }

}
