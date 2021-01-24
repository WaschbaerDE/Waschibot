package me.name.bot.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String rawContent = e.getMessage().getContentRaw();
        if(rawContent.startsWith("$") && !rawContent.startsWith(" ",1)){
            System.out.println("Event happening");
            if(rawContent.startsWith("help",1)){
                System.out.println("__Help_Event_triggered!");
                HelpEvent helpEvent = new HelpEvent();
                helpEvent.executeHelpEvent(e);
            }
        }
    }
}
