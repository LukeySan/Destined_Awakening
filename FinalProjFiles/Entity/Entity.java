package Entity;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class Entity {
    GamePanel gp;
    public int worldX;
    public int worldY;
    public int tempX,tempY;
    public int speed;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackDown1, attackDown2, attackDown3, attackLeft1, attackLeft2, attackLeft3, attackRight1,
    attackRight2, attackRight3;
    public String direction = "down";
    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision = false;
    public int type; //0 = player, 1 = npc , 2 = monster

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter;
    public boolean invincible = false;
    public int invincibleCounter  = 0;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarON = false;
    public boolean onPath = false;
    public int motion1_duration;
    public int motion2_duration;


    int dyingCounter = 0;
    int hpBarCounter = 0;




    public String dialogues[][] = new String[20][20];
    public int dialogueIndex= 0;
    public int dialogueSet = 0;

    //Player Status
    public int maxLife;
    public int life;
    public int level;
    public int strength;
    public int dexterity;
    
    public int defense;
    public int exp;
    public  int nextLevelExp;
    public Entity currentWeapon;
    

    

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public String getName(){
        return name;
    }
    public String getDirection(){
        return direction;
    }
    public void setAction(){}
    public void damageReaction(){}
    public void speak(){  }
    public void facePlayer(){
        switch(gp.player.direction){
            case"up":
                direction = "down";
                break;
            case"down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;

        }
    }
    public int  getCenterX(){
        int centerX = worldX+up1.getWidth()/2;
        return centerX;
    }
    public int getCenterY(){
        int centerY = worldY +up1.getHeight()/2;
        return centerY;
    }
    public int getXdistance(Entity target){
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }

    public int getYdistance(Entity target){
        int yDistance =  Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getTileDistance(Entity target){
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target){
        int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target){
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }
    public void attacking() {
    spriteCounter++;

    if (spriteCounter <= 5) {
        spriteNum = 1;
    }
    if (spriteCounter > 5 && spriteCounter <= 15) {
        spriteNum = 2;

        // Saves current worldx and worldy solid area position
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        // Adjust player's worldx/y for the attack area
        int attackAreaWidth = attackArea.width;
        int attackAreaHeight = attackArea.height;

        switch (direction) {
            case "up":
                worldY -= attackAreaHeight;
                break;
            case "down":
                worldY += attackAreaHeight;
                break;
            case "left":
                worldX -= attackAreaWidth;
                break;
            case "right":
                worldX += attackAreaWidth;
                break;
        }

        // Attack Area becomes solidArea
        solidArea.width = attackAreaWidth;
        solidArea.height = attackAreaHeight;

        // Perform collision checks and actions based on type
        if (type == 2) { // Monster
            if (gp.cChecker.checkPlayer(this)) {
                damagePlayer(strength);
            }
        }
        // After checking collision, restore the original data only if a collision occurred
        boolean collisionOccurred = (currentWorldX != worldX) || (currentWorldY != worldY);
        if (collisionOccurred) {
            worldX = currentWorldX;
            worldY = currentWorldY;
        }
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    }

    else if (spriteCounter > 15 && spriteCounter < 25) {
        spriteNum = 1;
        spriteCounter = 0;
        attacking = false;
    }
}

    public void checkAttackOrNot(int rate, int straight, int  horizontal){
        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch(direction){
            case"up":
                if(gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case"down":
                if(gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case"left":
                if(gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
            case"right":
                if(gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;      
            
        }

        if(targetInRange == true){
            int i = new Random().nextInt(rate);

            System.out.println(i);

            if (i == 0){
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }
    public void startDialogue(Entity entity, int setNum){

        if(gp.currentMap == 0 || gp.currentMap == 3){
            gp.gameState = gp.playDialogueState;
            

        }
        else if (gp.currentMap == 2){
            gp.gameState = gp.tutorialDialogueState;
        }
        gp.ui.npc = entity;
        dialogueSet = setNum;




    }
    public void checkCollision(){
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this,gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        gp.cChecker.checkEntity(this,gp.monster2);
        gp.cChecker.checkEntity(this,gp.monster3);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer == true){
           damagePlayer(strength);
        }
    }

    public void update(){

        
        if(attacking== true){
            attacking();
        }
        else{
            setAction();
            checkCollision();
            spriteCounter++;
             //IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn == false){
            switch(direction){
                case "up":
                    worldY -= speed;
                    break;
                case"down":
                    worldY += speed;
                    break;
                case"left":
                worldX -= speed;
                    break;
                case"right":
                worldX+= speed;
                    break;

            }
        }
        if(spriteCounter>5){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

       
        }
       
       

        
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter> 40){
                 invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void moveTowardPLayer(int interval){
        actionLockCounter++;
        if(actionLockCounter>interval){
            if(getXdistance(gp.player)>getYdistance(gp.player)){
                if(gp.player.getCenterX() < getCenterX()){
                    direction = "left";
                }
                else{
                    direction = "right";
                }
            }
            else if (getXdistance(gp.player) < getYdistance(gp.player)){
                if(gp.player.getCenterY() < getCenterY()){
                    direction = "up";
                }
                else{
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }

    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate){
        if(getTileDistance(target)<distance){
            int i = new Random().nextInt(rate);
            if (i == 0){
                onPath = true;
            }
        }
    }

    public void checkStopChasingOrNot(Entity target, int distance, int rate){
        if(getTileDistance(target)>distance){
            int i = new Random().nextInt(rate);
            if (i == 0){
                onPath =   false;
            }
        }
    }
    public void damagePlayer(int attack){
        if(gp.player.invincible == false){
            //Damage can be given

            int damage =  strength;

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize*5 >gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize <gp.player.worldX + gp.player.screenX &&
           worldY+ gp.tileSize*5 >gp.player.worldY - gp.player.screenY &&
           worldY- gp.tileSize <gp.player.worldY + gp.player.screenY){
            int tempScreenX = screenX;
            int tempScreenY = screenY;
    
            switch(direction){
                case"up":
                    if(attacking == false){
                        if(spriteNum == 1){
                            image = up1;
                        }
                        if(spriteNum == 2){
                            image = up2;
                        }if(spriteNum == 3){
                            image = up3;
                        }
                        
                    }
                    if(attacking == true){
                        tempScreenY = screenY - up1.getHeight();
                        if(spriteNum == 1){
                            image = attackUp1;
                        }
                        if(spriteNum == 2){
                            image = attackUp2;
                        }
                        if(spriteNum == 3){
                            image = attackUp3;
                        }
                        
                    }
                    break;
                case"down":
                if(attacking == false){
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    if(spriteNum == 3){
                        image = down3;
                    }
                  
                }
                if(attacking== true){
                    if(spriteNum == 1){
                        image = attackDown1;
                    }
                    if(spriteNum == 2){
                        image = attackDown2;
                    }
                    if(spriteNum == 3){
                        image = attackDown3;
                    }
                   
                }
                    break;
                case "left":
                if(attacking == false){
                    
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    if(spriteNum == 3){
                        image = left3;
                    }
                    
                }
                if(attacking== true){
                    tempScreenX = screenX - left1.getWidth();
                    if(spriteNum == 1){
                        image = attackLeft1;
                    }
                    if(spriteNum == 2){
                        image = attackLeft2;
                    }
                    if(spriteNum == 3){
                        image = attackLeft3;
                    }
                    
                }
                    break;
                case "right":
                if(attacking == false){
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                    if(spriteNum == 3){
                        image = right3;
                    }
                    
                }
                if(attacking== true){
                    if(spriteNum == 1){
                        image = attackRight1;
                    }
                    if(spriteNum == 2){
                        image = attackRight2;
                    }
                    if(spriteNum == 3){
                        image = attackRight3;
                    }
                    
                }
                    break;
            }
                //MONSTERHP BAR
                if(type == 2 && hpBarON == true){
                    //if monster max hp is 2 it will be 48 by 2, so 1 scale is 24  pixels.
                    //if  monster max hp is 4, one scale will be 12 pixels

                    double oneScale = (double)gp.tileSize/maxLife;
                    double hpBarValue= oneScale*life;


                    g2.setColor(Color.darkGray);
                    g2.fillRect(screenX-1,screenY-16,gp.tileSize+2,12);
                    g2.setColor(Color.RED);
                    g2.fillRect(screenX,screenY-15, (int)hpBarValue, 10);

                    hpBarCounter++;

                    if(hpBarCounter>600){
                        hpBarCounter = 0;
                        hpBarON = false;
                    }
                }

                if(invincible == true){
                    hpBarON = true;
                    hpBarCounter = 0;
                    changeAlpha(g2,0.4f);
                }

                if(dying == true){
                    dyingAnimation(g2);
                }

                g2.drawImage(image,tempScreenX,tempScreenY,null);

                changeAlpha(g2,1f);
                }
    }
    /* In every five frames, monster's alpha is witched to 0 or 1 to create a blinking effect */
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;

        int i = 15;

        if(dyingCounter <=i ){
            changeAlpha(g2,0f);}
        if(dyingCounter > i & dyingCounter <= i*2){
            changeAlpha(g2,1f);        }
        if(dyingCounter > i & dyingCounter <= i*3){
            changeAlpha(g2,0f);        }
        if(dyingCounter > i & dyingCounter <= i*4){
            changeAlpha(g2,1f);
        }if(dyingCounter > i & dyingCounter <= i*5){
            changeAlpha(g2,0f);
        }if(dyingCounter > i & dyingCounter <= i*6){
            changeAlpha(g2,1f);
        }if(dyingCounter > i & dyingCounter <= i*7){
            changeAlpha(g2,0f);
        }if(dyingCounter > i & dyingCounter <= i*8){
            changeAlpha(g2,1f);
        }if(dyingCounter > i*8 ){
            dying  = false;
            alive = false;
        }
    }
     public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height){

        UtilityTool uTool = new UtilityTool ();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream( imagePath + ".png"));
            image = uTool.scaleImage(image,width,height);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol,startRow,goalCol,goalRow,this);
        if(gp.pFinder.search() == true){

            //Next worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col*gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row*gp.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY  + gp.tileSize){
                if(enLeftX>nextX){
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX) {
                //up or left
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                //up or right
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX>nextX){
                //down or left
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }

            }
            else if (enTopY < nextY && enLeftX  <nextX){
                //down or left
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol  && nextRow == goalRow){
                onPath = false;

            }

        }
    }
    



}
