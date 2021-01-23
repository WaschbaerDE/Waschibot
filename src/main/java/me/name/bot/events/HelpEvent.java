package me.name.bot.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpEvent {

    public void executeHelpEvent(MessageReceivedEvent e) {
        String rawContent = e.getMessage().getContentRaw();
        if(rawContent.startsWith("$") && rawContent.startsWith("help",1)){
            System.out.println("Event happening");
            e.getChannel().sendMessage("Hallo ich bin ein toller Bot aber ich kann dir nicht helfen!").queue();

        }
    }

}
