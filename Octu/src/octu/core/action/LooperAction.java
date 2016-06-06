/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core.action;

import java.util.ArrayList;

/**
 *
 * @author Ali
 */
public class LooperAction extends Action{
    
    private int index;
    private ArrayList<Action> actions;
    
    public LooperAction(int por) {
        super(por);
        actions = new ArrayList<Action>();
    }

    @Override
    public void occur() {
        super.occur(); 
        
        if(actions.size() <= 0){
            return;
        }
        
        Action action = actions.get(index);
        action.occur();
        while(action.isFinished()){
            //wait until its done
        }
        
        index++;
        if(index >= actions.size()){
            finish();
        }
        //continue next iteration
        occur();
    }
    
    public void addAction(Action act){
        actions.add(act);
    }

    @Override
    public String getDescription() {
        String s = "Looper: size - " + actions.size();
        
        for (int i = 0; i < actions.size(); i++) {
            Action get = actions.get(i);
            s += "\n\t " + i + "."+ get.getDescription();
        }
        
        return s;
    }
    
    
}


