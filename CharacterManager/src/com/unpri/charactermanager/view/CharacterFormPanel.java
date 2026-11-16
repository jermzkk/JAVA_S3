package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class CharacterFormPanel extends JPanel {
    private CharacterManager manager;
    private Runnable onCharacterAdded;

    private JTextField nameField;
    private JComboBox<CharacterType> roleCombo;
    private JSpinner levelSpinner;
    private JButton addButton;
    private JLabel feedbackLabel;

    public CharacterFormPanel(CharacterManager manager, Runnable onCharacterAdded) {
        this.manager = manager;
        this.onCharacterAdded = onCharacterAdded;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 6));
        setBackground(AppTheme.BG_WINDOW);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 1, 0, AppTheme.ROLE_DEFAULT),
            BorderFactory.createEmptyBorder(10, 16, 10, 16)
        ));

        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        inputRow.setBackground(AppTheme.BG_WINDOW);

        JLabel nameLabel = createFieldLabel("Nama:");
        nameField = new JTextField(14);
        nameField.setFont(AppTheme.FONT_CARD_REGULAR);
        nameField.setBackground(AppTheme.BG_INPUT);
        nameField.setForeground(AppTheme.TEXT_PRIMARY);
        nameField.setCaretColor(AppTheme.TEXT_PRIMARY);
        nameField.setBorder(AppTheme.INPUT_NORMAL_BORDER);

        JLabel roleLabel = createFieldLabel("Role:");
        roleCombo = new JComboBox<>(CharacterType.values());
        roleCombo.setFont(AppTheme.FONT_CARD_REGULAR);
        roleCombo.setBackground(AppTheme.BG_INPUT);
        roleCombo.setForeground(AppTheme.TEXT_PRIMARY);

        JLabel levelLabel = createFieldLabel("Level (1-99):");
        levelSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        levelSpinner.setFont(AppTheme.FONT_CARD_REGULAR);

        addButton = new JButton("+ Tambah Karakter");
        addButton.setFont(AppTheme.FONT_FEEDBACK);
        addButton.setBackground(AppTheme.ACCENT_PRIMARY);
        addButton.setForeground(AppTheme.BG_PANEL);
        addButton.setFocusPainted(false);

        inputRow.add(nameLabel);
        inputRow.add(nameField);
        inputRow.add(roleLabel);
        inputRow.add(roleCombo);
        inputRow.add(levelLabel);
        inputRow.add(levelSpinner);
        inputRow.add(addButton);

        feedbackLabel = new JLabel("Masukkan nama (3–20 huruf), pilih role, dan tentukan level (1–99).");
        feedbackLabel.setFont(AppTheme.FONT_FEEDBACK);
        feedbackLabel.setForeground(AppTheme.TEXT_MUTED);
        feedbackLabel.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));

        add(inputRow, BorderLayout.NORTH);
        add(feedbackLabel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(AppTheme.FONT_FEEDBACK);
        label.setForeground(AppTheme.TEXT_PRIMARY);
        return label;
    }

    // Bagian A.2: Berurutan (kelengkapan -> panjang -> format -> keunikan), murni tanpa menyentuh komponen
    public String validateName(String rawName) {
        // 1. Kelengkapan
        if (rawName == null || rawName.trim().isEmpty()) {
            return "Nama wajib diisi — masukkan nama karakter sebelum menambah.";
        }

        String cleanName = rawName.trim();

        // 2. Panjang
        if (cleanName.length() < 3 || cleanName.length() > 20) {
            return "Panjang nama tidak valid (" + cleanName.length() + " karakter) — gunakan 3 hingga 20 karakter.";
        }

        // 3. Format (Hanya huruf & tanpa spasi ganda — Aturan Tambahan 2 di View)
        if (!cleanName.matches("[a-zA-Z ]+")) {
            return "Format nama tidak sah — gunakan huruf alfabet (A-Z) dan spasi saja tanpa angka/simbol.";
        }
        if (cleanName.contains("  ")) {
            return "Format spasi tidak rapi — hindari penggunaan spasi ganda di tengah nama.";
        }

        // 4. Keunikan
        if (manager.isNameTaken(cleanName)) {
            return "Nama '" + cleanName + "' sudah terdaftar — gunakan nama lain yang unik.";
        }

        return null;
    }

    private void handleSubmit() {
        nameField.setBorder(AppTheme.INPUT_NORMAL_BORDER);

        String rawName = nameField.getText();
        String nameError = validateName(rawName);
        if (nameError != null) {
            showError(nameError, true);
            return;
        }

        int level;
        try {
            levelSpinner.commitEdit();
            level = (Integer) levelSpinner.getValue();
        } catch (ParseException ex) {
            showError("Level tidak valid — masukkan angka bulat antara 1 hingga 99.", false);
            levelSpinner.requestFocusInWindow();
            return;
        }

        CharacterType selectedRole = (CharacterType) roleCombo.getSelectedItem();
        String cleanName = rawName.trim();

        try {
            Character newChar = new Character(cleanName, selectedRole, level, 100);
            boolean added = manager.addCharacter(newChar);
            if (!added) {
                showError("Nama '" + cleanName + "' sudah terdaftar di tim.", true);
                return;
            }

            nameField.setText("");
            nameField.setBorder(AppTheme.INPUT_NORMAL_BORDER);
            levelSpinner.setValue(1);
            feedbackLabel.setForeground(AppTheme.TEXT_SUCCESS);
            feedbackLabel.setText("✔ Berhasil menambahkan '" + cleanName + "' [" + selectedRole + " Lv." + level + "] ke dalam daftar!");
            nameField.requestFocusInWindow();

            if (onCharacterAdded != null) {
                onCharacterAdded.run();
            }
        } catch (IllegalArgumentException | IllegalStateException ex) {
            showError("Ditolak oleh Sistem: " + ex.getMessage(), false);
            levelSpinner.requestFocusInWindow();
        }
    }

    private void showError(String message, boolean highlightNameField) {
        feedbackLabel.setForeground(AppTheme.TEXT_ERROR);
        feedbackLabel.setText("✖ " + message);
        if (highlightNameField) {
            nameField.setBorder(AppTheme.INPUT_ERROR_BORDER);
            nameField.requestFocusInWindow();
        }
    }
}