public class YesNoQuestion extends Question implements EasyQuestion{
    private boolean correct;
    private String hint;

    public YesNoQuestion(String text, int points, boolean correct) {
        super(text, points);
        this.correct = correct;
        this.hint = "Leider kein Hinweis vorhanden";
    }

    @Override
    public boolean verify(String input) {
        setAnswer(input);
        // Input kann sein: ja, yes, true, oui, si
        switch (input.toLowerCase()) {
            case "ja":
            case "yes":
            case "true":
            case "oui":
            case "si":
                return correct;
            default:
                return !correct;
        }
    }

    @Override
    public String hint() {
        return this.hint;
    }

    @Override
    public void setHint(String hint) {
        this.hint = hint;
    }
}
