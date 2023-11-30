public class GapQuestion extends Question
        implements EasyQuestion, HardQuestion {

    private final String answer;
    private String hint;

    public GapQuestion(String text, int points, String answer) {
        super(text, points);
        this.answer = answer;
    }

    @Override
    public boolean verify(String input) {
        setAnswer(input);
        return input.equalsIgnoreCase(answer);
    }

    @Override
    public String hint() {
        return hint;
    }

    @Override
    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public int bonusPoints() {
        return 10;
    }
}
