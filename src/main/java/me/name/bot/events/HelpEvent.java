package me.name.bot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.Instant;

public class HelpEvent {

    public void executeHelpEvent(MessageReceivedEvent e) {
            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle("Help-Menu");
            embedBuilder.setDescription("I can't help you as well! :sob: ");
            embedBuilder.setColor(7111128);
            embedBuilder.addField("Basic-Commands","$help\n$ping",false);
            embedBuilder.addField("Image-Commands","$rndpic 1-5",false);
            embedBuilder.addField("Quote-Commands","$addq @quoteduser quote <-needs permission ($addqoterrank)",false);
            embedBuilder.addField("Musik-Commands","coming soooooon",false);
            embedBuilder.addField("Admin-Commands","Only Owner can do this!\n$addqoterrank @quoterrank <-this rank gives permission to add quotes",false);
            embedBuilder.setFooter("send by Waschibot");
            embedBuilder.setTimestamp(Instant.now());

            e.getChannel().sendMessage(embedBuilder.build()).queue();
    }

}
