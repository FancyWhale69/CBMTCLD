/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//V2
package maingui;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Me
 */
public class MainGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChoiseScreenFXML.fxml"));
        
        
        Scene scene = new Scene(root);
        
        
        
        primaryStage.setTitle("Login/Signup");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //if users dir does not exist then make it
        File dir= new File(TestSystem.mainDir);
        if(!dir.exists()){
            dir.mkdir();
            
        }
        dir= new File(TestSystem.mainDir+"/"+TestSystem.logdir);
            if(!dir.exists()){
            dir.mkdir();
            
        }
        
        launch(args);
    }
    
}
