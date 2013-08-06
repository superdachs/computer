/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

/**
 *
 * @author stk
 */
public interface Runnable extends java.lang.Runnable {

    @Override
    public void run();

    public boolean terminated();
    
    public void terminate();
    
    public void cycle();
    
    public String getName();
}
