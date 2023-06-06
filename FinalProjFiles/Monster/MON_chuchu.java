package Monster;

import java.util.Random;

import Entity.Entity;
import Main.GamePanel;

public class MON_chuchu extends Entity{
    GamePanel gp;
    public MON_chuchu(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        direction = "down";
        name = "chuchu";
        speed = 2;
        maxLife = 4;
        life = maxLife;
        strength = 1;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public String getDirection(){
        return direction;
    }

    public void getImage(){
        
        up1 = setup("/res/monster/chuchu_down1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/monster/chuchu_down2",gp.tileSize,gp.tileSize);
        down1 = setup("/res/monster/chuchu_down1",gp.tileSize,gp.tileSize);
        down2 = setup("/res/monster/chuchu_down2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/monster/chuchu_down1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/monster/chuchu_down2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/monster/chuchu_down1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/monster/chuchu_down2",gp.tileSize,gp.tileSize);

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
        if(onPath == true && tileDistance>20 ){
            onPath = false;
        }

        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);
        }

        else{
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


    }
    public void damageReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath= true;
        
    }
}