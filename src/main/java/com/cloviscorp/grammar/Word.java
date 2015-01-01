package com.cloviscorp.grammar;

import org.thymeleaf.util.StringUtils;

import javax.persistence.*;
import java.util.LinkedList;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class Word { // derive LatinWord, GreekWord, etc each with persistence to cat=<lang>. tab=<WORD>

    protected int id; // PK id in table
    protected int textid, clauseid, wordid;
    protected int dictid, morphid;

    protected Letters str;

    @OneToOne
    protected Lexeme lexeme; // if null then ambiguous

    @OneToOne
    protected Morpheme morpheme; // if null then ambiguous

    protected Context context;

    protected LinkedList<Syllable> syllables;
    // private Syntax syntax; // syntax in its clause

    @Id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTextid() { return context.textid; }
    public void setTextid(int textid) {
        if (context==null) context = new Context();
        context.textid = textid;
    }
    public int getClauseid() { return context.clauseid; }
    public void setClauseid(int clauseid) {
        if (context==null) context = new Context();
        context.clauseid = clauseid;
    }
    public int getWordid() { return context.wordid; }
    public void setWordid(int wordid) {
        if (context==null) context = new Context();
        context.wordid = wordid;
    }

    public int getDictid() { return dictid;  }
    public void setDictid(int dictid) { this.dictid = dictid; }

    public int getMorphid() { return morphid; }
    public void setMorphid(int morphid) { this.morphid = morphid; }



    @Transient
    public String getTranslit() {
        return str.getTranslit();
    }

    @Transient
    public String getPresent() {
        return str.getPresent();
    }

    @Override
    public String toString() {
        return "Word{#" + /*id +*/
                //", translit='" + getTranslit() + '\'' +
                //", present='" + getPresent() + '\'' +
                ", syllables='" + StringUtils.join(syllables, '-')+ '\'' +
                ", lexeme='" + lexeme + '\'' +
                ", morpheme='" + morpheme + '\'' +
                '}';
    }

}

