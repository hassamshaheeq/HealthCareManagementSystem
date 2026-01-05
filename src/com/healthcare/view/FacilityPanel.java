package com.healthcare.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import com.healthcare.logic.DataManager;
import com.healthcare.model.Facility;
import java.util.List;

public class FacilityPanel extends JPanel {
    private JTable facilityTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public FacilityPanel() {
        setLayout(new BorderLayout(15, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Medical Facilities Management");
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

        JButton btnAdd = UIStyles.createStyledButton("Add Facility", UIStyles.PRIMARY_COLOR);
        JButton btnDelete = UIStyles.createStyledButton("Delete", UIStyles.DANGER_COLOR);

        controls.add(searchField);
        controls.add(btnAdd);
        controls.add(btnDelete);
        header.add(controls, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Table
        String[] columns = { "ID", "Name", "Type", "Address", "Contact" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        facilityTable = new JTable(tableModel);
        UIStyles.styleTable(facilityTable);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        facilityTable.setRowSorter(sorter);

        searchField.addCaretListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(facilityTable);
        scrollPane.getViewport().setBackground(UIStyles.BG_MAIN);
        scrollPane.setBorder(UIStyles.PANEL_BORDER);
        add(scrollPane, BorderLayout.CENTER);

        loadData();

        btnAdd.addActionListener(e -> showAddDialog());
        btnDelete.addActionListener(e -> {
            int row = facilityTable.getSelectedRow();
            if (row != -1) {
                String id = (String) facilityTable.getValueAt(row, 0);
                DataManager.getInstance().deleteFacility(id);
                loadData();
            }
        });
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Facility> facilities = DataManager.getInstance().getFacilities();
        for (Facility f : facilities) {
            tableModel.addRow(
                    new Object[] { f.getFacilityID(), f.getName(), f.getType(), f.getAddress(), f.getContactNumber() });
        }
    }

    private void showAddDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField addrField = new JTextField();
        JTextField contactField = new JTextField();

        Object[] message = { "ID:", idField, "Name:", nameField, "Type:", typeField, "Address:", addrField, "Contact:",
                contactField };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Facility", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Facility f = new Facility(idField.getText(), nameField.getText(), typeField.getText(), addrField.getText(),
                    contactField.getText());
            DataManager.getInstance().addFacility(f);
            loadData();
        }
    }
}
