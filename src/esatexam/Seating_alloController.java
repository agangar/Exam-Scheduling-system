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
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import javafx.application.Platform;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * FXML Controller class
 *
 * @author Harsh
 */
public class Seating_alloController{
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
    private TableColumn<tabledata,Integer> Desk0;
    @FXML
    private TableColumn<tabledata,Integer> Desk1;  
     @FXML
    private TableColumn<tabledata,Integer> Desk2;
    @FXML
    private TableColumn<tabledata,Integer> Desk3;  
     @FXML
    private TableColumn<tabledata,Integer> col0;
    @FXML
    private TableColumn<tabledata,Integer> col1;  
     @FXML
    private TableColumn<tabledata,Integer> col2;
    @FXML
    private TableColumn<tabledata,Integer> col3;  
     @FXML
    private TableColumn<tabledata,Integer> col4;
    @FXML
    private TableColumn<tabledata,Integer> col5;  
     @FXML
    private TableColumn<tabledata,Integer> col6;
    @FXML
    private TableColumn<tabledata,Integer> col7;  
      @FXML
    private TableColumn<tabledata,String> row0;  
        @FXML
    private TableColumn<tabledata,String> row1;  
          @FXML
    private TableColumn<tabledata,String> row2;  
            @FXML
    private TableColumn<tabledata,String> row3;  
            
     ObservableList<seating> data2 = FXCollections.observableArrayList();
    private final Connection conn;
    /**
     * Initializes the controller class.
     */
     public Seating_alloController() throws SQLException {
        this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
        );
    }
     @FXML
   public void selecteds() throws SQLException{
        ObservableList<tabledata> data = FXCollections.observableArrayList();
        Desk0.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("a"));
         row0.setCellValueFactory(new PropertyValueFactory<tabledata,String>("row0"));
        col0.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("b"));
        col1.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("c"));
        Desk1.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("d"));
         row1.setCellValueFactory(new PropertyValueFactory<tabledata,String>("row1"));
        col2.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("e"));
        col3.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("f"));
        Desk2.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("g"));
         row2.setCellValueFactory(new PropertyValueFactory<tabledata,String>("row2"));
        col4.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("h"));
        col5.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("i"));
        Desk3.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("j"));
         row3.setCellValueFactory(new PropertyValueFactory<tabledata,String>("row3"));
        col6.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("k"));
        col7.setCellValueFactory(new PropertyValueFactory<tabledata,Integer>("l"));
        
       
      // Desk0.setCellValueFactory(new PropertyValueFactory<>(""));
       seating person = (seating) table.getSelectionModel().getSelectedItem();
       Block.setText(Integer.toString(person.getRoom_no()));
       Class.setText(person.getClassname());
       Roll_no.setText(person.getNos());
       xcept.setText(person.getExceptee());
       int block=person.getRoom_no();
       String[] classname=new String[3];
       String[] Except1=new String[3];
       
        Statement stm;
        int[] start=new int[3];
        int[] noe=new int[3];
        int[] end=new int[3];
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
        System.out.print(start[1]);
         
            int[][] except1 = new int[3][5];
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


