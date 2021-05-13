package me.name.bot.events.administration;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public  class SetBotActivity {

    public void setBotActivityEvent(MessageReceivedEvent e, String activity){

        if(activity.equals("playing")){
            e.getJDA().getPresence().setActivity(Activity.playing(e.getMessage().getContentRaw().substring(22)));
        }
        else if(activity.equals("listening")) {
            e.getJDA().getPresence().setActivity(Activity.listening(e.getMessage().getContentRaw().substring(23)));
        }
        else if(activity.equals("competing")) {
            e.getJDA().getPresence().setActivity(Activity.competing(e.getMessage().getContentRaw().substring(24)));
        }
        else if(activity.equals("streaming")){
            String messageContent = e.getMessage().getContentRaw().substring(24,e.getMessage().getContentRaw().indexOf("http://"));
            String streamUrl = "https://www.twitch.tv/WaschbaerDE";
            try {
                //check if substring is valid streaming url
                String url = messageContent.substring(messageContent.indexOf("http://"));
                if(Activity.isValidStreamingUrl(url)){
                    streamUrl = url;
                }
            }catch (Exception ignored){}
            e.getJDA().getPresence().setActivity(Activity.streaming(messageContent, streamUrl));
        }
        else if(activity.equals("watching")){
            e.getJDA().getPresence().setActivity(Activity.watching(e.getMessage().getContentRaw().substring(23)));
        }
    }
}
