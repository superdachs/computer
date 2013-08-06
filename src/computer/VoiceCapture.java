/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

/**
 *
 * @author stk
 */
public class VoiceCapture implements Runnable {

    public String name = "VoiceCaptureDaemon";
    private boolean terminated = false;
    
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
        while(!terminated()) {
            
        }
    }

    @Override
    public String getName() {
        return name;
    }
    
}
