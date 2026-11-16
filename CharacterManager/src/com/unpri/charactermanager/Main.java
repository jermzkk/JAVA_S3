package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BAGIAN A: INISIALISASI 3 KARAKTER & DEMO LEVEL UP ===");
        Character char1 = new Character("Arthur", CharacterType.WARRIOR);
        Character char2 = new Character("Merlin", CharacterType.MAGE, 3, 120);
        Character char3 = new Character("Legolas", CharacterType.ARCHER, 2, 80);

        System.out.println(char1);
        System.out.println(char2);
        System.out.println(char3);

        System.out.println("\n--- Demo Level Up Arthur ke Level 5 ---");
        char1.setLevel(5);
        System.out.println(char1);

        System.out.println("\n=== BAGIAN B: UJI COBA MANA & ISALIVE() ===");
        Character char4 = new Character("Ezio", CharacterType.ROGUE);
        char4.setMana(95);
        System.out.println("Karakter baru: " + char4);
        System.out.println("Status isAlive() " + char4.getName() + ": " + char4.isAlive());
    }
}