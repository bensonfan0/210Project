package model;

import calendar.Day;
import exceptions.NonMatchMuscleFocus;
import exceptions.TooManyDays;
import model.exercises.mainmovements.DeadLift;
import model.programtemplates.MyStrengthProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MyStrengthProgramTests extends MyProgramPalConstants {

    private MyStrengthProgram myStrengthProgram;

    @BeforeEach
    void runBefore() {
        List<Boolean> booleanHolder = Arrays.asList(true, true, true, true, true, true, true);
        List<String> stringHolder = Collections.singletonList(CHEST);

        myStrengthProgram = new MyStrengthProgram(225, 315, 405, 90, booleanHolder, stringHolder);
    }

    @Test
    void testGettersSetters() {
        assertEquals(7, myStrengthProgram.getMyTrainingWeek().getDaysAvailable());

        myStrengthProgram.setMaxTime(200);
        assertEquals(200, myStrengthProgram.getMaxTime());

        myStrengthProgram.setMyMuscleFocus(Collections.singletonList("happy monkey"));
        assertEquals(Collections.singletonList("happy monkey"), myStrengthProgram.getMyMuscleFocus());

        myStrengthProgram.setSecondaryHighVolumeRepsStarting(10);
        assertEquals(10, myStrengthProgram.getSecondaryHighVolumeRepsStarting());

        myStrengthProgram.setSecondaryMaxWorkingReps(7);
        assertEquals(7, myStrengthProgram.getSecondaryMaxWorkingReps());

        myStrengthProgram.setSecondaryMaxWorkingSets(5);
        assertEquals(5, myStrengthProgram.getSecondaryMaxWorkingSets());

        myStrengthProgram.setSecondaryRepsToDecrement(2);
        assertEquals(2, myStrengthProgram.getSecondaryRepsToDecrement());

        myStrengthProgram.setSecondaryStartingScale(0.2);
        assertEquals(0.2, myStrengthProgram.getSecondaryStartingScale());

        myStrengthProgram.setSecondaryToScaleIncrease(0.2);
        assertEquals(0.2, myStrengthProgram.getSecondaryToScaleIncrease());
    }

    @Test
    void testWeightCalculation() {
        assertEquals(5*5,myStrengthProgram.calcWeight(5, 5));

        assertEquals(5*6 + " x" + 7 + " <br>", myStrengthProgram.writeSet(5,6,7));

        assertEquals(5*6 + " x" + 7 + "+ <br>", myStrengthProgram.writeTopSet(5,6,7));
    }

    @Test
    void testTimeCalculation() {
        assertEquals(8*PERFORM_TIME,myStrengthProgram.calcPerformTime(8));

        DeadLift deadlift = new DeadLift(405);

        assertEquals(8*deadlift.getRestTime(), myStrengthProgram.calcRestTime(8));

    }

    @Test
    void testAssignCoreTrainingDays() {
        String holder = myStrengthProgram.getMyTrainingWeek().getListOfDay().get(0).getTrainingInstructions();
        int intholder = myStrengthProgram.getMyTrainingWeek().getListOfDay().size();

        assertEquals(7, intholder);
    }

    @Test
    void testAssignSecondaryTrainingDays() {

        myStrengthProgram.setMyMuscleFocus(Collections.singletonList(ABS));
        myStrengthProgram.createProgram();

        assertEquals("<b>Friday</b> <br><b>Standing<br> Bicep Curl</b> <br>70 x8 <br>70 x8 <br>70 x8 <br>70 x8 <br>70 x8+ <br><b>Tricep Cable<br> Pushdown</b> <br>55 x8 <br>60 x7 <br>60 x6 <br>65 x5 <br>65 x4 <br>70 x3 <br>", myStrengthProgram.getMyTrainingWeek().getListOfDay().get(4).getTrainingInstructions());

        myStrengthProgram.setMyMuscleFocus(Collections.singletonList(BACK));
        myStrengthProgram.createProgram();

        assertEquals("<b>Saturday</b> <br><b>Paused Deadlift</b> <br>200 x5 <br>215 x5 <br>225 x5 <br>225 x5 <br>225 x5 <br>225 x5+ <br>", myStrengthProgram.getMyTrainingWeek().getListOfDay().get(5).getTrainingInstructions());

        myStrengthProgram.setMyMuscleFocus(Collections.singletonList(ARMS));
        myStrengthProgram.createProgram();

        assertEquals("<b>Sunday</b> <br>Too many Arm Days!<br>This kid is <br> trying to workout <br> 7 days a week. <br> Stop that.", myStrengthProgram.getMyTrainingWeek().getListOfDay().get(6).getTrainingInstructions());

        myStrengthProgram.setMyMuscleFocus(Collections.singletonList(LEGS));
        myStrengthProgram.createProgram();

        assertEquals("<b>Friday</b> <br><b>Standing<br> Bicep Curl</b> <br>70 x8 <br>70 x8 <br>70 x8 <br>70 x8 <br>70 x8+ <br><b>Tricep Cable<br> Pushdown</b> <br>55 x8 <br>60 x7 <br>60 x6 <br>65 x5 <br>65 x4 <br>70 x3 <br>", myStrengthProgram.getMyTrainingWeek().getListOfDay().get(4).getTrainingInstructions());
    }

    @Test
    void testRobustNonMatchMuscleFocus() {
        List<String> nonMatchMuscle = Collections.singletonList("Apple");
        myStrengthProgram.setMyMuscleFocus(nonMatchMuscle);

        Day testDay = new Day("testDay", true);

        try {
            myStrengthProgram.assignTrainingDay(4, testDay);
            fail();
        } catch (NonMatchMuscleFocus e) {
            System.out.println("Non match muscle focus caught");
            myStrengthProgram.createProgram();
        } catch (TooManyDays e) {
            fail();
        }
    }

    @Test
    void testRobustTooManyDays() {
        Day testDay = new Day("testDay", true);

        try {
            myStrengthProgram.assignTrainingDay(8, testDay);
            fail();
        } catch (NonMatchMuscleFocus e) {
            fail();
        } catch (TooManyDays e) {
            System.out.println("Too Many days in a week caught");
            List<Boolean> booleanHolder = Arrays.asList(true, true, true, true, true, true, true, true);
            List<String> stringHolder = Collections.singletonList(CHEST);

            myStrengthProgram = new MyStrengthProgram(225, 315, 405, 90, booleanHolder, stringHolder);
            myStrengthProgram.createProgram();
        }
    }

}
