package model;

import calendar.Calendar;
import calendar.Day;
import calendar.Week;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarTests {
    private Calendar testCalendar;
    private Week testWeek;
    private Day testDayTrue;
    private Day testDayFalse;


    @BeforeEach
    void runBefore() {
        List<Boolean> initializeWeek = new ArrayList<>();

//        even days are available, odd days are not
        for (int i = 0; i < 7 ; i++) {
            if (i % 2 == 0) {
                initializeWeek.add(true);
            } else {
                initializeWeek.add(false);
            }
        }

        testCalendar = new Calendar(1, initializeWeek);
        testWeek = new Week(1, initializeWeek);
        testDayTrue = new Day("testTrue", true);
        testDayFalse = new Day("testFalse", false);
    }

    @Test
    void testCreateDay() {
        assertEquals("testTrue", testDayTrue.getName());
        assertFalse(testDayFalse.getAvailable());
    }

    @Test
    void testCreateWeek() {
        assertEquals(1, testWeek.getWeekNum());
        assertEquals(7, testWeek.getListOfDay().size());

        Day expectedMonday = testWeek.getListOfDay().get(0);

        assertEquals("Monday", expectedMonday.getName());

        Day expectedSunday = testWeek.getListOfDay().get(6);

        assertEquals("Sunday", expectedSunday.getName());
    }

    @Test
    void testWeekDaysAvailable() {
        assertEquals(4, testWeek.getDaysAvailable());
    }

    @Test
    void testCalendar() {
        List<Week> expectedWeek = testCalendar.getWeeks();
        assertEquals(1, expectedWeek.size());

        List<Boolean> booleanHolder = Arrays.asList(true, false, true);
        Week weekHolder = new Week(1, booleanHolder);

        ArrayList<Week> weekListHolder = new ArrayList<>();
        weekListHolder.add(weekHolder);

        testCalendar.setWeeks(weekListHolder);
    }

    @Test
    void testDay() {
        testDayFalse.createNewInstructions("happy monkey");
        assertEquals("<b>testFalse</b> <br>happy monkey", testDayFalse.getTrainingInstructions());

        testDayFalse.setName("Tuesday");
        assertEquals("Tuesday", testDayFalse.getName());
    }


    @Test
    void getAccessories() {
        testDayTrue.createAccessories("i love you");

        assertEquals("<b>testTrue</b> <br>i love you", testDayTrue.getAccessories());
    }


}
