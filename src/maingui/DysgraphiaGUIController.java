package maingui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


public class DysgraphiaGUIController implements Initializable {

    Media correctSound;
    Media worngSound;
    
    MediaPlayer correct;
    MediaPlayer wrong;
    
    long start, end;
    
    @FXML private Label scoreLabel;
    @FXML private Label missLabel;
    @FXML private Label roundLabel;
    
    @FXML
    HBox num_1;
    
    @FXML
    HBox num_2;
    
    @FXML
    HBox num_3;
    
    @FXML
    HBox num_4;
    
    @FXML
    private ImageView pic1_1, pic1_2, pic1_3, pic1_4, pic1_5;
    @FXML
    private ImageView pic2_1, pic2_2, pic2_3, pic2_4, pic2_5;
    @FXML
    private ImageView pic3_1, pic3_2, pic3_3, pic3_4, pic3_5;
    @FXML
    private ImageView pic4_1, pic4_2, pic4_3, pic4_4, pic4_5;

    private int[] correctChoice = new int[4]; // this array will contain all the correct answers of each row
    private int score = 0;
    private int miss = 0;
    private int buttons = 0;
    private boolean[] finishedRow = new boolean[4];
    
    String style= "-fx-border-color:#fa26a0";
    
    int rounds[]={5, 10, 15};
    int round=1;

    static int lvl;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        correctSound = new Media(new File("sounds\\correct.wav").toURI().toString());
        correct = new MediaPlayer(correctSound);

        worngSound = new Media(new File("sounds\\wrong.wav").toURI().toString());
        wrong = new MediaPlayer(worngSound);
        
