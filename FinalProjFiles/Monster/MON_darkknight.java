package Monster;

import java.util.Random;

import Entity.Entity;
import Main.GamePanel;

public class MON_darkknight extends Entity{
    GamePanel gp;
    public static final String monName = "Dark Knight";

    public MON_darkknight(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = 2;
        direction = "down";
        name = "monName";
        speed = 4;
        maxLife = 50;
        life = maxLife;
        strength = 9;
        exp = 300;

        int size  = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        
        

        getImage();
        getAttackImage();

    }
    public void getImage(){

        int i = 5;
        
       up1 = setup("/res/monster/darkknight_up_1",gp.tileSize*i,gp.tileSize*i);
        up2 = setup("/res/monster/darkknight_up_2",gp.tileSize*i,gp.tileSize*i);
        down1 = setup("/res/monster/darkknight_down_1",gp.tileSize*i,gp.tileSize*i);
        down2 = setup("/res/monster/darkknight_down_2",gp.tileSize*i,gp.tileSize*i);
        left1 = setup("/res/monster/darkknight_left_1",gp.tileSize*i,gp.tileSize*i);
        left2 = setup("/res/monster/darkknight_left_2",gp.tileSize*i,gp.tileSize*i);
        right1 = setup("/res/monster/darkknight_right_1",gp.tileSize*i,gp.tileSize*i);
        right2 = setup("/res/monster/darkknight_right_2",gp.tileSize*i,gp.tileSize*i);

        
    }

    public void getAttackImage(){
        int i = 5;
        attackUp1 = setup("/res/monster/darkknight_attack_up_1",gp.tileSize*i,gp.tileSize*i*2);
        attackUp2 = setup("/res/monster/darkknight_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
        attackDown1 = setup("/res/monster/darkknight_attack_down_1",gp.tileSize*i,gp.tileSize*i*2);
        attackDown2 = setup("/res/monster/darkknight_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
        attackLeft1 = setup("/res/monster/darkknight_attack_left_1",gp.tileSize*i*2,gp.tileSize*i);
        attackLeft2 = setup("/res/monster/darkknight_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
        attackRight1 = setup("/res/monster/darkknight_attack_right_1",gp.tileSize*i*2,gp.tileSize*i);
        attackRight2 = setup("/res/monster/darkknight_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
    }
   

    public void setAction(){
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance <5){
            int i = new Random().nextInt(100)+1;
            if(i>50){
                onPath = true;
            }
        }
       

        if(getTileDistance(gp.player)<10){
            moveTowardPLayer(15);
        }

        else{
            actionLockCounter ++;
            if(actionLockCounter > 120){
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

            if(this.attacking == false){
                checkAttackOrNot(30, gp.tileSize*10, gp.tileSize*10);
            }

        }
    }
    public void damageReaction(){
        actionLockCounter = 0;
        attacking();
        
    }
}
