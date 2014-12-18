package com.programmingfree.springservice;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Letters { // derive LatinString, GreekString, etc


    private List<Letter> letters;

    public Letters(List<Letter> lets) {
        this.letters = lets;
    }

    public Letter letterAt(int i) {
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
    public Letters substring(int i) {
        int i1 = length();
        return new Letters(letters.subList(i, i1));
    }
    @Override
    public java.lang.String toString() {
        return "Letters/String{" +
                ", Letters='" + letters + '\'' +
                '}';
    }

}

