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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Harsh
 */
public class TeacerDutyController {
    Connection conn;
    @FXML
    TableView table_teacher;
    ObservableList<ObservableList> csvData = FXCollections.observableArrayList();

    public TeacerDutyController() throws SQLException {
        this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
        );
    }
   /* @FXML    
    public int juniorTeacher(int numberofRoomUsed,int numberofJuniorteacher,String[] names,String[] dateString, int numberOfDays)
    {
        
       
    }*/
    @FXML
    public void initialize() throws SQLException{
        Statement stm,stm2,stm3;
        stm3=conn.createStatement();
        stm = conn.createStatement();
        stm2=conn.createStatement();
        stm.executeUpdate("Truncate table schema1.duty");
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
        
        
        TableColumn<ObservableList<String>, String> name = new TableColumn<ObservableList<String>, String>("name");
        name.setCellValueFactory(param ->
            new ReadOnlyObjectWrapper<>(param.getValue().get(0))
        );
        table_teacher.getColumns().add(name);
        
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
        int temp=p+1;
        number.setCellValueFactory(param ->
            new ReadOnlyObjectWrapper<>(param.getValue().get(temp))
        );  
        table_teacher.getColumns().add(number);
        int sum=0;
        for(int i=0;i<p;i++) sum+=noc[i];
        ResultSet ct5=stm2.executeQuery("select count(*) AS countf from Schema1.teacher where Availability='Available' and Seniority='0'");
        ct5.next();
        int nojjs=ct5.getInt("countf");
        ct5.close();
        
        ct5=stm2.executeQuery("select count(*) AS countf from Schema1.teacher where Availability='Available' and Seniority='1'");
        ct5.next();
        int nosjs=ct5.getInt("countf");
        ct5.close();
        int total=nojjs+nosjs;        
        int minimum=(sum*2)/total;
        System.out.println("minimum="+minimum);
        System.out.println(total);
        int id=1;
        ResultSet ct2 = stm.executeQuery("SELECT * FROM Schema1.teacher Where Seniority=0 and availability='available';");
        while(ct2.next()){
            stm3.executeUpdate("insert into Schema1.duty values('"+ct2.getString("Name")+"',' ',0,0,"+ct2.getInt("Seniority")+","+id+")");
            id++;
        }
         ResultSet ct3 = stm.executeQuery("SELECT * FROM Schema1.teacher Where Seniority=1 and availability='available';");
        while(ct3.next()){
            stm3.executeUpdate("insert into Schema1.duty values('"+ct3.getString("Name")+"',' ',0,0,"+ct3.getInt("Seniority")+","+id+")");
            id++;
        }
        for(int i=0;i<p;i++){
            int j=0,mastflag=0,flagsflag=0,count=0;
            while(j!=(noc[i]*2)){
                
                //System.out.print(count);
                if(count==100){System.out.print("Reached here");flagsflag=1;}
                Random rand = new Random();
                int  n = rand.nextInt(total) + 1;
                ct2=stm.executeQuery("SELECT * from Schema1.duty where id="+n+";");
                ct2.next();
                if(!ct2.getString("duty").contains(Integer.toString(i)) && (ct2.getInt("flag")==0 || flagsflag==1)){
                    if(ct2.getInt("Seniority")==1){
                        if(ct2.getInt("numofdut")<minimum || mastflag==1){
                        String noo=ct2.getString("duty").concat(Integer.toString(i)).concat(", ");
                        int nod=ct2.getInt("numofdut");
                        nod++;
                        int flag=0;
                        if(nod>=minimum && ct2.getInt("Seniority")==0){flag=1;}
                        stm3.executeUpdate("Update Schema1.duty set duty='"+noo+"', numofdut="+nod+", flag="+flag+" where id="+n+"");
                        j++;
                        ct3=stm2.executeQuery("SELECT * from Schema1.duty where seniority=0 and flag=0");
                        if (!ct3.isBeforeFirst() ) {    
                            mastflag=1; 
                        } 
                
                    }
                    }else{
                        String noo=ct2.getString("duty").concat(Integer.toString(i)).concat(", ");
                        int nod=ct2.getInt("numofdut");
                        nod++;
                        int flag=0;
                        if(nod>=minimum && ct2.getInt("Seniority")==0){flag=1;}
                        stm3.executeUpdate("Update Schema1.duty set duty='"+noo+"', numofdut="+nod+", flag="+flag+" where id="+n+"");
                        j++;
                        ct3=stm2.executeQuery("SELECT * from Schema1.duty where seniority=0 and flag=0");
                        if (!ct3.isBeforeFirst() ) {    
                            mastflag=1; 
                        } 
                    }
                }else count++;
            }
        }
            
        
//if()
    }
    
}
