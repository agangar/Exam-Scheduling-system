/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author victim
 */
public class Main_controller  {
    int Id,req,flag=0;
  
    
    @FXML 
    Button button_seating,button_createSeating,button_allocateDuties,button_paneclassroomback;
    
    @FXML
    Label label,label2;
    
    @FXML
    Button button_allocate;
    
    @FXML
    Button button_paneclassroom,button_paneteacher,button_panedata,pane_start2back,selectall_butt;
    
    @FXML
    AnchorPane outer;
    
    @FXML
    AnchorPane pane_start;
    
    @FXML
    AnchorPane pane_start1; 
    
    @FXML
    AnchorPane pane_start2,pane_start3,pane_start4;
    
    @FXML
    ListView listview_class;
    
    @FXML
    TextField input_shortform,input_name,input_color,name_seat;
    
    @FXML
    CheckBox checkbox_firstYear,checkbox_secondYear,checkbox_thirdYear,checkbox_fourthYear,checkbox_single,checkbox_double;
    
    @FXML
    TableView tablebranch,class_select;
    
    @FXML
    ImageView image,logo_image,img_classroom,img_subject,img_teacher,img_exam,img_exam1;
            
    @FXML
    private TableColumn<branch,String> shortform;
    
    @FXML
    private TableColumn<branch,String> name;
    
    @FXML
    private TableColumn<branch,String> color;

    @FXML
    private TableColumn<branch,Integer> branchid;
    
    @FXML
    private TableColumn<SelectClass,CheckBox> select_column;
    
    @FXML
    private TableColumn<SelectClass,Integer> serialno_column;
    
    @FXML
    private TableColumn<SelectClass,Integer> roomno_column;
    
    @FXML
    private TableColumn<SelectClass,String> year_column;
    
    @FXML
    private TableColumn<SelectClass,String> branch_column;
    
    @FXML
    private TableColumn<SelectClass,String> div_column;
    
    
    @FXML
    private Button button_insertbranch,button_updatebranch,button_deletebranch,button_finish;
    
