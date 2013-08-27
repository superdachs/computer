/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import computer.tools.WolframAlphaRequester;

/**
 *
 * @author stk
 */
public class Computer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        WolframAlphaRequester requester = new WolframAlphaRequester();
        System.out.println(requester.requestAnswer("When was Charles Darwin born?"));
        
        
        
        //init ProcessMonitor
        ProcessManager processManager = new ProcessManager();
        Thread processManagerThread = new Thread(processManager);
        processManager.setThreadAndRunnable(processManagerThread);
        processManagerThread.start();

        //init voice capturing daemon
        VoiceControlDaemon voiceCapture = new VoiceControlDaemon();
        voiceCapture.setup();
        voiceCapture.addCommand("Computer");
        processManager.addThread(voiceCapture);

        voiceCapture.addCommandListener(new CommandRecognitionListener() {
            @Override
            public void commandRecognized(String command) {
                System.out.println("Command: " + command + " recognized by Main Thread!");
                switch (command) {
                    case "Computer": 
                        System.out.println("YES?");
                }
            }
        });



        //init text to speech daemon
        TextToSpeechDaemon voice = new TextToSpeechDaemon();
        processManager.addThread(voice);

        voice.speak("Hello World!");
        voice.speak("How are you?");
        voice.speak("My name is computer!");

        System.out.println();
        System.out.println("Computer started.");
        System.out.println("Running Threads:");
        for (int i = 0; i < processManager.getRunningThreads().size(); i++) {
            System.out.println(i + " - " + processManager.getRunningThreads().get(i));
        }
        System.out.println();

    }

    public void initCommunication() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
