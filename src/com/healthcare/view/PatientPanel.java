package com.healthcare.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import com.healthcare.logic.DataManager;
import com.healthcare.model.Patient;
import java.util.List;

public class PatientPanel extends JPanel {
    private JTable patientTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public PatientPanel() {
        setLayout(new BorderLayout(15, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Patient Records Management");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_HIGHLIGHT);
        header.add(title, BorderLayout.WEST);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controls.setOpaque(false);

        searchField = new JTextField(15);
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.BLACK);
        searchField.setCaretColor(Color.BLACK);
        searchField.setBorder(UIStyles.FIELD_BORDER);

        JButton btnAdd = UIStyles.createStyledButton("Register Patient", UIStyles.PRIMARY_COLOR);
        JButton btnDelete = UIStyles.createStyledButton("Delete", UIStyles.DANGER_COLOR);

        controls.add(searchField);
        controls.add(btnAdd);
        controls.add(btnDelete);
        header.add(controls, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Table
        String[] columns = { "ID", "NHS No", "First Name", "Last Name", "DoB", "Gender", "Address", "Phone" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        patientTable = new JTable(tableModel);
        UIStyles.styleTable(patientTable);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        patientTable.setRowSorter(sorter);

        searchField.addCaretListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(patientTable);
        scrollPane.getViewport().setBackground(UIStyles.BG_MAIN);
        scrollPane.setBorder(UIStyles.PANEL_BORDER);
        add(scrollPane, BorderLayout.CENTER);

        loadPatientData();

        btnAdd.addActionListener(e -> showAddPatientDialog());
        btnDelete.addActionListener(e -> deleteSelectedPatient());
    }

    private void loadPatientData() {
        tableModel.setRowCount(0);
        List<Patient> patients = DataManager.getInstance().getPatients();
        for (Patient p : patients) {
            tableModel.addRow(new Object[] {
                    p.getPatientID(), p.getNhsNumber(), p.getFirstName(), p.getLastName(),
                    p.getDateOfBirth(), p.getGender(), p.getAddress(), p.getPhoneNumber()
            });
        }
    }

    private void deleteSelectedPatient() {
        int row = patientTable.getSelectedRow();
        if (row != -1) {
            String id = (String) patientTable.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Permanently delete patient " + id + "?", "Confirm Wipe",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DataManager.getInstance().deletePatient(id);
                loadPatientData();
            }
        }
    }

    private void showAddPatientDialog() {
        JTextField idField = new JTextField();
        JTextField nhsField = new JTextField();
        JTextField fnameField = new JTextField();
        JTextField lnameField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField addrField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] message = {
                "ID:", idField, "NHS Number:", nhsField, "First Name:", fnameField,
                "Last Name:", lnameField, "DoB (YYYY-MM-DD):", dobField, "Gender:", genderField,
                "Address:", addrField, "Phone:", phoneField, "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Register Patient", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Patient p = new Patient(idField.getText(), nhsField.getText(), fnameField.getText(),
                    lnameField.getText(), dobField.getText(), genderField.getText(),
                    addrField.getText(), phoneField.getText(), emailField.getText());
            DataManager.getInstance().addPatient(p);
            loadPatientData();
        }
    }
}
