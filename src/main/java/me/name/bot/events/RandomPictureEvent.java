package me.name.bot.events;

import me.name.bot.util.Generator;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RandomPictureEvent {

    //execute randomPictureEvent
    public void executeRandomPictureEvent(MessageReceivedEvent e,int count){
        for(int i = 0;i<count && i < 5;i++) {
            e.getChannel().sendMessage(getImageURL()).queue();
        }
    }


    //get random prntscrn url
    public static String generateRndURL(){
        Generator generator = new Generator();
        return "https://prnt.sc/"+generator.generateRndChar()+generator.generateRndChar()+generator.generateRndChar()+generator.generateRndChar()+generator.generateRndChar()+generator.generateRndChar();
    }
    //getImageURL of random prntscrn url
    public static String getImageURL(){
        String sourceCode = "";
        String imageUrl = "";
        while(imageUrl.length() < 5|| imageUrl.length() > 100) {
            String rndUrl = generateRndURL();
            try {
                sourceCode = getURLSource(rndUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String searchStringFront = "content=\"https://image.prntscr.com/image";
            imageUrl = sourceCode.substring(sourceCode.indexOf(searchStringFront) + 9, sourceCode.indexOf(".png\"") + 4);
        }
        return imageUrl;
    }
    //get Website-Sourcecode from URL
    public static String getURLSource(String url) throws IOException
    {
        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());
    }
    //convert InputStream to String
    private static String toString(InputStream inputStream) throws IOException
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }
            return stringBuilder.toString();
        }
    }
}
