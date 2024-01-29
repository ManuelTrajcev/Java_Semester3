package mk.ukim.finki.lab8;

import javax.management.QueryExp;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

interface QuestionStrategy {
    int isCorrect(TriviaQuestion question, String answer);
    void printQuestion(TriviaQuestion question);
}

class FreeFormStrategy implements QuestionStrategy {

    @Override
    public int isCorrect(TriviaQuestion question, String answer) {
        if (question.answer.equalsIgnoreCase(answer)) {
            System.out.println("That is correct!  You get " + question.value + " points.");
            return question.value;
        } else {
            System.out.println("Wrong, the correct answer is " + question.answer);
            return 0;
        }
    }

    @Override
    public void printQuestion(TriviaQuestion question) {
        System.out.println(question.question);
    }
}

class TrueFalseStrategy implements QuestionStrategy {

    @Override
    public int isCorrect(TriviaQuestion question, String answer) {
        if (question.answer.toLowerCase().charAt(0) == answer.toLowerCase().charAt(0)) {
            System.out.println("That is correct!  You get " + question.value + " points.");
            return question.value;
        } else {
            System.out.println("Wrong, the correct answer is " + question.answer);
            return 0;
        }
    }

    @Override
    public void printQuestion(TriviaQuestion question) {
        System.out.println(question.question);
        System.out.println("Enter 'T' for true or 'F' for false.");
    }
}

class TriviaQuestion {
    public static final int TRUEFALSE = 0;
    public static final int FREEFORM = 1;
    public String question;        // Actual question
    public String answer;        // Answer to question
    public int value;            // Point value of question
    public int type;            // Question type, TRUEFALSE or FREEFORM

    public TriviaQuestion() {
        question = "";
        answer = "";
        value = 0;
        type = FREEFORM;
    }

    public TriviaQuestion(String q, String a, int v, int t) {
        question = q;
        answer = a;
        value = v;
        type = t;
    }
}

class TriviaData {

    private ArrayList<TriviaQuestion> data;

    public TriviaData() {
        data = new ArrayList<TriviaQuestion>();
    }

    public void addQuestion(String q, String a, int v, int t) {
        TriviaQuestion question = new TriviaQuestion(q, a, v, t);
        data.add(question);
    }

    public void showQuestion(int index) {
        TriviaQuestion q = data.get(index);
        System.out.println("Question " + (index + 1) + ".  " + q.value + " points.");
        if (q.type == TriviaQuestion.TRUEFALSE) {
            QuestionStrategy qs = new TrueFalseStrategy();
            qs.printQuestion(q);
        } else if (q.type == TriviaQuestion.FREEFORM) {
            QuestionStrategy qs = new FreeFormStrategy();
            qs.printQuestion(q);
        }
    }

    public int numQuestions() {
        return data.size();
    }

    public TriviaQuestion getQuestion(int index) {
        return data.get(index);
    }
}

public class TriviaGame {

    public TriviaData questions;    // Questions

    public TriviaGame() {
        // Load questions
        questions = new TriviaData();
        questions.addQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, TriviaQuestion.FREEFORM);
        questions.addQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, TriviaQuestion.TRUEFALSE);
        questions.addQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, TriviaQuestion.FREEFORM);
        questions.addQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2, TriviaQuestion.FREEFORM);
        questions.addQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1, TriviaQuestion.TRUEFALSE);
    }
    // Main game loop

    public static void main(String[] args) {
        int score = 0;            // Overall score
        int questionNum = 0;    // Which question we're asking
        TriviaGame game = new TriviaGame();
        Scanner keyboard = new Scanner(System.in);
        // Ask a question as long as we haven't asked them all
        while (questionNum < game.questions.numQuestions()) {
            // Show question
            game.questions.showQuestion(questionNum);
            // Get answer
            String answer = keyboard.nextLine();
            // Validate answer
            TriviaQuestion q = game.questions.getQuestion(questionNum);
            if (q.type == TriviaQuestion.TRUEFALSE) {
                QuestionStrategy qs = new TrueFalseStrategy();
                score += qs.isCorrect(q, answer);
            } else if (q.type == TriviaQuestion.FREEFORM) {
                QuestionStrategy qs = new FreeFormStrategy();
                score += qs.isCorrect(q, answer);
            }
            System.out.println("Your score is " + score);
            questionNum++;
        }
        System.out.println("Game over!  Thanks for playing!");
    }
}
