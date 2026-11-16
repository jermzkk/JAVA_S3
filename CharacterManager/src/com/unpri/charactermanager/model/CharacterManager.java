package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {
    private ArrayList<Character> characters;

    public CharacterManager() {
        this.characters = new ArrayList<>();
    }

    // Bagian A.2 & A.3: Tambah karakter & tolak nama duplikat (menggunakan equals())
    public boolean addCharacter(Character character) {
        if (character == null || characters.contains(character)) {
            return false; // Ditolak jika null atau nama sudah ada
        }
        characters.add(character);
        return true;
    }

    // Bagian A.2: Cari berdasarkan nama
    public Character findByName(String name) {
        if (name == null) return null;
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    // Bagian A.2: Hapus karakter berdasarkan nama
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target != null) {
            return characters.remove(target);
        }
        return false;
    }

    // Bagian A.2: Total karakter dalam manager
    public int getTotalCharacters() {
        return characters.size();
    }

    // Bagian A.2 & A.4: Defensive copy (mengembalikan salinan ArrayList, bukan referensi asli)
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // Bagian B.1: Rata-rata level (mengembalikan 0 jika list kosong agar bebas ArithmeticException)
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0;
        }
        int totalLevel = 0;
        for (Character c : characters) {
            totalLevel += c.getLevel();
        }
        return (double) totalLevel / characters.size();
    }

    // Bagian B.2: Mengembalikan ArrayList semua karakter dengan tipe tertentu
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();
        if (type == null) return result;
        for (Character c : characters) {
            if (c.getType() == type) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Bagian C (Fitur Analitik Rancangan Sendiri):
     * Mengambil daftar karakter yang masih hidup (isAlive() == true) dan memiliki
     * kapasitas mana minimal sebesar parameter minMana (siap bertarung/casting skill).
     * Mengembalikan salinan list baru untuk menjaga prinsip encapsulation.
     *
     * @param minMana batas minimal mana yang dibutuhkan
     * @return ArrayList baru berisi karakter hidup yang memenuhi syarat mana
     */
    public ArrayList<Character> getCombatReadyCasters(int minMana) {
        ArrayList<Character> readyList = new ArrayList<>();
        for (Character c : characters) {
            if (c.isAlive() && c.getMana() >= minMana) {
                readyList.add(c);
            }
        }
        return readyList;
    }
}