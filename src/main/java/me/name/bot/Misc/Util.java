package me.name.bot.Misc;

import me.name.bot.secrets.DatabaseSecrets;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

//Permissions
    public String[] getRoleIdsFromEvent(MessageReceivedEvent e){
        Object[] roleObject = e.getMember().getRoles().toArray();
        String[] roleIds = new String[roleObject.length];
        for(int i = 0; i <roleObject.length;i++){
            String Role = roleObject[i].toString();
            roleIds[i] = (Role.substring(Role.lastIndexOf("(")+1,Role.lastIndexOf(")")));
        }
        return roleIds;
    }

    public boolean checkUserHasRole(MessageReceivedEvent e, String permissedRank) {
        String[] userRanks = getRoleIdsFromEvent(e);
        for(int i = 0; i < userRanks.length; i++){
            if(userRanks[i].equals(permissedRank)){
                return true;
            }
        }
        return false;
    }

//Generator
    //get random [a-z]
    public static char generateRndLetter(){return (char)((int)(Math.random()*26)+97);}

    //get random [0-9]
    public static char generateRndDigit(){return(char)((int)(Math.random()*10)+48); }

    //get random [a-z]or[0-9]
    public static char generateRndChar(){
        if(Math.random()*37<27){
            return generateRndLetter();
        }else{
            return generateRndDigit();
        }
    }




}
