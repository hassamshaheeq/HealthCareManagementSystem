package com.healthcare.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import com.healthcare.logic.DataManager;
import com.healthcare.model.Clinician;
import java.util.List;

public class ClinicianPanel extends JPanel {
    private JTable clinicianTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public ClinicianPanel() {
        setLayout(new BorderLayout(15, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Clinician Directory");
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

        JButton btnAdd = UIStyles.createStyledButton("Add Clinician", UIStyles.PRIMARY_COLOR);
        JButton btnDelete = UIStyles.createStyledButton("Delete", UIStyles.DANGER_COLOR);

        controls.add(searchField);
        controls.add(btnAdd);
        controls.add(btnDelete);
        header.add(controls, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Table
        String[] columns = { "Clinician ID", "Registry ID", "First Name", "Last Name", "Role", "Specialty", "Contact" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        clinicianTable = new JTable(tableModel);
        UIStyles.styleTable(clinicianTable);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        clinicianTable.setRowSorter(sorter);

        searchField.addCaretListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(clinicianTable);
        scrollPane.getViewport().setBackground(UIStyles.BG_MAIN);
        scrollPane.setBorder(UIStyles.PANEL_BORDER);
        add(scrollPane, BorderLayout.CENTER);

        loadClinicianData();

        btnAdd.addActionListener(e -> showAddClinicianDialog());
        btnDelete.addActionListener(e -> deleteSelectedClinician());
    }

    private void loadClinicianData() {
        tableModel.setRowCount(0);
        List<Clinician> clinicians = DataManager.getInstance().getClinicians();
        for (Clinician c : clinicians) {
            tableModel.addRow(new Object[] {
                    c.getClinicianID(), c.getRegistryID(), c.getFirstName(), c.getLastName(),
                    c.getRole(), c.getSpecialty(), c.getContactInfo()
            });
        }
    }

    private void deleteSelectedClinician() {
        int row = clinicianTable.getSelectedRow();
        if (row != -1) {
            String id = (String) clinicianTable.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Delete clinician " + id + "?", "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DataManager.getInstance().deleteClinician(id);
                loadClinicianData();
            }
        }
    }

    private void showAddClinicianDialog() {
        JTextField idField = new JTextField();
        JTextField regField = new JTextField();
        JTextField fnameField = new JTextField();
        JTextField lnameField = new JTextField();
        JTextField roleField = new JTextField();
        JTextField specField = new JTextField();
        JTextField contactField = new JTextField();

        Object[] message = {
                "Clinician ID:", idField,
                "Registry ID:", regField,
                "First Name:", fnameField,
                "Last Name:", lnameField,
                "Role:", roleField,
                "Specialty:", specField,
                "Contact Info:", contactField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New Clinician", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Clinician c = new Clinician(idField.getText(), regField.getText(), fnameField.getText(),
                    lnameField.getText(), roleField.getText(), specField.getText(), contactField.getText());
            DataManager.getInstance().addClinician(c);
            loadClinicianData();
        }
    }
}
