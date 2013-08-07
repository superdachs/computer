/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaFlacEncoder.FLAC_FileEncoder;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author stk
 */
public class FlacAudioRecorder {

    AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, /*44100.0F*/16000, 16, 2, 4, /*44100.0F*/16000, false);
    DataLine.Info info = new DataLine.Info(
            TargetDataLine.class, format);
    TargetDataLine targetLine;

    public FlacAudioRecorder() {
        try {
            targetLine = (TargetDataLine) AudioSystem.getLine(info);
            targetLine.open(format);

        } catch (LineUnavailableException ex) {
            Logger.getLogger(FlacAudioRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public File record(long milliseconds) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int numBytesRead;
        byte[] data = new byte[targetLine.getBufferSize() / 5];

        targetLine.start();

        Long startTime = new Date().getTime();
        System.out.println("RECORD STARTED");
        while (new Date().getTime() < (startTime + milliseconds + 1000)) {
            numBytesRead = targetLine.read(data, 0, data.length);
            out.write(data, 0, numBytesRead);
        }
        System.out.println("RECORD STOPPED");
        File wavFile = new File("speech" + new Date().getTime() + ".wav");
        ByteArrayInputStream bais = new ByteArrayInputStream(out.toByteArray());
        AudioInputStream ais = new AudioInputStream(bais, format, out.size());

        if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE, ais)) {
            try {
                AudioSystem.write(ais, AudioFileFormat.Type.WAVE, wavFile);
            } catch (IOException ex) {
                Logger.getLogger(FlacAudioRecorder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        targetLine.stop();
        try {
            ais.close();
        } catch (IOException ex) {
            Logger.getLogger(FlacAudioRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }

        FLAC_FileEncoder flacEncoder = new FLAC_FileEncoder();
        File flacFile = new File("speech.flac");

        if (flacFile.exists()) {
            flacFile.delete();
        }

        flacEncoder.encode(wavFile, flacFile);
        
        wavFile.delete();
        
        return flacFile;
    }
}
