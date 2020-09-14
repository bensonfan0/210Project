package model.programtemplates;

import calendar.Day;
import model.exercises.Accessory;

import java.util.List;

// holds the training instructions for specific training days, eg. deadlift day, back day, squat day, etc.
public class TrainingDayBuilder extends ProgramTemplate {
    protected int secondaryMaxWorkingSets = 6;
    protected int secondaryHighVolumeRepsStarting = 8;
    protected int secondaryRepsToDecrement = 1;
    protected double secondaryStartingScale = 0.6;
    protected double secondaryToScaleIncrease = 0.03;
    private int rowTrainingMax = (int) (userDeadlift.getPersonalRecord() * 0.5);
    private int ohpTrainingMax = (int) (userBench.getPersonalRecord() * 0.65);
    private int pausedSquatTrainingMax = (int) (userSquat.getPersonalRecord() * 0.6);
    private int inclineBenchTrainingMax = (int) (userBench.getPersonalRecord() * 0.9);

    public TrainingDayBuilder(int benchPR,
                              int squatPR,
                              int deadliftPR,
                              int maxTime,
                              List<Boolean> freeDays,
                              List<String> myMuscleFocus) {
        super(benchPR, squatPR, deadliftPR, maxTime, freeDays, myMuscleFocus);
    }

