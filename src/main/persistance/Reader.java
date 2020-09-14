package persistance;

import model.MyProgramPalConstants;
import model.user.MyProfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class Reader extends MyProgramPalConstants {
    public static final String DELIMITER = ",";


    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static MyProfile readAccounts(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static MyProfile parseContent(List<String> fileContent) {
        ArrayList<String> lineComponents = new ArrayList<>();
        for (String s : fileContent) {
            lineComponents = splitString(s);
        }
        return parseMyProfile(lineComponents);
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

/*
    private ProgramTemplate assignedProgram;
    private int maxTime;
    private String experienceLevel;
    private List<String> muscleFocus;
    private String primaryCompound;
    private String liftingGoal;
    private List<Boolean> myWeek;

    private int deadliftPR;
    private int benchPR;
    private int squatPR;
 */

    // REQUIRES: components has size 8 where element 0 represents maxTime of user,
    // elements 1 represents the experienceLevel, element 2
    // the muscleFocus, element 3 the primary Compound focus,
//    element 4 is the days of availability, element 5 is deadlift pr,
//    element 6 is bench pr, element 7 is squat pr
    // EFFECTS: returns an account constructed from components
    private static MyProfile parseMyProfile(List<String> components) {
        MyProfile user = new MyProfile();

        user.setMaxTime(Integer.parseInt(components.get(0)));
        user.setExperienceLevel(components.get(1));
        if (user.getExperienceLevel().equals(BEGINNER)) {
            user.setMuscleFocus(components.get(2));
            user.setLiftingGoal(components.get(3));
            for (int i = 4; i < 11; i++) {
                user.setMyDay(Boolean.parseBoolean(components.get(i)));
            }
            user.setDlPR(Integer.parseInt(components.get(11)));
            user.setBenchPR(Integer.parseInt(components.get(12)));
            user.setSquatPR(Integer.parseInt(components.get(13)));

            user.programAssigner();
            return user;
        } else {
            return handleExperiencedSaveFile(user, components);
        }
    }

    //EFFECTS: returns an account constructed from components
    private static MyProfile handleExperiencedSaveFile(MyProfile user, List<String> components) {
        user.setMuscleFocus(components.get(2));
        user.setMuscleFocus(components.get(3));
        user.setMuscleFocus(components.get(4));
        user.setLiftingGoal(components.get(5));
        for (int i = 6; i < 13; i++) {
            user.setMyDay(Boolean.parseBoolean(components.get(i)));
        }
        user.setDlPR(Integer.parseInt(components.get(13)));
        user.setBenchPR(Integer.parseInt(components.get(14)));
        user.setSquatPR(Integer.parseInt(components.get(15)));

        user.programAssigner();
        return user;
    }

}
