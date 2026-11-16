package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class AppTheme {
    public static final int VETERAN_LEVEL_THRESHOLD = 5;

    public static final Color BG_WINDOW = Color.decode("#1E1E2E");
    public static final Color BG_PANEL = Color.decode("#181825");
    public static final Color BG_INPUT = Color.decode("#313244");
    public static final Color TEXT_PRIMARY = Color.decode("#CDD6F4");
    public static final Color TEXT_MUTED = Color.decode("#A6ADC8");
    public static final Color TEXT_GOLD = Color.decode("#F9E2AF");

    public static final Color TEXT_ERROR = Color.decode("#F38BA8");
    public static final Color TEXT_SUCCESS = Color.decode("#A6E3A1");
    public static final Color ACCENT_PRIMARY = Color.decode("#89B4FA");
    public static final Color BORDER_ERROR = Color.decode("#F38BA8");
    public static final Color BORDER_NORMAL = Color.decode("#585B70");

    public static final Color ROLE_WARRIOR = Color.decode("#4A1E24");
    public static final Color ROLE_MAGE = Color.decode("#1E2A4A");
    public static final Color ROLE_ARCHER = Color.decode("#1E3F2B");
    public static final Color ROLE_ROGUE = Color.decode("#3B234A");
    public static final Color ROLE_DEFAULT = Color.decode("#2A2B3C");

    public static final Font FONT_HEADER = new Font("SansSerif", Font.BOLD, 17);
    public static final Font FONT_CARD_REGULAR = new Font("Monospaced", Font.PLAIN, 13);
    public static final Font FONT_CARD_VETERAN = FONT_CARD_REGULAR.deriveFont(Font.BOLD);
    public static final Font FONT_FOOTER = new Font("SansSerif", Font.BOLD, 14);
    public static final Font FONT_FEEDBACK = new Font("SansSerif", Font.BOLD, 12);

    public static final Border CARD_BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.decode("#45475A"), 1),
        BorderFactory.createEmptyBorder(8, 12, 8, 12)
    );

    public static final Border INPUT_NORMAL_BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(BORDER_NORMAL, 1),
        BorderFactory.createEmptyBorder(5, 8, 5, 8)
    );

    public static final Border INPUT_ERROR_BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(BORDER_ERROR, 2),
        BorderFactory.createEmptyBorder(4, 7, 4, 7)
    );

    public static final Border SECTION_PADDING = BorderFactory.createEmptyBorder(12, 16, 12, 16);

    private AppTheme() {
    }

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

    public static Border borderFor(CharacterType type) {
        Color accent = colorFor(type).brighter().brighter();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 5, 1, 1, accent),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
    }
}