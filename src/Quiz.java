import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz<T extends IQuestion> {
    private static Scanner sc;
    private List<T> questions = new ArrayList<>();

    public T getQuestion(int index) {
        return questions.get(index);
    }

    public int countQuestions() {
        return questions.size();
    }

    public T removeQuestion(int index) {
        return questions.remove(index);
    }

    public void addQuestion(T question) {
        questions.add(question);
    }


    public static void main(String[] args) {
        sc = new Scanner(System.in);

        Quiz<Question> quiz = new Quiz<>();
        Quiz<EasyQuestion> easyQuiz = new Quiz<>();
        Quiz<HardQuestion> hardQuiz = new Quiz<>();

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
        SingleChoiceQuestion q4 = new SingleChoiceQuestion("Welches Tier kann nicht fliegen?", 10);
        q4.addChoice(new Choice("Ente", false));
        q4.addChoice(new Choice("Pinguin", true));
        q4.addChoice(new Choice("Adler", false));
        q4.addChoice(new Choice("Fledermaus", false));
        q4.sortChoices();

        /*quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);*/
        quiz.addQuestion(q4);

        quiz.play();
    }

    private void play() {
        for (int i = 0; i < questions.size(); i++) {
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