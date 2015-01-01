package com.programmingfree.springservice.greek.model;

/**
 * Created by markcbordelon on 12/18/14.
 */

import com.programmingfree.springservice.Letter;
import com.programmingfree.springservice.Letters;
import com.programmingfree.springservice.Syllable;
import com.programmingfree.springservice.SyllableException;

import java.util.ArrayList;
import java.util.List;


public class GreekSyllable extends Syllable {

    public static List<Letters> validInitials;

    public GreekSyllable() { /* loadValidSyllables(); */ }

    public GreekSyllable(GreekString grkstr) throws SyllableException {
        int finalpos;
        if (grkstr.letterAt(0).isVowel() || grkstr.letterAt(0).isDiphthong()) {
            this.setInitials(null);
            this.setVowel(grkstr.letterAt(0));
            finalpos = 1;
        } else {
            int i;
            this.setInitials(new Letters());
            for (i = 0; i < grkstr.length(); i++) {
                if (grkstr.letterAt(i).isVowel() || grkstr.letterAt(i).isDiphthong()) {
                    break;
                }
                this.getInitials().append(grkstr.letterAt(i));
            }
            if (!isValidInitial(this.getInitials())) {
                throw new SyllableException("Invalid initial");
            }
            this.setVowel(grkstr.letterAt(i));
            finalpos = i + 1;
        }

        // check vowel
        if (!this.getVowel().isVowel() && !this.getVowel().isDiphthong()) {
            throw new SyllableException("invalid vowel");
        }

        this.setFinals(grkstr.substring(finalpos));

        // quick test for finals for valid syllable
        // 1 cannot contain vowel
        for (int i = 0; i < this.getFinals().length(); i++) {
            final Letter letter = this.getFinals().letterAt(i);
            if (letter.isVowel() || letter.isDiphthong()) { throw new SyllableException("invalid final -- contains vowel"); }
        }
        // 2 cannot contain a valid initial unless that is all it contains or length is 1
        if (this.getFinals().length() > 1) {
            int initialInFinalIx = validInitials.indexOf(this.getFinals());
            if (initialInFinalIx >= 0) {
                Letters initialInFinal = validInitials.get(validInitials.indexOf(this.getFinals()));
                if (initialInFinal.length() > this.getFinals().length()) { throw new SyllableException("invalid final -- contains initial"); }
            }
        }
        if (this.getFinals().length()==0) this.setFinals(null);
    }

    protected static boolean isValidInitial(Letters str) {
        if (validInitials==null) loadValidSyllables();
        return validInitials.contains(str);
    }

    protected static void loadValidSyllables() {
        // select distinct left(lex,patindex('%[aeiou]%',lex)-1),patindex('%[aeiou]%',lex)-1 from greek.dictionary order by patindex('%[aeiou]%',lex)-1 desc,left(lex,patindex('%[aeiou]%',lex)-1)
        validInitials = new ArrayList<Letters>();
        for (String let : "khth phth khl khr phl phr skh skl sph spl sth str thn thr bl br dr gl gn gr kh kl kn kr ks kt mn ph pl pn pr ps pt rh sk sm sp st th tm tr ts b d g k l m n p s t ` h".split(" ")) {
            GreekString str = new GreekString();
            str.setTranslit(let);
            validInitials.add(str);
        }
    }

}

