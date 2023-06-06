package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Chest_Opened extends Entity{
    public OBJ_Chest_Opened(GamePanel gp){
        super(gp);
        name= "Chest_Opened";
        down1 = setup("/res/objects/chest_opened",gp.tileSize,gp.tileSize);
        collision = true;
    }
}