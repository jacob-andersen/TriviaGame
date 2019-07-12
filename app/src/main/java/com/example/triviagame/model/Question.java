package com.example.triviagame.model;

public class Question {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correctAnswer;
    private String[] incorrectAnswers;

    public Question(String category, String type, String difficulty, String question, String correctAnswer, String[] incorrectAnswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(String[] incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }
}

//     "category": "Entertainment: Video Games",
//             "type": "multiple",
//             "difficulty": "easy",
//             "question": "In which mall does &quot;Dead Rising&quot; take place?",
//             "correct_answer": "Willamette Parkview Mall",
//             "incorrect_answers":
//}
