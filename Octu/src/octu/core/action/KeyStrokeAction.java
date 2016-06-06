/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octu.core.action;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import octu.core.action.Action;

/**
 *
 * @author Ali
 */
public class KeyStrokeAction extends Action {

    private Robot robot;
    private String text;
    
    public KeyStrokeAction(int por, String text) {
        super(por);
        this.text = text;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(KeyStrokeAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void occur() {
        super.occur(); 
        String str = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            char c = str.charAt(i);
            if(Character.isUpperCase(text.charAt(i))){
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress((int)c);
                robot.keyRelease((int)c);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }else{
                robot.keyPress((int)c);
                robot.keyRelease((int)c);
            }
            if(i == text.length()-1){
                finish();
                break;
            }
        }
    }

    @Override
    public String getDescription() {
        return "KeyStroke Action: \"" + text + "\"";
    }
    
    
    
}
