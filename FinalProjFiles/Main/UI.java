package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, earthbound,title;
    BufferedImage keyImage, heart_full , heart_half , heart_blank;
    public boolean messageOn = false;
    //public String message = "";
    //int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    private long lastFrameTime;
    public int commandNum = 0;
    public int tutorialPauseCommandNum = 0;
    public int playPauseCommandNum = 0;
    public String currentDialogue = "";
    public Entity npc;
    


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


        //GUI OBJECTS
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;



        arial_80B = new Font("Arial", Font.BOLD, 80);//Name of font, type(bold or plain), size of the font

        arial_40 = new Font("Arial", Font.PLAIN, 40);//Name of font, type(bold or plain), size of the font
        
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        lastFrameTime = System.nanoTime();
        
    }

    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    //Don't instantiate the class in the draw method, always do it in the constructor.
    //Doing it in the draw method conusmes time and resources as it is instatiatied 60 times every second.
    public void draw(Graphics2D g2){

        this.g2 = g2;
      

        //Title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen(g2);
        }

        else if(gp.gameState == gp.playState ||gp.gameState == gp.tutorialState ){
            if(gp.keyH.checkDrawTime == true){
                String text;
                int x, y;

                g2.setFont(earthbound.deriveFont(Font.BOLD,20F));
                g2.setColor(Color.white);
                

                //text = gp.player.worldX;
                x = gp.tileSize*4;
                y = gp.tileSize*10;
                g2.drawString("World X: " + gp.player.tempX,x,y);                
        
            }
        if(gameFinished){
            
            /*g2.setFont(earthbound.deriveFont(70F));
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

              gp.gameThread = null;//STOPS THE THREAD/STOPS GAME*/
 
        }

        else{
            g2.setFont(earthbound.deriveFont(55F));
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage,gp.tileSize/2, gp.tileSize*10,gp.tileSize,gp.tileSize,null);
            //g2.drawString("x "+gp.player.hasKey,74,530);
            /*The Y value of the drawString actually indicates the bottom of the text, rather than the top like most objects. */
            
            drawPlayerLife();
            drawMessage();

            //TIME
            //playTime += (double)1/60;
            long currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastFrameTime) / 1_000_000_000.0; // Convert to seconds
            lastFrameTime = currentTime;
            playTime += elapsedTime;
            //g2.drawString("Time:"+dFormat.format(playTime),gp.tileSize*14,65);

    
            //MESSAGE
            
            g2.setFont(earthbound.deriveFont(Font.BOLD,20F));
            String text = "";
            int x;
            int y;
        if(gp.gameState == gp.tutorialState){
            g2.setFont(earthbound.deriveFont(Font.BOLD,20F));
        
        text = "CONTROLS:";
        x = gp.tileSize*2;
        y = gp.tileSize;
        g2.drawString(text,x,y);

        text = "    W: UP";
        x = gp.tileSize*2;
        y = gp.tileSize+30;
        g2.drawString(text,x,y);


        text = "    A: LEFT";
        x = gp.tileSize*2;
        y = gp.tileSize+60;
        g2.drawString(text,x,y);

        text = "    S: DOWN";
        x = gp.tileSize*2;
        y = gp.tileSize+90;
        g2.drawString(text,x,y);

        text = "    D: RIGHT";
        x = gp.tileSize*2;
        y = gp.tileSize+120;
        g2.drawString(text,x,y);

        text = "       Enter: SELECT/ACTION";
        x = gp.tileSize;
        y = gp.tileSize+150;
        g2.drawString(text,x,y);
        }
    }
    }
        else if(gp.gameState == gp.playPauseState || gp.gameState == gp.tutorialPauseState ){
            drawPlayerLife();

            drawPauseScreen(g2);

        }
        else if(gp.gameState  == gp.tutorialPauseState ){
            drawPlayerLife();

            drawPauseScreen(g2);

        }
        if(gp.gameState == gp.playDialogueState){
            drawPlayerLife();

            drawDialogueScreen();

        }


        if(gp.gameState == gp.tutorialDialogueState){
            drawPlayerLife();

            drawDialogueScreen();

        }

        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }

        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        
    }

    public void drawGameOverScreen(){

            g2.setColor(new Color(0,0,0,150));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

            int x;
            int y;
            String text;
            g2.setFont(earthbound.deriveFont(Font.BOLD,110F));

            text = "Game Over";

            g2.setColor(Color.black);
            //Shadow Text
            x = getXforCenteredText(text);
            y = gp.tileSize*4;
            g2.drawString(text,x,y);
            //Main text
            g2.setColor(Color.WHITE);
            g2.drawString(text,x-4,y-4);

            //Retry
            g2.setFont(earthbound.deriveFont(Font.BOLD,50F));
            text = "Retry";
            x = getXforCenteredText(text);
            y+= gp.tileSize*4;
            g2.drawString(text,x,y);
            if(commandNum == 0){
                g2.drawString(">",x-40,y);
            }

            //Back to Main menu
            text = "Quit";
            x = getXforCenteredText(text);
            y+=gp.tileSize*2;
            g2.drawString(text,x,y);
            if(commandNum == 1){
                g2.drawString(">",x-40,y);
            }



    }
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(earthbound.deriveFont(Font.BOLD,32F));

        for(int i = 0; i<message.size(); i++ ){

            if(message.get(i) != null){
                g2.setColor(Color.BLACK);//shadow text
                g2.drawString(message.get(i),messageX+2,messageY+2);//shadow text
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i),messageX,messageY);
            }

            int counter = messageCounter.get(i) + 1; //messageCounter++
            messageCounter.set(i, counter); //set the countter to the array
            messageY += 50;

            if(messageCounter.get(i) >180) {
                message.remove(i);
                messageCounter.remove(i);
            }


        }

    }

    public void drawPlayerLife(){


        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //DRAW MAX LIFE
        while(i<gp.player.maxLife/2){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+= gp.tileSize;

        }

        //RESET
         x = gp.tileSize/2;
         y = gp.tileSize/2;
         i = 0;

         //DRAW CURRENT LIFE
         while (i<gp.player.life){
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i<gp.player.life){
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x+= gp.tileSize;
         }

    }

    public void drawDialogueScreen(){

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x,y,width,height);

        g2.setFont(earthbound.deriveFont(Font.PLAIN,35F));
        x+= gp.tileSize;
        y+=gp.tileSize;
        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex]!= null){

            currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            
            


            if(gp.keyH.enterPressed == true){
                if(gp.gameState == gp.tutorialDialogueState){
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
                if(gp.gameState == gp.playDialogueState){
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }

        }
        else{
            
            npc.dialogueIndex = 0;
            npc.dialogueSet++;


            if(gp.gameState == gp.tutorialDialogueState){
                gp.gameState = gp.tutorialState;
            }
            else if (gp.gameState == gp.playDialogueState){
                gp.gameState = gp.playState;
            }

        }

 

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+= 40;
        }
        
    }
    public void drawCharacterScreen(){
        //Create a fram
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize *5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //text
        g2.setColor(Color.white);
        g2.setFont(earthbound.deriveFont(Font.PLAIN,35F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 50;

        //Names
        g2.drawString("Level", textX, textY);
        textY+= lineHeight;
        g2.drawString("EXP",textX,textY);
        textY+= lineHeight;
        g2.drawString("Next Level",textX,textY);
        textY+= lineHeight;
        g2.drawString("HP",textX,textY);
        textY+= lineHeight;
        g2.drawString("Strength",textX,textY);
        textY+= lineHeight;

        //Values
        int tailX = (frameX + frameWidth) - 30;
        //Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+= lineHeight;

       value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.life) + "/" + String.valueOf(gp.player.maxLife) ;
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+= lineHeight;

        

    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c= new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);


    }


    public void drawPauseScreen(Graphics2D g2){
        g2.setFont(earthbound.deriveFont(Font.BOLD,130F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        

        g2.setFont(earthbound.deriveFont(Font.BOLD,40F));
            
            //Setup Resume
            text = "RESUME";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text,x,y);
            if(gp.gameState == gp.playPauseState && playPauseCommandNum == 0){
                g2.drawString(">",x-gp.tileSize*1,y);
            }
            if(gp.gameState == gp.tutorialPauseState && tutorialPauseCommandNum == 0){
                g2.drawString(">",x-gp.tileSize*1,y);
            }

        
            text = "MAIN MENU";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text,x,y);
            if(gp.gameState == gp.playPauseState && playPauseCommandNum == 1){
                g2.drawString(">",x-gp.tileSize*1,y);
            }
            if(gp.gameState == gp.tutorialPauseState && tutorialPauseCommandNum == 1){
                g2.drawString(">",x-gp.tileSize*1,y);
            }


            
        
             g2.setFont(earthbound.deriveFont(Font.BOLD,20F));

            text = "CONTROLS:";
            x = gp.tileSize*2;
            y = gp.tileSize;
            g2.drawString(text,x,y);
    
            text = "    W: UP";
            x = gp.tileSize*2;
            y = gp.tileSize+30;
            g2.drawString(text,x,y);
    
    
            text = "    A: LEFT";
            x = gp.tileSize*2;
            y = gp.tileSize+60;
            g2.drawString(text,x,y);
    
            text = "    S: DOWN";
            x = gp.tileSize*2;
            y = gp.tileSize+90;
            g2.drawString(text,x,y);
    
            text = "    D: RIGHT";
            x = gp.tileSize*2;
            y = gp.tileSize+120;
            g2.drawString(text,x,y);
    
            text = "    C: CHARACTER PAGE";
            x = gp.tileSize*2;
            y = gp.tileSize+150;
            g2.drawString(text,x,y);
    
            text = "    P: PAUSE";
            x = gp.tileSize*2;
            y = gp.tileSize+180;
            g2.drawString(text,x,y);
    
            text = "       Enter: SELECT/ACTION";
            x = 15;
            y = gp.tileSize+210;
            g2.drawString(text,x,y);

        

        

    }

    public void drawTitleScreen(Graphics2D g2){
        
        //BLUE BACKGROUND
        g2.setColor(new Color(57,110,173));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        
        //Title name
        g2.setFont(title.deriveFont(Font.BOLD,96F));
        String text = "DESTINED AWAKENING";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        //Shadow
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);

        //Main color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        g2.setFont(earthbound.deriveFont(Font.BOLD,20F));
        
        text = "CONTROLS:";
        x = gp.tileSize*2;
        y = gp.tileSize;
        g2.drawString(text,x,y);

        text = "    W: UP";
        x = gp.tileSize*2;
        y = gp.tileSize+30;
        g2.drawString(text,x,y);


        text = "    A: LEFT";
        x = gp.tileSize*2;
        y = gp.tileSize+60;
        g2.drawString(text,x,y);

        text = "    S: DOWN";
        x = gp.tileSize*2;
        y = gp.tileSize+90;
        g2.drawString(text,x,y);

        text = "    D: RIGHT";
        x = gp.tileSize*2;
        y = gp.tileSize+120;
        g2.drawString(text,x,y);

        text = "    C: CHARACTER PAGE";
        x = gp.tileSize*2;
        y = gp.tileSize+150;
        g2.drawString(text,x,y);

        text = "    P: PAUSE";
        x = gp.tileSize*2;
        y = gp.tileSize+180;
        g2.drawString(text,x,y);

        text = "       Enter: SELECT/ACTION";
        x = gp.tileSize;
        y = gp.tileSize+210;
        g2.drawString(text,x,y);


     
        //Menu Buttons
        g2.setFont(earthbound.deriveFont(Font.BOLD,48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x-gp.tileSize*1,y);
        }      

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">",x-gp.tileSize*1,y);
        }
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2-length/2;
        return x;
    }
    
    public int getXforAlignToRightText(String text,int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
