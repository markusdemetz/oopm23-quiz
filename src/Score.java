public class Score {

    private int score;

    public Score() {
        reset();
    }

    public void add(int score) {
        this.score += score;
    }

    public void reset() {
        this.score = 0;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}
