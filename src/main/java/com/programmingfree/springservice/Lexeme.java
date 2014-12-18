package com.programmingfree.springservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Lexeme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String lex;
    private String pars;
    private String semanid;
    private String def;
    private String etym;
    private String syntax;
    private Double freq;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLex() {
        return lex;
    }
    public void setLex(String lex) {
        this.lex = lex;
    }

    public String getPars() {
        return pars;
    }
    public void setPars(String pars) {
        this.pars = pars;
    }

    public String getSemanid() {
        return semanid;
    }
    public void setSemanid(String semanid) {
        this.semanid = semanid;
    }

    public String getDef() {
        return def;
    }
    public void setDef(String def) {
        this.def = def;
    }

    public String getEtym() {
        return etym;
    }
    public void setEtym(String etym) {
        this.etym = etym;
    }

    public String getSyntax() {
        return syntax;
    }
    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public Double getFreq() {
        return (freq==null)? 1.1: freq;
    }
    public void setFreq(Double freq) {
        this.freq = freq;
    }

    // lexical methods: synonyms, antonyms, pictures (via semanid), derivatives
    // grammar methods: getInflectedForms() returns lexpars+word, getInflectedForms(rulekey) returns lexpars+word

    @Override
    public String toString() {
        return "Lexeme{#"+ id +
               ", lex='" + lex + '\'' +
               ", pars='" + pars + '\'' +
               ", semanid='" + semanid + '\'' +
               ", def='" + def + '\'' +
               ", etym='" + etym + '\'' +
               ", syntax='" + syntax + '\'' +
               ", freq=" + freq +
               '}';
    }
}