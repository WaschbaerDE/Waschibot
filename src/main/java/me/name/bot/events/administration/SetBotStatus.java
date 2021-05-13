package me.name.bot.events.administration;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public  class SetBotStatus{

    public void setBotStatusEvent(MessageReceivedEvent e){
        //  e.getJDA().getPresence().setActivity(Activity.playing("die Doge-Hymne!"));
        //e.getJDA().getPresence().setActivity(Activity.listening("der Doge-Hymne!"));
        e.getJDA().getPresence().setActivity(Activity.competing("die Doge-Hymne!"));


    }

        //übergabe von String[1] je nach eingabe müssen andere Methoden ausgeführt werden
        //dann mit dieser Methode den entsprechenden Status setzten

}
