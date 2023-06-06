package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Key extends Entity{


    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/res/objects/key",gp.tileSize,gp.tileSize);

    }
}
