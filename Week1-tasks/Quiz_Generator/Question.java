package Quiz_Generator;
import java.util.*;

class Question {
    String questionText;
    List<String> options;
    int correctAnswer;

    public Question(String questionText, List<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}