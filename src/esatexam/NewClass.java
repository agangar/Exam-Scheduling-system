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

/**
 *
 * @author Harsh
 */
public class NewClass {
    
    // static String []classs={"TE CMPN A","TE CMPN B","SE EXTC A","SE EXTC B","SE IT A","SE IT B","TE EXTC A","TE EXTC B","SE CMPN A","SE CMPN B","TE IT A","TE IT B"};

    
    public static void main(String[] args) throws SQLException{
        
      Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schema1?zeroDateTimeBehavior=convertToNull","root", "1234"
                );
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
            int[] c=new int[x];
            int[] count=new int[x];
            String exceptee[]=new String[x];
    String sql = "Select * From Schema1.class_details order by Year,Branch,Division ";
    ResultSet result;
    int k=0;
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
                System.out.println(a[k]+""+names[k]);
                b[k]=a[k];
                c[k]=result.getInt("Starting_roll");
                count[k]=result.getInt("Room_no");
                k++;    
    }
     
         int count1=0;
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
             System.out.print(" "+count1+" "+names[i]+" "+noo);
              if(a[i+2]==(b[i+2])-20){noo=Integer.toString(c[i+2])+"-"+Integer.toString(c[i+2]+19);}
             else if(a[i+2]==(b[i+2])-40){noo=Integer.toString(c[i+2]+20)+"-"+Integer.toString(c[i+2]+39);}
             else if(a[i+2]==(b[i+2])-60){noo=Integer.toString(c[i+2]+40)+"-"+Integer.toString(c[i+2]+59);}
             else if(a[i+2]==(b[i+2])-80){noo=Integer.toString(c[i+2]+60)+"-"+Integer.toString(c[i+2]+79);}
             System.out.print(" "+count1+" "+names[i+2]+" "+noo);
             count1++;
             System.out.println();
         }
         }
         if(i==1||i==5)i+=2;
         }
        a=sorta(a,k,names,c,b);
         
        for(int i=0;i<k-1;i++){ System.out.println(names[i]+": "+a[i]);}   
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
             System.out.print(" "+count1+" "+names[i]+" "+noo);
               if(a[i+1]==(b[i+1])-20){noo=Integer.toString(c[i+1])+"-"+Integer.toString(c[i+1]+19);}
             else if(a[i+1]==(b[i+1])-40){noo=Integer.toString(c[i+1]+20)+"-"+Integer.toString(c[i+1]+39);}
             else if(a[i+1]==(b[i+1])-60){noo=Integer.toString(c[i+1]+40)+"-"+Integer.toString(c[i+1]+59);}
             else if(a[i+1]==(b[i+1])-80){noo=Integer.toString(c[i+1]+60)+"-"+Integer.toString(c[i+1]+79);}
            System.out.print(" "+count1+" "+names[i+1]+" "+noo); 
             
             count1++;
             System.out.println();
             a=sorta(a,k,names,b,c);
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
                 System.out.print(" "+count1+" "+names[i]+" "+noo);
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
                 System.out.print(" "+count1+" "+names[i+1]+":"+noo);
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
                 
                  System.out.print(" "+count1+" "+names[i+1]+":"+noo+" "+count1+" "+names[p]+":"+noo2);
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
                 System.out.print(" "+count1+" "+names[p]+":"+noo); 
                 a[p]=0;
                 }
                 }
                 a=sorta(a,k,names,b,c);
                 System.out.println();
                 count1++;
                 }
             else if(a[i]<20&&a[i+1]<20)
             {
                int total=40,temp2=0,tp=9;
                 int left1=total-20-a[i];
                 int left2=total-20-a[i+1];
                 int left=0;
                 if(left1>left2){left=left1;tp=0;}else{ left=left2;tp=1;}
                 System.out.println("left:"+left);
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
                     
                      
                      System.out.print(" "+count1+" "+names[i]+":"+noo2);
                 System.out.print(" "+count1+" "+names[i+1]+":"+noo);
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
                 
                        System.out.print(" "+count1+" "+names[i]+":"+noo);
                  System.out.print(" "+count1+" "+names[i+1]+":"+noo2+" "+count1+" "+names[p]+":"+noo3);
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
                 
                 System.out.print(" "+count1+" "+names[p]+":"+noo3);  
                 a[p]=0;
                 }
                 }
                 a=sorta(a,k,names,b,c);System.out.println(); count1++;
                 
}}}    public static int[] sorta(int []a, int n, String[] names,int[] b, int[] c){
        String temp1;
        int temp,temp2,temp3;
        for (int i = 0; i < n; i++) 
        {
            for (int j = i + 1; j < n; j++) 
            {
                if (a[i] < a[j]) 
                {
                    temp3=c[i];
                    temp2=b[i];
                    temp1=names[i];
                    temp = a[i];
                    c[i]=c[j];
                    b[i]=b[j];
                    names[i]=names[j];
                    a[i] = a[j];
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


