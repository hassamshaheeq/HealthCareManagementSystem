package com.healthcare.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import com.healthcare.logic.DataManager;
import com.healthcare.logic.PrescriptionManager;
import com.healthcare.model.Prescription;
import java.util.List;

public class PrescriptionPanel extends JPanel {
    private JTable prescriptionTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private PrescriptionManager prescriptionManager;

    public PrescriptionPanel() {
        this.prescriptionManager = new PrescriptionManager();
        setLayout(new BorderLayout(15, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Pharmacy & Prescriptions");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_HIGHLIGHT);
        header.add(title, BorderLayout.WEST);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controls.setOpaque(false);

        searchField = new JTextField(12);
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.BLACK);
        searchField.setCaretColor(Color.BLACK);
        searchField.setBorder(UIStyles.FIELD_BORDER);

        JButton btnAdd = UIStyles.createStyledButton("Issue New RX", UIStyles.ACCENT_COLOR);

        controls.add(searchField);
        controls.add(btnAdd);
        header.add(controls, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        String[] columns = { "ID", "Patient ID", "Clinician ID", "Medication", "Dosage", "Status", "Date" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        prescriptionTable = new JTable(tableModel);
        UIStyles.styleTable(prescriptionTable);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        prescriptionTable.setRowSorter(sorter);

        searchField.addCaretListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(prescriptionTable);
        scrollPane.getViewport().setBackground(UIStyles.BG_MAIN);
        scrollPane.setBorder(UIStyles.PANEL_BORDER);
        add(scrollPane, BorderLayout.CENTER);

        loadPrescriptionData();

        btnAdd.addActionListener(e -> showIssuePrescriptionDialog());
    }

    private void loadPrescriptionData() {
        tableModel.setRowCount(0);
        List<Prescription> prescriptions = DataManager.getInstance().getPrescriptions();
        for (Prescription p : prescriptions) {
            tableModel.addRow(new Object[] {
                    p.getPrescriptionID(), p.getPatientID(), p.getClinicianID(),
                    p.getMedication(), p.getDosage(), p.getPharmacyStatus(), p.getDateIssued()
            });
        }
    }

    private void showIssuePrescriptionDialog() {
        JTextField idField = new JTextField();
        JTextField patientIdField = new JTextField();
        JTextField clinicianIdField = new JTextField();
        JTextField medicationField = new JTextField();
        JTextField dosageField = new JTextField();
        JTextField freqField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField statusField = new JTextField("Pending");

        Object[] message = {
                "ID:", idField, "Patient ID:", patientIdField, "Clinician ID:", clinicianIdField,
                "Medication:", medicationField, "Dosage:", dosageField, "Freq:", freqField,
                "Duration:", durationField, "Status:", statusField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Issue Prescription", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Prescription p = new Prescription(idField.getText(), patientIdField.getText(), clinicianIdField.getText(),
                    medicationField.getText(), dosageField.getText(), freqField.getText(),
                    durationField.getText(), statusField.getText(), new java.util.Date().toString());
            prescriptionManager.createPrescription(p);
            loadPrescriptionData();
        }
    }
}
