package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;
    public boolean attackCanceled = false;


    
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(0,0, gp.tileSize,gp.tileSize);
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        System.out.println("DEFAULT VALUES SET");
        switch (GamePanel.gameState){
            case 1:
            worldX = gp.tileSize*94;
            worldY = gp.tileSize*164;
            System.out.println("big map position loaded!");
            break;

            case 2:
            worldX = gp.tileSize*24;
            worldY = gp.tileSize*21;
            System.out.println("TUTORIAL VALUES SET");
            break;

            case 3:
            worldX = gp.tileSize*94;
            worldY = gp.tileSize*164;
            System.out.println("big map position loaded!");
            break;

        }
        tempX = worldX;
        tempY = worldY;
        speed = 7;
        direction = "down";

        //Player Status
        level = 1;
        strength = 2; //Amount of damage done to monsters
        exp = 0;
        nextLevelExp = 5;
        maxLife = 6;
        life = maxLife;
    }

    public int getStrength(){
        return strength;
    }
    public void getPlayerImage(){
        up1 = setup("/res/player/boy_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/player/boy_up_2",gp.tileSize,gp.tileSize);
        up3 = setup("/res/player/boy_up_3",gp.tileSize,gp.tileSize);
        down1 = setup("/res/player/boy_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/res/player/boy_down_2",gp.tileSize,gp.tileSize);
        down3 = setup("/res/player/boy_down_3",gp.tileSize,gp.tileSize);
        left1 = setup("/res/player/boy_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/player/boy_left_2",gp.tileSize,gp.tileSize);
        left3 = setup("/res/player/boy_left_3",gp.tileSize,gp.tileSize);
        right1 = setup("/res/player/boy_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/player/boy_right_2",gp.tileSize,gp.tileSize);
        right3 = setup("/res/player/boy_right_3",gp.tileSize,gp.tileSize);        
    }

    public void getPlayerAttackImage(){
        attackUp1 = setup("/res/player/boy_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 = setup("/res/player/boy_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackUp3 = setup("/res/player/boy_attack_up_3",gp.tileSize,gp.tileSize*2);
        attackDown1 = setup("/res/player/boy_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setup("/res/player/boy_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackDown3 = setup("/res/player/boy_attack_down_3",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setup("/res/player/boy_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setup("/res/player/boy_attack_left_2",gp.tileSize*2,gp.tileSize);
        attackLeft3 = setup("/res/player/boy_attack_left_3",gp.tileSize*2,gp.tileSize);
        attackRight1 = setup("/res/player/boy_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight2 = setup("/res/player/boy_attack_right_2",gp.tileSize*2,gp.tileSize);
        attackRight3 = setup("/res/player/boy_attack_right_3",gp.tileSize*2,gp.tileSize); 

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
            } else { // Player
                // Check monster collision with the updated worldx, worldy, and solid area
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex);

                int monster2Index = gp.cChecker.checkEntity(this, gp.monster2);
                gp.player.damageMonster2(monster2Index);

                int monster3Index = gp.cChecker.checkEntity(this, gp.monster3);
                gp.player.damageMonster3(monster3Index);
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
            spriteNum = 3;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void setDefaultPositions(){
            worldX = gp.tileSize*94;
            worldY = gp.tileSize*164;
            System.out.println("big map position loaded!");
            direction = "down";

    }
    public void restoreLife(){
        life = maxLife;
        invincible = false;
    }

    

    public void update(){
        if(attacking == true){
            attacking();
        }

        else if(keyH.upPressed == true || keyH.downPressed == true || 
            keyH.leftPressed == true || keyH.rightPressed == true || keyH.quotePressed){       
                if(keyH.upPressed == true){
                    direction = "up";
                }
                else if(keyH.downPressed == true){
                    direction ="down";
                }
                else if(keyH.leftPressed == true){
                    direction = "left";
                }
                else if (keyH.rightPressed == true){
                    direction = "right";
                }
                
                //CHECK TILE COLLISION
                collisionOn = false;
                gp.cChecker.checkTile(this);

                //CHECK OBJECT COLLISION
              

                //check NPC collision
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                //check monster collision
                int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
                contactMonster(monsterIndex);

                //check monster2 collision
                int monsterIndex2 = gp.cChecker.checkEntity(this,gp.monster2);
                contactMonster2(monsterIndex2);

                int monsterIndex3 = gp.cChecker.checkEntity(this,gp.monster3);
                contactMonster3(monsterIndex3);

                //check event collision
                gp.eHandler.checkEvent();



                //IF COLLISION IS FALSE, PLAYER CAN MOVE
                if(collisionOn == false && keyH.enterPressed == false && keyH.quotePressed == false){
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

                if(keyH.quotePressed == true && attackCanceled == false){
                    gp.playSE(11);
                    attacking = true;
                    spriteCounter = 0;
                }

                attackCanceled = false;

                keyH.enterPressed = false;
                keyH.quotePressed = false;


                spriteCounter++;
                if(spriteCounter>5){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2){
                        spriteNum = 3;
                    }
                    else if(spriteNum == 3){
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
                if(keyH.godModeOn == false){
                    if(life<=0){
                        gp.stopMusic();
                        gp.gameState = gp.gameOverState;
                        gp.playSE(10);
                        gp.playMusic(14);
                    }
                }
                
            }
        //MUST BE OUTSIDE OF KEY IF STATEMENT!
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter> 60){
                 invincible = false;
                invincibleCounter = 0;
            }
        }

        
            

    }

    public void interactNPC(int i ){
        if(i!= 999){
            if(gp.keyH.enterPressed && gp.currentMap == 0){
                attackCanceled  = true;
                gp.npc[gp.currentMap][i].speak();
            }
            else if(gp.keyH.enterPressed && gp.currentMap == 2){
                attackCanceled  = true;
                gp.npc[gp.currentMap][i].speak();
            }
            else if(gp.keyH.enterPressed && gp.currentMap == 3){
                attackCanceled  = true;
                gp.npc[gp.currentMap][i].speak();
            }
            
        }
        

    }

    public void contactMonster(int i){

        if(i!=999){

            if(invincible == false && gp.monster[gp.currentMap][i].dying == false){
                gp.playSE(10);
                life-= gp.monster[gp.currentMap][i].strength;
                invincible = true;
            }
        }
    }

    public void contactMonster2(int i){

        if(i!=999){
            if(invincible == false && gp.monster2[gp.currentMap][i].dying == false){
                gp.playSE(10);
                life-= gp.monster2[gp.currentMap][i].strength;
                invincible = true;
            }
        }
    }

    public void contactMonster3(int i){

        if(i!=999){
            if(invincible == false && gp.monster3[gp.currentMap][i].dying == false){
                gp.playSE(10);
                life-= gp.monster3[gp.currentMap][i].strength;
                invincible = true;
            }
        }
    }
    
    public void damageMonster3(int i ){
        if(i != 999){
            if(gp.monster3[gp.currentMap][i] != null && gp.monster3[gp.currentMap][i].invincible == false){
                gp.playSE(9);
                gp.monster3[gp.currentMap][i].life -=strength;
                gp.monster3[gp.currentMap][i].invincible = true;
                gp.monster3[gp.currentMap][i].damageReaction();

                if(gp.monster3[gp.currentMap][i].life<=0){
                    gp.monster3[gp.currentMap][i].dying=true;
                    gp.ui.addMessage("EXP +" + gp.monster3[gp.currentMap][i].exp + "!");
                    exp+= gp.monster3[gp.currentMap][i].exp;
                    checkLevelUp();

                    
                }
            }
                      
        }
    }
    public void damageMonster2(int i ){
        if(i != 999){

            if(gp.monster2[gp.currentMap][i] != null && gp.monster2[gp.currentMap][i].invincible == false){
                gp.playSE(9);
                gp.monster2[gp.currentMap][i].life -=strength;
                gp.monster2[gp.currentMap][i].invincible = true;
                gp.monster2[gp.currentMap][i].damageReaction();

                if(gp.monster2[gp.currentMap][i].life<=0){
                    gp.monster2[gp.currentMap][i].dying=true;
                    gp.ui.addMessage("EXP +" + gp.monster2[gp.currentMap][i].exp + "!");
                    exp+= gp.monster2[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
            
        }
    }

    public void damageMonster(int i ){
        if(i != 999){
            if(gp.monster[gp.currentMap][i].invincible == false){
                gp.playSE(9);
                gp.monster[gp.currentMap][i].life -=strength;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life<=0){
                    gp.monster[gp.currentMap][i].dying=true;
                    gp.ui.addMessage("EXP +" + gp.monster[gp.currentMap][i].exp + "!");
                    exp+= gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                    if(gp.monster[gp.currentMap][i].equals(gp.monster[3][0])){
                        gp.gameState = gp.winState;
                        gp.stopMusic();
                        gp.playMusic(4);
                    }
                }
            }
                      
        }

        
        
            
        
        
    }

    public void checkLevelUp(){
        if (exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 4;
            strength+=2;

            gp.playSE(13);
            gp.ui.addMessage("Level up! You are now Level: "+level);

        
        }
    }

    

    

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x,y,gp.tileSize, gp.tileSize);

        BufferedImage image = null; 
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
                    }
                    if(spriteNum == 3){
                        image = up3;
                    }
                }
                if(attacking){
                    tempScreenY = screenY - gp.tileSize;
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
            if(attacking){
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
            if(attacking){
                tempScreenX = screenX - gp.tileSize;
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
            if(attacking){
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
        //Makes player sprite a litte transparent when invincible
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX,tempScreenY,null);

        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //Debug text
        //g2.setFont(new Font("Arial",Font.PLAIN,26));
        //g2.setColor(Color.white);
        //g2.drawString("invincible:" +invincibleCounter,10,400);
    }


}
