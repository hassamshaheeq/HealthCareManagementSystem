package com.healthcare.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import com.healthcare.logic.DataManager;
import com.healthcare.logic.AppointmentManager;
import com.healthcare.model.Appointment;
import java.util.List;

public class AppointmentPanel extends JPanel {
    private JTable appointmentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private AppointmentManager appointmentManager;

    public AppointmentPanel() {
        this.appointmentManager = new AppointmentManager();
        setLayout(new BorderLayout(15, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Appointment Schedule");
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

        JButton btnAdd = UIStyles.createStyledButton("Book Session", UIStyles.PRIMARY_COLOR);
        JButton btnDelete = UIStyles.createStyledButton("Cancel", UIStyles.DANGER_COLOR);

        controls.add(searchField);
        controls.add(btnAdd);
        controls.add(btnDelete);
        header.add(controls, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        String[] columns = { "ID", "Patient ID", "Clinician ID", "Facility ID", "Date/Time", "Reason", "Status" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        appointmentTable = new JTable(tableModel);
        UIStyles.styleTable(appointmentTable);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        appointmentTable.setRowSorter(sorter);

        searchField.addCaretListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        scrollPane.getViewport().setBackground(UIStyles.BG_MAIN);
        scrollPane.setBorder(UIStyles.PANEL_BORDER);
        add(scrollPane, BorderLayout.CENTER);

        loadAppointmentData();

        btnAdd.addActionListener(e -> showAddAppointmentDialog());
        btnDelete.addActionListener(e -> {
            int row = appointmentTable.getSelectedRow();
            if (row != -1) {
                String id = (String) appointmentTable.getValueAt(row, 0);
                DataManager.getInstance().deleteAppointment(id);
                loadAppointmentData();
            }
        });
    }

    private void loadAppointmentData() {
        tableModel.setRowCount(0);
        List<Appointment> appointments = DataManager.getInstance().getAppointments();
        for (Appointment a : appointments) {
            tableModel.addRow(new Object[] {
                    a.getAppointmentID(), a.getPatientID(), a.getClinicianID(),
                    a.getFacilityID(), a.getDateTime(), a.getReason(), a.getStatus()
            });
        }
    }

    private void showAddAppointmentDialog() {
        JTextField idField = new JTextField();
        JTextField patientIdField = new JTextField();
        JTextField clinicianIdField = new JTextField();
        JTextField facilityIdField = new JTextField();
        JTextField dateTimeField = new JTextField("2026-01-05 10:00");
        JTextField reasonField = new JTextField();

        Object[] message = {
                "ID:", idField, "Patient ID:", patientIdField, "Clinician ID:", clinicianIdField,
                "Facility ID:", facilityIdField, "Date/Time:", dateTimeField, "Reason:", reasonField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Book New Session", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Appointment a = new Appointment(idField.getText(), patientIdField.getText(), clinicianIdField.getText(),
                    facilityIdField.getText(), dateTimeField.getText(), reasonField.getText(), "Scheduled");
            appointmentManager.scheduleAppointment(a);
            loadAppointmentData();
        }
    }
}
