package test;

public class testmain {
    public static void main(String[] args){
        for(int i=0;i<100;i++){
            System.out.println(generateRndChar());
        }
    }

        public static char generateRndLetter(){return (char)((int)(Math.random()*26)+97);}
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
