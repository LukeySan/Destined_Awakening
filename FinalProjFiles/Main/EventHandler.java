package Main;

import java.awt.Rectangle;

import Entity.Entity;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public boolean uiDialogue = false;
    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventMaster = new Entity(gp);



        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(map<gp.maxMap && col<gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }

            }

        }

        setDialogue();
    }
    public void setDialogue(){

            eventMaster.dialogues[0][0] = "You fall into a pit!";
            eventMaster.dialogues[1][0] = "The water is nice and warm...";
            eventMaster.dialogues[1][1] = "You recovered all your HP!";
            eventMaster.dialogues[1][1] = "All monsters have respawned!";
            

    }

    public void checkEvent(){
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance >gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent){
            /*if(hit(0,23,21,"up") == true){
                System.out.println("HIT");
                damagePit(gp.playDialogueState);
            }*/
            /*if(hit(0,21,7,"up") == true){healingPool(gp.playDialogueState);}
            if(hit(0,22,7,"up") == true){healingPool(gp.playDialogueState);}
            if(hit(0,23,7,"up") == true){healingPool(gp.playDialogueState);}
            if(hit(0,24,7,"up") == true){healingPool(gp.playDialogueState);}
            if(hit(0,25,7,"up") == true){healingPool(gp.playDialogueState);}*/
            if(hit(3,91,170,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,92,170,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,90,171,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,90,171,"right") == true){healingPool(gp.playDialogueState);}
            if(hit(3,93,171,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,94,171,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,95,172,"left") == true){healingPool(gp.playDialogueState);}
            if(hit(3,95,173,"left") == true){healingPool(gp.playDialogueState);}
            if(hit(3,95,174,"left") == true){healingPool(gp.playDialogueState);}
            if(hit(3,89,172,"right") == true){healingPool(gp.playDialogueState);}
            if(hit(3,89,173,"right") == true){healingPool(gp.playDialogueState);}


            if(hit(3,137,190,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,138,190,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,139,190,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,140,190,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,136,191,"down") == true){healingPool(gp.playDialogueState);}
            if(hit(3,143,192,"down") == true){healingPool(gp.playDialogueState);}




        }


    }

    public boolean hit(int map, int col, int row, String reqDirection){

        boolean hit = false;

        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX+gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY+gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
    
            if(gp.player.solidArea.intersects(eventRect[map][col][row])){
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
    
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }

        


        return hit;

    }

    public void damagePit (int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        eventMaster.startDialogue(eventMaster,0);
        gp.player.life -=1;
        canTouchEvent = false;
    }
    public void healingPool(int gameState){
        if(gp.keyH.enterPressed == true){
            
            gp.gameState = gameState;
            gp.player.attackCanceled= true;
            gp.playSE(12);
            eventMaster.startDialogue(eventMaster,1);
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();
            gp.aSetter.setMonster2();
            gp.aSetter.setMonster3();

        }
    }
}
