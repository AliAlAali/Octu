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

    public static final String OPERATION_TYPE = "TYPE";
    public static final String OPERATION_PRESS = "PRESS";
    public static final String OPERATION_RELEASE = "RELEASE";

    public static final String KEY_SHIFT = "SHIFT";
    public static final String KEY_CTRL = "CTRL";
    public static final String KEY_ENTER = "ENTER";
    public static final String KEY_ESC = "ESC";
    public static final String KEY_DEL = "DELETE";
    public static final String KEY_LETTER = "LETTER";

    private Robot robot;
    private String text;
    private String type;

    public KeyStrokeAction(int por, String oper, String text) {
        super(por);
        this.text = text;
        this.type = oper;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void occur() {
        super.occur();
        String str = text.toUpperCase();
        if (type.equals(OPERATION_TYPE)) {
            for (int i = 0; i < text.length(); i++) {
                char c = str.charAt(i);
                if (Character.isUpperCase(text.charAt(i))) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress((int) c);
                    robot.keyRelease((int) c);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress((int) c);
                    robot.keyRelease((int) c);
                }
                if (i == text.length() - 1) {
                    finish();
                    break;
                }
            }
        }else if(type.equals(OPERATION_PRESS)){
            switch(text){
                case KEY_SHIFT:
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    break;
                case KEY_ENTER:
                    robot.keyPress(KeyEvent.VK_ENTER);
                    break;
                case KEY_CTRL:
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    break;
                case KEY_DEL:
                    robot.keyPress(KeyEvent.VK_DELETE);
                    break;
                case KEY_ESC:
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    break;
                default:
                    robot.keyPress(text.toUpperCase().charAt(0));
                    break;
            }
        }else if(type.equals(OPERATION_RELEASE)){
            switch(text){
                case KEY_SHIFT:
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    break;
                case KEY_ENTER:
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    break;
                case KEY_CTRL:
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    break;
                case KEY_DEL:
                    robot.keyRelease(KeyEvent.VK_DELETE);
                    break;
                case KEY_ESC:
                    robot.keyRelease(KeyEvent.VK_ESCAPE);
                    break;
                default:
                    robot.keyRelease(text.toUpperCase().charAt(0));
                    break;
            }
        }
        finish();
    }

    @Override
    public String getDescription() {
        return "KeyStroke Action: " + type + " \"" + text + "\"";
    }

}
