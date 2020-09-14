package gui;

import model.MyProgramPalConstants;
import model.user.MyProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// users can input their preference between a strength or hypertrophy orientated program
public class StrengthOrHypertrophyInterface extends JFrame {

    private MyProfile myProfile;

    private int interfaceWidth = 700;
    private int interfaceHeight = 400;

    private JPanel mainPanel;
    private JPanel buttonPanel;

    private JButton strengthButton;
    private JButton hypertrophyButton;

    private JLabel textBox;

    private JProgressBar progressBar;



    public StrengthOrHypertrophyInterface(MyProfile myProfile) {
        super("MyTrainingPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(interfaceWidth, interfaceHeight);
        setLayout(new GridBagLayout());

//        set up myTrainingPal related fields
        this.myProfile = myProfile;

        setUpPanels();
        setUpRegimentButton();
        setLabels();
        setListeners();
        setProgressBar();
        setMainPanel();

        add(mainPanel);
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
        mainPanel.add(textBox, c);

        c.gridy = 2;
        mainPanel.add(buttonPanel, c);
    }

    private void setProgressBar() {
        progressBar = new JProgressBar();

        progressBar.setMaximum(7);
        progressBar.setValue(5);

        progressBar.setPreferredSize(new Dimension(650, 25));

    }

    private void setLabels() {
        textBox = new JLabel("Select overall training style:");
    }

    private void setUpPanels() {
        mainPanel = new JPanel();
        buttonPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        buttonPanel.setLayout(new GridBagLayout());
    }

    private void setUpRegimentButton() {
        strengthButton = new JButton("Strength");
        hypertrophyButton = new JButton("Hypertrophy");

        addToButtonLabel();
    }

    private void addToButtonLabel() {
        buttonPanel.add(strengthButton);
        buttonPanel.add(hypertrophyButton);
    }

    // EFFECTS: sets up listeners
    private void setListeners() {
        TheHandler theHandler = new TheHandler();

        strengthButton.addActionListener(theHandler);
        hypertrophyButton.addActionListener((theHandler));
    }


    //    this class will essentially handle all events
    private class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == strengthButton) {
                myProfile.setLiftingGoal(MyProgramPalConstants.STRENGTH_COMMAND);
                openPersonalRecordInterface();
            } else if (e.getSource() == hypertrophyButton) {
                myProfile.setLiftingGoal(MyProgramPalConstants.HYPERTROPHY_COMMAND);
                openPersonalRecordInterface();
            }

            //JOptionPane.showMessageDialog(null, string);
        }

        public void openPersonalRecordInterface() {
            PersonalRecordInterface personalRecordInterface = new PersonalRecordInterface(myProfile);
            personalRecordInterface.setVisible(true);
            dispose();
        }
    }
}
