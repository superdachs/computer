/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import com.sun.speech.freetts.VoiceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stk
 */
public class TextToSpeechDaemon implements Runnable {

    private boolean terminated = false;
    private String name = "VoiceDaemon";
    private List<String> buffer = new ArrayList<>();
    private final static Voice voiceKevin16 = new Voice("kevin16");
    private boolean protect = true;
    
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

    //TODO: switch exceptions!!!
    @Override
    public void cycle() {
        while (!terminated) {

            if (!protect) {
                protect = true;
                for (String item : buffer) {
                    System.out.println("SPEAKING: " + item);
                    voiceKevin16.say(item);
                }
                buffer.clear();
                protect = false;
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(TextToSpeechDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void speak(String text) {
        while (protect) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TextToSpeechDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        protect = true;
        buffer.add(text);
        protect = false;

    }

    static class Voice {

        private String name;
        private com.sun.speech.freetts.Voice systemVoice;

        public Voice(String name) {
            this.name = name;
            this.systemVoice = VoiceManager.getInstance().getVoice(this.name);
            this.systemVoice.allocate();
        }

        public void say(String[] thingsToSay) {
            for (int i = 0; i < thingsToSay.length; i++) {
                this.say(thingsToSay[i]);
            }
        }

        public void say(String thingToSay) {
            this.systemVoice.speak(thingToSay);
        }

        public void dispose() {
            this.systemVoice.deallocate();
        }
    }
}
