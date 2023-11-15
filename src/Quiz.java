import java.util.Scanner;

public class Quiz<T extends IQuestion> {
    private static Scanner sc;
    private final static int SIZE = 30;
    private T[] questions;
    private int freeIndex = 0;

    public T getQuestion(int index) {
        if (index < 0 || index >= freeIndex) {
            System.out.println("Ungültiger Index");
        }
        return questions[index];
    }

    public int countQuestions() {
        return freeIndex;
    }

    public boolean removeQuestion(int index) {
        if (index < 0 || index >= freeIndex) {
            System.out.println("Ungültiger Index.");
            return false;
        }
        for (int i = index; i < freeIndex && i < SIZE - 1; i++) {
            questions[i] = questions[i + 1];
        }
        questions[--freeIndex] = null;
        return true;
    }

    public void addQuestion(T question) {
        if (freeIndex >= SIZE) {
            System.out.println("Liste voll!");
            return;
        }
        questions[freeIndex++] = question;
    }

    public Quiz(T[] questions) {
        this.questions = questions;
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        Quiz<Question> quiz = new Quiz<>(new Question[SIZE]);
        Quiz<EasyQuestion> easyQuiz = new Quiz<>(new EasyQuestion[SIZE]);
        Quiz<HardQuestion> hardQuiz = new Quiz<>(new HardQuestion[SIZE]);

        YesNoQuestion q1 = new YesNoQuestion(
                "Die Hauptstadt von Marokko ist Madrid",
                3,
                false
        );
        GapQuestion q2 = new GapQuestion(
                "Es gibt __ UML Diagramme!",
                10,
                "14"
        );
        GapQuestion q3 = new GapQuestion("Wir schreiben das Jahr ____.", 1, "2023");
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.play();
    }

    private void play() {
        for (int i = 0; i < freeIndex; i++) {
            T q = getQuestion(i);
            q.print();
            String eingabe = sc.nextLine();
            boolean result = q.verify(eingabe);
            if (result) {
                System.out.println("Antwort ist richtig!");
            } else {
                System.out.println("Antwort ist falsch!");
            }
        }
    }

}