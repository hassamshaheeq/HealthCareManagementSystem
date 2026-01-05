package com.healthcare.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import com.healthcare.logic.DataManager;
import com.healthcare.model.Staff;
import java.util.List;

public class StaffPanel extends JPanel {
    private JTable staffTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public StaffPanel() {
        setLayout(new BorderLayout(15, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Staff Directory");
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

        JButton btnAdd = UIStyles.createStyledButton("Add Staff", UIStyles.PRIMARY_COLOR);
        JButton btnDelete = UIStyles.createStyledButton("Delete", UIStyles.DANGER_COLOR);

        controls.add(searchField);
        controls.add(btnAdd);
        controls.add(btnDelete);
        header.add(controls, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Table
        String[] columns = { "Staff ID", "First Name", "Last Name", "Role", "Department", "Contact" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        staffTable = new JTable(tableModel);
        UIStyles.styleTable(staffTable);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        staffTable.setRowSorter(sorter);

        searchField.addCaretListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(staffTable);
        scrollPane.getViewport().setBackground(UIStyles.BG_MAIN);
        scrollPane.setBorder(UIStyles.PANEL_BORDER);
        add(scrollPane, BorderLayout.CENTER);

        loadStaffData();

        btnAdd.addActionListener(e -> showAddStaffDialog());
        btnDelete.addActionListener(e -> deleteSelectedStaff());
    }

    private void loadStaffData() {
        tableModel.setRowCount(0);
        List<Staff> staffList = DataManager.getInstance().getStaff();
        for (Staff s : staffList) {
            tableModel.addRow(new Object[] {
                    s.getStaffID(), s.getFirstName(), s.getLastName(),
                    s.getRole(), s.getDepartment(), s.getContactInfo()
            });
        }
    }

    private void deleteSelectedStaff() {
        int row = staffTable.getSelectedRow();
        if (row != -1) {
            String id = (String) staffTable.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Delete staff member " + id + "?", "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DataManager.getInstance().deleteStaff(id);
                loadStaffData();
            }
        }
    }

    private void showAddStaffDialog() {
        JTextField idField = new JTextField();
        JTextField fnameField = new JTextField();
        JTextField lnameField = new JTextField();
        JTextField roleField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField contactField = new JTextField();

        Object[] message = {
                "Staff ID:", idField,
                "First Name:", fnameField,
                "Last Name:", lnameField,
                "Role:", roleField,
                "Department:", deptField,
                "Contact Info:", contactField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New Staff", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Staff s = new Staff(idField.getText(), fnameField.getText(),
                    lnameField.getText(), roleField.getText(), deptField.getText(), contactField.getText());
            DataManager.getInstance().addStaff(s);
            loadStaffData();
        }
    }
}
