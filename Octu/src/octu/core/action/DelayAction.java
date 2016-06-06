/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octu.core.action;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Ali
 */
public class DelayAction extends Action{

    private long delay;
    private Timer timer;
    
    public DelayAction(int por, long delay) {
        super(por);
        this.delay = delay;
        timer = new Timer();
    }

    @Override
    public void occur() {
        super.occur(); 
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
            }
        }, delay);
    }

    @Override
    public String getDescription() {
        return "Delay: for " + (delay/1000.0) + " second(s)";
    }

    
    
    
}
