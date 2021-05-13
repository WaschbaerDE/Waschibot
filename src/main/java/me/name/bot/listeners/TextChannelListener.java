package me.name.bot.listeners;

import me.name.bot.events.administration.SetBotStatus;
import me.name.bot.events.quote.AddQuoteEvent;
import me.name.bot.events.quote.GetRandomQuoteEvent;
import me.name.bot.events.quote.SetQuoterRank;
import me.name.bot.events.*;
import me.name.bot.Misc.SqlStatements;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.sql.SQLException;

public class TextChannelListener extends ListenerAdapter {


    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("I am ready to go!");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String rawContentMessage = e.getMessage().getContentRaw();

        if(rawContentMessage.startsWith("$")){
            String[] messageArray = splitStringToArray(rawContentMessage);

        //Basic
            //help event
            if(messageArray[0].equals("help")){
                System.out.println("__Help_Event_triggered!");
                HelpEvent helpEvent = new HelpEvent();
                helpEvent.executeHelpEvent(e);
            }
            //ping event
            else if(messageArray[0].equals("ping")){
                System.out.println("__Ping_Event_triggered!");
                Pingevent pingevent = new Pingevent();
                pingevent.executePingEvent(e);
            }
        //Pictures
            //random picture event
            else if(messageArray[0].equals("rndpic")){
                System.out.println("__RandomPicture_event_triggered!");
                RandomPictureEvent randomPictureEvent = new RandomPictureEvent();
                int anzahl = 1;
                try{
                    anzahl = Integer.parseInt(messageArray[1]);
                }catch(Exception ignored){}
                if(anzahl > 5){
                    e.getChannel().sendMessage("Sorry! Limit of 5 Pictures at once!").queue();
                }
                randomPictureEvent.executeRandomPictureEvent(e,anzahl);
            }
        //Quotes
            //addQuote event
            else if(messageArray[0].equals("addq")){
                System.out.println("__AddQuote_event_triggered!");
                AddQuoteEvent addQuoteEvent = new AddQuoteEvent();
                addQuoteEvent.executeAddQuoteEvent(e,messageArray[1].substring(3,21),7+messageArray[1].length());
            }
            //rndQuote event
            else if(messageArray[0].equals("rndq")){
                System.out.println("__RndQuote_event_triggered!");
                GetRandomQuoteEvent randomQuoteEvent = new GetRandomQuoteEvent();
                try {
                    randomQuoteEvent.randomQuoteEvent(e);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        //LocalAdmin
            //addquoterank event
            else if(messageArray[0].equals("addquoterrank")){
                if(e.getMember().isOwner()){
                    System.out.println("__AddQuoteRank_event_triggered!");
                    SetQuoterRank setQuoterRank = new SetQuoterRank();
                    try {
                        setQuoterRank.setQuoterRank(e,messageArray[1]);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else{
                    e.getChannel().sendMessage("You need to be the Owner to execute this command!").queue();
                }
            }
            //changeprefix event
            else if(messageArray[0].equals("changeprefix")){
                System.out.println("__ChangePrefix_event_triggered!");
                ChangePrefixEvent changePrefixEvent = new ChangePrefixEvent();
                changePrefixEvent.executeChangePrefixEvent(e,messageArray);
            }
        //GlobalAdmin
            //setBotStatus event
            else if(messageArray[0].equals("setbotstatus")){
                System.out.println("__SetBotStatus_event_triggered!");
                SetBotStatus setBotStatus = new SetBotStatus();
                setBotStatus.setBotStatusEvent(e);
            }
        //Misc
            //unknown command
            else{
                e.getChannel().sendMessage("Unknown command! try $help").queue();
            }
        }
        //
            logMessageInDataBase(e);
            writeMessageToConsole(e);
    }
    private String[] splitStringToArray(String string){
        return string.substring(1).split(" ");
    }

    private void logMessageInDataBase(MessageReceivedEvent event){
        Message recentMessage = event.getMessage();
        SqlStatements sqlWriter = new SqlStatements(recentMessage);
        sqlWriter.writeMessageToDatabase();
    }

    private void writeMessageToConsole(MessageReceivedEvent event){
        System.out.println("__Message: " +event.getGuild().getName()+" || "+event.getChannel().getName()+" || "+event.getMember().getEffectiveName()+" || "+ event.getMessage().getContentRaw());

    }
}
