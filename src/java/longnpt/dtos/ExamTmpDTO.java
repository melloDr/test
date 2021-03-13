/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnpt.dtos;

/**
 *
 * @author Admin
 */
public class ExamTmpDTO {
    int idChoice;
    String question;
    String answer;
    String choice;
    boolean isCorrect;
    int idQuestion;

    public ExamTmpDTO() {
    }

    public ExamTmpDTO(int idChoice, String question, String answer, String choice, boolean isCorrect, int idQuestion) {
        this.idChoice = idChoice;
        this.question = question;
        this.answer = answer;
        this.choice = choice;
        this.isCorrect = isCorrect;
        this.idQuestion = idQuestion;
    }

    public int getIdChoice() {
        return idChoice;
    }

    public void setIdChoice(int idChoice) {
        this.idChoice = idChoice;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }
    
    
}
