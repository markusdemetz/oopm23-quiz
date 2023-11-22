public class Choice implements Comparable<Choice> {
    private String text;
    private boolean correct;

    public Choice(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public int compareTo(Choice o) {
        int l1 = getText().length();
        int l2 = o.getText().length();
        return Integer.compare(l1, l2);
    }
}
