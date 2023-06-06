package Monster;

import java.util.Random;

import Entity.Entity;
import Main.GamePanel;

public class MON_orc extends Entity{
    GamePanel gp;
    public MON_orc(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        direction = "down";
        name = "orc";
        speed = 2;
        maxLife = 10;
        life = maxLife;
        strength = 3;
        exp = 10;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width =  48;
        attackArea.height = 48;

        getImage();
        getAttackImage();
    }

    public void getImage(){
        
        up1 = setup("/res/Monster/orc_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/Monster/orc_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/res/Monster/orc_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/res/Monster/orc_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/Monster/orc_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/Monster/orc_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/Monster/orc_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/Monster/orc_right_2",gp.tileSize,gp.tileSize);

    }

    public void getAttackImage(){
        attackUp1 = setup("/res/Monster/orc_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 = setup("/res/Monster/orc_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackDown1 = setup("/res/Monster/orc_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setup("/res/Monster/orc_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setup("/res/Monster/orc_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setup("/res/Monster/orc_attack_left_2",gp.tileSize*2,gp.tileSize);
        attackRight1 = setup("/res/Monster/orc_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight2 = setup("/res/Monster/orc_attack_right_2",gp.tileSize*2,gp.tileSize);

    }
    public void update(){
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance <5){
            int i = new Random().nextInt(100)+1;
            if(i>50){
                onPath = true;
            }
        }
        if(onPath == true && tileDistance>20 ){
            onPath = false;
        }
    }
    public void setAction(){

        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            checkStopChasingOrNot(gp.player,15,100);

            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }

        else{
            checkStartChasingOrNot(gp.player,5,100);

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
        if(attacking == false){
            checkAttackOrNot(30,gp.tileSize*4,gp.tileSize);
        }


    }
    public void damageReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath= true;
        
    }
}