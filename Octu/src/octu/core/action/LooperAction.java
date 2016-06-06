/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core.action;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import octu.graphics.Graph;

/**
 *
 * @author Ali
 */
public class LooperAction extends Action {
    /*
     NOTE THAT THIS CLASS DOES NOT ACCEPT NESTED-LOOPER
     */

    private int index;
    private ArrayList<Action> actions;
    private Timer timer;
    private Graph graph; //to access graph directly

    public LooperAction(int por) {
        super(por);
        actions = new ArrayList<Action>();
        timer = new Timer();
    }

    @Override
    public void occur() {
        super.occur();
        timer.schedule(new Work(), 200);
    }

    public void addAction(Action act) {
        actions.add(act);
    }

    @Override
    public void flush() {
        super.flush();
        index = 0;

        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            action.flush();
        }
    }

    @Override
    public void finish() {
        super.finish(); 
        timer.cancel();
    }
    
    

    public void setGraph(Graph g) {
        this.graph = g;
    }

    @Override
    public String getDescription() {
        String s = "Looper: size - " + actions.size();

        for (int i = 0; i < actions.size(); i++) {
            Action get = actions.get(i);
            s += "\n\t " + i + "." + get.getDescription();
        }

        return s;
    }

    public class Work extends TimerTask {

        @Override
        public void run() {
            if (actions.size() <= 0) {
                return;
            }

            Action action = actions.get(index);
            if (!action.isOccuring()) {
                action.occur();

                if (action instanceof DelayAction) {
                    for (int i = 0; i < ((DelayAction) action).getDelay() / 1000; i++) {
                        graph.peak(false);
                    }
                } else {
                    graph.peak(true);
                }
            }
            
            if(action.isFinished())
                index++;
            if (index >= actions.size()) {
                finish();
            }
            timer.schedule(new Work(), 200);
        }

    }
}
