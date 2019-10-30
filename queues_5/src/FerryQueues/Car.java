/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FerryQueues;

/**
 *
 * @author Alex
 */
public class Car {
    private int igID;         //The ID of the car
    Car next;                 //the next node
    
    public int getID(){     //geting the car ID
        return igID;   
    }
    
    public void SetID(int iID){    //seting the car ID
        this.igID=iID;
    }
    
}
