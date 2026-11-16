package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {
    private CharacterManager manager;
    private JPanel listPanel;

    private JLabel statTotalLabel;
    private JLabel statAvgLevelLabel;
    private JLabel statVeteranLabel;
    private JLabel statAliveLabel;
    private JLabel statusLeftLabel;

    public MainWindow(CharacterManager manager) {
        this.manager = manager;
        initComponents();
        refreshList();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Character Manager v0.6 — TP14 Form Input & Validasi (Java Visual SIH-6406)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(AppTheme.BG_WINDOW);

        // 1. NORTH: Header + Form Input + Statistik Grid 2x2
        JPanel northContainer = new JPanel(new BorderLayout());
        northContainer.setBackground(AppTheme.BG_WINDOW);

        JLabel headerLabel = new JLabel("CHARACTER MANAGER v0.6 — FORM & VALIDASI DUA LAPIS", SwingConstants.CENTER);
        headerLabel.setFont(AppTheme.FONT_HEADER);
        headerLabel.setForeground(AppTheme.TEXT_GOLD);
        headerLabel.setBackground(AppTheme.BG_WINDOW);
        headerLabel.setOpaque(true);
        headerLabel.setBorder(AppTheme.SECTION_PADDING);
        northContainer.add(headerLabel, BorderLayout.NORTH);

        CharacterFormPanel formPanel = new CharacterFormPanel(manager, new Runnable() {
            @Override
            public void run() {
                refreshList();
            }
        });
        northContainer.add(formPanel, BorderLayout.CENTER);

        JPanel statsGridPanel = new JPanel(new GridLayout(2, 2, 10, 6));
        statsGridPanel.setBackground(AppTheme.BG_PANEL);
        statsGridPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, AppTheme.ROLE_DEFAULT),
            BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));

        statTotalLabel = createStatLabel("Total Karakter: 0");
        statAvgLevelLabel = createStatLabel("Rata-rata Level: 0.00");
        statVeteranLabel = createStatLabel("Veteran (Lv >= 5): 0");
        statAliveLabel = createStatLabel("Status Siap Tempur: 0");

        statsGridPanel.add(statTotalLabel);
        statsGridPanel.add(statAvgLevelLabel);
        statsGridPanel.add(statVeteranLabel);
        statsGridPanel.add(statAliveLabel);

        northContainer.add(statsGridPanel, BorderLayout.SOUTH);
        add(northContainer, BorderLayout.NORTH);

        // 2. CENTER: Daftar Karakter Berscroll
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 8, 8));
        listPanel.setBackground(AppTheme.BG_PANEL);

        JPanel listWrapper = new JPanel(new BorderLayout());
        listWrapper.setBackground(AppTheme.BG_PANEL);
        listWrapper.setBorder(AppTheme.SECTION_PADDING);
        listWrapper.add(listPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(listWrapper);
        scrollPane.setPreferredSize(new Dimension(620, 300));
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // 3. EAST: Legenda Role
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBackground(AppTheme.BG_WINDOW);
        legendPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 1, 0, 0, AppTheme.ROLE_DEFAULT),
            AppTheme.SECTION_PADDING
        ));

        JLabel legendTitle = new JLabel("LEGENDA ROLE");
        legendTitle.setFont(AppTheme.FONT_FOOTER);
        legendTitle.setForeground(AppTheme.TEXT_GOLD);
        legendTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        legendPanel.add(legendTitle);
        legendPanel.add(Box.createVerticalStrut(10));

        for (CharacterType type : CharacterType.values()) {
            JLabel roleBadge = new JLabel("  " + type.name() + "  ");
            roleBadge.setOpaque(true);
            roleBadge.setBackground(AppTheme.colorFor(type));
            roleBadge.setForeground(AppTheme.TEXT_PRIMARY);
            roleBadge.setFont(AppTheme.FONT_CARD_REGULAR);
            roleBadge.setBorder(AppTheme.borderFor(type));
            roleBadge.setMaximumSize(new Dimension(160, 32));
            roleBadge.setAlignmentX(Component.LEFT_ALIGNMENT);

            legendPanel.add(roleBadge);
            legendPanel.add(Box.createVerticalStrut(8));
        }

        legendPanel.add(Box.createVerticalGlue());

        JLabel veteranNote = new JLabel("★ Emas = Lv ≥ " + AppTheme.VETERAN_LEVEL_THRESHOLD);
        veteranNote.setFont(AppTheme.FONT_CARD_REGULAR);
        veteranNote.setForeground(AppTheme.TEXT_GOLD);
        veteranNote.setAlignmentX(Component.LEFT_ALIGNMENT);
        legendPanel.add(veteranNote);

        JLabel mageRuleNote = new JLabel("• MAGE Maks Lv 60");
        mageRuleNote.setFont(AppTheme.FONT_CARD_REGULAR);
        mageRuleNote.setForeground(AppTheme.TEXT_MUTED);
        mageRuleNote.setAlignmentX(Component.LEFT_ALIGNMENT);
        legendPanel.add(Box.createVerticalStrut(4));
        legendPanel.add(mageRuleNote);

        add(legendPanel, BorderLayout.EAST);

        // 4. SOUTH: Bar Status v0.6
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(AppTheme.BG_WINDOW);
        statusBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, AppTheme.ROLE_DEFAULT),
            BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));

        statusLeftLabel = new JLabel("Ready • Menampilkan 0 karakter");
        statusLeftLabel.setFont(AppTheme.FONT_CARD_REGULAR);
        statusLeftLabel.setForeground(AppTheme.TEXT_MUTED);

        JLabel versionRightLabel = new JLabel("v0.6", SwingConstants.RIGHT);
        versionRightLabel.setFont(AppTheme.FONT_FOOTER);
        versionRightLabel.setForeground(AppTheme.TEXT_GOLD);

        statusBar.add(statusLeftLabel, BorderLayout.WEST);
        statusBar.add(versionRightLabel, BorderLayout.EAST);

        add(statusBar, BorderLayout.SOUTH);
    }

    private JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(AppTheme.FONT_CARD_REGULAR);
        label.setForeground(AppTheme.TEXT_PRIMARY);
        return label;
    }

    public void refreshList() {
        listPanel.removeAll();

        ArrayList<Character> characters = manager.getAllCharacters();
        int veteranCount = 0;
        int aliveCount = 0;

        for (int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            boolean isVeteran = c.getLevel() >= AppTheme.VETERAN_LEVEL_THRESHOLD;
            if (isVeteran) {
                veteranCount++;
            }
            if (c.isAlive()) {
                aliveCount++;
            }

            String badge = isVeteran ? " [VETERAN]" : "";
            String rowText = String.format(
                "%d. %s [%s]%s — Lv: %d | HP: %d | ATK: %d | Mana: %d",
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
            itemLabel.setOpaque(true);
            itemLabel.setBackground(AppTheme.colorFor(c.getType()));

            if (isVeteran) {
                itemLabel.setForeground(AppTheme.TEXT_GOLD);
                itemLabel.setFont(AppTheme.FONT_CARD_VETERAN);
            } else {
                itemLabel.setForeground(AppTheme.TEXT_PRIMARY);
                itemLabel.setFont(AppTheme.FONT_CARD_REGULAR);
            }

            itemLabel.setBorder(AppTheme.borderFor(c.getType()));
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

        statTotalLabel.setText("Total Karakter: " + manager.getTotalCharacters() + "/" + CharacterManager.MAX_ROSTER_CAPACITY);
        statAvgLevelLabel.setText(String.format("Rata-rata Level: %.2f", manager.getAverageLevel()));
        statVeteranLabel.setText("Veteran (Lv ≥ " + AppTheme.VETERAN_LEVEL_THRESHOLD + "): " + veteranCount);
        statAliveLabel.setText("Status Siap Tempur: " + aliveCount + "/" + manager.getTotalCharacters());
        statusLeftLabel.setText("Status: Aktif • Menampilkan " + characters.size() + " karakter");

        listPanel.revalidate();
        listPanel.repaint();
    }
}