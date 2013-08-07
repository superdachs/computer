/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lcars.widgets;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author stk
 */
public class LcarsButton extends JButton {
    
    public LcarsButton(String caption, String color) {
        this.setText(caption);
        this.setBackground(Color.red);
    }
    
}
