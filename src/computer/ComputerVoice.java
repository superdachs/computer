/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import com.sun.speech.freetts.VoiceManager;

/**
 *
 * @author stk
 */
public class ComputerVoice implements Runnable {

    private boolean terminated = false;
    private String name = "VoiceDaemon";
    private String buffer = null;
    private final static Voice voiceKevin16 = new Voice("kevin16");
    private Boolean spoken = false;

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

            try {

                if (buffer != null) {
                    voiceKevin16.say(buffer);
                    buffer = null;

                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void speak(String text) {
        if (buffer != null) {
            buffer = buffer + "\n" + text;
        } else {
            buffer = text;
        }

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
