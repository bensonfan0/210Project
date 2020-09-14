package gui;

import model.user.MyProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// produces panel allowing users to select from the 7 days of the week to train
public class ChooseWeekAvailabilityInterface extends JFrame {

    private MyProfile myProfile;
    private List<Boolean> myWeek;

//    a panel that contains buttons for organization
    private JPanel mainPanel;
    private JPanel weekButtons;
    private JPanel picture;

    private JButton monday;
    private JButton tuesday;
    private JButton wednesday;
    private JButton thursday;
    private JButton friday;
    private JButton saturday;
    private JButton sunday;

    private JButton next;

    private JLabel topDialogue;

    private JProgressBar progressBar;

    private int interfaceWidth = 700;
    private int interfaceHeight = 400;

    public ChooseWeekAvailabilityInterface(MyProfile myProfile) {
        super("MyTrainingPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(interfaceWidth, interfaceHeight);
        setLayout(new GridBagLayout());

//        initialize myTrainingPal related fields
        this.myProfile = myProfile;
        myWeek = new ArrayList<>();
        myWeek = Arrays.asList(false, false, false, false, false, false, false);

        setUpPanels();
        initializeDialogue();
        addButtons();
        assignActionListener();
        setProgressBar();
        setMainPanel();

        add(mainPanel);


    }

    //    EFFECTS: sets progressBar
    private void setProgressBar() {
        progressBar = new JProgressBar();

        progressBar.setMaximum(7);
        progressBar.setValue(3);

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
        mainPanel.add(topDialogue, c);
        c.gridy = 2;
        mainPanel.add(weekButtons, c);
    }

    //    EFFECTS: instantiates panels and sets layout
    private void setUpPanels() {
        mainPanel = new JPanel();
        weekButtons = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        weekButtons.setLayout(new GridBagLayout());
    }


    //    MODIFIES: this
//    EFFECTS: create and add JLabels
    private void initializeDialogue() {
        topDialogue = new JLabel("Select the days you are free to train:");

    }

    //    MODIFIES: this
//    EFFECTS: creates constraints, applies to buttons, and adds to "button" JPanel
    private void addButtons() {
        GridBagConstraints c = new GridBagConstraints();
        createButtons();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 240;
        weekButtons.add(monday, c);

        c.gridx = 1;
        weekButtons.add(tuesday, c);

        c.gridx = 2;
        weekButtons.add(wednesday, c);

        c.gridx = 3;
        weekButtons.add(thursday, c);

        c.gridx = 4;
        weekButtons.add(friday, c);

        c.gridx = 5;
        weekButtons.add(saturday, c);

        c.gridx = 6;
        weekButtons.add(sunday, c);

        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        add(weekButtons, c);

        otherButtons();
    }

//    EFFECTS: continues to add buttons
    private void otherButtons() {
        GridBagConstraints c = new GridBagConstraints();
        weekButtons.add(next, c);
    }

//    EFFECTS: creates JButtons with appropriate names
    private void createButtons() {
        monday = new JButton("Mon");
        tuesday = new JButton("Tue");
        wednesday = new JButton("Wed");
        thursday = new JButton("Thu");
        friday = new JButton("Fri");
        saturday = new JButton("Sat");
        sunday = new JButton("Sun");

        next = new JButton("Continue");
    }

    // EFFECTS: sets up listeners
    private void assignActionListener() {
        TheHandler theHandler = new TheHandler();

        monday.addActionListener(theHandler);
        tuesday.addActionListener(theHandler);
        wednesday.addActionListener(theHandler);
        thursday.addActionListener(theHandler);
        friday.addActionListener(theHandler);
        saturday.addActionListener(theHandler);
        sunday.addActionListener(theHandler);

        next.addActionListener(theHandler);
    }




    //EFFECTS: if a button is pressed once then change day availability to true.
    //         handler class to handle any actions user performs
    private class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (monday.equals(e.getSource())) {
                setButtonClicked(monday);
                handleDayAvailable(monday, 0);
            } else if (tuesday.equals(e.getSource())) {
                setButtonClicked(tuesday);
                handleDayAvailable(tuesday, 1);
            } else if (wednesday.equals(e.getSource())) {
                setButtonClicked(wednesday);
                handleDayAvailable(wednesday, 2);
            } else if (thursday.equals(e.getSource())) {
                setButtonClicked(thursday);
                handleDayAvailable(thursday, 3);
            } else if (friday.equals(e.getSource())) {
                setButtonClicked(friday);
                handleDayAvailable(friday, 4);
            } else {
                actionPerformedContinued(e);
            }

        }

        public void actionPerformedContinued(ActionEvent e) {

            if (saturday.equals(e.getSource())) {
                setButtonClicked(saturday);
                handleDayAvailable(saturday, 5);
            } else if (sunday.equals(e.getSource())) {
                setButtonClicked(sunday);
                handleDayAvailable(sunday, 6);
            } else if (next.equals(e.getSource())) {
                openMinutesAvailable();
            }
        }

        // MODIFIES: this
        // EFFECTS: checks off which days were checked as available to train
        private void handleDayAvailable(JButton day, int listIndex) {
            if (day.getBackground().equals(Color.ORANGE)) {
                myWeek.set(listIndex, true);
            } else {
                myWeek.set(listIndex, false);
            }
        }

        // EFFECTS:
        private void setButtonClicked(JButton day) {
            if (!day.getBackground().equals(Color.ORANGE)) {
                day.setBackground(Color.ORANGE);
                day.setOpaque(true);
            } else {
                day.setBackground(Color.white);
                day.setOpaque(false);
            }

        }

        // MODIFIES: this, myProfile
        // EFFECTS: open main interface and sets available week to myProfile
        private void openMinutesAvailable() {
            myProfile.setMyWeek(myWeek);
            if (daysAvailable() < 3) {
                JOptionPane.showMessageDialog(null, "Training under 3 days a week is suboptimal");
            }
            MinutesAvailableInterface minutesAvailableInterface = new MinutesAvailableInterface(myProfile);
            minutesAvailableInterface.setVisible(true);
            dispose();
        }

        // EFFECTS: calculates how many days available to train
        private int daysAvailable() {
            int numDaysAvailable = 0;
            for (boolean b : myWeek) {
                if (b) {
                    numDaysAvailable++;
                }
            }
            return numDaysAvailable;
        }
    }
}
