/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class PublicPostGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextArea logHistory;
    
    
    @FXML
    TextArea newLog;
    
    @FXML
    Button post;
    
    @FXML
    ComboBox students;
    
    @FXML
    Label message;
    
    static boolean isPrivate;
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    @FXML
    public void back(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{//back to previous screen
        //clear user log
        TestSystem.user.getLog().getLogs().clear();
        
        Parent Root = FXMLLoader.load(getClass().getResource("PostChoiseGUIFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Post Choise");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
        
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    
    @FXML
    public void postButton(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{
        message.setAlignment(Pos.CENTER);
        message.setText("");
        
        
        
        if(isPrivate==true){//private post
            //check that tere are students registered
            if(students.getItems().size()==0){
                message.setTextFill(Color.web(TestSystem.errorColor));
                message.setText("There Are No Students!!!!");
                return;
            }
            
            TestSystem.user.getLog().post((String)students.getValue(), TestSystem.user.getUser(), newLog.getText());
            
        }else{//public
            TestSystem.user.getLog().post(TestSystem.user.getUser(), "None", newLog.getText());
        }
      logHistory.appendText(TestSystem.user.getLog().getLogs().get( TestSystem.user.getLog().getLogs().size()-1 )+"\n\n");
        
        newLog.clear();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void studentChosin(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{
        logHistory.clear();//clear text area
        
        TestSystem.user.getLog().fetchPrivate((String)students.getValue(),TestSystem.user.getUser());//update log
        
        for (String x : TestSystem.user.getLog().getLogs()) {//update textarea
            logHistory.appendText(x+"\n\n");
        }
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //clear textArea
        logHistory.clear();
        newLog.clear();
        
        //user is a teacher
        if(TestSystem.user.isTeacher()==true){
            newLog.setVisible(true);
            post.setVisible(true);
            
            students.setVisible(isPrivate);
            
        }else{//user is astudent
            newLog.setVisible(false);
            post.setVisible(false);
            students.setVisible(false);
        }
        
        logHistory.setEditable(false);
        
        //load
        
        if(isPrivate==false){//public post
            try {
            if(TestSystem.user.isTeacher()==true)//user is a teacher
                
                    TestSystem.user.getLog().fetchPublic(TestSystem.user.getUser());
            
            else
                TestSystem.user.getLog().fetchPublic(TestSystem.user.getTeacherName());
            
            
            
                } catch (IOException ex) {
                Logger.getLogger(PublicPostGUIController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PublicPostGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{//private
            try {
        if(TestSystem.user.isTeacher()==true)//user is a teacher
        {
                    //get list of students names
                        students.setItems(FXCollections.observableArrayList(TestSystem.fetchStudentsNames()));
                        if(students.getItems().size()>0){//teacher has students
                            students.setValue(students.getItems().get(0));
                            TestSystem.user.getLog().fetchPrivate((String)students.getValue(),TestSystem.user.getUser());//fetch log
                        }
                        
            
        }else{
            //get studen priavte log
            TestSystem.user.getLog().fetchPrivate(TestSystem.user.getUser(),TestSystem.user.getTeacherName());
        
        
        }
                
                
                } catch (IOException ex) {
            Logger.getLogger(PublicPostGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PublicPostGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
        //feed data to logHistory
        
        for (String x : TestSystem.user.getLog().getLogs()) {
            logHistory.appendText(x+"\n\n");
        }
        
        
    }    
    
}
