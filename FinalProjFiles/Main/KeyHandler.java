package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entity.Player;



public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    
    //DEBUG
    boolean checkDrawTime = false;
    public boolean godModeOn = false;

    GamePanel gp;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            titleState(code);
        }

        else if (gp.gameState == gp.playState ){
            playState(code);
    }
        else if (gp.gameState == gp.playPauseState ){
            playPauseState(code);
        }

      else if (gp.gameState == gp.tutorialState){
            tutorialState(code);
      }
      else if (gp.gameState == gp.tutorialPauseState ){
            tutorialPauseState(code);
    }
        else if (gp.gameState  == gp.playDialogueState){
            playDialogueState(code);
        }
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }

        else if (gp.gameState  == gp.tutorialDialogueState){
            tutorialDialogueState(code);
        }

        else if (gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
        

        //DEBUG
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;

            }
            else if (checkDrawTime == true){
                checkDrawTime = false;
            }
        }



    }

    public void gameOverState(int code){
        if(code== KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0){
                gp.ui.commandNum = 1;
            }
            gp.playSE(6);
        }
        if(code== KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum<1){
                gp.ui.commandNum = 0;
            }
            gp.playSE(6);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.stopMusic();
                gp.playSE(7);

                gp.gameState= gp.playState;
                gp.retry();
                gp.playMusic(0);
            }
            else if(gp.ui.commandNum == 1){
                gp.stopMusic();
                gp.playSE(7);

                gp.gameState =  gp.titleState;
                gp.restart();
                gp.playMusic(5);
            }
        }
    }


    public void titleState(int code){
        if(code == KeyEvent.VK_W && gp.ui.commandNum != 0){
            gp.ui.commandNum--;
            gp.playSE(6);
        }
        if(code == KeyEvent.VK_S && gp.ui.commandNum != 1){
            gp.ui.commandNum++;
            gp.playSE(6);
        }
        if(code == KeyEvent.VK_ENTER){

            if(gp.ui.commandNum == 0){
        
                gp.playSE(7);
                gp.gameState = gp.playState;
                gp.aSetter.setObject();
                gp.aSetter.setMonster();
                gp.aSetter.setMonster2();
                gp.aSetter.setMonster3();
                gp.aSetter.setNPC();
                gp.player.setDefaultValues();
                gp.stopMusic();
                gp.playMusic(0);

            }

            if(gp.ui.commandNum == 1){
                gp.playSE(7);
               System.exit(0);
                
            }

            //UNUSED BUTTON
            /*if(gp.ui.commandNum==2){
                
                gp.playSE(7);
                gp.gameState = gp.tutorialState;
                gp.player.setDefaultValues();
                System.out.println("Asset set Called");
                gp.stopMusic();
                gp.playMusic(8);
                
                //Player.worldX = gp.tileSize*24;
                //Player.worldY = gp.tileSize*21;
                //Tutorial
            }
            if(gp.ui.commandNum == 3){
                gp.playSE(7);
               System.exit(0);
            }*/
        }
    }

    public void playState(int code){
        if(code == KeyEvent.VK_W && gp.gameState == gp.playState){
            upPressed = true;       
        }
        if(code == KeyEvent.VK_S && gp.gameState == gp.playState){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A && gp.gameState == gp.playState){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D && gp.gameState == gp.playState){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.playSE(7);

                gp.gameState = gp.playPauseState;
            }
        }
        if(code == KeyEvent.VK_C){
            gp.playSE(7);

            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_G){
            if(godModeOn == false){
                godModeOn = true;
            }
            else if(godModeOn == true){
                godModeOn = false;
            }
        }
    }
    public void tutorialState(int code){
        if(code == KeyEvent.VK_W && gp.gameState == gp.tutorialState){
            upPressed = true;       
        }
        if(code == KeyEvent.VK_S && gp.gameState == gp.tutorialState){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A && gp.gameState == gp.tutorialState){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D && gp.gameState == gp.tutorialState){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.tutorialState){
                gp.gameState = gp.tutorialPauseState;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }

    public void playPauseState(int code){
        if(code == KeyEvent.VK_W && gp.gameState == gp.playPauseState && gp.ui.playPauseCommandNum != 0){
            gp.ui.playPauseCommandNum--;
            gp.playSE(6);
        }
        if(code == KeyEvent.VK_S && gp.gameState == gp.playPauseState && gp.ui.playPauseCommandNum != 1){
            gp.ui.playPauseCommandNum++;
            gp.playSE(6);
        }


        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playPauseState){
                gp.playSE(7);
                gp.gameState = gp.playState;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.playPauseCommandNum == 0){
                gp.playSE(7);
                gp.gameState = gp.playState;
            }

            if(gp.ui.playPauseCommandNum == 1){
        
                gp.playSE(7);
                gp.gameState = gp.titleState;
                gp.player.setDefaultValues();
                gp.stopMusic();
                gp.playMusic(5);

            }

            if(gp.ui.playPauseCommandNum == 2){
                gp.playSE(7);
                //Save Game
            }
            
        }
    }
    public void tutorialPauseState(int code){
        if(code == KeyEvent.VK_W && gp.gameState == gp.tutorialPauseState && gp.ui.tutorialPauseCommandNum != 0){
            gp.ui.tutorialPauseCommandNum--;
            gp.playSE(6);
        }
        if(code == KeyEvent.VK_S && gp.gameState == gp.tutorialPauseState && gp.ui.tutorialPauseCommandNum != 1){
            gp.ui.tutorialPauseCommandNum++;
            gp.playSE(6);
        }
        
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.tutorialPauseState){
                gp.gameState = gp.tutorialState;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.tutorialPauseCommandNum == 0){
                gp.playSE(7);
                gp.gameState = gp.tutorialState;
            }

            if(gp.ui.tutorialPauseCommandNum == 1){
        
                gp.playSE(7);
                gp.gameState = gp.titleState;
                gp.player.setDefaultValues();
                gp.stopMusic();
                gp.playMusic(5);

            }
        }
    }

    public void playDialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.playSE(7);         
            enterPressed = true;    
        }
    }
    public void tutorialDialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.playSE(7);   
            enterPressed = true;              
            


        }
    }   

    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.playSE(7);
            gp.gameState = gp.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
       
       
       
    }
    


}
