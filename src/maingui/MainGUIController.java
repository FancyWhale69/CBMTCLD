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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class MainGUIController implements Initializable {
    //Dyslexia, Dysgraphia, Dyscalculia
    @FXML
    ToggleGroup group = new ToggleGroup();
    
    @FXML
    RadioButton lvl1;
    
    @FXML
    RadioButton lvl2;
    
    @FXML
    RadioButton lvl3;
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    public int getDifLevel(){//return the selected sound level
        if(lvl1.isSelected()){
            return 1;
        }else if(lvl2.isSelected()){
            return 2;
        }else{
            return 3;
        }
    }
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void displayDyslexiaTest(ActionEvent event) throws IOException{//go to the dyslixia game
        DyslexiaController.level=getDifLevel();//pass sound level to the test
        ExplainPageController.game=1;
        //dyslexia
        Parent Root = FXMLLoader.load(getClass().getResource("ExplainPageFXML.fxml"));
        
        
        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Rules");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void displayDysgraphiaTest(ActionEvent event) throws IOException{
        DysgraphiaGUIController.lvl=getDifLevel();
        ExplainPageController.game=2;
        //"Dysgraphia.fxml"
        Parent Root = FXMLLoader.load(getClass().getResource("ExplainPageFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Rules");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void displayDyscalculiaTest(ActionEvent event) throws IOException{
        DyscalculiaGUIController.lvl=getDifLevel();
        ExplainPageController.game=3;
        //DyscalculiaGUIFXML
        Parent Root = FXMLLoader.load(getClass().getResource("ExplainPageFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Rules");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void displayReport(ActionEvent event) throws IOException{//go to report page
        Parent Root = FXMLLoader.load(getClass().getResource("ReportGUIFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Daily Report");
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvl1.setToggleGroup(group);
        lvl2.setToggleGroup(group);
        lvl3.setToggleGroup(group);
        lvl1.setSelected(true);
        
    }    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
}
