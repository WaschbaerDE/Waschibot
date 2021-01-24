package me.name.bot.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Pingevent {

    public void executePingEvent(MessageReceivedEvent e){
        if(Math.random()<0.1){
            e.getChannel().sendMessage("Ping").queue();
            try {
                wait(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            e.getChannel().sendMessage("Mhh.... Pong!").queue();
        }
        else {
            e.getChannel().sendMessage("Pong!").queue();
        }
    }
}
