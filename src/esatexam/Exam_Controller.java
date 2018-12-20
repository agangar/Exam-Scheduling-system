/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Harsh
 */
public class Exam_Controller {
     ObservableList<String> Semlist=FXCollections.observableArrayList("I","II","III","IV","V","VI","VII","VIII");
    ObservableList<String> yearlist=FXCollections.observableArrayList("FE","SE","TE","BE");
    ObservableList<String> branchlist=FXCollections.observableArrayList("CMPN","IT","EXTC","NA");
    ObservableList<String> hours=FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10", "11", "12");
     ObservableList<String> mins=FXCollections.observableArrayList("00","01","02","03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60");
    ObservableList<String> ampm=FXCollections.observableArrayList("am","pm");
   
     @FXML 
    private ChoiceBox Exm_sem,Exm_year,Exm_branch,Exm_sub,ea;
    @FXML
    private DatePicker datep;
    
    @FXML
    private ChoiceBox sh,sm,sa,eh,em;
    
    @FXML 
    TableView<Exam> examtable;
     @FXML
    private TableColumn<Exam, String> exam_id;
    @FXML
    private TableColumn<Exam, String> sem;
     @FXML
    private TableColumn<Exam, String> branch;
      @FXML
    private TableColumn<Exam, String> year;
       @FXML
    private TableColumn<Exam, String> subject_name;
        @FXML
    private TableColumn<Exam, Date> date;
         @FXML
    private TableColumn<Exam, String> start_time;
        @FXML
    private TableColumn<Exam, String> end_time;
        
    @FXML
    public void initialize() throws SQLException  {
        
        Exm_sem.setItems(Semlist);
        Exm_year.setItems(Variables.yearlist);
        Exm_branch.setItems(Variables.branchlist);
       sh.setItems(hours);
       sm.setItems(mins);
       sa.setItems(ampm);
       eh.setItems(hours);
       em.setItems(mins);
       ea.setItems(ampm);
       pop_table();
        
        
    }
    
    @FXML
    public void next() throws SQLException{
        
         Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
        String sem=(String)Exm_sem.getValue();
    String year=(String) Exm_year.getValue();
    String branch=(String)Exm_branch.getValue();
    
   
     stm = conn.createStatement();
    String sql = "Select * from Schema1.subject where Branch='"+branch+"' AND Year='"+year+"' AND Sem='"+sem+"';";
   ResultSet rst;
    rst = stm.executeQuery(sql);
    String[] a = new String[50];
    int temp=0;
     while (rst.next()) {
         a[temp]=rst.getString("Name");
             
         temp++;
       }
      List<String> list = new ArrayList<String>();
      for(int i=0;i<temp;i++){  
      list.add(a[i]);
      }
    
    ObservableList<String> SubList=FXCollections.observableArrayList(list);
           Exm_sub.setItems(SubList);
     
     }
    
