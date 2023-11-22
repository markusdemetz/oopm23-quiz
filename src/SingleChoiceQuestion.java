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
    public void print() {
        super.print();
        System.out.println();
        for (int i = 0; i < choices.size(); i++) {
            System.out.printf("%d - %s\n", (i + 1), choices.get(i).getText());
        }
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
        int choiceNr = Integer.parseInt(input);
        Choice choice = choices.get(choiceNr - 1);
        return choice.isCorrect();
    }
}
