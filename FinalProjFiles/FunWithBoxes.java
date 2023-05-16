
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FunWithBoxes{
    public static void main(String[]args){
        String name = "";
        String[] words = {"Apple","donkey","chicken","terminator","alarmClock"};
        //Scanner scan = new Scanner(System.in);
       // System.out.println("Hello");
        name = JOptionPane.showInputDialog("What is your name?");
        JOptionPane.showMessageDialog(null,"Hello, " + name);
        boolean play = true;
        while(play){

            for (int i = 0; i<5 ; i++){
                int rand = (int)(Math.random() *words.length);
                JOptionPane.showMessageDialog(null,"Remember this word: " + words[rand]);
            }
            int val = JOptionPane.showConfirmDialog(null,"Would you like to play again?");
            if(val == JOptionPane.NO_OPTION){
                play = false;
            }
        }
        
    }
}