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
//
//        //init ProcessMonitor
//        ProcessManager processManager = new ProcessManager();
//        Thread processManagerThread = new Thread(processManager);
//        processManager.setThreadAndRunnable(processManagerThread);
//        processManagerThread.start();
//
//        //init voice capturing daemon
//        VoiceCapture voiceCapture = new VoiceCapture();
//        voiceCapture.setup();
//        processManager.addThread(voiceCapture);
//
//        //init text to speech daemon
//        ComputerVoice voice = new ComputerVoice();
//        processManager.addThread(voice);
//        
//        voice.speak("Hello World!");
//        voice.speak("How are you?");
//        voice.speak("My name is computer!");
//
//        System.out.println();
//        System.out.println("Computer started.");
//        System.out.println("Running Threads:");
//        for (int i = 0; i < processManager.getRunningThreads().size(); i++) {
//            System.out.println(i + " - " + processManager.getRunningThreads().get(i));
//        }
//        System.out.println();
        
        SoundTest2 test = new SoundTest2();
        
    }
}
