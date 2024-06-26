import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Month;
import java.time.Year;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Main_Program extends UI_Config implements ActionListener, KeyListener, MouseListener {
    // VARIABLES LIST
    private Storage st = new Storage("Order_list.txt");
    private JTable order_list;
    private DefaultTableModel order_model;
    private Vector<String> columns, rows;
    private TableRowSorter<DefaultTableModel> order_sort;

    private JLabel filterSearch;
    private JTextField filterText;

    private JComboBox<Month> month_delivery;
    private JComboBox<Integer> day_delivery;
    private JComboBox<Integer> year_delivery;
    private int current_year = Year.now().getValue();
    private int current_month = java.time.LocalDate.now().getMonthValue();
    private int current_day = java.time.LocalDate.now().getDayOfMonth();

    private JButton add_order, delete_order, update_order, clear_order, status_order, complete_order, exit_program;

    private JLabel nameID, nameClient, nameDesc, namePrice, namePrio;
    private JTextField textID, textClient, textDesc, textPrice;
    private JComboBox<String> textPrio;

    // FONT LIST
    final private Font headerFont = new Font("Segoe UI Semibold", Font.BOLD, 20);
    final private Font subFont = new Font("Segoe UI", Font.PLAIN, 14);

    private JPanel orderPanel, current_datePanel, pick_up_panel, orderButtonPanel, statusButtonPanel, filterPanel, listPanel;

    // PROGRAM CODE
    public Main_Program() {
        startup();

        orderInfo();
        add(orderPanel).setBounds(10, 20, 300, 250);

        current_date();
        add(current_datePanel).setBounds(10, 270, 300, 50);

        pick_up_date();
        add(pick_up_panel).setBounds(10, 320, 300, 50);

        orderButton();
        add(orderButtonPanel).setBounds(10, 370, 300, 50);

        statusButton();
        add(statusButtonPanel).setBounds(360, 520, 1180, 50);

        add(filterSearch()).setBounds(1570, 20, 300, 50);
        add(listTable()).setBounds(360, 20, 1180, 500);
        add(frame()).setBounds(20, 20, 1920, 1080);



        
        textID.setText(randomID());

        resetData();


        

        // BUTTON LISTENERS
        add_order.addActionListener(this);
        update_order.addActionListener(this);
        clear_order.addActionListener(this);
        status_order.addActionListener(this);
        complete_order.addActionListener(this);
        delete_order.addActionListener(this);
        exit_program.addActionListener(this);

        order_list.addMouseListener(this);
        filterText.addKeyListener(this);

        // FILE UTILIZATION
        st = new Storage("Order_list.txt");
        st.displayRecords(order_model);

        // UI RESOLUTION
        setMyFrame("ORDER MANAGEMENT SYSTEM", 1920, 640, true, DISPOSE_ON_CLOSE, false);
        setLocationRelativeTo(null);
    }

    // OBJECT INITIALIZATION
    public void startup() {
        nameID = new JLabel("Order ID: ");
        nameClient = new JLabel("Client Name: ");
        nameDesc = new JLabel("Description: ");
        namePrice = new JLabel("Price: ");
        namePrio = new JLabel("Priority: ");

        textID = new JTextField(20);
        textID.setEditable(false);
        textClient = new JTextField(20);
        textDesc = new JTextField(20);
        textPrice = new JTextField(20);
        textPrio = new JComboBox<>();

        textPrio.addItem("High");
        textPrio.addItem("Medium");
        textPrio.addItem("Low");

        JLabel logo = new JLabel(new ImageIcon("LOGO\\Logo.jpg"));
        ImageIcon imageIcon = new ImageIcon(((ImageIcon) logo.getIcon()).getImage().getScaledInstance(340, 225, Image.SCALE_DEFAULT));
        logo.setIcon(imageIcon);
        add(logo).setBounds(1550, 100, 340, 225);

    }

    // RANDOM ID NUMBERS
    public String randomID() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }

    // ORDER INFORMATION
    public void orderInfo() {
        orderPanel = new JPanel();

        orderPanel.setLayout(new GridLayout(5, 2, 4, 2));
        orderPanel.setBorder(BorderFactory.createTitledBorder("ORDER INFORMATION"));

        orderPanel.add(nameID);
        orderPanel.add(textID);

        orderPanel.add(nameClient);
        orderPanel.add(textClient);

        orderPanel.add(nameDesc);
        orderPanel.add(textDesc);

        orderPanel.add(namePrice);
        orderPanel.add(textPrice);

        orderPanel.add(namePrio);
        orderPanel.add(textPrio);
    }

    public void current_date() {
        current_datePanel = new JPanel();
        current_datePanel.setLayout(new GridLayout(1, 3, 4, 2));
        current_datePanel.setBorder(BorderFactory.createTitledBorder("CURRENT DATE"));

        JLabel current_date = new JLabel(current_month + "/" + current_day + "/" + current_year);
        current_datePanel.add(current_date);
    }

    public void pick_up_date() {
        pick_up_panel = new JPanel();
        pick_up_panel.setLayout(new GridLayout(1, 5, 4, 2));
        pick_up_panel.setBorder(BorderFactory.createTitledBorder("PICK-UP DATE"));

        month_delivery = new JComboBox<>();
        day_delivery = new JComboBox<>();
        year_delivery = new JComboBox<>();
        year_delivery.addItem(2024);

        pick_up_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
        pick_up_panel.setBorder(BorderFactory.createTitledBorder("PICK-UP DATE"));

        pick_up_panel.add(month_delivery);
        pick_up_panel.add(day_delivery);
        pick_up_panel.add(year_delivery);

        for (int i = 1; i <= 31; i++) {
            day_delivery.addItem(i);
        }
        for (int i = current_month; i <= 12; i++) {
            month_delivery.addItem(Month.of(i));
        }
    }

    public void orderButton() {
        orderButtonPanel = new JPanel();
        orderButtonPanel.setLayout(new GridLayout(1, 3, 4, 2));
        orderButtonPanel.setBorder(BorderFactory.createTitledBorder("ORDER BUTTONS"));

        add_order = new JButton("ADD");
        update_order = new JButton("UPDATE");
        clear_order = new JButton("CLEAR");

        orderButtonPanel.add(add_order);
        orderButtonPanel.add(update_order);
        orderButtonPanel.add(clear_order);
    }

    public void statusButton() {
        statusButtonPanel = new JPanel();
        statusButtonPanel.setLayout(new GridLayout(1, 3, 4, 2));
        statusButtonPanel.setBorder(BorderFactory.createTitledBorder("STATUS BUTTONS"));

        status_order = new JButton("STATUS ORDER");
        complete_order = new JButton("COMPLETE ORDER");
        delete_order = new JButton("DELETE ORDER");
        exit_program = new JButton("EXIT PROGRAM");

        statusButtonPanel.add(status_order);
        statusButtonPanel.add(complete_order);
        statusButtonPanel.add(delete_order);
        statusButtonPanel.add(exit_program);

        status_order.setEnabled(false);
        complete_order.setEnabled(false);
        delete_order.setEnabled(false);
    }

    public JPanel filterSearch() {
        filterPanel = new JPanel();
        filterSearch = new JLabel("Search: ");
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
        filterPanel.setBorder(BorderFactory.createTitledBorder("FILTER ORDER"));

        filterText = new JTextField(20);

        filterPanel.add(filterSearch);
        filterPanel.add(filterText);

        return filterPanel;
    }

    public JPanel frame() {
        JPanel frame = new JPanel();
        frame.setLayout(new BorderLayout());
        frame.setBorder(BorderFactory.createTitledBorder("BASIC ORDER TRANSCRIPT SYSTEM"));

        return frame;
    }

    public JPanel listTable() {
        listPanel = new JPanel();
        order_list = new JTable();
        order_model = new DefaultTableModel();

        listPanel.setLayout(new BorderLayout());
        listPanel.add(new JScrollPane(order_list), BorderLayout.CENTER);

        String column_header[] = { "ID", "Client Name", "Description", "Price", "Priority", "Delivery Month", "Delivery Day", "Delivery Year" };

        columns = new Vector<>();

        for (String val : column_header) {
            columns.add(val);
        }

        order_model.setColumnIdentifiers(columns);
        order_list.setModel(order_model);

        return listPanel;
    }

    public void getData() {
        rows = new Vector<>();
        rows.add(textID.getText());
        rows.add(textClient.getText());
        rows.add(textDesc.getText());
        rows.add(textPrice.getText());
        rows.add(textPrio.getSelectedItem().toString());
        rows.add(month_delivery.getSelectedItem().toString());
        rows.add(day_delivery.getSelectedItem().toString());
        rows.add(year_delivery.getSelectedItem().toString());
    }

    public static void main(String[] args) {
        new Main_Program();
    }

    public void resetData() {
        textID.setText(randomID());
        textClient.setText("");
        textDesc.setText("");
        textPrice.setText("");

        status_order.setEnabled(false);
        complete_order.setEnabled(false);
        delete_order.setEnabled(false);
    }

    public void order_click() {
        add_order.setEnabled(false);
        update_order.setEnabled(true);
        delete_order.setEnabled(true);
        status_order.setEnabled(true);
        complete_order.setEnabled(true);
    }

    public void process_data() {
        String data = "";
        for (int i = 0; i < order_model.getRowCount(); i++) {
            for (int j = 0; j < order_model.getColumnCount(); j++) {
                data += order_model.getValueAt(i, j) + "#";
            }
            data += "\n";
        }
        st.storeToFile(data);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(order_list)) {
            int f = order_list.getSelectedRow();

            textID.setText(order_model.getValueAt(f, 0).toString());
            textClient.setText(order_model.getValueAt(f, 1).toString());
            textDesc.setText(order_model.getValueAt(f, 2).toString());
            textPrice.setText(order_model.getValueAt(f, 3).toString());
            textPrio.setSelectedItem(order_model.getValueAt(f, 4).toString());
            month_delivery.setSelectedItem(Month.valueOf(order_model.getValueAt(f, 5).toString()));
            day_delivery.setSelectedItem(Integer.parseInt(order_model.getValueAt(f, 6).toString()));
            year_delivery.setSelectedItem(Integer.parseInt(order_model.getValueAt(f, 7).toString()));

            order_click();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Unimplemented method
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Unimplemented method
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Unimplemented method
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Unimplemented method
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(textPrice)) {
            if ((e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z')) {
                e.consume();
            }
        } else if (e.getSource().equals(textClient) || e.getSource().equals(textDesc)) {
            if (!((e.getKeyChar() <= 'z' && e.getKeyChar() >= 'a') || (e.getKeyChar() <= 'Z' && e.getKeyChar() >= 'A'))) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Unimplemented method
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(filterText)) {
            String filter = filterText.getText();

            order_sort = new TableRowSorter<>(order_model);
            order_list.setRowSorter(order_sort);
            order_sort.setRowFilter(RowFilter.regexFilter(filter, 0, 1));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(add_order)) {
            getData();
            order_model.addRow(rows);
            getData();
            resetData();
        } else if (e.getSource().equals(update_order)) {
            int f = order_list.getSelectedRow();
            getData();
            for (int col = 0; col < order_list.getColumnCount(); col++) {
                order_list.setValueAt(rows.get(col), f, col);
            }
            resetData();
        } else if (e.getSource().equals(clear_order)) {
            resetData();
        } else if (e.getSource().equals(status_order)) {
            int f = order_list.getSelectedRow();
            order_model.setValueAt("On-Going", f, 4);
            process_data();
            resetData();
        } else if (e.getSource().equals(complete_order)) {
            int f = order_list.getSelectedRow();
            order_model.setValueAt("Completed", f, 4);
            process_data();
            resetData();
        } else if (e.getSource().equals(delete_order)) {
            int f = order_list.getSelectedRow();
            order_model.removeRow(f);
            process_data();
            resetData();
        } else if (e.getSource().equals(exit_program)) {
            System.exit(0);
        }
    }
}

