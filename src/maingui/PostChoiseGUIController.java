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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class PostChoiseGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void PrivatePost(ActionEvent event) throws IOException{//go to report page
        PublicPostGUIController.isPrivate=true;
        Parent Root = FXMLLoader.load(getClass().getResource("PublicPostGUIFXML.fxml"));//temp
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Private Post");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void PublicPost(ActionEvent event) throws IOException{//go to report page
        PublicPostGUIController.isPrivate=false;
        Parent Root = FXMLLoader.load(getClass().getResource("PublicPostGUIFXML.fxml"));//temp
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Public Post");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
     //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    @FXML
    public void back(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{//let an already existing user enter the system
        String pageAddress= (TestSystem.user.isTeacher()==true? "TeacherMenuGUIFXML.fxml":"MainGUIFXML.fxml");//go back to teacher screen or student screen
        
        Parent Root = FXMLLoader.load(getClass().getResource(pageAddress));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Main Menu");
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
