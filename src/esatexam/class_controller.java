/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Harsh
 */
public class class_controller {
     ObservableList<String> yearlist=FXCollections.observableArrayList("FE","SE","TE","BE");
    ObservableList<String> branchlist=FXCollections.observableArrayList("CMPN","IT","EXTC","NA");
int Id;
@FXML
private Pane classpane;
 @FXML
    private TextField div;
 @FXML
    private TextField nos;
 @FXML
    private TextField Except;
 @FXML
    private TextField Room_no;
  @FXML
    private TextField Startz;
 @FXML
    private ChoiceBox Cls_branch;
 @FXML
    private ChoiceBox Cls_year;
 
 @FXML 
    TableView<classs> classtable;
  @FXML
    private TableColumn<classs, Integer> Idt;
     @FXML
    private TableColumn<classs, Integer> room_col;
    @FXML
    private TableColumn<classs, String> year_col;
     @FXML
    private TableColumn<classs, String> branch_col;
      @FXML
    private TableColumn<classs, String> div_col;
      @FXML
    private TableColumn<classs, Integer> nos_col;
       @FXML
    private TableColumn<classs, String> except_col;
   @FXML
    private TableColumn<classs, Integer> Start_no;
  @FXML
    public void initialize() throws SQLException {
        
        Cls_branch.setItems(Variables.branchlist);
        Cls_year.setItems(yearlist);
        classpane.getStyleClass().add("bf-color-pane");
        pop_table();
    }
    
    
      @FXML 
    public void pop_table() throws SQLException
    {
        ObservableList<classs> data = FXCollections.observableArrayList();
         Idt.setCellValueFactory(new PropertyValueFactory<classs,Integer>("Id"));
          room_col.setCellValueFactory(new PropertyValueFactory<classs,Integer>("Room_no"));
        year_col.setCellValueFactory(new PropertyValueFactory<classs, String>("year"));
       branch_col.setCellValueFactory(new PropertyValueFactory<classs, String>("Branch"));
       div_col.setCellValueFactory(new PropertyValueFactory<classs, String>("Division"));
        nos_col.setCellValueFactory(new PropertyValueFactory<>("Nos"));
       except_col.setCellValueFactory(new PropertyValueFactory<classs, String>("Except"));
        Start_no.setCellValueFactory(new PropertyValueFactory<>("Start_no"));
       Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm,stm2;
    stm = conn.createStatement();
    stm2=conn.createStatement();
    String sql = "Select * From Schema1.class_details order by Year,Branch,Division";
    ResultSet rst;
    rst = stm.executeQuery(sql);
     
    while (rst.next()) {
        String exceptee = "";
        if(rst.getInt("Noe")!=0){
        String sql2 = "Select * From Schema1.except_class where Room_no="+rst.getInt("Room_no")+";";
    ResultSet rst2;
    
    rst2 = stm2.executeQuery(sql2);
    
    while (rst2.next()) {
          exceptee=exceptee+Integer.toString(rst2.getInt("Except_no"));
         exceptee=exceptee+",";
     }
   
    exceptee=exceptee.substring(0, (exceptee.length()-1));
   
        }else exceptee="None";
       classs customer = new classs(rst.getInt("Class_id"),rst.getInt("Room_no"), rst.getString("Branch"), rst.getString("Year"), rst.getInt("Nos"),rst.getString("Division"),exceptee,rst.getInt("Starting_roll"));
      
       data.add(customer);
    }
    
         

        classtable.setItems(data);
    }
    
