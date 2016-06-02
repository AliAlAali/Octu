/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Ali
 */
public class Handler {

    private ArrayList<Event> events;
    private ArrayList<Action> actions;
    private ArrayList<Action> loop;
    private ArrayList<Variable> variables;

    private Timer timer;
    
    private boolean hasStarted;
    private int index;
    
    public Handler() {
        events = new ArrayList<Event>();
        actions = new ArrayList<Action>();
        timer = new Timer();
    }
    
    private void arrangeActionsFromEvents(ArrayList<Event> from, 
            ArrayList<Action> to){
        //to be implemented later
        
        //basic implementation for testing purposes
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            actions = event.getActions();
        }
    }
    
    /*
        used to start the series of actions
    */
    public void start(){
        stop();
        hasStarted = true;
        timer.scheduleAtFixedRate(new Queqy(), 0, 200);
    }
    
    /*
        used for stopping the series of actions
    */
    public void stop(){
        timer.cancel();
        timer = new Timer();
        hasStarted = false;
    }
    
    public boolean isAlive(){
        return hasStarted;
    }
    
   public boolean createVariable(String name, String type, Object initial){
       
       //make sure no dulplicates found for the given name
       for (int i = 0; i < variables.size(); i++) {
           if(variables.get(i).getName().equals(name)){
               return false;
           }
       }
       Variable v = new Variable(name);
       v.setType(type);
       v.assignValue(initial);
       variables.add(v);
       return true;
   }
   
   public boolean addEvent(Event evt){
      //make sure not to add an event twice
       for (int i = 0; i < events.size(); i++) {
          if(events.get(i).getName().equals(evt.getName())){
             return false; 
          }
           
       }
       
       events.add(evt);
       arrangeActionsFromEvents(events, loop);
       return true;
   }
    
   
    public class Queqy extends TimerTask {

        @Override
        public void run() {
            //implement the actions here
            
            //basic implementation for testing only
            if(index < actions.size()){
                Action action = actions.get(index);
                while(!action.isFinished()){
                    action.occur();
                }
                index++;
            }
        }
        
    }
}
