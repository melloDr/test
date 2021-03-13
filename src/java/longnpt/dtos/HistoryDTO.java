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
public class HistoryDTO {
    int idHistory;
    int idSubject;
    String id;
    String startTime;
    String endTime;
    int sumCorrect;
    float point;

    public HistoryDTO() {
    }

    public HistoryDTO(int idHistory, int idSubject, String id, String startTime, String endTime, int sumCorrect, float point) {
        this.idHistory = idHistory;
        this.idSubject = idSubject;
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sumCorrect = sumCorrect;
        this.point = point;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getSumCorrect() {
        return sumCorrect;
    }

    public void setSumCorrect(int sumCorrect) {
        this.sumCorrect = sumCorrect;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
    
}
