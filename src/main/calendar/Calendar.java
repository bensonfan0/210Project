package calendar;

import java.util.ArrayList;
import java.util.List;

// Calendar Class which creates according program mesocycle (# of weeks in training program)
public class Calendar {
    private List<Week> weeks;


    public Calendar(int cycle, List<Boolean> available) {
        weeks = new ArrayList<>();

        for (int i = 1; i <= cycle; i++) {
            weeks.add(new Week(i, available));
        }
    }

    //    setters
    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }

    //    getters
    public List<Week> getWeeks() {
        return this.weeks;
    }


}
