/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octu.core.action;

import octu.core.Condition;
import octu.core.Listable;
import octu.core.Variable;

/**
 *
 * @author Ali
 */
public class Action implements Listable{
    
    public static final String TYPE_COMMAND = "command";
    public static final String TYPE_LUNCH_APP = "lunch application";
    public static final String TYPE_MOUSE = "mouse evnts";
    public static final String TYPE_SHUT_DOWN = "shut down the computer";
    public static final String TYPE_KEYSTROKES = "keystrokes";
    
    private int por; //constant from Event class
    private boolean finish;
    private boolean isOccuring;
    
    private Condition condition; //null if no condition
    
    public Action(int por){
        this.por = por;
    }
    
    public void occur(){
        this.isOccuring = true;
    }
    
    public boolean isOccuring(){
        return isOccuring;
    }
    
    /*
        if no condition,variable must be null
    */
    public boolean isExecutable(Variable v){
        
        return false;
    }
    
    /*
        used to determine which action should occur first
    */
    public int getPriority(){ 
        return por;
    }
    
    public boolean isFinished(){
        return finish;
    }
    
    public void finish(){
        this.finish = true;
    }
    
    public Condition getCondition(){
        return this.condition;
    }
    
    /*
        return every thing to its original state
    */
    public void flush(){
        isOccuring = false;
        finish = false;
    }
    
    /*
        used to save a file
    */
    public String getArguement(){
        return null;
    }

    @Override
    public String getDescription() {
        if(condition == null){
            return "Action: do stuff";
        }
        return "Action: do stuff if( " + condition.toString() + " )";
    }
}
