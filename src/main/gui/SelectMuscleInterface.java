package gui;

import model.MyProgramPalConstants;
import model.user.MyProfile;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener; // this "listens" for user to interact with gui
import java.awt.event.ActionEvent;

// allows users to select between 5 body parts to focus mesocycle upon (chest, back, arms, legs, or abs).
public class SelectMuscleInterface extends JFrame {

    private MyProfile myProfile;

    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private JPanel picture;

    private JLabel labelSelectButtons;

    private JButton chest;
    private JButton back;
    private JButton arms;
    private JButton legs;
    private JButton abs;

    private JProgressBar progressBar;

    private int interfaceWidth = 700;
    private int interfaceHeight = 400;


    public SelectMuscleInterface(MyProfile myProfile) {
        super("MyTrainingPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(interfaceWidth, interfaceHeight);
        setLayout(new GridBagLayout());

//        set up myTrainingPal related fields
        this.myProfile = myProfile;

        setUpPanels();
        addButtons();
        setProgressBar();
        setLabels();
        setMainPanel();
        setListeners();

        add(mainPanel);
    }

//    EFFECTS: sets up labels
    private void setLabels() {
        labelSelectButtons = new JLabel("Choose which body part to focus on:");
    }

    //    EFFECTS: sets progressBar
    private void setProgressBar() {
        progressBar = new JProgressBar();

        progressBar.setMaximum(7);
        progressBar.setValue(2);

        progressBar.setPreferredSize(new Dimension(650, 25));

    }

    //    EFFECTS: sets listeners
    private void setListeners() {
        TheHandler theHandler = new TheHandler();
        chest.addActionListener(theHandler);
        back.addActionListener(theHandler);
        arms.addActionListener(theHandler);
        legs.addActionListener(theHandler);
        abs.addActionListener(theHandler);
    }


    // MODIFIES: mainPanel
    //    EFFECTS: adds panels to mainPanel in correct orientation
    private void setMainPanel() {
        GridBagConstraints c = new GridBagConstraints();
        // the weights the component should have in concern to them being added to a container that is larger
        // than its preferred size
        c.weightx = 0.5;
        c.weighty = 1.0;

        // the coordinates of the component
        c.gridx = 3;
        c.gridy = 0;

        // the way the component should be "glued" to the container
        c.anchor = GridBagConstraints.LINE_START;

        // the way the component should expand in size if it is placed in a container that is larger
        // than its preferred size
        c.fill = GridBagConstraints.HORIZONTAL;

        c.anchor = GridBagConstraints.NORTH;
        mainPanel.add(progressBar, c);

        c.gridy = 1;
        mainPanel.add(labelSelectButtons, c);

        c.gridy = 2;
        mainPanel.add(buttonsPanel, c);

    }

    //    EFFECTS: instantiates panels and sets layout
    private void setUpPanels() {
        mainPanel = new JPanel();
        buttonsPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new GridBagLayout());

        mainPanel.setMinimumSize(new Dimension(interfaceWidth, interfaceHeight));
    }

//    MODIFIES: this
//    EFFECTS: creates constraints, applies to buttons, and adds to "button" JPanel
    private void addButtons() {
        GridBagConstraints c = new GridBagConstraints();
        createLabels();

        c.fill = GridBagConstraints.VERTICAL;
        c.ipadx = 60;
        c.ipady = 20;
        buttonsPanel.add(chest, c);

        c.gridy = 1;
        buttonsPanel.add(back, c);

        c.gridy = 2;
        buttonsPanel.add(arms, c);

        c.gridy = 3;
        buttonsPanel.add(legs, c);

        c.gridy = 4;
        buttonsPanel.add(abs, c);
    }

    // EFFECTS: sets up listeners
//    EFFECTS: creates and labels the buttons
    private void createLabels() {
        chest = new JButton("Chest");
        back = new JButton("Back");
        arms = new JButton("Arms");
        legs = new JButton("Legs");
        abs = new JButton("Abs");
    }


    //    this class will essentially handle all events
    private class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String string = "";

//            big else to check which "item" user interacted with
            if (e.getSource() == chest) {
                myProfile.setMuscleFocus(MyProgramPalConstants.CHEST);
                openWeekAvailability();
            } else if (e.getSource() == back) {
                myProfile.setMuscleFocus(MyProgramPalConstants.BACK);
                openWeekAvailability();
            } else if (e.getSource() == arms) {
                myProfile.setMuscleFocus(MyProgramPalConstants.ARMS);
                openWeekAvailability();
            } else if (e.getSource() == legs) {
                myProfile.setMuscleFocus(MyProgramPalConstants.LEGS);
                openWeekAvailability();
            } else if (e.getSource() == abs) {
                myProfile.setMuscleFocus(MyProgramPalConstants.ABS);
                openWeekAvailability();
            }

            //JOptionPane.showMessageDialog(null, string);
        }
    }

    // EFFECTS: opens WeekAvailability interface
    private void openWeekAvailability() {
        ChooseWeekAvailabilityInterface chooseWeekAvailability = new ChooseWeekAvailabilityInterface(myProfile);
        chooseWeekAvailability.setVisible(true);
        dispose();
    }
}
