import java.util.Scanner;

public class Quiz {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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

        IQuestion[] questions = {
                q1,
                q2,
                new GapQuestion("Wir schreiben das Jahr ____.", 1, "2023")
        };

        EasyQuestion[] easyQuestions = { q1, q2 };
        HardQuestion[] hardQuestions = { q2 };

        startEasyQuiz(easyQuestions);

        for (IQuestion q : questions) {
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

    public static void startEasyQuiz(EasyQuestion[] questions) {

    }

    public static void startHardQuiz(HardQuestion[] questions) {

    }
}