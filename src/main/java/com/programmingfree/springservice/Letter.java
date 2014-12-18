package com.programmingfree.springservice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
@MappedSuperclass
@Access(AccessType.PROPERTY)
*/
public abstract class Letter { // derive LatinLetter, GreekLetter, etc each with persistence to cat=<lang>. tab=<LETTER>

    public enum PhoneticType {
        PUNCT, DIACRITIC, VOWEL, SHORT, LONG, COMMON, DIPHTHONG, SEMIVOWEL, LIQUID, NASAL, LABIAL, DENTAL, GUTTURAL, SIBILANT, ASPIRATE, VOICED;
        public static boolean contains(String s) {
            for (PhoneticType choice : values()) if (choice.name().equals(s)) { return true; }
            return false;
        }
    }
    public enum PositionType {
        ALL, INITIAL, MEDIAL, FINAL;  /* initial, medial, final "only" */
        public static boolean contains(String s) {
            for (PositionType choice : values()) if (choice.name().equals(s)) { return true; }
            return false;
        }
    }
    public enum CaseType {
        ALL, LOWER, UPPER;
        public static boolean contains(String s) {
            for (CaseType choice : values()) if (choice.name().equals(s)) { return true; }
            return false;
        }
    }

    private int id;
    private String name;
    private String translit;
    private String present;
    private String types;
    private List<PhoneticType> phoneticTypes;
    private CaseType caseType;
    private PositionType positionType;

    //@Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

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

    public String getTypes() {
        StringBuilder sb = new StringBuilder(); //
        if (phoneticTypes!=null) for (PhoneticType type : phoneticTypes) sb.append(" ").append(type.name());
        if (caseType!=null) sb.append(" ").append(caseType.name());
        if (positionType!=null) sb.append(" ").append(positionType.name());
        return sb.substring( sb.length()>0?1:0 );
    }
    public void setTypes(String types) {
        List<PhoneticType> phoneticTypes = new ArrayList<PhoneticType>();
        CaseType caseType = null;
        PositionType positionType = null;
        for (String val : Arrays.asList(types.split(" "))) {
            String canonicalVal = val.toUpperCase();
            if (PhoneticType.contains(canonicalVal)) phoneticTypes.add(PhoneticType.valueOf(canonicalVal));
            else if (CaseType.contains(canonicalVal)) caseType = CaseType.valueOf(canonicalVal);
            else if (PositionType.contains(canonicalVal)) positionType = PositionType.valueOf(canonicalVal);
        }
        this.phoneticTypes = phoneticTypes; // could be zero-length list
        this.caseType = (caseType==null)? CaseType.ALL : caseType;
        this.positionType = (positionType==null)? PositionType.ALL : positionType;
    }

    @Override
    public String toString() {
        return "Letter{#" + id +
                ", translit='" + translit + '\'' +
                ", present='" + present + '\'' +
                ", types={" + getTypes() + "}" +
                '}';
    }

}

