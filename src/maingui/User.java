/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

import java.io.Serializable;

//this class is for the current user information

public class User implements Serializable{
    private String User;
    private String Password;
    private Boolean isTeacher;
    private Log log= new Log();
    private String teacherName;
    
    //normal user
    public User(String User, String Password, boolean isTeacher, String teacherName) {
        this.User = User;
        this.Password = Password;
        this.isTeacher= isTeacher;
        this.teacherName= teacherName;
        
    }
    
    
    //load password and teacher status
    public User(String User, boolean teacher, String teacherName) {
        this.User = User;
        this.isTeacher= teacher;
        this.teacherName= teacherName;
    }
    

    public User() {
    }

    public Log getLog() {
        return this.log;
    }

    
    
    public String getTeacherName() {
        return teacherName;
    }

    
    
    public boolean isTeacher(){
        return this.isTeacher;
    }
    

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public void destroyPassword(String userName){
        this.User=userName;
    }
    
}
