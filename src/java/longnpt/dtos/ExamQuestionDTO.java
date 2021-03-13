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
public class ExamQuestionDTO {

    int idQuestion;
    String question;
    String answer;
    String createDate;
    String subject;
    String status;
    int point;

    public ExamQuestionDTO() {
    }

    public ExamQuestionDTO(int idQuestion, String question, String answer, String createDate, String subject, String status, int point) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.answer = answer;
        this.createDate = createDate;
        this.subject = subject;
        this.status = status;
        this.point = point;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
