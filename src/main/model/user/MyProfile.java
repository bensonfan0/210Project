package model.user;

import model.programtemplates.ProgramTemplate;
import model.programtemplates.MyStrengthProgram;
import model.MyProgramPalConstants;
import persistance.Reader;
import persistance.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Class holds User information such as:
//    - maxTime allotted a day for working out
//    - myWeek (requires 7 booleans, 1st is monday ... 7th is sunday) which represents training days
//    - muscleFocus holds any specific muscle groups which the user wishes to focus on
//    - PR is an acronym for Personal Record (one rep max)

public class MyProfile implements Saveable {

    private static final int MAX_MUSCLE_FOCUS_BEGINNER = 1;
    private static final int MAX_MUSCLE_FOCUS_EXPERIENCED = 3;

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

    public MyProfile() {
        muscleFocus = new ArrayList<>();
        primaryCompound = null;
        liftingGoal = null;
        myWeek = new ArrayList<>();

        deadliftPR = 0;
        benchPR = 0;
        squatPR = 0;

    }

//    getters

    public int getBenchPR() {
        return benchPR;
    }

    public int getDlPR() {
        return deadliftPR;
    }

    public int getSquatPR() {
        return squatPR;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public ProgramTemplate getAssignedProgram() {
        return assignedProgram;
    }

    public String getPrimaryCompound() {
        return primaryCompound;
    }

    public String getLiftingGoal() {
        return liftingGoal;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public List<String> getMuscleFocus() {
        return muscleFocus;
    }

    public List<Boolean> getMyWeek() {
        return myWeek;
    }

    //    setters
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public void setBenchPR(int benchPR) {
        this.benchPR = benchPR;
    }

    public void setDlPR(int dlPR) {
        this.deadliftPR = dlPR;
    }

    public void setSquatPR(int squatPR) {
        this.squatPR = squatPR;
    }

    public void setMyWeek(List<Boolean> myWeek) {
        this.myWeek = myWeek;
    }

    //    REQUIRES: muscleFocus string is from beginnerList or experiencedList
//    MODIFIES: this
//    EFFECTS: will only keep 1 (3 if experienced) most recent muscle inputs
    public void setMuscleFocus(String muscleFocus) {

        if (MyProfile.listIntersects(MyProgramPalConstants.novelList, this.muscleFocus)) {
            this.muscleFocus.clear();
        } else {
            if (this.muscleFocus.size() >= MAX_MUSCLE_FOCUS_EXPERIENCED) {
                this.muscleFocus.remove(0);
            }
        }
        this.muscleFocus.add(muscleFocus);
    }

    public void setPrimaryCompound(String primaryCompound) {
        this.primaryCompound = primaryCompound;
    }

    public void setLiftingGoal(String liftingGoal) {
        this.liftingGoal = liftingGoal;
    }

    public void setMyDay(Boolean myWeek) {
        this.myWeek.add(myWeek);
    }


    //    EFFECTS: if two string lists intersect produce true
    public static boolean listIntersects(List<String> list1, List<String> list2) {
        boolean status = false;

        for (String s: list1) {
            if (list2.contains(s)) {
                status = true;
                break;
            }
        }
        return status;
    }

//    MODIFIES: this
//    EFFECTS: creates workout program and assigns to user
    public void programAssigner() {

        this.assignedProgram = new MyStrengthProgram(benchPR, squatPR, deadliftPR, maxTime, myWeek, muscleFocus);

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

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(maxTime);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(experienceLevel);
        printWriter.print(Reader.DELIMITER);
        for (String s: muscleFocus) {
            printWriter.print(s);
            printWriter.print(Reader.DELIMITER);
        }
        printWriter.print(liftingGoal);
        printWriter.print(Reader.DELIMITER);
        for (boolean w: myWeek) {
            printWriter.print(w);
            printWriter.print(Reader.DELIMITER);
        }
        printWriter.print(deadliftPR);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(benchPR);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(squatPR);
    }
}
