package com.healthcare.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import com.healthcare.logic.DataManager;
import com.healthcare.logic.ReferralManager;
import com.healthcare.model.Referral;
import java.util.List;

public class ReferralPanel extends JPanel {
    private JTable referralTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public ReferralPanel() {
        setLayout(new BorderLayout(15, 15));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Referral Tracking System");
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

        JButton btnAdd = UIStyles.createStyledButton("New Referral", UIStyles.ACCENT_COLOR);
        JButton btnAudit = UIStyles.createStyledButton("View Audit Log", UIStyles.WARNING_COLOR);

        controls.add(searchField);
        controls.add(btnAdd);
        controls.add(btnAudit);
        header.add(controls, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        String[] columns = { "ID", "Patient ID", "Source Clinician", "Target Facility", "Urgency", "Status" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        referralTable = new JTable(tableModel);
        UIStyles.styleTable(referralTable);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        referralTable.setRowSorter(sorter);

        searchField.addCaretListener(e -> {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(referralTable);
        scrollPane.getViewport().setBackground(UIStyles.BG_MAIN);
        scrollPane.setBorder(UIStyles.PANEL_BORDER);
        add(scrollPane, BorderLayout.CENTER);

        loadReferralData();

        btnAdd.addActionListener(e -> showAddReferralDialog());
        btnAudit.addActionListener(e -> showAuditLog());
    }

    private void loadReferralData() {
        tableModel.setRowCount(0);
        List<Referral> referrals = DataManager.getInstance().getReferrals();
        for (Referral r : referrals) {
            tableModel.addRow(new Object[] {
                    r.getReferralID(), r.getPatientID(), r.getSourceClinicianID(),
                    r.getTargetFacilityID(), r.getUrgencyLevel(), r.getStatus()
            });
        }
    }

    private void showAuditLog() {
        List<String> logs = ReferralManager.getInstance().getAuditLog();
        JTextArea logArea = new JTextArea(15, 45);
        logArea.setBackground(UIStyles.BG_CARD);
        logArea.setForeground(UIStyles.TEXT_WHITE);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        logArea.setEditable(false);

        for (String entry : logs) {
            logArea.append(entry + "\n");
        }

        JOptionPane.showMessageDialog(this, new JScrollPane(logArea), "Referral Audit Log", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAddReferralDialog() {
        JTextField idField = new JTextField();
        JTextField patientIdField = new JTextField();
        JTextField sourceClinicianField = new JTextField();
        JTextField targetFacilityField = new JTextField();
        JTextField reasonField = new JTextField();
        JTextField urgencyField = new JTextField("Routine");
        JTextArea summaryArea = new JTextArea(5, 20);

        Object[] message = {
                "ID:", idField, "Patient ID:", patientIdField, "Source ID:", sourceClinicianField,
                "Target Facility:", targetFacilityField, "Reason:", reasonField, "Urgency:", urgencyField,
                "Summary:", new JScrollPane(summaryArea)
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Initiate Referral", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Referral r = new Referral(idField.getText(), patientIdField.getText(), sourceClinicianField.getText(),
                    targetFacilityField.getText(), reasonField.getText(), urgencyField.getText(),
                    summaryArea.getText(), "Pending", new java.util.Date().toString());
            ReferralManager.getInstance().processReferral(r);
            loadReferralData();
        }
    }
}
