/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octu.core.action;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import octu.core.Command;
import octu.core.action.Action;

/**
 *
 * @author Ali
 */
public class ShutDownAction extends Action{
    
    private Command cmd;
    private String type; //from Command
    
    public ShutDownAction(int por, String type){
        super(por);
        this.type = type;
        this.cmd = new Command();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void occur() {
        try {
            super.occur();
            cmd.shutMachine(type);
            finish();//not necessary but no harm
        } catch (IOException ex) {
            Logger.getLogger(ShutDownAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getMeaning(String type){
        switch(type){
            case Command.MACHINE_LOG_OFF: return "Log-off";
            case Command.MACHINE_RESTART: return "Restart";
            case Command.MACHINE_SHUTDOWN: return"Shutdown";
        }
        return null;
    }
    @Override
    public String getDescription() {
        return "Shutdown Machine Action: " + getMeaning(type);
    }
    
    
    
    
}
