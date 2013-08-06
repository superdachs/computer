/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

/**
 *
 * @author stk
 */
public class TestClass implements Runnable {

    public String name = "";
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
            System.out.println("Process " + name + " running!");
        }
    }

    @Override
    public String getName() {
        return name;
    }
    
}
