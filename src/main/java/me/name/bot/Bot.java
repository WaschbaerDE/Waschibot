package me.name.bot;

import me.name.bot.secrets.*;
import me.name.bot.events.CommandListener;
import me.name.bot.events.HelloEvent;
import me.name.bot.logger.MessageLogger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
    public static JDA jda;

    public static void main(String[] args) throws Exception
    {
        Token token = new Token();

        jda = JDABuilder.createLight(token.getOAuth2Token(), GatewayIntent.GUILD_MESSAGES)
                .setActivity(Activity.listening("dir bei $help"))
                .setStatus(OnlineStatus.ONLINE)
                .build();
        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new MessageLogger());
        jda.addEventListener(new CommandListener());

    }
}