        roundLabel.setText("Round: "+round);
         startGame();
         start = System.currentTimeMillis();
    }
    
    public void startGame(){
        //reset finishedrow
        for (int i = 0; i < finishedRow.length; i++) {
            finishedRow[i]=false;
        }
        
        // first, we have to but all the buttons on the array to help us to do operations for them
        ImageView[][] arrayOfImages =
                {{pic1_1, pic1_2, pic1_3, pic1_4, pic1_5}
                        , {pic2_1, pic2_2, pic2_3, pic2_4, pic2_5}
                        , {pic3_1, pic3_2, pic3_3, pic3_4, pic3_5}
                        , {pic4_1, pic4_2, pic4_3, pic4_4, pic4_5}};

        File[] typesDir = new File("DysgraphiaPictures").listFiles(); // this array gonna contain the folders of each type
        int[] randomSequence = generateRandomSequence(typesDir.length, 4);
        int[] randomSequenceForImages = null;

        //Now, in each button we should add a random picture from the random type and we make one of them from the next random type!!!
        for (int i = 0; i < arrayOfImages.length; i++) {
            int theOddIndex = (int)(Math.random() * 5); // this is the index of the odd picture
            correctChoice[i] = theOddIndex;
            for (int j = 0; j < arrayOfImages[i].length; j++) {
                if (j != theOddIndex) {
                    File[] images = typesDir[randomSequence[i]].listFiles();
                    if (randomSequenceForImages == null) {
                        randomSequenceForImages = generateRandomSequence(images.length, 5);
                    }
                    String imagePath = Paths.get(images[randomSequenceForImages[j]].getPath()).toUri().toString();
                    arrayOfImages[i][j].setImage(new Image(imagePath));
                } else {
                    int nextType;
                    do {
                        nextType = (int)(Math.random() * typesDir.length);
                    } while (nextType == i);
                    File[] images = typesDir[randomSequence[nextType]].listFiles(); // we take a picture of the next type
                    int randomIndex = (int) (Math.random() * images.length);
                    String imagePath = Paths.get(images[randomIndex].getPath()).toUri().toString();
                    arrayOfImages[i][j].setImage(new Image(imagePath));
                }
            }
            randomSequenceForImages = null; // we reset the value with null
        }
        num_1.setStyle("DysgraphiaCSS.css");
        num_2.setStyle("DysgraphiaCSS.css");
        num_3.setStyle("DysgraphiaCSS.css");
        num_4.setStyle("DysgraphiaCSS.css");
    }

    /**
     * This method will generate array of integers generated randomly where all the numbers are less than the size and unique
     *
     * @param max the maximum value in the array
     * @param arraySize the size of the returned Value
     */
    private int[] generateRandomSequence(int max, int arraySize) {
        int[] randomSequence = new int[arraySize]; // we need only for numbers
        // we initialize all the values with -1
        for (int i = 0; i < arraySize; i++) {
            randomSequence[i] = -1;
        }

        for (int i = 0; i < arraySize; i++) {
            int randomNumber;
            do {
                randomNumber = (int)(Math.random()*max); // we will generate a random number until we get a unique number
            } while (!randomIsUnique(randomSequence, randomNumber));
            randomSequence[i] = randomNumber;
        }
        return randomSequence;
    }

    private boolean randomIsUnique (int[] randomSequence, int randomNumber) {
        for (int i = 0; i < randomSequence.length; i++) {
            if (randomSequence[i] == randomNumber) {
                // then the number is not unique
                return false;
            }else if (randomSequence[i] == -1) {
                return true;
            }
        }
        return true;
    }

    // now, we should implement row 1 methods

    @FXML
    public void picBehaviour1_1 (MouseEvent event) throws IOException {
        if (finishedRow[0]) {
            return;
        }
        if (correctChoice[0] == 0) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_1.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[0] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
            wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour1_2 (MouseEvent event) throws IOException {
        if (finishedRow[0]) {
            return;
        }
        if (correctChoice[0] == 1) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_1.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[0] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour1_3 (MouseEvent event) throws IOException {
        if (finishedRow[0]) {
            return;
        }
        if (correctChoice[0] == 2) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_1.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[0] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour1_4 (MouseEvent event) throws IOException {
        if (finishedRow[0]) {
            return;
        }
        if (correctChoice[0] == 3) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_1.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[0] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour1_5 (MouseEvent event) throws IOException {
        if (finishedRow[0]) {
            return;
        }
        if (correctChoice[0] == 4) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_1.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[0] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    // now, we should implement row 2 methods

    @FXML
    public void picBehaviour2_1 (MouseEvent event) throws IOException {
        if (finishedRow[1]) {
            return;
        }
        if (correctChoice[1] == 0) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_2.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[1] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour2_2 (MouseEvent event) throws IOException {
        if (finishedRow[1]) {
            return;
        }
        if (correctChoice[1] == 1) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_2.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[1] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour2_3 (MouseEvent event) throws IOException {
        if (finishedRow[1]) {
            return;
        }
        if (correctChoice[1] == 2) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_2.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[1] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour2_4 (MouseEvent event) throws IOException {
        if (finishedRow[1]) {
            return;
        }
        if (correctChoice[1] == 3) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_2.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[1] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour2_5 (MouseEvent event) throws IOException {
        if (finishedRow[1]) {
            return;
        }
        if (correctChoice[1] == 4) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_2.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[1] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    // now, we should implement row 3 methods

    @FXML
    public void picBehaviour3_1 (MouseEvent event) throws IOException {
        if (finishedRow[2]) {
            return;
        }
        if (correctChoice[2] == 0) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_3.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[2] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour3_2 (MouseEvent event) throws IOException {
        if (finishedRow[2]) {
            return;
        }
        if (correctChoice[2] == 1) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_3.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[2] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour3_3 (MouseEvent event) throws IOException {
        if (finishedRow[2]) {
            return;
        }
        if (correctChoice[2] == 2) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_3.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[2] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour3_4 (MouseEvent event) throws IOException {
        if (finishedRow[2]) {
            return;
        }
        if (correctChoice[2] == 3) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_3.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[2] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour3_5 (MouseEvent event) throws IOException {
        if (finishedRow[2]) {
            return;
        }
        if (correctChoice[2] == 4) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_3.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[2] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    // now, we should implement row 4 methods

    @FXML
    public void picBehaviour4_1 (MouseEvent event) throws IOException {
        if (finishedRow[3]) {
            return;
        }
        if (correctChoice[3] == 0) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_4.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[3] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour4_2 (MouseEvent event) throws IOException {
        if (finishedRow[3]) {
            return;
        }
        if (correctChoice[3] == 1) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_4.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[3] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour4_3 (MouseEvent event) throws IOException {
        if (finishedRow[3]) {
            return;
        }
        if (correctChoice[3] == 2) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_4.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[3] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour4_4 (MouseEvent event) throws IOException {
        if (finishedRow[3]) {
            return;
        }
        if (correctChoice[3] == 3) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_4.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[3] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
            //finishedRow[3] = true;
        }
        finishedTest (event);
    }

    @FXML
    public void picBehaviour4_5 (MouseEvent event) throws IOException {
        if (finishedRow[3]) {
            return;
        }
        if (correctChoice[3] == 4) {
            correct.seek(Duration.ZERO);
                correct.play();
                num_4.setStyle(style);
            score++;
            buttons++;
            scoreLabel.setText("Points: " + score);
            finishedRow[3] = true;
        } else {
            miss++;
            missLabel.setText("Miss: " + miss);
            wrong.seek(Duration.ZERO);
                    wrong.play();
        }
        finishedTest (event);
    }

    @FXML
    public void back (ActionEvent event) throws IOException {
        Parent Root = FXMLLoader.load(getClass().getResource("MainGUIFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Main Menu");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }

    private void finishedTest (MouseEvent event) throws IOException {
        // this method will check the win condition
        if(buttons==4){//all correct answers are found
            if(round++ == rounds[lvl-1]){
                end=System.currentTimeMillis();
            TestSystem.makeReports("Dysgraphia", lvl, score, miss, (int)(end-start));
            TestSystem test = new TestSystem();
            WinningFXMLController.scor=score;
            WinningFXMLController.tim=(int)(end-start);
            test.win(event);
            }else{
                buttons=0;
                roundLabel.setText("Round: "+round);
                startGame();
            }
        }
        
        
        
        
    }
}
