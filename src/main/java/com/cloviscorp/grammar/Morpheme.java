package com.cloviscorp.grammar;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MappedSuperclass
public class Morpheme {

    protected int id;

    protected String rulekey;
    protected String pars;
    protected List<MorphRule> rulevals;
    protected String pattern;
    protected Double freq;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
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

    @Column(name="ruleval")
    public String getRulevals() {
        StringBuilder sb = new StringBuilder();
        for (MorphRule rule : rulevals) sb.append(":").append(rule.getMorphrule());
        return sb.substring(sb.length() > 0 ? 1 : 0);
    }
    @Column(name="ruleval")
    public void setRulevals(String rulevalList) {
        this.rulevals = new ArrayList<MorphRule>();
        for (String str : Arrays.asList(rulevalList.split(":"))) {
            rulevals.add(new MorphRule(str));
        }
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
                ((rulevals==null)? "" : ", rulevals=" + this.getRulevals()) +
                ", pattern='" + pattern + '\'' +
                ", freq=" + freq +
                '}';
    }
}