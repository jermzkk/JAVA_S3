package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        CharacterManager manager = new CharacterManager();

        manager.addCharacter(new Character("Arthur", CharacterType.WARRIOR, 4, 50));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 6, 200));
        manager.addCharacter(new Character("Legolas", CharacterType.ARCHER, 9, 90));
        manager.addCharacter(new Character("Shadow", CharacterType.ROGUE, 3, 80));
        manager.addCharacter(new Character("Lyra", CharacterType.MAGE, 5, 180));
        manager.addCharacter(new Character("Vex", CharacterType.ROGUE, 5, 110));
        manager.addCharacter(new Character("Garrick", CharacterType.WARRIOR, 7, 40));
        manager.addCharacter(new Character("Sylvanas", CharacterType.ARCHER, 4, 120));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow window = new MainWindow(manager);
                window.setVisible(true);
            }
        });
    }
}