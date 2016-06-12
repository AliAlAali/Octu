/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core.action;

/**
 *
 * @author Ali
 */
public class StopApplicationAction extends Action{
    
    public StopApplicationAction(int por) {
        super(por);
    }

    @Override
    public String getArguement() {
        return "";
    }
    
    

    @Override
    public void occur() {
        super.occur(); 
        System.exit(0);
        finish();
    }

    @Override
    public String getDescription() {
        return "Stop Application";
    }
    
}
