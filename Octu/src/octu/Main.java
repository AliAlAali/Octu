/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import octu.core.Event;
import octu.core.FilerHandler;
import octu.core.Handler;
import octu.core.action.KeyStrokeAction;
import octu.core.action.Action;
import octu.core.action.DelayAction;
import octu.core.action.LooperAction;
import octu.core.action.MouseAction;
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
    private String selectedEvent;

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
                c = Calendar.getInstance();
                systemTime.setText(c.getTime().toString());
            }

        }, Math.min(60, 60 - c.get(Calendar.SECOND)), 1000);

        //adding filters for the save-open dialogs
        FileFilter filter = FilerHandler.getFileFilter();
        saveChooser.setFileFilter(filter);
        openChooser.setFileFilter(filter);

        
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
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        systemTime = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        historyList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        graph1 = new octu.graphics.Graph();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

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

        jButton14.setText("Delay");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Stop");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Looper");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
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
                            .addComponent(jButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
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
                    .addComponent(jButton14)
                    .addComponent(jButton15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton16)
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
        ShutDownMenu.add(logOff);

        restartCom.setText("Restart");
        ShutDownMenu.add(restartCom);

        shutCom.setText("Shutdown");
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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Right");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Middle");

        javax.swing.GroupLayout imageView1Layout = new javax.swing.GroupLayout(imageView1);
        imageView1.setLayout(imageView1Layout);
        imageView1Layout.setHorizontalGroup(
            imageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageView1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(imageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(imageView1Layout.createSequentialGroup()
                        .addComponent(mouseActionLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        imageView1Layout.setVerticalGroup(
            imageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageView1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(46, 46, 46)
                .addGroup(imageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mouseActionLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(imageView1, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        oneInText.setText("Enter");

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
                        .addComponent(oneInOk))
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
                    .addComponent(onePath_ok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(onePath_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE))
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
                    .addComponent(twoPath_ok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(twoPath_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE))
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
        menu_vk_press.add(menu_vk_shift);

        menu_vk_enter.setText("Enter");
        menu_vk_press.add(menu_vk_enter);

        menu_vk_del.setText("Delete");
        menu_vk_press.add(menu_vk_del);

        menu_vk_ctrl.setText("Ctrl");
        menu_vk_press.add(menu_vk_ctrl);

        menu_vk_esc.setText("ESC");
        menu_vk_press.add(menu_vk_esc);

        menu_vk_letter.setText("Letter");
        menu_vk_press.add(menu_vk_letter);

        KeystrokeMenu.add(menu_vk_press);

        menu_vk_release.setText("Release");

        menu_vk_shiftR.setText("Shift");
        menu_vk_release.add(menu_vk_shiftR);

        menu_vk_enterR.setText("Enter");
        menu_vk_release.add(menu_vk_enterR);

        menu_vk_delR.setText("Delete");
        menu_vk_release.add(menu_vk_delR);

        menu_vk_ctrlR.setText("Ctrl");
        menu_vk_release.add(menu_vk_ctrlR);

        menu_vk_escR.setText("ESC");
        menu_vk_release.add(menu_vk_escR);

        menu_vk_letterR.setText("Letter");
        menu_vk_release.add(menu_vk_letterR);

        KeystrokeMenu.add(menu_vk_release);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Octo");
        setIconImages(icons);

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

        jButton3.setText("New Action");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        jButton18.setText("Up");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Down");

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
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 289, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton12)
                    .addComponent(jButton18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton19))
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

        jButton10.setText("Run");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Stop");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
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
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)))
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

        jMenuItem4.setText("Help");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Developer");
        jMenu2.add(jMenuItem5);

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
        saveDialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        showConfirmDialog("Are you sure leaving the program?");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        openDialog.pack();
        centerDialog(openDialog);
        openDialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(eventList.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "You must have and select an event!");
            return;
        }
        newActionDialog.pack();
        centerDialog(newActionDialog);
        newActionDialog.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void actionFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionFileActionPerformed
        FileMenu.show(actionFile, actionFile.getX() / 2, 0);
    }//GEN-LAST:event_actionFileActionPerformed

    private void moveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveFileActionPerformed
        twoPathDialog.pack();
        twoPathDialog.setTitle("Move File: select path");
        centerDialog(twoPathDialog);
        twoPathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FilerHandler.getAbsoluteFilter());
    }//GEN-LAST:event_moveFileActionPerformed

    private void shutdownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutdownButtonActionPerformed
        ShutDownMenu.show(shutdownButton, shutdownButton.getX() / 2, 0);
    }//GEN-LAST:event_shutdownButtonActionPerformed

    private void mouseActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mouseActionButtonActionPerformed
        MouseActionMenu.show(mouseActionButton, mouseActionButton.getX() / 2, 0);
    }//GEN-LAST:event_mouseActionButtonActionPerformed

    private void clickMouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickMouseActionPerformed
        MouseActionDialog.pack();
        centerDialog(MouseActionDialog);
        MouseActionDialog.setVisible(true);
        newActionDialog.dispose();
    }//GEN-LAST:event_clickMouseActionPerformed

    private void pressMouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressMouseActionPerformed
        MouseActionDialog.pack();
        centerDialog(MouseActionDialog);
        MouseActionDialog.setVisible(true);
        newActionDialog.dispose();
    }//GEN-LAST:event_pressMouseActionPerformed

    private void releaseActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseActionActionPerformed
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
        MouseAction action = new MouseAction(octu.core.Event.POR_START, MouseAction.ACTION_MOVE, null);
        action.setX(Integer.parseInt(cordX.getText()));
        action.setY(Integer.parseInt(cordY.getText()));
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        model.addElement(action.getDescription());
        handler.getEvent(selectedEvent).addAction(action);
    }//GEN-LAST:event_cordOkActionPerformed

    private void cordCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cordCancelActionPerformed
        CoordinateInputDialog.dispose();
    }//GEN-LAST:event_cordCancelActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        handler.start();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        OneValueInput.pack();
        centerDialog(OneValueInput);
        OneValueInput.setVisible(true);
        newActionDialog.dispose();
        oneInputID = 0;
    }//GEN-LAST:event_jButton14ActionPerformed

    private void oneInOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneInOkActionPerformed
        switch (oneInputID) {
            case 0:
                DelayAction action = new DelayAction(Event.POR_START, 3 * 1000); // 3 seconds
                DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
                model.addElement(action.getDescription());
                Event event = handler.getEvent(selectedEvent);
                event.addAction(action);
                OneValueInput.dispose();
                break;
        }
    }//GEN-LAST:event_oneInOkActionPerformed

    private void oneInCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneInCancelActionPerformed
        OneValueInput.dispose();
    }//GEN-LAST:event_oneInCancelActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        StopApplicationAction action = new StopApplicationAction(Event.POR_START); // 3 seconds
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        model.addElement(action.getDescription());
        handler.getEvent(selectedEvent).addAction(action);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        LooperAction act = new LooperAction(Event.POR_START);
        act.addAction(new MouseAction(Event.POR_START, MouseAction.ACTION_CLICK, null));
        act.addAction(new StopApplicationAction(Event.POR_START));
        act.addAction(new DelayAction(Event.POR_START, 2000));
        act.addAction(new MouseAction(Event.POR_START, MouseAction.ACTION_CLICK, null));
        act.addAction(new StopApplicationAction(Event.POR_START));
        act.addAction(new DelayAction(Event.POR_START, 2000));
        act.addAction(new MouseAction(Event.POR_START, MouseAction.ACTION_CLICK, null));
        act.addAction(new StopApplicationAction(Event.POR_START));
        act.addAction(new DelayAction(Event.POR_START, 2000));
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        model.addElement(act.getDescription());
        handler.getEvent(selectedEvent).addAction(act);

    }//GEN-LAST:event_jButton16ActionPerformed

    private void lunchAppButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunchAppButtonActionPerformed
        //adding actoins for testing propuses only
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        MouseAction act1 = new MouseAction(Event.POR_START, MouseAction.ACTION_MOVE, null);
        act1.setX(200);
        act1.setY(200);
        DelayAction d1 = new DelayAction(Event.POR_START, 2000);
        MouseAction act2 = new MouseAction(Event.POR_START, MouseAction.ACTION_MOVE, null);
        act2.setX(500);
        act2.setY(500);
        DelayAction d2 = new DelayAction(Event.POR_START, 3000);
        MouseAction act3 = new MouseAction(Event.POR_START, MouseAction.ACTION_MOVE, null);
        act3.setX(600);
        act3.setY(600);
        DelayAction d3 = new DelayAction(Event.POR_START, 5000);
        handler.getEvent(selectedEvent).addAction(act1);
        handler.getEvent(selectedEvent).addAction(d1);
        handler.getEvent(selectedEvent).addAction(act2);
        handler.getEvent(selectedEvent).addAction(d2);
        handler.getEvent(selectedEvent).addAction(act3);
        handler.getEvent(selectedEvent).addAction(d3);
        model.addElement(act1.getDescription());
        model.addElement(d1.getDescription());
        model.addElement(act2.getDescription());
        model.addElement(d2.getDescription());
        model.addElement(act3.getDescription());
        model.addElement(d3.getDescription());

    }//GEN-LAST:event_lunchAppButtonActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        handler.flush();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selection = actionList.getSelectedIndex();
        int eventNum = Event.POR_START; //change this later, indicate selected event
        handler.removeAction(handler.findAction(eventNum, selection));
        //events.removeAction(selection);
        //testEvent.removeAction(selection);
        DefaultListModel<String> model = (DefaultListModel<String>) actionList.getModel();
        model.remove(selection);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void deleteFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFileActionPerformed
        onePathDialog.pack();
        onePathDialog.setTitle("Delete File: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FilerHandler.getAbsoluteFilter());
    }//GEN-LAST:event_deleteFileActionPerformed

    private void renameFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameFileActionPerformed
        onePathDialog.pack();
        onePathDialog.setTitle("Rename File: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FilerHandler.getAbsoluteFilter());
    }//GEN-LAST:event_renameFileActionPerformed

    private void makeDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeDirActionPerformed
        onePathDialog.pack();
        onePathDialog.setTitle("Make new Directory: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FilerHandler.getDirectoryFilter());
    }//GEN-LAST:event_makeDirActionPerformed

    private void changeAttribActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeAttribActionPerformed
        onePathDialog.pack();
        onePathDialog.setTitle("Hide/Unhide File: select path");
        centerDialog(onePathDialog);
        onePathDialog.setVisible(true);
        newActionDialog.dispose();
        fileChooser_chooser.setFileFilter(FilerHandler.getAbsoluteFilter());
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
        if (title.startsWith("Rename")) {
            //ask for the new file name
        } else if (title.startsWith("Delete")) {
            //just delete
        } else if (title.startsWith("Make")) {
            //take the name of the file - make sure its only folders
        } else if (title.startsWith("Hide")) {
            //ask whether to hide or unhide
        }
    }//GEN-LAST:event_onePath_okActionPerformed

    private void twoPath_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPath_cancelActionPerformed
        twoPathDialog.dispose();
    }//GEN-LAST:event_twoPath_cancelActionPerformed

    private void twoPath_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPath_okActionPerformed
        String title = twoPathDialog.getTitle();
        if(title.startsWith("Copy")){
            
        }else if(title.startsWith("Move")){
            
        }
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
        fileChooser_chooser.setFileFilter(FilerHandler.getAbsoluteFilter());
    }//GEN-LAST:event_copyFileActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
