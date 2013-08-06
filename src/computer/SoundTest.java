/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
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

    public SoundTest() {
        System.out.println("Soundtest!");
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            System.out.println("Line found: " + line.getLineInfo().toString());
            line.open(format);
            line.start();

            int bufferSize = (int) format.getSampleRate()
                    * format.getFrameSize();
            byte buffer[] = new byte[bufferSize];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Date start = new Date();
            while (new Date().getTime() < (start.getTime() + 10000)) {
                int count = line.read(buffer, 0, buffer.length);
                if (count > 0) {
                    out.write(buffer, 0, count);
                }
            }

            OutputStream outputStream = new FileOutputStream("audiofile.wav");
            out.writeTo(outputStream);


            out.close();


        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void test() {
    }
}
