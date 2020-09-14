package model;

import model.exercises.Accessory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessoryTest extends MyProgramPalConstants {

    Accessory testOHP;
    Accessory testOHP1;


    @BeforeEach
    void runBefore(){
        testOHP = new Accessory(9, 1, "OHP", Arrays.asList(ARMS, SIDEDELTS, REARDELTS, FRONTDELTS));
        testOHP1 = new Accessory(7, 2, "OHP", Arrays.asList(ARMS, SIDEDELTS, REARDELTS, FRONTDELTS));
    }

    @Test
    void testConstruction(){
        assertEquals(1, testOHP.getRestTime());
        assertEquals(9, testOHP.getRateOfPercievedExhaustion());
        assertEquals(SETS_AT_9_RPE_OR_GREATER, testOHP.getNumberOfSets());
        assertEquals(SETS_RPE_8_LESS, testOHP1.getNumberOfSets());

        List<String> expectedMuscleTarget = Arrays.asList(ARMS, SIDEDELTS, REARDELTS, FRONTDELTS);

        assertEquals(expectedMuscleTarget, testOHP1.getMuscleTarget());

        assertEquals("OHP", testOHP1.getName());

        testOHP1.setRateOfPercievedExhaustion(10);
        assertEquals(10, testOHP1.getRateOfPercievedExhaustion());
    }

    @Test
    void testGettersSetters(){
        testOHP.setRateOfPercievedExhaustion(10);
        assertEquals(10, testOHP.getRateOfPercievedExhaustion());

        testOHP.setMuscleTarget(Arrays.asList("chest"));

        assertEquals(1, testOHP.getMuscleTarget().size());
        assertEquals("chest", testOHP.getMuscleTarget().get(0));

        testOHP.setRestTime(2.5);
        assertEquals(2.5, testOHP.getRestTime());
    }

    @Test
    void testCalcTime() {
        int time = (int) (testOHP.getNumberOfSets()*testOHP.getRestTime()) + (int) (testOHP.getNumberOfSets()*PERFORM_TIME);
        assertEquals(time, testOHP.calcTime());
    }

//    "this.name for x at RPE z" or "this.name for x EMOM at RPE z or
    //             for y sets"
    @Test
    void testWriteSet() {
        String expected = "<u><i>OHP</i></u> for " + SETS_AT_9_RPE_OR_GREATER + " sets, RPE " + testOHP.getRateOfPercievedExhaustion() + " <br>";
        String expected2 = "<u><i>OHP</i></u> for " + EMOM_LENGTH + " minute EMOM, RPE " + testOHP1.getRateOfPercievedExhaustion();
        expected2 += " or for " + SETS_RPE_8_LESS + " sets <br>";

        assertEquals(expected, testOHP.writeSet());
        assertEquals(expected2, testOHP1.writeSet());

    }
}
