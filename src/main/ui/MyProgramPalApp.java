package ui;


import calendar.Day;
import model.MyProgramPalConstants;
import model.user.MyProfile;
import persistance.Reader;
import persistance.Writer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// user interface class
public class MyProgramPalApp extends MyProgramPalConstants {

    private static final String ACCOUNTS_FILE = "./data/myProfiles.txt";

    private static final int LONGTIME = 420;
    private static final int MAX_EXPERIENCED_MUSCLE_FOCUS = 3;

    private static final String CUSTOMIZE_COMMAND = "customize";
    private static final String CONFIRMATION_COMMAND = "spaghetti";
    private static final String SQUAT_COMMAND = "squat";
    private static final String DEADLIFT_COMMAND = "deadlift";
    private static final String BENCH_COMMAND = "bench";
    private static final String BEGINNER_COMMAND = "novel";
    private static final String EXPERIENCED_COMMAND = "experienced";
    private static final String EXIT_COMMAND = "exit";
    private static final String SAVE_COMMAND = "save";
    private static final String VIEW_COMMAND = "view";
    private static final String ERASE_COMMAND = "erase";


    private List<String> muscleGroups;

    private static final String YES_COMMAND = "y";
    private static final String NO_COMMAND = "n";

    private static final String STRENGTH_COMMAND = "strength";
    private static final String HYPERTROPHY_COMMAND = "hypertrophy";

    private static final String GENERAL_COMMAND = "general";

    private static final String NA_COMMAND = "n/a";

    private MyProfile user;
    private Scanner input;
    private boolean keepGoing;
    private String commandString;
    private int commandInt;
    private boolean userFileFound;
    private File accountFile;


    public MyProgramPalApp() {
        input = new Scanner(System.in);
        keepGoing = true;
        commandString = null;
        muscleGroups = new ArrayList<>();
        muscleGroups.addAll(novelList);

        List<String> a1 = Arrays.asList(FRONTDELTS, TRAPS, LATS, BACKWIDTH, BICEPS, TRICEPS, FOREARMS, LOWERABS);
        muscleGroups.addAll(a1);

        a1 = Arrays.asList(UPPERABS, QUADS, CALVES, HAMSTRINGS, UPPERCHEST, SIDEDELTS, REARDELTS);
        muscleGroups.addAll(a1);

        runMyProgramPalApp();
    }

    //    EFFECTS: handles user inputs
    public void runMyProgramPalApp() {
        randomFactoid();

        loadAccounts();

        welcomeScreen();

        if (userFileFound) {
            System.out.println("Welcome Back!");
            System.out.println("Type \"" + VIEW_COMMAND + "\" to view workout again.");
            System.out.println("Type \"" + CUSTOMIZE_COMMAND + "\" to recustomize options");
        } else {
            System.out.println("Welcome to your personal program designer");
            System.out.println("In order to initialize type \"" + CUSTOMIZE_COMMAND + "\".");
        }

        runApp();
    }

    private void runApp() {
        while (keepGoing) {

            boolean fullMuscleFocus = user.getMuscleFocus().size() >= MAX_EXPERIENCED_MUSCLE_FOCUS;

            if (fullMuscleFocus || MyProfile.listIntersects(user.getMuscleFocus(), novelList)) {
                if (user.getMyWeek().size() == 0) {
                    System.out.println("Which days of the week can you train?");
                    System.out.println("Type \"" + YES_COMMAND + "\"/\"" + NO_COMMAND + "\" as the questions appear");
                    System.out.println("NOTE: 3 days is MINIMUM (muscles won't grow themselves!)");
                    System.out.println("NOTE: 6 days is MAXIMUM (you need rest!)");
                    System.out.println("Can you train Mondays?");
                }

            }


            if (input.hasNextInt()) {
                commandInt = input.nextInt();
                processInputInt(commandInt);
            } else {
                commandString = cleanString(input.next());
                processInputString(commandString);
            }
        }

    }


    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts() {
        try {
            this.user = Reader.readAccounts(new File(ACCOUNTS_FILE));
            // checks if the first word is boolean false
            accountFile = new File(ACCOUNTS_FILE);
            userFileFound = true;

        } catch (IOException e) {
            init();
        }
    }


