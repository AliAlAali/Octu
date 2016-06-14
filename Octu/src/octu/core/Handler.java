/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import octu.core.action.Action;
import octu.core.action.ScheduledAction;

/**
 *
 * @author Ali
 */
public class Handler {

    private ArrayList<Event> events;
    private ArrayList<Action> actions;
    private ArrayList<ScheduledAction> scheduled;
    private ArrayList<Action> loop;
    private ArrayList<Variable> variables;
    private OnActionOccurListener actionOccur;

    private Timer timer;

    private boolean hasStarted;
    private int index;
    private int schInd;

    public Handler() {
        events = new ArrayList<Event>();
        actions = new ArrayList<Action>();
        scheduled = new ArrayList<>();
        timer = new Timer();
    }

    /*
     <<<<<<< HEAD
     arrange event's actions according to their importance
     this must be called when start() called
     */
    private void arrangeActionsFromEvents(ArrayList<Event> from,
            ArrayList<Action> to) {
        //to be implemented later
        actions.clear();
        int por = 1;
        //basic implementation for testing purposes
        // this should be the number of available events
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < events.size(); i++) {

                Event event = events.get(i);
                if (event.getPor() == por) {
                    for (int j = 0; j < event.getActions().size(); j++) {
                        Action get = event.getActions().get(j);
                        if (!(get instanceof ScheduledAction)) {
                            actions.add(get);
                        } else {
                            scheduled.add((ScheduledAction) get);
                        }
                    }
                }

            }
            por++;
        }
    }

    /*
     used to start the series of actions
     */
    public void start() {

        stop();
        arrangeActionsFromEvents(events, actions);
//        for (int i = 0; i < actions.size(); i++) {
//            Action action = actions.get(i);
//            System.out.print(action.getDescription());
//        }
        hasStarted = true;
        timer.schedule(new Queqy(), 200);

    }

    private void run() {
        if (index < actions.size()) {
            Action action = actions.get(index);
            action.occur();
            if (actionOccur != null) {
                actionOccur.change(action);
            }
            while (!action.isFinished()) {

            }
            if (action.isFinished()) {
                index++;
            }
            if (index < actions.size()) {
                run();
            }
        }
    }
    /*
     used for stopping the series of actions
     */

    public void stop() {
        flush();
    }

    public boolean isAlive() {
        return hasStarted;
    }

    public boolean createVariable(String name, String type, Object initial) {

        //make sure no dulplicates found for the given name
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getName().equals(name)) {
                return false;
            }
        }
        Variable v = new Variable(name);
        v.setType(type);
        v.assignValue(initial);
        variables.add(v);
        return true;
    }

    public Action removeAction(int index) {
        return actions.remove(index);
    }

    public boolean removeEventAndActions(String evtName) {
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getPriority() == getEvent(evtName).getPor()) {
                actions.remove(i);
                i--;
            }

        }
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.getName().equals(evtName)) {
                events.remove(i);
                return true;
            }
        }

        return false;
    }

    public int findAction(int por, int inEventIndex) {
        int initial = -1;
        for (int i = 0; i < actions.size(); i++) {
            Action act = actions.get(i);
            if (act.getPriority() == por && initial == -1) {
                initial = i;
                break;
            }
        }
        if (initial > -1) {
            return initial + inEventIndex;
        }
        return -1;
    }

    public Event getEvent(String name) {
        for (int i = 0; i < events.size(); i++) {
            Event get = events.get(i);
            if (get.getName().equals(name)) {
                return get;
            }
        }
        return null;
    }

    public ArrayList<Event> getEvents() {
        return this.events;
    }

    public Event getEvent(int index) {
        if (events.size() > 0) {
            return events.get(index);
        }
        return null;
    }

    public boolean addEvent(Event evt) {
        //make sure not to add an event twice
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getName().equals(evt.getName())) {
                return false;
            }

        }

        events.add(evt);
        //Suspect that this is not supposed to be here
//        arrangeActionsFromEvents(events, loop);
        return true;
    }

    private ScheduledAction getAvailableScheduledAction() {
        if (schInd < scheduled.size()) {
            Calendar c = Calendar.getInstance();
            int hr = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            for (int i = 0; i < scheduled.size(); i++) {
                ScheduledAction scheduledAction = scheduled.get(i);
                if (scheduledAction.getHour() <= hr && scheduledAction.getMinute() <= min
                        && !scheduledAction.isFinished()) {
                    return scheduledAction;
                }
            }

        }
        return null;
    }

    public class Queqy extends TimerTask {

        @Override
        public void run() {
            //implement the actions here

            //basic implementation for testing only
            if (index < actions.size() || schInd < scheduled.size()) {
                Action action = null;

                action = getAvailableScheduledAction();
                if (action != null && !action.isOccuring()) {
                    action.occur();
                }
                if (action == null && index < actions.size()) {
                    action = actions.get(index);
                    if (!action.isOccuring()) {
                        action.occur();
                        if (actionOccur != null) {
                            actionOccur.change(action);
                        }
                    } else {
                        if (actionOccur != null) {
                            actionOccur.change(null);
                        }
                    }
                }
                if (action != null && action.isFinished()) {
                    if (action instanceof ScheduledAction) {
                        schInd++;
                    } else {
                        index++;
                    }
                }
                timer.schedule(new Queqy(), 200);
            }
        }

    }

    public void flush() {
        index = 0;
        hasStarted = false;
        timer.cancel();
        timer = new Timer();

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            for (int j = 0; j < event.getActions().size(); j++) {
                event.getActions().get(j).flush();
            }
        }
    }

    public void setOnActoinOccurListener(OnActionOccurListener change) {
        this.actionOccur = change;

    }

    public interface OnActionOccurListener {

        public void change(Action act);
    }
}
