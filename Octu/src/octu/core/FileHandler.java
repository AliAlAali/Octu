/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.filechooser.FileFilter;
import octu.core.action.*;

/**
 *
 * @author Ali
 */
public class FileHandler {
    /*
     this class is supposed to extract information of a file 
     or compress them into one.
     */

    public static final String COMP = "<component>";
    public static final String POR = "<poriority>";
    public static final String NAME = "<name>";
    public static final String ARG = "<arguement>";
    public static final String ACT = "<action>";
    public static final String EVT = "<event>";
    public static final String SUB_ACT = "<sub-action>";

    public static FileFilter getFileFilter() {
        //create a file-filter for saving and opening sessions
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory() || f.getName().endsWith(".oct")) {
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

    public static FileFilter getDirectoryFilter() {
        //create a file-filter for saving and opening sessions
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Directory";
            }
        };

        return filter;
    }

    public static FileFilter getAbsoluteFilter() {
        //create a file-filter for saving and opening sessions
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return true;
            }

            @Override
            public String getDescription() {
                return "All";
            }
        };

        return filter;
    }

    /*
     path must include the file name with extension
     */
    public void saveFile(Handler handler, String path) {
        String str = generateText(handler);
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            bw.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private String generateText(Handler handler) {
        String s = "";
        ArrayList<Event> events = handler.getEvents();
        ArrayList<Action> actions;
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            s += EVT + NAME + event.getName() + NAME + POR + event.getPor() + POR + "\n\t";
            actions = event.getActions();
            for (int j = 0; j < actions.size(); j++) {
                Action action = actions.get(j);
                s += ACT + POR + action.getPriority() + POR + NAME + action.getClass().getSimpleName() + NAME
                        + action.getArguement();
                if (action instanceof ScheduledAction) {

                    for (int k = 0; k < ((ScheduledAction) action).getActions().size(); k++) {
                        Action sub = ((ScheduledAction) action).getActions().get(k);
                        s += SUB_ACT + POR + sub.getPriority() + POR + NAME + sub.getClass().getSimpleName() + NAME
                                + sub.getArguement() + SUB_ACT + "\n";
                    }
                }
                s += ACT + "\n\t";

            }
            s += "\r" + EVT + "\n";
        }
        System.out.println(s);
        return s;
    }

    private String getPart(String text, String part) {
        //System.out.println(text + "  " + part);
        int pos = text.indexOf(part);
        int nxtPos = text.indexOf(part, pos + 1);
        return text.substring(pos + part.length(), nxtPos);
    }

    public void open(File file, Handler handler, JList event, JList action) {
        String str = readFile(file);
        ArrayList<Event> evts = extractData(str);
        for (int i = 0; i < evts.size(); i++) {
            Event event1 = evts.get(i);
            handler.addEvent(event1);
        }
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < evts.size(); i++) {
            Event event1 = evts.get(i);
            model.addElement(event1.getName());
        }
        event.setModel(model);
        event.setSelectedIndex(event.getLastVisibleIndex());
        model = new DefaultListModel<>();
        ArrayList<Action> act = evts.get(event.getLastVisibleIndex()).getActions();
        for (int i = 0; i < act.size(); i++) {
            model.addElement(act.get(i).getDescription());
        }
        action.setModel(model);

    }

    private String readFile(File file) {
        String str = null;
        FileReader reader;
        BufferedReader br = null;
        try {
            reader = new FileReader(file);
            br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                str += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str;
    }

    /*
     to support new actions, this method should have instanceof of the new action
     */
    private ArrayList<Event> extractData(String data) {
        ArrayList<Event> events = new ArrayList<Event>();
        ArrayList<Action> actions = new ArrayList<Action>();
        ArrayList<Action> subs = new ArrayList<Action>();

        int eventNum = count(data, EVT);
        Event evt = null;
        Action act = null;

        for (int i = 0; i < eventNum; i++) {
            /*cutting the nessary part, then trimming it from
             the original string.
             */
            String tempE = getPart(data, EVT);
            data = data.substring(data.indexOf(tempE) + tempE.length() + EVT.length());
            //creating the event
            evt = new Event(inte(getPart(tempE, POR)));
            evt.setName(getPart(tempE, NAME));

            int actNum = count(tempE, ACT);
            for (int j = 0; j < actNum; j++) {
                //cutting the nessary piece of String
                String tempA = getPart(tempE, ACT);
                tempE = tempE.substring(tempE.indexOf(tempA) + tempA.length() + ACT.length());
                //creating an action
                String actName = getPart(tempA, NAME);
                int actPor = inte(getPart(tempA, POR));
                String[] args = getArguements(tempA);

                if (actName.equals(DelayAction.class.getSimpleName())) {
                    act = new DelayAction(actPor, Long.parseLong(args[0]));
                } else if (actName.equals(FileAction.class.getSimpleName())) {
                    act = new FileAction(actPor, args[0], args[1]);
                    if (args[2].equals("null")) {
                        ((FileAction) act).setNewPath(null);
                    } else {
                        ((FileAction) act).setNewPath(args[2]);
                    }
                } else if (actName.equals(KeyStrokeAction.class.getSimpleName())) {
                    act = new KeyStrokeAction(actPor, args[0], args[1]);
                } else if (actName.equals(LunchAppAction.class.getSimpleName())) {
                    act = new LunchAppAction(actPor, args[0]);
                } else if (actName.equals(MouseAction.class.getSimpleName())) {
                    act = new MouseAction(actPor, args[0], args[1]);
                    if (args[2].equals("")) {
                        ((MouseAction) act).setX(0);
                    } else {
                        ((MouseAction) act).setX(inte(args[2]));
                    }
                    if (args[3].isEmpty()) {
                        ((MouseAction) act).setY(0);
                    } else {
                        ((MouseAction) act).setY(inte(args[3]));
                    }
                } else if (actName.equals(ScheduledAction.class.getSimpleName())) {
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR_OF_DAY, inte(args[0]));
                    c.set(Calendar.MINUTE, inte(args[1]));
                    act = new ScheduledAction(actPor, c, null, null);
                } else if (actName.equals(ShutDownAction.class.getSimpleName())) {
                    act = new ShutDownAction(actPor, args[0]);
                } else if (actName.equals(StopApplicationAction.class.getSimpleName())) {
                    act = new StopApplicationAction(actPor);
                }

                evt.addAction(act);
                act = null;
            }
            events.add(evt);
            evt = null;

        }
        return events;
    }

    private String[] getArguements(String p) {
        String args[] = new String[count(p, ARG)];
        for (int i = 0; i < args.length; i++) {
            int pos = p.indexOf(ARG);
            int nxt = p.indexOf(ARG, pos + 1);
            args[i] = p.substring(pos + ARG.length(), nxt);
            p = p.substring(args[i].length() + 2 * ARG.length());
        }

        return args;
    }

    private int count(String str, String part) {
        int count = 0;
        int pos = str.indexOf(part);
        while (pos != -1) {
            count++;
            pos = str.indexOf(part, pos + 1);
        }

        return count / 2;
    }

    private int inte(String a) {
        return Integer.parseInt(a);
    }

    public void makeTextFile() {
        /*
         implement later
         */
    }
}
