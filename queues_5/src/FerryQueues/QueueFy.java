/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this carTemplate file, choose Tools | Templates
 * and open the carTemplate in the editor.
 */
package FerryQueues;

/**
 *
 * @author Alex
 */
public class QueueFy {
    private int igCount;         //Keeps track of the number of nodes

    private Car front;            //Pointer to the front of the queue
    private Car back;             //Pointer to the back of the queue

    public QueueFy() {
        igCount = 0;
        front = null;
        back = null;
    }

    //push method. Adds data to the back of the queue.
    public void push(int iNewCarID) {
        if (isFull()) return;                                     //return if the list is full  
        Car carTemp = new Car();                                  //Create new node
        carTemp.SetID(iNewCarID);                                 //Set its data
        carTemp.next = null;                                      //Its next pointer will point to null (since it will be at the back)

        
        if(front == null) {            
            front = carTemp;                                       //Check if queue is empty 
        }
        else {
            back.next = carTemp;                                  //Have the current back node point to the new node
        }
        back = carTemp;                                            //Make the back pointer point to the new node
        igCount++;       
    }

    //pop method. Removes and retrieves the data at the front of the queue.
    public Car pop() {
        if(front == null) return null;
        
        Car carTempCur = front;                             //Gets the data from the front of the queue
        Car carTempNew = front.next;                                 //Temporary pointer to the front of the queue
        front=null;
        front = carTempNew;
        igCount--;                                         
        return carTempCur;                                        //Return the value from the node that was at the front
    }

    //peek method. Retrieves (but does not remove) the data at the front of the queue.
    public Car peek() {
        return front;
    }

    //size method. Retrieves the length/number of nodes in the queue.
    int size() {
        return igCount;
    }
    
    //isFull method. Determines if the queue is full. 
    public boolean isFull() {
        return (igCount >= 30);
    }

    //isEmpty method. Determines if the queue is empty.
    public boolean isEmpty() {
        return (size()==0);
    }

    //Resets the queue
    void clear() {
        Car carCurrent = front;                                  //Starts at the front
        Car next;

        while (carCurrent != null) {
            next = carCurrent.next;                               //Gets the next node
            carCurrent = null;                                      //Frees the current node
            carCurrent = next;                                     //Makes the next node the current node
        }
        front = null;                                           //Updates the front to null
        back = null;                                            //Updates the back to null
    }

}
