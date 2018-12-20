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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Harsh
 */
public class Single_seatingController{

     Connection conn;
     int mcount=0;
    @FXML
TableView table;
@FXML
 private TableColumn<seating, Integer> Block_no;
     @FXML
    private TableColumn<seating, Integer> Branch;
    @FXML
    private TableColumn<seating, String> Roll;
       @FXML
    private TableColumn<seating, String> exceptee1;
     @FXML
    private TextField Block;
    @FXML
    private TextField Class,Roll_no,xcept;
    @FXML
    private TableView table1;
    @FXML
    private TableColumn<seating2,Integer> Desk0;
    @FXML
    private TableColumn<seating2,Integer> Desk1;  
     @FXML
    private TableColumn<seating2,Integer> Desk2;
    @FXML
    private TableColumn<seating2,Integer> Desk3;  
     @FXML
    private TableColumn<seating2,Integer> col0;
    @FXML
    private TableColumn<seating2,Integer> col1;  
     @FXML
    private TableColumn<seating2,Integer> col2;
    @FXML
    private TableColumn<seating2,Integer> col3;  
     @FXML
    private TableColumn<seating2,String> name0;
    @FXML
    private TableColumn<seating2,String> name1;  
     @FXML
    private TableColumn<seating2,String> name2;
    @FXML
    private TableColumn<seating2,String> name3;  
      @FXML
    private TableColumn<seating2,String> row0;  
        @FXML
    private TableColumn<seating2,String> row1;  
          @FXML
    private TableColumn<seating2,String> row2;  
            @FXML
    private TableColumn<seating2,String> row3;  
            
            ObservableList<seating> data = FXCollections.observableArrayList();

