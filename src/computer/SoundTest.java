/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author stk
 */
public class SoundTest {

    float sampleRate = 8000;
    int sampleSizeInBits = 8;
    int channels = 1;
    boolean signed = true;
    boolean bigEndian = true;
    AudioFormat format = new AudioFormat(sampleRate,
            sampleSizeInBits, channels, signed, bigEndian);
    DataLine.Info info = new DataLine.Info(
            TargetDataLine.class, format);
    TargetDataLine line;

    public void SoundTest() {
        try {
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            System.out.println("Line found: " + line.getLineInfo().toString());
        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void test() {
        
    }
}
