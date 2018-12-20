/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;

import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javax.security.auth.Subject;

public class FXMLDocumentController
{
     ObservableList<String> senoritylist=FXCollections.observableArrayList("0","1","2","3","4");
    ObservableList<String> availabilitylist=FXCollections.observableArrayList("Available","Unavailable");
    ObservableList<String> subjectlist=FXCollections.observableArrayList();
    
    @FXML
    private Pane paneTeacher;
    
    @FXML
    private Button insert,update,delete;
    
    @FXML
    private TextField text_teachername,text_id;
    
    @FXML
    private ChoiceBox choice_senority,choice_available, choice_subject;
    
    @FXML
    private TableView<Teacher> teacherTable;
    
    @FXML 
    private TableColumn<Teacher,String> table_id;
    
    @FXML 
    private TableColumn<Teacher,String> table_name;
    
    @FXML 
    private TableColumn<Teacher,String> table_senority;
    
    @FXML 
    private TableColumn<Teacher,String> table_availability;
    
    @FXML 
    private TableColumn<Teacher,String> table_subject;
    int Id;
    @FXML
    private void pop_table() throws SQLException {
        ObservableList<Teacher> data = FXCollections.observableArrayList();
        table_id.setCellValueFactory(new PropertyValueFactory<Teacher, String>("table_id"));
        table_name.setCellValueFactory(new PropertyValueFactory<Teacher, String>("table_name"));
        table_senority.setCellValueFactory(new PropertyValueFactory<Teacher, String>("table_senority"));
        table_availability.setCellValueFactory(new PropertyValueFactory<Teacher, String>("table_avaiability"));
        table_subject.setCellValueFactory(new PropertyValueFactory<Teacher, String>("table_subject"));
         Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","1234"
                );
         Statement stm;
    stm = conn.createStatement();
    String sql = "Select * From Schema1.teacher";
    ResultSet rst;
    
    rst = stm.executeQuery(sql);
    
    while (rst.next()) {
        Teacher customer = new Teacher(rst.getInt("Teacher_id"), rst.getString("Name"), rst.getString("Seniority"), rst.getString("Availability"),rst.getString("Subject"));
        data.add(customer);
    }
    
         

        //FINALLY ADDED TO TableView
        teacherTable.setItems(data);
    }
    
    @FXML
    public void insert () throws SQLException
    {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull","root","1234"
                );
        Statement smt = conn.createStatement();

        
        String name = text_teachername.getText();
        String senority = (String)choice_senority.getValue();
        String availability = (String)choice_available.getValue();
        String subject = (String)choice_subject.getValue();

        String sql =" insert into schema1.teacher values(0,'"+name+"','"+senority+"','"+availability+"','"+subject+"')";
        smt.executeUpdate(sql);
        pop_table();
    }
    
    @FXML
    public void updates() throws SQLException{
       Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
  
    String name=text_teachername.getText();
    String senority=(String)choice_senority.getValue();
    String available=(String) choice_available.getValue();
    String subject=(String)choice_subject.getValue();
    
    
    stm = conn.createStatement();
    String sql = "UPDATE Schema1.teacher SET Name = '"+name+"', Seniority= '"+senority+"', Availability = '"+available+"',Subject = '"+subject+"' Where Teacher_id='"+Id+"';";    
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    pop_table();
   }
    
    
    
   @FXML
   public void deletes() throws SQLException{
   Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
   
   stm = conn.createStatement();
    String sql = "Delete From Schema1.teacher Where Teacher_id='"+Id+"';";    
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    pop_table();
   }
   
   @FXML
   public void selecteds(){
       Teacher person = teacherTable.getSelectionModel().getSelectedItem();
        Id=person.getTable_id();
        text_teachername.setText(person.getTable_name());
        choice_senority.getSelectionModel().select(person.getTable_senority());
        choice_available.getSelectionModel().select(person.getTable_avaiability());
        choice_subject.getSelectionModel().select(person.getTable_subject());
   }
   
    @FXML
    public void initialize() throws SQLException {
        // TODO
       paneTeacher.getStyleClass().add("bf-color-pane");
        //text_teachername.getStyleClass().add("text-field");
        insert.getStyleClass().add("butt");
        update.getStyleClass().add("butt");
        delete.getStyleClass().add("butt");
        choice_senority.setItems(senoritylist);
        choice_available.setItems(availabilitylist);
         
         Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
       stm = conn.createStatement();
    String sql = "Select * from Schema1.subject;";
   ResultSet rst;
    rst = stm.executeQuery(sql);
    String[] a = new String[50];
    int temp=0;
     while (rst.next()) {
         a[temp]=rst.getString("Name");
         a[temp]+=" , ";
            a[temp]  = a[temp].concat(rst.getString("Branch"));
         temp++;
       }
      ObservableList<String> SubList=FXCollections.observableArrayList();
      for(int i=0;i<temp;i++){  
      SubList.add(a[i]);
      }
    
    
           choice_subject.setItems(SubList);
        pop_table();
    }    
    
    
    
}
