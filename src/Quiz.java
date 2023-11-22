import java.util.*;

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
        Map<String, Score> players = new HashMap<>();
        String player = null;
        do {
            System.out.println("Bitte Spielername eingeben: ");
            player = sc.nextLine();
            players.put(player, new Score());
        } while (!player.isBlank());

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

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);

        quiz.play(players);

        for (String p : players.keySet()) {
            System.out.printf(
                    "Score of player %s: %d\n",
                    p,
                    players.get(p).toString()
            );
        }
    }

    private void play(Map<String, Score> players) {
        for (int i = 0; i < questions.size(); i++) {
            for (String player : players.keySet()) {
                T q = getQuestion(i);
                q.print();
                boolean result = false;
                boolean flag = true;
                String eingabe = null;
                while (flag) {
                    try {
                        eingabe = sc.nextLine();
                        result = q.verify(eingabe);
                        flag = false;
                    } catch (Exception e) {
                        System.out.println("Ausnahme aufgetreten, bitte erneut eingeben: ");
                    }
                }
                if (result) {
                    System.out.println("Antwort ist richtig!");
                    players.get(player).add(q.getPoints());
                } else {
                    System.out.println("Antwort ist falsch!");
                }
            }
        }
    }

}