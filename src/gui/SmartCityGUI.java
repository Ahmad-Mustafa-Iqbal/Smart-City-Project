package gui;
import model.*;
import interfaces.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SmartCityGUI extends JFrame {

    private CityRepository<CityResource> repository;
    private JTable resourceTable;
    private DefaultTableModel tableModel;

    public SmartCityGUI() {
        repository = new CityRepository<>();
        setTitle("Smart City Resource Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Table
        String[] columns = {"ID", "Location", "Status", "Type", "Details"};
        tableModel = new DefaultTableModel(columns, 0);
        resourceTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resourceTable);

        // Buttons
        JButton addBtn = new JButton("Add Resource");
        JButton alertBtn = new JButton("Send Alert");
        JButton reportBtn = new JButton("Generate Report");
        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");

        // Panel for buttons
        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(alertBtn);
        btnPanel.add(reportBtn);
        btnPanel.add(saveBtn);
        btnPanel.add(loadBtn);

        // Add listeners
        addBtn.addActionListener(e -> showAddDialog());
        alertBtn.addActionListener(e -> sendAlert());
        reportBtn.addActionListener(e -> generateReports());
        saveBtn.addActionListener(e -> saveResources());
        loadBtn.addActionListener(e -> loadResources());

        // Layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void showAddDialog() {
        String[] options = {"Transport Unit", "Power Station", "Emergency Service"};
        String type = (String) JOptionPane.showInputDialog(this, "Select Resource Type:", "Add Resource",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (type == null) return;

        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField idField = new JTextField();
        JTextField locField = new JTextField();
        JTextField statusField = new JTextField();

        panel.add(new JLabel("Resource ID:")); panel.add(idField);
        panel.add(new JLabel("Location:")); panel.add(locField);
        panel.add(new JLabel("Status:")); panel.add(statusField);

        CityResource resource = null;

        switch (type) {
            case "Transport Unit" -> {
                JTextField passField = new JTextField();
                JTextField fuelField = new JTextField();
                panel.add(new JLabel("Passengers:")); panel.add(passField);
                panel.add(new JLabel("Fuel Cost/km:")); panel.add(fuelField);

                int result = JOptionPane.showConfirmDialog(this, panel, "Add Transport Unit", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    resource = new TransportUnit(idField.getText(), locField.getText(), statusField.getText(),
                            Integer.parseInt(passField.getText()), Double.parseDouble(fuelField.getText()));
                }
            }
            case "Power Station" -> {
                JTextField outputField = new JTextField();
                panel.add(new JLabel("Energy Output:")); panel.add(outputField);

                int result = JOptionPane.showConfirmDialog(this, panel, "Add Power Station", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    resource = new PowerStation(idField.getText(), locField.getText(), statusField.getText(),
                            Double.parseDouble(outputField.getText()));
                }
            }
            case "Emergency Service" -> {
                JTextField typeField = new JTextField();
                JTextField timeField = new JTextField();
                panel.add(new JLabel("Service Type:")); panel.add(typeField);
                panel.add(new JLabel("Response Time:")); panel.add(timeField);

                int result = JOptionPane.showConfirmDialog(this, panel, "Add Emergency Service", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    resource = new EmergencyService(idField.getText(), locField.getText(), statusField.getText(),
                            typeField.getText(), Integer.parseInt(timeField.getText()));
                }
            }
        }

        if (resource != null) {
            repository.addResource(resource);
            updateTable();
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Clear existing rows
        for (CityResource r : repository.getAllResources()) {
            String type = r.getClass().getSimpleName();
            tableModel.addRow(new Object[]{r.getResourceID(), r.getLocation(), r.getStatus(), type, r.toString()});
        }
    }

    private void sendAlert() {
        int row = resourceTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a resource first.");
            return;
        }

        String id = (String) tableModel.getValueAt(row, 0);
        CityResource resource = repository.findById(id);

        if (resource instanceof Alertable alertable) {
            String msg = JOptionPane.showInputDialog(this, "Enter Alert Message:");
            if (msg != null && !msg.trim().isEmpty()) {
                alertable.sendEmergencyAlert(msg);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selected resource does not support alerts.");
        }
    }

    private void generateReports() {
        StringBuilder sb = new StringBuilder("=== Usage Reports ===\n");
        for (CityResource r : repository.getAllResources()) {
            if (r instanceof Reportable reportable) {
                sb.append(reportable.generateUsageReport()).append("\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Usage Reports", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveResources() {
        FileManager.saveToFile(repository.getAllResources(), "resources.dat");
        JOptionPane.showMessageDialog(this, "Resources saved to resources.dat");
    }

    private void loadResources() {
        ArrayList<CityResource> loaded = FileManager.loadFromFile("resources.dat");
        for (CityResource res : loaded) {
            repository.addResource(res);
        }
        updateTable();
        JOptionPane.showMessageDialog(this, "Resources loaded from resources.dat");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartCityGUI().setVisible(true));
    }
}