    public Single_seatingController() throws SQLException {
        this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
        );
    }
            public void selecteds() throws SQLException{
     ObservableList<seating2> data = FXCollections.observableArrayList();
        Desk0.setCellValueFactory(new PropertyValueFactory<seating2,Integer>("a"));
         row0.setCellValueFactory(new PropertyValueFactory<seating2,String>("tp"));
         name0.setCellValueFactory(new PropertyValueFactory<seating2,String>("name0"));
        col0.setCellValueFactory(new PropertyValueFactory<seating2,Integer>("b"));
        Desk1.setCellValueFactory(new PropertyValueFactory<seating2,Integer>("c"));
        row1.setCellValueFactory(new PropertyValueFactory<seating2,String>("tp2"));
        name1.setCellValueFactory(new PropertyValueFactory<seating2,String>("name1"));
        col1.setCellValueFactory(new PropertyValueFactory<seating2,Integer>("d"));
         Desk2.setCellValueFactory(new PropertyValueFactory<seating2,Integer>("e"));
         row2.setCellValueFactory(new PropertyValueFactory<seating2,String>("tp3"));
         name2.setCellValueFactory(new PropertyValueFactory<seating2,String>("name2"));
        col2.setCellValueFactory(new PropertyValueFactory<seating2,Integer>("f"));
         Desk3.setCellValueFactory(new PropertyValueFactory<seating2,Integer>("g"));
         row3.setCellValueFactory(new PropertyValueFactory<seating2,String>("tp4"));
        name3.setCellValueFactory(new PropertyValueFactory<seating2,String>("name3"));
         col3.setCellValueFactory(new PropertyValueFactory<seating2,Integer>("h"));
       
         
      
      
       
      // Desk0.setCellValueFactory(new PropertyValueFactory<>(""));
       seating person = (seating) table.getSelectionModel().getSelectedItem();
       Block.setText(Integer.toString(person.getRoom_no()));
       Class.setText(person.getClassname());
       Roll_no.setText(person.getNos());
       xcept.setText(person.getExceptee());
       int block=person.getRoom_no();
       String[] classname=new String[4];
       String[] Except1=new String[4];
       
        Statement stm;
        int[] start=new int[4];
        int[] noe=new int[4];
        int[] end=new int[4];
        String sql2 = "select * from schema1.seating where Class="+block+"";
        ResultSet rst;
        stm = conn.createStatement();
        rst = stm.executeQuery(sql2);
        int i=0;
        while (rst.next()){
            classname[i]=rst.getString("Classname");
            String tp=rst.getString("Roll_no");
            start[i]=Integer.parseInt(tp.substring(0,tp.indexOf("-")));
            end[i]=Integer.parseInt(tp.substring(((tp.indexOf("-"))+1),tp.length()));
            Except1[i]=rst.getString("except");
            i++;
            
        }
       
          
            int[][] except1 = new int[4][5];
            for(int r=0;r<i;r++){
             String[] tp=new String[(Except1[r].length()/2)+2];
          int count=0;
  int j=0;
  tp[j]="";
  for(int w=0;w<Except1[r].length();w++){
      if(Except1[r].charAt(w)==','){
          count++;
          j++;
          tp[j]="";
      }else
      {
          tp[j]=tp[j]+Except1[r].charAt(w);
      }
      
      
  }
  if(Except1[r].equals("none")||Except1[r].equals("None")){
      count=0;
  }else{
  count++;}
noe[r]=count;
//System.out.print(count+" "+ tp[1]+" ");
for(int k=0; k<count;k++)
{
   // System.out.print(tp[k]+"  ");
    except1[r][k]=Integer.parseInt(tp[k]);
  
}
 }           
            int t=4,y=4,m=0,n=0;
        for(int j=10;j>0;j--){
            int b=0,d=0,f=0,h=0,c0=0,c1=1;
            String sname0="",sname1="",sname2="",sname3="";
            int a=j;
            int c=21-j;
               int e=20+j;
            int g=41-j;
            if(j%2==0){
           b=start[c0]+t;
           sname0=classname[c0];
             for(int excount=0;excount<noe[c0];excount++){
                 if(except1[c0][excount]>=start[c0])
               if(b>=except1[c0][excount])
                   b++;
           }
           if(b>end[c0]){
              if(i>1){
                  int rem=b-end[c0];
                  b=start[c0+2]+rem-1;
                  sname0=classname[c0+2];
                   for(int excount=0;excount<noe[c0+2];excount++){
                 if(except1[c0+2][excount]>=start[c0+2])
               if(b>=except1[c0+2][excount])
                   b++;
           }
                  if(b>end[c0+2]){b=0;sname0=" "; }
                  
              }else{
               b=0;
               sname0=" ";}}       
 
            
             d=start[c1]+5+m;
             sname1=classname[c1];
               for(int excount=0;excount<noe[c1];excount++){
                   if(except1[c1][excount]>=start[c1])
               if(d>=except1[c1][excount])
                   d++;
           }
            if(d>end[c1]){if(i>2){
                  int rem=d-end[c1];
                  d=start[c1+2]+rem-1;
                  sname1=classname[c1+2];
                   for(int excount=0;excount<noe[c1+2];excount++){
                 if(except1[c1+2][excount]>=start[c1+2])
               if(d>=except1[c1+2][excount])
                   d++;
           }
                  if(d>end[c1+2]){d=0;sname1=" "; }
              }else{
               d=0;
               sname1=" ";}}
            
            
              f=start[c0]+10+t;
            sname2=classname[c0]; 
              for(int excount=0;excount<noe[c0];excount++){
                  if(except1[c0][excount]>=start[c0])
               if(f>=except1[c0][excount])
                   f++;
           }
             if(f>end[c0]){if(i>1){
                  int rem=f-end[c0];
                  f=start[c0+2]+rem-1;
                  sname2=classname[c0+2];
                   for(int excount=0;excount<noe[c0+2];excount++){
                 if(except1[c0+2][excount]>=start[c0+2])
               if(f>=except1[c0+2][excount])
                   f++;
           }
                  if(f>end[c0+2]){f=0;sname2=" "; }
              }else{
               f=0;
               sname2=" ";}}
             
             
            
              h=start[c1]+15+m;
            sname3=classname[c1];
              for(int excount=0;excount<noe[c1];excount++){
                     if(except1[c1][excount]>=start[c1])
               if(h>=except1[c1][excount])
                   h++;
           }
              System.out.println(h+" "+end[c1]+" ");
             if(h>end[c1]){if(i>2){
                  int rem=h-end[c1];
                  h=start[c1+2]+rem-1;
                  sname3=classname[c1+2];
                        for(int excount=0;excount<noe[c1+2];excount++){
                 if(except1[c1+2][excount]>=start[c1+2])
               if(h>=except1[c1+2][excount])
                   h++;
           }
                  if(h>end[c1+2]){h=0;sname3=" "; }
              }else{
               h=0;
               sname3=" ";}}
             
             t--;m++;}else
            {
                //System.out.print("here  ");
                 b=start[c1]+y;
           sname0=classname[c1];
           for(int excount=0;excount<noe[c1];excount++){
                  if(except1[c1][excount]>=start[c1])
               if(b>=except1[c1][excount])
                   b++;
               
           }
           if(b>end[c1]){ if(i>2){
                  int rem=b-end[c1];
                  b=start[c1+2]+rem-1;
                  sname0=classname[c1+2];
                        for(int excount=0;excount<noe[c1+2];excount++){
                 if(except1[c1+2][excount]>=start[c1+2])
               if(b>=except1[c1+2][excount])
                   b++;
           }
                  if(h>end[c1+2]){h=0;sname0=" "; }
              }else{
               b=0;
               sname0=" ";}}    
           
           
             d=start[c0]+5+n;
             sname1=classname[c0];
              for(int excount=0;excount<noe[c0];excount++){
                  if(except1[c0][excount]>=start[c0])
               if(d>=except1[c0][excount])
                   d++;
           }
            if(d>end[c0]){
                if(i>1){
                  int rem=d-end[c0];
                  d=start[c0+2]+rem-1;
                  sname1=classname[c0+2];
                   for(int excount=0;excount<noe[c0+2];excount++){
                 if(except1[c0+2][excount]>=start[c0+2])
               if(d>=except1[c0+2][excount])
                   d++;
           }
                  if(d>end[c0+2]){d=0;sname1=" "; }
              }else{
               d=0;
               sname1=" ";}} 
            
            
              f=start[c1]+10+y;
              sname2=classname[c1]; 
                for(int excount=0;excount<noe[c1];excount++){
                       if(except1[c1][excount]>=start[c1])
               if(f>=except1[c1][excount])
                   f++;
           }
             if(f>end[c1]){ if(i>2){
                  int rem=f-end[c1];
                  f=start[c1+2]+rem-1;
                  sname2=classname[c1+2];
                        for(int excount=0;excount<noe[c1+2];excount++){
                 if(except1[c1+2][excount]>=start[c1+2])
               if(f>=except1[c1+2][excount])
                   f++;
           }
                  if(f>end[c1+2]){f=0;sname2=" "; }
              }else{
               f=0;
               sname2=" ";}}
             
             
              h=start[c0]+15+n;
              sname3=classname[c0];
                for(int excount=0;excount<noe[c0];excount++){
                    if(except1[c0][excount]>=start[c0])
               if(h>=except1[c0][excount])
                   h++;
           }
                System.out.println(h+" "+end[c1]+" :::");
             if(h>end[c0]){
                 if(i>1){
                  int rem=h-end[c0];
                  h=start[c0+2]+rem-1;
                  sname3=classname[c0+2];
                   for(int excount=0;excount<noe[c0+2];excount++){
                 if(except1[c0+2][excount]>=start[c0+2])
               if(h>=except1[c0+2][excount])
                   h++;
           }
                  if(h>end[c0+2]){h=0;sname3=" "; }
              }else{
               h=0;
               sname3=" ";}}
             
             
             y--;n++;
             
            }
            
            
            seating2 tpe=new seating2(a,"",sname0,b,c,"",sname1,d,e,"",sname2,f,g,"",sname3,h);
            
            data.add(tpe);
        }
        table1.setItems(data);
         Callback<TableColumn<seating2, String>, TableCell<seating2, String>> cellFactory2 =
        new Callback<TableColumn<seating2, String>, TableCell<seating2, String>>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<seating2, String>() {
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
                       String sql="select color from schema1.branch where shortform='"+tp.substring(3,tp.length()-2)+"'";
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
      name0.setCellFactory(cellFactory2);
      name1.setCellFactory(cellFactory2);
      name2.setCellFactory(cellFactory2);
      name3.setCellFactory(cellFactory2);
       /* col0.setText(classname[0]);
        col2.setText(classname[0]);
        col4.setText(classname[0]);
        col6.setText(classname[0]);
        col1.setText(classname[1]);
        col3.setText(classname[1]);
        col5.setText(classname[1]);
        col7.setText(classname[1]);
        
        String tp=classname[0].substring(3,5);
        String tp2=classname[1].substring(3,5);
       if(tp.equals("EX")){
           col0.getStyleClass().clear();
           col2.getStyleClass().clear();
           col4.getStyleClass().clear();
           col6.getStyleClass().clear();
           
           
           col0.getStyleClass().add("name-column");
           col2.getStyleClass().add("name-column");
            col4.getStyleClass().add("name-column");
           col6.getStyleClass().add("name-column");
       }
        if(tp2.equals("EX")){
            col1.getStyleClass().clear();
           col3.getStyleClass().clear();
           col5.getStyleClass().clear();
           col7.getStyleClass().clear();
           col1.getStyleClass().add("name-column");
           col3.getStyleClass().add("name-column");
            col5.getStyleClass().add("name-column");
           col7.getStyleClass().add("name-column");
       }
         if(tp.equals("IT")){
           col0.getStyleClass().clear();
           col2.getStyleClass().clear();
           col4.getStyleClass().clear();
           col6.getStyleClass().clear();
             col0.getStyleClass().add("name-column2");
           col2.getStyleClass().add("name-column2");
            col4.getStyleClass().add("name-column2");
           col6.getStyleClass().add("name-column2");
       }
        if(tp2.equals("IT")){
            col1.getStyleClass().clear();
           col3.getStyleClass().clear();
           col5.getStyleClass().clear();
           col7.getStyleClass().clear();
           col1.getStyleClass().add("name-column2");
           col3.getStyleClass().add("name-column2");
            col5.getStyleClass().add("name-column2");
           col7.getStyleClass().add("name-column2");
       }
         if(tp.equals("CM")){
           col0.getStyleClass().add("name-column3");
           col2.getStyleClass().add("name-column3");
            col4.getStyleClass().add("name-column3");
           col6.getStyleClass().add("name-column3");
       }
        if(tp2.equals("CM")){
           col1.getStyleClass().add("name-column3");
           col3.getStyleClass().add("name-column3");
            col5.getStyleClass().add("name-column3");
           col7.getStyleClass().add("name-column3");
       }*/
   }
   
    public void initialize() throws SQLException {
        Block_no.setCellValueFactory(new PropertyValueFactory<seating,Integer>("room_no"));
          Branch.setCellValueFactory(new PropertyValueFactory<seating,Integer>("classname"));
        Roll.setCellValueFactory(new PropertyValueFactory<seating, String>("nos"));
        exceptee1.setCellValueFactory(new PropertyValueFactory<seating, String>("exceptee"));
        /*Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );*/
      mfunction(Variables.count);
    table.setItems(data);
    
    }  
    
    public void mfunction(int [] count) throws SQLException{
          Statement stm,stm2,stm4;
        String trunc="Truncate table schema1.seating";
        stm2 = conn.createStatement();
    stm = conn.createStatement();
     stm4 = conn.createStatement();
    int x = 0;
    stm2.executeUpdate(trunc);
    ResultSet ct = stm.executeQuery("SELECT COUNT(*) FROM Schema1.class_details;");
            if(ct.next()){
                x=ct.getInt(1);
            }
        
            String[] names=new String[x];
            int[] a=new int[x];
            int[] b=new int[x];
            int yearno=Variables.yearlist.size(),k=0;
            int[] c=new int[x];
            //int[] count=new int[55];
            String exceptee[]=new String[x];
     for(int i=0;i<yearno;i++){
    String sql = "Select * From Schema1.class_details where Year='"+Variables.yearlist.get(i)+"' order by Year,Branch,Division ";
    ResultSet result;
    
    result = stm.executeQuery(sql);
    while(result.next()){
                String Class_name = result.getString("Year");
                Class_name=Class_name+" ";
            String concat = Class_name.concat(result.getString("Branch"));
                concat+=" ";
            String concat1 = concat.concat(result.getString("Division"));
                names[k]=concat1;
                exceptee[k]= "";
                
                
        if(result.getInt("Noe")!=0){
            
        String sql2 = "Select * From Schema1.except_class where Room_no="+result.getInt("Room_no")+";";
    ResultSet result2;
    
    result2 = stm4.executeQuery(sql2);
    
    while (result2.next()) {
          exceptee[k]=exceptee[k]+Integer.toString(result2.getInt("Except_no"));
         exceptee[k]=exceptee[k]+",";
     }
   
    exceptee[k]=exceptee[k].substring(0, (exceptee[k].length()-1));
   
        }else exceptee[k]="None";
                a[k]=result.getInt("Nos");
               // System.out.println(a[k]+""+names[k]);
                b[k]=a[k];
                c[k]=result.getInt("Starting_roll");
                //count[k]=result.getInt("Room_no");
                k++;    
    }}
    /* String sql2 = "Select * From Schema1.class_details order by Year,Branch,Division ";
    ResultSet result2;
    int k2=0;
   
    result2 = stm.executeQuery(sql2);
     while(result2.next()){
        count[k2]=result2.getInt("Room_no");
        k2++;
    }
           for(int r=k2;r<50;r++){
               count[r]=0;
           }*/
         int j=0;
         for(int i=0;i<k-2;i++){
             
         while(a[i]>=20&&a[i+2]>=20){
             if(a[i]>=20&&a[i+2]>=20)
         {
             String noo="";
             a[i]-=20;
             a[i+2]-=20;
             if(a[i]==(b[i])-20){noo=Integer.toString(c[i])+"-"+Integer.toString(c[i]+19);}
             else if(a[i]==(b[i])-40){noo=Integer.toString(c[i]+20)+"-"+Integer.toString(c[i]+39);}
             else if(a[i]==(b[i])-60){noo=Integer.toString(c[i]+40)+"-"+Integer.toString(c[i]+59);}
             else if(a[i]==(b[i])-80){noo=Integer.toString(c[i]+60)+"-"+Integer.toString(c[i]+79);}
             //System.out.print(" "+count[j]+" "+names[i]+" "+noo);
             data_add(count[j],names[i],noo,exceptee[i]);
             if(a[i+2]==(b[i+2])-20){noo=Integer.toString(c[i+2])+"-"+Integer.toString(c[i+2]+19);}
             else if(a[i+2]==(b[i+2])-40){noo=Integer.toString(c[i+2]+20)+"-"+Integer.toString(c[i+2]+39);}
             else if(a[i+2]==(b[i+2])-60){noo=Integer.toString(c[i+2]+40)+"-"+Integer.toString(c[i+2]+59);}
             else if(a[i+2]==(b[i+2])-80){noo=Integer.toString(c[i+2]+60)+"-"+Integer.toString(c[i+2]+79);}
             //System.out.print(" "+count[j]+" "+names[i+2]+" "+noo);
             data_add(count[j],names[i+2],noo,exceptee[i+2]);
             j++;
             mcount++;
            // System.out.println();
         }
         }
         if(i==1||i==5)i+=2;
         }
        a=sorta(a,k,names,c,b,exceptee);
         
       // for(int i=0;i<k-1;i++){ System.out.println(names[i]+": "+a[i]);}   
        int i=0;
        while(a[i]>0){
 
             if(a[i]>=20&&a[i+1]>=20)
         {
             String noo="";
              a[i]-=20;
             a[i+1]-=20;
            if(a[i]==(b[i])-20){noo=Integer.toString(c[i])+"-"+Integer.toString(c[i]+19);}
             else if(a[i]==(b[i])-40){noo=Integer.toString(c[i]+20)+"-"+Integer.toString(c[i]+39);}
             else if(a[i]==(b[i])-60){noo=Integer.toString(c[i]+40)+"-"+Integer.toString(c[i]+59);}
             else if(a[i]==(b[i])-80){noo=Integer.toString(c[i]+60)+"-"+Integer.toString(c[i]+79);}
            // System.out.print(" "+count[j]+" "+names[i]+" "+noo);
              data_add(count[j],names[i],noo,exceptee[i]);
               if(a[i+1]==(b[i+1])-20){noo=Integer.toString(c[i+1])+"-"+Integer.toString(c[i+1]+19);}
             else if(a[i+1]==(b[i+1])-40){noo=Integer.toString(c[i+1]+20)+"-"+Integer.toString(c[i+1]+39);}
             else if(a[i+1]==(b[i+1])-60){noo=Integer.toString(c[i+1]+40)+"-"+Integer.toString(c[i+1]+59);}
             else if(a[i+1]==(b[i+1])-80){noo=Integer.toString(c[i+1]+60)+"-"+Integer.toString(c[i+1]+79);}
            //System.out.print(" "+count[j]+" "+names[i+1]+" "+noo); 
              data_add(count[j],names[i+1],noo,exceptee[i+1]);
             j++;
             mcount++;
            // System.out.println();
             a=sorta(a,k,names,b,c,exceptee);
         }
            else if(a[i]>=20&&a[i+1]<20)
             {
                int total=40,temp2=0;
                a[i]=a[i]-20;
                String noo="";
                 if(a[i]==(b[i])-20){noo=Integer.toString(c[i])+"-"+Integer.toString(c[i]+19);}
             else if(a[i]==(b[i])-40){noo=Integer.toString(c[i]+20)+"-"+Integer.toString(c[i]+39);}
             else if(a[i]==(b[i])-60){noo=Integer.toString(c[i]+40)+"-"+Integer.toString(c[i]+59);}
             else if(a[i]==(b[i])-80){noo=Integer.toString(c[i]+60)+"-"+Integer.toString(c[i]+79);}
                 //System.out.print(" "+count[j]+" "+names[i]+" "+noo);
                  data_add(count[j],names[i],noo,exceptee[i]);
                 total=total-20;
                 int left=total-a[i+1];
                 int p=0;
                 for(p=i+2; p<k;p++)
                 {
                     if(a[p]<=left){
                         temp2=a[p];
                     break;
                     }
                 }
                 if(temp2==0){
                 if(b[i+1]>80){noo=Integer.toString(c[i+1]+80)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>60)&&(b[i+1]<=80)){noo=Integer.toString(c[i+1]+60)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>40)&&(b[i+1]<=60)){noo=Integer.toString(c[i+1]+40)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>20)&&(b[i+1]<=40)){noo=Integer.toString(c[i+1]+20)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 //System.out.print(" "+count[j]+" "+names[i+1]+":"+noo);
                  data_add(count[j],names[i+1],noo,exceptee[i+1]);
                 a[i+1]=0;
                 }
                 else{
                     
                if(b[i+1]>80){noo=Integer.toString(c[i+1]+80)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>60)&&(b[i+1]<=80)){noo=Integer.toString(c[i+1]+60)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>40)&&(b[i+1]<=60)){noo=Integer.toString(c[i+1]+40)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>20)&&(b[i+1]<=40)){noo=Integer.toString(c[i+1]+20)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 String noo2="";
                 if(b[p]>80){noo2=Integer.toString(c[p]+80)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>60)&&(b[p]<=80)){noo2=Integer.toString(c[p]+60)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>40)&&(b[p]<=60)){noo2=Integer.toString(c[p]+40)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>20)&&(b[p]<=40)){noo2=Integer.toString(c[p]+20)+"-"+Integer.toString(c[p]+b[p]-1);}
                 
                 // System.out.print(" "+count[j]+" "+names[i+1]+":"+noo+" "+count[j]+" "+names[p]+":"+noo2);
                  data_add(count[j],names[i+1],noo,exceptee[i+1]);
                   data_add(count[j],names[p],noo2,exceptee[p]);
                 left-=a[p];
                  int temp3=0;
                 a[i+1]=0;a[p]=0;
                 p=0;
                 for(p=i+2; p<k;p++)
                 {if (a[p]!=0)
                     if(a[p]<=left){
                         temp3=a[p];
                     break;
                     }
                 }
                 if(temp3!=0){
                     if(b[p]>80){noo=Integer.toString(c[p]+80)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>60)&&(b[p]<=80)){noo=Integer.toString(c[p]+60)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>40)&&(b[p]<=60)){noo=Integer.toString(c[p]+40)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>20)&&(b[p]<=40)){noo=Integer.toString(c[p]+20)+"-"+Integer.toString(c[p]+b[p]-1);}
                 //System.out.print(" "+count[j]+" "+names[p]+":"+noo);
                  data_add(count[j],names[p],noo,exceptee[p]);
                 a[p]=0;
                 }
                 }
                 a=sorta(a,k,names,b,c,exceptee);
                // System.out.println();
                 j++;
                 mcount++;
                 }
             else if(a[i]<20&&a[i+1]<20&&a[i+1]>0)
             {
                int total=40,temp2=0,tp=9;
                 int left1=total-20-a[i];
                 int left2=total-20-a[i+1];
                 int left=0;
                 if(left1>left2){left=left1;tp=0;}else{ left=left2;tp=1;}
                 //System.out.println("left:"+left);
                 int p=0;
                 for(p=i+2; p<k;p++)
                 {
                     if(a[p]<=left){
                         temp2=a[p];
                     break;
                     }
                 }
                 if(temp2==0){
                     String noo="",noo2="";
                     if(b[i+1]>80){noo=Integer.toString(c[i+1]+80)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>60)&&(b[i+1]<=80)){noo=Integer.toString(c[i+1]+60)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>40)&&(b[i+1]<=60)){noo=Integer.toString(c[i+1]+40)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>20)&&(b[i+1]<=40)){noo=Integer.toString(c[i+1]+20)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 if(b[i]>80){noo2=Integer.toString(c[i]+80)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>60)&&(b[i]<=80)){noo2=Integer.toString(c[i]+60)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>40)&&(b[i]<=60)){noo2=Integer.toString(c[i]+40)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>20)&&(b[i]<=40)){noo2=Integer.toString(c[i]+20)+"-"+Integer.toString(c[i]+b[i]-1);}
                     
                      
                      //System.out.print(" "+count[j]+" "+names[i]+":"+noo2);
                       data_add(count[j],names[i],noo2,exceptee[i]);
                 //System.out.print(" "+count[j]+" "+names[i+1]+":"+noo);
                  data_add(count[j],names[i+1],noo,exceptee[i+1]);
                 a[i+1]=0;
                 a[i]=0;
                 }
                 else{
                     String noo="",noo2="",noo3="";
                      if(b[i+1]>80){noo=Integer.toString(c[i+1]+80)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>60)&&(b[i+1]<=80)){noo=Integer.toString(c[i+1]+60)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>40)&&(b[i+1]<=60)){noo=Integer.toString(c[i+1]+40)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                 else if((b[i+1]>20)&&(b[i+1]<=40)){noo=Integer.toString(c[i+1]+20)+"-"+Integer.toString(c[i+1]+b[i+1]-1);}
                if(b[i]>80){noo2=Integer.toString(c[i]+80)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>60)&&(b[i]<=80)){noo2=Integer.toString(c[i]+60)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>40)&&(b[i]<=60)){noo2=Integer.toString(c[i]+40)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>20)&&(b[i]<=40)){noo2=Integer.toString(c[i]+20)+"-"+Integer.toString(c[i]+b[i]-1);}
                      
                     if(b[p]>80){noo3=Integer.toString(c[p]+80)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>60)&&(b[p]<=80)){noo3=Integer.toString(c[p]+60)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>40)&&(b[p]<=60)){noo3=Integer.toString(c[p]+40)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>20)&&(b[p]<=40)){noo3=Integer.toString(c[p]+20)+"-"+Integer.toString(c[p]+b[p]-1);}
                 
                        //System.out.print(" "+count[j]+" "+names[i]+":"+noo);
                         data_add(count[j],names[i],noo,exceptee[i]);
                  //System.out.print(" "+count[j]+" "+names[i+1]+":"+noo2+" "+count[j]+" "+names[p]+":"+noo3);
                  data_add(count[j],names[i+1],noo2,exceptee[i+1]);
                   data_add(count[j],names[p],noo3,exceptee[p]);
                  a[i+1]=0;a[p]=0;a[i]=0;
                  if(tp==0){left-=a[p];}else{left2-=a[p];}
                  if(left1>left2){left=left1;tp=0;}else{ left=left2;tp=1;}
                  int temp3=0;
                 p=0;
                 for(p=i+2; p<k;p++)
                 {if (a[p]!=0)
                     if(a[p]<=left){
                         temp3=a[p];
                     break;
                     }
                 }
                 if(temp3!=0){
                     
                     if(b[p]>80){noo3=Integer.toString(c[p]+80)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>60)&&(b[p]<=80)){noo3=Integer.toString(c[p]+60)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>40)&&(b[p]<=60)){noo3=Integer.toString(c[p]+40)+"-"+Integer.toString(c[p]+b[p]-1);}
                 else if((b[p]>20)&&(b[p]<=40)){noo3=Integer.toString(c[p]+20)+"-"+Integer.toString(c[p]+b[p]-1);}
                 
                 //System.out.print(" "+count[j]+" "+names[p]+":"+noo3);  
                  data_add(count[j],names[p],noo3,exceptee[p]);
                 a[p]=0;
                 }
                 }
                 a=sorta(a,k,names,b,c,exceptee);/*System.out.println();*/ j++;mcount++;
                 
}else if(a[i]>0&&a[i+1]==0){
    String noo2="";
    if(b[i]>80){noo2=Integer.toString(c[i]+80)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>60)&&(b[i]<=80)){noo2=Integer.toString(c[i]+60)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>40)&&(b[i]<=60)){noo2=Integer.toString(c[i]+40)+"-"+Integer.toString(c[i]+b[i]-1);}
                 else if((b[i]>20)&&(b[i]<=40)){noo2=Integer.toString(c[i]+20)+"-"+Integer.toString(c[i]+b[i]-1);}
    data_add(count[j],names[i],noo2,exceptee[i]); j++;mcount++;
}}
    }
        // TODO
        
    @FXML
    public void data_add(int count, String name, String noo,String exceptee ) throws SQLException{
    seating tp=new seating(count,name,noo,exceptee);
    data.add(tp);
      /*Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );*/
        Statement stm;
         stm = conn.createStatement();
    String sql2="insert into schema1.seating values("+count+",'"+name+"','"+noo+"',0,'"+exceptee+"')";
    
    
    try{
        stm.executeUpdate(sql2);
    }catch(Exception e)
    {
        e.printStackTrace();
    }
    
    
    
    
    
    }
    
    public int generatecount() throws SQLException{
        int[] counts=new int[1000];
        for(int i=0;i<1000;i++)counts[i]=0;
        mfunction(counts);
        return mcount;
    }
    
     public static int[] sorta(int []a, int n, String[] names,int[] b, int[] c,String[] exceptee){
        String temp1,temp4;
        int temp,temp2,temp3;
        for (int i = 0; i < n; i++) 
        {
            for (int j = i + 1; j < n; j++) 
            {
                if (a[i] < a[j]) 
                {
                    temp4=exceptee[i];
                    temp3=c[i];
                    temp2=b[i];
                    temp1=names[i];
                    temp = a[i];
                    exceptee[i]=exceptee[j];
                    c[i]=c[j];
                    b[i]=b[j];
                    names[i]=names[j];
                    a[i] = a[j];
                    exceptee[j]=temp4;
                    c[j]=temp3;
                    b[j]=temp2;
                    names[j]=temp1;
                    a[j] = temp;
                }
            }
        }
        return a;
    }
  }
    

