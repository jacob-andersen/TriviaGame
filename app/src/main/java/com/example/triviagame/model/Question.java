package com.example.triviagame.model;

import java.util.Arrays;

public class Question {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;

    public Question(String category, String type, String difficulty, String question, String correct_answer, String[] incorrect_answers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(String[] incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", question='" + question + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", incorrect_answers=" + Arrays.toString(incorrect_answers) +
                '}';
    }
}

//     "category": "Entertainment: Video Games",
//             "type": "multiple",
//             "difficulty": "easy",
//             "question": "In which mall does &quot;Dead Rising&quot; take place?",
//             "correct_answer": "Willamette Parkview Mall",
//             "incorrect_answers":
//}
