package com.cloviscorp.grammar.greek.model;

import com.cloviscorp.grammar.Morpheme;
import com.cloviscorp.grammar.MorphRule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="morphology", catalog="greek")
public class GreekMorpheme extends Morpheme {

    private List<MorphRule> variants;

    @OneToMany(mappedBy="morphid")
    private List<GreekWord> words;

    @Column(name="variant")
    public String getVariants() {
        StringBuilder sb = new StringBuilder();
        for (MorphRule rule : variants) sb.append(":").append(rule.getMorphrule());
        return sb.substring(sb.length() > 0 ? 1 : 0);
    }
    @Column(name="variant")
    public void setVariants(String variantList) {
        this.variants = new ArrayList<MorphRule>();
        for (String str : Arrays.asList(variantList.split(":"))) {
            variants.add(new MorphRule(str));
        }
    }

    @Override
    public String toString() {
        return "Morpheme{#"+ id +
            ", rulekey='" + rulekey + '\'' +
            ", pars='" + pars + '\'' +
            ", rulevals=" + this.getRulevals() +
            ((variants==null)? "" : ", variants=" + this.getVariants()) +
            ", pattern='" + pattern + '\'' +
            ", freq=" + freq +
            '}';
    }
}