System.out.print(count+" "+ tp[1]+" ");
for(int k=0; k<count;k++)
{
    //System.out.print(tp[k]);
    except1[r][k]=Integer.parseInt(tp[k]);
  
}
 }           
        for(int j=10;j>0;j--){
            int a=j;
            int lclass=0;
            if(i>1){
                if((end[1]-start[1])<(end[0]-start[0])){
                    lclass=1;
                }
            }
            System.out.print(lclass);
            
            
            int b=start[0]+j-1;
          for(int q=0;q<noe[0];q++){
              if(except1[0][q]>=start[0])
              if((b>=except1[0][q])&&(b<=end[0])){
                  
                  b++;
              }
          }
          if(b>end[0]){
              if(i>2 && lclass==0){
                  int temp=b-end[0];
                  b=start[2]+temp-1;
                   for(int q=0;q<noe[2];q++){
              if(except1[2][q]>=start[2])
              if((b>=except1[2][q])&&(b<=end[2])){
                  
                  b++;
              }
                  if(b>end[2])b=0;
              }
              
              }else b=0;
          }
          
          
          
          
          int c=start[1]+j-1;
            for(int q=0;q<noe[1];q++){
                 if(except1[1][q]>=start[1])
              if((c>=except1[1][q])&&(c<=end[1])){
                  c++;
              }
          }
             if(c>end[1]){
                  if(i>2 && lclass==1){
                  int temp=c-end[1];
                  c=start[2]+temp-1;
                   for(int q=0;q<noe[2];q++){
              if(except1[2][q]>=start[2])
              if((c>=except1[2][q])&&(c<=end[2])){
                  
                  c++;
              }
                  if(c>end[2])c=0;
              }
              
              }else c=0;}
            
            
            
            
            
            int d=21-j;
            int e=start[0]+20-j;
            for(int q=0;q<noe[0];q++){
                   if(except1[0][q]>=start[0])
              if((e>=except1[0][q])&&(e<=end[0])){
                  e++;
              }
          }
            if(e>end[0]){   
                if(i>2 && lclass==0){
                  int temp=e-end[0];
                  e=start[2]+temp-1;
                   for(int q=0;q<noe[2];q++){
              if(except1[2][q]>=start[2])
              if((e>=except1[2][q])&&(e<=end[2])){
                  
                  e++;
              }
                  if(e>end[2])e=0;
              }
              
              }else e=0;}  
            
            
            
             int f=start[1]+20-j;
             for(int q=0;q<noe[1];q++){
                    if(except1[1][q]>=start[1])
              if((f>=except1[1][q])&&(f<=end[1])){
                  f++;
              }
          }
             if(f>end[1]){
                  if(i>2 && lclass==1){
                  int temp=f-end[1];
                  f=start[2]+temp-1;
                   for(int q=0;q<noe[2];q++){
              if(except1[2][q]>=start[2])
              if((f>=except1[2][q])&&(f<=end[2])){
                  
                  f++;
              }
                  if(f>end[2])f=0;
              }
              
              }else f=0;}
             
             
            int g=20+j;
             int h=start[0]+j-1+20;
              for(int q=0;q<noe[0];q++){
                  if(except1[0][q]>=start[0])
              if((h>=except1[0][q])&&(h<=end[0])){
                  h++;
              }
          }
             if(h>end[0]){
                 if(i>2 && lclass==0){
                  int temp=h-end[0];
                  h=start[2]+temp-1;
                   for(int q=0;q<noe[2];q++){
              if(except1[2][q]>=start[2])
              if((h>=except1[2][q])&&(h<=end[2])){
                  
                  h++;
              }
                  if(h>end[2])h=0;
              }
              
              }else h=0;}
             
          int m=start[1]+j-1+20;
            for(int q=0;q<noe[1];q++){
                 if(except1[1][q]>=start[1])
              if((m>=except1[1][q])&&(m<=end[1])){
                  m++;
              }
          }
              if(m>end[1]){
                  if(i>2 && lclass==1){
                  int temp=m-end[1];
                  m=start[2]+temp-1;
                   for(int q=0;q<noe[2];q++){
              if(except1[2][q]>=start[2])
              if((m>=except1[2][q])&&(m<=end[2])){
                  
                  m++;
              }
                  if(m>end[2])m=0;
              }
              
              }else m=0;}
            
            
            int n=41-j;
              int k=start[0]+40-j;
               for(int q=0;q<noe[0];q++){
                   if(except1[0][q]>=start[0])
              if((k>=except1[0][q])&&(k<=end[0])){
                  k++;
              }
          }
              if(k>end[0]){
                  if(i>2 && lclass==0){
                  int temp=k-end[0];
                  k=start[2]+temp-1;
                   for(int q=0;q<noe[2];q++){
              if(except1[2][q]>=start[2])
              if((k>=except1[2][q])&&(k<=end[2])){
                  
                  k++;
              }
                  if(k>end[2])k=0;
              }
              
              }else k=0;}
              
              
              
          int l=start[1]+40-j;
          for(int q=0;q<noe[1];q++){
                 if(except1[1][q]>=start[1])
              if((l>=except1[1][q])&&(l<=end[1])){
                  l++;
              }
          }
      if(l>end[1]){
                  if(i>2 && lclass==1){
                  int temp=l-end[1];
                  l=start[2]+temp-1;
                   for(int q=0;q<noe[2];q++){
              if(except1[2][q]>=start[2])
              if((l>=except1[2][q])&&(l<=end[2])){
                  
                  l++;
              }
                  if(l>end[2])l=0;
              }
              
              }else l=0;}
            
            tabledata t=new tabledata(a,"",b,c,d,"",e,f,g,"",h,m,n,"",k,l);
            data.add(t);
        }
        table1.setItems(data);
        col0.setText(classname[0]);
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
       }
   }
   
   
   public void excel() throws IOException{
         Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);
        for (int j = 0; j < table.getColumns().size(); j++) {
            
            row.createCell(j).setCellValue(table.getColumns().get(j).toString());
        }
 

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
           seating person=(seating) table.getItems().get(i);
                
                    row.createCell(0).setCellValue(person.getRoom_no());
                    row.createCell(1).setCellValue(person.getClassname());
                    row.createCell(2).setCellValue(person.getNos());
                     row.createCell(3).setCellValue(person.getExceptee());
                    if(i%2!=0)
                    spreadsheet.addMergedRegion(new CellRangeAddress(i,i+1,0,0));
                       
            
        }
        
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();

       // Platform.exit();
   }
    @FXML
    public void initialize() throws SQLException {
         //ObservableList<seating> data = FXCollections.observableArrayList();
        Block_no.setCellValueFactory(new PropertyValueFactory<seating,Integer>("room_no"));
          Branch.setCellValueFactory(new PropertyValueFactory<seating,Integer>("classname"));
        Roll.setCellValueFactory(new PropertyValueFactory<seating, String>("nos"));
        exceptee1.setCellValueFactory(new PropertyValueFactory<seating, String>("exceptee"));
        System.out.println(table.getColumns().get(0).toString());
 
        
 
       mfunction(Variables.count);
    table.setItems(data2);
    }    
    public void mfunction(int[] count) throws SQLException{
        
    
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
            int k=0;
            int yearno=Variables.yearlist.size();
            int[] c=new int[x];
            //int[] count=new int[x];
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
                System.out.println(result.getInt("Room_no"));
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
                c[k]=result.getInt("Starting_roll");
                //count[k]=result.getInt("Room_no");
                k++;    
    }}
    String noo=" ";
    int j=0;
     for(int i=0;i<12;i++){
             
         while(a[i]>=40&&a[i+2]>=40){
             if(a[i]>=40&&a[i+2]>=40)
         {
             String d=Integer.toString(c[i]); 
             String e=Integer.toString(c[i]+39); 
             noo=(d+"-").concat(e);
             a[i]-=40;
             a[i+2]-=40;
             //System.out.print(" "+count[j]+" "+names[i]+":40");
            data_add(count[j],names[i],noo,exceptee[i]);
            c[i]+=40;
                  
            d=Integer.toString(c[i+2]); 
            e=Integer.toString(c[i+2]+39); 
                 noo=(d+"-").concat(e);
                 //System.out.print(" "+count[j]+" "+names[i+2]+":40"); 
            data_add(count[j],names[i+2],noo,exceptee[i+2]);
             c[i+2]+=40;
             j++;mcount++;
             //System.out.println();
         
             }//////////////////////////////
         }
         if(i==1||i==5)i+=2;
         }
        a=sorta(names,a,x,c,exceptee);
         
      //  for(int i=0;i<x;i++){ System.out.println(names[i]+": "+a[i]);}
         for(int i=0;i<x;i++){   
        while(a[i]>0){
             if(a[i]>=40&&a[i+1]>=40)
         {   
             String d=Integer.toString(c[i]); 
               String e=Integer.toString(c[i]+39); 
                 noo=(d+"-").concat(e);
             a[i]-=40;
             c[i]+=40;
             //System.out.print(" "+count[j]+" "+names[i]+":40");
             data_add(count[j],names[i],noo,exceptee[i]);
              
             
            d=Integer.toString(c[i+1]); 
            e=Integer.toString(c[i+1]+39); 
                 noo=(d+"-").concat(e);
             a[i+1]-=40;
             c[i+1]+=40;
             
                 //System.out.print(" "+count[j]+" "+names[i+1]+":40"); 
             data_add(count[j],names[i+1],noo,exceptee[i+1]);
             
           
                j++;mcount++;
             //System.out.println();
             a=sorta(names,a,x,c,exceptee);
         }
            else if(a[i]>=40&&a[i+1]<40)
             {
                int total=80,temp2=0;
              String d=Integer.toString(c[i]); 
               String e=Integer.toString(c[i]+39); 
                 noo=(d+"-").concat(e);
                a[i]=a[i]-40;
                c[i]+=40;
                 //System.out.print(" "+count[j]+" "+names[i]+":40");
            data_add(count[j],names[i],noo,exceptee[i]);
              
                 total=total-40;
                 int left=total-a[i+1];
                 int p=0;
                 for(p=i+2; p<x;p++)
                 {
                     if(a[p]<=left){
                         temp2=a[p];
                     break;
                     }
                 }
                 if(temp2==0){
                 //System.out.print(" "+count[j]+" "+names[i+1]+":"+c[i+1]);
                 
                 noo=Integer.toString(c[i+1])+"-"+Integer.toString(c[i+1]+a[i+1]-1);
             data_add(count[j],names[i+1],noo,exceptee[i+1]);
             
                 a[i+1]=0;
                 }
                 else{
                  //System.out.print(" "+count[j]+" "+names[i+1]+":"+a[i+1]+" "+count[j]+" "+names[p]+":"+a[p]);
                 noo=Integer.toString(c[i+1])+"-"+Integer.toString(c[i+1]+a[i+1]-1);
                  data_add(count[j],names[i+1],noo,exceptee[i+1]);
            noo=Integer.toString(c[p])+"-"+Integer.toString(c[p]+a[p]-1);
            data_add(count[j],names[p],noo,exceptee[p]);
            
                  a[i+1]=0;a[p]=0;
                 }
                 a=sorta(names,a,x,c,exceptee);
                 System.out.println();
                 j++;mcount++;
                 }
             else if(a[i]<40&&a[i+1]<40&&a[i+1]>0)
             {
                int temp2=0;
                 int left1=40-a[i];
                 int left2=40-a[i+1];
                 int left=0;
                 if(left1>left2)left=left1;else left=left2;
                 System.out.println("left:"+left+"Count"+count[j]+"a[i]"+a[i]+"a[i+1]"+a[i+1]);
                 int p=0;
                 for(p=i+2; p<x;p++)
                 {
                     if(a[p]<=left){
                         temp2=a[p];
                     break;
                     }
                 }
                 if(temp2==0){
                     //System.out.print(" "+count[j]+" "+names[i]+":"+c[i]);
                // System.out.print(" "+count[j]+" "+names[i+1]+":"+c[i+1]);
                 noo=Integer.toString(c[i])+"-"+Integer.toString(c[i]+a[i]-1);
                data_add(count[j],names[i],noo,exceptee[i]);
            
            noo=Integer.toString(c[i+1])+"-"+Integer.toString(c[i+1]+a[i+1]-1);
            data_add(count[j],names[i+1],noo,exceptee[i+1]);
            
                 
                 
                 a[i+1]=0;
                 a[i]=0;
                 }
                 else{
                        //System.out.print(" "+count+" "+names[i]+":"+a[i]);
                 // System.out.print(" "+count+" "+names[i+1]+":"+a[i+1]+" "+count+" "+names[p]+":"+a[p]);
                   noo=Integer.toString(c[i])+"-"+Integer.toString(c[i]+a[i]-1);
           data_add(count[j],names[i],noo,exceptee[i]);
                 noo=Integer.toString(c[i+1])+"-"+Integer.toString(c[i+1]+a[i+1]-1);
            data_add(count[j],names[i+1],noo,exceptee[i+1]);
            noo=Integer.toString(c[p])+"-"+Integer.toString(c[p]+a[p]-1);
            data_add(count[j],names[p],noo,exceptee[p]);
          
            a[i+1]=0;a[p]=0;a[i]=0;
                 }
                 a=sorta(names,a,x,c,exceptee);//System.out.println(); 
                 j++;mcount++;
                 
}else if(a[i]>0&&a[i+1]==0){
    noo=Integer.toString(c[i])+"-"+Integer.toString(c[i]+a[i]-1);
    data_add(count[j],names[i],noo,exceptee[i]);
    a[i]=0;  
    j++;mcount++;
}}}
    }
     public int generatecount() throws SQLException{
        int[] fcount=new int[100];
        for(int i=0;i<99;i++) fcount[i]=0;
         mfunction(fcount);
        return mcount;
    }
      @FXML
    public void data_add(int count, String name, String noo,String exceptee ) throws SQLException{
    seating tp=new seating(count,name,noo,exceptee);
    data2.add(tp);
      
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

public static int[] sorta(String [] names,int []a, int n,int[] c, String[] exceptee){
        String temp1,temp3;
        int temp,temp2;
        for (int i = 0; i < n; i++) 
        {
            //System.out.println(names[i]+"   "+c[i]);
            for (int j = i + 1; j < n; j++) 
            {
                if (a[i] < a[j]) 
                {
                    temp3=exceptee[i];
                    temp2=c[i];
                    temp1=names[i];
                    temp = a[i];
                    exceptee[i]=exceptee[j];
                    c[i]=c[j];
                    names[i]=names[j];
                    a[i] = a[j];
                    exceptee[j]=temp3;
                    c[j]=temp2;
                    names[j]=temp1;
                    a[j] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) 
        {
        System.out.println("names ai"+names[i]+a[i]);
        }
       
        
        //System.out.println();
        return a;
    }
        // TODO
    }    
    

