package maingui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class LogInGUIController implements Initializable {

    @FXML
    TextField userName;
    
    @FXML
    TextField password;
    
    @FXML
    Label message;
    
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void signIn(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{//let an already existing user enter the system
        message.setAlignment(Pos.CENTER);//make message centered
        
        if(userName.getText().equals("") || password.getText().equals("")){//fields are empty
            message.setTextFill(Color.web(TestSystem.errorColor));
            message.setText("Please Enter UserNama And Password!!");
            return;
        }
        
        
        if(TestSystem.checkUser(userName.getText(), password.getText())){//user name and password are correct
            
            
        String pageAddress= (TestSystem.user.isTeacher()? "TeacherMenuGUIFXML.fxml": "MainGUIFXML.fxml");//is user a teacher
        
        userName.clear();
        password.clear();
        
            
        
        Parent Root = FXMLLoader.load(getClass().getResource(pageAddress));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Main Menu");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
        }else{//user or password are wromg
            message.setTextFill(Color.web(TestSystem.errorColor));
            message.setText("UserName Or Password Is Wrong!!");
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
