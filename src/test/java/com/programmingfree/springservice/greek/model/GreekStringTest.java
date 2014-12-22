package com.programmingfree.springservice.greek.model;


import com.programmingfree.springservice.greek.GreekLetterRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import static org.junit.Assert.*;


public class GreekStringTest {

    GreekString str;


    @Before
    public void setup() {
        str = new GreekString();
        str.repository = Mockito.mock(GreekLetterRepository.class);
        Mockito.when(
                str.repository.findByTranslitStartsWith("a")
        ).thenReturn(
                Arrays.asList(
                        new GreekLetter("alpha", "a/", "ά", "VOWEL DIACRITIC"),
                        new GreekLetter("alpha", "a", "α", "VOWEL")
                )
        );
        Mockito.when(
                str.repository.findByPresentStartsWith("ά")
        ).thenReturn(
                Arrays.asList(
                        new GreekLetter("alpha", "a/", "ά", "VOWEL DIACRITIC")
                )
        );
        Mockito.when(
                str.repository.findByPresentStartsWith("α")
        ).thenReturn(
                Arrays.asList(
                        new GreekLetter("alpha", "a", "α", "VOWEL")
                )
        );
    }


    @Test
    public void setTranslitTest() {
        String translit = "kata/", present = "kαtά";

        str.setTranslit(translit);

        assertEquals(str.getTranslit(), translit);
        assertEquals(str.getPresent(), present);
    }

    @Test
    public void setPresentTest() {
        String translit = "?a?a/", present = "kαtά";

        str.setPresent(present);

        assertEquals(str.getTranslit(), translit);
        assertEquals(str.getPresent(), present);
    }
}