package model;

import model.user.MyProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyProfileTests {
    MyProfile testMyProfile;

    @BeforeEach
    void runBefore() {
        testMyProfile = new MyProfile();
    }

    @Test
    void testSettersGetters(){
        testMyProfile.setExperienceLevel("test");
        assertEquals("test", testMyProfile.getExperienceLevel());

        testMyProfile.setPrimaryCompound("squat");
        assertEquals("squat", testMyProfile.getPrimaryCompound());

        testMyProfile.setMaxTime(160);
        assertEquals(160, testMyProfile.getMaxTime());

        testMyProfile.setMuscleFocus("test");
        String holder = testMyProfile.getMuscleFocus().get(0);
        assertEquals("test", holder);

        testMyProfile.setLiftingGoal("strength");
        assertEquals("strength", testMyProfile.getLiftingGoal());


        testMyProfile.setMyDay(true);
        testMyProfile.setMyDay(false);
        testMyProfile.setMyDay(true);

        assertEquals(3, testMyProfile.getMyWeek().size());
        assertEquals(true, testMyProfile.getMyWeek().get(0));
        assertEquals(false, testMyProfile.getMyWeek().get(1));

        testMyProfile.setBenchPR(225);
        assertEquals(225, testMyProfile.getBenchPR());

        testMyProfile.setDlPR(405);
        assertEquals(405, testMyProfile.getDlPR());

        testMyProfile.setSquatPR(315);
        assertEquals(315, testMyProfile.getSquatPR());

        }

    @Test
    void testListIntersection() {
        List<String> list1 = Arrays.asList("red", "blue", "green");
        List<String> list2 = Arrays.asList("red", "yellow");
        List<String> list3 = Arrays.asList("sapphire");

        assertTrue(testMyProfile.listIntersects(list1, list2));
        assertFalse(testMyProfile.listIntersects(list1, list3));
    }

    @Test
    void testMuscleFocus() {
        String stringHolder = null;
        int intHolder = 0;
        assertEquals(0, testMyProfile.getMuscleFocus().size());

        testMyProfile.setMuscleFocus("chest");

        intHolder = testMyProfile.getMuscleFocus().size();
        assertEquals(1, intHolder);

        stringHolder = testMyProfile.getMuscleFocus().get(0);
        assertEquals("chest", stringHolder);

        testMyProfile.setMuscleFocus("back");
        intHolder = testMyProfile.getMuscleFocus().size();
        assertEquals(1, intHolder);

        stringHolder = testMyProfile.getMuscleFocus().get(0);
        assertEquals("back", stringHolder);

        testMyProfile.setMuscleFocus("happy");
        testMyProfile.setMuscleFocus("sad");

        intHolder = testMyProfile.getMuscleFocus().size();
        assertEquals(2, intHolder);

        stringHolder = testMyProfile.getMuscleFocus().get(1);
        assertEquals("sad", stringHolder);

        testMyProfile.setMuscleFocus("kind");
        testMyProfile.setMuscleFocus("foolish");

        stringHolder = testMyProfile.getMuscleFocus().get(0);
        assertEquals("sad", stringHolder);
    }

    @Test
    void testProgramAssigner() {
        List<String> stringListHolder = Arrays.asList("chest");

        testMyProfile.setSquatPR(315);
        testMyProfile.setDlPR(405);
        testMyProfile.setBenchPR(225);
        testMyProfile.setMaxTime(120);
        testMyProfile.getAssignedProgram();
        testMyProfile.setLiftingGoal("strength");
        testMyProfile.setMuscleFocus("chest");

        testMyProfile.programAssigner();

         assertEquals(stringListHolder, testMyProfile.getAssignedProgram().getMyMuscleFocus());
         assertEquals(120, testMyProfile.getAssignedProgram().getMaxTime());

    }

    @Test
    void setMyWeek() {
        List<Boolean> listBooleanHolder = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            listBooleanHolder.add(true);
        }

        testMyProfile.setMyWeek(listBooleanHolder);

        assertEquals(testMyProfile.getMyWeek(), listBooleanHolder);
    }


}