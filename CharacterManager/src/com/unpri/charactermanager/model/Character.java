package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {
    private static int totalCharacterCreated = 0; // Untuk analisis Bagian D.3

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // Constructor 1: Default
    public Character(String name, CharacterType type) {
        this.name = name;
        this.type = type;
        this.level = 1;
        this.mana = 50;
        recalculateStats();
        totalCharacterCreated++;
    }

    // Constructor 2: Lengkap
    public Character(String name, CharacterType type, int level, int mana) {
        this.name = name;
        this.type = type;
        setLevel(level);
        setMana(mana);
        totalCharacterCreated++;
    }

    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    private void recalculateStats() {
        int baseHp = (type == CharacterType.WARRIOR) ? 150 : 100;
        int baseAtk = (type == CharacterType.WARRIOR) ? 20 : 15;
        this.hitPoints = baseHp + (this.level * 25);
        this.attackPower = baseAtk + (this.level * 5);
    }

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CharacterType getType() { return type; }
    public void setType(CharacterType type) {
        this.type = type;
        recalculateStats();
    }

    public int getLevel() { return level; }
    public void setLevel(int level) {
        if (level < 1) throw new IllegalArgumentException("Level minimal 1");
        this.level = level;
        recalculateStats();
    }

    public int getHitPoints() { return hitPoints; }
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }

    public int getAttackPower() { return attackPower; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }

    public int getMana() { return mana; }
    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh bernilai negatif!");
        }
        this.mana = mana;
    }

    public boolean isAlive() {
        return this.hitPoints > 0;
    }

    // Bagian A.1 & D.4: Override equals() membandingkan nama (case-insensitive)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Character other = (Character) obj;
        return this.name != null && this.name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name == null ? null : name.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("Character{name='%s', type=%s, level=%d, HP=%d, ATK=%d, Mana=%d, Alive=%b}",
                name, type, level, hitPoints, attackPower, mana, isAlive());
    }
}