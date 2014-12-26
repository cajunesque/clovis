package com.programmingfree.springservice;

import org.thymeleaf.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

public abstract class Word { // derive LatinWord, GreekWord, etc each with persistence to cat=<lang>. tab=<WORD>

    //private Context id; // combination of textid and wordid, used to getPhrase and getClause

    private Letters str; // necessary?
    private List<Syllable> syllables;
    private Lexeme lexeme; // if null then ambiguous
    private Morpheme morpheme; // if null then ambiguous
    // private Syntax syntax; // syntax in its clause

    /*
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    */
    public String getTranslit() {
        return str.getTranslit();
    }

    public String getPresent() {
        return str.getPresent();
    }


    @Override
    public String toString() {
        return "Word{#" + /*id +*/
                ", translit='" + getTranslit() + '\'' +
                ", present='" + getPresent() + '\'' +
                ", syllables='" + StringUtils.join(syllables, '-')+ '\'' +
                ", lexeme='" + lexeme + '\'' +
                ", morpheme='" + morpheme + '\'' +
                '}';
    }

}

