package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // 1. Siapkan Model & Data Karakter (Total 5 Karakter)
        CharacterManager manager = new CharacterManager();

        // 3 Karakter Awal
        manager.addCharacter(new Character("Arthur", CharacterType.WARRIOR, 4, 60));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 6, 150));
        manager.addCharacter(new Character("Legolas", CharacterType.ARCHER, 3, 90));

        // +2 Karakter Baru (Bagian B.1: Total 5 karakter)
        manager.addCharacter(new Character("Vex", CharacterType.ROGUE, 5, 110));
        manager.addCharacter(new Character("Garrick", CharacterType.WARRIOR, 7, 40));

        // 2. Jalankan GUI Swing di Event Dispatch Thread (Bagian A.4)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow window = new MainWindow(manager);
                window.setVisible(true);

                // --- LANGKAH SCREENSHOT 1 & 2 (Bagian B.3) ---
                // Jalankan program pertama kali dengan 2 baris di bawah ini dikomentari (//)
                // untuk mengambil SCREENSHOT 1 (Sebelum data diubah: Rata-rata Level = 5.00).
                // Setelah itu, hapus tanda komentar (//) di bawah ini lalu jalankan ulang
                // untuk mengambil SCREENSHOT 2 (Sesudah data diubah: Rata-rata Level = 6.20):

                 manager.findByName("Legolas").setLevel(9);
                 window.refreshData();
            }
        });
    }
}