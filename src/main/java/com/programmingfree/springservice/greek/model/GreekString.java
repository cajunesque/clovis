package com.programmingfree.springservice.greek.model;


import com.programmingfree.springservice.Letter;
import com.programmingfree.springservice.Letters;
import com.programmingfree.springservice.greek.GreekLetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

public class GreekString extends Letters {

    @Autowired
    GreekLetterRepository repository;

    private Sort sortByTranslitLengthDesc() {
        return new Sort(Sort.Direction.DESC, "length(translit)");
    }

    public GreekString(String str) {
        // http://www.cs.cmu.edu/~pattis/15-1XX/15-200/lectures/llrecursion/
        // http://www.aclweb.org/anthology/I05-1039
        // turn the str into a list of GreekLetters
        // using GreekLetterRepository
        String presentWord = "";
        // loop through str
        List<GreekLetter> lets = repository.findByTypesLike(str.substring(0), sortByTranslitLengthDesc());
        //
/*
        // for loop goes through all the letters in translitWord
        for (var i=0; i< translitWord.length; i++) {
            var maxtranslit = translitTable[0].maxtranslit;
            alert(translitWord + i);
            alert(maxtranslit);
            var presentLet = '';
            // loop gets all partial strings starting with current letter, not longer than the maxtranslit length
            for (var l = Math.min(maxtranslit,translitWord.length-i);  l > 0; l--) {
                alert("in the l loop");
                presentLet = transliterateLetter(translitWord.substr(i, l), translitTable);

                if (presentLet != '') { break; }

            }

            // build up word with matched letter or original letter
            if (presentLet == '') { presentLet = translitWord.charAt(i); }
            presentWord += presentLet;
        }

        return presentWord;
*/
    }

    GreekLetter transliterateLetter(String translitLetter) {
        /*
        var presentLetter = '';
        for (var j=0; j < languageTable1.length; j++) {
            var translitObject = translitTable[j];
            if (translitLetter == translitObject.translit) {
                presentLetter = translitObject.present;
                break;
            }
        }
        */
        return null; // presentLetter;
    }
}
