package models;

/**
 * Choices class that links descriptions that the user can see and choose.
 * There are some choices available and the latest (old) choice is also saved so that the program knows this.
 * This class also has a numberofchoices variable that tells to the program how many choices that will be shown and selectable.
 */
public class Choices {
    private int numberOfChoices;
    private String choiceA = "a";
    private String choiceB = "b";
    private String choiceC = "c";
    private String oldChoiceA = "a";
    private String oldChoiceB = "b";
    private String oldChoiceC = "c";

    /**
     *
     * @return Old choice a to know what the old choice a was.
     */
    public String getOldChoiceA() {
        return oldChoiceA;
    }

    public void setOldChoiceA(String oldChoiceA) {
        this.oldChoiceA = oldChoiceA;
    }

    public String getOldChoiceB() {
        return oldChoiceB;
    }

    public void setOldChoiceB(String oldChoiceB) {
        this.oldChoiceB = oldChoiceB;
    }

    public String getOldChoiceC() {
        return oldChoiceC;
    }

    public void setOldChoiceC(String oldChoiceC) {
        this.oldChoiceC = oldChoiceC;
    }

    public int getNumberOfChoices() {
        return numberOfChoices;
    }

    public void setNumberOfChoices(int numberOfChoices) {
        this.numberOfChoices = numberOfChoices;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }
}
