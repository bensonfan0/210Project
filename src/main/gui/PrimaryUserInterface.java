package gui;

import calendar.Day;
import model.user.MyProfile;
import persistance.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

// interface which will display training regiment based on user specifications
public class PrimaryUserInterface extends JFrame {

    private static final String ACCOUNTS_FILE = "./data/myProfiles.txt";
    private File accountFile;

    private int interfaceWidth = 1350;
    private int interfaceHeight = 600;

    private MyProfile myProfile;

    private JPanel panelMainPanel;
    private JPanel panelCoreAndSecondary;
    private JPanel panelProfileViewer;
    private JPanel panelStatisticsViewer;

    private JPanel panelAccessories;

    private JScrollPane scrollPaneAccessories;

    private JMenuBar menuBar;
    private JMenu menuSettings;
    private JMenu menuRecustomize;

    //private JMenuItem menuItemLoadAcct;
    private JMenuItem menuItemSave;
    private JMenuItem menuItemDeleteAcct;
    private JMenuItem menuItemExit;

    private JMenuItem menuItemMuscleFocus;
    private JMenuItem menuItemRecustomize;
    private JMenuItem menuItemMinutesAvailable;
    private JMenuItem menuItemPersonalRecords;
    private JMenuItem menuItemTrainingGoal;

    private JEditorPane editorPaneTrainingMonday;
    private JEditorPane editorPaneTrainingTuesday;
    private JEditorPane editorPaneTrainingWednesday;
    private JEditorPane editorPaneTrainingThursday;
    private JEditorPane editorPaneTrainingFriday;
    private JEditorPane editorPaneTrainingSaturday;
    private JEditorPane editorPaneTrainingSunday;

    private JEditorPane editorPaneAccessories;

    private JButton buttonNewPersonalRecord;

    private JLabel labelProfileBenchPR;
    private JLabel labelProfileSquatPR;
    private JLabel labelProfileDeadliftPR;
    private JLabel labelProfileMinutes;
    private JLabel labelProfileExperience;
    private JLabel labelProfileBodyGroup;

    private JLabel labelAccessoryText;


    public PrimaryUserInterface(MyProfile myProfile) {
        super("MyTrainingPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(interfaceWidth, interfaceHeight);
        setLayout(new GridBagLayout());

        // set up my profile related fields
        this.myProfile = myProfile;
        myProfile.programAssigner();
        accountFile = new File(ACCOUNTS_FILE);

        setUpPanels();
        setUpMenuBar();
        setUpLabels();
        setUpTextFields();
        setUpButtons();
        setUpScrollPane();

        setUpListener();

        setUpStatisticsViewer();
        setUpCoreAndSecondaryViewer();
        setUpAccessoryViewer();
        setUpProfileViewer();
        setUpMainPanel();


        add(panelMainPanel);
    }

    // EFFECTS: sets up scrollPane
    private void setUpScrollPane() {
        scrollPaneAccessories = new JScrollPane(editorPaneAccessories);

        scrollPaneAccessories.setPreferredSize(new Dimension(interfaceWidth / 3, interfaceHeight * 2 / 3));

        scrollPaneAccessories.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneAccessories.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    // MODIFIES: accessoryViewer
    //EFFECTS: adds labels to accessoryViewer panel
    private void setUpAccessoryViewer() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHEAST;
        c.gridy = 0;
        c.gridx = 0;
        panelAccessories.add(labelAccessoryText);
        c.anchor = GridBagConstraints.EAST;
        c.gridy = 1;
        //panelAccessories.add(editorPaneAccessories, c);
        panelAccessories.add(scrollPaneAccessories, c);
    }

    // MODIFIES: profileViewer
    //EFFECTS: adds labels to profileViewer panel
    private void setUpProfileViewer() {
        panelProfileViewer.add(labelProfileBenchPR);
        panelProfileViewer.add(Box.createRigidArea(new Dimension(0, 10)));
        panelProfileViewer.add(labelProfileSquatPR);
        panelProfileViewer.add(Box.createRigidArea(new Dimension(0, 10)));
        panelProfileViewer.add(labelProfileDeadliftPR);
        panelProfileViewer.add(Box.createRigidArea(new Dimension(0, 10)));
        panelProfileViewer.add(labelProfileMinutes);
        panelProfileViewer.add(Box.createRigidArea(new Dimension(0, 10)));
        panelProfileViewer.add(labelProfileExperience);
        panelProfileViewer.add(Box.createRigidArea(new Dimension(0, 10)));
        panelProfileViewer.add(labelProfileBodyGroup);
    }

    private void setUpStatisticsViewer() {
        panelStatisticsViewer.add(buttonNewPersonalRecord);
    }

    // MODIFIES: mainPanel
    // EFFECTS: adds panels to mainPanel in correct orientation
    private void setUpMainPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTH;
        panelMainPanel.add(panelProfileViewer, c);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        panelMainPanel.add(panelCoreAndSecondary, c);
        setUpMainPanelContinued(c);
    }

