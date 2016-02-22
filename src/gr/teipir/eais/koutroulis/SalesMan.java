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
public class SalesMan {
    
    private Hashtable <String,String> salesManAttributes;
    
    protected SalesMan(String afm, String fullName, String address, String phone) {  // Τα afm και phone ορίζονται String
                                                                                    // διότι δεν προορίζονται για μαθηματικές πράξεις.        
        salesManAttributes = new Hashtable<String,String>();
        
        salesManAttributes.put("afm", afm);
        salesManAttributes.put("fullName", fullName);
        salesManAttributes.put("address", address);
        salesManAttributes.put("phone", phone);
    }
    
    //Getter Methods
    protected String getAfm() {
        return salesManAttributes.get("afm");
    }
    
    protected String getFullName(){
        return salesManAttributes.get("fullName");
    }
    
    protected String getAddress() {
        return salesManAttributes.get("address");
    }
    
    protected String getPhone() {
        return salesManAttributes.get("phone");
    }
    
    //Setter Methods
    protected void setAfm(String afm) {
       salesManAttributes.put("afm", afm); 
    }
    
    protected void setFullName(String fullName) {
        salesManAttributes.put("fullName", fullName);
    }
    
    protected void setAddress(String address) {
        salesManAttributes.put("address", address);
    }
    
    protected void setPhone(String phone) {
        salesManAttributes.put("phone", phone);
    }
}
    
