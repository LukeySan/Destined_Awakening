package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/res/sound/byurside.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sound/goodFanfare.wav");

    }

    public void setFile(int i ){
        try {
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
            if (i == 0 ) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-25.0f);
            }
            else if (i == 4){
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);
            }

        } catch (Exception e) {
        }
    }
    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();

    }
    
}
