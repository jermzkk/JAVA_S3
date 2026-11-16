package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CharacterManager manager = new CharacterManager();

        // Uji Bagian B.1: Edge case rata-rata level saat list kosong
        System.out.println("=== 1. Uji Edge Case List Kosong ===");
        System.out.println("Rata-rata level awal (kosong): " + manager.getAverageLevel());

        // Uji Bagian A.2: Menambah karakter
        System.out.println("\n=== 2. Penambahan Karakter & Uji Duplikat ===");
        System.out.println("Tambah Arthur: " + manager.addCharacter(new Character("Arthur", CharacterType.WARRIOR, 4, 60)));
        System.out.println("Tambah Merlin: " + manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 6, 150)));
        System.out.println("Tambah Legolas: " + manager.addCharacter(new Character("Legolas", CharacterType.ARCHER, 3, 90)));
        System.out.println("Tambah Vex: " + manager.addCharacter(new Character("Vex", CharacterType.ROGUE, 5, 110)));

        // Uji Bagian A.3: Penolakan nama duplikat (menguji equals() case-insensitive)
        boolean duplicateAdded = manager.addCharacter(new Character("arthur", CharacterType.MAGE));
        System.out.println("Tambah duplikat 'arthur': " + duplicateAdded + " (Ditolak!)");
        System.out.println("Total karakter di manager: " + manager.getTotalCharacters());
        System.out.println("Total objek Character dibuat (static): " + Character.getTotalCharacterCreated());

        // Uji Bagian A.4: Defensive Copy pada getAllCharacters()
        System.out.println("\n=== 3. Uji Defensive Copy getAllCharacters() ===");
        ArrayList<Character> externalCopy = manager.getAllCharacters();
        externalCopy.clear(); // Menghapus isi salinan luar
        System.out.println("Ukuran list salinan setelah .clear(): " + externalCopy.size());
        System.out.println("Total karakter asli di manager tetap: " + manager.getTotalCharacters());

        // Uji Bagian B.1 & B.2: Mini-Challenge
        System.out.println("\n=== 4. Uji Mini-Challenge (Average Level & Find By Type) ===");
        System.out.println("Rata-rata level saat ini: " + manager.getAverageLevel());
        System.out.println("Karakter bertipe MAGE: " + manager.findByType(CharacterType.MAGE));

        // Uji Bagian C: Fitur Analitik Rancangan Sendiri
        System.out.println("\n=== 5. Uji Fitur Mandiri: getCombatReadyCasters(minMana = 100) ===");
        ArrayList<Character> readyCasters = manager.getCombatReadyCasters(100);
        for (Character c : readyCasters) {
            System.out.println("- Siap tempur: " + c);
        }

        // Uji Remove & Find
        System.out.println("\n=== 6. Uji Find & Remove ===");
        System.out.println("Cari 'Merlin': " + manager.findByName("Merlin"));
        System.out.println("Hapus 'Vex': " + manager.removeCharacter("Vex"));
        System.out.println("Total karakter setelah penghapusan: " + manager.getTotalCharacters());
    }
}