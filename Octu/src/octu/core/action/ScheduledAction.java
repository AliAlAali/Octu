/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octu.core.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import octu.graphics.Graph;

/**
 *
 * @author Ali
 */
public class ScheduledAction extends Action{

    private Calendar c;
    private ArrayList<Action> actions;
    private Timer timer;
    private Graph graph;
    private JList list;
    
    private int index;
    
    public ScheduledAction(int por, Calendar c, Graph graph, JList list) {
        super(por);
        this.c = c;
        actions = new ArrayList<Action>();
        timer = new Timer();
        this.graph = graph;
        this.list = list;
    }

    @Override
    public void occur() {
        super.occur();
        timer.schedule(new Task(), 200);
    }

    @Override
    public String getDescription() {
        String s = "SCHEDULE ACTION " + getTime() + " \n";
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            s += action.getDescription() + "\n";
        }
        
        return s;
    }
    
    public Action getAction(int ind){
        if(actions.size() > 0)
            return actions.get(ind);
        return null;
    }
    
    public void addAction(Action act){
        actions.add(act);
    }
    
    public void addAction(Action act, int ind){
        actions.add(ind, act);
    }

    public Calendar getCalendar() {
        return c;
    }

    public void setCalendar(Calendar c) {
        this.c = c;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }
    
    public String getTime(){
        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
    }
    
    
    
    public class Task extends TimerTask{

        @Override
        public void run() {
            
            if (index < actions.size()) {
                Action action = actions.get(index);
                if (!action.isOccuring()) {
                    action.occur();
                    //add action to the list
                    DefaultListModel<String> model = (DefaultListModel<String>)list.getModel();
                    model.addElement("Scheduled Event at " + getTime() + " : " + action.getDescription());
                   //update graph here
                    if(action instanceof octu.core.action.DelayAction){
                        graph.peak(false);
                    }else{
                        graph.peak(true);
                    }
                } else {
                    //update graph here
                    graph.peak(false);
                }
                if (action.isFinished()) {
                    index++;
                }
                timer.schedule(new Task(), 200);
            }else{
                finish();
            }
        }
        
    }
}
