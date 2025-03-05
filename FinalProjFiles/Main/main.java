package Main;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Main.GamePanel;

public class main {
    public static JFrame window;
    public static void main(String[]args){
       

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game!");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();

        gamePanel.startGameThread();

    }
}
