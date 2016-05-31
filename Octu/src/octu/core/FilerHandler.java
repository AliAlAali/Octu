/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Ali
 */
public class FilerHandler {
    /*
        this class is supposed to extract information of a file 
        or compress them into one.
    */
    
    public static FileFilter getFileFilter(){
        //create a file-filter for saving and opening sessions
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.isDirectory() || f.getName().endsWith(".oct")){
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return ".oct";
            }
        };
        
        return filter;
    }
}
