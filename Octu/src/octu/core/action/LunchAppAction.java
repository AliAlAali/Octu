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
import octu.core.FileHandler;

/**
 *
 * @author Ali
 */
public class LunchAppAction extends Action {

    private String path;
    private Command cmd;

    public LunchAppAction(int por, String path) {
        super(por);
        this.path = path;
        cmd = new Command();
    }

    @Override
    public String getArguement() {
        return FileHandler.ARG + path + FileHandler.ARG;
    }

    
    @Override
    public void occur() {
        try {
            super.occur();
            cmd.lunchExternalProgramm(path);
        } catch (IOException ex) {
            Logger.getLogger(LunchAppAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            finish();
        }
    }

    @Override
    public String getDescription() {
        return "Lunch Application: " + path;
    }

}
