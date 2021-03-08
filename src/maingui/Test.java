/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

import java.io.Serializable;
import java.util.Date;

//class that will hold the information present in report
public class Test implements Serializable{
    String user = TestSystem.user.getUser(), test;
    int score, miss; 
    String level;
    
    String duration;
    Date examDate;

    public Test( String test, String level, int score, int miss,  String duration, Date examDate) {
        
        this.test = test;
        
        this.level = level;
        this.score = score;
        this.miss= miss;
        this.duration = duration;
        this.examDate = examDate;
    }

    public Test() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }
    
    

    
    
    
    
}
