package model.exercises;

// Compound movements will have their own personal one rep max, name (bench, deadlift, etc), rest time.
public abstract class Compound {

    protected String name;

    protected int personalRecord;
    protected int trainingMax;

    protected double restTime = 3.5;


    public Compound(int personalRecord) {
        this.personalRecord = personalRecord;
        this.trainingMax = (int) (personalRecord * 0.9);
    }

    //    setters
    public void setPersonalRecord(int personalRecord) {
        this.personalRecord = personalRecord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrainingMax(int trainingMax) {
        this.trainingMax = trainingMax;
    }

    public void setRestTime(double restTime) {
        this.restTime = restTime;
    }

    //    getters
    public int getPersonalRecord() {
        return this.personalRecord;
    }

    public String getName() {
        return name;
    }

    public double getRestTime() {
        return restTime;
    }

    public int getTrainingMax() {
        return trainingMax;
    }
}
