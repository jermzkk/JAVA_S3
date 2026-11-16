package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {
    private CharacterManager manager;
    private JPanel listPanel;
    private JLabel avgLevelLabel;

    public MainWindow(CharacterManager manager) {
        this.manager = manager;
        initComponents();
        refreshData();
        pack(); // Menggunakan pack() sesuai Bagian A.3
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Character Manager v0.4 — TP10 Theming (Java Visual SIH-6406)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(AppTheme.BG_WINDOW);

        // Header Judul
        JLabel headerLabel = new JLabel("DAFTAR KARAKTER GAME (v0.4 THEMED)", SwingConstants.CENTER);
        headerLabel.setFont(AppTheme.FONT_HEADER);
        headerLabel.setForeground(AppTheme.TEXT_GOLD);
        headerLabel.setBackground(AppTheme.BG_WINDOW);
        headerLabel.setOpaque(true);
        headerLabel.setBorder(AppTheme.SECTION_PADDING);
        add(headerLabel, BorderLayout.NORTH);

        // Panel Daftar Karakter Dinamis
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 8, 8));
        listPanel.setBackground(AppTheme.BG_PANEL);
        listPanel.setBorder(AppTheme.SECTION_PADDING);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(640, 340)); // Ukuran ideal sebelum pack()
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Bawah: Statistik Rata-rata Level
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(AppTheme.BG_WINDOW);
        footerPanel.setBorder(AppTheme.SECTION_PADDING);

        avgLevelLabel = new JLabel("Total Karakter: 0  |  Rata-rata Level: 0.00", SwingConstants.RIGHT);
        avgLevelLabel.setFont(AppTheme.FONT_FOOTER);
        avgLevelLabel.setForeground(AppTheme.TEXT_PRIMARY);
        footerPanel.add(avgLevelLabel, BorderLayout.CENTER);

        add(footerPanel, BorderLayout.SOUTH);
    }

    public void refreshData() {
        listPanel.removeAll();

        ArrayList<Character> characters = manager.getAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            boolean isVeteran = c.getLevel() >= AppTheme.VETERAN_LEVEL_THRESHOLD;

            String badge = isVeteran ? " [VETERAN]" : "";
            String rowText = String.format(
                "%d. %s [%s]%s — Level: %d | HP: %d | ATK: %d | Mana: %d",
                (i + 1),
                c.getName(),
                c.getType(),
                badge,
                c.getLevel(),
                c.getHitPoints(),
                c.getAttackPower(),
                c.getMana()
            );

            JLabel itemLabel = new JLabel(rowText);

            // Wajib setOpaque(true) agar warna latar kartu tampil pada JLabel (Bagian A.3)
            itemLabel.setOpaque(true);
            itemLabel.setBackground(AppTheme.colorFor(c.getType()));

            // Mini-Challenge: Level >= 5 teks emas dan tebal (Bagian B.1)
            if (isVeteran) {
                itemLabel.setForeground(AppTheme.TEXT_GOLD);
                itemLabel.setFont(AppTheme.FONT_CARD_VETERAN);
            } else {
                itemLabel.setForeground(AppTheme.TEXT_PRIMARY);
                itemLabel.setFont(AppTheme.FONT_CARD_REGULAR);
            }

            // Properti Tambahan 1: Border aksen berbeda per role
            itemLabel.setBorder(AppTheme.borderFor(c.getType()));

            // Properti Tambahan 2: Tooltip interaktif berisi detail status karakter
            itemLabel.setToolTipText(String.format(
                "Detail %s (%s) — Level %d | HP: %d | ATK: %d | Mana: %d | Status: %s",
                c.getName(),
                c.getType(),
                c.getLevel(),
                c.getHitPoints(),
                c.getAttackPower(),
                c.getMana(),
                c.isAlive() ? "Siap Tempur" : "Gugur"
            ));

            listPanel.add(itemLabel);
        }

        avgLevelLabel.setText(String.format(
            "Total Karakter: %d  |  Rata-rata Level: %.2f",
            manager.getTotalCharacters(),
            manager.getAverageLevel()
        ));

        listPanel.revalidate();
        listPanel.repaint();
    }
}