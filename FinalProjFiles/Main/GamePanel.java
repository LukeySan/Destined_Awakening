package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Main.KeyHandler;
import object.SuperObject;
import tile.TileManager;
import Entity.Entity;
import Entity.Player;

public class GamePanel extends JPanel implements Runnable{
     final int originalTitleSize = 16; //16x16 tile
     final int scale = 3;

     public final int tileSize = originalTitleSize * scale;//48*48 tile
     public final int maxScreenCol = 20; //16
     public final int maxScreenRow = 14; //12
     public final int screenWidth = tileSize * maxScreenCol; //960 pixels   //768 pixels
     public final int screenHeight = tileSize * maxScreenRow;         //576 pixels

     //WORLD SETTINGS
     public final int maxWorldCol = 50;//50
     public final int maxWorldRow= 50;//50
     public final int worldWidth = tileSize * maxWorldCol;
     public final int worldHeight = tileSize * maxWorldRow;
     public final int maxMap = 10;
     public static int currentMap;

     //GAME STATE
     public static int gameState;
     public static int titleState = 0;
     public static int playState = 1;
     public static int tutorialState = 2;
     public static int playPauseState = 3;
     public static int tutorialPauseState = 4;
     public final int playDialogueState =  5;
     public final int tutorialDialogueState = 6;


     //FOR FULL SCREEN
     int screenWidth2 = screenWidth;
     int screenHeight2 = screenHeight;
     BufferedImage tempScreen;
     Graphics2D g2;


     //FPS
     int FPS =  60;

    //SYSTEM
     TileManager tileM = new TileManager(this);
     public KeyHandler keyH = new KeyHandler(this);
     Sound se = new Sound();
     Sound music = new Sound();
     public CollisionChecker cChecker =  new CollisionChecker(this);
     public AssetSetter aSetter = new AssetSetter(this);
     public UI ui = new UI(this);
     Thread gameThread; 
     int count = 0;


     //ENTITY AND OBJEJCTS
     public Player player = new Player(this,keyH);
     public SuperObject obj[][] = new SuperObject[maxMap][10];
     public Entity npc[][] = new Entity[maxMap][10];


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(5);
        gameState = titleState;

       tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB); //Buffered image as large as the game window
        g2 = (Graphics2D)tempScreen.getGraphics();

        //setFullScreen();
    }

    public void setFullScreen(){

        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(main.window);

        //GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = main.window.getWidth();
        screenHeight2 = main.window.getHeight();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); 

    }
    public void paintComponent(Graphics g){
        

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

    //DEBUG
    long  drawStart = 0;
    if(keyH.checkDrawTime){
    drawStart = System.nanoTime();

    }
    //TITLE SCREEN
    if(gameState == titleState){
        ui.draw(g2);  
    }
    else if(gameState == tutorialState || gameState == tutorialPauseState){
        currentMap = 2;
        tileM.draw(g2);
        for(int i = 0; i <obj[1].length; i++){
            if (obj[currentMap][i] != null){
                obj[currentMap][i].draw(g2, this);
                }
        }
         if(count!= 1){
            player.worldX = tileSize*25;  
            player.worldY = tileSize*25;
            count++;
            System.out.println(count);
         }
        player.draw(g2);
        for(int i = 0; i<npc.length; i++){
            if(npc[currentMap][i]!= null){
                npc[currentMap][i].draw(g2);
                
            }
        }

        

        ui.draw(g2);

        if (g2 != null) {
            g2.dispose();
        }


    }
    
    
    //OTHERS
    else if (gameState == playState || gameState == playPauseState){
            
            currentMap = 0;
            
            
         //TILE
            tileM.draw(g2); 
         //OBJECT
             for(int i = 0; i <obj[1].length; i++){
                if (obj[currentMap][i] != null){
                 obj[currentMap][i].draw(g2, this);
                }
    }
        //PLAYER
        player.draw(g2);
        for(int i = 0; i<npc.length; i++){
            if(npc[currentMap][i]!= null){
                npc[currentMap][i].draw(g2);
                
            }
        }
    
        //UI
        ui.draw(g2);

        

        


        if (g2 != null) {
            g2.dispose();
        }
    }

    else if(gameState == tutorialDialogueState ){
        currentMap = 2;
            
            
         //TILE
            tileM.draw(g2); 
         //OBJECT
             for(int i = 0; i <obj[1].length; i++){
                if (obj[currentMap][i] != null){
                 obj[currentMap][i].draw(g2, this);
                }
    }
        //PLAYER
        player.draw(g2);
        for(int i = 0; i<npc.length; i++){
            if(npc[currentMap][i]!= null){
                npc[currentMap][i].draw(g2);
            }
        }
    
        //UI
        ui.draw(g2);

        

        


        if (g2 != null) {
            g2.dispose();
        }
    }
    else if(gameState == playDialogueState ){
        currentMap = 0;
            
            
         //TILE
            tileM.draw(g2); 
         //OBJECT
             for(int i = 0; i <obj[1].length; i++){
                if (obj[currentMap][i] != null){
                 obj[currentMap][i].draw(g2, this);
                }
    }
        //PLAYER
        player.draw(g2);
        for(int i = 0; i<npc.length; i++){
            if(npc[currentMap][i]!= null){
                npc[currentMap][i].draw(g2);
            }
        }
    
        //UI
        ui.draw(g2);

        

        


        if (g2 != null) {
            g2.dispose();
        }
    }

    //DEBUG
        if(keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);

        }
        
    }
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread!= null){

            currentTime = System.nanoTime();

            delta+= (currentTime - lastTime) / drawInterval;
            timer+= (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>= 1){
                update();
                repaint(); //For when running on mac
                paintComponent(g2); //For when running on mac
                //drawToTempScreen();//draw everything to the buffered image
                //drawToScreen();//draw the buffered image to the screen
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS" + drawCount);
                //System.out.println(player.speed);
                drawCount = 0;
                timer = 0;
            }

           
        }
    }

    public void update(){
        if(gameState == playState ||gameState == tutorialState ){
            //Update player position
            player.update();

            //Update NPC position
            for(int i = 0; i<npc.length; i++){
                if(npc[currentMap][i]!=null){
                    npc[currentMap][i].update();
                }
            }
        }
        if(gameState == playPauseState || gameState == tutorialPauseState){

        }

    } 

    
    public void playMusic(int i ){
        music.setFile(i);
        music.play();
        music.loop();

    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
        /*public void drawToScreen(){
        Graphics g = getGraphics();

        g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
        g.dispose();
    }
    
    public void drawToTempScreen(){
        
        //DEBUG
        long  drawStart = 0;
        if(keyH.checkDrawTime){
            drawStart = System.nanoTime();

        }

        //TILE
        tileM.draw(g2); 

        //OBJECT
        for(int i = 0; i <obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        //DEBUG
        if(keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);

        }

        g2.dispose();
        
    }*/
    /*  public void run() {
        // TODO Auto-generated method stub

        double drawInterval = 1000000000/FPS; //0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread!= null){


            update();

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;


                if(remainingTime < 0 ){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime+=drawInterval;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        int i = 1;
        while(gameThread != null){
            if(i == 60){
                i = 1;
            }
            System.out.println(i);
            i++;

        }
        //throw new UnsupportedOperationException("Unimplemented method 'run'");
    }*/
    
}
