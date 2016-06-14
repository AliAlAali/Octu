/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core.action;

import octu.core.Command;
import octu.core.FileHandler;
import octu.core.action.Action;

/**
 *
 * @author Ali
 */
public class FileAction extends Action {

    public static final String TYPE_MOVE_FILE = "MOVE FILE";
    public static final String TYPE_DELETE_FILE = "DELETE FILE";
    public static final String TYPE_RENAME_FILE = "RENAME FILE";
    public static final String TYPE_COPY_FILE = "COPY FILE";
    public static final String TYPE_MAKE_DIRECTORY = "MAKE DIRECTORY";
    public static final String TYPE_HIDE_FILE = "HIDE FILE";
    public static final String TYPE_UNHIDE_FILE = "UNHIDE FILE";

    private String oldPath;
    private String newPath;
    private String type;

    private Command cmd;

    public FileAction(int por, String type, String path) {
        super(por);
        this.type = type;
        this.oldPath = path;
        cmd = new Command();
    }

    public String getType(){
        return this.type;
    }
    
    /*
        should be rarely used
    */
    public void setType(String type){
        this.type = type;
    }
    
    public String getNewPath() {
        return this.newPath;
    }

    public void setNewPath(String path) {
        this.newPath = path;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    /*
        newPath could be null
    */
    @Override
    public String getArguement() {
        return FileHandler.ARG + type + FileHandler.ARG + FileHandler.ARG + oldPath + FileHandler.ARG 
                + FileHandler.ARG + newPath + FileHandler.ARG;
    }

    @Override
    public void occur() {
        super.occur();
        try {
            switch (type) {
                case TYPE_MOVE_FILE:
                    cmd.moveFile(oldPath, newPath);
                    break;
                case TYPE_DELETE_FILE:
                    //note that this is for files only
                    cmd.deleteFile(oldPath);
                    break;
                case TYPE_RENAME_FILE:
                    System.out.println("renamed things");
                    cmd.renameFile(oldPath, newPath);
                    break;
                case TYPE_COPY_FILE:
                    cmd.copyFile(oldPath, newPath);
                    break;
                case TYPE_MAKE_DIRECTORY:
                    cmd.makeDirecotry(oldPath);
                    break;
                case TYPE_HIDE_FILE:
                    cmd.changeFileAttribute(oldPath, Command.ATTRIB_HIDE);
                    break;
                case TYPE_UNHIDE_FILE:
                    cmd.changeFileAttribute(oldPath, Command.ATTRIB_REVEAL);
                    break;
            }
        } catch (Exception e) {

        }
        finish();
    }

    @Override
    public String getDescription() {
        if (newPath != null) {
            return "File Action: " + type + " " + oldPath + " to " + newPath;
        } else {
            return "File Action: " + type + " " + oldPath;
        }
    }

}
