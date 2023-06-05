package Main;

import Entity.NPC_OldMan;
import Entity.NPC_TutOldMan;
import Monster.MON_chuchu;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;



public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter (GamePanel gp){
        this.gp = gp;
    }


    public void setObject(){

        int mapNum = 0;
        int i =0;


            //TEST MAIN MAP OBJECT PLACEMENT
            gp.obj[mapNum][i] = new OBJ_Key(gp);
            gp.obj[mapNum][i].worldX= 23 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 7 * gp.tileSize;

            i++;

            gp.obj[mapNum][i] = new OBJ_Key(gp);
            gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 40*gp.tileSize;
            i++;

            gp.obj[mapNum][i] = new OBJ_Key(gp);
            gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 7  *gp.tileSize;
            i++;

            gp.obj[mapNum][i] = new OBJ_Door(gp);
            gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 11*gp.tileSize;
            i++;

            gp.obj[mapNum][i] = new OBJ_Door(gp);
            gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 28 *gp.tileSize;
            i++;

            gp.obj[mapNum][i] = new OBJ_Door(gp);
            gp.obj[mapNum][i].worldX = 12  * gp.tileSize;
            gp.obj[mapNum][i].worldY = 22 *gp.tileSize;
            i++;

            gp.obj[mapNum][i] = new OBJ_Chest(gp);
            gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 7*gp.tileSize;
            i++;

            gp.obj[mapNum][i] = new OBJ_Boots(gp);
            gp.obj[mapNum][i].worldX = 37 * gp.tileSize; //COLUMN THE NUMBER HERE  IS ONE LESS THANT HE LINE OF TEXT ON THE MAP
            gp.obj[mapNum][i].worldY = 42*gp.tileSize; 
            i++;

            //TUTORIAL MAP OBJECT PLACEMENT
            mapNum = 2;
            gp.obj[mapNum][0] = new OBJ_Door(gp);
            gp.obj[mapNum][0].worldX = 25*gp.tileSize; //COLUMN THE NUMBER HERE  IS ONE LESS THANT HE LINE OF TEXT ON THE MAP
            gp.obj[mapNum][0].worldY = 4*gp.tileSize;
            i++;

            //REAL MAIN MAP OBJECT PLACEMENT
            mapNum = 3;




        }

    public void setNPC(){
        int mapNum = 0;
        int i = 0;
        //World01


        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*21;
        i++;
        
        mapNum =1;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*21;
        i++;
        //Interior map


        mapNum=2;
        //Tutorial Map
        gp.npc[mapNum][i] = new NPC_TutOldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*26;
        gp.npc[mapNum][i].worldY = gp.tileSize*26;
        i++;

        //REAL MAIN MAP OBJECT PLACEMENT
        mapNum = 3;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*88;
        gp.npc[mapNum][i].worldY = gp.tileSize*160;
        i++;


    }
    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        mapNum = 1;

        mapNum = 2;

        mapNum = 3;
        gp.monster[mapNum][i] = new MON_chuchu(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*90;
        gp.monster[mapNum][i].worldX = gp.tileSize*162;
        i++;

    }
        


}

