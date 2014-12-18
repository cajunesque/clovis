package com.programmingfree.springservice;

import java.util.List;

public abstract class Syllable { // derive LatinSyllable, GreekSyllable, etc each with persistence to cat=<lang>. tab=<LETTER>

    protected static List<List<Letter>> validInitials;

    public enum SyllableLength {SHORT, COMMON, LONG}

    private Letters initials; // 0 or more consonants
    private Letter vowel; // or diphthong
    private Letters finals; // 0 or more letters, ideally all consonants


    // no ctor, since abstract, but language ctor will initially try to load the syllable into initial, vowel, and finals
    // counting from beginning for consonants unless a vowel is found first
    // one vowel or diphthong in the vowel
    // all other letters (vowels too) in final
    // if all initials are empty or found in a language word (start con(s)) and vowel is one vowel or diphthong,
    // from remaining finals (e.g. rbstan) a new syllable can be made assign a type


    public String getTranslit() {
        return initials.toString();
    }

    public String getPresent() {
        return initials.toString();
    }

    public boolean isClosed() {
        return finals!=null && finals.length()>0;
    }
    public boolean isOpen() {
        return !isClosed();
    }

    public SyllableLength getLength() {
        if (isClosed()) return SyllableLength.LONG;
        else if (isOpen() && vowel.getTypes().contains("SHORT")) return SyllableLength.SHORT;
        else return SyllableLength.COMMON;
    }

    public Letters getInitials() {
        return initials;
    }
    public void setInitials(Letters initials) {
        this.initials = initials;
    }

    public Letter getVowel() {
        return vowel;
    }
    public void setVowel(Letter vowel) {
        this.vowel = vowel;
    }

    public Letters getFinals() {
        return finals;
    }
    public void setFinals(Letters finals) {
        this.finals = finals;
    }

    protected boolean isValidInitial() { return validInitials.contains(initials); }

    @Override
    public String toString() {
        return "Syllable{" +
                ", (initials,vowel,finals)=(" + initials + ',' + vowel + ','+ finals  + ')' +
                ", closed=" + isClosed() +
                ", length=" + getLength() +
                '}';
    }

}