    ObservableList<SelectClass> data = FXCollections.observableArrayList();
   
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
        if(event.getSource()== button_seating)
        {
       // System.out.println("You clicked me!");
             pane_start.setVisible(false);
             pane_start1.setVisible(true);
       
        }
        else if(event.getSource() == button_panedata){
            pane_start1.setVisible(false);
            pane_start4.setVisible(true);
        }
        else if(event.getSource() == button_paneclassroom){
             pane_start4.setVisible(false);
             pane_start2.setVisible(true);    
        }
       else if(event.getSource() ==button_paneclassroomback)
        {
             pane_start2.setVisible(false); 
            pane_start4.setVisible(false);
            pane_start1.setVisible(true);    
        }
       else if(event.getSource()==pane_start2back)
       {
          
             pane_start2.setVisible(false);  
              pane_start1.setVisible(false);
               pane_start4.setVisible(true);
       }
    }
     @FXML
   public void subject() throws IOException{
      
   Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("esatsubject.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Subject");
            Scene scene1=new Scene(root,800,530);
            scene1.getStylesheets().add(getClass().getResource("effect.css").toExternalForm());
            stage.setScene(scene1);
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
   }
    @FXML
   public void classroom() throws IOException{
      
   Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("esat_class.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);
            Scene scene2=new Scene(root);
            scene2.getStylesheets().add(getClass().getResource("effect.css").toExternalForm());
           
            stage.setTitle("Class Details");
            stage.setScene(scene2);
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
   }
       @FXML
   public void exams() throws IOException{
      
   Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("exam1.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Exam Details");
            Scene scene3=new Scene(root,800,530);
            scene3.getStylesheets().add(getClass().getResource("effect.css").toExternalForm());
           
            stage.setScene(scene3);
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
   }
   
      @FXML
   public void teachers() throws IOException{
      
   Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDoc.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Teachers");
            Scene scene4=new Scene(root,800,530);
            scene4.getStylesheets().add(getClass().getResource("effect.css").toExternalForm());
           
            stage.setScene(scene4);
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
   }
   
       
   
   @FXML
   public void Seatingf() throws IOException{
      Variables.count=new int[req];
      int i=0;
       for(SelectClass select : data){
            if(select.getSelect().isSelected()){
                Variables.count[i]=select.getRoom_no();
            i++;
            }
        }
       
       if(i==req){
           
           label2.setVisible(false);
       if (Variables.type.equals("Single")){
   Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("single_seating.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setTitle("Single Seating");
            stage.setScene(new Scene(root, 1000, 530));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
      }else{
          Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Seating_allo.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setTitle("Double Seating");
            stage.setScene(new Scene(root, 1000, 530));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
          
      }}else{
           label2.setVisible(true);
       }
   }
   @FXML
   public void teacherf(){
       
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("teacherduty.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setTitle("Teachers Duties Allocation");
            stage.setScene(new Scene(root, 1000, 530));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       
       
   }
   
  @FXML
   public void select_all(){
       for(SelectClass select : data){
        select.setSelect(select.select);
       }  
   }
   
   
   
   @FXML
   public void pop_table() throws SQLException
   {
        ObservableList<branch> data = FXCollections.observableArrayList();
        shortform.setCellValueFactory(new PropertyValueFactory<branch,String>("shortform"));
        name.setCellValueFactory(new PropertyValueFactory<branch,String>("name"));
        color.setCellValueFactory(new PropertyValueFactory<branch,String>("color"));
        branchid.setCellValueFactory(new PropertyValueFactory<branch,Integer>("branchid"));
   
        String sqlquery =  "Select * from schema1.branch";
         Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
        Statement stm;
        stm = conn.createStatement();
        ResultSet rst;
       
            rst = stm.executeQuery(sqlquery);
            

        while(rst.next())
        {
            branch b =  new branch(rst.getString("shortform"),rst.getString("name"),rst.getString("color"),rst.getInt("branchid"));
            data.add(b);
            
        }
        tablebranch.setItems(data);
      Callback<TableColumn<branch, String>, TableCell<branch, String>> cellFactory =
        new Callback<TableColumn<branch, String>, TableCell<branch, String>>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<branch, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getString());
                        setStyle("-fx-background-color:"+getString());
                    }

                    private String getString() {
                        return getItem() == null ? "" : getItem().toString();
                    }
                };


                return cell;
            }
        };
      color.setCellFactory(cellFactory);
   }
   @FXML
   public void selecteds(){
        branch person = (branch) tablebranch.getSelectionModel().getSelectedItem();
        input_shortform.setText(person.getShortform());
        input_name.setText(person.getName());
        input_color.setText(person.getColor());
        Id=person.getBranchid();        
   }
   
      @FXML
   public void updates() throws SQLException
   {
       String shortform = input_shortform.getText();
       String name = input_name.getText();
       String color = input_color.getText();
       String sql = "update schema1.branch Set  shortform='"+shortform+"', Name='"+name+"', color='"+color+"' Where branchid="+Id+"";
       Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
       Statement stm;
        stm = conn.createStatement();
        try{
        stm.executeUpdate(sql);}
        catch(Exception e){
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
    String sql = "Delete From Schema1.branch Where branchid='"+Id+"';";    
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    pop_table();
   }

   @FXML
   public void inserts() throws SQLException
   {
       String shortform = input_shortform.getText();
       String name = input_name.getText();
       String color = input_color.getText();
       String sql = "insert into schema1.branch values ('"+shortform+"','"+name+"','"+color+"',0)";
       Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
        Statement stm;
        stm = conn.createStatement();
        try{
        stm.executeUpdate(sql);}
        catch(Exception e){
            e.printStackTrace();
        }
        pop_table();
   }
   @FXML
   public void nexts() throws SQLException{
    Variables.name=name_seat.getText();
    if(checkbox_firstYear.isSelected()){
        Variables.yearlist.add("FE");
    }
    if(checkbox_secondYear.isSelected()){
        Variables.yearlist.add("SE");
    }
    if(checkbox_thirdYear.isSelected()){
        Variables.yearlist.add("TE");
    }
    if(checkbox_fourthYear.isSelected()){
        Variables.yearlist.add("BE");
    }
     String sqlquery =  "Select * from schema1.branch";
         Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
        Statement stm;
        stm = conn.createStatement();
        ResultSet rst;
       
            rst = stm.executeQuery(sqlquery);
            

        while(rst.next())
        {
            Variables.branchlist.add(rst.getString("shortform"));
            
        }
        
        if(checkbox_single.isSelected()){
            Variables.type="Single";
        }
        else Variables.type="Double";
        
        System.out.println(Variables.name+" "+Variables.type);
        for(int i=0;i<Variables.branchlist.size();i++){
            System.out.println(Variables.branchlist.get(i));
        }
        for(int i=0;i<Variables.yearlist.size();i++){
            System.out.println(Variables.yearlist.get(i));
        }
   
    
}
   @FXML
   public void selectClass() throws SQLException
   {
       
       
       
       data.clear();
               
    select_column.setCellValueFactory(new PropertyValueFactory<SelectClass,CheckBox>("select"));
    serialno_column.setCellValueFactory(new PropertyValueFactory<SelectClass,Integer>("Id"));
    roomno_column.setCellValueFactory(new PropertyValueFactory<SelectClass,Integer>("Room_no"));
    year_column.setCellValueFactory(new PropertyValueFactory<SelectClass,String>("Year"));
    branch_column.setCellValueFactory(new PropertyValueFactory<SelectClass,String>("Branch"));
    div_column.setCellValueFactory(new PropertyValueFactory<SelectClass,String>("Division"));
   
     String sqlquery2 =  "Select * from schema1.class_details order by Year,Branch,Division";
         Connection conn2=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
        Statement stm,stm2;
        stm = conn2.createStatement();
        ResultSet rst2;
       
            rst2 = stm.executeQuery(sqlquery2);
            
        int count=1;
        while(rst2.next())
        {
            SelectClass b =  new SelectClass(count,rst2.getInt("Room_no"),rst2.getString("Year"),rst2.getString("Branch"),rst2.getString("Division"));
            count++;
            data.add(b);
            
        }
        class_select.setItems(data);
        if(Variables.type.equals("Single")){
            Single_seatingController sc= new Single_seatingController();
            req=sc.generatecount();
        }else{
            Seating_alloController sc=new Seating_alloController();
            req=sc.generatecount();
        }
        
   }
   
     @FXML
   public void single_click(){
       checkbox_double.setSelected(false);
         Image img = new Image("images/single.jpg");
    image.setImage(img);
       
   } 
   
     @FXML
   public void double_click(){
       checkbox_single.setSelected(false);
         Image img = new Image("images/double.jpg");
    image.setImage(img);
    
       
   } 
   
    @FXML
    private void handleClassAction() throws SQLException {
        int count=0;
        
        for(SelectClass select : data){
            if(select.getSelect().isSelected()){
                count++;
            } else {
            }
                label.setText(count+" no.of Classes selected. \n you have to select total "+req);
        }
    
    }
    @FXML
    public void home()
    {
        System.out.println("Clicked");
         pane_start.setVisible(true);
         pane_start1.setVisible(false);
          pane_start2.setVisible(false);
             pane_start4.setVisible(false);
    }
    
    @FXML
    public void initialize() throws SQLException {
         
        pop_table();
        Image img = new Image("images/logo.png");
        logo_image.setImage(img);
        Image img2 = new Image("images/classrooms.png");
        img_classroom.setImage(img2);
        Image img3 = new Image("images/subjects.png");
        img_subject.setImage(img3);
        Image img4 = new Image("images/teachers.png");
        img_teacher.setImage(img4);
        Image img5 = new Image("images/exam.png");
        img_exam.setImage(img5);
        
        
        outer.getStyleClass().add("bf-color-pane");
        pane_start.getStyleClass().add("bf-color-pane");
        pane_start1.getStyleClass().add("bf-color-pane");
        pane_start2.getStyleClass().add("bf-color-pane");
        checkbox_firstYear.getStyleClass().add("box");
        checkbox_secondYear.getStyleClass().add("box");
        checkbox_thirdYear.getStyleClass().add("box");
        checkbox_fourthYear.getStyleClass().add("box");
        checkbox_single.getStyleClass().add("box");
        checkbox_double.getStyleClass().add("box");


        button_createSeating.getStyleClass().add("buttB");
        pane_start2back.getStyleClass().add("buttB");
        button_allocateDuties.getStyleClass().add("buttB");
button_paneclassroomback.getStyleClass().add("butt");
        button_seating.getStyleClass().add("seating");
        button_insertbranch.getStyleClass().add("butt");
        selectall_butt.getStyleClass().add("butt");
        button_updatebranch.getStyleClass().add("butt");
        button_deletebranch.getStyleClass().add("butt");
        button_paneclassroom.getStyleClass().add("butt");
        button_panedata.getStyleClass().add("seating");
        name_seat.getStyleClass().add("text-field");
        input_color.getStyleClass().add("text-field");
        input_name.getStyleClass().add("text-field");
        input_shortform.getStyleClass().add("text-field");
//        button_paneteacher.getStyleClass().add("seating");
//       button_finish.getStyleClass().add("seating");

      
    } 
    
    
}
