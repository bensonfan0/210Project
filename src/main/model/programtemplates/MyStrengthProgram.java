package model.programtemplates;

import calendar.Day;
import calendar.Week;
import exceptions.NonMatchMuscleFocus;
import exceptions.TooManyDays;
import exceptions.TooManyTrainingDays;
import gui.ChangePersonalRecordsOnly;
import model.exercises.Accessory;

import java.util.List;

import static model.user.MyProfile.listIntersects;

// Builds a strength oriented weekly linear progression program by choosing which training days best fit user preference
public class MyStrengthProgram extends TrainingDayBuilder {

    protected int secondaryMaxWorkingReps = 8;


    public MyStrengthProgram(int benchPR,
                             int squatPR,
                             int dlPR,
                             int maxTime,
                             List<Boolean> freeDays,
                             List<String> myMuscleFocus) {
        super(benchPR, squatPR, dlPR, maxTime, freeDays, myMuscleFocus);

        createProgram();

    }

//    getters

    public int getSecondaryHighVolumeRepsStarting() {
        return secondaryHighVolumeRepsStarting;
    }

    public double getSecondaryStartingScale() {
        return secondaryStartingScale;
    }

    public double getSecondaryToScaleIncrease() {
        return secondaryToScaleIncrease;
    }

    public int getSecondaryRepsToDecrement() {
        return secondaryRepsToDecrement;
    }

    public int getSecondaryMaxWorkingReps() {
        return secondaryMaxWorkingReps;
    }

    public int getSecondaryMaxWorkingSets() {
        return secondaryMaxWorkingSets;
    }


//    setters

    public void setSecondaryHighVolumeRepsStarting(int secondaryHighVolumeRepsStarting) {
        this.secondaryHighVolumeRepsStarting = secondaryHighVolumeRepsStarting;
    }

    public void setSecondaryMaxWorkingReps(int secondaryMaxWorkingReps) {
        this.secondaryMaxWorkingReps = secondaryMaxWorkingReps;
    }

    public void setSecondaryMaxWorkingSets(int secondaryMaxWorkingSets) {
        this.secondaryMaxWorkingSets = secondaryMaxWorkingSets;
    }

    public void setSecondaryRepsToDecrement(int secondaryRepsToDecrement) {
        this.secondaryRepsToDecrement = secondaryRepsToDecrement;
    }

    public void setSecondaryStartingScale(double secondaryStartingScale) {
        this.secondaryStartingScale = secondaryStartingScale;
    }

    public void setSecondaryToScaleIncrease(double secondaryToScaleIncrease) {
        this.secondaryToScaleIncrease = secondaryToScaleIncrease;
    }


    // MODIFIES: this
// EFFECTS: inputs instructions into each training day
    public void createProgram() {
        try {
            int weekTracker = 0;
            int trainingDaysTracker = 0;
            for (Week w : myTrainingCycle.getWeeks()) {

                for (Day d : w.getListOfDay()) {

                    if (d.getAvailable()) {
                        trainingDaysTracker++;
                        d.createNewInstructions(assignTrainingDay(trainingDaysTracker, d));
                    } else {
                        d.createNewInstructions(REST_DAY);
                    }
                }
                weekTracker++;
            }
        } catch (TooManyDays e) {
            e.printStackTrace();
            System.out.println("Calendar week has too many days");
        } catch (NonMatchMuscleFocus e) {
            e.printStackTrace();
            System.out.println("MuscleFocus not found");
        }
    }

    //    EFFECTS: assigns the main three lifts (squat, deadlift, bench) to first three available days.
    //             then assigns extra available days to work on desired body group.
    public String assignTrainingDay(int trainingDaysTracker, Day d) throws TooManyDays, NonMatchMuscleFocus {
        try {
            switch (trainingDaysTracker) {
//               DeadLift, Back, Abs
                case 1:
                    return createHeavyDeadLiftDay(d);
//                Bench, Arms
                case 2:
                    return createHeavyBenchDay(d);
//                Squat, Legs
                case 3:
                    return createHeavySquatDay(d);
                default:
                    return assignSecondaryTrainingDays(trainingDaysTracker, d);
            }
        } catch (TooManyTrainingDays e) {
            return mockUser(e.getMessage());
        }
    }

