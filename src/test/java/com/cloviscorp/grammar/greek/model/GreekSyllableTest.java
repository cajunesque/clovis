package com.cloviscorp.grammar.greek.model;

import com.cloviscorp.grammar.Letters;
import com.cloviscorp.grammar.SyllableException;
import com.cloviscorp.grammar.greek.model.GreekLetter;
import com.cloviscorp.grammar.greek.model.GreekString;
import com.cloviscorp.grammar.greek.model.GreekSyllable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GreekSyllableTest {

    GreekSyllable s;

    @Before
    public void setup() {
        GreekSyllable.validInitials = new ArrayList<Letters>();
        for (String cluster : "kh,th ph,th kh,l kh,r ph,l ph,r s,kh s,k,l s,ph s,p,l s,th s,t,r th,n th,r b,l b,r d,r g,l g,n g,r kh k,l k,n k,r k,s k,t m,n ph p,l p,n p,r ps p,t rh s,k s,m s,p s,t th t,m t,r ts b d g k l m n p s t ` h".split(" ")) {
            GreekString str = new GreekString();
            for (String let : cluster.split(",")) { str.append(createGreekLetter(let)); }
            GreekSyllable.validInitials.add(str);
        }
    }

    private GreekString prepareTestString(String initialStr, String vowelStr, String finalStr) {
        GreekString str = new GreekString();
        if (initialStr!=null) { for (String let : initialStr.split(" ")) { str.append(createGreekLetter(let)); } }
        if (vowelStr!=null) { for (String let : vowelStr.split(" ")) { str.append(createGreekLetter(let)); } }
        if (finalStr!=null) { for (String let : finalStr.split(" ")) { str.append(createGreekLetter(let)); } }
        return str;
    }

    private GreekLetter createGreekLetter(String translit) {
        return new GreekLetter("", translit, "", translit.matches(".*[aeiou].*")? "VOWEL":"CONSONANT" );
    }


    @Test
    public void validInitialTest() {
        String initialStr = "b";
        GreekString str = new GreekString();
        if (initialStr!=null) { for (String let : initialStr.split(" ")) { str.append(createGreekLetter(let)); } }
        assertTrue(initialStr+" is valid initial.", GreekSyllable.isValidInitial(str));
    }


    @Test
    public void ctorValidV1Test() {
        String initialStr = null, vowelStr = "ai/", finalStr = null;
        GreekString str = prepareTestString(initialStr, vowelStr, finalStr);
        try {
            s = new GreekSyllable(str);
            assertNull(s.getInitials());
            assertEquals(vowelStr, s.getVowel().getTranslit());
            assertNull(s.getFinals());
        } catch (SyllableException e) {
            assertTrue(e.toString(), false);
        }
    }

    @Test
    public void ctorValidCVTest() {
        String initialStr = "k", vowelStr = "a/", finalStr = null;
        GreekString str = prepareTestString(initialStr, vowelStr, finalStr);
        try {
            s = new GreekSyllable(str);
            assertEquals(initialStr.replaceAll(" ",""), s.getInitials().getTranslit());
            assertEquals(vowelStr.replaceAll(" ",""), s.getVowel().getTranslit());
            assertNull(s.getFinals());
        } catch (SyllableException e) {
            assertTrue(e.toString(), false);
        }
    }
    @Test
    public void ctorValidCVCTest() {
        String initialStr = "b", vowelStr = "ai/", finalStr = "n";
        GreekString str = prepareTestString(initialStr, vowelStr, finalStr);
        try {
            s = new GreekSyllable(str);
            assertEquals(initialStr.replaceAll(" ",""), s.getInitials().getTranslit());
            assertEquals(vowelStr.replaceAll(" ",""), s.getVowel().getTranslit());
            assertEquals(finalStr.replaceAll(" ",""), s.getFinals().getTranslit());
        } catch (SyllableException e) {
            assertTrue(e.toString(), false);
        }
    }
    @Test
    public void ctorValidCVCC1Test() {
        String initialStr = "b", vowelStr = "a/", finalStr = "r s p";
        GreekString str = prepareTestString(initialStr, vowelStr, finalStr);
        try {
            s = new GreekSyllable(str);
            assertEquals(initialStr.replaceAll(" ",""), s.getInitials().getTranslit());
            assertEquals(vowelStr.replaceAll(" ",""), s.getVowel().getTranslit());
            assertEquals(finalStr.replaceAll(" ",""), s.getFinals().getTranslit());
        } catch (SyllableException e) {
            assertTrue(e.toString(), false);
        }
    }
    @Test
    public void ctorValidCVCC2Test() {
        String initialStr = "b r", vowelStr = "a/", finalStr = "s k";
        GreekString str = prepareTestString(initialStr, vowelStr, finalStr);
        try {
            s = new GreekSyllable(str);
            assertEquals(initialStr.replaceAll(" ",""), s.getInitials().getTranslit());
            assertEquals(vowelStr.replaceAll(" ",""), s.getVowel().getTranslit());
            assertEquals(finalStr.replaceAll(" ",""), s.getFinals().getTranslit());
        } catch (SyllableException e) {
            assertTrue(e.toString(), false);
        }
    }

    @Test
    public void ctorInvalidInitialTest() {
        String initialStr = "r b", vowelStr = "a", finalStr = null;
        GreekString str = prepareTestString(initialStr, vowelStr, finalStr);
        try {
            s = new GreekSyllable(str);
            Assert.fail("should be invalid initial");
        } catch (SyllableException e) {
            System.out.println(e);
        }
    }
    @Test
     public void ctorInvalidVowelTest() {
        String initialStr = "k", vowelStr = "a e", finalStr = "t";
        GreekString str = prepareTestString(initialStr, vowelStr, finalStr);
        try {
            s = new GreekSyllable(str);
            Assert.fail("should be invalid vowel or invalid final");
        } catch (SyllableException e) {
            System.out.println(e);
        }
    }
    @Test
    public void ctorInvalidFinalTest() {
        String initialStr = "k", vowelStr = "`a", finalStr = "ta/";
        GreekString str = prepareTestString(initialStr, vowelStr, finalStr);
        try {
            s = new GreekSyllable(str);
            Assert.fail("should be invalid final");
        } catch (SyllableException e) {
            System.out.println(e);
        }
    }

}