    // MODIFIES: mainPanel
    // EFFECTS: adds panels to mainPanel in correct orientation
    private void setUpMainPanelContinued(GridBagConstraints c) {
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        panelMainPanel.add(panelAccessories);
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(0,0,20,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 1;       //second row
        panelMainPanel.add(panelStatisticsViewer, c);
    }

    // MODIFIES: panelCoreAndSecondary
    // EFFECTS: adds editor panes to panel
    private void setUpCoreAndSecondaryViewer() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.ipadx = 5;
        c.anchor = GridBagConstraints.NORTH;
        panelCoreAndSecondary.add(editorPaneTrainingMonday, c);
        c.gridx = 1;
        panelCoreAndSecondary.add(editorPaneTrainingTuesday, c);
        c.gridx = 2;
        panelCoreAndSecondary.add(editorPaneTrainingWednesday, c);
        c.gridx = 3;
        panelCoreAndSecondary.add(editorPaneTrainingThursday, c);
        c.gridx = 4;
        panelCoreAndSecondary.add(editorPaneTrainingFriday, c);
        c.gridx = 5;
        panelCoreAndSecondary.add(editorPaneTrainingSaturday, c);
        c.gridx = 6;
        panelCoreAndSecondary.add(editorPaneTrainingSunday, c);
        //c.gridx = 7;
        //panelCoreAndSecondary.add(editorPaneAccessories, c);
    }

    // EFFECTS: instantiates buttons
    private void setUpButtons() {
        buttonNewPersonalRecord = new JButton("New Personal Record!");
    }

    // EFFECTS: instantiates textfields and adds training instructions
    private void setUpTextFields() {
        editorPaneTrainingMonday = new JEditorPane("text/html", printOutWork(0));
        editorPaneTrainingTuesday = new JEditorPane("text/html", printOutWork(1));
        editorPaneTrainingWednesday = new JEditorPane("text/html", printOutWork(2));
        editorPaneTrainingThursday = new JEditorPane("text/html", printOutWork(3));
        editorPaneTrainingFriday = new JEditorPane("text/html", printOutWork(4));
        editorPaneTrainingSaturday = new JEditorPane("text/html", printOutWork(5));
        editorPaneTrainingSunday = new JEditorPane("text/html", printOutWork(6));
        editorPaneAccessories = new JEditorPane("text/html", printOutAccessories());

        editorPaneTrainingMonday.setEditable(false);
        editorPaneTrainingTuesday.setEditable(false);
        editorPaneTrainingWednesday.setEditable(false);
        editorPaneTrainingThursday.setEditable(false);
        editorPaneTrainingFriday.setEditable(false);
        editorPaneTrainingSaturday.setEditable(false);
        editorPaneTrainingSunday.setEditable(false);
        editorPaneAccessories.setEditable(false);
    }

    //    EFFECTS: assigns accessories of days to labelAccessories
    private String printOutAccessories() {
        StringBuilder str = new StringBuilder();

        for (Day d : myProfile.getAssignedProgram().getMyTrainingWeek().getListOfDay()) {
            str.append(d.getAccessories());
        }

        return str.toString();
    }



    //    EFFECTS: system screen prints all the assigned training day instructions
    //             monday is index 0 ... sunday is index 6;
    private String printOutWork(int dayIndex) {
        String str = "";

        str = myProfile.getAssignedProgram().getMyTrainingWeek().getListOfDay().get(dayIndex).getTrainingInstructions();

        return str;
    }

    // EFFECTS: instantiates and sets up panels to correct size
    public void setUpPanels() {
        panelMainPanel = new JPanel();
        panelCoreAndSecondary = new JPanel();
        panelStatisticsViewer = new JPanel();
        panelProfileViewer = new JPanel();
        panelAccessories = new JPanel();

        panelMainPanel.setLayout(new GridBagLayout());
        panelCoreAndSecondary.setLayout(new GridBagLayout());
        panelStatisticsViewer.setLayout(new GridLayout());
        panelProfileViewer.setLayout(new BoxLayout(panelProfileViewer, BoxLayout.Y_AXIS));
        panelAccessories.setLayout(new GridBagLayout());

        panelMainPanel.setMinimumSize(new Dimension(interfaceWidth, interfaceHeight));
        panelMainPanel.setPreferredSize(new Dimension(interfaceWidth, interfaceHeight));
        panelMainPanel.setMaximumSize(new Dimension(interfaceWidth, interfaceHeight * 3 / 2));
    }


