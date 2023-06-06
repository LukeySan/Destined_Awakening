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
        
        up1 = setup("/res/npc/oldman_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/npc/oldman_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/res/npc/oldman_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/res/npc/oldman_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/npc/oldman_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/npc/oldman_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/npc/oldman_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/npc/oldman_right_2",gp.tileSize,gp.tileSize);
    }

    public void setDialogue(){

        dialogues[0][0] = "So you've finally awaken lad..";
        dialogues[0][1] = "You are the chosen one who can defeat the evil\nknight!";
        dialogues[0][2] = "You can go to his lair and fight him now, but you\nare quite weak at the moment.";
        dialogues[0][3] = "I suggest you go and train by fighting weaker\nmonsters first until you feel you are ready to fight\nthe evil knight.";
        dialogues[0][4] = "If you are wounded, find a small body of water\nto rest in by pressing ENTER.\nYou should feel fully rejuvenated afterwards.";
        dialogues[0][5] = "If you ever want to talk again you can find me over\nhere..";
        dialogues[0][6] = "Now go young one! I wish you luck...";

        dialogues[1][0] = "You can go to his lair and fight him now, but you\nare quite weak at the moment.";
        dialogues[1][1] = "I suggest you go and train by fighting weaker\nmonsters first until you feel you are ready to fight\nthe evil knight.";
        dialogues[1][2] = "If you are wounded, find a small body of water\nto rest in by pressing ENTER.\nYou should feel fully rejuvenated afterwards.";
        dialogues[1][3] = "If you ever want to talk again you can find me over\nhere..";
        dialogues[1][4] = "Now go young one! I wish you luck...";

        
        


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
