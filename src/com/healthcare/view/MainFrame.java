package com.healthcare.view;

import javax.swing.*;
import java.awt.*;
import com.healthcare.logic.DataManager;

public class MainFrame extends JFrame {
    private JPanel mainContent;
    private CardLayout cardLayout;
    private JLabel statusLabel;

    public MainFrame() {
        setTitle("NHS Healthcare Management System | Premium Suite");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set main background
        getContentPane().setBackground(UIStyles.BG_MAIN);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(240, 800));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(UIStyles.BG_SIDEBAR);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel logoLabel = new JLabel("NHS PORTAL", SwingConstants.CENTER);
        logoLabel.setForeground(UIStyles.PRIMARY_COLOR);
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logoLabel);
        sidebar.add(Box.createVerticalStrut(30));

        JButton btnDash = createSidebarButton("Dashboard", "🏠");
        JButton btnPatients = createSidebarButton("Patients", "👤");
        JButton btnAppointments = createSidebarButton("Appointments", "📅");
        JButton btnFacilities = createSidebarButton("Facilities", "🏥");
        JButton btnPrescriptions = createSidebarButton("Prescriptions", "💊");
        JButton btnReferrals = createSidebarButton("Referrals", "📄");
        JButton btnClinicians = createSidebarButton("Clinicians", "👨‍⚕️");
        JButton btnStaff = createSidebarButton("Staff", "👥");

        sidebar.add(btnDash);
        sidebar.add(btnPatients);
        sidebar.add(btnAppointments);
        sidebar.add(btnFacilities);
        sidebar.add(btnPrescriptions);
        sidebar.add(btnReferrals);
        sidebar.add(btnClinicians);
        sidebar.add(btnStaff);

        sidebar.add(Box.createVerticalGlue());

        JButton btnSave = createSidebarButton("SAVE DATA", "💾");
        btnSave.setForeground(UIStyles.ACCENT_COLOR);
        sidebar.add(btnSave);

        // Main Content Area
        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);
        mainContent.setOpaque(false);

        mainContent.add(new DashboardPanel(), "Dashboard");
        mainContent.add(new PatientPanel(), "Patients");
        mainContent.add(new AppointmentPanel(), "Appointments");
        mainContent.add(new FacilityPanel(), "Facilities");
        mainContent.add(new PrescriptionPanel(), "Prescriptions");
        mainContent.add(new ReferralPanel(), "Referrals");
        mainContent.add(new ClinicianPanel(), "Clinicians");
        mainContent.add(new StaffPanel(), "Staff");

        // Status Panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(UIStyles.BG_SIDEBAR);
        statusPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(60, 68, 81)));
        statusLabel = new JLabel("  System connected. All modules loaded.");
        statusLabel.setForeground(UIStyles.TEXT_WHITE);
        statusLabel.setFont(UIStyles.LABEL_FONT);
        statusLabel.setPreferredSize(new Dimension(1200, 30));
        statusPanel.add(statusLabel, BorderLayout.CENTER);

        // Layout Integration
        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(mainContent, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        // Action Listeners
        btnDash.addActionListener(e -> {
            cardLayout.show(mainContent, "Dashboard");
            updateStatus("Dashboard Overview");
        });
        btnPatients.addActionListener(e -> {
            cardLayout.show(mainContent, "Patients");
            updateStatus("Managing Patient Records");
        });
        btnAppointments.addActionListener(e -> {
            cardLayout.show(mainContent, "Appointments");
            updateStatus("Appointment Schedule");
        });
        btnFacilities.addActionListener(e -> {
            cardLayout.show(mainContent, "Facilities");
            updateStatus("Facility Management");
        });
        btnPrescriptions.addActionListener(e -> {
            cardLayout.show(mainContent, "Prescriptions");
            updateStatus("Pharmacy & Prescriptions");
        });
        btnReferrals.addActionListener(e -> {
            cardLayout.show(mainContent, "Referrals");
            updateStatus("Referral Tracking");
        });
        btnClinicians.addActionListener(e -> {
            cardLayout.show(mainContent, "Clinicians");
            updateStatus("Clinician Directory");
        });
        btnStaff.addActionListener(e -> {
            cardLayout.show(mainContent, "Staff");
            updateStatus("Staff Directory");
        });

        btnSave.addActionListener(e -> {
            DataManager.getInstance().saveAll();
            updateStatus("All changes committed to database files.");
            JOptionPane.showMessageDialog(this, "All database files updated successfully.", "Sync Complete",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void updateStatus(String msg) {
        statusLabel.setText("  " + msg);
    }

    private JButton createSidebarButton(String text, String icon) {
        JButton btn = new JButton(icon + "  " + text);
        btn.setMaximumSize(new Dimension(240, 50));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(UIStyles.BG_SIDEBAR);
        btn.setForeground(UIStyles.TEXT_BLACK);
        btn.setFont(UIStyles.HEADER_FONT);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(189, 195, 199));
                btn.setForeground(UIStyles.TEXT_BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(UIStyles.BG_SIDEBAR);
                btn.setForeground(UIStyles.TEXT_BLACK);
            }
        });

        return btn;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
