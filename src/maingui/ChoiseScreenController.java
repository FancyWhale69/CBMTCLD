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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Me
 */




public class ChoiseScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void signInPage(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{//take user to signin page
        
        
        
        
        Parent Root = FXMLLoader.load(getClass().getResource("LogInGUIFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("SignIn");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
        
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------

    @FXML
    public void signUpPage(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{//let an already existing user enter the system
        
        
        
        
        
        
        Parent Root = FXMLLoader.load(getClass().getResource("SignUpGUIFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("SignUp");
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
