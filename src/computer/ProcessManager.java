/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stk
 */
public class ProcessManager implements Runnable {

    public String name = "ProcessMonitor";
    public Thread myThread;
    public Boolean terminate = false;
    private HashMap<String, Thread> threads = new HashMap<>();
    private HashMap<String, Runnable> runnables = new HashMap<>();

    public void setThreadAndRunnable(Thread t) {
        threads.put(name, t);
        runnables.put(name, this);
    }

    @Override
    public void run() {
        cycle();
    }

    @Override
    public boolean terminated() {
        return terminate;
    }

    @Override
    public void terminate() {
        terminate = true;
    }

    @Override
    public void cycle() {
        
        while (!terminate) {
            for (Entry entry : runnables.entrySet()) {
                String n = (String) entry.getKey();
                Runnable r = (Runnable) entry.getValue();
                if (!threads.containsKey(n) && !r.terminated()) {
                    startThread(n);
                }
                if (threads.containsKey(n) && r.terminated()) {
                    stopThread(n);
                }
            }
        }
        
    }

    public List<String> getRunningThreads() {
        List<String> l = new ArrayList<>();
        for (String n : threads.keySet()) {
            l.add(n);
        }

        return l;
    }

    public Integer getNumberOfRunningThreads() {
        return threads.size();
    }

    public void addThread(Runnable _runnable) {
        runnables.put(_runnable.getName(), _runnable);

    }

    public Thread startThread(String _name) {
        Runnable r = runnables.get(_name);
        Thread t = new Thread(r);
        t.start();

        threads.put(_name, t);
        return t;
    }

    public void stopThread(String _name) {
        Thread t = threads.get(_name);
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcessManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
