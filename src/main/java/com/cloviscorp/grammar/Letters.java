package com.cloviscorp.grammar;

import java.util.ArrayList;
import java.util.List;


public class Letters { // derive LatinString, GreekString, etc

    private List<Letter> letters;

    public Letters() {
        letters = new ArrayList<Letter>();
    }
    public Letters(List<Letter> lets) {
        this.letters = lets;
    }

    public List<Letter> getLetters() { return letters; }
    public void setLetters(List<Letter> letters) { this.letters = letters; }

    public Letter letterAt(int i) {
        if (i<0 || i>length()-1) throw new LettersException("Out of Bounds");
        return letters.get(i);
    }
    public int length() {
        return letters.size();
    }

    public String getTranslit() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length(); i++) { sb.append(letterAt(i).getTranslit()); }
        return sb.toString();
    }
    public String getPresent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length(); i++) { sb.append(letterAt(i).getPresent()); }
        return sb.toString();
    }

    public Letters append(Letter let) {
        letters.add(let);
        return new Letters(letters);
    }
    public Letters append(Letters lets) {
        letters.addAll(lets.letters);
        return new Letters(letters);
    }
    public Letters substring(int from) {
        return new Letters(letters.subList(from, length()));
    }
    public Letters substring(int from, int to) {
        return new Letters(letters.subList(from, to));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null /*|| getClass() != o.getClass()*/) return false; // instance of

        Letters letters1 = (Letters) o;

        if (!letters.equals(letters1.letters)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return letters.hashCode();
    }

    @Override
    public java.lang.String toString() {
        return "Letters/String{" +
                ", Letters='" + letters + '\'' +
                '}';
    }

}

