package ru.b60d.model.examles;

/**
 * Created by Administrator1 on 18.05.2017.
 */
public class TextExampl {
    private String question;
    private String answer;

    public TextExampl(String question, String ansver) {
        this.question = question;
        this.answer = ansver;
    }
    public boolean testAnswer(String answer){
        return this.answer.equalsIgnoreCase(answer);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "TextExampl{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
