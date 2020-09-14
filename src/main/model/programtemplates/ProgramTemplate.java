package model.programtemplates;

import calendar.Calendar;
import calendar.Week;
import model.exercises.Accessory;
import model.exercises.mainmovements.Bench;
import model.exercises.mainmovements.DeadLift;
import model.exercises.mainmovements.Squat;
import model.user.MyProfile;
import model.MyProgramPalConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// ProgramRegime will be templates for strength or hypertrophy oriented programs. Fields consist of
//    - user main compound movement Personal Records (one rep max)
//    - user training days
//    - user training proficiency
//    - user maximum time available per workout
//    - user desired muscle group to grow
public abstract class ProgramTemplate extends MyProgramPalConstants {


    protected Bench userBench;
    protected Squat userSquat;
    protected DeadLift userDeadlift;

    protected Calendar myTrainingCycle;
    protected Week myTrainingWeek;

    protected String myProficiencyRequirement;

    protected int maxTime;
    protected List<String> myMuscleFocus;

    protected List<Accessory> myDaysAccessories;
    private HashSet<Accessory> repeatedMuscleFocusAccessories;
    private HashSet<Accessory> myDaysAccessoriesTracker;

    protected int timeSoFar;
    protected int accessorySoFar;


    public ProgramTemplate(int benchPR,
                           int squatPR,
                           int deadliftPR,
                           int maxTime,
                           List<Boolean> freeDays,
                           List<String> myMuscleFocus) {
        this.userBench = new Bench(benchPR);
        this.userSquat = new Squat(squatPR);
        this.userDeadlift = new DeadLift(deadliftPR);
        this.myMuscleFocus = myMuscleFocus;
        this.myDaysAccessories = new ArrayList<>();
        this.maxTime = maxTime;

        myTrainingCycle = new Calendar(1, freeDays);
        myTrainingWeek = myTrainingCycle.getWeeks().get(0);

        repeatedMuscleFocusAccessories = new HashSet<>();
        myDaysAccessoriesTracker = new HashSet<>();

        timeSoFar = 0;
        accessorySoFar = 0;
    }


//    getters

    public Week getMyTrainingWeek() {
        return myTrainingWeek;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public List<String> getMyMuscleFocus() {
        return myMuscleFocus;
    }

    //    setters

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void setMyMuscleFocus(List<String> myMuscleFocus) {
        this.myMuscleFocus = myMuscleFocus;
    }


    //  EFFECTS: multiplies and casts return value to int
    public int calcWeight(int personalRecord, double toScale) {
        return 5 * (Math.round((int) ((personalRecord * toScale) / 5)));
    }

    //    EFFECTS: writes out String of form "int xsets \n"
    public String writeSet(int personalRecord, double toScale, int reps) {
        return calcWeight(personalRecord, toScale) + " x" + reps + " <br>";
    }

    //    EFFECTS: writes out String of form "int xsets+ \n"
    public String writeTopSet(int personalRecord, double toScale, int reps) {
        return calcWeight(personalRecord, toScale) + " x" + reps + "+ <br>";
    }

    //    EFFECTS: multiplies sets and perform time
    public int calcPerformTime(int sets) {
        return (int) (sets * PERFORM_TIME);
    }

    //    EFFECTS: multiplies compound rest time and sets
    public int calcRestTime(int sets) {
        return (int) (sets * userDeadlift.getRestTime());
    }

    //    EFFECTS: creates a list choosing the appropriate accessories given maxTime, muscleFocus, and daySpecificA
    //             but chooses only up to two accessories before returning
    public void chooseAccessoriesMaxTwo(List<String> otherAccessories) {
        accessorySoFar = 0;
        int numAccessoryMuscleF = 0;
        myDaysAccessories.clear();
        boolean keepGoing = true;
        while (keepGoing) {
            for (Accessory accessory : accessoryList) {
//            CHECK TOTAL TIME ALLOTTED TO WORKOUT VS #OF ACCESSORIES AND TIME FOR MAIN LIFTS
                int timeToBe = accessory.calcTime() + timeSoFar;
                numAccessoryMuscleF = accessoryMuscleFocus(numAccessoryMuscleF, accessory, timeToBe);
                addAccessoryBasedOnDay(otherAccessories, accessory, timeToBe);
                if (accessorySoFar >= 2) {
                    keepGoing = false;
                    //accessorySoFar = 0;
                    break;
                }
            }
            // repeatedMuscleFocusAccessories is to keep track of already used accssry that meet musclefocus goal
            if (numAccessoryMuscleF == 0 && timeSoFar < maxTime - 15) {
                repeatedMuscleFocusAccessories.clear();
            } else {
                keepGoing = false;
            }
        }
    }

    //    EFFECTS: creates a list choosing the appropriate accessories given maxTime, muscleFocus, and daySpecificA
    public void chooseAccessories(List<String> otherAccessories) {
        int numAccessoryMuscleF = 0;
        myDaysAccessories.clear();
        boolean keepGoing = true;
        while (keepGoing) {
            for (Accessory accessory : accessoryList) {
//            CHECK TOTAL TIME ALLOTTED TO WORKOUT VS #OF ACCESSORIES AND TIME FOR MAIN LIFTS
                int timeToBe = accessory.calcTime() + timeSoFar;
                numAccessoryMuscleF = accessoryMuscleFocus(numAccessoryMuscleF, accessory, timeToBe);
                addAccessoryBasedOnDay(otherAccessories, accessory, timeToBe);
            }
            if (numAccessoryMuscleF == 0 && timeSoFar < maxTime - 15) {
                repeatedMuscleFocusAccessories.clear();
            } else {
                keepGoing = false;
            }
        }
        myDaysAccessoriesTracker.clear();
    }

    // MODIFIES: this
    // EFFECTS: if accessory matches the muscle group and doesn't go over the max time, add to accessory list
    private int accessoryMuscleFocus(int numAccessoryMuscleF, Accessory a, int timeToBe) {
        boolean myMuscleFocusMatch = MyProfile.listIntersects(a.getMuscleTarget(), myMuscleFocus);
        if (myMuscleFocusMatch && timeToBe <= maxTime) {
            if (!repeatedMuscleFocusAccessories.contains(a)) {
                myDaysAccessories.add(a);
                myDaysAccessoriesTracker.add(a);
                repeatedMuscleFocusAccessories.add(a);
                timeSoFar += a.calcTime();
                numAccessoryMuscleF++;
                accessorySoFar++;
            }
        }
        return numAccessoryMuscleF;
    }

    // MODIFIES: this
    // EFFECTS: if accessory matches the training day and doesn't go over the max time, add to accessory list
    private void addAccessoryBasedOnDay(List<String> otherAccessories, Accessory a, int workoutTimeToBe) {
        boolean daysAccessoryMatch = MyProfile.listIntersects(a.getMuscleTarget(), otherAccessories);
        if (daysAccessoryMatch && workoutTimeToBe <= maxTime) {
            boolean holder1 = myDaysAccessoriesTracker.contains(a);
            boolean holder2 = myDaysAccessories.contains(a);
            if (!holder1 && !holder2 && !repeatedMuscleFocusAccessories.contains(a)) {
                myDaysAccessories.add(a);
                myDaysAccessoriesTracker.add(a);
                timeSoFar += a.calcTime();
                accessorySoFar++;
            }
        }
    }

}
