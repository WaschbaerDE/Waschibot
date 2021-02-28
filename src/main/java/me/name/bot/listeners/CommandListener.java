package me.name.bot.listeners;

import me.name.bot.administration.SetQuoterRank;
import me.name.bot.events.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;
import java.util.Locale;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        String messageString = e.getMessage().getContentRaw();

        if(messageString.startsWith("$")){

            //splits Message in Array
            //$rndpic 5 test-> String Array {$;rncpic;5}
            String[] messageArray = new String[3];
            String[] placeholderArray = messageString.substring(1).trim().split(" ");
            for(int i = 1; i < placeholderArray.length && i < messageArray.length; i++) {
                messageArray[i] = placeholderArray[i-1];
            }
            if(placeholderArray.length==1){
                messageArray[1]=messageString.substring(1);
            }

        //Basic
            //help event
            if(messageArray[1].equals("help")){
                System.out.println("__Help_Event_triggered!");
                HelpEvent helpEvent = new HelpEvent();
                helpEvent.executeHelpEvent(e);
            }
            //ping event
            else if(messageArray[1].equals("ping")){
                System.out.println("__Ping_Event_triggered!");
                Pingevent pingevent = new Pingevent();
                pingevent.executePingEvent(e);
            }
        //Pictures
            //random picture event
            else if(messageArray[1].equals("rndpic")){
                System.out.println("__RandomPicture_event_triggered!");
                RandomPictureEvent randomPictureEvent = new RandomPictureEvent();
                int anzahl = 1;
                try{
                    anzahl = Integer.parseInt(messageArray[2]);
                }catch(Exception exception){}
                System.out.println(anzahl);
                if(anzahl > 5){
                    e.getChannel().sendMessage("Sorry! Limit of 5 Pictures at once!").queue();
                }
                randomPictureEvent.executeRandomPictureEvent(e,anzahl);
            }
        //Quotes
            //addQuote event
            else if(messageArray[1].equals("addq")){
                System.out.println("__AddQuote_event_triggered!");
                AddQuoteEvent addQuoteEvent = new AddQuoteEvent();
                addQuoteEvent.executeAddQuoteEvent(e,messageArray[2].substring(3,21),7+messageArray[2].length());
            }
            //rndQuote event
            else if(messageArray[1].equals("rndq")){
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
            else if(messageArray[1].equals("addquoterrank")){
                if(e.getMember().isOwner()){
                    System.out.println("__AddQuoteRank_event_triggered!");
                    SetQuoterRank setQuoterRank = new SetQuoterRank();
                    try {
                        setQuoterRank.setQuoterRank(e,messageArray[2]);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else{
                    e.getChannel().sendMessage("You need to be the Owner to execute this command!").queue();
                }
            }
            //changeprefix event
            else if(messageArray[1].equals("changeprefix")){
                System.out.println("__ChangePrefix_event_triggered!");
                ChangePrefixEvent changePrefixEvent = new ChangePrefixEvent();
                changePrefixEvent.executeChangePrefixEvent(e,messageArray);
            }
        //Misc
            //unknown command
            else{
                e.getChannel().sendMessage("Unknown command! try $help").queue();
            }
        }
    }
}
