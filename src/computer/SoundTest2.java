/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import computer.tools.FlacRecorder;
import computer.tools.GoogleSpeechAPIConverter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stk
 */
public class SoundTest2 {

    public SoundTest2() {
//        FlacRecorder rec = new FlacRecorder();
//        File f = rec.record(3000);
        File f2 = new File("testfile.flac");

        GoogleSpeechAPIConverter converter = new GoogleSpeechAPIConverter();
        try {
            converter.convert(f2);
        } catch (IOException ex) {
            Logger.getLogger(SoundTest2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
