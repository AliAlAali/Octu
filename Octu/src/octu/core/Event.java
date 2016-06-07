/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octu.core;

import octu.core.action.Action;
import java.util.ArrayList;

/**
 *
 * @author Ali
 */
public class Event implements Listable{
    
    public static final int POR_START = 1;
    public static final int POR_GOING = 4;
    public static final int POR_SCHEDULE = 2;
    public static final int POR_TRIGGER = 3;
    public static final int POR_LOOP = 5;
    
    private ArrayList<Action> actions;
    private String name;
    private int por;

    public Event(int por){
        actions = new ArrayList<Action>();
        this.por = por;
    }
    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public Action removeAction(int index){
        return actions.remove(index);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addAction(Action act){
        //to be implemented
        
        //basic implementation for testing propuses
        actions.add(act);
    }

    public int getPor() {
        return por;
    }
    
<<<<<<< HEAD
    public Action getAction(int index){
        if(actions.size() > 0)
             return actions.get(index);
        return null;
    }
=======
    
>>>>>>> 0eeab5323e53a32e6ed53148a65600fed1ae57ba
}
