package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        CharacterManager manager = new CharacterManager();

        // Total 6 Karakter dengan variasi role dan level (< 5 dan >= 5)
        manager.addCharacter(new Character("Arthur", CharacterType.WARRIOR, 4, 60));   // Level < 5 (Putih)
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 6, 150));     // Level >= 5 (Emas Tebal)
        manager.addCharacter(new Character("Legolas", CharacterType.ARCHER, 3, 90));   // Level < 5 (Putih)
        manager.addCharacter(new Character("Vex", CharacterType.ROGUE, 5, 110));       // Level >= 5 (Emas Tebal)
        manager.addCharacter(new Character("Garrick", CharacterType.WARRIOR, 7, 40));  // Level >= 5 (Emas Tebal)
        manager.addCharacter(new Character("Lyra", CharacterType.MAGE, 2, 130));       // Level < 5 (Putih)

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow window = new MainWindow(manager);
                window.setVisible(true);
            }
        });
    }
}