package test;

public class testmain {
    public static void main(String[] args) {

        String messageString = "$asd 123 kgjasd 4325";
        String[] arr = splitStringToArray(messageString);

        for(int i =0; i<arr.length;i++){
            System.out.println(arr[i]);
        }


    }

    public static String[] splitStringToArray(String string){
        return string.substring(1).split(" ");
    }
}
