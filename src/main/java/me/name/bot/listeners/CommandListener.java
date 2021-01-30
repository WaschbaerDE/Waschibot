package me.name.bot.listeners;

import me.name.bot.administration.SetQuoterRank;
import me.name.bot.events.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        String rawContent = e.getMessage().getContentRaw();
        String[] splitContentArray = rawContent.split(" ");
        //an event is happening

        if(rawContent.startsWith("$") && !rawContent.startsWith(" ",1)){
        //Basic
            //help event
            if(rawContent.startsWith("help",1)){
                System.out.println("__Help_Event_triggered!");
                HelpEvent helpEvent = new HelpEvent();
                helpEvent.executeHelpEvent(e);
            }
            //ping event
            else if(rawContent.startsWith("ping",1)){
                System.out.println("__Ping_Event_triggered!");
                Pingevent pingevent = new Pingevent();
                pingevent.executePingEvent(e);
            }
        //Pictures
            //random picture event
            else if(rawContent.startsWith("rndpic",1)){
                System.out.println("__RandomPicture_event_triggered!");
                RandomPictureEvent randomPictureEvent = new RandomPictureEvent();
                int anzahl = 1;
                try{
                    anzahl = Integer.parseInt(splitContentArray[1]);
                }catch(Exception exception){}
                System.out.println(anzahl);
                if(anzahl > 5){
                    e.getChannel().sendMessage("Sorry! Limit of 5 Pictures at once!").queue();
                }
                randomPictureEvent.executeRandomPictureEvent(e,anzahl);
            }
        //Quotes
            //addQuote event
            else if(rawContent.startsWith("addq",1) && rawContent.startsWith(" ",5)){
                System.out.println("__AddQuote_event_triggered!");
                AddQuoteEvent addQuoteEvent = new AddQuoteEvent();
                addQuoteEvent.executeAddQuoteEvent(e,splitContentArray[1].substring(3,21),7+splitContentArray[1].length());
            }
            //rndQuote event
            else if(rawContent.startsWith("rndq",1)){
                System.out.println("__RndQuote_event_triggered!");
                RandomQuoteEvent randomQuoteEvent = new RandomQuoteEvent();
                try {
                    randomQuoteEvent.randomQuoteEvent(e);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        //Admin
            //addquoterank event
            else if(rawContent.startsWith("addquoterrank",1 )){
                if(e.getMember().isOwner()){
                    System.out.println("__AddQuoteRank_event_triggered!");
                    SetQuoterRank setQuoterRank = new SetQuoterRank();
                    try {
                        setQuoterRank.setQuoterRank(e,splitContentArray[1].substring(3,21));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else{
                    e.getChannel().sendMessage("You need to be the Owner to execute this command!").queue();
                }
            }
        //Misc
            //unknown command
            else{
                e.getChannel().sendMessage("Unknown command! try $help").queue();
            }
        }
    }
}
