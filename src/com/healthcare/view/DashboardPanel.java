package com.healthcare.view;

import javax.swing.*;
import java.awt.*;
import com.healthcare.logic.DataManager;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new BorderLayout(20, 20));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header
        JLabel title = new JLabel("Health Portal Dashboard");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_BLACK);
        add(title, BorderLayout.NORTH);

        // Stats Grid
        JPanel statsGrid = new JPanel(new GridLayout(1, 3, 20, 20));
        statsGrid.setOpaque(false);

        statsGrid.add(createStatCard("Total Patients", String.valueOf(DataManager.getInstance().getTotalPatients()),
                UIStyles.PRIMARY_COLOR));
        statsGrid.add(createStatCard("Active Appointments",
                String.valueOf(DataManager.getInstance().getPendingAppointments()), UIStyles.ACCENT_COLOR));
        statsGrid.add(createStatCard("Pending Referrals",
                String.valueOf(DataManager.getInstance().getPendingReferrals()), UIStyles.WARNING_COLOR));

        add(statsGrid, BorderLayout.CENTER);

        // Quick Actions or Recent Activity could go here
    }

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(UIStyles.BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                UIStyles.PANEL_BORDER,
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(UIStyles.HEADER_FONT);
        titleLabel.setForeground(UIStyles.TEXT_BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(UIStyles.STAT_FONT);
        valueLabel.setForeground(color);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);

        return card;
    }
}
