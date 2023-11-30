import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SingleChoiceQuestion extends Question {

    private List<Choice> choices = new ArrayList<>();

    public SingleChoiceQuestion(String text, int points) {
        super(text, points);
    }

    @Override
    public String print() {
        String s = super.print();
        s += System.lineSeparator();
        for (int i = 0; i < choices.size(); i++) {
            s += String.format("%d - %s\n", (i + 1), choices.get(i).getText());
        }
        return s;
    }

    public void sortChoices() {
        choices.sort(new ChoiceComparator());
    }

    public void shuffle() {
        Collections.shuffle(choices);
    }

    public void addChoice(Choice choice) {
        choices.add(choice);
    }

    @Override
    public boolean verify(String input) {
        int choiceNr = -1;
        try {
            choiceNr = Integer.parseInt(input);
        }
        catch (NumberFormatException ex) {
            throw new InvalidChoiceException("Eingabe ist keine Zahl!");
        }
        if (choiceNr < 1 || choices.size() > 4) {
            throw new InvalidChoiceException("ChoiceNr gibt es nicht.");
        }
        setAnswer(input);
        Choice choice = choices.get(choiceNr - 1);
        return choice.isCorrect();
    }
}
