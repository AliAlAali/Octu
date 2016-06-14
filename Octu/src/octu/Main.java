/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import octu.core.Command;
import octu.core.Event;
import octu.core.FileHandler;
import octu.core.Handler;
import octu.core.action.Action;
import octu.core.action.DelayAction;
import octu.core.action.FileAction;
import octu.core.action.KeyStrokeAction;
import octu.core.action.LooperAction;
import octu.core.action.LunchAppAction;
import octu.core.action.MouseAction;
import octu.core.action.ScheduledAction;
import octu.core.action.ShutDownAction;
import octu.core.action.StopApplicationAction;

/**
 *
 * @author Ali
 */
public class Main extends javax.swing.JFrame {

    private Dimension screen;
    private Timer timer;

    private Calendar c;
    private List<BufferedImage> icons;
    private Handler handler;

    private int oneInputID; //for managing variety of actions using one dialog
    private int lastSelectedActionl; //loop and schedule events
    private String mouseButtonSelection; //used to determine which button is selected
    private String selectedEvent;
    private boolean edit;

    /**
     * Creates new form Main
     */
    public Main() {
        //initializing the handler for events and actions
        handler = new Handler();
        //adding images to the icons for the JVM to select from
        icons = new ArrayList<BufferedImage>();
        icons.add(getImage("Octo_16.png"));
        icons.add(getImage("Octo_32.png"));
        icons.add(getImage("Octo_64.png"));
        icons.add(getImage("Octo_128.png"));
        initComponents();
        //to show in the middle of the screen
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) screen.getWidth() / 2 - getWidth() / 2, (int) screen.getHeight() / 2 - getHeight() / 2, getWidth(), getHeight());
        //preventing accedintal closing
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                showConfirmDialog("Are you sure leaving the program?");
            }
        });

        //change the graph according to action changes
        handler.setOnActoinOccurListener(new Handler.OnActionOccurListener() {
            @Override
            public void change(Action act) {
                if (act == null || act instanceof DelayAction) {
//                   for (int i = 0; i < ((DelayAction)act).getDelay()/1000; i++) {
//                       graph1.peak(false);
//                   }
                    graph1.peak(false);
                    if (act == null) {
                        return;
                    }
                } else if (act instanceof LooperAction) {

                } else {
                    graph1.peak(true);
                }
                ((DefaultListModel<String>) historyList.getModel()).addElement(act.getDescription());
            }
        });

        //for the time and date
        c = Calendar.getInstance();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                //update system time
                c = Calendar.getInstance();
                systemTime.setText(c.getTime().toString());

            }

        }, Math.min(60, 60 - c.get(Calendar.SECOND)), 1000);

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                //update mouse position
                if (!control_freeze.isSelected()) {
                    Point p = MouseInfo.getPointerInfo().getLocation();
                    control_mouseX.setText(String.valueOf(p.getX()));
                    control_mouseY.setText(String.valueOf(p.getY()));
                }
            }
        }, 0, 50);
        //adding filters for the save-open dialogs
        FileFilter filter = FileHandler.getFileFilter();
        saveChooser.setFileFilter(filter);
        openChooser.setFileFilter(filter);

        //set keylistener for enter
        oneInTextFeild.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    oneInOk.doClick();
                }
            }

        });

        //listening to global keyevents
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                //listening for a freeze command
                if (e.getKeyCode() == KeyEvent.VK_F3) {
                    control_freeze.setSelected(true);
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    //for stopping the application
                    stopButton.doClick();
                } else if (e.getKeyCode() == KeyEvent.VK_F1) {
                    runButton.doClick();
                }
                return false;
            }
        });
    }

    private BufferedImage getImage(String name) {
        try {
            return ImageIO.read(new File(getClass().getClassLoader().getResource("resources/" + name).getFile()));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void showConfirmDialog(String message) {
        if (JOptionPane.showConfirmDialog(Main.this, message, "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
//            //BufferedImage image = ImageIO.read(new File(getClass().getClassLoader().getResource("MouseDrawing.png")));
//            g.drawLine(0, 0, 100, 100);
//        }
//        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EventSelector = new javax.swing.JDialog();
        bt_event_start = new javax.swing.JButton();
        bt_event_going = new javax.swing.JButton();
        bt_event_trigger = new javax.swing.JButton();
        bt_event_loop = new javax.swing.JButton();
        bt_event_schedule = new javax.swing.JButton();
        saveDialog = new javax.swing.JDialog();
        saveChooser = new javax.swing.JFileChooser();
        openDialog = new javax.swing.JDialog();
        openChooser = new javax.swing.JFileChooser();
        newActionDialog = new javax.swing.JDialog();
        mouseActionButton = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        lunchAppButton = new javax.swing.JButton();
        actionFile = new javax.swing.JButton();
        shutdownButton = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        delayActionButton = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        newAction_looper = new javax.swing.JButton();
        FileMenu = new javax.swing.JPopupMenu();
        moveFile = new javax.swing.JMenuItem();
        deleteFile = new javax.swing.JMenuItem();
        renameFile = new javax.swing.JMenuItem();
        copyFile = new javax.swing.JMenuItem();
        makeDir = new javax.swing.JMenuItem();
        changeAttrib = new javax.swing.JMenuItem();
        ShutDownMenu = new javax.swing.JPopupMenu();
        logOff = new javax.swing.JMenuItem();
        restartCom = new javax.swing.JMenuItem();
        shutCom = new javax.swing.JMenuItem();
        MouseActionMenu = new javax.swing.JPopupMenu();
        clickMouse = new javax.swing.JMenuItem();
        pressMouse = new javax.swing.JMenuItem();
        releaseAction = new javax.swing.JMenuItem();
        scrollMouse = new javax.swing.JMenuItem();
        moveMouse = new javax.swing.JMenuItem();
        MouseActionDialog = new javax.swing.JDialog();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        imageView1 = new octu.graphics.ImageView();
        mouseActionLeft = new javax.swing.JLabel();
        mouseActionRight = new javax.swing.JLabel();
        mouseActionMiddle = new javax.swing.JLabel();
        CoordinateInputDialog = new javax.swing.JDialog();
        cordOk = new javax.swing.JButton();
        cordCancel = new javax.swing.JButton();
        cordX = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cordY = new javax.swing.JTextField();
        OneValueInput = new javax.swing.JDialog();
        oneInText = new javax.swing.JLabel();
        oneInTextFeild = new javax.swing.JTextField();
        oneInOk = new javax.swing.JButton();
        oneInCancel = new javax.swing.JButton();
        onePathDialog = new javax.swing.JDialog();
        onePath_text = new javax.swing.JTextField();
        onePath_select = new javax.swing.JButton();
        onePath_ok = new javax.swing.JButton();
        onePath_cancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        FileSelector = new javax.swing.JDialog();
        fileChooser_chooser = new javax.swing.JFileChooser();
        twoPathDialog = new javax.swing.JDialog();
        jSeparator2 = new javax.swing.JSeparator();
        twoPath_cancel = new javax.swing.JButton();
        twoPath_ok = new javax.swing.JButton();
        twoPath_oldPath = new javax.swing.JButton();
        twoPath_oldText = new javax.swing.JTextField();
        twoPath_newPath = new javax.swing.JButton();
        twoPath_newText = new javax.swing.JTextField();
        KeystrokeMenu = new javax.swing.JPopupMenu();
        menu_type = new javax.swing.JMenuItem();
        menu_vk_press = new javax.swing.JMenu();
        menu_vk_shift = new javax.swing.JMenuItem();
        menu_vk_enter = new javax.swing.JMenuItem();
        menu_vk_del = new javax.swing.JMenuItem();
        menu_vk_ctrl = new javax.swing.JMenuItem();
        menu_vk_esc = new javax.swing.JMenuItem();
        menu_vk_letter = new javax.swing.JMenuItem();
        menu_vk_release = new javax.swing.JMenu();
        menu_vk_shiftR = new javax.swing.JMenuItem();
        menu_vk_enterR = new javax.swing.JMenuItem();
        menu_vk_delR = new javax.swing.JMenuItem();
        menu_vk_ctrlR = new javax.swing.JMenuItem();
        menu_vk_escR = new javax.swing.JMenuItem();
        menu_vk_letterR = new javax.swing.JMenuItem();
        subActionEditor = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        subAction_list = new javax.swing.JList();
        subAction_add = new javax.swing.JButton();
        subAction_edit = new javax.swing.JButton();
        subAction_remove = new javax.swing.JButton();
        subAction_up = new javax.swing.JButton();
        subAction_down = new javax.swing.JButton();
        subAction_submit = new javax.swing.JButton();
        subAction_cancel = new javax.swing.JButton();
        newSubActionDialog = new javax.swing.JDialog();
        newSubAct_mouse = new javax.swing.JButton();
        newSubAct_keystroke = new javax.swing.JButton();
        newSubAct_lunch = new javax.swing.JButton();
        newSubAct_file = new javax.swing.JButton();
        newSubAct_shutdown = new javax.swing.JButton();
        newSubAct_varialb = new javax.swing.JButton();
        newSubAct_delay = new javax.swing.JButton();
        newSubAct_stop = new javax.swing.JButton();
        subFileMenu = new javax.swing.JPopupMenu();
        subFileMenu_move = new javax.swing.JMenuItem();
        subFileMenu_del = new javax.swing.JMenuItem();
        subFileMenu_rename = new javax.swing.JMenuItem();
        subFileMenu_copy = new javax.swing.JMenuItem();
        subFileMenu_makeDir = new javax.swing.JMenuItem();
        subFileMenu_changeAtrib = new javax.swing.JMenuItem();
        subShutMenu = new javax.swing.JPopupMenu();
        subShutMenu_log = new javax.swing.JMenuItem();
        subShutMenu_restart = new javax.swing.JMenuItem();
        subShutMenu_shutcom = new javax.swing.JMenuItem();
        subMouseMenu = new javax.swing.JPopupMenu();
        subMouseMenu_click = new javax.swing.JMenuItem();
        subMouseMenu_press = new javax.swing.JMenuItem();
        subMouseMenu_release = new javax.swing.JMenuItem();
        subMouseMenu_scroll = new javax.swing.JMenuItem();
        subMouseMenu_move = new javax.swing.JMenuItem();
        subKeyMenu = new javax.swing.JPopupMenu();
        subKeyMenu_type = new javax.swing.JMenuItem();
        subKeyMenu_press = new javax.swing.JMenu();
        subKeyMenu_press_shift = new javax.swing.JMenuItem();
        subKeyMenu_press_enter = new javax.swing.JMenuItem();
        subKeyMenu_press_del = new javax.swing.JMenuItem();
        subKeyMenu_press_ctrl = new javax.swing.JMenuItem();
        subKeyMenu_press_esc = new javax.swing.JMenuItem();
        subKeyMenu_press_letter = new javax.swing.JMenuItem();
        subKeyMenu_release = new javax.swing.JMenu();
        subKeyMenu_release_shfit = new javax.swing.JMenuItem();
        subKeyMenu_release_enter = new javax.swing.JMenuItem();
        subKeyMenu_release_del = new javax.swing.JMenuItem();
        subKeyMenu_release_ctrl = new javax.swing.JMenuItem();
        subKeyMenu_release_esc = new javax.swing.JMenuItem();
        subKeyMenu_release_letter = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        eventList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        actionList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        newActionButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        systemTime = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        control_mouseX = new javax.swing.JTextField();
        control_mouseY = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        control_freeze = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        historyList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        graph1 = new octu.graphics.Graph();
        runButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        aboutMenu_help = new javax.swing.JMenuItem();
        aboutMenu_dev = new javax.swing.JMenuItem();

        EventSelector.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        EventSelector.setTitle("New Event");
        EventSelector.setIconImages(icons);

        bt_event_start.setText("onStart");
        bt_event_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_event_startActionPerformed(evt);
            }
        });

        bt_event_going.setText("onGoing");
        bt_event_going.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_event_goingActionPerformed(evt);
            }
        });

        bt_event_trigger.setText("Trigger");
        bt_event_trigger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_event_triggerActionPerformed(evt);
            }
        });

        bt_event_loop.setText("Loop");
        bt_event_loop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_event_loopActionPerformed(evt);
            }
        });

        bt_event_schedule.setText("Schedule");
        bt_event_schedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_event_scheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EventSelectorLayout = new javax.swing.GroupLayout(EventSelector.getContentPane());
        EventSelector.getContentPane().setLayout(EventSelectorLayout);
        EventSelectorLayout.setHorizontalGroup(
            EventSelectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventSelectorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EventSelectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_event_trigger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_event_going, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_event_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(EventSelectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_event_schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_event_loop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EventSelectorLayout.setVerticalGroup(
            EventSelectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventSelectorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EventSelectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_event_start)
                    .addComponent(bt_event_loop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EventSelectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_event_going)
                    .addComponent(bt_event_schedule))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_event_trigger)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        saveDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        saveDialog.setTitle("Save Session");
        saveDialog.setIconImages(icons);

        saveChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);

        javax.swing.GroupLayout saveDialogLayout = new javax.swing.GroupLayout(saveDialog.getContentPane());
        saveDialog.getContentPane().setLayout(saveDialogLayout);
        saveDialogLayout.setHorizontalGroup(
            saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        saveDialogLayout.setVerticalGroup(
            saveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        openDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        openDialog.setTitle("Open Previous Session");
        openDialog.setIconImages(icons);

        javax.swing.GroupLayout openDialogLayout = new javax.swing.GroupLayout(openDialog.getContentPane());
        openDialog.getContentPane().setLayout(openDialogLayout);
        openDialogLayout.setHorizontalGroup(
            openDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, openDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(openChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        openDialogLayout.setVerticalGroup(
            openDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, openDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(openChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        newActionDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        newActionDialog.setTitle("Action");
        newActionDialog.setIconImages(icons);

        mouseActionButton.setText("Mouse");
        mouseActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mouseActionButtonActionPerformed(evt);
            }
        });

        jButton13.setText("Keystroke");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        lunchAppButton.setText("Lunch App");
        lunchAppButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunchAppButtonActionPerformed(evt);
            }
        });

        actionFile.setText("File");
        actionFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionFileActionPerformed(evt);
            }
        });

        shutdownButton.setText("Shutdown");
        shutdownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutdownButtonActionPerformed(evt);
            }
        });

        jButton17.setText("Variable");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        delayActionButton.setText("Delay");
        delayActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delayActionButtonActionPerformed(evt);
            }
        });

        jButton15.setText("Stop");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        newAction_looper.setText("Looper");
        newAction_looper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction_looperActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newActionDialogLayout = new javax.swing.GroupLayout(newActionDialog.getContentPane());
        newActionDialog.getContentPane().setLayout(newActionDialogLayout);
        newActionDialogLayout.setHorizontalGroup(
            newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newActionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newActionDialogLayout.createSequentialGroup()
                        .addGroup(newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lunchAppButton, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(mouseActionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shutdownButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(actionFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(newActionDialogLayout.createSequentialGroup()
                        .addGroup(newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(newAction_looper, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(delayActionButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        newActionDialogLayout.setVerticalGroup(
            newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newActionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mouseActionButton)
                    .addComponent(actionFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(shutdownButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lunchAppButton)
                    .addComponent(jButton17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delayActionButton)
                    .addComponent(jButton15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newAction_looper)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        moveFile.setText("Move File");
        moveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveFileActionPerformed(evt);
            }
        });
        FileMenu.add(moveFile);

        deleteFile.setText("Delete File");
        deleteFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFileActionPerformed(evt);
            }
        });
        FileMenu.add(deleteFile);

        renameFile.setText("Rename File");
        renameFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameFileActionPerformed(evt);
            }
        });
        FileMenu.add(renameFile);

        copyFile.setText("Copy File");
        copyFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyFileActionPerformed(evt);
            }
        });
        FileMenu.add(copyFile);

        makeDir.setText("Make Directory");
        makeDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeDirActionPerformed(evt);
            }
        });
        FileMenu.add(makeDir);

        changeAttrib.setText("Hide/Unhide File");
        changeAttrib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeAttribActionPerformed(evt);
            }
        });
        FileMenu.add(changeAttrib);

        logOff.setText("Log Off");
        logOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOffActionPerformed(evt);
            }
        });
        ShutDownMenu.add(logOff);

        restartCom.setText("Restart");
        restartCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartComActionPerformed(evt);
            }
        });
        ShutDownMenu.add(restartCom);

        shutCom.setText("Shutdown");
        shutCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutComActionPerformed(evt);
            }
        });
        ShutDownMenu.add(shutCom);

        clickMouse.setText("Click");
        clickMouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickMouseActionPerformed(evt);
            }
        });
        MouseActionMenu.add(clickMouse);

        pressMouse.setText("Press");
        pressMouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressMouseActionPerformed(evt);
            }
        });
        MouseActionMenu.add(pressMouse);

        releaseAction.setText("Release");
        releaseAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseActionActionPerformed(evt);
            }
        });
        MouseActionMenu.add(releaseAction);

        scrollMouse.setText("Scroll");
        scrollMouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scrollMouseActionPerformed(evt);
            }
        });
        MouseActionMenu.add(scrollMouse);

        moveMouse.setText("Move");
        moveMouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveMouseActionPerformed(evt);
            }
        });
        MouseActionMenu.add(moveMouse);

        MouseActionDialog.setTitle("Select Mouse Button");
        MouseActionDialog.setIconImages(icons);

        mouseActionLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mouseActionLeft.setText("Left");
        mouseActionLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseActionLeftMouseClicked(evt);
            }
        });

        mouseActionRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mouseActionRight.setText("Right");
        mouseActionRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseActionRightMouseClicked(evt);
            }
        });
        mouseActionRight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mouseActionRightKeyPressed(evt);
            }
        });

        mouseActionMiddle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mouseActionMiddle.setText("Middle");
        mouseActionMiddle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseActionMiddleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imageView1Layout = new javax.swing.GroupLayout(imageView1);
        imageView1.setLayout(imageView1Layout);
        imageView1Layout.setHorizontalGroup(
            imageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageView1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(imageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mouseActionMiddle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(imageView1Layout.createSequentialGroup()
                        .addComponent(mouseActionLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mouseActionRight, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        imageView1Layout.setVerticalGroup(
            imageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageView1Layout.createSequentialGroup()
                .addComponent(mouseActionMiddle)
                .addGap(46, 46, 46)
                .addGroup(imageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mouseActionLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mouseActionRight, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addComponent(imageView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jLayeredPane1.setLayer(imageView1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout MouseActionDialogLayout = new javax.swing.GroupLayout(MouseActionDialog.getContentPane());
        MouseActionDialog.getContentPane().setLayout(MouseActionDialogLayout);
        MouseActionDialogLayout.setHorizontalGroup(
            MouseActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        MouseActionDialogLayout.setVerticalGroup(
            MouseActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MouseActionDialogLayout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        CoordinateInputDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        CoordinateInputDialog.setTitle("Coordinates");
        CoordinateInputDialog.setIconImages(icons);

        cordOk.setText("Ok");
        cordOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cordOkActionPerformed(evt);
            }
        });

        cordCancel.setText("Cancel");
        cordCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cordCancelActionPerformed(evt);
            }
        });

        jLabel5.setText("X");

        jLabel6.setText("Y");

        javax.swing.GroupLayout CoordinateInputDialogLayout = new javax.swing.GroupLayout(CoordinateInputDialog.getContentPane());
        CoordinateInputDialog.getContentPane().setLayout(CoordinateInputDialogLayout);
        CoordinateInputDialogLayout.setHorizontalGroup(
            CoordinateInputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoordinateInputDialogLayout.createSequentialGroup()
                .addGroup(CoordinateInputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CoordinateInputDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cordX, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cordY))
                    .addGroup(CoordinateInputDialogLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(cordCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cordOk, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CoordinateInputDialogLayout.setVerticalGroup(
            CoordinateInputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoordinateInputDialogLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(CoordinateInputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cordX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cordY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CoordinateInputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cordOk)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CoordinateInputDialogLayout.createSequentialGroup()
                        .addComponent(cordCancel)
                        .addContainerGap())))
        );

        OneValueInput.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        OneValueInput.setTitle("Input");
        OneValueInput.setIconImages(icons);

        oneInText.setText("Enter delay in milliseconds");

        oneInTextFeild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneInTextFeildActionPerformed(evt);
            }
        });

        oneInOk.setText("Ok");
        oneInOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneInOkActionPerformed(evt);
            }
        });

        oneInCancel.setText("Cancel");
        oneInCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneInCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OneValueInputLayout = new javax.swing.GroupLayout(OneValueInput.getContentPane());
        OneValueInput.getContentPane().setLayout(OneValueInputLayout);
        OneValueInputLayout.setHorizontalGroup(
            OneValueInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OneValueInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OneValueInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(OneValueInputLayout.createSequentialGroup()
                        .addComponent(oneInCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oneInOk, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OneValueInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(oneInText, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(oneInTextFeild, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OneValueInputLayout.setVerticalGroup(
            OneValueInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OneValueInputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(oneInText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oneInTextFeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OneValueInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oneInOk)
                    .addComponent(oneInCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        onePathDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        onePathDialog.setTitle("Select Path");
        onePathDialog.setIconImages(icons);

        onePath_select.setText("Path");
        onePath_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onePath_selectActionPerformed(evt);
            }
        });

        onePath_ok.setText("Ok");
        onePath_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onePath_okActionPerformed(evt);
            }
        });

        onePath_cancel.setText("Cancel");
        onePath_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onePath_cancelActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout onePathDialogLayout = new javax.swing.GroupLayout(onePathDialog.getContentPane());
        onePathDialog.getContentPane().setLayout(onePathDialogLayout);
        onePathDialogLayout.setHorizontalGroup(
            onePathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onePathDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(onePath_text, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(onePath_select)
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(onePathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(onePath_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(onePath_ok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        onePathDialogLayout.setVerticalGroup(
            onePathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onePathDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(onePathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(onePathDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(onePathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(onePath_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(onePath_select))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, onePathDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(onePath_ok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(onePath_cancel))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        FileSelector.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        FileSelector.setTitle("Select a file");
        FileSelector.setAlwaysOnTop(true);
        FileSelector.setIconImages(icons);

        fileChooser_chooser.setDialogTitle("");
        fileChooser_chooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser_chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooser_chooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FileSelectorLayout = new javax.swing.GroupLayout(FileSelector.getContentPane());
        FileSelector.getContentPane().setLayout(FileSelectorLayout);
        FileSelectorLayout.setHorizontalGroup(
            FileSelectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        FileSelectorLayout.setVerticalGroup(
            FileSelectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        twoPathDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        twoPathDialog.setIconImages(icons);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        twoPath_cancel.setText("Cancel");
        twoPath_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoPath_cancelActionPerformed(evt);
            }
        });

        twoPath_ok.setText("Ok");
        twoPath_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoPath_okActionPerformed(evt);
            }
        });

        twoPath_oldPath.setText("Path");
        twoPath_oldPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoPath_oldPathActionPerformed(evt);
            }
        });

        twoPath_oldText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoPath_oldTextActionPerformed(evt);
            }
        });

        twoPath_newPath.setText("New Path");
        twoPath_newPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoPath_newPathActionPerformed(evt);
            }
        });

        twoPath_newText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoPath_newTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout twoPathDialogLayout = new javax.swing.GroupLayout(twoPathDialog.getContentPane());
        twoPathDialog.getContentPane().setLayout(twoPathDialogLayout);
        twoPathDialogLayout.setHorizontalGroup(
            twoPathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(twoPathDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(twoPathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(twoPathDialogLayout.createSequentialGroup()
                        .addComponent(twoPath_oldText, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(twoPath_oldPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(twoPathDialogLayout.createSequentialGroup()
                        .addComponent(twoPath_newText, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(twoPath_newPath)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(twoPathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(twoPath_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(twoPath_ok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        twoPathDialogLayout.setVerticalGroup(
            twoPathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(twoPathDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(twoPathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(twoPathDialogLayout.createSequentialGroup()
                        .addGroup(twoPathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(twoPath_oldText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(twoPath_oldPath))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(twoPathDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(twoPath_newText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(twoPath_newPath)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, twoPathDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(twoPath_ok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(twoPath_cancel))
                    .addComponent(jSeparator2))
                .addContainerGap())
        );

        menu_type.setText("Type");
        menu_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_typeActionPerformed(evt);
            }
        });
        KeystrokeMenu.add(menu_type);

        menu_vk_press.setText("Press");

        menu_vk_shift.setText("Shift");
        menu_vk_shift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_shiftActionPerformed(evt);
            }
        });
        menu_vk_press.add(menu_vk_shift);

        menu_vk_enter.setText("Enter");
        menu_vk_enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_enterActionPerformed(evt);
            }
        });
        menu_vk_press.add(menu_vk_enter);

        menu_vk_del.setText("Delete");
        menu_vk_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_delActionPerformed(evt);
            }
        });
        menu_vk_press.add(menu_vk_del);

        menu_vk_ctrl.setText("Ctrl");
        menu_vk_ctrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_ctrlActionPerformed(evt);
            }
        });
        menu_vk_press.add(menu_vk_ctrl);

        menu_vk_esc.setText("ESC");
        menu_vk_esc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_escActionPerformed(evt);
            }
        });
        menu_vk_press.add(menu_vk_esc);

        menu_vk_letter.setText("Letter");
        menu_vk_letter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_letterActionPerformed(evt);
            }
        });
        menu_vk_press.add(menu_vk_letter);

        KeystrokeMenu.add(menu_vk_press);

        menu_vk_release.setText("Release");

        menu_vk_shiftR.setText("Shift");
        menu_vk_shiftR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_shiftRActionPerformed(evt);
            }
        });
        menu_vk_release.add(menu_vk_shiftR);

        menu_vk_enterR.setText("Enter");
        menu_vk_enterR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_enterRActionPerformed(evt);
            }
        });
        menu_vk_release.add(menu_vk_enterR);

        menu_vk_delR.setText("Delete");
        menu_vk_delR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_delRActionPerformed(evt);
            }
        });
        menu_vk_release.add(menu_vk_delR);

        menu_vk_ctrlR.setText("Ctrl");
        menu_vk_ctrlR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_ctrlRActionPerformed(evt);
            }
        });
        menu_vk_release.add(menu_vk_ctrlR);

        menu_vk_escR.setText("ESC");
        menu_vk_escR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_escRActionPerformed(evt);
            }
        });
        menu_vk_release.add(menu_vk_escR);

        menu_vk_letterR.setText("Letter");
        menu_vk_letterR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_vk_letterRActionPerformed(evt);
            }
        });
        menu_vk_release.add(menu_vk_letterR);

        KeystrokeMenu.add(menu_vk_release);

        subActionEditor.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        subActionEditor.setTitle("Sub Action Editor");
        subActionEditor.setIconImages(icons);
        subActionEditor.setModalityType(null);

        subAction_list.setModel(new DefaultListModel<String>());
        jScrollPane4.setViewportView(subAction_list);

        subAction_add.setText("+ Action");
        subAction_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAction_addActionPerformed(evt);
            }
        });

        subAction_edit.setText("Edit");
        subAction_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAction_editActionPerformed(evt);
            }
        });

        subAction_remove.setText("- Action");
        subAction_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAction_removeActionPerformed(evt);
            }
        });

        subAction_up.setText("Up");
        subAction_up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAction_upActionPerformed(evt);
            }
        });

        subAction_down.setText("Down");
        subAction_down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAction_downActionPerformed(evt);
            }
        });

        subAction_submit.setText("Submit");

        subAction_cancel.setText("Cancel");
        subAction_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAction_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout subActionEditorLayout = new javax.swing.GroupLayout(subActionEditor.getContentPane());
        subActionEditor.getContentPane().setLayout(subActionEditorLayout);
        subActionEditorLayout.setHorizontalGroup(
            subActionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subActionEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subActionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(subActionEditorLayout.createSequentialGroup()
                        .addComponent(subAction_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subAction_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subActionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(subAction_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subAction_remove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subAction_up, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subAction_down, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subAction_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        subActionEditorLayout.setVerticalGroup(
            subActionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subActionEditorLayout.createSequentialGroup()
                .addGroup(subActionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(subActionEditorLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(subAction_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subAction_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subAction_remove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(subAction_up)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subAction_down))
                    .addGroup(subActionEditorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(subActionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(subAction_submit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subAction_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        newSubActionDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        newSubActionDialog.setTitle("Action");
        newSubActionDialog.setIconImages(icons);
        newSubActionDialog.setModalityType(null);

        newSubAct_mouse.setText("Mouse");
        newSubAct_mouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubAct_mouseActionPerformed(evt);
            }
        });

        newSubAct_keystroke.setText("Keystroke");
        newSubAct_keystroke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubAct_keystrokeActionPerformed(evt);
            }
        });

        newSubAct_lunch.setText("Lunch App");
        newSubAct_lunch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubAct_lunchActionPerformed(evt);
            }
        });

        newSubAct_file.setText("File");
        newSubAct_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubAct_fileActionPerformed(evt);
            }
        });

        newSubAct_shutdown.setText("Shutdown");
        newSubAct_shutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubAct_shutdownActionPerformed(evt);
            }
        });

        newSubAct_varialb.setText("Variable");
        newSubAct_varialb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubAct_varialbActionPerformed(evt);
            }
        });

        newSubAct_delay.setText("Delay");
        newSubAct_delay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubAct_delayActionPerformed(evt);
            }
        });

        newSubAct_stop.setText("Stop");
        newSubAct_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSubAct_stopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newSubActionDialogLayout = new javax.swing.GroupLayout(newSubActionDialog.getContentPane());
        newSubActionDialog.getContentPane().setLayout(newSubActionDialogLayout);
        newSubActionDialogLayout.setHorizontalGroup(
            newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newSubActionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newSubActionDialogLayout.createSequentialGroup()
                        .addGroup(newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(newSubAct_lunch, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(newSubAct_mouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newSubAct_keystroke, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newSubAct_shutdown, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(newSubAct_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newSubAct_varialb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(newSubActionDialogLayout.createSequentialGroup()
                        .addComponent(newSubAct_delay, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newSubAct_stop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        newSubActionDialogLayout.setVerticalGroup(
            newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newSubActionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSubAct_mouse)
                    .addComponent(newSubAct_file))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSubAct_keystroke)
                    .addComponent(newSubAct_shutdown))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSubAct_lunch)
                    .addComponent(newSubAct_varialb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newSubActionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSubAct_delay)
                    .addComponent(newSubAct_stop))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subFileMenu_move.setText("Move File");
        subFileMenu_move.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subFileMenu_moveActionPerformed(evt);
            }
        });
        subFileMenu.add(subFileMenu_move);

        subFileMenu_del.setText("Delete File");
        subFileMenu_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subFileMenu_delActionPerformed(evt);
            }
        });
        subFileMenu.add(subFileMenu_del);

        subFileMenu_rename.setText("Rename File");
        subFileMenu_rename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subFileMenu_renameActionPerformed(evt);
            }
        });
        subFileMenu.add(subFileMenu_rename);

        subFileMenu_copy.setText("Copy File");
        subFileMenu_copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subFileMenu_copyActionPerformed(evt);
            }
        });
        subFileMenu.add(subFileMenu_copy);

        subFileMenu_makeDir.setText("Make Directory");
        subFileMenu_makeDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subFileMenu_makeDirActionPerformed(evt);
            }
        });
        subFileMenu.add(subFileMenu_makeDir);

        subFileMenu_changeAtrib.setText("Hide/Unhide File");
        subFileMenu_changeAtrib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subFileMenu_changeAtribActionPerformed(evt);
            }
        });
        subFileMenu.add(subFileMenu_changeAtrib);

        subShutMenu_log.setText("Log Off");
        subShutMenu_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subShutMenu_logActionPerformed(evt);
            }
        });
        subShutMenu.add(subShutMenu_log);

        subShutMenu_restart.setText("Restart");
        subShutMenu_restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subShutMenu_restartActionPerformed(evt);
            }
        });
        subShutMenu.add(subShutMenu_restart);

        subShutMenu_shutcom.setText("Shutdown");
        subShutMenu_shutcom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subShutMenu_shutcomActionPerformed(evt);
            }
        });
        subShutMenu.add(subShutMenu_shutcom);

        subMouseMenu_click.setText("Click");
        subMouseMenu_click.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMouseMenu_clickActionPerformed(evt);
            }
        });
        subMouseMenu.add(subMouseMenu_click);

        subMouseMenu_press.setText("Press");
        subMouseMenu_press.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMouseMenu_pressActionPerformed(evt);
            }
        });
        subMouseMenu.add(subMouseMenu_press);

        subMouseMenu_release.setText("Release");
        subMouseMenu_release.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMouseMenu_releaseActionPerformed(evt);
            }
        });
        subMouseMenu.add(subMouseMenu_release);

        subMouseMenu_scroll.setText("Scroll");
        subMouseMenu_scroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMouseMenu_scrollActionPerformed(evt);
            }
        });
        subMouseMenu.add(subMouseMenu_scroll);

        subMouseMenu_move.setText("Move");
        subMouseMenu_move.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMouseMenu_moveActionPerformed(evt);
            }
        });
        subMouseMenu.add(subMouseMenu_move);

        subKeyMenu_type.setText("Type");
        subKeyMenu_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_typeActionPerformed(evt);
            }
        });
        subKeyMenu.add(subKeyMenu_type);

        subKeyMenu_press.setText("Press");

        subKeyMenu_press_shift.setText("Shift");
        subKeyMenu_press_shift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_press_shiftActionPerformed(evt);
            }
        });
        subKeyMenu_press.add(subKeyMenu_press_shift);

        subKeyMenu_press_enter.setText("Enter");
        subKeyMenu_press_enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_press_enterActionPerformed(evt);
            }
        });
        subKeyMenu_press.add(subKeyMenu_press_enter);

        subKeyMenu_press_del.setText("Delete");
        subKeyMenu_press_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_press_delActionPerformed(evt);
            }
        });
        subKeyMenu_press.add(subKeyMenu_press_del);

        subKeyMenu_press_ctrl.setText("Ctrl");
        subKeyMenu_press_ctrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_press_ctrlActionPerformed(evt);
            }
        });
        subKeyMenu_press.add(subKeyMenu_press_ctrl);

        subKeyMenu_press_esc.setText("ESC");
        subKeyMenu_press_esc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_press_escActionPerformed(evt);
            }
        });
        subKeyMenu_press.add(subKeyMenu_press_esc);

        subKeyMenu_press_letter.setText("Letter");
        subKeyMenu_press_letter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_press_letterActionPerformed(evt);
            }
        });
        subKeyMenu_press.add(subKeyMenu_press_letter);

        subKeyMenu.add(subKeyMenu_press);

        subKeyMenu_release.setText("Release");

        subKeyMenu_release_shfit.setText("Shift");
        subKeyMenu_release_shfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_release_shfitActionPerformed(evt);
            }
        });
        subKeyMenu_release.add(subKeyMenu_release_shfit);

        subKeyMenu_release_enter.setText("Enter");
        subKeyMenu_release_enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_release_enterActionPerformed(evt);
            }
        });
        subKeyMenu_release.add(subKeyMenu_release_enter);

        subKeyMenu_release_del.setText("Delete");
        subKeyMenu_release_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_release_delActionPerformed(evt);
            }
        });
        subKeyMenu_release.add(subKeyMenu_release_del);

        subKeyMenu_release_ctrl.setText("Ctrl");
        subKeyMenu_release_ctrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_release_ctrlActionPerformed(evt);
            }
        });
        subKeyMenu_release.add(subKeyMenu_release_ctrl);

        subKeyMenu_release_esc.setText("ESC");
        subKeyMenu_release_esc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_release_escActionPerformed(evt);
            }
        });
        subKeyMenu_release.add(subKeyMenu_release_esc);

        subKeyMenu_release_letter.setText("Letter");
        subKeyMenu_release_letter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subKeyMenu_release_letterActionPerformed(evt);
            }
        });
        subKeyMenu_release.add(subKeyMenu_release_letter);

        subKeyMenu.add(subKeyMenu_release);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Octo");
        setIconImages(icons);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setToolTipText("");

        eventList.setModel(new DefaultListModel<String>());
        eventList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eventListMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(eventList);

        actionList.setModel(new DefaultListModel<String>());
        jScrollPane2.setViewportView(actionList);

        jLabel1.setText("Events");

        jLabel2.setText("Actions");

        jButton1.setText("New Event");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete Event");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        newActionButton.setText("New Action");
        newActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newActionButtonActionPerformed(evt);
            }
        });

        jButton4.setText("Delete Action");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        systemTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        systemTime.setText("Time and Date");

        jButton12.setText("Edit Action");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton18.setText("Up");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Down");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        control_mouseX.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        control_mouseY.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Y");

        control_freeze.setText("Freeze (F3)");
        control_freeze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                control_freezeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(newActionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(control_mouseX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(control_mouseY, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(control_freeze)
                        .addGap(48, 48, 48)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(systemTime, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(newActionButton)
                            .addComponent(jButton12)
                            .addComponent(jButton18)
                            .addComponent(control_mouseX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(jButton19)
                            .addComponent(control_mouseY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(control_freeze)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(systemTime)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Control", jPanel1);

        historyList.setModel(new DefaultListModel<String>());
        jScrollPane3.setViewportView(historyList);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout graph1Layout = new javax.swing.GroupLayout(graph1);
        graph1.setLayout(graph1Layout);
        graph1Layout.setHorizontalGroup(
            graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        graph1Layout.setVerticalGroup(
            graph1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graph1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(graph1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        runButton.setText("Run (F1)");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        stopButton.setText("Stop (F2)");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(stopButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runButton)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Run", jPanel2);

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("About");

        aboutMenu_help.setText("Help");
        aboutMenu_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenu_helpActionPerformed(evt);
            }
        });
        jMenu2.add(aboutMenu_help);

        aboutMenu_dev.setText("Developer");
        aboutMenu_dev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenu_devActionPerformed(evt);
            }
        });
        jMenu2.add(aboutMenu_dev);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EventSelector.pack();
        EventSelector.setBounds(this.getX() + this.getWidth() / 2 - EventSelector.getWidth() / 2, this.getY() + this.getHeight() / 2 - EventSelector.getHeight() / 2,
                EventSelector.getWidth(), EventSelector.getHeight());
        EventSelector.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        saveDialog.pack();
        centerDialog(saveDialog);
        saveChooser.showDialog(saveDialog, "Save");
        saveChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File file = saveChooser.getCurrentDirectory();
        if (saveChooser.getSelectedFile() == null) {
            return;
        }
        new FileHandler().saveFile(handler, file.getAbsolutePath() + "\\" + saveChooser.getSelectedFile().getName() + ".oct");
        JOptionPane.showMessageDialog(this, "File was Saved!");

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        showConfirmDialog("Are you sure leaving the program?");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        openDialog.pack();
        centerDialog(openDialog);
        openChooser.showOpenDialog(openDialog);
        File file = openChooser.getSelectedFile();
        new FileHandler().open(file, handler, eventList, actionList);

        //adding history list and graph to ScheduledActions
        Event sch = handler.getEvent("Schedule");
        if (sch != null) {
            for (int i = 0; i < sch.getActions().size(); i++) {
                ScheduledAction action = (ScheduledAction) sch.getActions().get(i);
                action.setGraph(graph1);
                action.setHistoryList(historyList);
            }
        }
        
        //select event
        eventList.setSelectedIndex(eventList.getLastVisibleIndex());
        selectedEvent = handler.getEvent(eventList.getLastVisibleIndex()).getName();

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void newActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newActionButtonActionPerformed

        if (eventList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "You must have and select an event!");
            return;
        }
        if (!handler.getEvent(selectedEvent).getName().toLowerCase().startsWith("schedu")) {
            newActionDialog.pack();
            centerDialog(newActionDialog);
            newActionDialog.setVisible(true);
        } else {
            //lunch sub-action editor and select a time
            int h = Math.min(24, Math.max(0, Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the scheduled Hour: "))));
            int min = Math.min(59, Math.max(0, Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the scheduled Minutes: "))));
            Event event = handler.getEvent(selectedEvent);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, h);
            c.set(Calendar.MINUTE, min);
            ScheduledAction act = new ScheduledAction(event.getPor(), c, graph1, historyList);
            if (edit) {
                edit = false;
                act = (ScheduledAction) event.getAction(actionList.getSelectedIndex());
                act.setCalendar(c);
                //displaying action editor
                updateList(actionList, event);
                updateList(subAction_list, act.getActions());
                subActionEditor.pack();
                centerDialog(subActionEditor);
                subActionEditor.setVisible(true);
                actionList.setSelectedIndex(lastSelectedActionl);
                return;
            }
            prepareAction(act);
            actionList.setSelectedIndex(actionList.getLastVisibleIndex());

            //displaying action editor
            subActionEditor.pack();
            centerDialog(subActionEditor);
            subActionEditor.setVisible(true);

        }

    }//GEN-LAST:event_newActionButtonActionPerformed

    private void actionFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionFileActionPerformed
        FileMenu.show(actionFile, actionFile.getX() / 2, 0);
    }//GEN-LAST:event_actionFileActionPerformed

    private void moveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveFileActionPerformed
        twoPathDialog.pack();
        twoPathDialog.setTitle("Move File: select path");
        centerDialog(twoPathDialog);
        twoPathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FileHandler.getAbsoluteFilter());
    }//GEN-LAST:event_moveFileActionPerformed

    private void shutdownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutdownButtonActionPerformed
        ShutDownMenu.show(shutdownButton, shutdownButton.getX() / 2, 0);
    }//GEN-LAST:event_shutdownButtonActionPerformed

    private void mouseActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mouseActionButtonActionPerformed
        MouseActionMenu.show(mouseActionButton, mouseActionButton.getX() / 2, 0);
    }//GEN-LAST:event_mouseActionButtonActionPerformed

    private void clickMouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickMouseActionPerformed
        mouseButtonSelection = MouseAction.ACTION_CLICK;
        MouseActionDialog.pack();
        centerDialog(MouseActionDialog);
        MouseActionDialog.setVisible(true);
        newActionDialog.dispose();
    }//GEN-LAST:event_clickMouseActionPerformed

    private void pressMouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressMouseActionPerformed
        mouseButtonSelection = MouseAction.ACTION_PRESS;
        MouseActionDialog.pack();
        centerDialog(MouseActionDialog);
        MouseActionDialog.setVisible(true);
        newActionDialog.dispose();
    }//GEN-LAST:event_pressMouseActionPerformed

    private void releaseActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseActionActionPerformed
        mouseButtonSelection = MouseAction.ACTION_RELEASE;
        MouseActionDialog.pack();
        centerDialog(MouseActionDialog);
        MouseActionDialog.setVisible(true);
        newActionDialog.dispose();
    }//GEN-LAST:event_releaseActionActionPerformed

    private void moveMouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveMouseActionPerformed
        CoordinateInputDialog.pack();
        centerDialog(CoordinateInputDialog);
        CoordinateInputDialog.setVisible(true);
        newActionDialog.dispose();
    }//GEN-LAST:event_moveMouseActionPerformed

    private void cordOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cordOkActionPerformed
        newActionDialog.dispose();
        MouseAction action = new MouseAction(handler.getEvent(selectedEvent).getPor(), MouseAction.ACTION_MOVE, null);
        action.setX(Integer.parseInt(cordX.getText()));
        action.setY(Integer.parseInt(cordY.getText()));
        if (edit) {
            edit = false;
            action = (MouseAction) handler.getEvent(selectedEvent).getAction(actionList.getSelectedIndex());
            action.setX(Integer.parseInt(cordX.getText()));
            action.setY(Integer.parseInt(cordY.getText()));
            updateList(actionList, handler.getEvent(selectedEvent));
            CoordinateInputDialog.dispose();
            return;
        }
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        model.addElement(action.getDescription());
        handler.getEvent(selectedEvent).addAction(action);
        CoordinateInputDialog.dispose();
    }//GEN-LAST:event_cordOkActionPerformed

    private void cordCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cordCancelActionPerformed
        CoordinateInputDialog.dispose();
    }//GEN-LAST:event_cordCancelActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        historyList.setModel(new DefaultListModel<String>());
        handler.start();
    }//GEN-LAST:event_runButtonActionPerformed

    private void delayActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delayActionButtonActionPerformed
        OneValueInput.pack();
        centerDialog(OneValueInput);
        OneValueInput.setVisible(true);
        newActionDialog.dispose();
        oneInputID = 0;
    }//GEN-LAST:event_delayActionButtonActionPerformed

    private void oneInOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneInOkActionPerformed
        switch (oneInputID) {
            case 0:
                Event event = handler.getEvent(selectedEvent);
                DelayAction action = new DelayAction(event.getPor(), Integer.parseInt(oneInTextFeild.getText())); // 3 seconds

                if (edit) {
                    edit = false;
                    DelayAction act = (DelayAction) event.getAction(actionList.getSelectedIndex());
                    act.setDelay(Integer.parseInt(oneInTextFeild.getText()));
                    updateList(actionList, event);
                    OneValueInput.dispose();
                    return;
                }
                prepareAction(action);
                OneValueInput.dispose();
                break;
        }
    }//GEN-LAST:event_oneInOkActionPerformed

    private void oneInCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneInCancelActionPerformed
        OneValueInput.dispose();
    }//GEN-LAST:event_oneInCancelActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        StopApplicationAction action = new StopApplicationAction(handler.getEvent(selectedEvent).getPor()); // 3 seconds
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        model.addElement(action.getDescription());
        handler.getEvent(selectedEvent).addAction(action);
        newActionDialog.dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void newAction_looperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction_looperActionPerformed
        newActionDialog.dispose();
        LooperAction act = new LooperAction(handler.getEvent(selectedEvent).getPor());
        act.addAction(new MouseAction(handler.getEvent(selectedEvent).getPor(), MouseAction.ACTION_CLICK, null));
        act.addAction(new StopApplicationAction(handler.getEvent(selectedEvent).getPor()));
        act.addAction(new DelayAction(handler.getEvent(selectedEvent).getPor(), 2000));
        act.addAction(new MouseAction(handler.getEvent(selectedEvent).getPor(), MouseAction.ACTION_CLICK, null));
        act.addAction(new StopApplicationAction(handler.getEvent(selectedEvent).getPor()));
        act.addAction(new DelayAction(handler.getEvent(selectedEvent).getPor(), 2000));
        act.addAction(new MouseAction(handler.getEvent(selectedEvent).getPor(), MouseAction.ACTION_CLICK, null));
        act.addAction(new StopApplicationAction(handler.getEvent(selectedEvent).getPor()));
        act.addAction(new DelayAction(handler.getEvent(selectedEvent).getPor(), 2000));
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        model.addElement(act.getDescription());
        handler.getEvent(selectedEvent).addAction(act);

    }//GEN-LAST:event_newAction_looperActionPerformed

    private void lunchAppButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunchAppButtonActionPerformed
        //adding actoins for testing propuses only
        newActionDialog.dispose();
        Event event = handler.getEvent(selectedEvent);
