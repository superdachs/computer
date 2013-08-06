/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computer;

import computer.tools.FlacRecorder;

/**
 *
 * @author stk
 */
public class SoundTest2 {
    public SoundTest2() {
        FlacRecorder rec = new FlacRecorder();
        rec.record(10000);
    }
}
