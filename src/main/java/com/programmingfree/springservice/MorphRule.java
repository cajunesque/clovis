package com.programmingfree.springservice;

import java.util.Map;

/**
 * Created by markcbordelon on 12/27/14.
 */
public class MorphRule {

    static Map<String, String> operations; // ops between litterals: sandhi, augments, redup variable names with their values
    static Map<String, String> macros; // procs on words: accentuation etc variable names with their values

    String morphrule; // literals (pref, end, operators), variables (stems, operators, macros)

    public MorphRule(String morphRuleStr) {
      this.morphrule = morphRuleStr;
    }

    public String getMorphrule() {
        return morphrule;
    }
    public void setMorphrule(String morphrule) {
        this.morphrule = morphrule;
    }

    private static String resolveAllVariables(String rule, Map<String, String> vars) { return rule; }
    private static String resolveAllOps(String rule) { return rule; }
    private static String resolveAllMacros(String rule) { return rule; }

    public String resolvedRule(Map<String, String> varlist) {
        return resolveAllMacros(resolveAllOps(resolveAllVariables(morphrule, varlist)));
    }

    @Override
    public String toString() {
        return "MorphRule{" +
                "morphrule='" + morphrule + '\'' +
                '}';
    }
}
