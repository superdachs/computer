/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
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

    AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0F, 16, 2, 4, 44100.0F, false);
    DataLine.Info info = new DataLine.Info(
            TargetDataLine.class, format);
    TargetDataLine targetLine;

    public SoundTest() {
        try {
            System.out.println("Soundtest!");

            targetLine = (TargetDataLine) AudioSystem.getLine(info);
            targetLine.open(format);
            targetLine.start();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numBytesRead;
            byte[] data = new byte[targetLine.getBufferSize() / 5];

            targetLine.start();

            long start = new Date().getTime();

            System.out.println("start record...");
            while (new Date().getTime() < (start + 10000)) {
                numBytesRead = targetLine.read(data, 0, data.length);
                out.write(data, 0, numBytesRead);
            }
            System.out.println("stop record...");

            File fileOut = new File("speech" + new Date().getTime() + ".wav");
            ByteArrayInputStream bais = new ByteArrayInputStream(out.toByteArray());
            AudioInputStream ais = new AudioInputStream(bais, format, out.size());

            if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE, ais)) {
                AudioSystem.write(ais, AudioFileFormat.Type.WAVE, fileOut);
            }
            targetLine.stop();

            ais.close();



        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
