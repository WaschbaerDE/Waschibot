package test;

public class testmain {
    public static void main(String[] args) {
        try {
            String messageString = "$asd";
            String[] messageArray = new String[3];
            messageArray[0] = messageString.substring(0, 1);
            String[] placeholderArray = messageString.substring(1).trim().split(" ");
            System.out.println("Length: " + placeholderArray.length);
            for (int i = 1; i < placeholderArray.length && i < 3; i++) {
                messageArray[i] = placeholderArray[i - 1];
            }
            if(placeholderArray.length==1){
                messageArray[1]=messageString.substring(1);
            }

            for (int i = 0; i < 3; i++) {
                System.out.println(messageArray[i]);
            }



        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
