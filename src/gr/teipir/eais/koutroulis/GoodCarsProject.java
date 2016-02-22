/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teipir.eais.koutroulis;

import javax.swing.JOptionPane;
import java.lang.NumberFormatException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Koutroulis C.
 */
public class GoodCarsProject {

    private static GoodCarCompany company;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here   

        company = GoodCarCompany.getInstance();
        GoodCarsProject myProject = new GoodCarsProject();
        myProject.start();

    }   // End main()

    public void start() {
        System.out.println("\n \t\t -----------------------------------------");
        int firstAnswer = 0;
        String[] options = new String[]{"Διαχείριση Στοιχείων", "Αναζήτηση Στοιχείων"};
        firstAnswer = JOptionPane.showOptionDialog(null, "Τι θέλετε να κάνετε;", "GoodCarCompany Software", //Δημιουργία πρώτου διαλόγου.
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (firstAnswer == 0) {   // Επιλογή του είδους καταχώρησης
            int secondAnswer = 0;
            options = new String[]{"Διαχείριση Αυτοκινήτων", "Διαχείριση Πωλητών", "Διαχείριση Πωλήσεων"};  //Επιλογές διαχείρισης.
            secondAnswer = JOptionPane.showOptionDialog(null, "Τι θέλετε να κάνετε;", "GoodCarCompany Software",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch (secondAnswer) {
                case 0:   // Για διαχείριση αυτοκινήτων
                    int thirdAnswer = 0;
                    options = new String[]{"Εγγραφή Νέου Αυτοκινήτου", "Επεξεργασία Αυτοκινήτου", "Διαγραφή Αυτοκινήτου"};  //Επιλογές διαχείρισης αυτοκινήτων.
                    thirdAnswer = JOptionPane.showOptionDialog(null, "Τι θέλετε να κάνετε;", "GoodCarCompany Software",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    switch (thirdAnswer) {
                        case 0:
                            userInputCar();
                            break;
                        case 1:
                            editCar();
                            break;
                        case 2:
                            deleteCar();
                            break;
                    }// End switch
                    break;
                case 1: //Για διαχείριση πωλητών
                    options = new String[]{"Εγγραφή Νέου Πωλητή", "Επεξεργασία Πωλητή", "Διαγραφή Πωλητή"};  //Επιλογές διαχείρισης πωλητών.
                    thirdAnswer = JOptionPane.showOptionDialog(null, "Τι θέλετε να κάνετε;", "GoodCarCompany Software",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    switch (thirdAnswer) {
                        case 0:
                            userInputSalesMan();
                            break;
                        case 1:
                            editSalesMan();
                            break;
                        case 2:
                            deleteSalesMan();
                            break;
                    }// End switch
                    break;
                case 2: //Για διαχείριση πωλήσεων
                    options = new String[]{"Εγγραφή Νέας Πώλησης", "Επεξεργασία Πώλησης", "Διαγραφή Πώλησης"};  //Επιλογές διαχείρισης πωλήσεων.
                    thirdAnswer = JOptionPane.showOptionDialog(null, "Τι θέλετε να κάνετε;", "GoodCarCompany Software",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    switch (thirdAnswer) {
                        case 0:
                            userInputSale();
                            break;
                        case 1:
                            editSale();
                            break;
                        case 2:
                            deleteSale();
                            break;
                    }// End switch
                    break;
            } //End Switch
        } else if (firstAnswer == 1) {
            int secondAnswer = 0;
            options = new String[]{"Πωλήσεις ανά Πωλητή", "Πωλήσεις ανά Μοντέλο"};                                    //Επιλογές Αναζήτησης
            secondAnswer = JOptionPane.showOptionDialog(null, "Τι θέλετε να αναζητήσετε;", "GoodCarCompany Software",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (secondAnswer == 0) {
                printSalesMenStats();
            } else if (secondAnswer == 1) {
                printModelStats();
            } //End if            

        } // End if  

    } //End start()

    public void userInputCar() {
        boolean isInValid = false;
        String serialNumber = null;
        String model = null;
        Float floatPrice = 0f;
        Vector<Car> cars = cars = company.getCarVector();
        Boolean isDuplicate = false;
        do {   // Εισαγωγή Σειριακού Αριθμού με έλεγχο για την ύπαρξη κομμάτων. Τα κόμματα είναι δεσμευμένα για να ξεχωρίζονται 
            // τα πεδία όταν γίνεται ανάγνωση από το αρχείο.
            serialNumber = JOptionPane.showInputDialog(null, "Εισάγετε το Σειριακό Αριθμό του Αυτοκινήτου (χωρίς κόμματα):");
            if (serialNumber.indexOf(",") >= 0) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
            } else {
                isInValid = false;
                isDuplicate = false;
                for (Car oneCar : cars) {  //Έλεγχος για το αν υπάρχει ήδη ο σειριακός αριθμός.
                    if (oneCar.getSerialNumber().equalsIgnoreCase(serialNumber)) {
                        isDuplicate = true;
                        isInValid = true;
                    }
                }
                if (isDuplicate) {
                    JOptionPane.showMessageDialog(null, "Ο Σειριακός αριθμός υπάρχει ήδη. \nΕπιλέξτε ένα διαφορετικό.");
                }
            }
        } while (isInValid);

        do {  // Εισαγωγή Μοντέλου με έλεγχο για την ύπαρξη κομμάτων.
            model = JOptionPane.showInputDialog(null, "Εισάγετε το Μοντέλο του Αυτοκινήτου (χωρίς κόμματα):");
            if (model.indexOf(",") >= 0) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
            } else {
                isInValid = false;
            }
        } while (isInValid);

        do {    // Εισαγωγή Τιμής με έλεγχο για σωστή αριθμητική τιμή (θετικός αριθμός).
            String price = JOptionPane.showInputDialog(null, "Εισάγετε την τιμή του Αυτοκινήτου (για υποδιαστολή βάζετε \".\") :");
            isInValid = false;
            try {
                floatPrice = Float.parseFloat(price);
                if (floatPrice < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Βάλτε έγκυρο αριθμό για τιμή αυτοκινήτου!");
            }
        } while (isInValid);

        Car car = new Car(serialNumber, model, floatPrice);
        company.registerCar(car);
        JOptionPane.showMessageDialog(null, "Η εγγραφή ολοκληρώθηκε επιτυχώς!");
        start();

    }  //End of userInputCar()

    public void deleteCar() {
        Vector<Car> cars = company.getCarVector();
        Car carToDelete = null;
        boolean found = true;
        for (Car oneCar : cars) {
            System.out.printf("%-15s %-30s %10.2f€ \n", oneCar.getSerialNumber(), oneCar.getModel(), oneCar.getPrice());
        }//End for
        String serialNumber = JOptionPane.showInputDialog("Δείτε στην κονσόλα όλα τα αυτοκίνητα και \n"
                + "εισάγετε τον σειριακό αριθμό του αυτοκινήτου που θέλετε να διαγραφεί:");
        for (Car oneCar : cars) {
            if (oneCar.getSerialNumber().equals(serialNumber)) {
                carToDelete = oneCar;
                found = true;
                break;
            } else {
                found = false;
            }
        }//End for
        if (!found) {
            JOptionPane.showMessageDialog(null, "Δεν υπάρχει ο σειριακός αριθμός που δώσατε.");
            start();
        } else {
            cars.remove(carToDelete);    //Διαγραφή από το Vector.

            FileManager.deleteCarsFile();   // Δημιουργία νέων εγγράφων κειμένου με τις αλλαγές.
            for (Car oneCar : cars) {
                FileManager.writeCarToFile(oneCar);
            }//End for
            JOptionPane.showMessageDialog(null, "Η διαγραφή έγινε με επιτυχία.");
        }// End if
    }// End of deleteCar()

    public void editCar() {
        Vector<Car> cars = company.getCarVector();
        Car carToEdit = null;
        boolean isInValid = false;
        int indexToEdit = 0;
        for (Car oneCar : cars) {
            System.out.printf("%-5d %-15s %-25s %10.2f€ \n", cars.indexOf(oneCar), oneCar.getSerialNumber(), oneCar.getModel(),
                    oneCar.getPrice());
        }//End for

        do {
            String carNumber = JOptionPane.showInputDialog("Δείτε στην κονσόλα όλα τα αυτοκίνητα και \n"
                    + "εισάγετε τον αριθμό του αυτοκινήτου που θέλετε να επεξεργαστείτε:");
            if (carNumber == null) {
                return;
            }
            try {
                indexToEdit = Integer.parseInt(carNumber);
                if (Integer.parseInt(carNumber) < 0 || Integer.parseInt(carNumber) > cars.size() - 1) {
                    throw new NumberFormatException();
                }
                carToEdit = cars.get(indexToEdit);
                isInValid = false;
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Εισάγετε μια έγκυρη τιμή!");
            }
        } while (isInValid);

        indexToEdit = 0;
        do {
            String optionNumber = JOptionPane.showInputDialog("Ποια τιμή θέλετε να αλλάξετε; \n"
                    + " 1   Το Σειριακό Αριθμό αυτοκινήτου \n 2  Το Μοντέλο του αυτοκινήτου \n 3   Την τιμή του αυτοκινήτου \n\n");
            if (optionNumber == null) {
                return;
            }
            try {
                indexToEdit = Integer.parseInt(optionNumber);
                if (Integer.parseInt(optionNumber) < 1 || Integer.parseInt(optionNumber) > 3) {
                    throw new NumberFormatException();
                }
                isInValid = false;
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Εισάγετε μια έγκυρη τιμή!");
            }
        } while (isInValid);

        String newValue = null;
        switch (indexToEdit) {
            case 1: //Για αλλαγή Σειριακού αριθμού
                Boolean isDuplicate = false;
                do {   // Εισαγωγή Σειριακού Αριθμού με έλεγχο για την ύπαρξη κομμάτων. Τα κόμματα είναι δεσμευμένα για να ξεχωρίζονται 
                    // τα πεδία όταν γίνεται ανάγνωση από το αρχείο.
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το Σειριακό Αριθμό του Αυτοκινήτου (χωρίς κόμματα):");
                    if (newValue.indexOf(",") >= 0) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
                    } else {
                        isInValid = false;
                        isDuplicate = false;
                        for (Car oneCar : cars) {  //Έλεγχος για το αν υπάρχει ήδη ο σειριακός αριθμός.
                            if (oneCar.getSerialNumber().equalsIgnoreCase(newValue)) {
                                isDuplicate = true;
                                isInValid = true;
                            }
                        }
                        if (isDuplicate) {
                            JOptionPane.showMessageDialog(null, "Ο Σειριακός αριθμός υπάρχει ήδη. \nΕπιλέξτε ένα διαφορετικό.");
                        }
                    }
                } while (isInValid);
                carToEdit.setSerialNumber(newValue);
                break;
            case 2:  //Για αλλαγή μοντέλου
                do {   // Εισαγωγή μοντέλου με έλεγχο για την ύπαρξη κομμάτων.                     
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το Μοντέλο του αυτοκινήτου (χωρίς κόμματα) :");
                    if (newValue.indexOf(",") >= 0) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
                    } else {
                        isInValid = false;
                    }
                } while (isInValid);
                carToEdit.setModel(newValue);
                break;
            case 3:  //Για αλλαγή τιμής
                do {    // Εισαγωγή Τιμής με έλεγχο για σωστή αριθμητική τιμή (θετικός αριθμός).
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε την τιμή του Αυτοκινήτου (για υποδιαστολή βάζετε \".\") :");
                    isInValid = false;
                    try {
                        Float floatPrice = Float.parseFloat(newValue);
                        if (floatPrice < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Βάλτε έγκυρο αριθμό για τιμή αυτοκινήτου!");
                    }
                } while (isInValid);
                carToEdit.setPrice(Float.parseFloat(newValue));
                break;
        } //Εnd of switch
        FileManager.deleteCarsFile();   // Δημιουργία νέων εγγράφων κειμένου με τις αλλαγές.
        for (Car oneCar : cars) {
            FileManager.writeCarToFile(oneCar);
        }//End for
        JOptionPane.showMessageDialog(null, "Η αλλαγή αποθηκεύτηκε με επιτυχία!");
        start();
    }// End of editCar()

    public void userInputSalesMan() {
        boolean isInValid = false;
        String afm = null;
        String fullName = null;
        String address = null;
        String phone = null;
        Vector<SalesMan> salesMen = company.getSalesManVector();
        boolean isDuplicate = false;
        do {   // Εισαγωγή ΑΦΜ με έλεγχο για την ύπαρξη κομμάτων.                     
            afm = JOptionPane.showInputDialog(null, "Εισάγετε το ΑΦΜ του πωλητή:");
            if (afm.indexOf(",") >= 0) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
            } else {
                isInValid = false;
                isDuplicate = false;
                for (SalesMan oneSalesMan : salesMen) {  //Έλεγχος για το αν υπάρχει ήδη το ΑΦΜ.
                    if (oneSalesMan.getAfm().equalsIgnoreCase(afm)) {
                        isDuplicate = true;
                        isInValid = true;
                    }
                }
                if (isDuplicate) {
                    JOptionPane.showMessageDialog(null, "Το ΑΦΜ υπάρχει ήδη. \nΕπιλέξτε ένα διαφορετικό.");
                }
            }
        } while (isInValid);
        //test
//        do {   // Εισαγωγή Σειριακού Αριθμού με έλεγχο για την ύπαρξη κομμάτων. Τα κόμματα είναι δεσμευμένα για να ξεχωρίζονται 
//            // τα πεδία όταν γίνεται ανάγνωση από το αρχείο.
//            serialNumber = JOptionPane.showInputDialog(null, "Εισάγετε το Σειριακό Αριθμό του Αυτοκινήτου (χωρίς κόμματα):");
//            if (serialNumber.indexOf(",") >= 0) {
//                isInValid = true;
//                JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
//            } else {
//                isInValid = false;
//                isDuplicate = false;
//                for (Car oneCar : cars) {  //Έλεγχος για το αν υπάρχει ήδη ο σειριακός αριθμός.
//                    if (oneCar.getSerialNumber().equalsIgnoreCase(serialNumber)) {
//                        isDuplicate = true;
//                        isInValid = true;
//                    }
//                }
//                if (isDuplicate) {
//                    JOptionPane.showMessageDialog(null, "Ο Σειριακός αριθμός υπάρχει ήδη. \nΕπιλέξτε ένα διαφορετικό.");
//                }
//            }
//        } while (isInValid);
        //test
        do {   // Εισαγωγή Ονοματεπώνυμου με έλεγχο για την ύπαρξη κομμάτων.                     
            fullName = JOptionPane.showInputDialog(null, "Εισάγετε το Ονοματεπώνυμο του πωλητή (χωρίς κόμματα) :");
            if (fullName.indexOf(",") >= 0) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
            } else {
                isInValid = false;
            }
        } while (isInValid);

        do {   // Εισαγωγή Διεύθυνσης με έλεγχο για την ύπαρξη κομμάτων.                     
            address = JOptionPane.showInputDialog(null, "Εισάγετε τη Διεύθυνση του πωλητή (χωρίς κόμματα) :");
            if (address.indexOf(",") >= 0) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
            } else {
                isInValid = false;
            }
        } while (isInValid);

        do {   // Εισαγωγή Τηλεφώνου με έλεγχο για την ύπαρξη κομμάτων.                     
            phone = JOptionPane.showInputDialog(null, "Εισάγετε το Τηλέφωνο του πωλητή (χωρίς κόμματα) :");
            if (phone.indexOf(",") >= 0) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
            } else {
                isInValid = false;
            }
        } while (isInValid);

        SalesMan salesMan = new SalesMan(afm, fullName, address, phone);
        company.registerSalesMan(salesMan);
        JOptionPane.showMessageDialog(null, "Η εγγραφή ολοκληρώθηκε επιτυχώς!");
        start();

    } // End of userInputSalesMan()

    public void deleteSalesMan() {

        Vector<SalesMan> salesMen = company.getSalesManVector();
        SalesMan salesManToDelete = null;
        boolean found = true;
        for (SalesMan oneSalesMan : salesMen) {
            System.out.printf("%-15s %-30s %-15s %-15s \n", oneSalesMan.getAfm(), oneSalesMan.getFullName(),
                    oneSalesMan.getAddress(), oneSalesMan.getPhone());
        }//End for
        String salesManAfm = JOptionPane.showInputDialog("Δείτε στην κονσόλα όλους τους πωλητές και \n"
                + "εισάγετε το ΑΦΜ αυτού που θέλετε να διαγραφεί:");
        for (SalesMan oneSalesMan : salesMen) {
            if (oneSalesMan.getAfm().equals(salesManAfm)) {
                salesManToDelete = oneSalesMan;
                found = true;
                break;
            } else {
                found = false;
            }
        }//End for
        if (!found) {
            JOptionPane.showMessageDialog(null, "Δεν υπάρχει το ΑΦΜ που δώσατε.");
            start();
        } else {
            salesMen.remove(salesManToDelete);    //Διαγραφή από το Vector.

            FileManager.deleteSalesMenFile();   // Δημιουργία νέων εγγράφων κειμένου με τις αλλαγές.
            for (SalesMan oneSalesMan : salesMen) {
                FileManager.writeSalesManToFile(oneSalesMan);
            }//End for
            JOptionPane.showMessageDialog(null, "Η διαγραφή έγινε με επιτυχία.");
        }// End if
    }//End of deleteSalesMan()

    public void editSalesMan() {
        Vector<SalesMan> salesMen = company.getSalesManVector();
        SalesMan salesManToEdit = null;
        boolean isInValid = false;
        int indexToEdit = 0;
        for (SalesMan oneSalesMan : salesMen) {
            System.out.printf("%-5d %-15s %-30s %-30s %-15s \n", salesMen.indexOf(oneSalesMan), oneSalesMan.getAfm(), oneSalesMan.getFullName(),
                    oneSalesMan.getAddress(), oneSalesMan.getPhone());
        }//End for

        do {
            String salesManNumber = JOptionPane.showInputDialog("Δείτε στην κονσόλα όλους τους πωλητές και \n"
                    + "εισάγετε τον αριθμό πωλητή που θέλετε να επεξεργαστείτε:");
            if (salesManNumber == null) {
                return;
            }
            try {
                indexToEdit = Integer.parseInt(salesManNumber);
                if (Integer.parseInt(salesManNumber) < 0 || Integer.parseInt(salesManNumber) > salesMen.size() - 1) {
                    throw new NumberFormatException();
                }
                salesManToEdit = salesMen.get(indexToEdit);
                isInValid = false;
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Εισάγετε μια έγκυρη τιμή!");
            }
        } while (isInValid);

        indexToEdit = 0;
        do {
            String optionNumber = JOptionPane.showInputDialog("Ποια τιμή θέλετε να αλλάξετε; \n"
                    + " 1   Το ΑΦΜ του πωλητή \n 2  Το Όνομα του Πωλητή \n 3   Τη διεύθυνση του πωλητή \n"
                    + " 4   Το τηλέφωνο του πωλητή \n\n");
            if (optionNumber == null) {
                return;
            }
            try {
                indexToEdit = Integer.parseInt(optionNumber);
                if (Integer.parseInt(optionNumber) < 1 || Integer.parseInt(optionNumber) > 4) {
                    throw new NumberFormatException();
                }
                isInValid = false;
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Εισάγετε μια έγκυρη τιμή!");
            }
        } while (isInValid);

        String newValue = null;
        switch (indexToEdit) {
            case 1: //Για αλλαγή ΑΦΜ
                boolean isDuplicate = false;
                do {   // Εισαγωγή ΑΦΜ με έλεγχο για την ύπαρξη κομμάτων.                     
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το ΑΦΜ του πωλητή:");
                    if (newValue.indexOf(",") >= 0) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
                    } else {
                        isInValid = false;
                        isDuplicate = false;
                        for (SalesMan oneSalesMan : salesMen) {  //Έλεγχος για το αν υπάρχει ήδη το ΑΦΜ.
                            if (oneSalesMan.getAfm().equalsIgnoreCase(newValue)) {
                                isDuplicate = true;
                                isInValid = true;
                            }
                        }
                        if (isDuplicate) {
                            JOptionPane.showMessageDialog(null, "Το ΑΦΜ υπάρχει ήδη. \nΕπιλέξτε ένα διαφορετικό.");
                        }
                    }
                } while (isInValid);
                salesManToEdit.setAfm(newValue);
                break;
            case 2:  //Για αλλαγή Ονόματος
                do {   // Εισαγωγή Ονοματεπώνυμου με έλεγχο για την ύπαρξη κομμάτων.                     
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το Ονοματεπώνυμο του πωλητή (χωρίς κόμματα) :");
                    if (newValue.indexOf(",") >= 0) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
                    } else {
                        isInValid = false;
                    }
                } while (isInValid);
                salesManToEdit.setFullName(newValue);
                break;
            case 3:  //Για αλλαγή διεύθυνσης
                do {   // Εισαγωγή Διεύθυνσης με έλεγχο για την ύπαρξη κομμάτων.                     
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε τη Διεύθυνση του πωλητή (χωρίς κόμματα) :");
                    if (newValue.indexOf(",") >= 0) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
                    } else {
                        isInValid = false;
                    }
                } while (isInValid);
                salesManToEdit.setAddress(newValue);
                break;
            case 4: //Για αλλαγή τηλεφώνου
                do {   // Εισαγωγή Τηλεφώνου με έλεγχο για την ύπαρξη κομμάτων.                     
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το Τηλέφωνο του πωλητή (χωρίς κόμματα) :");
                    if (newValue.indexOf(",") >= 0) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Δεν μπορεί να περιέχει κόμμα \",\" ");
                    } else {
                        isInValid = false;
                    }
                } while (isInValid);
                salesManToEdit.setPhone(newValue);
                break;
        } //Εnd fo switch
        FileManager.deleteSalesMenFile();   // Δημιουργία νέων εγγράφων κειμένου με τις αλλαγές.
        for (SalesMan oneSalesMan : salesMen) {
            FileManager.writeSalesManToFile(oneSalesMan);
        }//End for
        JOptionPane.showMessageDialog(null, "Η αλλαγή αποθηκεύτηκε με επιτυχία!");
        start();
    }//End of editSalesMan()

    public void userInputSale() {

        boolean isInValid = false;
        String salesManAfm = null;
        String carSerialNumber = null;
        int intSoldItems = 0;
        float floatPrice = 0f;
        short shortYear = 0;
        byte byteMonth = 0;
        byte byteDay = 0;
        Vector<Car> cars = company.getCarVector();
        Vector<SalesMan> salesMen = company.getSalesManVector();

        do {   // Εισαγωγή ΑΦΜ με έλεγχο για την ύπαρξη του, στους πωλήτές.                     
            salesManAfm = JOptionPane.showInputDialog(null, "Εισάγετε το ΑΦΜ του πωλητή που έκανε την πώληση:");
            if (salesManAfm == null) {    // Για να σταματάει ο βρόγχος όταν ο χρήστης πατήσει το cancel.
                return;
            }
            for (SalesMan oneSalesMan : salesMen) {
                if (oneSalesMan.getAfm().equals(salesManAfm)) {
                    isInValid = false;
                    break;
                } else {
                    isInValid = true;
                } //End if
            } //End For
            if (isInValid) {
                JOptionPane.showMessageDialog(null, "Δεν υπάρχει πωλητής με αυτό το ΑΦΜ. Βάλτε μια έγκυρη τιμή.");
            } //End if
        } while (isInValid);

        do {   // Εισαγωγή Σειριακού αριθμού αυτοκινήτου με έλεγχο για ύπαρξη του, στα αυτοκίνητα.                     
            carSerialNumber = JOptionPane.showInputDialog(null, "Εισάγετε το Σειριακό Αριθμό του Αυτοκινήτου:");
            if (carSerialNumber == null) {    // Για να σταματάει ο βρόγχος όταν ο χρήστης πατήσει το cancel.
                return;
            }
            for (Car oneCar : cars) {
                if (oneCar.getSerialNumber().equals(carSerialNumber)) {
                    isInValid = false;
                    break;
                } else {
                    isInValid = true;
                } //End if
            }// End for
            if (isInValid) {
                JOptionPane.showMessageDialog(null, "Δεν υπάρχει αυτοκίνητο με αυτό το Serial Number. \n Βάλτε μια έγκυρη τιμή.");
            } //End if
        } while (isInValid);

        do {    // Εισαγωγή πλήθους με έλεγχο για σωστή αριθμητική τιμή (θετικός αριθμός).
            String soldItems = JOptionPane.showInputDialog(null, "Εισάγετε το Πλήθος Αυτοκινήτων που πωλήθηκαν:");
            isInValid = false;
            try {
                intSoldItems = Integer.parseInt(soldItems);
                if (intSoldItems < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Βάλτε έγκυρη τιμή!");
            }
        } while (isInValid);

        //Ανεύρεση της τιμή του αυτοκινήτου από το serialNumber του.
        for (Car oneCar : cars) {
            if (oneCar.getSerialNumber().equals(carSerialNumber)) {
                floatPrice = oneCar.getPrice();
            }
        }

        do {    // Εισαγωγή Χρονιάς με έλεγχο για σωστή αριθμητική τιμή (μεγαλύτερος από 1900).
            String year = JOptionPane.showInputDialog(null, "Εισάγετε την Χρονιά της πώλησης Αυτοκινήτου:");
            isInValid = false;
            try {
                shortYear = Short.parseShort(year);
                if (shortYear <= 1900) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Βάλτε έγκυρο αριθμό για Χρονιά Πώλησης!  ( > 1900)");
            }
        } while (isInValid);

        do {    // Εισαγωγή Μήνα Πώλησης με έλεγχο για σωστή αριθμητική τιμή (από 0 έως 12).
            String month = JOptionPane.showInputDialog(null, "Εισάγετε το Μήνα Πώλησης Αυτοκινήτου:");
            isInValid = false;
            try {
                byteMonth = Byte.parseByte(month);
                if (byteMonth <= 0 || byteMonth > 12) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Βάλτε έγκυρο αριθμό για Μήνα Πώλησης!");
            }
        } while (isInValid);

        do {    // Εισαγωγή Ημέρας Πώλησης με έλεγχο για σωστή αριθμητική τιμή (από 0 έως 31).
            String day = JOptionPane.showInputDialog(null, "Εισάγετε την Ημέρα Πώλησης Αυτοκινήτου:");
            isInValid = false;
            try {
                byteDay = Byte.parseByte(day);
                if (byteDay <= 0 || byteDay > 31) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Βάλτε έγκυρο αριθμό για Ημέρα Πώλησης!");
            }
        } while (isInValid);

        Sale sale = new Sale(salesManAfm, carSerialNumber, intSoldItems, floatPrice, shortYear, byteMonth, byteDay);

        company.registerSale(sale);

        JOptionPane.showMessageDialog(
                null, "Η εγγραφή ολοκληρώθηκε επιτυχώς!");
        start();
    } // End of userInputSale()

    public void deleteSale() {
        Vector<Sale> sales = company.getSaleVector();
        boolean isInValid = false;
        int indexToDelete = 0;
        for (Sale oneSale : sales) {
            System.out.printf("%-5d%-15s %-20s %-4d %10.2f€ %-8d %-4d %-4d\n", sales.indexOf(oneSale), oneSale.getSalesManAfm(), oneSale.getCarSerialNumber(),
                    oneSale.getSoldItems(), oneSale.getPrice(), oneSale.getYear(), oneSale.getMonth(), oneSale.getDay());
        }//End for
        do {
            String saleNumber = JOptionPane.showInputDialog("Δείτε στην κονσόλα όλες τις πωλήσεις και \n"
                    + "εισάγετε τον αριθμό πώλησης που θέλετε να διαγραφεί:");
            if (saleNumber == null) {
                return;
            }
            try {
                indexToDelete = Integer.parseInt(saleNumber);
                if (Integer.parseInt(saleNumber) < 0 || Integer.parseInt(saleNumber) > sales.size() - 1) {
                    throw new NumberFormatException();
                }
                isInValid = false;
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Εισάγετε μια έγκυρη τιμή!");
            }
        } while (isInValid);
        sales.removeElementAt(indexToDelete); //Διαγραφή από το Vector.

        FileManager.deleteSalesFile();   // Δημιουργία νέων εγγράφων κειμένου με τις αλλαγές.
        for (Sale oneSale : sales) {
            FileManager.writeSaleToFile(oneSale);
        }//End for
        JOptionPane.showMessageDialog(null, "Η διαγραφή έγινε με επιτυχία.");
    }//End of deleteSale()

    public void editSale() {
        Vector<Sale> sales = company.getSaleVector();
        Sale saleToEdit = null;
        boolean isInValid = false;
        int indexToEdit = 0;
        for (Sale oneSale : sales) {
            System.out.printf("%-5d%-15s %-20s %-4d %10.2f€ %-8d %-4d %-4d\n", sales.indexOf(oneSale), oneSale.getSalesManAfm(), oneSale.getCarSerialNumber(),
                    oneSale.getSoldItems(), oneSale.getPrice(), oneSale.getYear(), oneSale.getMonth(), oneSale.getDay());
        }//End for
        do {
            String saleNumber = JOptionPane.showInputDialog("Δείτε στην κονσόλα όλες τις πωλήσεις και \n"
                    + "εισάγετε τον αριθμό πώλησης που θέλετε να επεξεργαστείτε:");
            if (saleNumber == null) {
                return;
            }
            try {
                indexToEdit = Integer.parseInt(saleNumber);
                if (Integer.parseInt(saleNumber) < 0 || Integer.parseInt(saleNumber) > sales.size() - 1) {
                    throw new NumberFormatException();
                }
                saleToEdit = sales.get(indexToEdit);
                isInValid = false;
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Εισάγετε μια έγκυρη τιμή!");
            }
        } while (isInValid);

        indexToEdit = 0;
        do {
            String optionNumber = JOptionPane.showInputDialog("Ποια τιμή θέλετε να αλλάξετε; \n"
                    + " 1   Το ΑΦΜ του πωλητή \n 2  Το Σειριακό Αριθμό Αυτοκινήτου \n 3   Το πλήθος αυτοκινήτων \n"
                    + " 4   Το έτος πώλησης \n 5   Το μήνα πώλησης \n 6   Την ημέρα πώλησης \n\n");
            if (optionNumber == null) {
                return;
            }
            try {
                indexToEdit = Integer.parseInt(optionNumber);
                if (Integer.parseInt(optionNumber) < 1 || Integer.parseInt(optionNumber) > 6) {
                    throw new NumberFormatException();
                }
                isInValid = false;
            } catch (NumberFormatException ex) {
                isInValid = true;
                JOptionPane.showMessageDialog(null, "Εισάγετε μια έγκυρη τιμή!");
            }
        } while (isInValid);

        String newValue = null;
        Vector<SalesMan> salesMen = company.getSalesManVector();
        Vector<Car> cars = company.getCarVector();

        switch (indexToEdit) {
            case 1: //Για αλλαγή ΑΦΜ
                do {   // Εισαγωγή ΑΦΜ με έλεγχο για την ύπαρξη του στους πωλήτές.                     
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το ΑΦΜ του πωλητή που έκανε την πώληση:");
                    if (newValue == null) {    // Για να σταματάει ο βρόγχος όταν ο χρήστης πατήσει το cancel.
                        return;
                    }
                    for (SalesMan oneSalesMan : salesMen) {
                        if (oneSalesMan.getAfm().equals(newValue)) {
                            isInValid = false;
                            break;
                        } else {
                            isInValid = true;
                        } //End if
                    } //End For
                    if (isInValid) {
                        JOptionPane.showMessageDialog(null, "Δεν υπάρχει πωλητής με αυτό το ΑΦΜ. Βάλτε μια έγκυρη τιμή.");
                    } //End if
                } while (isInValid);
                saleToEdit.setSalesManAfm(newValue);
                break;
            case 2:  //Για αλλαγή serial number                
                do {   // Εισαγωγή Σειριακού αριθμού αυτοκινήτου με έλεγχο για ύπαρξη του στα αυτοκίνητα.                     
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το Σειριακό Αριθμό του Αυτοκινήτου:");
                    if (newValue == null) {    // Για να σταματάει ο βρόγχος όταν ο χρήστης πατήσει το cancel.
                        return;
                    }
                    for (Car oneCar : cars) {
                        if (oneCar.getSerialNumber().equals(newValue)) {
                            isInValid = false;
                            break;
                        } else {
                            isInValid = true;
                        } //End if
                    }// End for
                    if (isInValid) {
                        JOptionPane.showMessageDialog(null, "Δεν υπάρχει αυτοκίνητο με αυτό το Serial Number. \n Βάλτε μια έγκυρη τιμή.");
                    } //End if
                } while (isInValid);
                saleToEdit.setCarSerialNumber(newValue);

                //Ανεύρεση της τιμή του αυτοκινήτου από το serialNumber του.
                Float floatPrice = 0f;
                for (Car oneCar : cars) {
                    if (oneCar.getSerialNumber().equals(newValue)) {
                        floatPrice = oneCar.getPrice();
                    }
                }
                saleToEdit.setPrice(floatPrice);
                break;

            case 3: //Για αλλαγή πλήθους
                do {    // Εισαγωγή πλήθους με έλεγχο για σωστή αριθμητική τιμή (θετικός αριθμός).
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το Πλήθος Αυτοκινήτων που πωλήθηκαν:");
                    isInValid = false;
                    try {
                        int intSoldItems = Integer.parseInt(newValue);
                        if (intSoldItems < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Βάλτε έγκυρη τιμή!");
                    }
                } while (isInValid);
                saleToEdit.setSoldItems(Integer.parseInt(newValue));
                break;
            case 4: // Για αλλαγή του έτους πώλησης
                do {    // Εισαγωγή Χρονιάς με έλεγχο για σωστή αριθμητική τιμή (μεγαλύτερος από 1900).
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε την Χρονιά της πώλησης Αυτοκινήτου:");
                    isInValid = false;
                    try {
                        Short shortYear = Short.parseShort(newValue);
                        if (shortYear <= 1900) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Βάλτε έγκυρο αριθμό για Χρονιά Πώλησης!  ( > 1900)");
                    }
                } while (isInValid);
                saleToEdit.setYear(Short.parseShort(newValue));
                break;

            case 5:  //Για αλλαγή του μήνα
                do {    // Εισαγωγή Μήνα Πώλησης με έλεγχο για σωστή αριθμητική τιμή (από 0 έως 12).
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε το Μήνα Πώλησης Αυτοκινήτου:");
                    isInValid = false;
                    try {
                        byte byteMonth = Byte.parseByte(newValue);
                        if (byteMonth <= 0 || byteMonth > 12) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Βάλτε έγκυρο αριθμό για Μήνα Πώλησης!");
                    }
                } while (isInValid);
                saleToEdit.setMonth(Byte.parseByte(newValue));
                break;

            case 6: //Για αλλαγή της ημέρας
                do {    // Εισαγωγή Ημέρας Πώλησης με έλεγχο για σωστή αριθμητική τιμή (από 0 έως 31).
                    newValue = JOptionPane.showInputDialog(null, "Εισάγετε την Ημέρα Πώλησης Αυτοκινήτου:");
                    isInValid = false;
                    try {
                        byte byteDay = Byte.parseByte(newValue);
                        if (byteDay <= 0 || byteDay > 31) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        isInValid = true;
                        JOptionPane.showMessageDialog(null, "Βάλτε έγκυρο αριθμό για Ημέρα Πώλησης!");
                    }
                } while (isInValid);
                saleToEdit.setDay(Byte.parseByte(newValue));
                break;
        }//End of Switch

        FileManager.deleteSalesFile();   // Δημιουργία νέων εγγράφων κειμένου με τις αλλαγές.
        for (Sale oneSale : sales) {
            FileManager.writeSaleToFile(oneSale);
        }//End for
        JOptionPane.showMessageDialog(null, "Η αλλαγή αποθηκεύτηκε με επιτυχία!");
        start();
    }//End of editSale()

    public void printSalesMenStats() {
        Vector<Sale> salesVector = company.getSaleVector();
        Collections.sort(salesVector, new Comparator() {                      //Εδώ ο ρίζεται ο τρόπος που θα αποφασίζεται ποιο sale 
            //είναι μεγαλύτερο από το άλλο.
            @Override
            public int compare(Object o1, Object o2) {

                Sale sale1 = (Sale) o1;
                Sale sale2 = (Sale) o2;
                if (sale1.getYear() > sale2.getYear()) {
                    return 1;
                } else if (sale1.getYear() < sale2.getYear()) {
                    return -1;
                } else if (sale1.getYear() == sale2.getYear()) {
                    return 0;
                } else {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }
        });

        Vector<Vector<Sale>> salesPerYearVector = new Vector<Vector<Sale>>();
        Vector<Sale> tempVector = new Vector<Sale>();
        Short currentSaleYear = salesVector.get(0).getYear();
        for (Sale oneSale : salesVector) {
            if (oneSale.getYear() == currentSaleYear) {
                tempVector.add(oneSale);
            } else {
                currentSaleYear = oneSale.getYear();
                salesPerYearVector.add(tempVector);  // Διαχωρισμός του αρχικού Vector<Sale> σε ένα Vector<Sale> για κάθε έτος.
                tempVector = new Vector<Sale>();
                tempVector.add(oneSale);
            }
        }
        salesPerYearVector.add(tempVector); // Προσθήκη του τελευταίου Vector που γέμιζε μέσα στο πρώτο σκέλος του if.

        String titleYear = null;
        String resultFormatted = null;
        Short currentYear = 0;
        System.out.println("Αποτελέσματα ανά έτος:");
        for (Vector<Sale> oneVector : salesPerYearVector) {     // Για κάθε ετήσιο Vector<Sale           

            currentYear = oneVector.get(0).getYear();
            titleYear = currentYear.toString();
            System.out.println("\n\t  " + titleYear + "\n\t" + "--------");

            Vector<Hashtable<String, String>> resultVector = sumPerSalesMan(oneVector);  //Εκτύπωση αποτελεσμάτων
            for (Hashtable<String, String> resultTable : resultVector) {
                System.out.printf("%-15s %-30s %-3s %10.2f€ \n", resultTable.get("afm"), resultTable.get("fullName"),
                        resultTable.get("soldItems"), Float.parseFloat(resultTable.get("value")));    //Εκτύπωση αποτελεσμάτων ανά έτος.
            }
        }

        Vector<Vector<Sale>> tempSaleVector = new Vector<Vector<Sale>>(); // Ταξινόμηση των πωλήσεων ανά μήνα
        for (Vector<Sale> oneVector : salesPerYearVector) {     // Για κάθε ετήσιο Vector<Sale>  

            Collections.sort(oneVector, new Comparator() {                      //Εδώ ο ρίζεται ο τρόπος που θα αποφασίζεται ποιο sale 
                //είναι μεγαλύτερο από το άλλο.
                @Override
                public int compare(Object o1, Object o2) {

                    Sale sale1 = (Sale) o1;
                    Sale sale2 = (Sale) o2;
                    if (sale1.getMonth() > sale2.getMonth()) {                //Ταξινόμηση ανά μήνα
                        return 1;
                    } else if (sale1.getMonth() < sale2.getMonth()) {
                        return -1;
                    } else if (sale1.getMonth() == sale2.getMonth()) {
                        return 0;
                    } else {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                }
            });

            tempSaleVector.add(oneVector);
        }
        salesPerYearVector = tempSaleVector;            // Καταχωρούμε το ταξινομημένο ανά μήνες Vector πάνω στο προηγούμενο

        System.out.println("\n\n Αποτελέσματα ανά έτος ΚΑΙ ανά μήνα:");   //Εκτύπωση αποτελεσμάτων ανά έτος ΚΑΙ ανά μήνα.
        tempVector = new Vector<Sale>();
        byte currentSaleMonth = 0;
        for (Vector<Sale> oneVector : salesPerYearVector) {
            currentSaleMonth = oneVector.get(0).getMonth();
            currentSaleYear = oneVector.get(0).getYear();

            System.out.println("\n\t  " + currentSaleYear + "\n\t" + "--------");

            for (Sale oneSale : oneVector) {

                if (oneSale.getMonth() == currentSaleMonth) {
                    //Όσο είμαστε ακόμα στον ίδιο μήνα

                    tempVector.add(oneSale);
                } else {
                    //Μόλις αλλάξουμε μήνα

                    System.out.println("\n\t Για το μήνα " + currentSaleMonth + "o");
                    currentSaleYear = oneSale.getYear();
                    currentSaleMonth = oneSale.getMonth();

                    Vector<Hashtable<String, String>> resultVector = sumPerSalesMan(tempVector);

                    for (Hashtable<String, String> resultTable : resultVector) {   //Εκτύπωση αποτελεσμάτων
                        System.out.printf("%-15s %-30s %-3s %10.2f€ \n", resultTable.get("afm"), resultTable.get("fullName"),
                                resultTable.get("soldItems"), Float.parseFloat(resultTable.get("value")));    //Εκτύπωση αποτελεσμάτων ανά μήνα.
                    }//End of for loop
                    tempVector = new Vector<Sale>();
                    tempVector.add(oneSale);
                }
            } // End of  for loop
            //Εκτύπωση και κάθε φορά που τελειώνει το έτος  
            System.out.println("\n\t Για το μήνα " + currentSaleMonth + "o");
            Vector<Hashtable<String, String>> resultVector = sumPerSalesMan(tempVector);
            for (Hashtable<String, String> resultTable : resultVector) {
                System.out.printf("%-15s %-30s %-3s %10.2f€ \n", resultTable.get("afm"), resultTable.get("fullName"),
                        resultTable.get("soldItems"), Float.parseFloat(resultTable.get("value")));    //Εκτύπωση αποτελεσμάτων ανά μήνα.
            }//End of for loop

            tempVector = new Vector<Sale>();   //Δημιουργία φρέσκου Vector<Sale> κάθε φορά που τελειώνει το έτος.

        }// End of for loop

        JOptionPane.showMessageDialog(
                null, "Δείτε το αποτέλεσμα της αναζήτησης στην κονσόλα.");
        start();
    } //End of printSalesMentStats()

    public void printModelStats() {

        Vector<Sale> salesVector = company.getSaleVector();
        Collections.sort(salesVector, new Comparator() {                      //Εδώ ορίζεται ο τρόπος που θα αποφασίζεται ποιο sale 
            //είναι μεγαλύτερο από το άλλο.
            @Override
            public int compare(Object o1, Object o2) {

                Sale sale1 = (Sale) o1;
                Sale sale2 = (Sale) o2;
                if (sale1.getYear() > sale2.getYear()) {
                    return 1;
                } else if (sale1.getYear() < sale2.getYear()) {
                    return -1;
                } else if (sale1.getYear() == sale2.getYear()) {
                    return 0;
                } else {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }
        });

        Vector<Vector<Sale>> salesPerYearVector = new Vector<Vector<Sale>>();
        Vector<Sale> tempVector = new Vector<Sale>();
        Short currentSaleYear = salesVector.get(0).getYear();
        for (Sale oneSale : salesVector) {
            if (oneSale.getYear() == currentSaleYear) {
                tempVector.add(oneSale);
            } else {
                currentSaleYear = oneSale.getYear();
                salesPerYearVector.add(tempVector);                         // Διαχωρισμός του αρχικού Vector<Sale> σε ένα Vector<Sale> για κάθε έτος.
                tempVector = new Vector<Sale>();
                tempVector.add(oneSale);
            }
        }
        salesPerYearVector.add(tempVector); // Προσθήκη του τελευταίου Vector που γέμιζε μέσα στο πρώτο σκέλος του if.

        String titleYear = null;
        String resultFormatted = null;
        Short currentYear = 0;
        System.out.println("Αποτελέσματα ανά έτος:");
        for (Vector<Sale> oneVector : salesPerYearVector) {     // Για κάθε ετήσιο Vector<Sale           

            currentYear = oneVector.get(0).getYear();
            titleYear = currentYear.toString();
            System.out.println("\n\t  " + titleYear + "\n\t" + "--------");

            Vector<Hashtable<String, String>> resultVector = sumPerCar(oneVector);  //Εκτύπωση αποτελεσμάτων
            for (Hashtable<String, String> resultTable : resultVector) {
                System.out.printf("%-15s %-30s %-30s\n", resultTable.get("serialNumber"), resultTable.get("model"),
                        resultTable.get("soldItems"));    //Εκτύπωση αποτελεσμάτων ανά έτος.
            }
        }

        Vector<Vector<Sale>> tempSaleVector = new Vector<Vector<Sale>>(); // Ταξινόμηση των πωλήσεων ανά μήνα
        for (Vector<Sale> oneVector : salesPerYearVector) {     // Για κάθε ετήσιο Vector<Sale>   

            Collections.sort(oneVector, new Comparator() {                      //Εδώ ο ρίζεται ο τρόπος που θα αποφασίζεται ποιο sale 
                //είναι μεγαλύτερο από το άλλο.
                @Override
                public int compare(Object o1, Object o2) {

                    Sale sale1 = (Sale) o1;
                    Sale sale2 = (Sale) o2;
                    if (sale1.getMonth() > sale2.getMonth()) {                //Ταξινόμηση ανά μήνα
                        return 1;
                    } else if (sale1.getMonth() < sale2.getMonth()) {
                        return -1;
                    } else if (sale1.getMonth() == sale2.getMonth()) {
                        return 0;
                    } else {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                }
            });
            tempSaleVector.add(oneVector);
        }
        salesPerYearVector = tempSaleVector;            // Καταχωρούμε το ταξινομημένο ανά μήνες Vector πάνω στο προηγούμενο

        System.out.println("\n\n Αποτελέσματα ανά έτος ΚΑΙ ανά μήνα:");   //Εκτύπωση αποτελεσμάτων ανά έτος ΚΑΙ ανά μήνα.
        tempVector = new Vector<Sale>();
        byte currentSaleMonth = 0;
        for (Vector<Sale> oneVector : salesPerYearVector) {
            currentSaleMonth = oneVector.get(0).getMonth();
            currentSaleYear = oneVector.get(0).getYear();

            System.out.println("\n\t  " + currentSaleYear + "\n\t" + "--------");

            for (Sale oneSale : oneVector) {

                if (oneSale.getMonth() == currentSaleMonth) {
                    //Όσο είμαστε ακόμα στον ίδιο μήνα

                    tempVector.add(oneSale);
                } else {
                    //Μόλις αλλάξουμε μήνα

                    System.out.println("\n\t Για το μήνα " + currentSaleMonth + "o");
                    currentSaleYear = oneSale.getYear();
                    currentSaleMonth = oneSale.getMonth();

                    Vector<Hashtable<String, String>> resultVector = sumPerCar(tempVector);   // Εκτύπωση αποτελεσμάτων

                    for (Hashtable<String, String> resultTable : resultVector) {
                        System.out.printf("%-15s %-30s %-30s\n", resultTable.get("serialNumber"), resultTable.get("model"),
                                resultTable.get("soldItems"));    //Εκτύπωση αποτελεσμάτων ανά μήνα.
                    }//End of for loop
                    tempVector = new Vector<Sale>();
                    tempVector.add(oneSale);
                }
            } // End of  for loop
            //Εκτύπωση και κάθε φορά που τελειώνει το έτος  
            System.out.println("\n\t Για το μήνα " + currentSaleMonth + "o");
            Vector<Hashtable<String, String>> resultVector = sumPerCar(tempVector);
            for (Hashtable<String, String> resultTable : resultVector) {
                System.out.printf("%-15s %-30s %-30s\n", resultTable.get("serialNumber"), resultTable.get("model"),
                        resultTable.get("soldItems"));    //Εκτύπωση αποτελεσμάτων ανά μήνα.
            }//End of for loop

            tempVector = new Vector<Sale>();   //Δημιουργία φρέσκου Vector<Sale> κάθε φορά που τελειώνει το έτος.

        }// End of for loop

        JOptionPane.showMessageDialog(
                null, "Δείτε το αποτέλεσμα της αναζήτησης στην κονσόλα.");
        start();

    } //End of printModelStats()

    public Vector<Hashtable<String, String>> sumPerSalesMan(Vector<Sale> salesPerYear) {

        Vector<Hashtable<String, String>> resultVector = new Vector<Hashtable<String, String>>();
        Hashtable<String, String> resultTable = null;
        Vector<SalesMan> salesMen = company.getSalesManVector();

        boolean found = false;
        String previousItems = null;
        int currentItems = 0;
        String previousValue = null;
        float currentValue = 0f;

        for (SalesMan aSalesMan : salesMen) {
            resultTable = new Hashtable<String, String>();
            found = false;
            previousItems = null;
            currentItems = 0;
            previousValue = null;
            currentValue = 0f;

            for (Sale oneSale : salesPerYear) {
                if (aSalesMan.getAfm().equals(oneSale.getSalesManAfm())) {
                    found = true;
                    resultTable.put("afm", oneSale.getSalesManAfm());
                    resultTable.put("fullName", aSalesMan.getFullName());
                    previousItems = resultTable.put("soldItems", Integer.toString(oneSale.getSoldItems()));
                    if (previousItems != null) {
                        currentItems = oneSale.getSoldItems() + Integer.parseInt(previousItems);
                        resultTable.put("soldItems", Integer.toString(currentItems));
                    }
                    currentValue = oneSale.getPrice() * oneSale.getSoldItems();
                    previousValue = resultTable.put("value", Float.toString(currentValue));
                    if (previousValue != null) {
                        currentValue = (oneSale.getPrice() * oneSale.getSoldItems()) + Float.parseFloat(previousValue);
                        resultTable.put("value", Float.toString(currentValue));
                    }
                } // End of first if
            } // End of for salesPerYear
            if (!found) {
                resultTable.put("afm", aSalesMan.getAfm());
                resultTable.put("fullName", aSalesMan.getFullName());
                resultTable.put("soldItems", "0");
                resultTable.put("value", "0");
            }
            resultVector.add(resultTable);
        } //End of for salesMen
        return resultVector;
    } //End of sumPerSalesMan()

    public Vector<Hashtable<String, String>> sumPerCar(Vector<Sale> salesPerYear) {
        Vector<Hashtable<String, String>> resultVector = new Vector<Hashtable<String, String>>();
        Hashtable<String, String> resultTable = null;
        Vector<Car> cars = company.getCarVector();

        boolean found = false;
        String previousItems = null;
        int currentItems = 0;

        for (Car aCar : cars) {
            resultTable = new Hashtable<String, String>();
            found = false;
            previousItems = null;
            currentItems = 0;

            for (Sale oneSale : salesPerYear) {
                if (aCar.getSerialNumber().equals(oneSale.getCarSerialNumber())) {
                    found = true;
                    resultTable.put("serialNumber", oneSale.getCarSerialNumber());
                    resultTable.put("model", aCar.getModel());
                    previousItems = resultTable.put("soldItems", Integer.toString(oneSale.getSoldItems()));
                    if (previousItems != null) {
                        currentItems = oneSale.getSoldItems() + Integer.parseInt(previousItems);
                        resultTable.put("soldItems", Integer.toString(currentItems));
                    }
                } // End of first if                
            } // End of for salesPerSerialNumber
            if (!found) {
                resultTable.put("serialNumber", aCar.getSerialNumber());
                resultTable.put("model", aCar.getModel());
                resultTable.put("soldItems", "0");
            }
            resultVector.add(resultTable);
        } //End of for Cars     

        return resultVector;
    } //End of sumPerCar()

} //End of Class
