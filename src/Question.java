public abstract class Question implements IQuestion {
    private String text;
    private int points;
    private String answer;

    public Question(String text, int points) {
        this.points = points;
        this.text = text;
    }

    public String print() {
        return String.format("%s (%d Pts.)", text, points);
    }

    @Override
    public void increaseScore() {
        this.points ++;
    }

    public abstract boolean verify(String input);

    @Override
    public int getPoints() {
        return points;
    }

    protected void setAnswer(String answer) {
        this.answer = answer;
    }
}
