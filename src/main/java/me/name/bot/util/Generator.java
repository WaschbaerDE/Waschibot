package me.name.bot.util;

public class Generator {
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