    //    EFFECTS: processes user inputs which are Strings
    private void processInputString(String commandString) {
        if (muscleGroups.contains(commandString)) {
            System.out.println(commandString + " is now set as desired muscle group to grow");
            user.setMuscleFocus(commandString);
        } else {
            switch (commandString) {
                case CUSTOMIZE_COMMAND:
                    System.out.println("Please answer the following questions");
                    System.out.println("How many minutes can you train in a day?");
                    break;
                case BEGINNER_COMMAND:
                case EXPERIENCED_COMMAND:
                    processNovelorExperienced(commandString);
                    break;
                case NA_COMMAND:
                    System.out.println("Confirming no specific muscle group focus");
                    user.setMuscleFocus(GENERAL_COMMAND);
                    break;
                default:
                    processInputString2(commandString);
                    break;
            }
        }
    }

    private void processInputString2(String commandString) {
        switch (commandString) {
            case EXIT_COMMAND:
                //saveMyProfile();
                System.out.println("If there's a will, there's a way! \n Have a nice day.");
                keepGoing = false;
                break;
            case YES_COMMAND:
            case NO_COMMAND:
                processWeeklyAvailability(commandString);
                break;
            case STRENGTH_COMMAND:
            case HYPERTROPHY_COMMAND:
                processStrengthorHypertrophy(commandString);
                break;
            default:
                processInputString3(commandString);
                break;

        }
    }

