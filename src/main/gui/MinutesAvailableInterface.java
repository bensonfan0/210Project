package gui;

import model.user.MyProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// allows users to choose between 6 options of time availability
public class MinutesAvailableInterface extends JFrame {

    private MyProfile myProfile;
    private int interfaceWidth = 700;
    private int interfaceHeight = 400;

    private JPanel mainPanel;

    private JPanel minuteButtonPanel;
    private JButton fortyFiveMin;
    private JButton sixtyMin;
    private JButton seventyFiveMinute;
    private JButton ninetyMinutes;
    private JButton oneHundredTwentyMinutes;
    private JButton oneHundredTwentyMinutesPlus;

    private JProgressBar progressBar;

    private JPanel labelPanel;
    private JLabel chooseTimeText;

    public MinutesAvailableInterface(MyProfile myProfile) {
        super("MyTrainingPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(interfaceWidth, interfaceHeight);
        setLayout(new GridBagLayout());

        this.myProfile = myProfile;
        setUpPanels();
        setMinuteButtons();
        setLabels();
        setListeners();
        setProgressBar();

        setMainPanel();

        add(mainPanel);
    }

    //    EFFECTS: sets progressBar
    private void setProgressBar() {
        progressBar = new JProgressBar();

        progressBar.setMaximum(7);
        progressBar.setValue(4);

        progressBar.setPreferredSize(new Dimension(650, 25));

    }

    // MODIFIES: this
    // EFFECTS: adds panels to mainPanel in correct orientation
    private void setMainPanel() {
        GridBagConstraints c = new GridBagConstraints();
        // the weights the component should have in concern to them being added to a container that is larger
        // than its preferred size
        c.weightx = 0.5;
        c.weighty = 0.5;

        // the coordinates of the component
        c.gridx = 0;
        c.gridy = 0;

        // the way the component should be "glued" to the container
        c.anchor = GridBagConstraints.LINE_START;

        // the way the component should expand in size if it is placed in a container that is larger
        // than its preferred size
        c.fill = GridBagConstraints.HORIZONTAL;

        mainPanel.add(progressBar, c);

        c.gridy = 1;
        mainPanel.add(labelPanel, c);

        c.gridy = 2;
        mainPanel.add(minuteButtonPanel, c);
    }


//   EFFECTS: sets up panels
    private void setUpPanels() {
        minuteButtonPanel = new JPanel();
        mainPanel = new JPanel();
        labelPanel = new JPanel();

        minuteButtonPanel.setLayout(new GridBagLayout());
        mainPanel.setLayout(new GridBagLayout());
        labelPanel.setLayout(new GridBagLayout());

    }


    //   EFFECTS: creates buttons and adds to minuteButtonPanel
    public void setMinuteButtons() {
        fortyFiveMin = new                 JButton("45 Minutes    (3/4    Hr)");
        sixtyMin = new                     JButton("60 Minutes      (1      Hr)");
        seventyFiveMinute = new            JButton("75 Minutes    (1 1/4 Hrs)");
        ninetyMinutes = new                JButton("90 Minutes    (1 1/2 Hrs)");
        oneHundredTwentyMinutes = new      JButton("120 Minutes     (2     Hrs)");
        oneHundredTwentyMinutesPlus = new  JButton("120 Minutes+  (2+    Hrs)");

        addMinuteButtons();
    }

    // MODIFIES: this
    //   EFFECTS: adds to minuteButtonPanel
    public void addMinuteButtons() {
        GridBagConstraints c = new GridBagConstraints();

        // the weights the component should have in concern to them being added to a container that is larger
        // than its preferred size
        c.weightx = 0.5;
        c.weighty = 0.5;

        // the coordinates of the component
        c.gridx = 0;
        c.gridy = 0;

        // the way the component should be "glued" to the container
        c.anchor = GridBagConstraints.LINE_START;

        // the way the component should expand in size if it is placed in a container that is larger
        // than its preferred size
        c.fill = GridBagConstraints.HORIZONTAL;
        minuteButtonPanel.add(fortyFiveMin, c);

        c.gridy = 1;
        minuteButtonPanel.add(sixtyMin, c);

        c.gridy = 2;
        minuteButtonPanel.add(seventyFiveMinute, c);

        c.gridy = 3;
        minuteButtonPanel.add(ninetyMinutes, c);

        c.gridy = 4;
        minuteButtonPanel.add(oneHundredTwentyMinutes, c);

        c.gridy = 5;
        minuteButtonPanel.add(oneHundredTwentyMinutesPlus, c);
    }

    // MODIFIES: this
//    EFFECTS: sets up labels and adds to labelPanel
    public void setLabels() {
        GridBagConstraints c = new GridBagConstraints();
        chooseTimeText = new JLabel("Select available minutes per training session:");

        labelPanel.add(chooseTimeText);
    }

    // MODIFIES: this
    // EFFECTS: sets up listeners
    public void setListeners() {
        TheHandler theHandler = new TheHandler();
        fortyFiveMin.addActionListener(theHandler);
        sixtyMin.addActionListener(theHandler);
        seventyFiveMinute.addActionListener(theHandler);
        ninetyMinutes.addActionListener(theHandler);
        oneHundredTwentyMinutes.addActionListener(theHandler);
        oneHundredTwentyMinutesPlus.addActionListener(theHandler);

    }

    //    this class will essentially handle all events
    private class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == fortyFiveMin) {
                myProfile.setMaxTime(45);
                openStrengthOrHyperTrophyInterface();
            } else if (e.getSource() == sixtyMin) {
                myProfile.setMaxTime(60);
                openStrengthOrHyperTrophyInterface();
            } else if (e.getSource() == seventyFiveMinute) {
                myProfile.setMaxTime(75);
                openStrengthOrHyperTrophyInterface();
            } else if (e.getSource() == ninetyMinutes) {
                myProfile.setMaxTime(90);
                openStrengthOrHyperTrophyInterface();
            } else if (e.getSource() == oneHundredTwentyMinutes) {
                myProfile.setMaxTime(120);
                openStrengthOrHyperTrophyInterface();
            } else if (e.getSource() == oneHundredTwentyMinutesPlus) {
                myProfile.setMaxTime(200);
                openStrengthOrHyperTrophyInterface();
            }


            //JOptionPane.showMessageDialog(null, string);
        }

        // MODIFIES: this
        // EFFECT: opens new interface
        public void openStrengthOrHyperTrophyInterface() {
            StrengthOrHypertrophyInterface sorhInterface = new StrengthOrHypertrophyInterface(myProfile);
            sorhInterface.setVisible(true);
            dispose();
        }


    }

}
