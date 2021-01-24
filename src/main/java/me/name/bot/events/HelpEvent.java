package me.name.bot.events;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpEvent {
    private String description = "I can't help you as well! :sob: \n" +
                                "Current Commands:\n" +
                                "$help $ping";


    public void executeHelpEvent(MessageReceivedEvent e) {
        String rawContent = e.getMessage().getContentRaw();
        if(rawContent.startsWith("$") && rawContent.startsWith("help",1)){
            MessageEmbed messageEmbed = new MessageEmbed(null,"Help-Menu",description,null,null,10038562,null,null,null, null, null, null,null);
            e.getChannel().sendMessage(messageEmbed).queue();

        }
    }

}
