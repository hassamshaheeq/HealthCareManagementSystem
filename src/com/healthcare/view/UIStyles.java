package com.healthcare.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UIStyles {
    // Light Grey & Solid Black Theme
    public static final Color BG_SIDEBAR = new Color(220, 221, 225); // Slightly darker grey for sidebar
    public static final Color BG_MAIN = new Color(245, 246, 250); // Light grey/off-white for main
    public static final Color BG_CARD = new Color(255, 255, 255); // White cards for contrast
    public static final Color PRIMARY_COLOR = new Color(200, 204, 208); // Medium light grey
    public static final Color ACCENT_COLOR = new Color(225, 227, 230); // Light grey
    public static final Color WARNING_COLOR = new Color(235, 237, 240);
    public static final Color DANGER_COLOR = new Color(170, 175, 180); // Darker shade for contrast
    public static final Color TEXT_BLACK = Color.BLACK; // Solid Black text
    public static final Color TEXT_WHITE = Color.WHITE;
    public static final Color TEXT_HIGHLIGHT = Color.BLACK;

    // Borders & Theme Colors
    public static final Color BORDER_COLOR_LIGHT = new Color(189, 195, 199);
    public static final Color BORDER_COLOR_DARK = new Color(60, 68, 81);
    public static final Border FIELD_BORDER = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(BORDER_COLOR_LIGHT), "Search", 0, 0, new Font("Segoe UI", Font.PLAIN, 13),
            Color.DARK_GRAY);
    public static final Border PANEL_BORDER = BorderFactory.createLineBorder(BORDER_COLOR_DARK);

    // Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font HEADER_FONT = new Font("Segoe UI Semibold", Font.PLAIN, 15);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font STAT_FONT = new Font("Segoe UI", Font.BOLD, 28);

    public static JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setOpaque(true);
        btn.setBackground(bgColor);
        btn.setForeground(TEXT_BLACK); // Force black text
        btn.setFocusPainted(false);
        btn.setFont(HEADER_FONT);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(8, 18, 8, 18)));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Simple hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(bgColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });

        return btn;
    }

    public static JPanel createCard(String title) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 68, 81), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JLabel label = new JLabel(title);
        label.setFont(HEADER_FONT);
        label.setForeground(PRIMARY_COLOR);
        card.add(label, BorderLayout.NORTH);

        return card;
    }

    public static void styleTable(JTable table) {
        table.setFont(LABEL_FONT);
        table.setRowHeight(35);
        table.setBackground(Color.WHITE);
        table.setForeground(TEXT_BLACK);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 221, 225));

        table.getTableHeader().setBackground(BG_SIDEBAR);
        table.getTableHeader().setForeground(TEXT_BLACK);
        table.getTableHeader().setFont(HEADER_FONT);
        table.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));

        table.setSelectionBackground(new Color(232, 241, 250));
        table.setSelectionForeground(TEXT_BLACK);
    }
}