//        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
//        MouseAction act1 = new MouseAction(event.getPor(), MouseAction.ACTION_MOVE, null);
//        act1.setX(200);
//        act1.setY(200);
//        DelayAction d1 = new DelayAction(event.getPor(), 2000);
//        MouseAction act2 = new MouseAction(event.getPor(), MouseAction.ACTION_MOVE, null);
//        act2.setX(500);
//        act2.setY(500);
//        DelayAction d2 = new DelayAction(event.getPor(), 3000);
//        MouseAction act3 = new MouseAction(event.getPor(), MouseAction.ACTION_MOVE, null);
//        act3.setX(600);
//        act3.setY(600);
//        DelayAction d3 = new DelayAction(event.getPor(), 5000);
//        
//        handler.getEvent(selectedEvent).addAction(act1);
//        handler.getEvent(selectedEvent).addAction(d1);
//        handler.getEvent(selectedEvent).addAction(act2);
//        handler.getEvent(selectedEvent).addAction(d2);
//        handler.getEvent(selectedEvent).addAction(act3);
//        handler.getEvent(selectedEvent).addAction(d3);
//        model.addElement(act1.getDescription());
//        model.addElement(d1.getDescription());
//        model.addElement(act2.getDescription());
//        model.addElement(d2.getDescription());
//        model.addElement(act3.getDescription());
//        model.addElement(d3.getDescription());
        onePathDialog.pack();
        onePathDialog.setTitle("Lunch Application: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FileHandler.getAbsoluteFilter());


    }//GEN-LAST:event_lunchAppButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        handler.flush();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selection = actionList.getSelectedIndex();
        if (selection == -1) {
            return;
        }
        handler.getEvent(selectedEvent).removeAction(selection);
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        model.remove(selection);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void deleteFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFileActionPerformed
        onePathDialog.pack();
        onePathDialog.setTitle("Delete File: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FileHandler.getAbsoluteFilter());
    }//GEN-LAST:event_deleteFileActionPerformed

    private void renameFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameFileActionPerformed
        onePathDialog.pack();
        onePathDialog.setTitle("Rename File: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FileHandler.getAbsoluteFilter());
    }//GEN-LAST:event_renameFileActionPerformed

    private void makeDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeDirActionPerformed
        onePathDialog.pack();
        onePathDialog.setTitle("Make new Directory: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FileHandler.getDirectoryFilter());
    }//GEN-LAST:event_makeDirActionPerformed

    private void changeAttribActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeAttribActionPerformed
        onePathDialog.pack();
        onePathDialog.setTitle("Hide/Unhide File: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FileHandler.getAbsoluteFilter());
    }//GEN-LAST:event_changeAttribActionPerformed

    private void onePath_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onePath_selectActionPerformed
        FileSelector.pack();
        centerDialog(FileSelector);
        fileChooser_chooser.showOpenDialog(onePath_select);
        File file = fileChooser_chooser.getSelectedFile();
        onePath_text.setText(file.getAbsolutePath());
    }//GEN-LAST:event_onePath_selectActionPerformed

    private void fileChooser_chooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooser_chooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileChooser_chooserActionPerformed

    private void onePath_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onePath_cancelActionPerformed
        onePathDialog.dispose();
    }//GEN-LAST:event_onePath_cancelActionPerformed

    private void onePath_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onePath_okActionPerformed
        String title = onePathDialog.getTitle();
        String path1 = "\"" + onePath_text.getText() + "\"";
        Event event = handler.getEvent(selectedEvent);
        System.out.println(event.getName());
        Action act = null;

        if (title.startsWith("Rename")) {
            //ask for the new file name
            String fileName = JOptionPane.showInputDialog(this, "Enter the new file name: ");
            String ext = "";
            if (fileName.indexOf(".") == -1) {
                ext = path1.substring(path1.lastIndexOf("."));
            } else {
                ext = "\"";
            }
            act = new FileAction(event.getPor(), FileAction.TYPE_RENAME_FILE, path1);
            ((FileAction) act).setNewPath("\"" + fileName + ext + "\"");
            if (edit) {
                edit = false;
                act = (FileAction) event.getAction(actionList.getSelectedIndex());
                ((FileAction) act).setOldPath(path1);
                ((FileAction) act).setNewPath("\"" + fileName + ext + "\"");
                updateList(actionList, event);
                onePathDialog.dispose();
                return;
            }

        } else if (title.startsWith("Delete")) {
            //just delete
            act = new FileAction(event.getPor(), FileAction.TYPE_DELETE_FILE, path1);
            if (edit) {
                edit = false;
                act = (FileAction) event.getAction(actionList.getSelectedIndex());
                ((FileAction) act).setOldPath(path1);
                updateList(actionList, event);
                onePathDialog.dispose();
                return;
            }
        } else if (title.startsWith("Make")) {
            //take the name of the file - make sure its only folders
            String folderName = JOptionPane.showInputDialog(this, "Type directory name: ");
            act = new FileAction(event.getPor(), FileAction.TYPE_MAKE_DIRECTORY, path1.substring(0, path1.length() - 1) + "\\" + folderName + "\"");
            if (edit) {
                edit = false;
                act = (FileAction) event.getAction(actionList.getSelectedIndex());
                ((FileAction) act).setOldPath(path1.substring(0, path1.length() - 1) + "\\" + folderName + "\"");
                updateList(actionList, event);
                onePathDialog.dispose();
                return;
            }
        } else if (title.startsWith("Hide")) {
            //ask whether to hide or unhide
            char com = JOptionPane.showInputDialog(this, "Enter H: hide file, U: unhide file :").toLowerCase().charAt(0);
            if (com != 'h' && com != 'u') {
                return;
            }
            act = new FileAction(event.getPor(), ((com == 'h') ? FileAction.TYPE_HIDE_FILE : FileAction.TYPE_UNHIDE_FILE), path1);
            if (edit) {
                edit = false;
                act = (FileAction) event.getAction(actionList.getSelectedIndex());
                ((FileAction) act).setOldPath(path1);
                ((FileAction) act).setType(((com == 'h') ? FileAction.TYPE_HIDE_FILE : FileAction.TYPE_UNHIDE_FILE));
                updateList(actionList, event);
                onePathDialog.dispose();
                return;
            }
        } else if (title.startsWith("Lunch")) {

            act = new LunchAppAction(event.getPor(), path1);
            if (edit) {
                edit = false;
                Action update = event.getAction(actionList.getSelectedIndex());
                ((LunchAppAction) update).setPath(path1);
                updateList(actionList, event);
                onePathDialog.dispose();
                return;
            }

        }

        if (!event.getName().equals("Schedule")) {
            prepareAction(act);
        } else if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
        onePathDialog.dispose();
    }//GEN-LAST:event_onePath_okActionPerformed

    private void twoPath_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPath_cancelActionPerformed
        twoPathDialog.dispose();
    }//GEN-LAST:event_twoPath_cancelActionPerformed

    private void twoPath_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPath_okActionPerformed
        String title = twoPathDialog.getTitle();
        String oldPath = "\"" + twoPath_oldText.getText() + "\"";
        String newPath = "\"" + twoPath_newText.getText();

        //getting the name fo the file
        String fileName = twoPath_oldText.getText().substring(twoPath_oldText.getText().lastIndexOf("\\") + 1);
        boolean folder = (fileName.indexOf(".") != -1) ? false : true;
        System.out.println(fileName + "  " + oldPath + " " + newPath);
        Event event = handler.getEvent(selectedEvent);
        Action act = null;
        if (title.startsWith("Copy")) {
            act = new FileAction(event.getPor(), FileAction.TYPE_COPY_FILE, oldPath);
            ((FileAction) act).setNewPath(newPath + "\\" + fileName + "\"");
            if (edit) {
                edit = false;
                act = (FileAction) event.getAction(actionList.getSelectedIndex());
                ((FileAction) act).setOldPath(oldPath);
                ((FileAction) act).setNewPath(newPath + "\\" + fileName + "\"");
                updateList(actionList, event);
                twoPathDialog.dispose();
                return;
            }
        } else if (title.startsWith("Move")) {
            act = new FileAction(event.getPor(), FileAction.TYPE_MOVE_FILE, oldPath);
            ((FileAction) act).setNewPath(newPath + "\\" + fileName + "\"");

            if (edit) {
                edit = false;
                act = (FileAction) event.getAction(actionList.getSelectedIndex());
                ((FileAction) act).setOldPath(oldPath);
                ((FileAction) act).setNewPath(newPath + "\\" + fileName + "\"");
                updateList(actionList, event);
                twoPathDialog.dispose();
                return;
            }
        }
        if (!event.getName().equalsIgnoreCase("Schedule")) {
            prepareAction(act);
        } else if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
        twoPathDialog.dispose();
    }//GEN-LAST:event_twoPath_okActionPerformed

    private void twoPath_oldPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPath_oldPathActionPerformed
        FileSelector.pack();
        centerDialog(FileSelector);
        fileChooser_chooser.showOpenDialog(twoPath_oldPath);
        File file = fileChooser_chooser.getSelectedFile();
        twoPath_oldText.setText(file.getAbsolutePath());
    }//GEN-LAST:event_twoPath_oldPathActionPerformed

    private void twoPath_oldTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPath_oldTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_twoPath_oldTextActionPerformed

    private void twoPath_newPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPath_newPathActionPerformed
        FileSelector.pack();
        centerDialog(FileSelector);
        fileChooser_chooser.showOpenDialog(twoPath_newPath);
        File file = fileChooser_chooser.getSelectedFile();
        twoPath_newText.setText(file.getAbsolutePath());
    }//GEN-LAST:event_twoPath_newPathActionPerformed

    private void twoPath_newTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPath_newTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_twoPath_newTextActionPerformed

    private void copyFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyFileActionPerformed
        twoPathDialog.pack();
        twoPathDialog.setTitle("Copy File: select path");
        centerDialog(twoPathDialog);
        twoPathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FileHandler.getAbsoluteFilter());
    }//GEN-LAST:event_copyFileActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