     @FXML
   public void selecteds(){
       classs person = classtable.getSelectionModel().getSelectedItem();
       Id=person.getId();
        Room_no.setText(Integer.toString(person.getRoom_no()));
        div.setText(person.getDivision());
        Except.setText(person.getExcept());
         nos.setText(Integer.toString(person.getNos()));
        Cls_year.getSelectionModel().select(person.getYear());
        Cls_branch.getSelectionModel().select(person.getBranch());
        Startz.setText(Integer.toString(person.getStart_no()));
        
   }
     @FXML
   public void updates() throws SQLException{
       Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
    
    int rooms=Integer.parseInt(Room_no.getText());
    String divs=div.getText();
    String Except1=(String)Except.getText();
    int noss=Integer.parseInt(nos.getText());
    String year=(String) Cls_year.getValue();
    String branch=(String)Cls_branch.getValue();
    int Start_no=Integer.parseInt(Startz.getText());
      String[] tp=new String[(Except1.length()/2)+2];
  int count=0;
  int j=0;
  tp[j]="";
  for(int i=0;i<Except1.length();i++){
      if(Except1.charAt(i)==','){
          count++;
          
        
          j++;
          tp[j]="";
      }else
      {
          tp[j]=tp[j]+Except1.charAt(i);
      }
      
      
  }
  if(Except1.equals("none")||Except1.equals("None")){
      count=0;
  }else{
  count++;}

int[] a = new int[count];
for(int k=0; k<count;k++)
{
    a[k]=Integer.parseInt(tp[k]);
  
}
 
  
    stm = conn.createStatement();
    String sql = "UPDATE Schema1.class_details set Room_no="+rooms+", Branch = '"+branch+"', Year = '"+year+"',Nos = "+noss+", Division='"+divs+"', Noe="+count+", Starting_roll="+Start_no+" Where Class_id="+Id+";";    
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    
    String sql3="Delete from Schema1.except_class where Id="+Id+";";
    try{
        stm.executeUpdate(sql3);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
     if(count!=0){
    for(int k=0; k<count;k++)
{
    String sql2 = "Insert into Schema1.except_class values("+rooms+","+a[k]+","+Id+");";
    try{
         stm.executeUpdate(sql2);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    }
    
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
     String sql2 = "Delete From Schema1.except_class Where Id="+Id+";";    
    try{
        stm.executeUpdate(sql2);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
   
    String sql = "Delete From Schema1.class_details Where Class_id="+Id+";";    
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
  
    pop_table();
   }
   
    
    
    
    
    
    
    
    @FXML
    public void insert()throws SQLException {
        Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
    int room_no=Integer.parseInt(Room_no.getText());
    String year=(String) Cls_year.getValue();
    String branch=(String)Cls_branch.getValue();
    int nos1=Integer.parseInt(nos.getText());
    String div1=(String)div.getText();
    String Except1=(String)Except.getText();
    int Start_no=Integer.parseInt(Startz.getText());
     String[] tp=new String[(Except1.length()/2)+2];
  int count=0;
  int j=0;
  tp[j]="";
  for(int i=0;i<Except1.length();i++){
      if(Except1.charAt(i)==','){
          count++;
          
        
          j++;
          tp[j]="";
      }else
      {
          tp[j]=tp[j]+Except1.charAt(i);
      }
      
      
  }
  if(Except1.equals("none")||Except1.equals("None")){
      count=0;
  }else{
  count++;}

int[] a = new int[count];
for(int k=0; k<count;k++)
{
    a[k]=Integer.parseInt(tp[k]);
  
}
    stm = conn.createStatement();
    String sql = "Insert into Schema1.class_details values(0,"+room_no+",'"+branch+"','"+year+"',"+nos1+",'"+div1+"',"+count+","+Start_no+");";
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    String sql3="Select Class_id from Schema1.class_details where Room_no="+room_no+" and Branch='"+branch+"'and Year='"+year+"' and Division='"+div1+"';" ;
     ResultSet rst;
     rst = stm.executeQuery(sql3);
     while(rst.next())
     Id=rst.getInt("Class_id");
    if(count!=0){
    for(int k=0; k<count;k++)
{
    String sql2 = "Insert into Schema1.except_class values("+room_no+","+a[k]+","+Id+");";
    try{
         stm.executeUpdate(sql2);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    }
    
    }
    pop_table();
    }
    
}
