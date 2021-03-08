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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import static maingui.DyslexiaController.level;
/**
 * FXML Controller class
 *
 * @author Me
 */
public class DyscalculiaGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label points;
    
    @FXML
    Label miss;
    
    @FXML
    Label round;
    
    @FXML
    Label equation;
   
    @FXML
    Label message;
    
    @FXML
    Button next;
    
    @FXML
    TextField answer;
    
    Media correctSound;
    Media worngSound;
    
    MediaPlayer correct;
    MediaPlayer wrong;
    
    static int lvl;
    
    int pts=0, mis=0;
    
    int rounds=1;
    
    int eq_answer;
    
    long start, end;
    
    int end_point[]={10, 100, 1000};
    int rounds_limit[]={5, 10, 15};
    char ope[]={'-','+'};
    
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
    public void get_Equation(){//make a random equation
        Random rand = new Random();
        
        
        //get the right and left numbers, and the operation
        int right= rand.nextInt(end_point[lvl-1]);
        int left= rand.nextInt(end_point[lvl-1]);
        char op = ope[rand.nextInt(2)];
        
        //save the answer
        eq_answer=(op == '-'? right-left:right+left);
        
        //print the equation
        equation.setText(""+right+op+left);
    }
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void answerButton(ActionEvent event) throws IOException, InterruptedException{//check the answer
        
        if(next.getText().equals("Answer")){//answer mode
            //check textField not empty
        if (answer.getText().equals("")){//answer empty
            wrong.seek(Duration.ZERO);
                    wrong.play();
            message.setTextFill(Color.web(TestSystem.errorColor));
            message.setText("Please Enter An Answer!!");
            return;
        }
        
        //check that answer is a number
        try{
            int user_answer= Integer.parseInt(answer.getText());
            
            //check answer
        if(eq_answer == user_answer){//correct
            correct.seek(Duration.ZERO);
                correct.play();
            points.setText("POINTS: "+ (++pts));//update points
            message.setTextFill(Color.web(TestSystem.messageColor));
            message.setText("CORRECT");
        }else{//wrong
            wrong.seek(Duration.ZERO);
                    wrong.play();
            miss.setText("MISS: "+ (++mis));//update points
            message.setTextFill(Color.web(TestSystem.errorColor));
            message.setText("WRONG. Correct Answer Is: "+eq_answer);
        }
            
        }catch(Exception e){
            wrong.seek(Duration.ZERO);
                    wrong.play();
            message.setTextFill(Color.web(TestSystem.errorColor));
            message.setText("Please Enter A Number!!");
            return;
        }
        
        
        
        
        }else{//change round mode
            if(rounds++==rounds_limit[lvl-1]){//finished rounds
            end=System.currentTimeMillis();//end timer
            TestSystem.makeReports("Dyscalculia", lvl, pts, mis, (int)(end-start));
            
            //pass values to winningcontroller
            WinningFXMLController.scor=pts;
            WinningFXMLController.tim=(int)(end-start);
                
            TestSystem test= new TestSystem();
            test.win(event);
        }
        
        //update rounds
        round.setText("ROUNDS: "+rounds);
        
        //clear text
        message.setText(""); 
        answer.clear();
        
        //update equation
        get_Equation();
        }
        
        
       changeButtonText();
        
        
            
        
        
    }
    
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void changeButtonText(){
        if(next.getText().equals("Answer"))
            next.setText("Ok");
        else
            next.setText("Answer");
    }
     //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start= System.currentTimeMillis();//start timer
        
        correctSound = new Media(new File("sounds\\correct.wav").toURI().toString());
        correct = new MediaPlayer(correctSound);

        worngSound = new Media(new File("sounds\\wrong.wav").toURI().toString());
        wrong = new MediaPlayer(worngSound);
        points.setText("POINTS: "+pts);
        miss.setText("MISS: "+mis);
        round.setText("ROUNDS: "+rounds);
        
        equation.setAlignment(Pos.CENTER);
        message.setAlignment(Pos.CENTER);
        next.setAlignment(Pos.CENTER);
        
        get_Equation();
        start= System.currentTimeMillis();//start timer
    }    
    
}
