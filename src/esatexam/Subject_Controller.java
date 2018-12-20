/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 *
 * @author Harsh
 */
public class Subject_Controller  {
    ObservableList<String> Semlist=FXCollections.observableArrayList("I","II","III","IV","V","VI","VII","VIII");
    ObservableList<String> yearlist=FXCollections.observableArrayList("FE","SE","TE","BE");
    ObservableList<String> branchlist=FXCollections.observableArrayList("CMPN","IT","EXTC","NA");
    int Id;
    @FXML
    private TextField Sub_id;
    @FXML
    private TextField Sub_name;
    @FXML
    private ChoiceBox Sub_sem;
    @FXML
    private ChoiceBox Sub_year;
    @FXML
    private ChoiceBox Sub_branch;
    @FXML 
    TableView<Subject> examtable;
     @FXML
    private TableColumn<Subject, Integer> Idt;
     @FXML
    private TableColumn<Subject, String> sub_id;
    @FXML
    private TableColumn<Subject, String> name;
     @FXML
    private TableColumn<Subject, String> branch;
      @FXML
    private TableColumn<Subject, String> year;
       @FXML
    private TableColumn<Subject, String> sem;
       @FXML
       private Button insert_button,insert_button1,insert_button11;
       @FXML
       private Pane pane_subject;

    
    
    
    @FXML
    public void initialize() throws SQLException  {
        
        Sub_sem.setItems(Semlist);
        Sub_year.setItems(yearlist);
        Sub_branch.setItems(Variables.branchlist);
        pop_table();
        pane_subject.getStyleClass().add("bf-color-pane");
        insert_button.getStyleClass().add("butt");
        insert_button1.getStyleClass().add("butt");
        insert_button11.getStyleClass().add("butt");
        Sub_id.getStyleClass().add("text-field");
        Sub_name.getStyleClass().add("text-field");
      // Sub_name.textProperty().addListener((ov, oldValue, newValue) -> {
     //Sub_name.setText(newValue.toUpperCase());
//});
      


        
    }
    @FXML 
    public void pop_table() throws SQLException
    {
        ObservableList<Subject> data = FXCollections.observableArrayList();
         Idt.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("Id"));
          sub_id.setCellValueFactory(new PropertyValueFactory<Subject, String>("subject_id"));
        branch.setCellValueFactory(new PropertyValueFactory<Subject, String>("branch"));
        name.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
        sem.setCellValueFactory(new PropertyValueFactory<Subject, String>("sem"));
        year.setCellValueFactory(new PropertyValueFactory<Subject, String>("year"));
       Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
    stm = conn.createStatement();
    String sql = "Select * From Schema1.subject";
    ResultSet rst;
    rst = stm.executeQuery(sql);
    
    while (rst.next()) {
        Subject customer = new Subject(rst.getInt("Id"),rst.getString("Subject_id"), rst.getString("Name"), rst.getString("Sem"), rst.getString("Year"),rst.getString("Branch"));
        data.add(customer);
    }
    
         

        //FINALLY ADDED TO TableView
        examtable.setItems(data);
         Callback<TableColumn<Subject, String>, TableCell<Subject, String>> cellFactory2 =
        new Callback<TableColumn<Subject, String>, TableCell<Subject, String>>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<Subject, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getStrings());
                        try {
                            setStyle("-fx-background-color:"+getColor());
                        } catch (SQLException ex) {
                            Logger.getLogger(Single_seatingController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    private String getStrings() {
                        return getItem() == null ? " " : getItem().toString();
                    }
                    private String getColor() throws SQLException{
                        String tp=getStrings();
                        if(!tp.equals(" ")){
                       String sql="select color from schema1.branch where shortform='"+tp+"'";
                      /* Connection conn=DriverManager.getConnection(
                       "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                       );*/
                       Statement stm;
                       stm = conn.createStatement();
                       ResultSet rst;
                       rst=stm.executeQuery(sql);
                       String color="white";
                       
                       while(rst.next()){
                           color=rst.getString("color");
                       }
                       //System.out.println(color);
                       return color;
                        }
                        else
                            return "white";
                    } 
                };


                return cell;
            }
        };
      branch.setCellFactory(cellFactory2);
    }
    @FXML
    public void insert()throws SQLException {
        Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
    String id=Sub_id.getText();
    String name=Sub_name.getText();
    String sem=(String)Sub_sem.getValue();
    String year=(String) Sub_year.getValue();
    String branch=(String)Sub_branch.getValue();
    
    
    stm = conn.createStatement();
    String sql = "Insert into Schema1.subject values(0,'"+id+"','"+name+"','"+branch+"','"+year+"','"+sem+"');";
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
       Subject person = examtable.getSelectionModel().getSelectedItem();
       Id=person.getId();
        Sub_id.setText(person.getSubject_id());
        Sub_name.setText(person.getName());
        Sub_sem.getSelectionModel().select(person.getSem());
        Sub_year.getSelectionModel().select(person.getYear());
        Sub_branch.getSelectionModel().select(person.getBranch());
   }
    
   @FXML
   public void updates() throws SQLException{
       Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
    Statement stm;
    String id=Sub_id.getText();
    String name=Sub_name.getText();
    String sem=(String)Sub_sem.getValue();
    String year=(String) Sub_year.getValue();
    String branch=(String)Sub_branch.getValue();
    
    
    stm = conn.createStatement();
    String sql = "UPDATE Schema1.subject SET Branch = '"+branch+"', Name= '"+name+"', Year = '"+year+"',Sem = '"+sem+"', Subject_id='"+id+"' Where Id="+Id+";";    
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
    String id=Sub_id.getText();
   stm = conn.createStatement();
    String sql = "Delete From Schema1.subject Where Id="+Id+";";    
    try{
        stm.executeUpdate(sql);
    }catch(Exception e)
    {
        e.printStackTrace();

    }
    pop_table();
   }
   
    
}
