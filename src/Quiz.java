import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
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

        while(true) {
            System.out.println("Bitte Spielername eingeben: ");
            String player = sc.nextLine();
            if (player.isBlank()) {
                break;
            }
            players.put(player, new Score());
        }

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

        saveQuizReport(quiz);

        for (String player : players.keySet()) {
            System.out.printf(
                    "Score of player %s: %s\n",
                    player,
                    players.get(player).toString()
            );
        }
    }

    private static void saveQuizReport(Quiz<Question> quiz) {
        String fileName = "quiz_" + LocalDateTime.now().toString().replace(":", "_") + ".txt";
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            for (Question q : quiz.questions) {
                out.write(q.print());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void play(Map<String, Score> players) {
        for (int i = 0; i < questions.size(); i++) {
            for (String player : players.keySet()) {
                T q = getQuestion(i);
                System.out.println("Spieler: " + player);
                System.out.println(q.print());
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
                System.out.println();
            }
        }
    }

}