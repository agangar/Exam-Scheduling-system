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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author victim
 */



import java.util.Random;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author victim
 */
public class Teacherallocation {
    Connection conn;
    int maxdu=1;
    
    
    @FXML
    TableView table_teacher;
     ObservableList<ObservableList> csvData = FXCollections.observableArrayList();
     
      public Teacherallocation() throws SQLException{
        this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
        );
    }
      //List<String> rows = new ArrayList<String>();

    public String[][] allocateDuty(String a[][],int m,int n,int j,int num, String[][] names2) throws SQLException
    {
       
        Statement stm3,stm;
        stm=conn.createStatement();
        stm3=conn.createStatement();
        
        int select = 0;
         //need to get the value from the database
        Random randomGenerator = new Random(); 
        boolean selected = false;
        int count=0;
        while(selected == false)
        {   
            count++;
            //System.out.println("COUNT COUNT COUNT"+count);
            if(count==10000){
                System.out.println("COUNT COUNT COUNT"+count);
                maxdu++;
            }
            j=randomGenerator.nextInt(m);
            select = randomGenerator.nextInt(n);
            ResultSet ct2=stm.executeQuery("SELECT * from Schema1.duty where Name='"+names2[j][select]+"';");
                ct2.next();
            if( (!a[j][select].equals("None")) && ct2.getInt("numofdut")<=maxdu && ((a[j][select]).indexOf(Integer.toString(num))==-1 ))
            {
                a[j][select]+= Integer.toString(num);
                a[j][select]+=",";
                String noo=ct2.getString("duty").concat(a[j][select]);
                System.out.println("heyy:"+noo);
                int nod=ct2.getInt("numofdut");
                        nod++;
                        int flag=0;
                        
                stm3.executeUpdate("Update Schema1.duty set duty='"+noo+"', numofdut="+nod+", flag="+flag+" where Name='"+names2[j][select]+"'");
                selected = true;
            }
            
        }
        
       
        return a;
    }
     public String[][] allocateDutySenior(String a[][],int m,int n,int j,int num)
     {
        int select = 0;
        int max = 3; 
        Random randomGenerator = new Random(); 
        boolean selected = false;
        
        while(selected == false)
        {
         j=randomGenerator.nextInt(m);
            select = randomGenerator.nextInt(n);
           
            if( (!a[j][select].equals("None")) && (a[j][select].length()<(max*2)) && ((a[j][select]).indexOf(Integer.toString(num))==-1 ) )
            {
                a[j][select]+= Integer.toString(num);
                   a[j][select]+=",";
                
                
                selected = true;
            }
            
        }
     
        return a;
    }

   
    @FXML    
    public int juniorTeacher(int numberofRoomUsed,int numberofJuniorteacher,String[] names,String[] dateString, int numberOfDays,int[] noc) throws SQLException
    {
      /*  int sum=0;
        for(int i=0;i<numberOfDays;i++){
        sum+=noc[i];
    }
    int total=numberofJuniorteacher;
    int min=3;
    //int min=(sum*2)/numberofJuniorteacher;
   // int minno=(min+1)*numberofJuniorteacher-(sum*2);
    for(int i = 0;i<numberofJuniorteacher;i++)
        {
        System.out.println(names[i]);
        }
    for(int i = 0;i<numberOfDays;i++)
        {
            List<Integer> selectedjun = new ArrayList<Integer>();
            for(int j = 0; j < (noc[i]*2); j++)
            {
    Statement stm3,stm;
        stm=conn.createStatement();
        stm3=conn.createStatement();
        
        int select = 0;
         //need to get the value from the database
        Random randomGenerator = new Random(); 
        boolean selected = false;
        int count=0;
        while(selected == false)
        {   
            count++;
            //System.out.println("COUNT COUNT COUNT"+count);
           
            int m=randomGenerator.nextInt(numberofJuniorteacher);
            ResultSet ct2=stm.executeQuery("SELECT * from Schema1.duty where Name='"+names[m]+"';");
                ct2.next();
                
            if( ct2.getString("duty").indexOf(Integer.toString(i))==-1 && ct2.getInt("numofdut")<min )
            {
                String temp= Integer.toString(i);
                temp+=",";
                String noo=ct2.getString("duty").concat(temp);
                System.out.println("heyy:"+noo);
                int nod=ct2.getInt("numofdut");
                        nod++;
                        int flag=0;
                selectedjun.add(m);
                stm3.executeUpdate("Update Schema1.duty set duty='"+noo+"', numofdut="+nod+", flag="+flag+" where Name='"+names[m]+"'");
                selected = true;
            }
             if(count==10000){
               ResultSet ct3=stm.executeQuery("SELECT * from Schema1.duty where numofdut<"+min+";");
               String temp2="";
               
               if(ct3.next()){
                   
               if( ct3.getString("duty").indexOf(Integer.toString(i))==-1){
                temp2= Integer.toString(i);
                temp2+=",";
                String noo=ct3.getString("duty").concat(temp2);
                System.out.println("heyy:"+noo);
                int nod=ct3.getInt("numofdut");
                        nod++;
                        int flag=0;
                 selectedjun.add(ct3.getInt("id"));       
                stm3.executeUpdate("Update Schema1.duty set duty='"+noo+"', numofdut="+nod+", flag="+flag+" where Name='"+ct3.getString("name")+"'");
                selected = true;
            }else{
                 int flag=0,p=0;
                 while(flag==0){
                 p=randomGenerator.nextInt(numberofJuniorteacher);
                 if(!selectedjun.contains(p)){
                     flag=1;
                 }
                 }
                 ResultSet ct4=stm.executeQuery("SELECT * from Schema1.duty where Name='"+names[p]+"';");
                 ct4.next();
                 String noo2=ct4.getString("duty");
                 int length=noo2.length();
                 int c=Integer.parseInt(noo2.substring(length-2,length-1));
                 noo2=noo2.substring(0,length-2) + Integer.toString(i) + noo2.substring(length-1);
                 ResultSet ct8=stm.executeQuery("SELECT * from Schema1.duty where numofdut<"+min+";");
                 ct8.next();
                 String dutys=ct8.getString("duty");
                 if(dutys.indexOf(Integer.toString(c))!=-1)
                 {
                 stm3.executeUpdate("Update Schema1.duty set duty='"+noo2+"' where Name='"+names[p]+"'");
                 String temp= Integer.toString(c);
                temp+=",";
                ResultSet ct7=stm.executeQuery("SELECT * from Schema1.duty where numofdut<"+min+";");
                ct7.next();
                String noo=ct7.getString("duty").concat(temp);
                System.out.println("heyy:"+noo);
                int nod=ct7.getInt("numofdut");
                        nod++;
                selectedjun.add(ct7.getInt("id"));
                stm3.executeUpdate("Update Schema1.duty set duty='"+noo+"', numofdut="+nod+", flag="+flag+" where Name='"+ct7.getString("name")+"'");
                selected = true;
                 }
            }
               }else{
                   ResultSet ct5=stm.executeQuery("SELECT * from Schema1.duty where id="+total+";");
                   ct5.next();
                   String dutys=ct5.getString("duty");
                   if(!selectedjun.contains(total)){
                   String temp= Integer.toString(i);
                temp+=",";
                String noo=ct5.getString("duty").concat(temp);
                System.out.println("heyy:"+noo);
                int nod=ct5.getInt("numofdut");
                        nod++;
                        int flag=0;
                selectedjun.add(ct5.getInt("id"));
                stm3.executeUpdate("Update Schema1.duty set duty='"+noo+"', numofdut="+nod+", flag="+flag+" where Name='"+ct5.getString("name")+"'");
                selected = true;
                   }else{
                       int flag=0,p=0;
                 while(flag==0){
                 p=randomGenerator.nextInt(numberofJuniorteacher);
                 if(!selectedjun.contains(p) && p!=(total-1)){
                     flag=1;
                 }
                 }
                 ResultSet ct4=stm.executeQuery("SELECT * from Schema1.duty where Name='"+names[p]+"';");
                 ct4.next();
                 String noo2=ct4.getString("duty");
                 int length=noo2.length();
                  int c=Integer.parseInt(noo2.substring(length-2,length-1));
                 noo2=noo2.substring(0,length-2) + Integer.toString(i) + noo2.substring(length-1);
                 if(dutys.indexOf(Integer.toString(c))!=-1)
                 {
                 stm3.executeUpdate("Update Schema1.duty set duty='"+noo2+"' where Name='"+names[p]+"'");
                 String temp= Integer.toString(c);
                temp+=",";
                ResultSet ct6=stm.executeQuery("SELECT * from Schema1.duty where id="+total+";");
                   ct6.next();
                String noo=ct6.getString("duty").concat(temp);
                System.out.println("heyy:"+noo);
                int nod=ct6.getInt("numofdut");
                        nod++;
                selectedjun.add(ct6.getInt("id"));
                stm3.executeUpdate("Update Schema1.duty set duty='"+noo+"', numofdut="+nod+", flag="+flag+" where Name='"+ct6.getString("name")+"'");
                selected = true;
                 }  
                 }
               total--;
               
               
               
               
               
               }
             
             
             
             
             
            
        }
            }
        }
        }
    
    */
    
ObservableList<teaching> data=FXCollections.observableArrayList();
        int m = 0,n=0;
        String[][] duty = null;
        String names2[][]=null;
        for(int k = 0 ;k < numberOfDays ;k++)
        {
            
        m = noc[k]*2;
        float value;
        if(m==0){
            m=2;
        }
        n = numberofJuniorteacher / m;
        int nRem = numberofJuniorteacher % m;
        if(n>0)
        {
            n++;
        }
        
        int arrayJuniorTeacher[][] = new int[m][n];
        duty= new String [m][n];
        int x = 1,o=0;
        names2=new String[m][n];
        for(int i = 0;i<n;i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(x>numberofJuniorteacher)
                {
                    arrayJuniorTeacher[j][i] = 999;
                    duty[j][i] = "None";
                    names2[j][i]="none";
                   
                }
                else
                {
                    arrayJuniorTeacher[j][i] = x++;
                    duty[j][i] = "";
                    names2[j][i]=names[o];
                    o++;
                }
            }
        }
        for(int i = 0;i<m;i++)
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(names2[i][j]+"  |");
            }
            System.out.println();
        }
      
            System.out.print("noc:"+m);
            for(int j = 0;j<m;j++)
            {   
                
                duty = allocateDuty(duty,m,n,j,k,names2);
               
            }
            
                    System.out.println("loop");
           

          
        }
             
        Statement stm;
        stm=conn.createStatement();
             ResultSet ct2=stm.executeQuery("SELECT * from Schema1.duty;");
                while(ct2.next()){
                    ObservableList<String> row = FXCollections.observableArrayList();
                   row.clear();
                   row.add(ct2.getString("Name"));
                   String dutyp=ct2.getString("duty");
                   for(int i=0;i<numberOfDays;i++){
                       if(dutyp.indexOf(Integer.toString(i))==-1){
                               row.add("-- ");
                           }
                           else{
                               row.add("JS");
                           }
                   }
                   row.add(Integer.toString(ct2.getInt("numofdut")));
                   csvData.add(row);
                    
                    
                }
           
       
       
       
       return 0; 
       
    }
  
    public int seniorTeacher(int numberofRoomUsed,int numberofSeniorteacher,int handleClassroom, String[] names, int numberOfDays)
    {
        int m = numberofRoomUsed/handleClassroom;
        int mRem = numberofRoomUsed%handleClassroom;
        if(mRem>0)
        {
            m++;
        }
       
        int n = numberofSeniorteacher /m;
        int nRem = numberofSeniorteacher % m;
        if(nRem>0)
        {
            n++;
        }
        int arraySeniorTeacher[][] = new int[m][n];
        String seniorduty[][] = new String [m][n];
        String[][] names3=new String[m][n];
        int x = 1;
        int o = 0;
        for(int i = 0;i<n;i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(x>numberofSeniorteacher)
                {
                    arraySeniorTeacher[j][i] = 999;
                    seniorduty[j][i] = "None";
                     names3[j][i]="none";
                }
                else
                {
                    arraySeniorTeacher[j][i] = x++;
                    seniorduty[j][i] = "";
                    names3[j][i]=names[o];
                    o++;
                }
            }
        }
        //int numberOfDays = 4;//retrive no f subject or no of days exam to be conducted
        for(int i = 0 ;i < numberOfDays ;i++)
        {
            for(int j = 0;j<m;j++)
            {
                seniorduty = allocateDutySenior(seniorduty,m,n,j,i);
               
            }
           /* System.out.println("after iteeration"+ i);
          for(int k = 0;k<m;k++)
            {
                
                for(int l = 0;l<n ;l++)
                {
                    System.out.print("duty "+seniorduty[k][l]+" ");
                     System.out.print(" "+names3[k][l]);
                }
                System.out.println();
            }
        */
        }
        
          String[] duties=new String[numberOfDays];
       
            for(int k = 0;k<m;k++)
            {
              
                for(int l = 0;l<n ;l++)
                {  if(!names3[k][l].equals("none")){
                   ObservableList<String> rowss = FXCollections.observableArrayList();
                   rowss.clear(); 
                   int numberofduty=0;
                   rowss.add(names3[k][l]);
                       for(int p=0;p<numberOfDays;p++){
                           if(seniorduty[k][l].indexOf(Integer.toString(p))==-1){
                               rowss.add("-- ");
                           }
                           else{
                               rowss.add("SS");
                               numberofduty++;
                           }
                       }
                       
                  rowss.add(Integer.toString(numberofduty));
                  /*for(int t=0;t<5;t++)
                  System.out.print(rowss.get(t));*/
                csvData.add(rowss);
                   
                }
            }
            }
       return 0; 
       
    }
    
    @FXML
    public void initialize() throws SQLException {
        // TODO code application logic here
         int numberofJuniorteacherused = 0,numberofRoomsUsed = 0;
        int numberofJuniorteacher = 0; //need to get this value from database;
        int numberofSeniorteacher = 0;//need to get this value from database;
        int handleClassroom = 6;
        String[] namesj =new String[300];
         String[] namess =new String[300];
         
    Statement stm,stm2;
    stm2=conn.createStatement();
    stm = conn.createStatement();
    stm.executeUpdate("Truncate table schema1.duty");
    ResultSet ct = stm.executeQuery("SELECT COUNT(*) FROM Schema1.class_details;");//to be changed later
            if(ct.next()){
                numberofRoomsUsed=ct.getInt(1);
            }
       
       ResultSet ct2 = stm.executeQuery("SELECT * FROM Schema1.teacher Where Seniority=0 and availability='available';");
       int id=1;
       while(ct2.next()){
           namesj[numberofJuniorteacher]=ct2.getString("name");
           stm2.executeUpdate("insert into Schema1.duty values('"+ct2.getString("Name")+"',' ',0,0,0,"+id+")");
            id++;
           numberofJuniorteacher++;
       }
       ResultSet ct3 = stm.executeQuery("SELECT * FROM Schema1.teacher Where Seniority=1 and availability='available';");
       
       while(ct3.next()){
           namess[numberofSeniorteacher]=ct3.getString("name");
           numberofSeniorteacher++;
       }
       ResultSet ct4 = stm.executeQuery("SELECT * FROM Schema1.exam;");
       String[] dateString=new String[20];
       Date[] dates=new Date[20];
        String[] start_time=new String[20];
        String[] end_time=new String[20];
        int[] noc=new int[20];
        int p=0;
        while(ct4.next()){
            SimpleDateFormat sdfr = new SimpleDateFormat("dd-MM-yyyy");

            try{
                dateString[p] = sdfr.format(ct4.getDate("date"));
            }catch (Exception ex ){
                System.out.println(ex);
            }
            dateString[p]+=" \n";
            dateString[p]=dateString[p].concat(ct4.getString("start_time"));
            dateString[p]+="-";
            dateString[p]=dateString[p].concat(ct4.getString("end_time"));
            int temp=0;
            for(int tee=0;tee<p;tee++){
                if(dateString[tee].equals(dateString[p])){
                    temp=1;
                }
            }
            if(temp==0){ 
                dates[p]=ct4.getDate("date");
                
                start_time[p]=ct4.getString("start_time");
                end_time[p]=ct4.getString("end_time");
                
                /*ResultSet ct5=stm2.executeQuery("select count(*) AS countf from Schema1.seating where Classname like '"+year[p]+"%';");
                ct5.next();
                noc[p]=ct5.getInt("countf");
                ct5.close();*/
                //System.out.println(noc[p]+" "+dateString[p]);
                p++;
            }
        }
         
        for(int i=0;i<p;i++){
            List<Integer> rooms = new ArrayList<Integer>();
//            LocalDate date = dates[i].toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            ct4 = stm.executeQuery("SELECT * FROM Schema1.exam where date='"+dates[i]+"' and start_time='"+start_time[i]+"' and end_time='"+end_time[i]+"'; ");
            while(ct4.next()){
               
                String noo= ct4.getString("year").concat(" ").concat(ct4.getString("branch"));
                System.out.println(noo);
            ResultSet ct5=stm2.executeQuery("select * from Schema1.seating where Classname like '"+noo+"%';");
                while(ct5.next()){
                if(rooms.contains(ct5.getInt("Class"))==false){
                    rooms.add(ct5.getInt("Class"));
                }

                }
                                
       
                ct5.close();
        }
            noc[i]=rooms.size();
            System.out.println(noc[i]+" "+dateString[i]);
        }
        int sum=0;
        for(int i=0;i<p;i++) sum+=noc[i];
        System.out.print("sum="+sum);
       
// need to get this value from database
        //Teacherallocation t = new Teacherallocation();
    TableColumn<ObservableList<String>, String> name = new TableColumn<ObservableList<String>, String>("name");
    name.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper<>(param.getValue().get(0))
            );

    //name.setCellValueFactory(new PropertyValueFactory<ObservableList<String>, String>("name"));
    table_teacher.getColumns().add(name);
        //System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEE "+p);
    for(int i=0;i<p;i++){
            int temp=i+1;
                TableColumn<ObservableList<String>, String> tc=new TableColumn<>(dateString[i]);
                 tc.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper<>(param.getValue().get(temp))
            );

                table_teacher.getColumns().add(tc);
                tc.setText(dateString[i]);
        }
 TableColumn<ObservableList<String>, String> number = new TableColumn<ObservableList<String>, String>("nod");
// number.setCellValueFactory(new PropertyValueFactory<ObservableList<String>, String>("nod"));
int temp=p+1;
number.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper<>(param.getValue().get(temp))
            );  
table_teacher.getColumns().add(number);

        /*ct3 = stm.executeQuery("SELECT * FROM Schema1.teacher Where Seniority=1 and availability='available';");
        while(ct3.next()){
            //stm2.executeUpdate("insert into Schema1.duty values('"+ct3.getString("Name")+"',' ',0,0,0,"+id+")");
            id++;
        }*/
            
        
         int numberofSeniorteacherused = seniorTeacher(numberofRoomsUsed,numberofSeniorteacher,handleClassroom,namess,p);
            numberofJuniorteacherused = juniorTeacher(sum*2,numberofJuniorteacher,namesj,dateString,p,noc);
           //  int numberofSeniorteacherused = seniorTeacher(numberofRoomsUsed,numberofSeniorteacher,handleClassroom,namess);
        table_teacher.setItems(csvData);
      
    }
    
    
    
}
/*
class Createobject {

    int duties[][];
    public Createobject(int m,int n) {
        duties = new int[m][n];
        
    }

    
}*/
