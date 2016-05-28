/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Ali
 */
public class Graph extends JPanel {

    private int interval;
    private int values[];
    private int index; //used to determine new points index

    public Graph() {
        this.interval = 20;
        this.values = new int[interval];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int unit = getWidth() / interval;
        g.setColor(Color.lightGray);
        for (int i = 0; i < interval; i++) {
            g.drawLine(unit * i, 0, unit * i, getHeight());
        }
        
        //drawing the recieved data
        //changing colors
        g.setColor(Color.BLUE);
        for (int i = 0; i < values.length; i++) {
            if(values[i] == 1){
                g.drawLine(unit * i, getHeight()/2, unit* i + unit, getHeight()/2);
                if(i != 0 && values[i - 1] == 0){
                    g.drawLine(unit * i, getHeight()/2, unit * i, getHeight() - 3);
                }
            }else{
                g.drawLine(unit * i, getHeight() - 3, unit * i + unit, getHeight() - 3);
                if(i != 0 && values[i - 1] == 1){
                   g.drawLine(unit * i, getHeight()/2, unit * i, getHeight() - 3);
                }
            }
        }
    }

    public void peak(boolean value) {
        incrementIndex();
        if (value) {
            values[index] = 1;
        }else{
            values[index] = 0;
        }
        repaint();
    }

    private void incrementIndex() {
        if (index + 1 >= interval) {
            index = interval-1;
            //request shifting for arrayelemetns
            shiftElements();
        } else {
            index++;
        }
    }
    
    private void shiftElements(){
        for (int i = 0; i < values.length - 1; i++) {
            values[i] = values[i+1];
        }
    }

}
