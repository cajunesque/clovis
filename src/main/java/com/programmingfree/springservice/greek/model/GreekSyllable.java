package com.programmingfree.springservice.greek.model;

/**
 * Created by markcbordelon on 12/18/14.
 */

import com.programmingfree.springservice.Syllable;

import java.util.ArrayList;
import java.util.List;


public class GreekSyllable extends Syllable {

    protected static List<GreekString> validInitials;

    public GreekSyllable() {
        validInitials = new ArrayList<GreekString>();
        for (String let : "b bl br c etc".split(" ")) { validInitials.add(new GreekString(let)); }
    }
    public GreekSyllable(String translit) {
        this();
        GreekString grkstr = new GreekString(translit);
        int finalpos;
        if (grkstr.letterAt(0).isVowel() || grkstr.letterAt(0).isDiphthong()) {
            this.setInitials(null);
            this.setVowel(grkstr.letterAt(0));
            finalpos = 1;
        } else {
            int i;
            for (i = 0; i < grkstr.length(); i++) {
                if (grkstr.letterAt(i).isVowel() || grkstr.letterAt(i).isDiphthong()) {
                    break;
                }
                this.getInitials().append(grkstr.letterAt(i));
            }
            this.setVowel(grkstr.letterAt(i));
            finalpos = i + 1;
        }
        this.setFinals(grkstr.substring(finalpos));
    }
}

