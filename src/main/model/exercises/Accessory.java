package model.exercises;


import model.MyProgramPalConstants;

import java.util.List;

// accessory exercises to supplement main movements
public class Accessory extends MyProgramPalConstants {

    protected String name;
    protected List<String> muscleTarget;
    protected int rateOfPercievedExhaustion;
    protected int numberOfSets;
    protected double restTime;

    public Accessory(int rateOfPercievedExhaustion, double restTime, String name, List<String> muscleTarget) {
        this.rateOfPercievedExhaustion = rateOfPercievedExhaustion;
        this.restTime = restTime;
        this.name = name;
        this.muscleTarget = muscleTarget;

        chooseNumberOfSets();
    }

//    getters

    public int getRateOfPercievedExhaustion() {
        return rateOfPercievedExhaustion;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public String getName() {
        return name;
    }

    public double getRestTime() {
        return restTime;
    }

    public List<String> getMuscleTarget() {
        return muscleTarget;
    }

    //    setters

    public void setRateOfPercievedExhaustion(int rateOfPercievedExhaustion) {
        this.rateOfPercievedExhaustion = rateOfPercievedExhaustion;
    }

    public void setRestTime(double restTime) {
        this.restTime = restTime;
    }

    public void setMuscleTarget(List<String> muscleTarget) {
        this.muscleTarget = muscleTarget;
    }


    //    EFFECTS: outputs sets at x rpe
    private String developSetScheme() {

        if (rateOfPercievedExhaustion >= 9) {
            return numberOfSets + " sets, RPE " + rateOfPercievedExhaustion;
        } else {
            return EMOM_LENGTH + " minute EMOM, RPE " + rateOfPercievedExhaustion + " or for " + numberOfSets + " sets";
        }
    }

    //    EFFECTS: chooses how many sets to perform
    private void chooseNumberOfSets() {
        if (rateOfPercievedExhaustion >= 9) {
            numberOfSets = SETS_AT_9_RPE_OR_GREATER;
        } else {
            numberOfSets = SETS_RPE_8_LESS;
        }
    }

    //    EFFECTS: calculates time required to perform exercise
    public int calcTime() {
        int restTime = (int) (numberOfSets * this.restTime);
        int performTime = (int) (numberOfSets * PERFORM_TIME);

        return restTime + performTime;
    }

    //    EFFECTS: writes out set for program in form "this.name for x at RPE z" or "this.name for x EMOM at RPE z or
//             for y sets"
    public String writeSet() {
        return "<u><i>" + this.name + "</i></u>" + " for " + this.developSetScheme() + " <br>";
    }

}
