/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class ExplainPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label explain;
    
    @FXML
    Label diffucilty;
    
    @FXML
    Label correctLabel;
    
    @FXML
    Label wrongLabel;
    
    static int game;
    
    MediaPlayer correct;
    MediaPlayer wrong;
    
    Media correctSound;
    Media worngSound;
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void playButton(ActionEvent event) throws IOException{//exit the game into the main menu
        String page="";
        String title="";
        
        switch(game){
            case 1:{//dyslixia
                page="dyslexia.fxml";
                title="Dyslexia";
                break;
            }
            
            case 2:{//disgraphia
                page="Dysgraphia.fxml";
                title="Dysgraphia";
                break;
            }
            
            case 3:{//discalculia
                page="DyscalculiaGUIFXML.fxml";
                title="Dyscalculia";
                break;
            }
        }
        
        Parent Root = FXMLLoader.load(getClass().getResource(page));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle(title);
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void backButton(ActionEvent event) throws IOException{//exit the game into the main menu
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
        String rule = "";
        String diff="";
        switch(game){
            case 1:{//dyslixia
                rule="Score points by clicking on the matching letters.";
                diff="Difficulty setting:\nEasier: 5 rounds, slowest voise speed.\nEasy: 10 rounds, slow voise speed.\nNormal: 15 rounds, normal voise speed.";
                break;
            }
            
            case 2:{//disgraphia
                rule="Score points by clicking on the odd picture in each row. NOTE: Unsolved rows will be outlined in black";
                diff="Difficulty setting:\nEasier: 5 rounds.\nEasy: 10 rounds.\nNormal: 15 rounds.";
                break;
            }
            
            case 3:{//discalculia
                rule="Score points by writing the correct solution to the equation.";
                diff="Difficulty setting:\nEasier: 5 rounds, range from 0 to 10.\nEasy: 10 rounds, range from 0 to 100.\nNormal: 15 rounds, range from 0 to 1000.";
                break;
            }
        }
        System.out.println(game);
        explain.setAlignment(Pos.CENTER);
        explain.setText(rule);
        diffucilty.setText(diff);
        
        //load sounds
        correctSound = new Media(new File("sounds\\correct.wav").toURI().toString());
        correct = new MediaPlayer(correctSound);

        worngSound = new Media(new File("sounds\\wrong.wav").toURI().toString());
        wrong = new MediaPlayer(worngSound);
        
        //bind methods to trigger sound when mouse hovers
        correctLabel.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                correct.seek(Duration.ZERO);
                correct.play();
            }       
        });
        
        wrongLabel.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                wrong.seek(Duration.ZERO);
                wrong.play();
            }       
        });
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
}
