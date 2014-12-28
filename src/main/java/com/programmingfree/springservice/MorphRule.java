package com.programmingfree.springservice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by markcbordelon on 12/27/14.
 */
public class MorphRule {

    static final String OPS_START = "&(", OPS_STOP = ")", OPS_OUTER_DELIM = ":", OPS_INNER_DELIM = ">";
    static Map<String, String> operations; // ops between litterals: sandhi, augments, redup variable names with their values
    static final String MAC_START = "@(", MAC_STOP = ")"; // code is a function of String function(String)
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

    private static String resolveAllVariables(String rule, Map<String, String> vars) {
        for (String key : vars.keySet()) { rule.replaceAll(key,vars.get(key)); }
        return rule;
    }
    private static String resolveAllOps(String rule) {
        if (operations==null) return rule;
        for (String key : operations.keySet()) { rule.replaceAll(key,operations.get(key)); }
        return performAllOps(rule);
    }

    private static String performAllOps(String rule) {
        int startpos = rule.indexOf(OPS_START);
        while (startpos>=0) {
            int endpos = rule.indexOf(OPS_STOP);
            Map<String,String> sandhiList = new HashMap<String, String>();
            int maxlenkey = loadSandhiMap(sandhiList, rule.substring(startpos, endpos));
            int startn = maxlenkey, startm = maxlenkey;
            int n = 0, m = 0;
            String sandhiTrial, sandhiResult = "";
            for (n = startn; n >=0; n--) {
                for (m = startm; m >= 0; m--) {
                    sandhiTrial = rule.substring(startpos-n, endpos+m);
                    if (sandhiList.containsKey(sandhiTrial)) {
                        sandhiResult = sandhiList.get(sandhiTrial);
                        break;
                    }
                }
            }
            rule = rule.substring(0,startpos-n) + sandhiResult + rule.substring(endpos+m);
        }
        return rule;
    }

    public static int loadSandhiMap(Map<String,String> map, String tokenizedString) {
        String[] nameValues = tokenizedString.split(OPS_OUTER_DELIM);
        int maxkeylen = 0;
        for (String nameValue : nameValues) {
            String[] pair = nameValue.split(OPS_INNER_DELIM);
            String key=pair[0], val=pair[1]; // check for error
            /* if (pair.length == 1) {
                // Duplicate the key name if there is only one value
                map.put(pair[0], pair[0]);
            } else { */
                map.put(key, val);
            // }
            if (key.length()>maxkeylen) maxkeylen = key.length();
        }
        return maxkeylen;
    }


    private static String resolveAndPerformAllMacros(String rule) {
        if (macros==null) return rule;
        for (String key : macros.keySet()) {
            int startpos = rule.indexOf(key);
            if (startpos>=0) {
                int endpos = rule.indexOf(MAC_STOP);
                String funcArg = rule.substring(startpos, endpos);
                String funcVal = ""; // = eval(macros.get(key), funcArg)  --  RHINO SCRIPT ENGINE or GROOVY:  http://www.beyondlinux.com/2011/08/07/3-method-to-evaluate-expressions/
                rule = rule.substring(0,startpos) + funcVal + rule.substring(endpos);
            }
        }
        return rule;
    }

    public String resolvedRule(Map<String, String> varlist) {
        return resolveAndPerformAllMacros(resolveAllOps(resolveAllVariables(morphrule, varlist)));
    }

    @Override
    public String toString() {
        return "MorphRule{" +
                "morphrule='" + morphrule + '\'' +
                '}';
    }
}
