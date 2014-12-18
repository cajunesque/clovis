package com.programmingfree.springservice;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public class Morpheme {

    @Id
    private int id;

    private String rulekey;
    private String pars;
    private List<String> ruleval;
    private String pattern;
    private Double freq;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getRulekey() {
        return rulekey;
    }
    public void setRulekey(String rulekey) {
        this.rulekey = rulekey;
    }

    public String getPars() {
        return pars;
    }
    public void setPars(String pars) {
        this.pars = pars;
    }

    public List<String> getRuleval() {
        return ruleval;
    }
    public void setRuleval(List<String> ruleval) {
        this.ruleval = ruleval;
    }

    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Double getFreq() {
        return (freq==null)? 1.1: freq;
    }
    public void setFreq(Double freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "Morpheme{#"+ id +
                ", rulekey='" + rulekey + '\'' +
                ", pars='" + pars + '\'' +
                ", ruleval=" + ruleval +
                ", pattern='" + pattern + '\'' +
                ", freq=" + freq +
                '}';
    }
}