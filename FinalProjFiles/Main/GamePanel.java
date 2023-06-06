package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Main.KeyHandler;
import ai.PathFinder;
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
     public final int maxWorldCol = 250;//50
     public final int maxWorldRow= 250;//50
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
     public final int characterState = 7;
     public final int gameOverState = 8;


     //FOR FULL SCREEN
     int screenWidth2 = screenWidth;
     int screenHeight2 = screenHeight;
     BufferedImage tempScreen;
     Graphics2D g2;


     //FPS
     int FPS =  60;

    //SYSTEM
     public TileManager tileM = new TileManager(this);
     public KeyHandler keyH = new KeyHandler(this);
     Sound se = new Sound();
     Sound music = new Sound();
     public CollisionChecker cChecker =  new CollisionChecker(this);
     public AssetSetter aSetter = new AssetSetter(this);
     public UI ui = new UI(this);
     public EventHandler eHandler = new EventHandler(this);
     public PathFinder pFinder = new PathFinder(this);
     
     Thread gameThread; 
     int count = 0;


     //ENTITY AND OBJEJCTS
     public Player player = new Player(this,keyH);
     public Entity obj[][] = new Entity[maxMap][20];
     public Entity npc[][] = new Entity[maxMap][20];
     public Entity monster[][] = new Entity[maxMap][20];
     public Entity monster2[][] = new Entity[maxMap][20];
     public Entity monster3[][] = new Entity[maxMap][20];

     ArrayList<Entity> entityList = new ArrayList<>();


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setMonster();
        aSetter.setMonster2();
        aSetter.setMonster3();

        aSetter.setNPC();
        playMusic(5);
        gameState = titleState;

       tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB); //Buffered image as large as the game window
        g2 = (Graphics2D)tempScreen.getGraphics();

        //setFullScreen();
    }

    //UNUSED
    public void setFullScreen(){

        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(main.window);

        //GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = main.window.getWidth();
        screenHeight2 = main.window.getHeight();
    }

    public void retry(){
        player.setDefaultPositions();
        player.restoreLife();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setMonster2();
        aSetter.setMonster3();

    }

    public void restart(){
        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreLife();
        aSetter.setObject();
        aSetter.setMonster();
        aSetter.setMonster2();
        aSetter.setMonster3();
        aSetter.setNPC();

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); 

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

            for(int i = 0; i<monster.length ; i++){
                if(monster[currentMap][i]!=null){
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                        monster[currentMap][i] = null;
                }
            }
        }
        for(int i = 0; i<monster2.length ; i++){
            if(monster2[currentMap][i]!=null){
                if(monster2[currentMap][i].alive == true && monster2[currentMap][i].dying == false){
                    monster2[currentMap][i].update();
                }
                if(monster2[currentMap][i].alive == false){
                    monster2[currentMap][i] = null;
                }
            }
        }   

        for(int i = 0; i<monster3.length ; i++){
            if(monster3[currentMap][i]!=null){
                if(monster3[currentMap][i].alive == true && monster3[currentMap][i].dying == false){
                    monster3[currentMap][i].update();
                }
                if(monster3[currentMap][i].alive == false){
                    monster3[currentMap][i] = null;
            }
        }
    }
        if(gameState == playPauseState || gameState == tutorialPauseState){

        }

        } 
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
     if(gameState == tutorialState || gameState == tutorialPauseState|| gameState == tutorialDialogueState){

        currentMap = 2;
        tileM.draw(g2);
        //adds entities to list
        entityList.add(player);

        for(int i = 0; i<npc.length; i++){
            if(npc[currentMap][i] != null){
                entityList.add(npc[currentMap][i]);
            }
        }

        for (int i = 0; i<obj.length;i++){
            if(obj[currentMap][i] != null){
                entityList.add(obj[currentMap][i]);
            }
        }
        for (int i = 0; i<monster.length;i++){
            if(monster[currentMap][i] != null){
                entityList.add(monster[currentMap][i]);
            }
        }
    //sort
        /*for(int i = 0;i<entityList.size(); i++){
            if(entityList.get(i).equals(null)){
                System.out.println("null object at element" + currentMap + i);
            }
        }*/

    /*Collections.sort(entityList, new Comparator<Entity>(){
        public int compare (Entity e1, Entity e2){
            int result = Integer.compare(e1.worldY, e2.worldY);
            return result;
        }
    });*/

    //draw entities
    for (int i = 0; i<entityList.size();i++){
        entityList.get(i).draw(g2);

    }
    for (int i = 0; i<entityList.size();i++){
        entityList.remove(i);
        
    }

        

        ui.draw(g2);

        if (g2 != null) {
            g2.dispose();
        }


    }
    
    
    //OTHERS
    if (gameState == playState || gameState == playPauseState || gameState == playDialogueState || gameState == characterState || gameState == gameOverState){
            
            currentMap = 3;
            

            
         //TILE
            tileM.draw(g2); 

            //adds entities to list
            entityList.add(player);

            for(int i = 0; i<npc.length; i++){
                if(npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i<obj.length;i++){
                if(obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            
            for (int i = 0; i<monster.length;i++){
                if(monster[currentMap][i] != null){
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i<monster2.length;i++){
                if(monster2[currentMap][i] != null){
                    entityList.add(monster2[currentMap][i]);
                }
            }
            for (int i = 0; i<monster3.length;i++){
                if(monster3[currentMap][i] != null){
                    entityList.add(monster3[currentMap][i]);
                }
            }
        
        //sort
        /*Collections.sort(entityList, new Comparator<Entity>(){
            public int compare (Entity e1, Entity e2){
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
        });*/

        //draw entities
        for (int i = 0; i<entityList.size();i++){
            
                entityList.get(i).draw(g2);  
        }
        entityList.clear();

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
     
}
