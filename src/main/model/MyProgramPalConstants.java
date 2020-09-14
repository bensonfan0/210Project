package model;

import model.exercises.Accessory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//    Holds string, int, double, and List constants used throughout program. Also instantiates accessory exercises
//    which are used throughout the program.

public abstract class MyProgramPalConstants {

    public static final String BEGINNER = "novel";
    public static final String INTERMEDIATE = "intermediate";

    public static final String CHEST = "chest";
    public static final String BACK = "back";
    public static final String ARMS = "arms";
    public static final String LEGS = "legs";
    public static final String ABS = "abs";
    public static final String UPPERCHEST = "upperchest";
    public static final String SIDEDELTS = "sidedelts";
    public static final String REARDELTS = "reardelts";
    public static final String FRONTDELTS = "frontdelts";
    public static final String TRAPS = "traps";
    public static final String LATS = "lats";
    public static final String BACKWIDTH = "back-width";
    public static final String BICEPS = "biceps";
    public static final String TRICEPS = "triceps";
    public static final String FOREARMS = "forearms";
    public static final String LOWERABS = "lowerabs";
    public static final String UPPERABS = "upperabs";
    public static final String QUADS = "quads";
    public static final String CALVES = "calves";
    public static final String HAMSTRINGS = "hamstrings";
    public static final String NA = "n/a";

    public static final String STRENGTH_COMMAND = "strength";
    public static final String HYPERTROPHY_COMMAND = "hypertrophy";

    public static final List<String> novelList = Arrays.asList(CHEST, BACK, ARMS, LEGS, ABS, NA);

    public static final int SETS_AT_9_RPE_OR_GREATER = 3;
    public static final int SETS_RPE_8_LESS = 4;

    public static final int EMOM_LENGTH = 8;

//    Time needed to perform said exercise set
    protected static final double PERFORM_TIME = 0.5;

    protected static final String REST_DAY = "Rest Day";

    protected static Accessory bicepCurl = new Accessory(8, 2, "Bicep Curl", Arrays.asList(ARMS, BICEPS));
    protected static Accessory cableAbCurl = new Accessory(9, 2,"Cable Ab Curl", Arrays.asList(ABS, UPPERABS));
    protected static Accessory calfRaises = new Accessory(9, 2.5,"Calf Raises", Arrays.asList(LEGS, CALVES));
    protected static Accessory chestFly = new Accessory(8, 2, "Chest Fly", Arrays.asList(CHEST, UPPERCHEST));
    protected static Accessory facePulls = new Accessory(9, 2, "Face Pulls", Arrays.asList(ARMS, REARDELTS, BACK));
    protected static Accessory hammerCurl = new Accessory(8, 2, "Hammer Curl", Arrays.asList(ARMS, BICEPS));
    protected static Accessory inclineChestPress;

    static {
        inclineChestPress = new Accessory(9, 2.5, "Incline Chest Press", Arrays.asList(CHEST, UPPERCHEST));
    }

    protected static Accessory latPullDown = new Accessory(9, 2, "Lat PullDown", Arrays.asList(BACK, BACKWIDTH, LATS));
    protected static Accessory legCurl = new Accessory(8, 1.5, "Leg Curl", Arrays.asList(LEGS, HAMSTRINGS));
    protected static Accessory legExtension = new Accessory(8, 1.5, "Leg Extension", Arrays.asList(LEGS, QUADS));
    protected static Accessory legRaise = new Accessory(9,2, "Leg Raise", Arrays.asList(LOWERABS, ABS));
    protected static Accessory medialDeltRaise;

    static {
        medialDeltRaise = new Accessory(8, 1.5, "Medial Delt Raise", Arrays.asList(ARMS, SIDEDELTS));
    }

    protected static Accessory ohp;

    static {
        ohp = new Accessory(9, 3, "OverHead Press", Arrays.asList(ARMS, SIDEDELTS, REARDELTS, FRONTDELTS));
    }

    protected static Accessory preacherCurl = new Accessory(9,1.5, "Preacher Curl", Arrays.asList(ARMS, BICEPS));
    protected static Accessory rows = new Accessory(9,2, "Rows", Arrays.asList(BACK, BACKWIDTH));
    protected static Accessory shrugs = new Accessory(9,1.5, "Shrugs", Arrays.asList(BACK, TRAPS));
    protected static Accessory tricepExtension = new Accessory(9,1.5, "Tricep Extension", Arrays.asList(ARMS, TRICEPS));

    private List<Accessory> a1 = Arrays.asList(cableAbCurl, calfRaises, chestFly, facePulls, tricepExtension);
    private List<Accessory> a2 = Arrays.asList(hammerCurl, inclineChestPress, latPullDown, legCurl, legExtension);
    private List<Accessory> a3 = Stream.concat(a1.stream(), a2.stream()).collect(Collectors.toList());
    private List<Accessory> a4 = Arrays.asList(legRaise, medialDeltRaise, ohp, preacherCurl, rows, shrugs, bicepCurl);

    public List<Accessory> accessoryList = Stream.concat(a3.stream(), a4.stream()).collect(Collectors.toList());

    public List<String> chest = Arrays.asList(CHEST, UPPERCHEST);
    public List<String> back = Arrays.asList(BACK, BACKWIDTH, LATS, TRAPS);
    public List<String> legs = Arrays.asList(LEGS, QUADS, HAMSTRINGS, CALVES);
    public List<String> abs = Arrays.asList(UPPERABS, LOWERABS, ABS);
    public List<String> arms = Arrays.asList(ARMS, FOREARMS, BICEPS, TRICEPS, SIDEDELTS, REARDELTS, FRONTDELTS);

}
