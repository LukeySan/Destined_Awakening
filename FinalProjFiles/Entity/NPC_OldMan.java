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

        dialogues[0][0] = "So you've finally awaken lad..";
        dialogues[0][1] = "You are the chosen one who can defeat the evil\nknight!";
        dialogues[0][2] = "You can go to his lair and fight him now, but you\nare quite weak at the moment.";
        dialogues[0][3] = "I suggest you go and train by fighting weaker\nmonsters first until you feel you are ready to fight\nthe evil knight.";
        dialogues[0][4] = "Now go young one! I wish you luck...";

        dialogues[1][0] = "You can go to his lair and fight him now, but you\nare quite weak at the moment.";
        dialogues[1][1] = "I suggest you go and train by fighting weaker\nmonsters first until you feel you are ready to fight\nthe evil knight.";
        dialogues[1][2] = "Now go young one! I wish you luck...";

        
        


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

        if(dialogues[dialogueSet][0]==null){
            dialogueSet--;
        }
    }

    



   
}
