package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_80B = new Font("Arial", Font.BOLD, 80);//Name of font, type(bold or plain), size of the font

        arial_40 = new Font("Arial", Font.PLAIN, 40);//Name of font, type(bold or plain), size of the font
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    //Don't instantiate the class in the draw method, always do it in the constructor.
    //Doing it in the draw method conusmes time and resources as it is instatiatied 60 times every second.
    public void draw(Graphics2D g2){
        if(gameFinished){
            
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); //returns length of text

             x = gp.screenWidth/2-textLength/2;//aligns text in the center
             y = gp.screenHeight/2 - (gp.tileSize*3);
             g2.drawString(text, x,y);


            text = "Your Time is:"+dFormat.format(playTime) + "!";
             textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); //returns length of text
             x = gp.screenWidth/2-textLength/2;//aligns text in the center
             y = gp.screenHeight/2 + (gp.tileSize*3);
             g2.drawString(text, x,y);
             
             g2.setFont(arial_80B);
             g2.setColor(Color.yellow);
             text = "Congratulations!";
             textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); //returns length of text
              x = gp.screenWidth/2-textLength/2;//aligns text in the center
              y = gp.screenHeight/2 + (gp.tileSize*2);
              g2.drawString(text, x,y);

              gp.gameThread = null;//STOPS THE THREAD/STOPS GAME
 
        }

        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage,gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+gp.player.hasKey,74,65);
            /*The Y value of the drawString actually indicates the bottom of the text, rather than the top like most objects. */
            
            //TIME
            playTime += (double)1/60;
            g2.drawString("Time:"+dFormat.format(playTime),gp.tileSize*11,65);

    
            //MESSAGE
            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,gp.tileSize/2,gp.tileSize*5);
                
                messageCounter++;
                //shows for 2 seconds bc counter increases by 60 since fps is 60
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
    
                }
            }
        }
        
    }
}
