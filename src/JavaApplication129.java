/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ikinci.deneme;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *
 * @author HP
 */
public class oyunMuzigi {
    private static Clip clip; 

    public static void MusicRun(String pathName) {
        try {
            AudioInputStream sesAl = AudioSystem.getAudioInputStream(new File(pathName));
            clip = AudioSystem.getClip();
            clip.open(sesAl);
            clip.loop(5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(oyunMuzigi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void stopMusicc() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            
        }
    }
     
    
    public static void sesSeviyesiniAyarla(float sesSeviyesi) {
    if (clip != null) {
        FloatControl kontrol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(sesSeviyesi) / Math.log(10.0) * 20.0);
        kontrol.setValue(dB);
    }
}  
}