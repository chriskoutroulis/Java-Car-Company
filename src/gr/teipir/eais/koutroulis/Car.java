/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teipir.eais.koutroulis;

import java.util.Hashtable;

/**
 *
 * @author Koutroulis C.
 */
public class Car {
    private Hashtable <String,Object> carAttributes;
    
    protected Car(String serialNumber, String model, float price) {
        
        carAttributes = new Hashtable<String,Object>();
        
        carAttributes.put("serialNumber",serialNumber);
        carAttributes.put("model",model);
        carAttributes.put("price",price);        
    }
    
    //Getter Methods  
    protected String getSerialNumber() {
        return (String) carAttributes.get("serialNumber");
    }
    
    protected String getModel() {
        return (String) carAttributes.get("model");
    }
    
    protected float getPrice() {
        return (float) carAttributes.get("price");
    }
    
    //Setter Methods
       protected void setSerialNumber(String serialNumber) {
        carAttributes.put("serialNumber",serialNumber);
    }
    
    protected void setModel(String model) {
         carAttributes.put("model",model);
    }
    
    protected void setPrice(float price){
         carAttributes.put("price",price);   
    }
}
