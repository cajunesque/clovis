package com.programmingfree.springservice;

import java.util.List;

public abstract class Word { // derive LatinWord, GreekWord, etc each with persistence to cat=<lang>. tab=<WORD>

    //private Context id; // combination of textid and wordid, used to getPhrase and getClause

    private String translit;
    private String present;
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


    @Override
    public String toString() {
        return "Word{#" + /*id +*/
                ", translit='" + translit + '\'' +
                ", present='" + present + '\'' +
                '}';
    }

}

