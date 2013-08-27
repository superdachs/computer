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
        String waanswer = requester.requestAnswer("When was Charles Darwin born?");
        
        
        
        //init ProcessMonitor
        ProcessManager processManager = new ProcessManager();
        Thread processManagerThread = new Thread(processManager);
        processManager.setThreadAndRunnable(processManagerThread);
        processManagerThread.start();

         //init text to speech daemon
        final TextToSpeechDaemon voice = new TextToSpeechDaemon();
        processManager.addThread(voice);

        //init voice capturing daemon
        final VoiceControlDaemon voiceCapture = new VoiceControlDaemon();
        voiceCapture.setup();
        voiceCapture.addCommand("computer");
        processManager.addThread(voiceCapture);

        voiceCapture.addCommandListener(new CommandRecognitionListener() {
            @Override
            public void commandRecognized(String command) {
                System.out.println("Command: " + command + " recognized by Main Thread!");
                switch (command) {
                    case "computer": 
                        System.out.println("YES?");
                        String[] question = voiceCapture.getSentence();
                        System.out.println(question[0]);
                        String answer = new WolframAlphaRequester().requestAnswer(question[0]);
                        voice.speak(answer);
                }
            }
        });



       
        System.out.println();
        System.out.println("Computer started.");
        System.out.println("Running Threads:");
        for (int i = 0; i < processManager.getRunningThreads().size(); i++) {
            System.out.println(i + " - " + processManager.getRunningThreads().get(i));
        }
        System.out.println();

    }

    public void initCommunication() {
        
    }
}