    @FXML
    public void inserts() throws SQLException{
         Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm,stm1;
    
    
    String sem=(String)Exm_sem.getValue();
    String year=(String) Exm_year.getValue();
    String branch=(String)Exm_branch.getValue();
     String temp1= (String)Exm_sub.getValue();
    int tp=0;
   String sql2 = "Select * from Schema1.subject where Branch='"+branch+"' AND Year='"+year+"' AND Sem='"+sem+"';";
   ResultSet rst;
   stm1 = conn.createStatement();
    rst = stm1.executeQuery(sql2);
    String[] a = new String[50];
    int temp=0;
     while (rst.next()) {
         a[temp]=rst.getString("Name");
      
            if(a[temp].equals(temp1)){
                tp=1;
            }
         temp++;
       }
    String name;
    if(tp==1){
   name=temp1;
    }
    else{
        name=null;
    }
    LocalDate date=datep.getValue();
    String str1=(String)sh.getValue();
    String str2=str1.concat(":");
    String str3=str2.concat((String)sm.getValue());
    String str4_start=str3.concat((String)sa.getValue());
     String str5=(String)eh.getValue();
    String str6=str5.concat(":");
    String str7=str6.concat((String)em.getValue());
    String str8_end=str7.concat((String)ea.getValue());
   
    if((name != null) && (name!="")){
    stm = conn.createStatement();
    String sql = "Insert into Schema1.exam values(0,'"+sem+"','"+branch+"','"+year+"','"+name+"','"+date+"','"+str4_start+"','"+str8_end+"');";
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
     pop_table();
    }
    
    }
    @FXML
      public void handle() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("esat_class.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

     @FXML 
    public void pop_table() throws SQLException
    {
        ObservableList<Exam> data = FXCollections.observableArrayList();
          exam_id.setCellValueFactory(new PropertyValueFactory<Exam, String>("exam_id"));
        sem.setCellValueFactory(new PropertyValueFactory<Exam, String>("sem"));
        branch.setCellValueFactory(new PropertyValueFactory<Exam, String>("branch"));
        year.setCellValueFactory(new PropertyValueFactory<Exam, String>("year"));
        subject_name.setCellValueFactory(new PropertyValueFactory<Exam, String>("subject_name"));
          date.setCellValueFactory(new PropertyValueFactory<Exam, Date>("date"));
        start_time.setCellValueFactory(new PropertyValueFactory<Exam, String>("start_time"));
        end_time.setCellValueFactory(new PropertyValueFactory<Exam, String>("end_time"));
        
       Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
    stm = conn.createStatement();
    String sql = "Select * From Schema1.exam";
    ResultSet rst;
    rst = stm.executeQuery(sql);
    
    while (rst.next()) {
        Exam customer = new Exam(rst.getInt("exam_id"), rst.getString("sem"), rst.getString("branch"), rst.getString("year"),rst.getString("subject_name"),rst.getDate("date"),rst.getString("start_time"), rst.getString("end_time"));
        data.add(customer);
    }
    
         

        //FINALLY ADDED TO TableView
        examtable.setItems(data);
    }
    @FXML
   public void selecteds() throws SQLException{
       Exam person = examtable.getSelectionModel().getSelectedItem();
        Exm_sem.getSelectionModel().select(person.getSem());
        Exm_year.getSelectionModel().select(person.getYear());
        Exm_branch.getSelectionModel().select(person.getBranch());
        next();
         Exm_sub.getSelectionModel().select(person.getSubject_name());
     
         datep.setValue(person.getDate().toLocalDate());
         sh.getSelectionModel().select(person.getStart_time().substring(0, 2));
         sm.getSelectionModel().select(person.getStart_time().substring(3, 5));
         sa.getSelectionModel().select(person.getStart_time().substring(5, 7));
               eh.getSelectionModel().select(person.getEnd_time().substring(0, 2));
         em.getSelectionModel().select(person.getEnd_time().substring(3, 5));
         ea.getSelectionModel().select(person.getEnd_time().substring(5, 7));
   }
    @FXML
    public void updates() throws SQLException{
         Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm,stm1;
    Exam person = examtable.getSelectionModel().getSelectedItem();
    int exm_id=person.getExam_id();
    String sem=(String)Exm_sem.getValue();
    String year=(String) Exm_year.getValue();
    String branch=(String)Exm_branch.getValue();
    String temp1= (String)Exm_sub.getValue();
    int tp=0;
   String sql2 = "Select * from Schema1.subject where Branch='"+branch+"' AND Year='"+year+"' AND Sem='"+sem+"';";
   ResultSet rst;
   stm1 = conn.createStatement();
    rst = stm1.executeQuery(sql2);
    String[] a = new String[50];
    int temp=0;
     while (rst.next()) {
         a[temp]=rst.getString("Name");
      
            if(a[temp].equals(temp1)){
                tp=1;
            }
         temp++;
       }
    String name;
    if(tp==1){
   name=temp1;
    }
    else{
        name=null;
    }
    LocalDate date=datep.getValue();
    String str1=(String)sh.getValue();
    String str2=str1.concat(":");
    String str3=str2.concat((String)sm.getValue());
    String str4_start=str3.concat((String)sa.getValue());
     String str5=(String)eh.getValue();
    String str6=str5.concat(":");
    String str7=str6.concat((String)em.getValue());
    String str8_end=str7.concat((String)ea.getValue());
   
    if((name != null) && (name!="")){
  
    stm = conn.createStatement();
    String sql ="UPDATE Schema1.exam SET end_time='"+str8_end+"',start_time='"+str4_start+"',date='"+date+"',branch = '"+branch+"', subject_name= '"+name+"', year = '"+year+"',sem = '"+sem+"' Where exam_id='"+exm_id+"';";
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    pop_table();
    }
    }

    @FXML
   public void deletes() throws SQLException{
   Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
 Exam person = examtable.getSelectionModel().getSelectedItem();
    int id=person.getExam_id();
   stm = conn.createStatement();
    String sql = "Delete From Schema1.exam Where exam_id='"+id+"';";    
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    pop_table();
   }

    
    
    
    }
    
