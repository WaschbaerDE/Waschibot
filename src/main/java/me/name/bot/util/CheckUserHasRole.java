package me.name.bot.util;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.math.BigInteger;

public class CheckUserHasRole {
    public boolean checkUserHasRole(MessageReceivedEvent e, String permissedRank) {
        GetRoleIdsFromEvent getRoleIdsFromEvent = new GetRoleIdsFromEvent();
        String[] userRanks = getRoleIdsFromEvent.getRoleIdsFromEvent(e);
        for(int i = 0; i < userRanks.length; i++){
            if(userRanks[i].equals(permissedRank)){
                return true;
            }
        }
        return false;
    }
}
