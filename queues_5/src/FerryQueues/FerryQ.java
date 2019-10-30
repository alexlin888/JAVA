/*
 * FerryQ.java - Main class and function
 * Car.java    - define the Car object 
 * QueueFy.java - define the Ferry Queues 
 */
package FerryQueues;

import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class FerryQ {
    // global variable 
    public static QueueFy[] qLanes;             //Queues for storage the car
    
    private static int igCarID;                 //Car ID
    private static int igCurLaneID;             //point to current adding lane
    private static int igCurLoadLaneID;         //point to current loading lane

    //main function 
    public static void main(String[] args) {
        //initial variable and queues
        igCurLaneID=0;                      
        igCurLoadLaneID=0;                  
        igCarID=0;                                 
        
        qLanes=new QueueFy[10];             //base on the requirement, create 10 lanes
        for (int i=0;i<10;i++){             //initial each queue
            qLanes[i]=new QueueFy(); 
        }

        Scanner sInput = new Scanner(System.in);

               
        while (true){
            System.out.println("\n******************************************************");
            System.out.println("Available command list below:");
            System.out.println("   add - adding the car to Ferry Queues.");
            System.out.println("   load - loading the car.");
            System.out.println("   exit -  Exit this program.");
            System.out.println("******************************************************");
            System.out.print("Command: ");

            String sFunction=sInput.nextLine();     //get the user input
            
            switch (sFunction.toLowerCase()) {
                case "load":        //call loading function
                    loading(100,true);
                    System.out.println("");
                    break;
                case "add":        //call adding function
                    System.out.print("Number of cars: ");
                    adding(Integer.parseInt(sInput.nextLine()),true);
                    System.out.println("");
                    break;
                case "exit":        //exit program
                    System.out.println("\n************* Thank you for your using **************\n");
                    return;
                default:
                    System.out.println("Command enter error. Please try again.");
                    break;
            }        
        }
    }    
    
    //loading the car from the queues 
    private static void loading(int iLoad,boolean runAgain){
        for (int i=0;i<10;i++){
            if (i<igCurLoadLaneID) continue;    //making sure that loading starts from the same lane with the previous.           
            if (iLoad<1) break;                 //return if all car loading complete
            
            int iCarEachLane=0;
            
            //if current lane is not empty, load the car from this queue.
            while (!(qLanes[i].isEmpty())){
                if (iLoad<1) break;     //exit if all car alreadly loaded
                qLanes[i].pop();        //load the car from the queue
                iCarEachLane++;
                iLoad--;
                igCurLoadLaneID=i;      //update the current loading lane ID;
            }
            
            if (iCarEachLane>0){
                System.out.println("Loaded "+iCarEachLane +" cars from Lane "+(i+1));
            }
        }
        
        //if loading still not finish
        if (iLoad>0){
            // if lane 10 (ID is 9) is empty, go back to lane 1 (ID is 0) and load others.
            if ((igCurLoadLaneID>=9) && (runAgain)) {
                igCurLoadLaneID=0;        
                loading(iLoad,false);       //"false" is make sure just call this function 1 more time. 
            } 
            else{
                //all lanes are empty
                System.out.println("All queues empty.");
            }
        } 
    }
    
    //adding the car to the queues 
    private static void adding(int iAdd,boolean runAgain){
        
        for (int i=0;i<10;i++){
            if (i<igCurLaneID) continue;        //making sure that adding starts from the same lane with the previous.      
            if (iAdd<1) break;                  //return if all car alreadly added
            
            int iCarEachLane=0;
            
            //if current lane is not full, add the car in it.
            while (!(qLanes[i].isFull())){      
                if (iAdd<1) break;          //exit if all car alreadly added
                qLanes[i].push(++igCarID);  //add the car into the queue
                iCarEachLane++;
                iAdd--;
            }
            
            if (qLanes[i].isFull()) igCurLaneID++;      //update the current adding lane ID;

            
            if (iCarEachLane>0){
                System.out.println("Added "+iCarEachLane +" cars from Lane "+(i+1));                
            }
        }
       
        //if adding still not finish
        if (iAdd>0){
            // if lane 10 (ID is 9) is full, go back to lane 1 (ID is 0) to add others.
            if ((igCurLaneID>=9) && (runAgain)) {
                igCurLaneID=0;        
                adding(iAdd,false);             //"false" is make sure just call this function 1 more time. 
            } 
            else{
                //all lanes are full
                System.out.println("Could not add "+iAdd+" cars. All queues full.");
            }
        }
    }
}
