/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtb;

/**
 *
 * @author Alex
 */
public class HashTable {
    public static KVP[] map;                                                      //Array of KVPs (Really an array of pointers to KVPs)
    private int igSize;                                            //Size of the array
    
    //Constructor. Instantiates the array and sets each KVP pointer to NULL
    public HashTable(int iSize) {
        map = new KVP[iSize];
        for(int i=0; i<iSize; i++) {
            map[i] = new KVP("","");
        }
        this.igSize=iSize;
    }

    //Adds a KVP to the Hash Table
    void put(String sKey, String sValue,int iHashType) {
        int hashValue =0;
        switch (iHashType){
            case 0:{
                hashValue = SimpleHashFunction(sKey); 
                break;}
            case 1:{
                hashValue = MyHashFunction(sKey); 
                break;}
            default:{
                return;}
        }
        //int hashValue = hashFunction(key);                          //Calculates the hash value
        if(map[hashValue] == null) {
            KVP temp = new KVP(sKey, sValue);                        //Create the new KVP and put it in the array
            map[hashValue] = temp;
        }
        else if(map[hashValue].getKey().equals(sKey)) {
            map[hashValue].setValue(sValue);                        //Replace value of existing KVP
        }
        else {
            System.out.println("Hash Collision");             //A different KVP was already hashed to that location
        }
    }

        //Gets the value associated with the supplied key
    String get(String sKey,int iHashType) {
        int hashValue =0;
        switch (iHashType){
            case 0:{
                hashValue = SimpleHashFunction(sKey); 
                break;}
            case 1:{
                hashValue = MyHashFunction(sKey); 
                break;}
            default:{
                return "";}
        }
        
        if(map[hashValue] != null) {
            return map[hashValue].getValue();                      //Return the value of the KVP
        }
        else {
            System.out.println("KVP not found");              //KVP not found
            return "";
        }
    }

        //Removes a KVP, if it exists
        //(Doesn't need to return true/false but can be helpful to know if something was deleted or not)
    boolean remove(String sKey,int iHashType) {
        int hashValue =0;
        switch (iHashType){
            case 0:{
                hashValue = SimpleHashFunction(sKey); 
                break;}
            case 1:{
                hashValue = MyHashFunction(sKey); 
                break;}
            default:{
                return false;}
        }
        
        if(map[hashValue] != null) {
            if(map[hashValue].getKey().equals(sKey)) {
                map[hashValue] = null;                                  //Set the array position to NULL
                return true;
            }
        }
        return false;                                           //KVP not there; Nothing to delete
    }  
        
    //Calculates a hash value for a string
    //Multiplies by a prime number to reduce the number of factors.
    int SimpleHashFunction(String sKey) {
        int hash = 0;
        for(int i = 0; i < sKey.length(); i++) {
            hash = hash + sKey.charAt(i);
        }
        int iRtn=(int)(hash % igSize);
        return iRtn;
    }

    //Multiplies by a prime number to reduce the number of factors.
    int MyHashFunction(String sKey) {
        long hash = 0;
        for(int i = 0; i < sKey.length(); i++) {
           
            hash = (7 * hash)/3 + sKey.charAt(i);
        }
        int iRtn=(int)(hash % igSize);
        return iRtn;
    }
}

class KVP {
    private String key;                                //The KVP's key
    private String value;                           //The KVP's value

    //Constructor
    public KVP(String k, String v) {
            this.key = k;
            this.value = v;
    }

    //Getter for key
    String getKey() {
        return key;
    }

    //Getter for value
    String getValue() {
        return value;
    }

    //Setter for value (no setter for key as it should never change)
    void setValue(String v) {
        value = v;
    }
}