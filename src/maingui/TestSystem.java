/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author Me
 */
public class TestSystem {
    static ArrayList<Test> reports = new ArrayList();
    static String mainDir = "users";
    static String passFile = "password.ser";
    static String logdir = "Logs";
    static String welcomelog = "Welcome to the CBMTCLD";
    static String breakLine="------------------";
    static String errorColor = "#ff0509";
    static String messageColor = "#14bf01";
    static User user;
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static void userReports(File user) throws FileNotFoundException, IOException, ClassNotFoundException{//load the user reports
        //clear reports
        reports.clear();
        
        File files[]= user.listFiles();
        FileInputStream inf=null;
        ObjectInputStream obf=null;
        
        //load reports for the user
        for (File x : files) {
            
            if(!x.getName().equals(passFile)){//not the password file
                inf=new FileInputStream(x.toString());
                obf = new ObjectInputStream(inf);
                try{//if invalid file is read ignore it
                    reports.add((Test)obf.readObject());
                }catch(Exception e){
                    
                }
                
            }
        }
        if(inf != null){
            obf.close();
            inf.close();
        }
        
        
        
    }
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static boolean checkUser(String userName, String password) throws FileNotFoundException, IOException, ClassNotFoundException{//make sure the username ans password are correct
        if(userExist(userName)){//search for userName
            File userDir = new File(mainDir+"/"+userName);//open user directory
            
            
            FileInputStream file = new FileInputStream(mainDir+"/"+userName+"/"+passFile);
            ObjectInputStream obj = new ObjectInputStream(file);
            user = (User)obj.readObject();
            
            if(user.getUser().equals( encrypt(password) )){//password is stored in user field because of the counstractor in the user class
                user.destroyPassword(userName);//erase password from object and replaced with the username
                
                userReports(userDir);//load user reports
                obj.close();
                file.close();
                
                return true;//user and password correct
            }
        }
        
        
        return false;//user or password are wrong
    }
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static String userPath(String userName) throws IOException{//search if the user exist
        File dir = new File(mainDir);//get all dir in users
        
        
        String contents []= dir.list();
        
        for (String x : contents) {
            if(x.split("-")[0].equals(userName)){//userName found
                return x;
            }
        }
        return "None";
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static boolean userExist(String userName) throws IOException{//search if the user exist
        File dir = new File(mainDir);//get all dir in users
        if(!dir.exists()){//if users dir not found then create one
                dir.mkdir();
                return false;
            }
        
        String contents []= dir.list();
        
        for (String x : contents) {
            if(x.split("-")[0].equals(userName)){//userName found
                return true;
            }
        }
        
        return false;
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static boolean checkTeacher(String teacherName) throws IOException, ClassNotFoundException{//search if the user is a teacher
        if(userExist(teacherName)){//search for userName
                       
            
            FileInputStream file = new FileInputStream(mainDir+"/"+teacherName+"/"+passFile);
            ObjectInputStream obj = new ObjectInputStream(file);
            User tempuser = (User)obj.readObject();
            obj.close();
            file.close();
            
            return tempuser.isTeacher();
            
            
            
            
        }
        
        
        return false;//user not a teacher
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static String encrypt(String password){//encrypt the password string
        String secret="";
        int y=0;
        for (int i = 0; i < password.length(); i++) {
            y= (password.charAt(i)+10)%126;
            secret+=(char)y;        
        }
        
        
        return secret;
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static String decrypt(String password){//return password to orignal state
        String secret="";
        int y=0;
        for (int i = 0; i < password.length(); i++) {
            y= (password.charAt(i)-10<0)? (password.charAt(i)-10)+126 : password.charAt(i)-10;
            secret+=(char)y;        
        }
        
        
        return secret;
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void gameOver(ActionEvent event) throws IOException{//go to game over screen
        Parent Root = FXMLLoader.load(getClass().getResource("GameOverFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("GameOver");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void win(ActionEvent event) throws IOException{//go to the win screen
        Parent Root = FXMLLoader.load(getClass().getResource("WinningFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Winning");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static void makeReports(String type, int level, int score,int miss, int duration) throws FileNotFoundException, IOException{//make a test object and save it in the user dir
        
        
        Test report = new Test( type, TestSystem.levelName(level), score, miss, returnTimeMinute(duration), Calendar.getInstance().getTime());//make object
        
        reports.add(report);//add report to arrayList
        
        //write data to file
        FileOutputStream ouf = new FileOutputStream(TestSystem.mainDir+"/"+user.getUser()+"/"+type+"_"+System.currentTimeMillis()+".ser");//file path and name
        ObjectOutputStream oub = new ObjectOutputStream(ouf);
        oub.writeObject(report);
        
        //close stream objects
        oub.close();
        ouf.close();
    }
    
    
     //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static String levelName(int lvl){//take level value and output a string
        if(lvl==0){
            return "None";
        }
        else if(lvl==1){
            return "Easier";
            
        }else if(lvl==2){
            return "Easy";
        }else{
            return "Normal";
        }
    } 
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static String returnTimeMinute(int dur){//convert time to a string (e.g: input=110, output: 1:50)
        int min=(dur/1000)/60, sec=(dur/1000);
        
        
        return min+":"+sec;
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void win(MouseEvent event) throws IOException{//go to the win screen
        Parent Root = FXMLLoader.load(getClass().getResource("WinningFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Winning");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void gameOver(MouseEvent event) throws IOException{//go to game over screen
        Parent Root = FXMLLoader.load(getClass().getResource("GameOverFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("GameOver");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    static ArrayList<String> fetchStudentsNames(){//fetch the names of all students under this teacher
        ArrayList<String> studentsNames= new ArrayList();
        File dir = new File(mainDir+"/"+logdir);
        
        for (File x: dir.listFiles()) {//go throgh all files in logs
            if(x.getName().split("-")[1].equals(user.getUser()))
                studentsNames.add(x.getName().split("-")[0]);
        }
        
        
        
        return  studentsNames;
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
}
