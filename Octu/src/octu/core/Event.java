/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octu.core;

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
    
    private ArrayList<Action> actions;

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