    private void processInputString3(String commandString) {
        switch (commandString) {
            case SAVE_COMMAND:
                saveMyProfile();
                break;
            case VIEW_COMMAND:
                printOutWork();
                break;
            case ERASE_COMMAND:
                deleteMyProfile();
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    // EFFECTS: deletes my profile
    private void deleteMyProfile() {
        if (accountFile.delete()) {
            System.out.println("File deleted successfully");
            runMyProgramPalApp();
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    // EFFECTS: saves myProfile
    private void saveMyProfile() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            writer.write(user);
            writer.close();
            System.out.println("myProfile saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save myProfile to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }


    //    MODIFIES: user
//    EFFECTS: processes user inputs which are integers
    private void processInputInt(Integer command) {
        String yearsTrainingQ = "Would you consider yourself " + BEGINNER_COMMAND + " or " + EXPERIENCED_COMMAND + "?";

        if (command <= LONGTIME) {
            user.setMaxTime(command);
            System.out.println("Confirming " + command + " minutes available to train");
            System.out.print(yearsTrainingQ);
        } else {
            user.setMaxTime(command);
            System.out.println("Note: 3+ hours is too long, and likely to affect future performance negatively");
            System.out.print(yearsTrainingQ);
        }
    }

    //    EFFECTS: instantiates a MyProfile
    private void init() {
        user = new MyProfile();
    }

    private void welcomeScreen() {
        System.out.println("This is an app designed to make resistance training easy.");
        System.out.println("Type \"" + EXIT_COMMAND + "\" to end myPersonalProgram");
    }

    //    EFFECTS: checks if value is of type int
    private static boolean isStringInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //    EFFECTS: removes white space and quotation marks around s
    private String cleanString(String s) {
        s = s.toLowerCase();
        s = s.trim();

        return s;
    }

    //    EFFECTS: prints out a random resistance training fact at beginning of program
    private void randomFactoid() {

    }

    private void processWeeklyAvailability(String commandString) {
        addBoolean(commandString);
        switch (user.getMyWeek().size()) {
            case 1:
                System.out.println("confirming " + commandString + " for Mondays");
                System.out.println("Can you train Tuesdays?");
                break;
            case 2:
                System.out.println("confirming " + commandString + " for Tuesdays");
                System.out.println("Can you train Wednesdays?");
                break;
            case 3:
                System.out.println("confirming " + commandString + " for Wednesdays");
                System.out.println("Can you train Thursdays?");
                break;
            case 4:
                System.out.println("confirming " + commandString + " for Thursdays");
                System.out.println("Can you train Fridays?");
                break;
            default:
                processWeekAvailability2(commandString);
                break;
        }
    }

    private void processWeekAvailability2(String commandString) {
        switch (user.getMyWeek().size()) {
            case 5:
                System.out.println("confirming " + commandString + " for Fridays");
                System.out.println("Can you train Saturdays?");
                break;
            case 6:
                System.out.println("confirming " + commandString + " for Saturdays");
                System.out.println("Can you train Sundays?");
                break;
            case 7:
                System.out.println("confirming " + commandString + " for Sundays");
                System.out.println("Great, we're almost done.");
                System.out.println("Lastly, are you training for Strength or Hypertrophy?");
                break;
            default:
                System.out.println("Error at processWeekAvailability2");
                break;
        }
    }

    //    MODIFIES: this
//    EFFECTS: creates a list of freeDays
    private void addBoolean(String commandString) {

        if (user.getMyWeek().size() < 7) {
            if (commandString.equals("y")) {
                user.setMyDay(true);
            } else {
                user.setMyDay(false);
            }
        }
    }

    //    MODIFIES: user
//    EFFECTS: sets user Bench PR
    private void processPersonalRecords() {
        boolean keepGoing = true;

        System.out.println("Input your BenchPress one rep max:");

        while (keepGoing) {

            if (input.hasNextInt()) {
                user.setBenchPR(input.nextInt());
                keepGoing = false;
            } else {
                System.out.println("Invalid Input");
            }

        }
        processSquatPersonalRecord();

    }

    //    MODIFIES: user
//    EFFECTS: sets user Deadlift PR
    private void processDeadLiftPersonalRecord() {
        boolean keepGoing = true;

        System.out.println("Input your DeadLift one rep max:");

        while (keepGoing) {

            if (input.hasNextInt()) {
                user.setDlPR(input.nextInt());
                keepGoing = false;
            } else {
                System.out.println("Invalid Input");
            }
        }
    }

    //    MODIFIES: user
//    EFFECTS: sets user Squat PR
    private void processSquatPersonalRecord() {
        boolean keepGoing = true;
        System.out.println("Input your Squat one rep max:");

        while (keepGoing) {

            if (input.hasNextInt()) {
                user.setSquatPR(input.nextInt());
                keepGoing = false;
            } else {
                System.out.println("Invalid Input");
            }

        }
        processDeadLiftPersonalRecord();
    }

    //    EFFECTS: system screen prints all the assigned training day instructions
    private void printOutWork() {

        for (Day d : user.getAssignedProgram().getMyTrainingWeek().getListOfDay()) {
            System.out.println(d.getTrainingInstructions());
        }

        System.out.println("A workout a day keeps the girls in bay");

    }

    //    MODIFIES: user
    //    EFFECTS: output appropriate lines given novel or experience
    private void processNovelorExperienced(String commandString) {

        if (commandString.contains(BEGINNER_COMMAND)) {
            System.out.println("Confirming as " + BEGINNER_COMMAND + " lifter");
            System.out.println("Is there a specific muscle focus?");
            System.out.println("Choose one: \n CHEST \n BACK \n ARMS \n LEGS \n ABS \n or enter \"n/a\" ");
        } else {
            System.out.println("Confirming as " + EXPERIENCED_COMMAND + " lifter");
            System.out.println("Personalize specific areas during mesocycle");
            System.out.println("Choose at most 3: \n UPPERCHEST  LOWERCHEST  SIDEDELTS  REARDELTS");
            System.out.println("FRONTDELTS  TRAPS  LATS  BACK-WIDTH  BICEPS  TRICEPS  FOREARMS");
            System.out.println("LOWERABS  UPPERABS  QUADS  HAMSTRINGS  CALVES \n or n/a");
        }
        user.setExperienceLevel(commandString);
    }

    //    MODIFIES: user
//    EFFECTS: sets lifting goal of user
    private void processStrengthorHypertrophy(String commandString) {

        if (commandString.contains(STRENGTH_COMMAND)) {
            user.setLiftingGoal("strength");
        } else {
            user.setLiftingGoal("hypertrophy");
        }
        processPersonalRecords();
        System.out.println("Beginning to process a program tailored to your needs");
        user.programAssigner();
        printOutWork();

    }


}
