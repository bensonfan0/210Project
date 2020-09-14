package calendar;

import java.util.ArrayList;
import java.util.List;

// Class representing 7 days of the week, consisting of a ListOfDay (list size is always 7)
public class Week {
    private int weekNum;
    private List<Day> listOfDay;
    private int daysAvailable;

    public Week(int weekNum, List<Boolean> availability) {
        listOfDay = new ArrayList<>();
        this.weekNum = weekNum;
        createWeek(availability);
        daysAvailable = calcDaysAvailable();
    }

    //    getters
    public List<Day> getListOfDay() {
        return listOfDay;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public int getDaysAvailable() {
        return daysAvailable;
    }

    //    REQUIRES: availability will be a list of 7 booleans
//    MODIFIES: this
//    EFFECTS: creates a week with according availabilities
    private void createWeek(List<Boolean> availablility) {
        int placeIndex = 0;
        for (boolean b : availablility) {
            switch (placeIndex) {
                case 0:
                    Day monday = new Day("Monday", b);
                    listOfDay.add(monday);
                    break;
                case 1:
                    Day tuesday = new Day("Tuesday", b);
                    listOfDay.add(tuesday);
                    break;
                case 2:
                    Day wednesday = new Day("Wednesday", b);
                    listOfDay.add(wednesday);
                    break;
                default:
                    createWeek1(availablility, placeIndex);
                    break;
            }
            placeIndex++;
        }
    }

    private void createWeek1(List<Boolean> availability, int placeIndex) {
        switch (placeIndex) {
            case 3:
                Day thursday = new Day("Thursday", availability.get(placeIndex));
                listOfDay.add(thursday);
                break;
            case 4:
                Day friday = new Day("Friday", availability.get(placeIndex));
                listOfDay.add(friday);
                break;
            case 5:
                Day saturday = new Day("Saturday", availability.get(placeIndex));
                listOfDay.add(saturday);
                break;
            default:
                Day sunday = new Day("Sunday", availability.get(placeIndex));
                listOfDay.add(sunday);
        }
        placeIndex++;

    }

    //    EFFECTS: returns int of days with availability = true
    public int calcDaysAvailable() {
        int daysAvailable = 0;
        for (Day d : listOfDay) {

            if (d.getAvailable()) {
                daysAvailable++;
            }

        }
        return daysAvailable;
    }


}
