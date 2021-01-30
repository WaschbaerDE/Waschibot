package me.name.bot;

import me.name.bot.secrets.*;
import me.name.bot.listeners.CommandListener;
import me.name.bot.listeners.MessageReadEvent;
import me.name.bot.listeners.MessageLogger;
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
    //This command is to initialize the jda(DiscordBot)
    //It needs at least the ".createLight(Token, GatewayIntent.GUILD_MESSAGE).build()"
    //Other attributes are used for modification rigth away
        jda = JDABuilder.createLight(token.getTestOAuth2Token(), GatewayIntent.GUILD_MESSAGES)
                .setActivity(Activity.listening("dir bei $help"))
                .setStatus(OnlineStatus.ONLINE)
                .build();
    //This commands are used to add an EventListener to the bot
        jda.addEventListener(new MessageReadEvent());
        jda.addEventListener(new MessageLogger());
        jda.addEventListener(new CommandListener());
    }

}
