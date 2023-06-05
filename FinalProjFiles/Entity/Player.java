package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import object.OBJ_Chest_Opened;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;

    
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


        setDefaultValues();
        getPlayerImage();
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
        maxLife = 6;
        life = maxLife;
    }

    


    public void getPlayerImage(){
        up1 = setup("/res/player/boy_up_1");
        up2 = setup("/res/player/boy_up_2");
        up3 = setup("/res/player/boy_up_3");
        down1 = setup("/res/player/boy_down_1");
        down2 = setup("/res/player/boy_down_2");
        down3 = setup("/res/player/boy_down_3");
        left1 = setup("/res/player/boy_left_1");
        left2 = setup("/res/player/boy_left_2");
        left3 = setup("/res/player/boy_left_3");
        right1 = setup("/res/player/boy_right_1");
        right2 = setup("/res/player/boy_right_2");
        right3 = setup("/res/player/boy_right_3");        
    }

    

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || 
            keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){       
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
                int objIndex = gp.cChecker.checkObject(this,true);
                pickUpObject(objIndex);

                //check NPC collision
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                //check event collision
                gp.eHandler.checkEvent();



                //IF COLLISION IS FALSE, PLAYER CAN MOVE
                if(collisionOn == false && keyH.enterPressed == false){
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

                keyH.enterPressed = false;


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
            }
        
            

    }

    public void interactNPC(int i ){
        if(i!= 999){
            if(gp.keyH.enterPressed && gp.currentMap == 0){
                gp.npc[gp.currentMap][i].speak();
            }
            else if(gp.keyH.enterPressed && gp.currentMap == 2){
                gp.npc[gp.currentMap][i].speak();
            }
            else if(gp.keyH.enterPressed && gp.currentMap == 3){
                gp.npc[gp.currentMap][i].speak();
            }
            
        }

    }


    public void pickUpObject(int i){
        if(i!= 999){
            String objectName = gp.obj[gp.currentMap][i].name;

            switch(objectName){
            case "Key":
             gp.playSE(1);
            hasKey++;
            gp.obj[gp.currentMap][i] =null;
            gp.ui.showMessage("You got a key!");
                break;
                
            case"Door":
                if(hasKey> 0){
                    gp.playSE(3);
                    gp.obj[gp.currentMap][i] = null;
                    hasKey--;
                    gp.ui.showMessage("You opened the door!");
                }
                else{
                    gp.ui.showMessage("You need a key!");
                }
                System.out.println("Keys:"+hasKey);
                break;

            case "Boots":
                gp.playSE(2);
                speed+=4;
                gp.obj[gp.currentMap][i] = null;
                gp.ui.showMessage("Speed up!");

                break;

            case "Chest":;
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(4);
                int tempX = gp.obj[gp.currentMap][i].worldX;
                int tempY = gp.obj[gp.currentMap][i].worldY;
                gp.obj[gp.currentMap][i] = new OBJ_Chest_Opened(gp); 
                gp.obj[gp.currentMap][i].worldX = tempX;
                gp.obj[gp.currentMap][i].worldY = tempY;
                gp.ui.showMessage("Chest opened");

                break;
           

            }
        }

    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x,y,gp.tileSize, gp.tileSize);

        BufferedImage image = null; 

        switch(direction){
            case"up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up3;
                }
                break;
            case"down":
                if(spriteNum==1){
                    image = down1;

                }
                if(spriteNum==2){
                    image = down2;

                }
                if(spriteNum == 3){
                    image = down3;
                }
                break;
            case "left":
            if(spriteNum==1){
                image = left1;

            }
            if(spriteNum==2){
                image = left2;

            }
            if(spriteNum == 3){
                image = left3;
            }
                break;
            case "right":
            if(spriteNum==1){
                image = right1;

            }
            if(spriteNum==2){
                image = right2;

            }
            if(spriteNum==3){
                image = right3;

            }
                break;
        }
        g2.drawImage(image, screenX,screenY,null);
    }


}