    // MODIFIES: this
    //    EFFECTS: creates up to maxSecondaryWorkingSets, scales working weight up, and decreases reps
    public String createSecondaryProgression(int secondaryPR, String daySoFar) {
        int secondarySets = 0;
        int repsToPerform = secondaryHighVolumeRepsStarting;
        double toScale = secondaryStartingScale;

        while (timeSoFar < maxTime && secondarySets < secondaryMaxWorkingSets) {

            timeSoFar += calcRestTime(1) + calcPerformTime(1);
            daySoFar += this.writeSet(secondaryPR, toScale, repsToPerform);

            repsToPerform -= secondaryRepsToDecrement;

            timeSoFar += calcRestTime(1) + calcPerformTime(1);
            secondarySets++;
            toScale += secondaryToScaleIncrease;
        }
        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates instructions for heavy deadlift days
    protected String createHeavyDeadLiftDay(Day d) {
        int personalRecord = userDeadlift.getPersonalRecord();
        timeSoFar = 0;
        String daySoFar = "";

        daySoFar += "<b>Deadlift</b> <br>" + this.writeSet(personalRecord, 0.75, 5);
        daySoFar += this.writeSet(personalRecord, 0.85, 3);
        daySoFar += this.writeTopSet(personalRecord, 0.95, 1);
        daySoFar += this.writeSet(personalRecord, 0.9, 3);
        daySoFar += this.writeSet(personalRecord, 0.85, 3);
        daySoFar += this.writeSet(personalRecord, 0.80, 3);
        daySoFar += this.writeSet(personalRecord, 0.75, 3);
        daySoFar += this.writeSet(personalRecord, 0.70, 3);
        daySoFar += this.writeTopSet(personalRecord, 0.65, 3);

        timeSoFar += calcRestTime(9) + calcPerformTime(9);

        d.createAccessories(stringAccessoriesMaxTwo(back));

        daySoFar += "<b>Row</b> <br>";
        daySoFar = createSecondaryProgression(rowTrainingMax, daySoFar);

        //daySoFar = stringAccessories(daySoFar, back);
        d.createAccessories(stringAccessories(back));

        //System.out.println(timeSoFar);
        //System.out.println(daySoFar);
//        assert (timeSoFar <= maxTime);
        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates instructions for heavy bench days
    protected String createHeavyBenchDay(Day d) {
        String daySoFar = "";
        int personalRecord = userBench.getPersonalRecord();
        timeSoFar = 0;

        daySoFar += "<b>Bench</b> <br>" + this.writeSet(personalRecord, 0.75, 5);
        daySoFar += this.writeSet(personalRecord, 0.85, 3);
        daySoFar += this.writeTopSet(personalRecord, 0.95, 1);
        daySoFar += this.writeSet(personalRecord, 0.9, 3);
        daySoFar += this.writeSet(personalRecord, 0.85, 5);
        daySoFar += this.writeSet(personalRecord, 0.80, 3);
        daySoFar += this.writeSet(personalRecord, 0.75, 5);
        daySoFar += this.writeSet(personalRecord, 0.70, 3);
        daySoFar += this.writeTopSet(personalRecord, 0.65, 5);

        timeSoFar += calcRestTime(9) + calcPerformTime(9);

        d.createAccessories(stringAccessoriesMaxTwo(chest));

        daySoFar += "<b>Incline Bench</b> <br>";
        daySoFar = createSecondaryProgression(inclineBenchTrainingMax, daySoFar);

        //daySoFar = stringAccessories(daySoFar, chest);
        d.createAccessories(stringAccessories(chest));
        //System.out.println(timeSoFar);
        //System.out.println(daySoFar);
//        assert (timeSoFar <= maxTime);
        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates heavySquat days
    protected String createHeavySquatDay(Day d) {
        String daySoFar = "";
        int personalRecord = userSquat.getPersonalRecord();
        timeSoFar = 0;

        daySoFar += "<b>Squat</b> <br>" + this.writeSet(personalRecord, 0.75, 5);
        daySoFar += this.writeSet(personalRecord, 0.85, 3);
        daySoFar += this.writeTopSet(personalRecord, 0.95, 1);
        daySoFar += this.writeSet(personalRecord, 0.9, 3);
        daySoFar += this.writeSet(personalRecord, 0.85, 3);
        daySoFar += this.writeSet(personalRecord, 0.80, 3);
        daySoFar += this.writeSet(personalRecord, 0.75, 5);
        daySoFar += this.writeSet(personalRecord, 0.70, 5);
        daySoFar += this.writeTopSet(personalRecord, 0.65, 5);


        timeSoFar += calcRestTime(9) + calcPerformTime(9);

        d.createAccessories(stringAccessoriesMaxTwo(legs));

        daySoFar += "<b>PausedSquat</b> <br>";
        daySoFar = createSecondaryProgression(pausedSquatTrainingMax, daySoFar);

        //daySoFar = stringAccessories(daySoFar, legs);
        d.createAccessories(stringAccessories(legs));
        //System.out.println(timeSoFar);
        //System.out.println(daySoFar);
//        assert (timeSoFar <= maxTime);
        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates training instructions for volume bench day
    protected String createVolumeChestDay(Day d) {
        String daySoFar = "";
        int pr = userBench.getPersonalRecord();
        timeSoFar = 0;

        daySoFar += "<b>Bench</b> <br>" + this.writeSet(pr, 0.65, 8);
        daySoFar += this.writeSet(pr, 0.75, 6);
        daySoFar += this.writeSet(pr, 0.85, 4);
        daySoFar += this.writeSet(pr, 0.85, 4);
        daySoFar += this.writeSet(pr, 0.85, 4);
        daySoFar += this.writeSet(pr, 0.8, 5);
        daySoFar += this.writeSet(pr, 0.75, 6);
        daySoFar += this.writeSet(pr, 0.7, 7);
        daySoFar += this.writeTopSet(pr, 0.65, 8);

        timeSoFar += calcRestTime(9) + calcPerformTime(9);

        d.createAccessories(stringAccessoriesMaxTwo(chest));

        daySoFar += "<b>OHP</b> <br>";
        daySoFar = createSecondaryProgression(ohpTrainingMax, daySoFar);

        //daySoFar = stringAccessories(daySoFar, chest);
        d.createAccessories(stringAccessories(chest));
        //System.out.println(timeSoFar);
        //System.out.println(daySoFar);
//        assert (timeSoFar <= maxTime);
        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates training instructions for overheadpress day
    public String createOverHeadPressDay(Day d) {
        String daySoFar = "";
        int pr = ohpTrainingMax;
        timeSoFar = 0;

        daySoFar += "<b>OHP</b> <br>" + this.writeSet(pr, 0.65, 8);
        daySoFar += this.writeSet(pr, 0.75, 6);
        daySoFar += this.writeSet(pr, 0.85, 4);
        daySoFar += this.writeSet(pr, 0.8, 5);
        daySoFar += this.writeSet(pr, 0.75, 6);
        daySoFar += this.writeSet(pr, 0.7, 7);
        daySoFar += this.writeTopSet(pr, 0.65, 8);

        timeSoFar += calcRestTime(9) + calcPerformTime(9);

        d.createAccessories(stringAccessoriesMaxTwo(chest));

        daySoFar += "<b>Incline Bench</b> <br>";
        daySoFar = createSecondaryProgression(userBench.getPersonalRecord(), daySoFar);

        //daySoFar = stringAccessories(daySoFar, chest);
        d.createAccessories(stringAccessories(chest));
        //System.out.println(timeSoFar);
        //System.out.println(daySoFar);
//        assert (timeSoFar <= maxTime);
        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates heavy arm day routine with primary and secondary lifts
    protected String createArmDay(Day d) {
        String daySoFar = "";
        int pr = ohpTrainingMax * 2 / 3;
        timeSoFar = 0;

        daySoFar += "<b>Standing<br> Bicep Curl</b> <br>" + this.writeSet(pr, 0.75, 8);
        daySoFar += this.writeSet(pr, 0.75, 8);
        daySoFar += this.writeSet(pr, 0.75, 8);
        daySoFar += this.writeSet(pr, 0.75, 8);
        daySoFar += this.writeTopSet(pr, 0.75, 8);

        timeSoFar += calcRestTime(5) + calcPerformTime(5);

        //d.createAccessories(stringAccessoriesMaxTwo(arms));

        daySoFar += "<b>Tricep Cable<br> Pushdown</b> <br>";
        daySoFar = createSecondaryProgression(ohpTrainingMax * 2 / 3, daySoFar);
        //daySoFar = stringAccessories(daySoFar, chest);
        d.createAccessories(stringAccessories(arms));
        //System.out.println(timeSoFar);
        //System.out.println(daySoFar);
//        assert (timeSoFar <= maxTime);
        System.out.println(daySoFar);
        return daySoFar;

    }

    // MODIFIES: this
    //    EFFECTS: creates heavy deadliftDay routine with primary and secondary lifts
    protected String createVolumeDeadliftDay(Day d) {
        String daySoFar = "";
        int pr = userDeadlift.getPersonalRecord();
        timeSoFar = 0;

        daySoFar += "<b>Sumo Deadlift</b> <br>" + this.writeSet(pr, 0.70, 5);
        daySoFar += this.writeSet(pr, 0.70, 5);
        daySoFar += this.writeSet(pr, 0.70, 5);
        daySoFar += this.writeSet(pr, 0.70, 5);
        daySoFar += this.writeSet(pr, 0.70, 5);

        timeSoFar += calcRestTime(5) + calcPerformTime(5);

        d.createAccessories(stringAccessoriesMaxTwo(back));

        daySoFar += "<b>Conventional Deadlift</b> <br>";
        daySoFar = createSecondaryProgression(userDeadlift.getPersonalRecord() * 2 / 3, daySoFar);
        //daySoFar = stringAccessories(daySoFar, chest);
        d.createAccessories(stringAccessories(back));
        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates row day routine with primary and secondary lifts
    protected String createPausedDLDay(Day d) {
        String daySoFar = "";
        int pr = userDeadlift.getPersonalRecord() * 2 / 3;
        timeSoFar = 0;

        daySoFar += "<b>Paused Deadlift</b> <br>" + this.writeSet(pr, 0.75, 5);
        daySoFar += this.writeSet(pr, 0.80, 5);
        daySoFar += this.writeSet(pr, 0.85, 5);
        daySoFar += this.writeSet(pr, 0.85, 5);
        daySoFar += this.writeSet(pr, 0.85, 5);
        daySoFar += this.writeTopSet(pr, 0.85, 5);

        timeSoFar += calcRestTime(6) + calcPerformTime(6);

        //daySoFar = stringAccessories(daySoFar, chest);
        d.createAccessories(stringAccessories(back));
        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates high volume squat day
    protected String createVolumeSquatDay(Day d) {
        String daySoFar = "";
        int pr = userSquat.getPersonalRecord();
        timeSoFar = 0;

        daySoFar += "<b>Squat</b> <br>" + this.writeSet(pr, 0.75, 5);
        daySoFar += this.writeSet(pr, 0.75, 5);
        daySoFar += this.writeSet(pr, 0.75, 5);
        daySoFar += this.writeSet(pr, 0.80, 4);
        daySoFar += this.writeSet(pr, 0.85, 3);
        daySoFar += this.writeTopSet(pr, 0.85, 3);

        timeSoFar += calcRestTime(5) + calcPerformTime(5);

        d.createAccessories(stringAccessoriesMaxTwo(legs));

        daySoFar += "<b>Paused Squat</b> <br>";
        daySoFar = createSecondaryProgression(userSquat.getPersonalRecord() * 2 / 3, daySoFar);
        //daySoFar = stringAccessories(daySoFar, chest);
        d.createAccessories(stringAccessories(legs));
        return  daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: create leg focused day with intent of squat volume and lunges as secondary
    protected String createLegDay(Day d) {
        String daySoFar = "";
        int pr = userSquat.getPersonalRecord();
        timeSoFar = 0;

        daySoFar += "<b>Squat</b> <br>" + this.writeSet(pr, 0.65, 10);
        daySoFar += this.writeSet(pr, 0.65, 10);
        daySoFar += this.writeSet(pr, 0.65, 10);
        daySoFar += this.writeSet(pr, 0.65, 10);
        daySoFar += this.writeTopSet(pr, 0.70, 8);

        timeSoFar += calcRestTime(5) + calcPerformTime(5);

        daySoFar += "<b>Lunges</b> <br>";
        daySoFar += this.writeSet(pr / 6, 0.65, 15);
        daySoFar += this.writeSet(pr / 6, 0.65, 15);
        daySoFar += this.writeSet(pr / 6, 0.65, 15);
        daySoFar += this.writeSet(pr / 6, 0.65, 15);

        timeSoFar += calcRestTime(4) + calcPerformTime(4);
        d.createAccessories(stringAccessories(legs));

        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: create ab focused day with primary movement as leg raise and secondary of any ab machine
    protected String createAbsDay(Day d) {
        String daySoFar = "";
        int pr = userDeadlift.getPersonalRecord() / 20;
        timeSoFar = 0;

        daySoFar += "<b>Weighted Leg<br>Raises</b> <br>" + this.writeSet(pr, 0.8, 10);
        daySoFar += this.writeSet(pr, 0.8, 10);
        daySoFar += this.writeSet(pr, 0.8, 10);
        daySoFar += this.writeSet(pr, 0.8, 10);
        daySoFar += this.writeTopSet(pr, 0.8, 8);

        timeSoFar += calcRestTime(5) + calcPerformTime(5);

        pr = userBench.getPersonalRecord() / 2;
        daySoFar += "<b>Ab Machine (any)</b> <br>";
        daySoFar += this.writeSet(pr, 0.80, 15);
        daySoFar += this.writeSet(pr, 0.80, 15);
        daySoFar += this.writeSet(pr, 0.80, 15);
        daySoFar += this.writeSet(pr, 0.80, 15);

        timeSoFar += calcRestTime(4) + calcPerformTime(4);
        d.createAccessories(stringAccessories(abs));

        return daySoFar;
    }

    // MODIFIES: this
    //    EFFECTS: creates cardio day with intent of developing abs
    protected String createCardioDay(Day d) {
        String daySoFar = "";
        int pr = userDeadlift.getPersonalRecord();
        timeSoFar = 0;

        daySoFar += "<b>Sled Push/<br>Farmer Carry</b> <br>" + this.writeSet(pr, 0.6, 10);
        daySoFar += this.writeSet(pr, 0.6, 10);
        daySoFar += this.writeSet(pr, 0.6, 10);
        daySoFar += this.writeSet(pr, 0.6, 10);
        daySoFar += this.writeTopSet(pr, 0.6, 8);

        timeSoFar += calcRestTime(5) + calcPerformTime(5);

        pr = userBench.getPersonalRecord() / 2;
        daySoFar += "<b>Ab Machine (any)</b> <br>";
        daySoFar += this.writeSet(pr, 0.85, 15);
        daySoFar += this.writeSet(pr, 0.85, 15);
        daySoFar += this.writeSet(pr, 0.85, 15);
        daySoFar += this.writeSet(pr, 0.85, 15);

        timeSoFar += calcRestTime(4) + calcPerformTime(4);
        d.createAccessories(stringAccessories(abs));

        return daySoFar;
    }

    // MODIFIES: myTrainingWeek
    // EFFECTS: adds training at most two accessories to myTrainingWeek
    private String stringAccessoriesMaxTwo(List<String> currentDayAccessories) {
        chooseAccessoriesMaxTwo(currentDayAccessories);
        String daySoFar = "";

        StringBuilder daySoFarBuilder = new StringBuilder(daySoFar);
        for (Accessory accessory : myDaysAccessories) {
            daySoFarBuilder.append(accessory.writeSet());
        }
        daySoFar = daySoFarBuilder.toString();
        return daySoFar;
    }

    // MODIFIES: myTrainingWeek
    // EFFECTS: adds training accessories to myTrainingWeek
    private String stringAccessories(List<String> currentDayAccessories) {
        chooseAccessories(currentDayAccessories);
        String daySoFar = "";

        StringBuilder daySoFarBuilder = new StringBuilder(daySoFar);
        for (Accessory accessory : myDaysAccessories) {
            daySoFarBuilder.append(accessory.writeSet());
        }
        myDaysAccessories.clear();
        daySoFar = daySoFarBuilder.toString();
        return daySoFar;
    }
}
