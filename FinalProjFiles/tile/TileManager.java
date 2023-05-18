package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt",0);
        loadMap(("/res/maps/interior01.txt"), 1);
    }

    public void getTileImage(){

        System.out.println("Image loading started");
        setUp(0,"grass",false);
        setUp(1,"wall",true);
        setUp(2,"water",true);
        setUp(3,"earth",false);
        setUp(4,"tree",true);
        setUp(5,"sand",false);
        setUp(6, "hut", false);
        setUp(7, "floor01", false);
        setUp(8, "table01", true);
        setUp(9, "door", true);
        setUp(10, "grass", true);
        setUp(11, "wall", true);
        setUp(12, "water", true);
        setUp(13, "earth", true);
        setUp(14, "tree", true);
        setUp(15, "sand", true);
        setUp(16, "hut", true);
        setUp(17, "floor01", true);
        setUp(18, "table01", true);
        setUp(19, "door", true);
        setUp(20, "door", true);


        System.out.println("Image loading finished");

    }

    public void setUp(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" +imageName+ ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].collision = collision;

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String x, int map){
        try {
            InputStream is = getClass().getResourceAsStream(x);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void draw(Graphics2D g2 ){

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol< gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize >gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize <gp.player.worldX + gp.player.screenX &&
               worldY+ gp.tileSize >gp.player.worldY - gp.player.screenY &&
               worldY- gp.tileSize <gp.player.worldY + gp.player.screenY){
                    g2.drawImage(tile[tileNum].image,screenX,screenY,null);
                     
                    }

            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }

        }



        //g2.drawImage(tile[0].image,0,0, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[1].image,48,0, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[2].image,96,0, gp.tileSize, gp.tileSize, null);


    }
}
