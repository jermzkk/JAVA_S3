package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {
    private static int totalCharacterCreated = 0;

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;
    private boolean alive;

    public Character(String name, CharacterType type) {
        this(name, type, 1, 100);
    }

    public Character(String name, CharacterType type, int level, int mana) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama karakter tidak boleh kosong.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Role karakter wajib dipilih.");
        }
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException("Level karakter harus berada di rentang 1 hingga 99.");
        }
        // Mini-Challenge (Bagian B.1): MAGE maksimal level 60 di Model
        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException("Aturan Domain: Karakter bertipe MAGE maksimal mencapai level 60.");
        }
        if (mana < 0) {
            throw new IllegalArgumentException("Mana karakter tidak boleh bernilai negatif.");
        }

        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.mana = mana;
        this.alive = true;
        recalculateStats();
        totalCharacterCreated++;
    }

    private void recalculateStats() {
        this.hitPoints = 100 + (this.level * 25);
        this.attackPower = 15 + (this.level * 5);
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException("Level karakter harus berada di rentang 1 hingga 99.");
        }
        if (this.type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException("Aturan Domain: Karakter bertipe MAGE maksimal mencapai level 60.");
        }
        this.level = level;
        recalculateStats();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif.");
        }
        this.mana = mana;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Character other = (Character) obj;
        return this.name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return "Character{name='" + name + "', type=" + type + ", level=" + level +
               ", HP=" + hitPoints + ", ATK=" + attackPower + ", Mana=" + mana + ", Alive=" + alive + "}";
    }
}