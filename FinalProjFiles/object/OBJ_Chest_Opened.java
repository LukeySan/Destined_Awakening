package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Chest_Opened extends SuperObject{
    GamePanel gp;
    public OBJ_Chest_Opened(GamePanel gp){
        name= "Chest_Opened";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest_opened.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }