package Entity;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Main.GamePanel;
import Main.KeyHandler;



public class NPC_OldMan extends Entity{

    KeyHandler keyH;


    public NPC_OldMan (GamePanel gp){
        super(gp);
        
        
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage(){

        up1 = setup("/res/npc/oldman_up_1");
        up2 = setup("/res/npc/oldman_up_2");
        down1 = setup("/res/npc/oldman_down_1");
        down2 = setup("/res/npc/oldman_down_2");
        left1 = setup("/res/npc/oldman_left_1");
        left2 = setup("/res/npc/oldman_left_2");
        right1 = setup("/res/npc/oldman_right_1");
        right2 = setup("/res/npc/oldman_right_2");
    }

    public void setDialogue(){

        dialogues[0][0] = "Hello lad";
        dialogues[0][1] = "So you've come to this island to find the treasure?";
        dialogues[0][2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
        dialogues[0][3] = "Well, good luck on you.";

        dialogues[1][0] = "If you get tired you can rest at the water";
        dialogues[1][1] = "However, the monsters reappear if you rest. \nI don't know why but that's how it works.";
        dialogues[1][2] = "In any case, don't push yourself too hard.";

        dialogues[2][0]= "I wonder how to open that door...";
        


    }
    
    public void setAction(){

        actionLockCounter ++;
        if(actionLockCounter == 120){
        Random random = new Random();
        int i = random.nextInt(100)+1; // pick up a number from 1 to 100

        if(i<=25){
            direction = "up";
        }

        if(i>25&& i <=50){
            direction="down";
        }

        if(i>50&&i<=75){
            direction = "left";
        }

        if(i>75 && i<=100){
            direction = "right";
        }
        actionLockCounter = 0;
     }
    }
    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);
    }

    



   
}
