package Main;

import Entity.NPC_OldMan;
import Entity.NPC_TutOldMan;
import Monster.MON_chuchu;
import Monster.MON_darkknight;
import Monster.MON_orc;
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
            /*gp.obj[mapNum][i] = new OBJ_Key(gp);
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
            i++;*/

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
        gp.npc[mapNum][0] = new NPC_OldMan(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize*88;
        gp.npc[mapNum][0].worldY = gp.tileSize*160;
        i++;
        


    }
    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        mapNum = 1;

        mapNum = 2;

        mapNum = 3;
        gp.monster[3][0] = new MON_darkknight(gp);
        gp.monster[3][0].worldX = gp.tileSize*142;//col
        gp.monster[3][0].worldY = gp.tileSize*92;//row
        i++;

        gp.monster[3][1] = new MON_chuchu(gp);
        gp.monster[3][1].worldX = gp.tileSize*120;//col
        gp.monster[3][1].worldY = gp.tileSize*162;//row
        i++;

        gp.monster[3][2] = new MON_chuchu(gp);
        gp.monster[3][2].worldX = gp.tileSize*114;//col
        gp.monster[3][2].worldY = gp.tileSize*166;//row
        i++;

        gp.monster[3][3] = new MON_chuchu(gp);
        gp.monster[3][3].worldX = gp.tileSize*119;//col
        gp.monster[3][3].worldY = gp.tileSize*179;//row
        i++;

        gp.monster[3][4] = new MON_chuchu(gp);
        gp.monster[3][4].worldX = gp.tileSize*129;//col
        gp.monster[3][4].worldY = gp.tileSize*175;//row
        i++;

        gp.monster[3][5] = new MON_chuchu(gp);
        gp.monster[3][5].worldX = gp.tileSize*133;//col
        gp.monster[3][5].worldY = gp.tileSize*179;//row
        i++;

        gp.monster[3][6] = new MON_chuchu(gp);
        gp.monster[3][6].worldX = gp.tileSize*146;//col
        gp.monster[3][6].worldY = gp.tileSize*187;//row
        i++;

        gp.monster[3][7] = new MON_chuchu(gp);
        gp.monster[3][7].worldX = gp.tileSize*162;//col
        gp.monster[3][7].worldY = gp.tileSize*183;//row
        i++;

        gp.monster[3][8] = new MON_chuchu(gp);
        gp.monster[3][8].worldX = gp.tileSize*145;//col
        gp.monster[3][8].worldY = gp.tileSize*96;//row

        gp.monster[3][9] = new MON_chuchu(gp);
        gp.monster[3][9].worldX = gp.tileSize*138;//col
        gp.monster[3][9].worldY = gp.tileSize*182;//row
        i++;

       
    }
    public void setMonster2(){
        gp.monster2[3][0] = new MON_chuchu(gp);
        gp.monster2[3][0].worldX = gp.tileSize*170;//col
        gp.monster2[3][0].worldY = gp.tileSize*182;//row

        gp.monster2[3][1] = new MON_chuchu(gp);
        gp.monster2[3][1].worldX = gp.tileSize*183;//col
        gp.monster2[3][1].worldY = gp.tileSize*188;//row

        gp.monster2[3][2] = new MON_chuchu(gp);
        gp.monster2[3][2].worldX = gp.tileSize*207;//col
        gp.monster2[3][2].worldY = gp.tileSize*177;//row

        gp.monster2[3][3] = new MON_chuchu(gp);
        gp.monster2[3][3].worldX = gp.tileSize*213;//col
        gp.monster2[3][3].worldY = gp.tileSize*185;//row

        gp.monster2[3][4] = new MON_orc(gp);
        gp.monster2[3][4].worldX = gp.tileSize*207;//col
        gp.monster2[3][4].worldY = gp.tileSize*190;//row

        gp.monster2[3][5] = new MON_orc(gp);
        gp.monster2[3][5].worldX = gp.tileSize*83;//col
        gp.monster2[3][5].worldY = gp.tileSize*140;//row

        gp.monster2[3][6] = new MON_orc(gp);
        gp.monster2[3][6].worldX = gp.tileSize*91;//col
        gp.monster2[3][6].worldY = gp.tileSize*130;//row

        gp.monster2[3][7] = new MON_orc(gp);
        gp.monster2[3][7].worldX = gp.tileSize*119;//col
        gp.monster2[3][7].worldY = gp.tileSize*112;//row

        gp.monster2[3][8] = new MON_orc(gp);
        gp.monster2[3][8].worldX = gp.tileSize*143;//col
        gp.monster2[3][8].worldY = gp.tileSize*83;//row

        gp.monster2[3][8] = new MON_orc(gp);
        gp.monster2[3][8].worldX = gp.tileSize*121;//col
        gp.monster2[3][8].worldY = gp.tileSize*91;//row

        gp.monster2[3][9] = new MON_orc(gp);
        gp.monster2[3][9].worldX = gp.tileSize*133;//col
        gp.monster2[3][9].worldY = gp.tileSize*105;//row

    }
    public void setMonster3(){
        gp.monster2[3][0] = new MON_chuchu(gp);
        gp.monster2[3][0].worldX = gp.tileSize*137;//col
        gp.monster2[3][0].worldY = gp.tileSize*157;//row

        gp.monster2[3][1] = new MON_orc(gp);
        gp.monster2[3][1].worldX = gp.tileSize*154;//col
        gp.monster2[3][1].worldY = gp.tileSize*164;//row

        gp.monster2[3][2] = new MON_orc(gp);
        gp.monster2[3][2].worldX = gp.tileSize*129;//col
        gp.monster2[3][2].worldY = gp.tileSize*182;//row

        gp.monster2[3][3] = new MON_chuchu(gp);
        gp.monster2[3][3].worldX = gp.tileSize*205;//col
        gp.monster2[3][3].worldY = gp.tileSize*169;//row

        gp.monster2[3][4] = new MON_chuchu(gp);
        gp.monster2[3][4].worldX = gp.tileSize*204;//col
        gp.monster2[3][4].worldY = gp.tileSize*195;//row

        gp.monster2[3][5] = new MON_chuchu(gp);
        gp.monster2[3][5].worldX = gp.tileSize*208;//col
        gp.monster2[3][5].worldY = gp.tileSize*183;//row

        gp.monster2[3][6] = new MON_orc(gp);
        gp.monster2[3][6].worldX = gp.tileSize*85;//col
        gp.monster2[3][6].worldY = gp.tileSize*146;//row

        gp.monster2[3][7] = new MON_orc(gp);
        gp.monster2[3][7].worldX = gp.tileSize*85;//col
        gp.monster2[3][7].worldY = gp.tileSize*146;//row

        gp.monster2[3][8] = new MON_orc(gp);
        gp.monster2[3][8].worldX = gp.tileSize*140;//col
        gp.monster2[3][8].worldY = gp.tileSize*116;//row

        gp.monster2[3][9] = new MON_orc(gp);
        gp.monster2[3][9].worldX = gp.tileSize*204;//col
        gp.monster2[3][9].worldY = gp.tileSize*187;//row

        

    }
        


}