//        newActionDialog.dispose();
//        String text = JOptionPane.showInputDialog(this,"Type text for Keystroke Action:");
//        KeyStrokeAction action = new KeyStrokeAction(Event.POR_START, text);
//        addElementToList(actionList, action.getDescription());
//        testEvent.addAction(action);
//        handler.addEvent(testEvent);
        KeystrokeMenu.show(jButton13, jButton13.getX()/2, 0);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void scrollMouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scrollMouseActionPerformed
        newActionDialog.dispose();
        int scroll = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the Y change: "));
        MouseAction act = new MouseAction(Event.POR_START, MouseAction.ACTION_SCROLL, null);
        act.setY(scroll);
        addElementToList(actionList, act.getDescription());
        handler.getEvent(selectedEvent).addAction(act);
       
    }//GEN-LAST:event_scrollMouseActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void menu_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_typeActionPerformed
        newActionDialog.dispose();
        String text = JOptionPane.showInputDialog(this,"Type text for Keystroke Action:");
        KeyStrokeAction action = new KeyStrokeAction(Event.POR_START, text);
        addElementToList(actionList, action.getDescription());
        handler.getEvent(selectedEvent).addAction(action);
    }//GEN-LAST:event_menu_typeActionPerformed

    private void bt_event_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_startActionPerformed
        addEvent("onStart", Event.POR_START);
    }//GEN-LAST:event_bt_event_startActionPerformed

    private void bt_event_goingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_goingActionPerformed
        addEvent("onGoing", Event.POR_GOING);
    }//GEN-LAST:event_bt_event_goingActionPerformed

    private void bt_event_loopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_loopActionPerformed
        addEvent("Loop", Event.POR_LOOP);
    }//GEN-LAST:event_bt_event_loopActionPerformed

    private void bt_event_scheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_scheduleActionPerformed
        addEvent("Schedule", Event.POR_SCHEDULE);
    }//GEN-LAST:event_bt_event_scheduleActionPerformed

    private void bt_event_triggerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_event_triggerActionPerformed
        addEvent("Trigger", Event.POR_TRIGGER);
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

    public void addEvent(String name, int por){
        Event event = new Event(por);
        event.setName(name);
        if(handler.addEvent(event))
            addElementToList(eventList, event.getName());
        EventSelector.dispose();
    }
    
    public void addElementToList(JList list, String elem){
        DefaultListModel<String> model = (DefaultListModel<String>)list.getModel();
        model.addElement(elem);
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
    private javax.swing.JButton actionFile;
    private javax.swing.JList actionList;
    private javax.swing.JButton bt_event_going;
    private javax.swing.JButton bt_event_loop;
    private javax.swing.JButton bt_event_schedule;
    private javax.swing.JButton bt_event_start;
    private javax.swing.JButton bt_event_trigger;
    private javax.swing.JMenuItem changeAttrib;
    private javax.swing.JMenuItem clickMouse;
    private javax.swing.JMenuItem copyFile;
    private javax.swing.JButton cordCancel;
    private javax.swing.JButton cordOk;
    private javax.swing.JTextField cordX;
    private javax.swing.JTextField cordY;
    private javax.swing.JMenuItem deleteFile;
    private javax.swing.JList eventList;
    private javax.swing.JFileChooser fileChooser_chooser;
    private octu.graphics.Graph graph1;
    private javax.swing.JList historyList;
    private octu.graphics.ImageView imageView1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JMenuItem moveFile;
    private javax.swing.JMenuItem moveMouse;
    private javax.swing.JDialog newActionDialog;
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
    private javax.swing.JFileChooser saveChooser;
    private javax.swing.JDialog saveDialog;
    private javax.swing.JMenuItem scrollMouse;
    private javax.swing.JMenuItem shutCom;
    private javax.swing.JButton shutdownButton;
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
