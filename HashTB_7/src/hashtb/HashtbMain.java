/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtb;

//import java.util.*;

/**
 *
 * @author Alex
 */
public class HashtbMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //for (int i=0;i<300;i++)
        //System.out.println(GetJNumber());
       TestHash(100,127,0); 
       System.out.println("-----------------");
       TestHash(100,127,1); 
       //TestHash(10,12,1); 
        
    }
    
    public static void TestHash(int iTotalJNo, int iArrLength,int iHashType) {
        int h;
        int a[] = new int[iArrLength] ;
        HashTable ht= new HashTable(iArrLength);

        for (int i=0; i<iTotalJNo; i++){
            switch (iHashType){
                case 0:{
                    h=ht.SimpleHashFunction(GetJNumber()); 
                    break;
                }
                case 1:{
                    h=ht.MyHashFunction(GetJNumber()); 

                    break;
                }
                default:{
                    return;
                }
            }
            a[h]++;
        }

        for(int i = 0; i < iArrLength; i++) {
            System.out.println("index "+ i +" count = " + a[i]);
        }
    }
    
    // Generate random a 8 digit number
    public static String GetJNumber(){
        double dNum = Math.random()*8.7+1;
        long iNum=(long)(dNum*10000000);
        return "J"+Long.toString(iNum);
    }
    
}
