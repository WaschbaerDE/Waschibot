package me.name.bot.util;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.math.BigInteger;

public class GetRoleIdsFromEvent {
    public String[] getRoleIdsFromEvent(MessageReceivedEvent e){
        Object[] roleObject = e.getMember().getRoles().toArray();
        String[] roleIds = new String[roleObject.length];
        for(int i = 0; i <roleObject.length;i++){
            String Role = roleObject[i].toString();
            roleIds[i] = (Role.substring(Role.lastIndexOf("(")+1,Role.lastIndexOf(")")));
        }
        return roleIds;
    }
}
