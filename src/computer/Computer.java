/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

/**
 *
 * @author stk
 */
public class Computer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ProcessManager processManager = new ProcessManager();
        Thread processManagerThread = new Thread(processManager);
        processManager.setThreadAndRunnable(processManagerThread);
        processManagerThread.start();

        ComputerVoice voice = new ComputerVoice();
        processManager.addThread(voice);
        TestClass testClass = new TestClass();
        testClass.name = "test";
        processManager.addThread(testClass);
        voice.speak("Hello World!");
        voice.speak("How are you?");
        voice.speak("My name is computer!");
        
    }
}
