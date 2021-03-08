/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

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
public class TeacherMenuGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void Option(ActionEvent event) throws IOException{//go to report page
       
        //Report
        Parent Root = FXMLLoader.load(getClass().getResource("OptionGUIFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Students Reports");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void postChoise(ActionEvent event) throws IOException{//go to report page
       
        
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
    public void Signout(ActionEvent event) throws IOException{//switch users
        TestSystem.reports.clear();//clear the reports list for new log in
        TestSystem.user=null;//delete current user
        
        Parent Root = FXMLLoader.load(getClass().getResource("LogInGUIFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Log In");
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
