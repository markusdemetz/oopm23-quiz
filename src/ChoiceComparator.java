import java.util.Comparator;

public class ChoiceComparator implements Comparator<Choice> {

    @Override
    public int compare(Choice o1, Choice o2) {
        return Boolean.compare(o1.isCorrect(), o2.isCorrect());
    }
}
