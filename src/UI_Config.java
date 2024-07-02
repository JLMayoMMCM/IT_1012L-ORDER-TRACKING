/**
 * The UI_Config class represents a custom JFrame with various methods to configure its properties.
 * It inherits from the JFrame class, extending its functionality.
 * The class provides methods to set the frame resolution, frame title, visibility, close operation, and resizability.
 * It also provides a method to set a background image for the frame.
 * The class demonstrates object-oriented programming concepts such as inheritance, encapsulation, polymorphism, and abstraction.
 */

import javax.swing.*;


public class UI_Config extends JFrame {
    private int H, W;

    // DEFAULT CONSTRUCTOR
    public UI_Config() {
        super();
        H = 1280;
        W = 720;
        SetResolution(H, W);
    }

    // PARAMETERIZED CONSTRUCTOR
    public UI_Config(String title, int width, int height, boolean visible) {
        super(title);
        H = height;
        W = width;
        SetResolution(H, W);
        setVisible(visible);
    }

    // SETS FRAME RESOLUTION AND OTHER BASIC PROPERTIES
    public void SetResolution(int height, int width) {
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // SETS FRAME TITLE AND RESOLUTION
    public void setMyFrame(String title, int width, int height) {
        setTitle(title);
        SetResolution(height, width);
    }

    // SETS FRAME TITLE, RESOLUTION, AND VISIBILITY
    public void setMyFrame(String title, int width, int height, boolean visible) {
        setMyFrame(title, width, height);
        setVisible(visible);
    }

    // SETS FRAME TITLE, RESOLUTION, VISIBILITY, AND CLOSE OPERATION
    public void setMyFrame(String title, int width, int height, boolean visible, int close_operation) {
        setMyFrame(title, width, height, visible);
        setDefaultCloseOperation(close_operation);
    }

    // SETS FRAME TITLE, RESOLUTION, VISIBILITY, CLOSE OPERATION, AND RESIZABILITY
    public void setMyFrame(String title, int width, int height, boolean visible, int close_operation, boolean resize) {
        setMyFrame(title, width, height, visible, close_operation);
        setResizable(resize);
    }

    // SETS BACKGROUND IMAGE FOR THE FRAME
    public JPanel setBackgroundImage(String file) {
        JPanel panelBG = new JPanel();
        JLabel img = new JLabel(new ImageIcon(file));
        panelBG.add(img);
        return panelBG;
    }
}

// OBJECT-ORIENTED PROGRAMMING (OOP) IMPLEMENTATIONS:
// 1. INHERITANCE: THE UI_Config CLASS INHERITS FROM JFrame, EXTENDING ITS FUNCTIONALITY.
// 2. ENCAPSULATION: THE PRIVATE VARIABLES H AND W ARE ENCAPSULATED WITHIN THE CLASS, AND PUBLIC METHODS ARE PROVIDED TO INTERACT WITH THEM.
// 3. POLYMORPHISM: METHOD OVERLOADING IS USED IN setMyFrame TO PROVIDE MULTIPLE VERSIONS OF THE METHOD.
// 4. ABSTRACTION: THE CLASS PROVIDES SIMPLE INTERFACES (METHODS) FOR SETTING FRAME PROPERTIES AND BACKGROUND IMAGE, HIDING THE COMPLEXITY OF THE IMPLEMENTATION.
