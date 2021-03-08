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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class ReportGUIController implements Initializable {

    
    @FXML
   public TableView<Test> dailyReport;
    
    
    
    
    @FXML
    public TableColumn <Test, String>NameCol;
    
    @FXML
    public TableColumn <Test, String>testNameCol;
    
    @FXML
    public TableColumn <Test, String>levelCol;
    
    @FXML
    public TableColumn <Test, String>ScoreCol;
    
    @FXML
    public TableColumn <Test, String>MissCol;
    
    @FXML
    public TableColumn <Test, String>TimeCol;
    
    @FXML
    public TableColumn <Test, Date>DateCol;
    
    ObservableList<Test> data;
    
    @FXML
    ComboBox students;
    
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    @FXML
    public void back(ActionEvent event) throws IOException{
        if(TestSystem.user.isTeacher())
        TestSystem.reports.clear();
        
        String pageAddress= (TestSystem.user.isTeacher()==true? "OptionGUIFXML.fxml":"MainGUIFXML.fxml");//go back to teacher screen or student screen
        
        Parent Root = FXMLLoader.load(getClass().getResource(pageAddress));
        

        Scene scene = new Scene(Root);

        Stage AddPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        AddPageStage.setTitle("Main Menu");
        AddPageStage.setScene(scene);
        AddPageStage.setResizable(false);
        AddPageStage.show();
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @FXML
    public void showStudentReports(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException{
        TestSystem.userReports(new File(TestSystem.mainDir+"/"+students.getValue()));
        
        data = FXCollections.observableArrayList(TestSystem.reports);
        
        
        dailyReport.setItems(data);//feed the object array to tableview
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        if(TestSystem.user.isTeacher()){//user is a teacher
            students.setVisible(true);
            //fetch students
        students.setItems(FXCollections.observableArrayList(TestSystem.fetchStudentsNames()));
                        if(students.getItems().size()>0){//teacher has students
                            students.setValue(students.getItems().get(0));
                try {
                    TestSystem.userReports(new File(TestSystem.mainDir+"/"+students.getItems().get(0)));
                } catch (IOException ex) {
                    Logger.getLogger(ReportGUIController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReportGUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
                            data = FXCollections.observableArrayList(TestSystem.reports);
                        }
        }
        else{//user is astudent
            students.setVisible(false);
            data = FXCollections.observableArrayList(TestSystem.reports);
        }
        
        
        //bind each column with a variable from the test class
        NameCol.setCellValueFactory(new PropertyValueFactory<Test, String>("user"));
        testNameCol.setCellValueFactory(new PropertyValueFactory<Test, String>("test"));
        levelCol.setCellValueFactory(new PropertyValueFactory<Test, String>("level"));
        
        ScoreCol.setCellValueFactory(new PropertyValueFactory<Test, String>("score"));
        MissCol.setCellValueFactory(new PropertyValueFactory<Test, String>("miss"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<Test, String>("duration"));
        DateCol.setCellValueFactory(new PropertyValueFactory<Test, Date>("examDate"));
        
        
         
        dailyReport.setItems(data);//feed the object array to tableview
        
        
        
    }
    //----------------------------------------------------------------------------------//-----------------------------------------------------------------
    
    
    
    
}
