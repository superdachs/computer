/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import computer.tools.FlacRecorder;
import computer.tools.GoogleSpeechAPIConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

/**
 *
 * @author stk
 */
public class CommandCapture implements Runnable {

    List<CommandListener> commandListeners = new ArrayList<>();
    List<String> commands = new ArrayList<>();
    
    
    public String name = "VoiceCaptureDaemon";
    private boolean terminated = false;
    private Mixer mixer;
    private List<Mixer> mixers = new ArrayList<>();
    
    private FlacRecorder recorder = new FlacRecorder();
    private GoogleSpeechAPIConverter converter = new GoogleSpeechAPIConverter();
    private boolean modify;
    private boolean protect;

    public void setup() {
    }

    public void addCommandListener(CommandListener l) {
        commandListeners.add(l);
    }
    
    public void addCommand(String c) {
        
        while(protect) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(CommandCapture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        modify = true;
        commands.add(c);
        modify = false;
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
            try {
                String[] firstCommand = converter.convert(recorder.record(2000));
                if (firstCommand != null) {
                    while(modify) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CommandCapture.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    protect = true;
                    for(String command : commands) {
                    if (firstCommand[0].equals(command)) {
                        for(CommandListener l : commandListeners) {
                            l.commandRecognized(command);
                        }
                    }
                    }
                    protect = false;
                }
            } catch (IOException ex) {
                Logger.getLogger(CommandCapture.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CommandCapture.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public String getName() {
        return name;
    }
}

