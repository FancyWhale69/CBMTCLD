/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DyslexiaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    String letter[];
    
    int score = 0, miss=0, buttocount=0;//user score, user mistakes, number of correctly selected pair
    
    boolean Clicked = false;//check if a button was clicked

    
    //sound variables
    MediaPlayer a;
    MediaPlayer b;
    MediaPlayer c;
    MediaPlayer d;
    
    
    MediaPlayer correct;
    MediaPlayer wrong;

    Media aSound;
    Media bSound;
    Media cSound;
    Media dSound;
    
    
    Media correctSound;
    Media worngSound;

    
    static int level;//get sound level from mainguifxml

    long start=0, end=0;//timer
    
    int round=1;
    
    int rounds[]={5, 10, 15};
    
    @FXML
    public Label labelScore;
    
    @FXML
    public Label labelMiss;
    
    @FXML
    public Label labelRound;

    @FXML
    public Button temp;

    @FXML
    public Button top1;
    
    @FXML
    public Button top2;
    
    @FXML
    public Button top3;

    @FXML
    public Button middle1;
    
    @FXML
    public Button middle2;

    @FXML
    public Button bottom1;
    
    @FXML
    public Button bottom2;
    
    @FXML
    public Button bottom3;

    ArrayList<Button> buttons = new ArrayList() ;//hold all the buttons
    ArrayList<String> letters = new ArrayList() ;//hold all the letters
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public String [] fillButtons(){//choose a random pair of buttons and assign a letter to them
        Random random = new Random();
        
        
        
        //array to hold chosin letters to load their sound files
        String lett []= new String[4];
        
        int count=0;//counter for the array
        
        
        buttons.clear();
        letters.clear();
        //fill the arrayLists
        buttonArray();
        letterArray();
        
        
        
        int buttonIndex1;//hold index of the first button
        int buttonIndex2;//hold index of the second button
        int letterIndex;//hold index of the letter
        
        
        Button temp1=null;
        Button temp2=null;
        
        while(buttons.size() > 0){//continue until all buttons are filled
            
        
            
            //choose the first button
        buttonIndex1=random.nextInt(buttons.size());
        
        //choose the second button
        buttonIndex2 = random.nextInt(buttons.size());
        
        while(buttonIndex1 == buttonIndex2){//if button is chosen twise
            buttonIndex2 = random.nextInt(buttons.size());
        }
        
        //choose a letter
         letterIndex=random.nextInt(letters.size());
        
            
         
        //bind the buttons with the letter
        temp1= buttons.get(buttonIndex1);
        temp2= buttons.get(buttonIndex2);
        
        temp1.setText(letters.get(letterIndex));
        temp2.setText(letters.get(letterIndex));
        
        //make buttons visable
        temp1.setVisible(true);
        temp2.setVisible(true);
        
        //remove buttons from the arraylist
        buttons.remove(temp1);
        buttons.remove(temp2);
        
        //destroy the temp pointers
        temp1=null;
        temp2=null;
        
        //megrate letter from arraylist to String array 
        lett[count++]= letters.get(letterIndex);
        
        //remove letter from arralist
        letters.remove(letterIndex);
        
        
        }
        
        return lett;
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void buttonArray(){//fill the buttons arrayList
        buttons.add(top1);
        buttons.add(top2);
        buttons.add(top3);
        
        buttons.add(middle1);
        buttons.add(middle2);
        
        buttons.add(bottom1);
        buttons.add(bottom2);
        buttons.add(bottom3);
        
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void letterArray(){//fill the letters arrayList
        letters.add("a");
        letters.add("b");
        letters.add("c");
        letters.add("d");
        letters.add("e");
        letters.add("f");
        letters.add("g");
        letters.add("h");
        letters.add("i");
        letters.add("j");
        letters.add("k");
        letters.add("l");
        letters.add("m");
        letters.add("n");
        letters.add("o");
        letters.add("p");
        letters.add("q");
        letters.add("r");
        letters.add("s");
        letters.add("t");
        letters.add("u");
        letters.add("v");
        letters.add("w");
        letters.add("x");
        letters.add("y");
        letters.add("z");
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

    @FXML
    private void handleButtonActions(ActionEvent event) throws IOException {//game logic
        //get the source
        Button source= (Button) event.getSource();
         
        
        
        //PlayTheLetterSound(source.getText());//play the clicked letter sound
        
        if (Clicked) {//second button clicked
              temp.setStyle("BackButtonStyle.css");//reset button style to default
              
            //both buttons have the same letter
            if (Objects.equals(temp.getText(), source.getText())) {

                //same button clicked twice
                if (temp == source) {
                    wrong.seek(Duration.ZERO);
                    wrong.play();
                    Clicked = false;
                    return;
                }
                
                //diffrenet button
                correct.seek(Duration.ZERO);
                correct.play();
                score += 1;
                buttocount++;
                 labelScore.setText(score+"");
                source.setVisible(false);
                temp.setVisible(false);
                
                
                
            } else {//buttons have diffrenet letters
                wrong.seek(Duration.ZERO);
                wrong.play();
                miss++;
                labelMiss.setText(miss+"");

            }
            Clicked = false;//reset

        } else {//first button to be clicked
            Clicked = true;//rise flag
            source = (Button) event.getSource();//store button
            temp = source;
            temp.setStyle("-fx-background-color: #000000;\n" +
                          "    -fx-text-fill: #fa26a0;");//keep highlight
        }
        
        
        
        if(buttocount==4){//finished a round
            
            if(round++==rounds[level-1]){//finished all rounds
               end=System.currentTimeMillis();//end timer
            TestSystem.makeReports("Dyslexia", level, score, miss, (int)(end-start));
            
            //pass values to winningcontroller
            WinningFXMLController.scor=score;
            WinningFXMLController.tim=(int)(end-start);
            
            TestSystem ts = new TestSystem();
            ts.win(event);//change scene 
            
            }else{
                labelRound.setText("Round: "+round);
                buttocount=0;
                startGame();
            }
            
            
        }
    }

    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void startGame(){
        //set buttons and get the letters for which you need to load the sound files
        letter = fillButtons();
        

        //load sounds
        correctSound = new Media(new File("sounds\\correct.wav").toURI().toString());
        correct = new MediaPlayer(correctSound);

        worngSound = new Media(new File("sounds\\wrong.wav").toURI().toString());
        wrong = new MediaPlayer(worngSound);

        int eq= (-level+4);//get the correct sound level
        
        aSound = new Media(new File("sounds\\"+letter[0]+  eq + ".mp3").toURI().toString());
        a = new MediaPlayer(aSound);

        bSound = new Media(new File("sounds\\"+letter[1]+ eq + ".mp3").toURI().toString());
        b = new MediaPlayer(bSound);

        cSound = new Media(new File("sounds\\"+letter[2]+ eq + ".mp3").toURI().toString());
        c = new MediaPlayer(cSound);

        dSound = new Media(new File("sounds\\"+letter[3]+ eq + ".mp3").toURI().toString());
        d = new MediaPlayer(dSound);
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         start = System.currentTimeMillis();//start timer
        
        //set labels
        labelScore.setText(score+"");
        labelMiss.setText(miss+"");
        labelRound.setText("Round: "+round);
        
        startGame();
        

        

        
        //set mouse event, play sound when mouse hover over button
        top1.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                PlayTheLetterSound((Button) event.getSource());
            }       
        });
        top2.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                PlayTheLetterSound((Button) event.getSource());
            }       
        });
        top3.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                PlayTheLetterSound((Button) event.getSource());
            }       
        });
        
        middle1.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                PlayTheLetterSound((Button) event.getSource());
            }       
        });
        middle2.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                PlayTheLetterSound((Button) event.getSource());
            }       
        });
        
        bottom1.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                PlayTheLetterSound((Button) event.getSource());
            }       
        });
        bottom2.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                PlayTheLetterSound((Button) event.getSource());
            }       
        });
        bottom3.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                PlayTheLetterSound((Button) event.getSource());
            }       
        });
      
    }
//----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void PlayTheLetterSound(Button btn) {//play the sound of the letter which bounded to the button
        if(btn.getText().equals(letter[0])){
            a.seek(Duration.ZERO);
            a.play();
        }else if(btn.getText().equals(letter[1])){
            b.seek(Duration.ZERO);
            b.play();
        }else if(btn.getText().equals(letter[2])){
            c.seek(Duration.ZERO);
            c.play();
        }else{
            d.seek(Duration.ZERO);
            d.play();
        }
        
        
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------

}