    // EFFECTS: instantiates Labels with proper text
    public void setUpLabels() {
        labelProfileBenchPR = new JLabel("BenchPress Personal Record: " + myProfile.getBenchPR());
        labelProfileSquatPR = new JLabel("Squat Personal Record: " + myProfile.getSquatPR());
        labelProfileDeadliftPR = new JLabel("Deadlift Personal Record: " + myProfile.getDlPR());
        labelProfileMinutes = new JLabel("Minutes/Workout: " + myProfile.getMaxTime());
        labelProfileExperience = new JLabel("Experience type: " + myProfile.getExperienceLevel());
        labelProfileBodyGroup = new JLabel("BodyGroup Focus: " + myProfile.getMuscleFocus());

        labelAccessoryText = new JLabel("Accessories to be done after main lifts (each set ~ 10-12 reps):");

    }

    // MODIFIES: this
    // EFFECTS: instantiates menuBar, and menuItems and adds to this
    public void setUpMenuBar() {
        instantiateMenuObjects();

        //menuSettings.add(menuItemLoadAcct);
        menuSettings.add(menuItemDeleteAcct);
        menuSettings.add(menuItemSave);
        menuSettings.add(menuItemExit);

        menuRecustomize.add(menuItemRecustomize);
//        menuRecustomize.add(menuItemMinutesAvailable);
//        menuRecustomize.add(menuItemMuscleFocus);
        menuRecustomize.add(menuItemPersonalRecords);
//        menuRecustomize.add(menuItemTrainingGoal);

        menuBar.add(menuSettings);
        menuBar.add(menuRecustomize);
        setJMenuBar(menuBar);
    }

    // EFFECTS: instantiates menuBar, and menuItems
    public void instantiateMenuObjects() {
        menuBar = new JMenuBar();
        menuSettings = new JMenu("Settings");

        //menuItemLoadAcct = new JMenuItem("Load Account");
        menuRecustomize = new JMenu("Recustomize");
        menuItemExit = new JMenuItem("Exit");
        menuItemDeleteAcct = new JMenuItem("Delete Account");
        menuItemSave = new JMenuItem("Save");

        menuItemRecustomize = new JMenuItem("Reset Preferences");
//        menuItemMinutesAvailable = new JMenuItem("Minutes Available");
//        menuItemMuscleFocus = new JMenuItem("Body Group Focus");
        menuItemPersonalRecords = new JMenuItem("Personal Records");
//        menuItemTrainingGoal = new JMenuItem("Training Goal");
    }

    // EFFECTS: sets up listeners
    private void setUpListener() {
        TheHandler theHandler = new TheHandler();
        menuItemSave.addActionListener(theHandler);
        menuItemExit.addActionListener(theHandler);
        menuItemDeleteAcct.addActionListener(theHandler);
        menuItemRecustomize.addActionListener(theHandler);
        buttonNewPersonalRecord.addActionListener(theHandler);
        menuItemPersonalRecords.addActionListener(theHandler);
    }

    private PrimaryUserInterface getThis() {
        return this;
    }

    //    this class will essentially handle all events
    private class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == menuItemSave) {
                saveMyProfile();
            } else if (e.getSource() == menuItemExit) {
                dispose();
                saveMyProfile();
                System.exit(0);
            } else if (e.getSource() == menuItemDeleteAcct) {
                deleteMyProfile();
            } else if (e.getSource() == menuItemRecustomize) {
                WelcomeInterface welcomeInterface = new WelcomeInterface(myProfile);
                welcomeInterface.setVisible(true);
                dispose();
            } else if (e.getSource() == buttonNewPersonalRecord) {
                PersonalRecordInspiration personalRecordInspiration = new PersonalRecordInspiration();
                personalRecordInspiration.setVisible(true);
            } else if (e.getSource() == menuItemPersonalRecords) {
                ChangePersonalRecordsOnly changePrOnly = new ChangePersonalRecordsOnly(myProfile, getThis());
                changePrOnly.setVisible(true);
                //dispose();
            }
            //JOptionPane.showMessageDialog(null, string);
        }

        // EFFECTS: deletes my profile
        private void deleteMyProfile() {
            if (accountFile.delete()) {
                JOptionPane.showMessageDialog(null, "Deletion Successful");
                openLoginScreen();
            } else {
                JOptionPane.showMessageDialog(null, "Error Cannot Delete");
            }
        }

        // EFFECTS: opens login screen
        private void openLoginScreen() {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
            dispose();
        }

        // EFFECTS: saves myProfile
        private void saveMyProfile() {
            try {
                Writer writer = new Writer(new File(ACCOUNTS_FILE));
                writer.write(myProfile);
                writer.close();
                System.out.println("myProfile saved to file " + ACCOUNTS_FILE);
                JOptionPane.showMessageDialog(null, "Profile Saved");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to save myProfile to " + ACCOUNTS_FILE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                // this is due to a programming error
            }
        }
    }


}
