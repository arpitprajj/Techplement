
package Quiz_Generator;
import java.util.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, List<Question>> quizzes = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the Java Quiz Generator!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add a question to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": createQuiz(); break;
                case "2": addQuestion(); break;
                case "3": takeQuiz(); break;
                case "4":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void createQuiz() {
        System.out.print("Enter quiz name: ");
        String name = scanner.nextLine();
        if (quizzes.containsKey(name)) {
            System.out.println("Quiz already exists!");
        } else {
            quizzes.put(name, new ArrayList<>());
            System.out.println("Quiz '" + name + "' created.");
        }
    }

    static void addQuestion() {
        System.out.print("Enter quiz name to add question: ");
        String name = scanner.nextLine();

        if (!quizzes.containsKey(name)) {
            System.out.println("Quiz not found!");
            return;
        }

        System.out.print("Enter question: ");
        String questionText = scanner.nextLine();

        List<String> options = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            System.out.print("Enter option " + i + ": ");
            options.add(scanner.nextLine());
        }

        System.out.print("Enter correct option number (1-4): ");
        int correct;
        try {
            correct = Integer.parseInt(scanner.nextLine()) - 1;
            if (correct < 0 || correct >= 4) throw new Exception();
        } catch (Exception e) {
            System.out.println("Invalid option number!");
            return;
        }

        quizzes.get(name).add(new Question(questionText, options, correct));
        System.out.println("Question added successfully!");
    }

    static void takeQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Available quizzes:");
        for (String name : quizzes.keySet()) {
            System.out.println("- " + name);
        }

        System.out.print("Enter quiz name to take: ");
        String name = scanner.nextLine();

        if (!quizzes.containsKey(name)) {
            System.out.println("Quiz not found.");
            return;
        }

        List<Question> questions = quizzes.get(name);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ": " + q.questionText);
            for (int j = 0; j < q.options.size(); j++) {
                System.out.println((j + 1) + ". " + q.options.get(j));
            }

            System.out.print("Your answer (1-4): ");
            int ans;
            try {
                ans = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (Exception e) {
                System.out.println("Invalid input! Skipping question.");
                continue;
            }

            if (ans == q.correctAnswer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer: " + q.options.get(q.correctAnswer));
            }
        }
        System.out.println("\nQuiz Completed!");
        System.out.println("You scored " + score + " out of " + questions.size());
    }
}

