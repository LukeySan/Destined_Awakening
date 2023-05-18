package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
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
            }
            else
            if(code == KeyEvent.VK_S && gp.ui.commandNum != 2){
                gp.ui.commandNum++;
            }
            
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(0);

                }
                if(gp.ui.commandNum == 1){
                    //add later
                }
                if(gp.ui.commandNum == 2){
                   System.exit(0);
                }
            }
        }


        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
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