//        newActionDialog.dispose();
//        String text = JOptionPane.showInputDialog(this,"Type text for Keystroke Action:");
//        KeyStrokeAction action = new KeyStrokeAction(Event.POR_START, text);
//        addElementToList(actionList, action.getDescription());
//        testEvent.addAction(action);
//        handler.addEvent(testEvent);

        KeystrokeMenu.show(jButton13, jButton13.getX() / 2, 0);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void scrollMouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scrollMouseActionPerformed
        newActionDialog.dispose();
        int scroll = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the Y change: "));
        MouseAction act = new MouseAction(handler.getEvent(selectedEvent).getPor(), MouseAction.ACTION_SCROLL, null);
        act.setY(scroll);
        if (edit) {
            edit = false;
            Event event = handler.getEvent(selectedEvent);
            act = (MouseAction) event.getAction(actionList.getSelectedIndex());
            act.setY(scroll);
            updateList(actionList, event);
            return;
        }
        addElementToList(actionList, act.getDescription());
        handler.getEvent(selectedEvent).addAction(act);

    }//GEN-LAST:event_scrollMouseActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {
        int actSelection = actionList.getSelectedIndex();
        if (actSelection == -1) {
            JOptionPane.showMessageDialog(this, "You must select an action first");
        } else {
            if (actSelection > 0) {
                ArrayList<Action> actions = handler.getEvent(selectedEvent).getActions();
                actions.add(actSelection - 1, handler.getEvent(selectedEvent).getAction(actSelection));
                actions.remove(actSelection + 1);
                DefaultListModel<String> model = new DefaultListModel<String>();
                for (int i = 0; i < actions.size(); i++) {
                    Action action = actions.get(i);
                    model.addElement(action.getDescription());
                }
                actionList.setModel(model);
            }
            actionList.setSelectedIndex(actSelection - 1);
        }

    }


    private void menu_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_typeActionPerformed
        newActionDialog.dispose();
        String text = JOptionPane.showInputDialog(this, "Type text for Keystroke Action:");
        KeyStrokeAction action = new KeyStrokeAction(handler.getEvent(selectedEvent).getPor(), KeyStrokeAction.OPERATION_TYPE, text);
        if (edit) {
            edit = false;
            Event event = handler.getEvent(selectedEvent);
            action = (KeyStrokeAction) event.getAction(actionList.getSelectedIndex());
            action.setText(text);
            updateList(actionList, event);
            return;
        }
        addElementToList(actionList, action.getDescription());
        handler.getEvent(selectedEvent).addAction(action);
    }//GEN-LAST:event_menu_typeActionPerformed

    private void bt_event_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_startActionPerformed
        addEvent("onStart", Event.POR_START);
        eventList.setSelectedIndex(eventList.getLastVisibleIndex());
        Event event = handler.getEvent(eventList.getSelectedIndex());
        selectedEvent = event.getName();
        //update the view of actionList
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < event.getActions().size(); i++) {
            model.addElement(event.getActions().get(i).getDescription());
        }
        actionList.setModel(model);


    }//GEN-LAST:event_bt_event_startActionPerformed

    private void bt_event_goingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_goingActionPerformed
        addEvent("onGoing", Event.POR_GOING);
        eventList.setSelectedIndex(eventList.getLastVisibleIndex());
        Event event = handler.getEvent(eventList.getSelectedIndex());
        selectedEvent = event.getName();
        //update the view of actionList
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < event.getActions().size(); i++) {
            model.addElement(event.getActions().get(i).getDescription());
        }
        actionList.setModel(model);
    }//GEN-LAST:event_bt_event_goingActionPerformed

    private void bt_event_loopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_loopActionPerformed
        addEvent("Loop", Event.POR_LOOP);
        eventList.setSelectedIndex(eventList.getLastVisibleIndex());
        Event event = handler.getEvent(eventList.getSelectedIndex());
        selectedEvent = event.getName();
        //update the view of actionList
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < event.getActions().size(); i++) {
            model.addElement(event.getActions().get(i).getDescription());
        }
        actionList.setModel(model);

    }//GEN-LAST:event_bt_event_loopActionPerformed

    private void bt_event_scheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_scheduleActionPerformed
        addEvent("Schedule", Event.POR_SCHEDULE);
        eventList.setSelectedIndex(eventList.getLastVisibleIndex());
        Event event = handler.getEvent(eventList.getSelectedIndex());
        selectedEvent = event.getName();
        //update the view of actionList
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < event.getActions().size(); i++) {
            model.addElement(event.getActions().get(i).getDescription());
        }
        actionList.setModel(model);

    }//GEN-LAST:event_bt_event_scheduleActionPerformed

    private void bt_event_triggerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_triggerActionPerformed
        addEvent("Trigger", Event.POR_TRIGGER);

        eventList.setSelectedIndex(eventList.getLastVisibleIndex());
        Event event = handler.getEvent(eventList.getSelectedIndex());
        selectedEvent = event.getName();
        //update the view of actionList
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < event.getActions().size(); i++) {
            model.addElement(event.getActions().get(i).getDescription());
        }
        actionList.setModel(model);

    }//GEN-LAST:event_bt_event_triggerActionPerformed

    private void eventListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventListMousePressed
        Event event = handler.getEvent(eventList.getSelectedIndex());
        selectedEvent = event.getName();

        //update the view of actionList
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < event.getActions().size(); i++) {
            model.addElement(event.getActions().get(i).getDescription());
        }
        actionList.setModel(model);
    }//GEN-LAST:event_eventListMousePressed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        int actSelection = actionList.getSelectedIndex();
        if (actSelection == -1) {
            JOptionPane.showMessageDialog(this, "You must select an action first");
        } else if (actSelection < actionList.getLastVisibleIndex() - 1) {
            ArrayList<Action> actions = handler.getEvent(selectedEvent).getActions();
            actions.add(actSelection + 2, handler.getEvent(selectedEvent).getAction(actSelection));
            actions.remove(actSelection);
            DefaultListModel<String> model = new DefaultListModel<String>();
            for (int i = 0; i < actions.size(); i++) {
                Action action = actions.get(i);
                model.addElement(action.getDescription());
            }
            actionList.setModel(model);
            actionList.setSelectedIndex(actSelection + 1);
        } else {
            ArrayList<Action> actions = handler.getEvent(selectedEvent).getActions();
            actions.add(handler.getEvent(selectedEvent).getAction(actSelection));
            actions.remove(actSelection);
            DefaultListModel<String> model = new DefaultListModel<String>();
            for (int i = 0; i < actions.size(); i++) {
                Action action = actions.get(i);
                model.addElement(action.getDescription());
            }
            actionList.setModel(model);
            actionList.setSelectedIndex(actionList.getLastVisibleIndex());
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void menu_vk_shiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_shiftActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_SHIFT);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_shiftActionPerformed

    private void menu_vk_enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_enterActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_ENTER);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_enterActionPerformed

    private void menu_vk_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_delActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_DEL);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_delActionPerformed

    private void menu_vk_ctrlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_ctrlActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_CTRL);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_ctrlActionPerformed

    private void menu_vk_escActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_escActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_ESC);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_escActionPerformed

    private void menu_vk_letterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_letterActionPerformed
        Event event = handler.getEvent(selectedEvent);
        String str = JOptionPane.showInputDialog(this, "Enter a single Letter:");
        if (str.length() == 0) {
            return;
        }
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, str.substring(0, 1));
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_letterActionPerformed

    private void menu_vk_shiftRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_shiftRActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_SHIFT);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_shiftRActionPerformed

    private void menu_vk_enterRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_enterRActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_ENTER);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_enterRActionPerformed

    private void menu_vk_delRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_delRActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_DEL);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_delRActionPerformed

    private void menu_vk_ctrlRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_ctrlRActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_DEL);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_ctrlRActionPerformed

    private void menu_vk_escRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_escRActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_ESC);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_escRActionPerformed

    private void menu_vk_letterRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_vk_letterRActionPerformed
        Event event = handler.getEvent(selectedEvent);
        String str = JOptionPane.showInputDialog(this, "Enter a single Letter:");
        if (str.length() == 0) {
            return;
        }
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, str.substring(0, 1));
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_menu_vk_letterRActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int evtSel = eventList.getSelectedIndex();
        if (evtSel == -1) {
            JOptionPane.showMessageDialog(this, "Select an event first");
            return;
        }

        handler.removeEventAndActions(handler.getEvent(evtSel).getName());
        DefaultListModel<String> model = new DefaultListModel<String>();
        if (handler.getEvents().size() > 0) {
            Event event = handler.getEvent(eventList.getLastVisibleIndex() - 1);
            selectedEvent = event.getName();

            //update the view of actionList
            for (int i = 0; i < event.getActions().size(); i++) {
                model.addElement(event.getActions().get(i).getDescription());
            }
        }
        actionList.setModel(model);
        //update events' list
        DefaultListModel<String> evtModel = new DefaultListModel<String>();
        if (handler.getEvents().size() > 0) {
            for (int i = 0; i < handler.getEvents().size(); i++) {
                evtModel.addElement(handler.getEvents().get(i).getName());
            }
        }
        eventList.setModel(evtModel);
        eventList.setSelectedIndex(eventList.getLastVisibleIndex()); //highlighting it again
    }//GEN-LAST:event_jButton2ActionPerformed

    private void mouseActionRightKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mouseActionRightKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mouseActionRightKeyPressed

    private void mouseActionRightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseActionRightMouseClicked
        createMouseAction(mouseButtonSelection, MouseAction.BUTTON_RIGHT);
    }//GEN-LAST:event_mouseActionRightMouseClicked

    private void mouseActionMiddleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseActionMiddleMouseClicked
        createMouseAction(mouseButtonSelection, MouseAction.BUTTON_CENTER);
    }//GEN-LAST:event_mouseActionMiddleMouseClicked

    private void mouseActionLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseActionLeftMouseClicked
        createMouseAction(mouseButtonSelection, MouseAction.BUTTON_LEFT);
    }//GEN-LAST:event_mouseActionLeftMouseClicked

    private void logOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOffActionPerformed
        Event evnt = handler.getEvent(selectedEvent);
        ShutDownAction act = new ShutDownAction(evnt.getPor(), Command.MACHINE_LOG_OFF);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_logOffActionPerformed

    private void restartComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartComActionPerformed
        Event evnt = handler.getEvent(selectedEvent);
        ShutDownAction act = new ShutDownAction(evnt.getPor(), Command.MACHINE_RESTART);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_restartComActionPerformed

    private void shutComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutComActionPerformed
        Event evnt = handler.getEvent(selectedEvent);
        ShutDownAction act = new ShutDownAction(evnt.getPor(), Command.MACHINE_SHUTDOWN);
        prepareAction(act);
        newActionDialog.dispose();
    }//GEN-LAST:event_shutComActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        newActionDialog.dispose();
        JOptionPane.showMessageDialog(this, "Future update");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        int actSel = actionList.getSelectedIndex();
        if (actSel == -1) {
            return;
        }
        Event event = handler.getEvent(selectedEvent);
        edit = true;
        Action act = event.getAction(actSel);
        if (act instanceof DelayAction) {
            delayActionButton.doClick();
        } else if (act instanceof LunchAppAction) {
            lunchAppButton.doClick();
        } else if (act instanceof MouseAction) {
            String type = ((MouseAction) act).getType();
            switch (type) {
                case MouseAction.ACTION_CLICK:
                    clickMouse.doClick();
                    break;
                case MouseAction.ACTION_MOVE:
                    moveMouse.doClick();
                    break;
                case MouseAction.ACTION_PRESS:
                    pressMouse.doClick();
                    break;
                case MouseAction.ACTION_RELEASE:
                    releaseAction.doClick();
                    break;
                case MouseAction.ACTION_SCROLL:
                    scrollMouse.doClick();
                    break;
            }
        } else if (act instanceof FileAction) {
            String type = ((FileAction) act).getType();
            switch (type) {
                case FileAction.TYPE_RENAME_FILE:
                    renameFile.doClick();
                    break;
                case FileAction.TYPE_DELETE_FILE:
                    deleteFile.doClick();
                    break;
                case FileAction.TYPE_COPY_FILE:
                    copyFile.doClick();
                    break;
                case FileAction.TYPE_HIDE_FILE:
                case FileAction.TYPE_UNHIDE_FILE:
                    changeAttrib.doClick();
                    break;
                case FileAction.TYPE_MAKE_DIRECTORY:
                    makeDir.doClick();
                    break;
                case FileAction.TYPE_MOVE_FILE:
                    moveFile.doClick();
                    break;

            }
        } else if (act instanceof ShutDownAction) {
            edit = false;
        } else if (act instanceof KeyStrokeAction) {
            String type = ((KeyStrokeAction) act).getType();
            switch (type) {
                case KeyStrokeAction.OPERATION_TYPE:
                    menu_type.doClick();
                    break;
                case KeyStrokeAction.OPERATION_PRESS:
                    edit = false;
                    break;
                case KeyStrokeAction.OPERATION_RELEASE:
                    edit = false;
                    break;
            }
        } else if (act instanceof ScheduledAction) {
            newActionButton.doClick();
            lastSelectedActionl = actionList.getSelectedIndex();
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void control_freezeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_control_freezeActionPerformed
        control_freeze.setSelected(false);
    }//GEN-LAST:event_control_freezeActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void aboutMenu_devActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenu_devActionPerformed
        DeveloperFrame dev = new DeveloperFrame();
        dev.pack();
        //make sure its in the middle
        dev.setBounds((int) (screen.getWidth() - dev.getWidth()) / 2, ((int) screen.getHeight() - dev.getHeight()) / 2,
                dev.getWidth(), dev.getHeight());

        dev.setVisible(true);
    }//GEN-LAST:event_aboutMenu_devActionPerformed

    private void subAction_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAction_addActionPerformed
        if (actionList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "You must select an action first");
            return;
        }
        newSubActionDialog.pack();
        centerDialog(newSubActionDialog);
        newSubActionDialog.setVisible(true);
    }//GEN-LAST:event_subAction_addActionPerformed

    private void newSubAct_mouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSubAct_mouseActionPerformed
        subMouseMenu.show(newSubAct_mouse, newSubAct_mouse.getX() / 2, 0);
    }//GEN-LAST:event_newSubAct_mouseActionPerformed

    private void newSubAct_keystrokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSubAct_keystrokeActionPerformed
        subKeyMenu.show(newSubAct_keystroke, newSubAct_keystroke.getX() / 2, 0);
    }//GEN-LAST:event_newSubAct_keystrokeActionPerformed

    private void newSubAct_lunchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSubAct_lunchActionPerformed
        onePathDialog.setTitle("Lunch Application: select a path: ");
        newSubActionDialog.dispose();
        displayDialog(onePathDialog);
    }//GEN-LAST:event_newSubAct_lunchActionPerformed

    private void newSubAct_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSubAct_fileActionPerformed
        subFileMenu.show(newSubAct_file, newSubAct_file.getX() / 2, 0);
    }//GEN-LAST:event_newSubAct_fileActionPerformed

    private void newSubAct_shutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSubAct_shutdownActionPerformed
        subShutMenu.show(newSubAct_shutdown, newSubAct_shutdown.getX() / 2, 0);
    }//GEN-LAST:event_newSubAct_shutdownActionPerformed

    private void newSubAct_varialbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSubAct_varialbActionPerformed
        JOptionPane.showMessageDialog(this, "Future update");
    }//GEN-LAST:event_newSubAct_varialbActionPerformed

    private void newSubAct_delayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSubAct_delayActionPerformed
        int delay = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter delay in milliseconds: "));
        delay = Math.max(0, delay);
        Event event = handler.getEvent(selectedEvent);
        DelayAction act = new DelayAction(event.getPor(), delay);
        prepareSubScheduledAction(act, event);
    }//GEN-LAST:event_newSubAct_delayActionPerformed

    public void prepareSubScheduledAction(Action act, Event event) {
        newSubActionDialog.dispose();
        lastSelectedActionl = actionList.getSelectedIndex();
        ((ScheduledAction) event.getAction(actionList.getSelectedIndex())).addAction(act);
        addElementToList(subAction_list, act.getDescription());
        updateList(actionList, event);
        actionList.setSelectedIndex(lastSelectedActionl);
    }

    private void newSubAct_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSubAct_stopActionPerformed
        Event event = handler.getEvent(selectedEvent);
        StopApplicationAction act = new StopApplicationAction(event.getPor());
        prepareSubScheduledAction(act, event);
    }//GEN-LAST:event_newSubAct_stopActionPerformed

    private void subFileMenu_moveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subFileMenu_moveActionPerformed
        twoPathDialog.setTitle("Move File: select a path: ");
        displayDialog(twoPathDialog);
        newSubActionDialog.dispose();
    }//GEN-LAST:event_subFileMenu_moveActionPerformed

    private void subFileMenu_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subFileMenu_delActionPerformed
        onePathDialog.setTitle("Delete File: select a path: ");
        newSubActionDialog.dispose();
        displayDialog(onePathDialog);
    }//GEN-LAST:event_subFileMenu_delActionPerformed

    private void subFileMenu_renameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subFileMenu_renameActionPerformed
        onePathDialog.setTitle("Rename File: select a path: ");
        newSubActionDialog.dispose();
        displayDialog(onePathDialog);
    }//GEN-LAST:event_subFileMenu_renameActionPerformed

    private void subFileMenu_copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subFileMenu_copyActionPerformed
        twoPathDialog.setTitle("Copy File: select a path: ");
        displayDialog(twoPathDialog);
        newSubActionDialog.dispose();
    }//GEN-LAST:event_subFileMenu_copyActionPerformed

    private void subFileMenu_makeDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subFileMenu_makeDirActionPerformed
        onePathDialog.setTitle("Make New Directory: select a path: ");
        newSubActionDialog.dispose();
        displayDialog(onePathDialog);
    }//GEN-LAST:event_subFileMenu_makeDirActionPerformed

    private void subFileMenu_changeAtribActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subFileMenu_changeAtribActionPerformed
        onePathDialog.setTitle("Hide/Unhide File: select a path: ");
        newSubActionDialog.dispose();
        displayDialog(onePathDialog);
    }//GEN-LAST:event_subFileMenu_changeAtribActionPerformed

    private void subShutMenu_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subShutMenu_logActionPerformed
        Event event = handler.getEvent(selectedEvent);
        ShutDownAction act = new ShutDownAction(event.getPor(), Command.MACHINE_LOG_OFF);
        prepareSubScheduledAction(act, event);
    }//GEN-LAST:event_subShutMenu_logActionPerformed

    private void subShutMenu_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subShutMenu_restartActionPerformed
        Event event = handler.getEvent(selectedEvent);
        ShutDownAction act = new ShutDownAction(event.getPor(), Command.MACHINE_RESTART);
        prepareSubScheduledAction(act, event);
    }//GEN-LAST:event_subShutMenu_restartActionPerformed

    private void subShutMenu_shutcomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subShutMenu_shutcomActionPerformed
        Event event = handler.getEvent(selectedEvent);
        ShutDownAction act = new ShutDownAction(event.getPor(), Command.MACHINE_SHUTDOWN);
        prepareSubScheduledAction(act, event);
    }//GEN-LAST:event_subShutMenu_shutcomActionPerformed

    private void subMouseMenu_clickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMouseMenu_clickActionPerformed
        displayDialog(MouseActionDialog);
        mouseButtonSelection = MouseAction.ACTION_CLICK;
        newSubActionDialog.dispose();
    }//GEN-LAST:event_subMouseMenu_clickActionPerformed

    private void subMouseMenu_pressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMouseMenu_pressActionPerformed
        displayDialog(MouseActionDialog);
        mouseButtonSelection = MouseAction.ACTION_PRESS;
        newSubActionDialog.dispose();
    }//GEN-LAST:event_subMouseMenu_pressActionPerformed

    private void subMouseMenu_releaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMouseMenu_releaseActionPerformed
        displayDialog(MouseActionDialog);
        mouseButtonSelection = MouseAction.ACTION_RELEASE;
        newSubActionDialog.dispose();
    }//GEN-LAST:event_subMouseMenu_releaseActionPerformed

    private void subMouseMenu_scrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMouseMenu_scrollActionPerformed
        newSubActionDialog.dispose();
        int y = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the "));
        Event event = handler.getEvent(selectedEvent);
        MouseAction act = new MouseAction(event.getPor(), MouseAction.ACTION_SCROLL, null);
        act.setY(y);
        lastSelectedActionl = actionList.getSelectedIndex();
        ScheduledAction bigAct = ((ScheduledAction) event.getAction(actionList.getSelectedIndex()));
        bigAct.addAction(act);
        addElementToList(subAction_list, act.getDescription());
        updateListWithNumbers(actionList, event);
        actionList.setSelectedIndex(lastSelectedActionl);
    }//GEN-LAST:event_subMouseMenu_scrollActionPerformed

    public void updateList(JList list, Event evt) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ArrayList<Action> actions = evt.getActions();
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            model.addElement(action.getDescription());
        }
        list.setModel(model);
    }

    public void updateListWithNumbers(JList list, Event evt) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        ArrayList<Action> actions = evt.getActions();
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            model.addElement((i + 1) + "." + action.getDescription());
        }
        list.setModel(model);
    }

    public void updateList(JList list, ArrayList<Action> acts) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < acts.size(); i++) {
            Action action = acts.get(i);
            model.addElement((i + 1) + "." + action.getDescription());
        }
        list.setModel(model);
    }
    private void subMouseMenu_moveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMouseMenu_moveActionPerformed
        displayDialog(CoordinateInputDialog);
        newSubActionDialog.dispose();
    }//GEN-LAST:event_subMouseMenu_moveActionPerformed

    private void subKeyMenu_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_typeActionPerformed
        newSubActionDialog.dispose();
        String text = JOptionPane.showInputDialog(this, "Type text for Keystroke Action:");
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction action = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_TYPE, text);
        if (event.getName().equals("Schedule")) {
            prepareSubScheduledAction(action, event);
        }

    }//GEN-LAST:event_subKeyMenu_typeActionPerformed

    private void subKeyMenu_press_shiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_press_shiftActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_SHIFT);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_press_shiftActionPerformed

    private void subKeyMenu_press_enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_press_enterActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_ENTER);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_press_enterActionPerformed

    private void subKeyMenu_press_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_press_delActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_DEL);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_press_delActionPerformed

    private void subKeyMenu_press_ctrlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_press_ctrlActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_CTRL);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_press_ctrlActionPerformed

    private void subKeyMenu_press_escActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_press_escActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, KeyStrokeAction.KEY_ESC);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_press_escActionPerformed

    private void subKeyMenu_press_letterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_press_letterActionPerformed
        Event event = handler.getEvent(selectedEvent);
        String str = JOptionPane.showInputDialog(this, "Enter a single Letter:");
        if (str.length() == 0) {
            return;
        }
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_PRESS, str.substring(0, 1));
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_press_letterActionPerformed

    private void subKeyMenu_release_shfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_release_shfitActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_SHIFT);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_release_shfitActionPerformed

    private void subKeyMenu_release_enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_release_enterActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_ENTER);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_release_enterActionPerformed

    private void subKeyMenu_release_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_release_delActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_DEL);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_release_delActionPerformed

    private void subKeyMenu_release_ctrlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_release_ctrlActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_CTRL);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_release_ctrlActionPerformed

    private void subKeyMenu_release_escActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_release_escActionPerformed
        Event event = handler.getEvent(selectedEvent);
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, KeyStrokeAction.KEY_ESC);
        if (event.getName().equalsIgnoreCase("Schedule")) {
            prepareSubScheduledAction(act, event);
        }
    }//GEN-LAST:event_subKeyMenu_release_escActionPerformed

    private void subKeyMenu_release_letterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subKeyMenu_release_letterActionPerformed
        Event event = handler.getEvent(selectedEvent);
        String str = JOptionPane.showInputDialog(this, "Enter a single Letter:");
        if (str.length() == 0) {
            return;
        }
        KeyStrokeAction act = new KeyStrokeAction(event.getPor(), KeyStrokeAction.OPERATION_RELEASE, str.substring(0, 1));
    }//GEN-LAST:event_subKeyMenu_release_letterActionPerformed

    private void subAction_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAction_cancelActionPerformed
        subActionEditor.dispose();
    }//GEN-LAST:event_subAction_cancelActionPerformed

    private void aboutMenu_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenu_helpActionPerformed
        JOptionPane.showMessageDialog(this, "Check Project Documentation");
    }//GEN-LAST:event_aboutMenu_helpActionPerformed

    private void oneInTextFeildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneInTextFeildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oneInTextFeildActionPerformed

    private void subAction_upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAction_upActionPerformed
        int sel = subAction_list.getSelectedIndex();
        if (sel == -1) {
            return;
        }
        lastSelectedActionl = actionList.getSelectedIndex();
        Action father = handler.getEvent(selectedEvent).getAction(actionList.getSelectedIndex());
        if (father instanceof ScheduledAction) {
            Action act = ((ScheduledAction)father).getAction(sel);
            if(sel - 1 >= 0){
                ArrayList<Action> acts = ((ScheduledAction)father).getActions();
                acts.add(sel - 1, act);
                acts.remove(sel + 1);
                
                updateList(actionList, handler.getEvent(selectedEvent));
                updateList(subAction_list, acts);
                subAction_list.setSelectedIndex(sel - 1);
                actionList.setSelectedIndex(lastSelectedActionl);
            }
        }
    }//GEN-LAST:event_subAction_upActionPerformed

    private void subAction_downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAction_downActionPerformed
        int sel = subAction_list.getSelectedIndex();
        if (sel == -1) {
            return;
        }
        lastSelectedActionl = actionList.getSelectedIndex();
        Action father = handler.getEvent(selectedEvent).getAction(actionList.getSelectedIndex());
        if (father instanceof ScheduledAction) {
            Action act = ((ScheduledAction)father).getAction(sel);
            ArrayList<Action> acts = ((ScheduledAction)father).getActions();
            if(sel < acts.size() - 1){
                acts.add(sel + 2, act);
                acts.remove(sel);
                
                updateList(actionList, handler.getEvent(selectedEvent));
                updateList(subAction_list, acts);
                subAction_list.setSelectedIndex(sel + 1);
                actionList.setSelectedIndex(lastSelectedActionl);
            }
        }
    }//GEN-LAST:event_subAction_downActionPerformed

    private void subAction_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAction_removeActionPerformed
       int sel = subAction_list.getSelectedIndex();
        if (sel == -1) {
            return;
        }
        lastSelectedActionl = actionList.getSelectedIndex();
        Action father = handler.getEvent(selectedEvent).getAction(actionList.getSelectedIndex());
        if (father instanceof ScheduledAction) {
            Action act = ((ScheduledAction)father).getAction(sel);
            ArrayList<Action> acts = ((ScheduledAction)father).getActions();
            acts.remove(sel);
            updateList(subAction_list, acts);
            updateList(actionList, handler.getEvent(selectedEvent));
            actionList.setSelectedIndex(lastSelectedActionl);
        }
    }//GEN-LAST:event_subAction_removeActionPerformed

    private void subAction_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAction_editActionPerformed
       JOptionPane.showMessageDialog(this, "Future Update");
    }//GEN-LAST:event_subAction_editActionPerformed

    public void displayDialog(JDialog dialog) {
        dialog.pack();
        centerDialog(dialog);
        dialog.setVisible(true);
    }

    private void createMouseAction(String type, String button) {
        MouseActionDialog.dispose();
        MouseAction act = null;
        Event evt = handler.getEvent(selectedEvent);
        if (!edit) {
            switch (type) {
                case MouseAction.ACTION_CLICK:
                    act = new MouseAction(evt.getPor(), MouseAction.ACTION_CLICK, button);
                    break;
                case MouseAction.ACTION_PRESS:
                    act = new MouseAction(evt.getPor(), MouseAction.ACTION_PRESS, button);
                    break;
                case MouseAction.ACTION_RELEASE:
                    act = new MouseAction(evt.getPor(), MouseAction.ACTION_RELEASE, button);
                    break;
            }
            if (!evt.getName().equals("Schedule")) {
                prepareAction(act);
            } else {
                lastSelectedActionl = actionList.getSelectedIndex();
                ((ScheduledAction) evt.getAction(lastSelectedActionl)).addAction(act);
                addElementToList(subAction_list, act.getDescription());
                updateListWithNumbers(actionList, evt);
                actionList.setSelectedIndex(lastSelectedActionl);
            }
        } else {
            MouseAction action = (MouseAction) handler.getEvent(selectedEvent).getAction(actionList.getSelectedIndex());
            action.setButton(button);
            action.setType(type);
            invalidateList(actionList);
            edit = false;
        }
    }

    public void addEvent(String name, int por) {
        Event event = new Event(por);
        event.setName(name);
        if (handler.addEvent(event)) {
            addElementToList(eventList, event.getName());
        }
        EventSelector.dispose();
    }

    private void invalidateList(JList list) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        Event evt = handler.getEvent(selectedEvent);
        for (int i = 0; i < evt.getActions().size(); i++) {
            model.addElement(evt.getActions().get(i).getDescription());
        }
        list.setModel(model);
    }

    /*
     simplify the process of adding actions
     */
    private void prepareAction(Action act) {
        handler.getEvent(selectedEvent).addAction(act);
        addElementToList(actionList, act.getDescription());
    }

    private void updateAction(Action act, int pos) {
        handler.getEvent(selectedEvent).addAction(act);
        addElementToListAndRemove(actionList, act.getDescription(), pos);
    }

    public void addElementToList(JList list, String elem) {
        DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
        model.addElement(elem);
    }

    public void addElementToListAndRemove(JList list, String elem, int index) {
        DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
        model.remove(index);
        model.add(index, elem);
    }

    public void centerDialog(JDialog dialog) {
        dialog.setBounds(getMiddleX(dialog), getMiddleY(dialog), dialog.getWidth(), dialog.getHeight());
    }

    public int getMiddleX(JDialog dialog) {
        return getX() + getWidth() / 2 - dialog.getWidth() / 2;
    }

    public int getMiddleY(JDialog dialog) {
        return getY() + getHeight() / 2 - dialog.getHeight() / 2;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CoordinateInputDialog;
    private javax.swing.JDialog EventSelector;
    private javax.swing.JPopupMenu FileMenu;
    private javax.swing.JDialog FileSelector;
    private javax.swing.JPopupMenu KeystrokeMenu;
    private javax.swing.JDialog MouseActionDialog;
    private javax.swing.JPopupMenu MouseActionMenu;
    private javax.swing.JDialog OneValueInput;
    private javax.swing.JPopupMenu ShutDownMenu;
    private javax.swing.JMenuItem aboutMenu_dev;
    private javax.swing.JMenuItem aboutMenu_help;
    private javax.swing.JButton actionFile;
    private javax.swing.JList actionList;
    private javax.swing.JButton bt_event_going;
    private javax.swing.JButton bt_event_loop;
    private javax.swing.JButton bt_event_schedule;
    private javax.swing.JButton bt_event_start;
    private javax.swing.JButton bt_event_trigger;
    private javax.swing.JMenuItem changeAttrib;
    private javax.swing.JMenuItem clickMouse;
    private javax.swing.JToggleButton control_freeze;
    private javax.swing.JTextField control_mouseX;
    private javax.swing.JTextField control_mouseY;
    private javax.swing.JMenuItem copyFile;
    private javax.swing.JButton cordCancel;
    private javax.swing.JButton cordOk;
    private javax.swing.JTextField cordX;
    private javax.swing.JTextField cordY;
    private javax.swing.JButton delayActionButton;
    private javax.swing.JMenuItem deleteFile;
    private javax.swing.JList eventList;
    private javax.swing.JFileChooser fileChooser_chooser;
    private octu.graphics.Graph graph1;
    private javax.swing.JList historyList;
    private octu.graphics.ImageView imageView1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem logOff;
    private javax.swing.JButton lunchAppButton;
    private javax.swing.JMenuItem makeDir;
    private javax.swing.JMenuItem menu_type;
    private javax.swing.JMenuItem menu_vk_ctrl;
    private javax.swing.JMenuItem menu_vk_ctrlR;
    private javax.swing.JMenuItem menu_vk_del;
    private javax.swing.JMenuItem menu_vk_delR;
    private javax.swing.JMenuItem menu_vk_enter;
    private javax.swing.JMenuItem menu_vk_enterR;
    private javax.swing.JMenuItem menu_vk_esc;
    private javax.swing.JMenuItem menu_vk_escR;
    private javax.swing.JMenuItem menu_vk_letter;
    private javax.swing.JMenuItem menu_vk_letterR;
    private javax.swing.JMenu menu_vk_press;
    private javax.swing.JMenu menu_vk_release;
    private javax.swing.JMenuItem menu_vk_shift;
    private javax.swing.JMenuItem menu_vk_shiftR;
    private javax.swing.JButton mouseActionButton;
    private javax.swing.JLabel mouseActionLeft;
    private javax.swing.JLabel mouseActionMiddle;
    private javax.swing.JLabel mouseActionRight;
    private javax.swing.JMenuItem moveFile;
    private javax.swing.JMenuItem moveMouse;
    private javax.swing.JButton newActionButton;
    private javax.swing.JDialog newActionDialog;
    private javax.swing.JButton newAction_looper;
    private javax.swing.JButton newSubAct_delay;
    private javax.swing.JButton newSubAct_file;
    private javax.swing.JButton newSubAct_keystroke;
    private javax.swing.JButton newSubAct_lunch;
    private javax.swing.JButton newSubAct_mouse;
    private javax.swing.JButton newSubAct_shutdown;
    private javax.swing.JButton newSubAct_stop;
    private javax.swing.JButton newSubAct_varialb;
    private javax.swing.JDialog newSubActionDialog;
    private javax.swing.JButton oneInCancel;
    private javax.swing.JButton oneInOk;
    private javax.swing.JLabel oneInText;
    private javax.swing.JTextField oneInTextFeild;
    private javax.swing.JDialog onePathDialog;
    private javax.swing.JButton onePath_cancel;
    private javax.swing.JButton onePath_ok;
    private javax.swing.JButton onePath_select;
    private javax.swing.JTextField onePath_text;
    private javax.swing.JFileChooser openChooser;
    private javax.swing.JDialog openDialog;
    private javax.swing.JMenuItem pressMouse;
    private javax.swing.JMenuItem releaseAction;
    private javax.swing.JMenuItem renameFile;
    private javax.swing.JMenuItem restartCom;
    private javax.swing.JButton runButton;
    private javax.swing.JFileChooser saveChooser;
    private javax.swing.JDialog saveDialog;
    private javax.swing.JMenuItem scrollMouse;
    private javax.swing.JMenuItem shutCom;
    private javax.swing.JButton shutdownButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JDialog subActionEditor;
    private javax.swing.JButton subAction_add;
    private javax.swing.JButton subAction_cancel;
    private javax.swing.JButton subAction_down;
    private javax.swing.JButton subAction_edit;
    private javax.swing.JList subAction_list;
    private javax.swing.JButton subAction_remove;
    private javax.swing.JButton subAction_submit;
    private javax.swing.JButton subAction_up;
    private javax.swing.JPopupMenu subFileMenu;
    private javax.swing.JMenuItem subFileMenu_changeAtrib;
    private javax.swing.JMenuItem subFileMenu_copy;
    private javax.swing.JMenuItem subFileMenu_del;
    private javax.swing.JMenuItem subFileMenu_makeDir;
    private javax.swing.JMenuItem subFileMenu_move;
    private javax.swing.JMenuItem subFileMenu_rename;
    private javax.swing.JPopupMenu subKeyMenu;
    private javax.swing.JMenu subKeyMenu_press;
    private javax.swing.JMenuItem subKeyMenu_press_ctrl;
    private javax.swing.JMenuItem subKeyMenu_press_del;
    private javax.swing.JMenuItem subKeyMenu_press_enter;
    private javax.swing.JMenuItem subKeyMenu_press_esc;
    private javax.swing.JMenuItem subKeyMenu_press_letter;
    private javax.swing.JMenuItem subKeyMenu_press_shift;
    private javax.swing.JMenu subKeyMenu_release;
    private javax.swing.JMenuItem subKeyMenu_release_ctrl;
    private javax.swing.JMenuItem subKeyMenu_release_del;
    private javax.swing.JMenuItem subKeyMenu_release_enter;
    private javax.swing.JMenuItem subKeyMenu_release_esc;
    private javax.swing.JMenuItem subKeyMenu_release_letter;
    private javax.swing.JMenuItem subKeyMenu_release_shfit;
    private javax.swing.JMenuItem subKeyMenu_type;
    private javax.swing.JPopupMenu subMouseMenu;
    private javax.swing.JMenuItem subMouseMenu_click;
    private javax.swing.JMenuItem subMouseMenu_move;
    private javax.swing.JMenuItem subMouseMenu_press;
    private javax.swing.JMenuItem subMouseMenu_release;
    private javax.swing.JMenuItem subMouseMenu_scroll;
    private javax.swing.JPopupMenu subShutMenu;
    private javax.swing.JMenuItem subShutMenu_log;
    private javax.swing.JMenuItem subShutMenu_restart;
    private javax.swing.JMenuItem subShutMenu_shutcom;
    private javax.swing.JLabel systemTime;
    private javax.swing.JDialog twoPathDialog;
    private javax.swing.JButton twoPath_cancel;
    private javax.swing.JButton twoPath_newPath;
    private javax.swing.JTextField twoPath_newText;
    private javax.swing.JButton twoPath_ok;
    private javax.swing.JButton twoPath_oldPath;
    private javax.swing.JTextField twoPath_oldText;
    // End of variables declaration//GEN-END:variables
}
