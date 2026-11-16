package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {
    public static final int MAX_ROSTER_CAPACITY = 12;
    private ArrayList<Character> characters;

    public CharacterManager() {
        this.characters = new ArrayList<>();
    }

    public boolean isNameTaken(String name) {
        if (name == null) {
            return false;
        }
        String cleanName = name.trim();
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(cleanName)) {
                return true;
            }
        }
        return false;
    }

    public boolean addCharacter(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Objek karakter tidak boleh null.");
        }
        // Aturan Validasi Tambahan 1 (Model): Kapasitas maksimal 12 karakter
        if (characters.size() >= MAX_ROSTER_CAPACITY) {
            throw new IllegalStateException("Kapasitas tim penuh! Maksimal " + MAX_ROSTER_CAPACITY + " karakter di dalam daftar.");
        }
        if (isNameTaken(character.getName())) {
            return false;
        }
        characters.add(character);
        return true;
    }

    public Character findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name.trim())) {
                return c;
            }
        }
        return null;
    }

    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target != null) {
            return characters.remove(target);
        }
        return false;
    }

    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(this.characters);
    }

    public int getTotalCharacters() {
        return characters.size();
    }

    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0.0;
        }
        int totalLevel = 0;
        for (Character c : characters) {
            totalLevel += c.getLevel();
        }
        return (double) totalLevel / characters.size();
    }

    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();
        if (type == null) {
            return result;
        }
        for (Character c : characters) {
            if (c.getType() == type) {
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<Character> getCombatReadyCasters(int minMana) {
        ArrayList<Character> readyCasters = new ArrayList<>();
        for (Character c : characters) {
            if (c.isAlive() && c.getMana() >= minMana) {
                readyCasters.add(c);
            }
        }
        return readyCasters;
    }
}