package gui;

import model.MyProgramPalConstants;
import model.user.MyProfile;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener; // this "listens" for user to interact with gui
import java.awt.event.ActionEvent;

// new users will see this page, ie when no saved file is found
public class WelcomeInterface extends JFrame {

    private MyProfile myProfile;

    private JLabel welcomeDialogue;
    private JLabel selectDialogue;

    private JPanel mainPanel;
    private JPanel buttonHolder;
    private JPanel labelPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    private JButton novelButton;
    private JButton expButton;
    private JTextField enterMinutes;
    private JPasswordField passwordField;

    private JProgressBar progressBar;

    private JMenuBar menuBar;

    private int interfaceWidth = 700;
    private int interfaceHeight = 400;

    public WelcomeInterface(MyProfile myProfile) {
        super("MyTrainingPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(interfaceWidth, interfaceHeight);
        setLayout(new GridBagLayout());

//        super("MyTrainingPal");
//        setLayout(new FlowLayout(FlowLayout.CENTER, interfaceWidth, interfaceHeight / 4));

//        set up myTrainingPal related fields
        this.myProfile = myProfile;

        setUpMenuBar();
        setUpPanels();
        createDialogue();
        addButtons();
        setListeners();
        setProgressBar();
        setMainPanel();


        GridBagConstraints c = new GridBagConstraints();
        add(mainPanel);

    }

// MODIFIES: mainPanel
    //    EFFECTS: adds panels to mainPanel
    private void setMainPanel() {
        GridBagConstraints c = new GridBagConstraints();
        mainPanel.add(topPanel);
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(bottomPanel, c);
        c = addComponentsIntoTopPanel();
        addComponentsIntoBottomPanel(c);
    }

    // MODIFIES: topPanel, bottomPanel
   // EFFECTS: adds components to top panel
    private GridBagConstraints addComponentsIntoTopPanel() {
        GridBagConstraints c;
        c = new GridBagConstraints();
        // the weights the component should have in concern to them being added to a container that is larger
        // than its preferred size
        c.weightx = 0.5;
        c.weighty = 1;

        // the coordinates of the component
        c.gridx = 3;
        c.gridy = 0;

        // the way the component should be "glued" to the container
        c.anchor = GridBagConstraints.LINE_START;

        // the way the component should expand in size if it is placed in a container that is larger
        // than its preferred size
        c.fill = GridBagConstraints.HORIZONTAL;

        c.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;

        topPanel.add(progressBar, c);
        c.insets = new Insets(25, 0, 25, 0);

        c.gridy = 1;
        topPanel.add(labelPanel, c);
        return c;
    }

    // MODIFIES: bottomPanel
    // EFFECTS: adds components to bottom panel
    private void addComponentsIntoBottomPanel(GridBagConstraints c) {
        c.insets = new Insets(75, 0, 25, 0);

        c.weighty = 10.0;

        c.gridy = 2;
        bottomPanel.add(buttonHolder, c);
    }

    private void setUpMenuBar() {

    }

    //    EFFECTS: sets progressBar
    private void setProgressBar() {
        progressBar = new JProgressBar();

        progressBar.setMaximum(7);
        progressBar.setValue(1);

        progressBar.setPreferredSize(new Dimension(650, 25));

    }

    //    EFFECTS: sets listeners
    private void setListeners() {
        // when you enter text into a field it will tell you what you entered
        TheHandler handler = new TheHandler();
        novelButton.addActionListener(handler);
        expButton.addActionListener(handler);
    }

    //    EFFECTS: instantiates panels and sets layout
    private void setUpPanels() {
        mainPanel = new JPanel();
        buttonHolder = new JPanel();
        labelPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        buttonHolder.setLayout(new GridBagLayout());
        labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.Y_AXIS));

        mainPanel.setMinimumSize(new Dimension(interfaceWidth, interfaceHeight));
        topPanel.setMinimumSize(new Dimension(interfaceWidth,50));
        bottomPanel.setMinimumSize(new Dimension(interfaceWidth, interfaceHeight - 50));
    }

    //    EFFECTS: creates text to be shown on screen
    private void createDialogue() {
        // labels are basically texts on the screen
        welcomeDialogue = new JLabel("Please answer the following questions to optimize your training regiment.");
        welcomeDialogue.setToolTipText("being swole is the secret to happiness"); // shows up when mouse hovers over it
        labelPanel.add(welcomeDialogue); // you always need to "add" the object into the window

        selectDialogue = new JLabel("Select from the following:");
        labelPanel.add(selectDialogue);
    }

    //    EFFECTS: creates buttons and adds to buttonHolderField
    private void addButtons() {
        novelButton = new JButton("Novel Lifter");
        novelButton.setToolTipText("We all got to start somewhere!");
        buttonHolder.add(novelButton);

        expButton = new JButton("Experienced Lifter");
        expButton.setToolTipText("erybody wanna be a bodybuilder, but ain't nobody wanna lift no heavy ass weights");
        buttonHolder.add(expButton);
    }

    //    this class will essentially handle all events
    private class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String string = "";

//            big else to check which "item" user interacted with
            if (e.getSource() == novelButton) {
                myProfile.setExperienceLevel(MyProgramPalConstants.BEGINNER);
                SelectMuscleInterface selectMuscleInterface = new SelectMuscleInterface(myProfile);
                selectMuscleInterface.setVisible(true);
                dispose();
                // TODO make new screen highlighting body parts
            } else if (e.getSource() == expButton) {
                myProfile.setExperienceLevel(MyProgramPalConstants.BEGINNER);
                // TODO implement later lol!
            }

            //JOptionPane.showMessageDialog(null, string);
        }
    }

    // getters

    public int getInterfaceHeight() {
        return interfaceHeight;
    }

    public int getInterfaceWidth() {
        return interfaceWidth;
    }

    // setters
    public void setInterfaceHeight(int interfaceHeight) {
        this.interfaceHeight = interfaceHeight;
    }

    public void setInterfaceWidth(int interfaceWidth) {
        this.interfaceWidth = interfaceWidth;
    }
}
