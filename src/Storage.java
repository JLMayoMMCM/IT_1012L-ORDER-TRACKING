import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Storage {
    private File file = null;
    private FileWriter fWrite = null;
    private FileReader fRead = null;
    private Scanner scan = null;
    private Vector<String> row;

    // DEFAULT CONSTRUCTOR
    public Storage() {
    }

    // PARAMETERIZED CONSTRUCTOR
    public Storage(String filename) {
        file = new File(filename);
    }

    // SETS THE FILENAME FOR STORAGE OPERATIONS
    public void setFilename(String filename) {
        file = new File(filename);
    }

    // GETS THE FILENAME
    public String getFilename() {
        return file.getName();
    }

    // DISPLAYS AN ERROR MESSAGE DIALOG
    public void errorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // STORES DATA TO THE SPECIFIED FILE
    public void storeToFile(String data) {
        try {
            fWrite = new FileWriter(file);
            fWrite.write(data);
            fWrite.flush();
            fWrite.close();
        } catch (Exception e) {
            errorMessage("Error 101: storeToFile\n" + e.getMessage());
        }
    }

    // DISPLAYS RECORDS FROM THE FILE TO THE PROVIDED TABLE MODEL
    public void displayRecords(DefaultTableModel model) {
        try {
            fRead = new FileReader(file);
            scan = new Scanner(fRead);

            String[] data;

            while (scan.hasNext()) {
                data = scan.nextLine().split("#");
                row = new Vector<>();
                for (int i = 0; i < model.getColumnCount(); i++) {
                    row.add(data[i]);
                }
                model.addRow(row);
            }
            fRead.close();
        } catch (Exception e) {
            errorMessage("Error 102: displayRecords\n" + e.getMessage());
        }
    }
}

// OBJECT-ORIENTED PROGRAMMING (OOP) IMPLEMENTATIONS:
// 1. ENCAPSULATION: THE PRIVATE VARIABLES file, fWrite, fRead, AND scan ARE ENCAPSULATED WITHIN THE CLASS, AND PUBLIC METHODS ARE PROVIDED TO INTERACT WITH THEM.
// 2. POLYMORPHISM: METHOD OVERLOADING IS USED IN THE CONSTRUCTORS TO PROVIDE MULTIPLE WAYS OF INSTANTIATING THE CLASS.
// 3. ABSTRACTION: THE CLASS PROVIDES SIMPLE INTERFACES (METHODS) FOR STORING DATA TO A FILE AND DISPLAYING RECORDS, HIDING THE COMPLEXITY OF THE IMPLEMENTATION.
// 4. REUSABILITY: THE CLASS CAN BE EASILY REUSED IN OTHER PARTS OF THE PROGRAM OR IN OTHER PROJECTS DUE TO ITS GENERIC FUNCTIONALITY.
