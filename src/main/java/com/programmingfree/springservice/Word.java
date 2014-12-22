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

    // Generic version: split double vowels, double consonants, consonants between vowels
    public LinkedList<Syllable> hyphenate(Letters str) {
        LinkedList<Syllable> syls = new LinkedList<Syllable>();
        syls.add(new Syllable(str)); // give basic logic without valid initials and make non-abstract
        syllabify(syls);
        // syls.removeFirst();
        return syls;
    }

    private void syllabify(LinkedList<Syllable> syls) {
        try {
            Letters finalstr = syls.getLast().getFinals();
            // break the final into 0.final and 1.initial (try to use logic already in new GreekSyllable)
            Letters final0 = finalstr.substring(0,2);
            Letters initial1 = finalstr.substring(3);
            syls.getLast().setFinals(final0);
            syls.add(new Syllable(new initial1));
            return;
        } catch (SyllableException e) {
            syllabify(syls);
        }
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

