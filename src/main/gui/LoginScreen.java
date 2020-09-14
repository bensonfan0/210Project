package gui;

import model.user.MyProfile;
import persistance.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// produces first frame when program is launched
public class LoginScreen extends JFrame {

    private static final String ACCOUNTS_FILE = "./data/myProfiles.txt";
    private File accountFile;

    private int interfaceWidth = 700;
    private int interfaceHeight = 400;

    private JPanel panelMainPanel;
    private JPanel panelTopHalf;
    private JPanel panelBottomHalf;

    private JButton buttonLogin;
    private JButton buttonInitialize;

    private JLabel labelWelcome;

    private MyProfile myProfile;

    public LoginScreen() {
        super("myTrainingPal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(interfaceWidth, interfaceHeight);
        setLayout(new GridBagLayout());

        // set up myProfile related fields

        setUpPanels();
        setUpLabels();
        setUpButtons();
        setUpTopBotPanels();

        setUpMainLabel();

        setUpListeners();

        add(panelMainPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets up top and bottom panels
    private void setUpTopBotPanels() {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 1;

        // the coordinates of the component
        c.gridx = 3;
        c.gridy = 0;

        // the way the component should be "glued" to the container
        //c.anchor = GridBagConstraints.NORTH;

        // the way the component should expand in size if it is placed in a container that is larger
        // than its preferred size
        c.fill = GridBagConstraints.HORIZONTAL;

        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(5, 100, 0, 0);
        panelTopHalf.add(labelWelcome, c);
        c.insets = new Insets(100, 100, 0, 0);
        panelBottomHalf.add(buttonInitialize, c);
        panelBottomHalf.add(buttonLogin, c);
    }

    // MODIFIES: this
    // EFFECTS: adds panels to mainPanel in correct orientation
    private void setUpMainLabel() {
        GridBagConstraints c = new GridBagConstraints();
        panelMainPanel.add(panelTopHalf);
        c.gridx = 0;
        c.gridy = 1;
        panelMainPanel.add(panelBottomHalf, c);
    }

    // EFFECTS: initializes Panels
    private void setUpPanels() {
        panelBottomHalf = new JPanel();
        panelTopHalf = new JPanel();
        panelMainPanel = new JPanel();

        panelMainPanel.setLayout(new GridBagLayout());
        panelTopHalf.setLayout(new GridBagLayout());
        panelBottomHalf.setLayout(new BoxLayout(panelBottomHalf, BoxLayout.Y_AXIS));


        panelMainPanel.setLayout(new BoxLayout(panelMainPanel,BoxLayout.Y_AXIS));

        panelMainPanel.setMinimumSize(new Dimension(interfaceWidth, interfaceHeight));
        panelMainPanel.setPreferredSize(new Dimension(interfaceWidth, interfaceHeight));
        panelTopHalf.setMinimumSize(new Dimension(interfaceWidth,50));
        panelTopHalf.setMaximumSize(new Dimension(interfaceWidth, 50));
        panelBottomHalf.setMinimumSize(new Dimension(interfaceWidth, interfaceHeight - 50));
    }

    // EFFECTS: sets up labels with appropriate texts
    private void setUpLabels() {
        labelWelcome = new JLabel("Welcome! MyTrainingPal is designed to make resistance training easy.");
    }

//    EFFECTS: sets up buttons with proper tags
    private void setUpButtons() {
        buttonInitialize = new JButton("Initialize");
        buttonLogin = new JButton("Reload Account");
    }

    // EFFECTS: sets up listeners
    private void setUpListeners() {
        TheHandler theHandler = new TheHandler();

        buttonLogin.addActionListener(theHandler);
        buttonInitialize.addActionListener(theHandler);
    }

    // EFFECTS: handler class to handle any actions user performs
    private class TheHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonInitialize) {
                openWelcomeInterface();
            } else if (e.getSource() == buttonLogin) {
                loadAccounts();
            }
            //JOptionPane.showMessageDialog(null, string);
        }

        // MODIFIES: this
        // EFFECTS: opens new welcome interface
        public void openWelcomeInterface() {
            myProfile = new MyProfile();
            WelcomeInterface welcomeInterface = new WelcomeInterface(myProfile);
            welcomeInterface.setVisible(true);
            dispose();
        }

        // MODIFIES: this
        // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
        // otherwise initializes accounts with default values
        private void loadAccounts() {
            try {
                myProfile = Reader.readAccounts(new File(ACCOUNTS_FILE));
                // checks if the first word is boolean false
                accountFile = new File(ACCOUNTS_FILE);
                PrimaryUserInterface primaryUserInterface = new PrimaryUserInterface(myProfile);
                primaryUserInterface.setVisible(true);
                dispose();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Account not Found. Please Initialize.");
            }
        }


    }

}
