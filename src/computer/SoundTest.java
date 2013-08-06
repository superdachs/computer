/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
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
    AudioFormat format = new AudioFormat(AudioFormat.Encoding.ALAW, sampleRate,
            sampleSizeInBits, channels, channels, sampleRate, bigEndian);
    DataLine.Info info = new DataLine.Info(
            TargetDataLine.class, format);
    TargetDataLine line;

    public SoundTest() {
        try {
            System.out.println("Soundtest!");

            line = (TargetDataLine) AudioSystem.getLine(info);
            System.out.println("Line found: " + line.getLineInfo().toString());
            line.open(format);
            line.start();

            while (true) {
            }

        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void test() {
    }
}
