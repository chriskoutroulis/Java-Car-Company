/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teipir.eais.koutroulis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Koutroulis C.
 */
public class GoodCarCompany {
    
    private Vector<Car> cars = new Vector<Car>();
    private Vector<SalesMan> salesMen = new Vector<SalesMan>();
    private Vector<Sale> sales = new Vector<Sale>();
    
    private static GoodCarCompany company = new GoodCarCompany();  //Sinlgeton Pattern
    
    private GoodCarCompany() {    // Εφαρμόζουμε το Sinleton pattern επειδή δε χρειάζομαστε πάνω από ένα instance του GoodCarCompany, ποτέ.
        
        // Εδώ να γεμίζουν τα vectors από τα αρχεία .txt        
        
       FileManager.getCarsFromFile(cars);
       FileManager.getSalesMenFromFile(salesMen);
       FileManager.getSalesFromFile(sales);  
    }
    
    public static GoodCarCompany getInstance() {  //Μέρος του Singleton Pattern
        
        return company;
    }
    
    public Vector<Car> getCarVector() {
        return cars;
    }
    
    public Vector<SalesMan> getSalesManVector() {
        return salesMen;
    }
    
    public Vector<Sale> getSaleVector() {
        return sales;
    }
    
    public void registerCar(Car car) {
        // Εδώ θα προστίθενται στο Vector και στο αρχείο .txt της εταιρείας!
        
        cars.add(car);
        FileManager.writeCarToFile(car);
    }
    
     public void registerSalesMan(SalesMan salesMan) {
        // Εδώ θα προστίθενται στο Vector και στο αρχείο .txt της εταιρείας!
         
         salesMen.add(salesMan);
         FileManager.writeSalesManToFile(salesMan);
    }
     
     public void registerSale (Sale sale) {
         // Εδώ θα προστίθενται στο Vector και στο αρχείο .txt της εταιρείας!
         sales.add(sale);
         FileManager.writeSaleToFile(sale);
     }
    
}
