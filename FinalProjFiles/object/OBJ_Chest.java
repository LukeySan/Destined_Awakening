package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Chest extends Entity{
    public OBJ_Chest(GamePanel gp){
        super(gp);
        name= "Chest";
        down1 = setup("/res/objects/chest");
        collision = true;

    }
}
    