    private String mockUser(String s) {
        String stringHolder;
        stringHolder = s + "<br>";
        stringHolder += "This kid is <br> trying to workout <br> 7 days a week. <br> Stop that.";
        return stringHolder;
    }

    //    EFFECTS: chooses appropriate secondary training days to supplement muscleFocus
    private String assignSecondaryTrainingDays(int trainingDaysTracker, Day d) throws TooManyDays, NonMatchMuscleFocus {
        if (trainingDaysTracker <= 7) {
            if (listIntersects(novelList, myMuscleFocus)) {
                if (listIntersects(myMuscleFocus, arms) || listIntersects(myMuscleFocus, chest)) {
                    return produceChestArmsDays(trainingDaysTracker, d);
                } else if (listIntersects(myMuscleFocus, back)) {
                    return produceBackDays(trainingDaysTracker, d);
                } else if (listIntersects(myMuscleFocus, legs)) {
                    return produceLegDays(trainingDaysTracker, d);
                } else {
                    return produceAbDays(trainingDaysTracker, d);
                }
            } else {
                throw new NonMatchMuscleFocus("ERROR: musclefocus does not match in database");
            }
        } else {
            throw new TooManyDays("ERROR: #weekdays > 7 days");
        }
    }


    //    EFFECTS: Chooses appropriate chest focused day based on how many training days already assigned
    private String produceChestArmsDays(int trainingDaysTracker, Day d) throws TooManyTrainingDays {
        if (trainingDaysTracker <= 6) {
            switch (trainingDaysTracker) {
                case 4:
//                add Volume Chest day
                    return createVolumeChestDay(d);
                case 5:
//                add OHP day
                    return createOverHeadPressDay(d);
                default:
//                add Arm Day
                    return createArmDay(d);
            }
        } else {
            if (myMuscleFocus.contains(CHEST)) {
                throw new TooManyTrainingDays("Too many Chest Days!");
            } else {
                throw new TooManyTrainingDays("Too many Arm Days!");
            }
        }
    }

    //    EFFECTS: Chooses appropriate back focused day based on how many training days already assigned
    //             also chooses accessories to assign to day.createAccessories();
    private String produceBackDays(int trainingDaysTracker, Day d) throws TooManyTrainingDays {
        if (trainingDaysTracker <= 6) {
            switch (trainingDaysTracker) {
                case 4:
//                add Volume Deadlift day
                    return createVolumeDeadliftDay(d);
                case 5:
//                add Arms day
                    return createArmDay(d);
                default:
//                add paused DL day
                    return createPausedDLDay(d);
            }
        } else {
            throw new TooManyTrainingDays("Too many Back Days!");
        }
    }

    //    EFFECTS: Chooses appropriate Leg focused days based on how many training days already assigned
    private String produceLegDays(int trainingDaysTracker, Day d) throws TooManyTrainingDays {
        if (trainingDaysTracker <= 6) {
            switch (trainingDaysTracker) {
                case 4:
//                add Volume Squat day
                    return createVolumeSquatDay(d);
                case 5:
//                add Arms day
                    return createArmDay(d);
                default:
//                add Leg Day
                    return createLegDay(d);
            }
        } else {
            throw new TooManyTrainingDays("Too many Leg Days!");
        }
    }

    //    EFFECTS: Chooses appropriate abs focused day based on how many training days already assigned
    private String produceAbDays(int trainingDaysTracker, Day d) throws TooManyTrainingDays {
        if (trainingDaysTracker <= 6) {
            switch (trainingDaysTracker) {
                case 4:
//                add Abs day
                    return createAbsDay(d);
                case 5:
//                add Arms day
                    return createArmDay(d);
                default:
//                add Cardio Day
                    return createCardioDay(d);
            }
        } else {
            throw new TooManyTrainingDays("Too many Ab Days!");
        }
    }
}
