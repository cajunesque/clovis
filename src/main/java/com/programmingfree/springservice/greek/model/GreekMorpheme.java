package com.programmingfree.springservice.greek.model;

import com.programmingfree.springservice.MorphRule;
import com.programmingfree.springservice.Morpheme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="morphology", catalog="greek")
public class GreekMorpheme extends Morpheme {

    private List<MorphRule> variants;

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

