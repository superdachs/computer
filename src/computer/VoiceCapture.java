/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

/**
 *
 * @author stk
 */
public class VoiceCapture implements Runnable {

    public String name = "VoiceCaptureDaemon";
    private boolean terminated = false;
    private Mixer mixer;
    private List<Mixer> mixers = new ArrayList<>();

    public void setup() {
        System.out.println("Searching for microphones");
        for (Mixer.Info mixerinfo : AudioSystem.getMixerInfo()) {
            mixer = AudioSystem.getMixer(mixerinfo);
            System.out.println("MIXER: " + mixerinfo.toString());

            
            System.out.println(mixer.getSourceLineInfo());
            System.out.println(mixer.getTargetLineInfo());
            
        }
        System.out.println();
    }

    @Override
    public void run() {




        cycle();
    }

    @Override
    public boolean terminated() {
        return terminated;
    }

    @Override
    public void terminate() {
        terminated = true;
    }

    @Override
    public void cycle() {
        while (!terminated()) {
            //call event when audio is captured!
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
