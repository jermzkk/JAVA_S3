package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        CharacterManager manager = new CharacterManager();

        // Total 8 Karakter dengan variasi role dan level (Bagian B.2)
        manager.addCharacter(new Character("Arthur", CharacterType.WARRIOR, 4, 60));    // Lv < 5
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 6, 150));      // Lv >= 5 (Veteran)
        manager.addCharacter(new Character("Legolas", CharacterType.ARCHER, 3, 90));    // Lv < 5
        manager.addCharacter(new Character("Vex", CharacterType.ROGUE, 5, 110));        // Lv >= 5 (Veteran)
        manager.addCharacter(new Character("Garrick", CharacterType.WARRIOR, 7, 40));   // Lv >= 5 (Veteran)
        manager.addCharacter(new Character("Lyra", CharacterType.MAGE, 2, 130));        // Lv < 5
        manager.addCharacter(new Character("Sylvanas", CharacterType.ARCHER, 8, 120));  // Lv >= 5 (Veteran)
        manager.addCharacter(new Character("Shade", CharacterType.ROGUE, 1, 80));       // Lv < 5

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow window = new MainWindow(manager);
                window.setVisible(true);
            }
        });
    }
}