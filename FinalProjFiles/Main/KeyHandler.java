package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entity.Player;



public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    
    //DEBUG
    boolean checkDrawTime = false;

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
            if(code == KeyEvent.VK_W && gp.ui.commandNum != 0){
                gp.ui.commandNum--;
                gp.playSE(6);
            }
            if(code == KeyEvent.VK_S && gp.ui.commandNum != 3){
                gp.ui.commandNum++;
                gp.playSE(6);
            }
            if(code == KeyEvent.VK_ENTER){

                if(gp.ui.commandNum == 0){
            
                    gp.playSE(7);
                    gp.gameState = gp.playState;
                    gp.player.setDefaultValues();
                    gp.stopMusic();
                    gp.playMusic(0);

                }

                if(gp.ui.commandNum == 1){
                    gp.playSE(7);
                    //load file
                }
                if(gp.ui.commandNum==2){
                    
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
                }
            }
        }

        else if (gp.gameState == gp.playState ){
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
                gp.gameState = gp.playPauseState;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }
        else if (gp.gameState == gp.playPauseState ){
            if(code == KeyEvent.VK_W && gp.gameState == gp.playPauseState && gp.ui.playPauseCommandNum != 0){
                gp.ui.playPauseCommandNum--;
                gp.playSE(6);
            }
            if(code == KeyEvent.VK_S && gp.gameState == gp.playPauseState && gp.ui.playPauseCommandNum != 2){
                gp.ui.playPauseCommandNum++;
                gp.playSE(6);
            }


            if(code == KeyEvent.VK_P){
                if(gp.gameState == gp.playPauseState){
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

      
      else if (gp.gameState == gp.tutorialState){
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
      else if (gp.gameState == gp.tutorialPauseState ){
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
        else if (gp.gameState  == gp.playDialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.playSE(7);         
                enterPressed = true;    
                


            }
        }

        else if (gp.gameState  == gp.tutorialDialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.playSE(7);   
                enterPressed = true;              
                


            }
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
