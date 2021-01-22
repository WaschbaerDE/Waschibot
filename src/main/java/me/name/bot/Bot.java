package me.name.bot;

import me.name.bot.events.HelloEvent;
import me.name.bot.events.HelpEvent;
import me.name.bot.logger.MessageLogger;
import me.name.bot.secrets.Token;
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
                .setActivity(Activity.listening("dir immer"))
                .setStatus(OnlineStatus.ONLINE)
                .build();

        jda.addEventListener(new MessageLogger());

        jda.addEventListener(new HelloEvent());
        //jda.addEventListener(new CommandListener());
        jda.addEventListener(new HelpEvent());
    }
}
//command
