package gui;

import model.MyProgramPalConstants;
import model.user.MyProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// allows users to record their estimated/actual one rep max for bench, squat, deadlift
public class PersonalRecordInterface extends JFrame {
    private MyProfile myProfile;

    private int interfaceWidth = 700;
    private int interfaceHeight = 400;

    private JPanel mainPanel;
    private JPanel textFieldPanel;

    protected JPanel benchPanel;
    protected JPanel squatPanel;
    protected JPanel deadliftPanel;

    protected JLabel benchLabel;
    protected JLabel squatLabel;
    protected JLabel deadliftLabel;

    protected JTextField benchPR;
    protected JTextField squatPR;
    protected JTextField deadliftPR;

    private JButton buttonContinue;

    private JLabel textBox;

    private JProgressBar progressBar;



    public PersonalRecordInterface(MyProfile myProfile) {
        super("MyTrainingPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(interfaceWidth, interfaceHeight);
        setLayout(new GridBagLayout());

//        set up myTrainingPal related fields
        this.myProfile = myProfile;

//        NOTE: the order of these matter, the methods depend on one another
        setUpPanels();
        setLabels();
        setTextFields();
        setButtons();
        setListeners();
        setProgressBar();
        setMainPanel();

        add(mainPanel);
    }

    private void setButtons() {
        buttonContinue = new JButton("Continue");
    }

    private void setTextFields() {
        benchPR = new JTextField();
        squatPR = new JTextField();
        deadliftPR = new JTextField();


        benchPR.setColumns(5);
        squatPR.setColumns(5);
        deadliftPR.setColumns(5);

        benchPanel.add(benchPR);
        squatPanel.add(squatPR);
        deadliftPanel.add(deadliftPR);

        // should be horizontal
        textFieldPanel.add(benchPanel);
        textFieldPanel.add(squatPanel);
        textFieldPanel.add(deadliftPanel);
    }

    // MODIFIES: mainPanel
    // EFFECTS: adds panels to mainPanel in correct orientation
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
        mainPanel.add(textFieldPanel, c);

        c.gridy = 5;
        mainPanel.add(buttonContinue);
    }

    private void setProgressBar() {
        progressBar = new JProgressBar();

        progressBar.setMaximum(7);
        progressBar.setValue(6);

        progressBar.setPreferredSize(new Dimension(650, 25));

    }

    //    MODIFIES: benchPanel. squatPanel, deadlift Panel
    //    EFFECTS: initializes up labels
    private void setLabels() {
        textBox = new JLabel("Enter maximum one repetition personal record for each lift:");

        benchLabel = new JLabel("BenchPress:");
        squatLabel = new JLabel("Squat:");
        deadliftLabel = new JLabel("Deadlift:");

        benchPanel.add(benchLabel);
        squatPanel.add(squatLabel);
        deadliftPanel.add(deadliftLabel);
    }

    // EFFECTS: initializes Panels
    private void setUpPanels() {
        mainPanel = new JPanel();
        textFieldPanel = new JPanel();
        benchPanel = new JPanel();
        squatPanel = new JPanel();
        deadliftPanel = new JPanel();

        mainPanel.setMinimumSize(new Dimension(interfaceWidth, interfaceHeight));
        textFieldPanel.setLayout(new GridBagLayout());
    }

    // EFFECTS: sets up listeners
    private void setListeners() {
        TheHandler theHandler = new TheHandler();

        buttonContinue.addActionListener(theHandler);

    }

    // EFFECTS: handler class to handle all user actions (inputs PR from fields to myProfile)
    protected class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int prHolder;
            String stringHolder;

            if (e.getSource() == buttonContinue) {
                stringHolder = cleanString(benchPR.getText());
                prHolder = Integer.parseInt(stringHolder);
                myProfile.setBenchPR(prHolder);

                stringHolder = cleanString(squatPR.getText());
                prHolder = Integer.parseInt(stringHolder);
                myProfile.setSquatPR(prHolder);

                stringHolder = cleanString(deadliftPR.getText());
                prHolder = Integer.parseInt(stringHolder);
                myProfile.setDlPR(prHolder);

                PrimaryUserInterface primaryUserInterface = new PrimaryUserInterface(myProfile);
                primaryUserInterface.setVisible(true);
                dispose();
            }


        }

        //    EFFECTS: removes white space and quotation marks around s
        private String cleanString(String s) {
            s = s.toLowerCase();
            s = s.trim();

            return s;
        }

    }
}
