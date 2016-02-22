/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teipir.eais.koutroulis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Koutroulis C.
 */
public class FileManager {

    private static File carTextFile = new File("cars.txt");
    private static File salesMenTextFile = new File("salesMen.txt");
    private static File salesTextFile = new File("sales.txt");

    public static void getCarsFromFile(Vector<Car> cars) {

        BufferedReader myReader = null;
        String[] tempStringsArray = null;
        Vector<String[]> tempStringsArrayVector = new Vector<String[]>();
        String tempString = null;
        try {
            myReader = new BufferedReader(new FileReader(carTextFile));
            while ((tempString = myReader.readLine()) != null) {

                tempStringsArray = tempString.split(",");

                Car oneCar = new Car(tempStringsArray[0], tempStringsArray[1], Float.parseFloat(tempStringsArray[2]));
                cars.add(oneCar);
            }
            myReader.close();

        } catch (Exception ex) {

        }
    }

    public static void getSalesMenFromFile(Vector<SalesMan> salesMen) {

        BufferedReader myReader = null;
        String[] tempStringsArray = null;
        Vector<String[]> tempStringsArrayVector = new Vector<String[]>();
        String tempString = null;
        try {
            myReader = new BufferedReader(new FileReader(salesMenTextFile));
            while ((tempString = myReader.readLine()) != null) {

                tempStringsArray = tempString.split(",");

                SalesMan oneSalesMan = new SalesMan(tempStringsArray[0], tempStringsArray[1], tempStringsArray[2], tempStringsArray[3]);
                salesMen.add(oneSalesMan);
            }
            myReader.close();

        } catch (Exception ex) {

        }
    }

    public static void getSalesFromFile(Vector<Sale> sales) {

        BufferedReader myReader = null;
        String[] tempStringsArray = null;
        Vector<String[]> tempStringsArrayVector = new Vector<String[]>();
        String tempString = null;
        try {
            myReader = new BufferedReader(new FileReader(salesTextFile));
            while ((tempString = myReader.readLine()) != null) {

                tempStringsArray = tempString.split(",");

                Sale oneSale = new Sale(tempStringsArray[0], tempStringsArray[1], Integer.parseInt(tempStringsArray[2]),
                        Float.parseFloat(tempStringsArray[3]), Short.parseShort(tempStringsArray[4]), Byte.parseByte(tempStringsArray[5]),
                        Byte.parseByte(tempStringsArray[6]));
                sales.add(oneSale);
            }
            myReader.close();

        } catch (Exception ex) {

        }
    }

    protected static void writeCarToFile(Car car) {
        try {
            PrintWriter myPrintWriter = new PrintWriter(new FileWriter(carTextFile, true));
            myPrintWriter.println(car.getSerialNumber() + "," + car.getModel() + "," + car.getPrice());
            myPrintWriter.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Δεν μπορεί να δημιουργηθεί αρχείο cars.txt");
        }
    }

    protected static void writeSalesManToFile(SalesMan salesMan) {
        try {
            PrintWriter myPrintWriter = new PrintWriter(new FileWriter(salesMenTextFile, true));
            myPrintWriter.println(salesMan.getAfm() + "," + salesMan.getFullName() + "," + salesMan.getAddress()
                    + "," + salesMan.getPhone());
            myPrintWriter.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Δεν μπορεί να δημιουργηθεί αρχείο salesMen.txt");
        }
    }

    protected static void writeSaleToFile(Sale sale) {
        try {
            PrintWriter myPrintWriter = new PrintWriter(new FileWriter(salesTextFile, true));
            myPrintWriter.println(sale.getSalesManAfm() + "," + sale.getCarSerialNumber() + "," + sale.getSoldItems()
                    + "," + sale.getPrice() + "," + sale.getYear() + "," + sale.getMonth() + "," + sale.getDay());
            myPrintWriter.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Δεν μπορεί να δημιουργηθεί αρχείο sales.txt");
        }
    }

    protected static void deleteCarsFile() {
        if (carTextFile.exists()) {
            carTextFile.delete();
        }
    }

    protected static void deleteSalesMenFile() {
        if (salesMenTextFile.exists()) {
            salesMenTextFile.delete();
        }
    }

    protected static void deleteSalesFile() {
        if (salesTextFile.exists()) {
            salesTextFile.delete();
        }
    }
}
