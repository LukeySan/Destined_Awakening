package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, earthbound,title;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    private long lastFrameTime;
    public int commandNum = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){

        this.gp = gp;
        try{
        InputStream is = getClass().getResourceAsStream("/res/fonts/earthbound.ttf");
        earthbound = Font.createFont(Font.TRUETYPE_FONT,is );
        is = getClass().getResourceAsStream("/res/fonts/Title.ttf");
        title = Font.createFont(Font.TRUETYPE_FONT,is);
        }catch(FontFormatException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }



        arial_80B = new Font("Arial", Font.BOLD, 80);//Name of font, type(bold or plain), size of the font

        arial_40 = new Font("Arial", Font.PLAIN, 40);//Name of font, type(bold or plain), size of the font
        
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        lastFrameTime = System.nanoTime();
        
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    //Don't instantiate the class in the draw method, always do it in the constructor.
    //Doing it in the draw method conusmes time and resources as it is instatiatied 60 times every second.
    public void draw(Graphics2D g2){

        this.g2 = g2;

        //Title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen(g2);
        }

        else{
        if(gameFinished){
            
            g2.setFont(earthbound.deriveFont(70F));
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
             
             g2.setFont(earthbound.deriveFont(125F));
             g2.setColor(Color.yellow);
             text = "Congratulations!";
             textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); //returns length of text
              x = gp.screenWidth/2-textLength/2;//aligns text in the center
              y = gp.screenHeight/2 + (gp.tileSize*2)-100;
              g2.drawString(text, x,y);

              gp.gameThread = null;//STOPS THE THREAD/STOPS GAME
 
        }

        else {
            g2.setFont(earthbound.deriveFont(55F));
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage,gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+gp.player.hasKey,74,65);
            /*The Y value of the drawString actually indicates the bottom of the text, rather than the top like most objects. */
            
            //TIME
            //playTime += (double)1/60;
            long currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastFrameTime) / 1_000_000_000.0; // Convert to seconds
            lastFrameTime = currentTime;
            playTime += elapsedTime;
            g2.drawString("Time:"+dFormat.format(playTime),gp.tileSize*14,65);

    
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

    public void drawTitleScreen(Graphics2D g2){
        
        g2.setColor(new Color(57,110,173));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        
        //Title name
        g2.setFont(title.deriveFont(Font.BOLD,96F));
        String text = "TITLE SCREEN";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        //Shadow
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);

        //Main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //Menu Buttons
        g2.setFont(earthbound.deriveFont(Font.BOLD,48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x-gp.tileSize*1,y);
        }
        
        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">",x-gp.tileSize*1,y);
        }
        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">",x-gp.tileSize*1,y);
        }
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2-length/2;
        return x;
    }
}
