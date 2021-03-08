/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maingui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class ChartGUIFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    @FXML
    LineChart<String, Number> chart;
    
    @FXML
    ComboBox students;
    
    @FXML
    ComboBox atrributes;
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public int getAttre(Test test){
        if(atrributes.getValue().equals("Score"))
            return test.getScore();
        else if(atrributes.getValue().equals("Miss"))
            return test.getMiss();
        else
            return getSeconds(test.getDuration());
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public int getSeconds(String time){//String format min:sec
        int minToSec= Integer.parseInt(time.split(":")[0])*60;
        int sec= Integer.parseInt(time.split(":")[1]);
        
        return minToSec+sec;
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public String getDate(String Date){//String format dayname mon day
        String arr[]= Date.split(" ");
        
        return ""+arr[0]+" "+arr[1]+" "+arr[2];
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void loadChart() throws IOException, FileNotFoundException, ClassNotFoundException{
        if(students.getValue()==null )
            return;
        
        chart.getData().clear();
        if(students.getItems().size()>0){//Sudents are avilable
            //load students report
            TestSystem.userReports(new File(TestSystem.mainDir+"/"+students.getValue()));
            
            XYChart.Series<String, Number> Dyslexia = new XYChart.Series<String, Number>();
            XYChart.Series<String, Number> Dysgraphia = new XYChart.Series<String, Number>();
            XYChart.Series<String, Number> Dyscalculia = new XYChart.Series<String, Number>();
            
            //load data for chart
            for(Test x : TestSystem.reports){
                if(x.getTest().equals("Dyslexia"))
                    Dyslexia.getData().add(new XYChart.Data<String, Number>(getDate(x.getExamDate().toString()), getAttre(x)));
                else if(x.getTest().equals("Dysgraphia"))
                    Dysgraphia.getData().add(new XYChart.Data<String, Number>(getDate(x.getExamDate().toString()), getAttre(x)));
                else
                    Dyscalculia.getData().add(new XYChart.Data<String, Number>(getDate(x.getExamDate().toString()), getAttre(x)));
            }
            
            //set legend names
            Dyslexia.setName("Dyslexia");
            Dysgraphia.setName("Dysgraphia");
            Dyscalculia.setName("Dyscalculia");
            
            
            //load data into chart
            chart.getData().add(Dyslexia);
            chart.getData().add(Dysgraphia);
            chart.getData().add(Dyscalculia);
            
            
            
            
           
        }
    }
        
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    public void Back(ActionEvent event) throws IOException{//go to report page
       
        //Report
        Parent Root = FXMLLoader.load(getClass().getResource("OptionGUIFXML.fxml"));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Options");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //fill the attribute combo box
        atrributes.setItems( FXCollections.observableArrayList( "Score", "Miss", "Duration" ) );
        atrributes.setValue(atrributes.getItems().get(0));
        
        //fill the student combobox
        students.setItems(FXCollections.observableArrayList(TestSystem.fetchStudentsNames()));
        
     
    }    
    
}
