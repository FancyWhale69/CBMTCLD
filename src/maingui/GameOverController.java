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
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class GameOverController implements Initializable {
//----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void backButton(ActionEvent event) throws IOException{
        Parent Root = FXMLLoader.load(getClass().getResource("MainGUIFXML.fxml"));
        

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
