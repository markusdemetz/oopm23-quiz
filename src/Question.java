public abstract class Question implements IQuestion {
    private String text;
    private int points;

    public Question(String text, int points) {
        this.points = points;
        this.text = text;
    }

    public void print() {
        System.out.printf("%s (%d Pts.)", text, points);
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

}
