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

        //init ProcessMonitor
        ProcessManager processManager = new ProcessManager();
        Thread processManagerThread = new Thread(processManager);
        processManager.setThreadAndRunnable(processManagerThread);
        processManagerThread.start();

        //init voice capturing daemon
        CommandRecognitionDaemon voiceCapture = new CommandRecognitionDaemon();
        voiceCapture.setup();
        voiceCapture.addCommand("Computer");
        processManager.addThread(voiceCapture);

        voiceCapture.addCommandListener(new CommandRecognitionListener() {
            @Override
            public void commandRecognized(String command) {
                System.out.println("Command: " + command + " recognized by Main Thread!");
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
}
