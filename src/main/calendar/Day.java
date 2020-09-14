package calendar;

// Day is either available for training, or a rest day. Day holds string which will describe exercises/training scheme.
public class Day {
    private String name;
    private boolean available;
    private String trainingInstructions;
    private String accessories;

    public Day(String name, boolean available) {
        this.name = name;
        this.available = available;
        trainingInstructions = "";
        accessories = "";
    }

//    setters
    public void setName(String name) {
        this.name = name;
    }

    //    getters
    public String getName() {
        return name;
    }

    public boolean getAvailable() {
        return available;
    }

    //uses HTML
    public String getTrainingInstructions() {
        return "<b>" + name + "</b>" + " <br>" + trainingInstructions;
    }

    //uses HTML
    public String getAccessories() {
        return "<b>" + name + "</b>" + " <br>" + accessories;
    }


//    EFFECTS: adds onto training instructions
    public void createNewInstructions(String moreInstructions) {
        trainingInstructions = moreInstructions;
    }

    public void createAccessories(String accessories) {
        this.accessories += accessories;
    }

}
