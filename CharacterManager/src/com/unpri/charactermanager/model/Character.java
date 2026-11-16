package com.unpri.charactermanager.model;

public class Character {
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana; // Atribut baru Bagian B

    // Constructor 1: Default / Overloaded (mulai dari level 1, mana default 50)
    public Character(String name, CharacterType type) {
        this(name, type, 1, 50);
    }

    // Constructor 2: Lengkap
    public Character(String name, CharacterType type, int level, int mana) {
        this.name = name;
        this.type = type;
        setLevel(level);
        setMana(mana);
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level minimal adalah 1");
        }
        this.level = level;
        // Hitung ulang HP dan Attack otomatis saat level berubah
        this.hitPoints = 100 + (level * 20);
        this.attackPower = 15 + (level * 5);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = Math.max(0, hitPoints);
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    // Bagian B: Getter & Setter Mana dengan validasi
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh bernilai negatif!");
        }
        this.mana = mana;
    }

    // Bagian B: Method isAlive()
    public boolean isAlive() {
        return this.hitPoints > 0;
    }

    @Override
    public String toString() {
        return String.format(
            "Character{name='%s', type=%s, level=%d, HP=%d, ATK=%d, Mana=%d, Alive=%b}",
            name, type, level, hitPoints, attackPower, mana, isAlive()
        );
    }
}