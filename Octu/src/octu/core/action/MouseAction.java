/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core.action;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import octu.core.FileHandler;

/**
 *
 * @author Ali
 */
public class MouseAction extends Action {

    public static final String ACTION_CLICK = "CLICK";
    public static final String ACTION_PRESS = "PRESS";
    public static final String ACTION_RELEASE = "RELEASE";
    public static final String ACTION_SCROLL = "SCROLL";
    public static final String ACTION_MOVE = "MOVE";

    public static final String BUTTON_LEFT = "LEFT";
    public static final String BUTTON_RIGHT = "RIGHT";
    public static final String BUTTON_CENTER = "CENTER";

    private int x, y;
    private String type;
    private String button;
    private Robot robot;

    /*
     set button to null for scorlling and moving
     */
    public MouseAction(int por, String type, String button) {
        super(por);
        this.type = type;
        this.button = button;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(MouseAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getArguement() {
        return FileHandler.ARG + type + FileHandler.ARG
                + FileHandler.ARG + button + FileHandler.ARG
                + FileHandler.ARG + x + FileHandler.ARG
                + FileHandler.ARG + y + FileHandler.ARG;
    }
    
    

    public void setType(String type){
        this.type = type;
    }
    
    public void setButton(String button){
        this.button = button;
    }
    
    @Override
    public void occur() {
        super.occur();
        switch (type) {
            case ACTION_CLICK:
                click(button);
                break;
            case ACTION_PRESS:
                press(button);
                break;
            case ACTION_RELEASE:
                release(button);
                break;
            case ACTION_SCROLL:
                scroll(y);
                break;
            case ACTION_MOVE:
                move(x, y);
                break;
        }
        finish();
    }

    private void move(int x, int y) {
        robot.mouseMove(x, y);
    }

    private void click(String button) {
        switch (button) {
            case BUTTON_RIGHT:
                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                break;
            case BUTTON_LEFT:
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                break;
            case BUTTON_CENTER:
                robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                break;
        }
    }

    private void scroll(int y) {
        robot.mouseWheel(y);
    }

    private void press(String button) {
        switch (button) {
            case BUTTON_RIGHT:
                robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                
                break;
            case BUTTON_LEFT:
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                
                break;
            case BUTTON_CENTER:
                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                
                break;
        }
    }

    private void release(String button) {
        switch (button) {
            case BUTTON_RIGHT:
                
                robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                break;
            case BUTTON_LEFT:
                
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                break;
            case BUTTON_CENTER:
                
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String getDescription() {
        if(button != null){
            return "Mouse Action: " + type + " " + button;
        }else if(type.equals(ACTION_MOVE)){
            return "Mouse Action: " + type + " to (" + x + "," + y +")" ;
        }
        return "Mouse Action: " + type + " to " + y;
    }

    
}
