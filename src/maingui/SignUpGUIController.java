/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class SignUpGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField userName;
    
    @FXML
    TextField teacherName;
    
    @FXML
    TextField password;
    
    @FXML
    Label message;
    
    @FXML
    ToggleGroup toggle = new ToggleGroup();
    
    @FXML
    RadioButton yes;
    
    @FXML
    RadioButton no;
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void signUp() throws IOException, ClassNotFoundException{//create a new user
        message.setAlignment(Pos.CENTER);//make message centered
        
        if(userName.getText().equals("") || password.getText().equals("")){//fields are empty
            
            String erorrmessage= (no.isSelected() && teacherName.getText().equals("")?  
                    "Please Enter UserNama And Password And Teacher Name!!": "Please Enter UserNama And Password!!");
            
            message.setTextFill(Color.web(TestSystem.errorColor));
            message.setText(erorrmessage);
            return;
        }
        
        
        if(!TestSystem.userExist(userName.getText())){//userName not in the system
            
            //get the teacher name
            boolean isTeacher= (yes.isSelected()? true:false);//is user a teacher?
            String teacher="None";
            
            if(no.isSelected() ){//user is a student and teacher exist
                if(TestSystem.checkTeacher(teacherName.getText()))//teacher exist
                teacher=teacherName.getText();
                else{//teacher does not exist
                message.setTextFill(Color.web(TestSystem.errorColor));
                message.setText("Teacher Does Not Exist!!");
                return;
                }
            }
            
            File newUser = new File(TestSystem.mainDir+"/"+userName.getText());
            newUser.mkdir();//create dir for the user
            
            //write the password file
            FileOutputStream ouf=new FileOutputStream(TestSystem.mainDir+"/"+userName.getText()+"/"+TestSystem.passFile);
            ObjectOutputStream oub=new ObjectOutputStream(ouf);
            oub.writeObject(new User(TestSystem.encrypt(password.getText()),  isTeacher, teacher));//save the encrypted password
            oub.close();
            ouf.close();
            
            //write log file
            Log temp= new Log();
            temp.post(userName.getText(),teacher, TestSystem.welcomelog);
            temp=null;
            
            //sucess mssage
            message.setTextFill(Color.web(TestSystem.messageColor));
            message.setText("User Added");
            
            userName.clear();
            password.clear();
            teacherName.clear();
        }else{//username exist in the system
            
            message.setTextFill(Color.web(TestSystem.errorColor));
            message.setText("User Already Exist!!");
        }
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    @FXML
    public void back(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{//let an already existing user enter the system
        
        
        
        
        
        
        Parent Root = FXMLLoader.load(getClass().getResource("ChoiseScreenFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Login/Signup");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
        
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void visable(ActionEvent event){
        teacherName.setVisible(true);
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void invisable(ActionEvent event){
        teacherName.setVisible(false);
        teacherName.clear();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        yes.setToggleGroup(toggle);
        no.setToggleGroup(toggle);
        no.setSelected(true);
        
        
    }    
    
}
