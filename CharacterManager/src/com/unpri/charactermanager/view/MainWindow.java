package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
    }

    private void initComponents() {
        setTitle("Character Manager — TP8 (Java Visual SIH-6406)");
        setSize(560, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Jendela muncul di tengah layar
        setLayout(new BorderLayout(10, 10));

        // Header Judul
        JLabel headerLabel = new JLabel("DAFTAR KARAKTER GAME", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(12, 10, 8, 10));
        add(headerLabel, BorderLayout.NORTH);

        // Panel Daftar Karakter Dinamis (Bagian A.3)
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 6, 6));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Bawah: Statistik Rata-rata Level (Bagian B.2)
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBorder(BorderFactory.createEmptyBorder(8, 15, 12, 15));

        avgLevelLabel = new JLabel("Rata-rata Level: 0.00", SwingConstants.RIGHT);
        avgLevelLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        footerPanel.add(avgLevelLabel, BorderLayout.CENTER);

        add(footerPanel, BorderLayout.SOUTH);
    }

    // Mengisi daftar karakter secara DINAMIS (loop) dan memperbarui statistik
    public void refreshData() {
        listPanel.removeAll();

        ArrayList<Character> characters = manager.getAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            String rowText = String.format(
                "%d. %s [%s] — Level: %d | HP: %d | ATK: %d | Mana: %d | Status: %s",
                (i + 1),
                c.getName(),
                c.getType(),
                c.getLevel(),
                c.getHitPoints(),
                c.getAttackPower(),
                c.getMana(),
                c.isAlive() ? "Hidup" : "Gugur"
            );

            JLabel itemLabel = new JLabel(rowText);
            itemLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
            itemLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
            ));
            listPanel.add(itemLabel);
        }

        // Update statistik Rata-rata Level secara akurat
        double avgLevel = manager.getAverageLevel();
        avgLevelLabel.setText(String.format("Total Karakter: %d  |  Rata-rata Level: %.2f",
                manager.getTotalCharacters(), avgLevel));

        listPanel.revalidate();
        listPanel.repaint();
    }
}