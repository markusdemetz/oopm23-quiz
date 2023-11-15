public class SingleChoiceQuestion extends Question {
    public SingleChoiceQuestion(String text, int points) {
        super(text, points);
    }

    @Override
    public boolean verify(String input) {
        return false;
    }
}
