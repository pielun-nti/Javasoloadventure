package models;

/**
 * Choices class. Links descriptions.
 */
public class Choices {
    private int numberOfChoices;
    private String choiceA = "a";
    private String choiceB = "b";
    private String choiceC = "c";
    private String oldChoiceA = "a";
    private String oldChoiceB = "b";
    private String oldChoiceC = "c";

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
