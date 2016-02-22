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
public class Sale {
    
    private Hashtable<String,Object> saleAttributes;
    
    protected Sale(String salesManAfm, String carSerialNumber, int soldItems, float price, short year, byte month, byte day) {
    // Η προσθήκη του πρώτου ορίσματος "salesManAfm", καθιστά το αντικείμενο sale να είναι ορισμένο ανά πωλητή
    // αφού το Afm είναι μοναδικό σε κάθε πωλητή.
        
        
        saleAttributes = new Hashtable<String,Object>();
        
        saleAttributes.put("salesManAfm",salesManAfm);
        saleAttributes.put("carSerialNumber",carSerialNumber);
        saleAttributes.put("soldItems",soldItems);
        saleAttributes.put("price",price);
        saleAttributes.put("year",year);
        saleAttributes.put("month",month);
        saleAttributes.put("day",day);
    }   
    
    // Getter Methods
    protected String getSalesManAfm() {
        return (String) saleAttributes.get("salesManAfm");
    }
    protected String getCarSerialNumber() {
        return (String) saleAttributes.get("carSerialNumber");
    }
    
    protected int getSoldItems() {
        return (int) saleAttributes.get("soldItems");
    }
    
    protected float getPrice() {
        return (float) saleAttributes.get("price");
    }
    
    protected short getYear() {
        return (short) saleAttributes.get("year");
    }
    
    protected byte getMonth() {
        return (byte) saleAttributes.get("month");
    }
    
    protected byte getDay() {
        return (byte) saleAttributes.get("day");
    }
    
    //Setter Methods
    protected void setSalesManAfm(String salesManAfm) {
        saleAttributes.put("salesManAfm",salesManAfm);
    }
    
    protected void setCarSerialNumber(String carSerialNumber) {
        saleAttributes.put("carSerialNumber",carSerialNumber);
    }
    
    protected void setSoldItems (int soldItems) {
        saleAttributes.put("soldItems",soldItems);
    }
    
    protected void setPrice(float price) {
        saleAttributes.put("price",price);
    }
    
    protected void setYear(short year) {
       saleAttributes.put("year",year); 
    }
    
    protected void setMonth(byte month) {
        saleAttributes.put("month",month);
    }
    
    protected void setDay(byte day) {
        saleAttributes.put("day",day);
    }
}
