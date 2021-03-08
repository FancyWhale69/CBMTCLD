/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

/**
 *
 * @author Me
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import static maingui.TestSystem.user;

public class Log implements Serializable{
    private ArrayList<String> logs= new ArrayList<String>();
    
//----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    public ArrayList<String> getLogs() {
        return logs;
    }
    
//----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void post(String message) throws IOException{//user already signed in
        String postBy= (TestSystem.user==null? "System":TestSystem.user.getUser());
        logs.add("Posted By: "+postBy+"\n At: "+Calendar.getInstance().getTime()+"\n"+message+"\n"+TestSystem.breakLine);
        
        
        //write data to file
        FileOutputStream ouf = new FileOutputStream(TestSystem.mainDir+"/"+TestSystem.logdir+"/"+TestSystem.user.getUser()+"-"+TestSystem.user.getTeacherName()+"-Log.ser");//file path and name
        ObjectOutputStream oub = new ObjectOutputStream(ouf);
        oub.writeObject(this.logs);
        
        //close stream objects
        oub.close();
        ouf.close();
         
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void post(String name,String teacher, String message) throws IOException{//used in signUp
        String postBy= (TestSystem.user==null? "System":TestSystem.user.getUser());
        logs.add("Posted By: "+postBy+"\n At: "+Calendar.getInstance().getTime()+"\n\n"+message+"\n"+TestSystem.breakLine);
        
        
        //write data to file
        FileOutputStream ouf = new FileOutputStream(TestSystem.mainDir+"/"+TestSystem.logdir+"/"+name+"-"+teacher+"-Log.ser");//file path and name
        ObjectOutputStream oub = new ObjectOutputStream(ouf);
        oub.writeObject(this.logs);
        
        //close stream objects
        oub.close();
        ouf.close();
         
    }
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    public void fetchPublic(String name) throws FileNotFoundException, IOException, ClassNotFoundException{//fetch public logs
        //read data from file
        FileInputStream inf = new FileInputStream(TestSystem.mainDir+"/"+TestSystem.logdir+"/"+name+"-None-Log.ser");//file path and name
        ObjectInputStream inb = new ObjectInputStream(inf);
        this.logs= (ArrayList<String>)inb.readObject();
        
        //close stream objects
        inb.close();
        inf.close();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    //WIP
    public void fetchPrivate(String stuName, String teaName) throws FileNotFoundException, IOException, ClassNotFoundException{//fetch private logs
        //read data from file
        FileInputStream inf = new FileInputStream(TestSystem.mainDir+"/"+TestSystem.logdir+"/"+stuName+"-"+teaName+"-Log.ser");//file path and name
        ObjectInputStream inb = new ObjectInputStream(inf);
        this.logs= (ArrayList<String>)inb.readObject();
        
        //close stream objects
        inb.close();
        inf.close();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
}
