package com.programmingfree.springservice;

import java.util.List;

public abstract class Syllable { // derive LatinSyllable, GreekSyllable, etc each with persistence to cat=<lang>. tab=<LETTER>

    public enum SyllableType { OPEN_SHORT, OPEN_LONG, CLOSED }

    private int id;

    private String translit;
    private String present;
    private SyllableType type;
    private List<Letter> letters;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTranslit() {
        return translit;
    }
    public void setTranslit(String translit) {
        this.translit = translit;
    }

    public String getPresent() {
        return present;
    }
    public void setPresent(String present) {
        this.present = present;
    }

    public SyllableType getType() {
        return type;
    }
    public void setType(SyllableType type) {
        this.type = type;
    }

    public List<Letter> getLetters() {
        return letters;
    }
    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

    @Override
    public String toString() {
        return "Syllable{#" + id +
                ", translit='" + translit + '\'' +
                ", present='" + present + '\'' +
                ", type=" + type +
                '}';
    }

}

