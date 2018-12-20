
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Harsh
 */
public class NewClass {
    
    public static void main(String args[]){
              
        
        int arr[]=new int[10000];
        long start,end;
        Random r=new Random();
        for(int i=0;i<10000;i++)
        arr[i]=r.nextInt()%16;
       
        start=System.nanoTime();
        int n=arr.length;
        
        int temp = 0;
        for(int i = 0 ;i<n-1 ;i++)
        {
            for(int j=0 ;j<n-i- 1;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }

            }
        }
        end=System.nanoTime();
       
        System.out.println("It took "+(end-start)+"ns time");

/*for(int i=0; i<n ;i++)
{
System.out.print(arr[i]+" " );
}*/
 for(int i=0;i<10000;i++)
        arr[i]=r.nextInt()%16;
       
        start=System.nanoTime();
 for (int i = 0; i < n/2; i++ ) {
  for (int j = 0; j+1 < n; j += 2)
  if (arr[j] > arr[j+1]) {
  int T = arr[j];
  arr[j] = arr[j+1];
  arr[j+1] = T;
  }
  for (int j = 1; j+1 < arr.length; j += 2)
  if (arr[j] > arr[j+1]) {
  int T = arr[j];
  arr[j] = arr[j+1];
  arr[j+1] = T;
    }
    
}
  end=System.nanoTime();
       
        System.out.println("It took "+(end-start)+"ns time");
/*
for(int i=0; i<n ;i++)
{
System.out.print(arr[i]+" " );
}*/
    }
}
