package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class AppTheme {
    // Konstanta Mini-Challenge: Batas level karakter veteran (Bagian B.1)
    public static final int VETERAN_LEVEL_THRESHOLD = 5;

    // 1. Minimal 3 Warna Dasar UI (Dark Theme)
    public static final Color BG_WINDOW = Color.decode("#1E1E2E");
    public static final Color BG_PANEL = Color.decode("#181825");
    public static final Color TEXT_PRIMARY = Color.decode("#CDD6F4");
    public static final Color TEXT_MUTED = Color.decode("#A6ADC8");
    public static final Color TEXT_GOLD = Color.decode("#F9E2AF"); // Teks emas untuk level >= 5

    // Palet Warna Kartu per Role (CharacterType)
    public static final Color ROLE_WARRIOR = Color.decode("#4A1E24");
    public static final Color ROLE_MAGE = Color.decode("#1E2A4A");
    public static final Color ROLE_ARCHER = Color.decode("#1E3F2B");
    public static final Color ROLE_ROGUE = Color.decode("#3B234A");
    public static final Color ROLE_DEFAULT = Color.decode("#2A2B3C");

    // 2. Minimal 3 Font Logis (Bagian A.1 & D.5)
    public static final Font FONT_HEADER = new Font("SansSerif", Font.BOLD, 17);
    public static final Font FONT_CARD_REGULAR = new Font("Monospaced", Font.PLAIN, 13);
    public static final Font FONT_CARD_VETERAN = FONT_CARD_REGULAR.deriveFont(Font.BOLD);
    public static final Font FONT_FOOTER = new Font("SansSerif", Font.BOLD, 14);

    // 3. Minimal 1 Border Dasar (Bagian A.1)
    public static final Border CARD_BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.decode("#45475A"), 1),
        BorderFactory.createEmptyBorder(8, 12, 8, 12)
    );

    public static final Border SECTION_PADDING = BorderFactory.createEmptyBorder(12, 16, 12, 16);

    private AppTheme() {
        // Mencegah instansiasi utility class tema
    }

    // Method pemetaan warna latar kartu berdasarkan role (Bagian A.1)
    public static Color colorFor(CharacterType type) {
        if (type == null) {
            return ROLE_DEFAULT;
        }
        switch (type) {
            case WARRIOR:
                return ROLE_WARRIOR;
            case MAGE:
                return ROLE_MAGE;
            case ARCHER:
                return ROLE_ARCHER;
            case ROGUE:
                return ROLE_ROGUE;
            default:
                return ROLE_DEFAULT;
        }
    }

    // Properti Tambahan 2: Border aksen samping berwarna khusus per role
    public static Border borderFor(CharacterType type) {
        Color accent = colorFor(type).brighter().brighter();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 5, 1, 1, accent),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
    }
}