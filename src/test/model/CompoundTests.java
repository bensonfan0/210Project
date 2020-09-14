package model;

import model.exercises.Compound;
import model.exercises.mainmovements.Bench;
import model.exercises.mainmovements.DeadLift;
import model.exercises.mainmovements.Squat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CompoundTests {

    Compound deadlift;
    Compound bench;
    Compound squat;

    @BeforeEach
    void runBefore() {
        deadlift = new DeadLift(495);
        bench = new Bench(225);
        squat = new Squat(405);
    }

    @Test
    void testConstruction() {
        assertEquals(495, deadlift.getPersonalRecord());
        assertEquals(405, squat.getPersonalRecord());
        assertEquals(225, bench.getPersonalRecord());

    }

    @Test
    void testSetterGetters() {
        assertEquals((int) (495 * 0.9), deadlift.getTrainingMax());

        deadlift.setName("Deadlift");
        assertEquals("Deadlift", deadlift.getName());

        deadlift.setTrainingMax(100);
        assertEquals(100, deadlift.getTrainingMax());

        deadlift.setPersonalRecord(200);
        assertEquals(200, deadlift.getPersonalRecord());

        deadlift.setRestTime(3.5);
        assertEquals(3.5, deadlift.getRestTime());

    }




}
