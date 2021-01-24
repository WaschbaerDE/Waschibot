package me.name.bot.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String rawContent = e.getMessage().getContentRaw();
        //event
        if(rawContent.startsWith("$") && !rawContent.startsWith(" ",1)){
            //help
            if(rawContent.startsWith("help",1)){
                System.out.println("__Help_Event_triggered!");
                HelpEvent helpEvent = new HelpEvent();
                helpEvent.executeHelpEvent(e);
            }
            //ping
            if(rawContent.startsWith("ping",1)){
                System.out.println("__Ping_Event_triggered!");
                Pingevent pingevent = new Pingevent();
                pingevent.executePingEvent(e);
            }
        }
    }
}
