package com.programmingfree.springservice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class Letter { // derive LatinLetter, GreekLetter, etc each with persistence to cat=<lang>. tab=<LETTER>

    public enum PhoneticType {
        PUNCT, DIACRITIC, VOWEL, SHORT, LONG, COMMON, DIPHTHONG, SEMIVOWEL, LIQUID, NASAL, LABIAL, DENTAL, GUTTURAL, SIBILANT, ASPIRATE, VOICED;
        public static PhoneticType safeValueOf(String s) {
            s = s.toUpperCase();
            for (PhoneticType choice : values()) { if (choice.name().equals(s)) { return choice; } }
            return null;
        }
    }

    public enum PositionType {
        ALLPOSITION, INITIAL, MEDIAL, FINAL;  /* initial, medial, final "only" */
        public static PositionType safeValueOf(String s) {
            s = s.toUpperCase();
            for (PositionType choice : values()) { if (choice.name().equals(s)) { return choice; } }
            return null;
        }
    }

    public enum CaseType {
        ALLCASE, LOWER, UPPER;
        public static CaseType safeValueOf(String s) {
            s = s.toUpperCase();
            for (CaseType choice : values()) { if (choice.name().equals(s)) { return choice; } }
            return null;
        }
    }

    private int id;
    private String name;
    private String translit;
    private String present;

    /*@JsonInclude*/ private String types;
    private List<PhoneticType> phoneticTypes;
    private CaseType caseType;
    private PositionType positionType;

    @Id
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
        if (phoneticTypes != null) for (PhoneticType type : phoneticTypes) sb.append(" ").append(type.name());
        if (caseType != null) sb.append(" ").append(caseType.name());
        if (positionType != null) sb.append(" ").append(positionType.name());
        return sb.substring(sb.length() > 0 ? 1 : 0);
    }
    public void setTypes(String types) {
        this.phoneticTypes = new ArrayList<PhoneticType>();
        PhoneticType ph = null;
        CaseType c = null;
        PositionType p = null;
        for (String str : Arrays.asList(types.split(" "))) {
            ph = PhoneticType.safeValueOf(str);
            if (ph != null) this.phoneticTypes.add(ph);
            if (c == null) c = CaseType.safeValueOf(str);
            if (p == null) p = PositionType.safeValueOf(str);
        }
        this.caseType = ((c == null) ? CaseType.ALLCASE : c);
        this.positionType = ((p == null) ? PositionType.ALLPOSITION : p);
    }

    @Transient public boolean isVowel() { return phoneticTypes.contains(PhoneticType.VOWEL); }
    @Transient public boolean isDiphthong() { return phoneticTypes.contains(PhoneticType.DIPHTHONG); }
    // more as needed

    @Override
    public String toString() {
        return "Letter{#" + id +
                ", translit='" + translit + '\'' +
                ", present='" + present + '\'' +
                ", types={" + getTypes() + "}" +
                '}';
    }

}

