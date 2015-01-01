package com.programmingfree.springservice.greek.model;


import com.programmingfree.springservice.Letter;
import com.programmingfree.springservice.Letters;
import com.programmingfree.springservice.greek.GreekLetterRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.data.domain.Sort;

public class GreekString extends Letters implements InitializingBean {

    @Autowired
    public GreekLetterRepository repository; // this is null until the bean is completely instantiated
/*
    private Sort sortByTranslitLengthDesc() {
        return new Sort(Sort.Direction.DESC, "length(translit)");
    }
*/



    public GreekString() {
        super();
    }

    public GreekString(Letters str) {
        super();
        this.setLetters(str.getLetters());
    }


    @Override
    public void afterPropertiesSet() {
        Assert.notNull(repository);
    }

    @PostConstruct
    private void checkRepo() {
        System.out.println("repo check");
        Assert.notNull(repository);
    }


    public void setTranslit(String str) { // litterates from str as translit
        List<Letter> lets = new ArrayList<Letter>();
        for (int i = 0; i < str.length(); i++) {
            String partialWord = str.substring(i);
            String partialWord0 = partialWord.substring(0,1);
            List<GreekLetter> potentialMatches = repository.findByTranslitStartsWith(partialWord0);
            GreekLetter match = null;
            for (GreekLetter potentialMatch : potentialMatches) {
                if (partialWord.startsWith(potentialMatch.getTranslit())) {
                    match = potentialMatch;
                    break;
                }
            }
            if (match==null) {
                Letter let = new GreekLetter("", partialWord0, partialWord0, ""); // present = translit here
                lets.add(let);
            } else {
                lets.add(match);
                i += (match.getTranslit().length() - 1);
            }
        }
        this.setLetters(lets);
    }

    public void setPresent(String str) { // litterates from str as present
        List<Letter> lets = new ArrayList<Letter>();
        for (int i = 0; i < str.length(); i++) {
            String partialWord = str.substring(i);
            String partialWord0 = partialWord.substring(0,1);
            List<GreekLetter> potentialMatches = repository.findByPresentStartsWith(partialWord0);
            GreekLetter match = null;
            for (GreekLetter potentialMatch : potentialMatches) {
                if (partialWord.startsWith(potentialMatch.getPresent())) {
                    match = potentialMatch;
                    break;
                }
            }
            if (match==null) {
                Letter let = new GreekLetter("", "?", partialWord0, ""); // no gain in translit = present, so ? instead
                lets.add(let);
            } else {
                lets.add(match);
                i += (match.getPresent().length() - 1);
            }
        }
        this.setLetters(lets);
    }


    // syllabify
    /*
    http://www.cs.cmu.edu/~pattis/15-1XX/15-200/lectures/llrecursion/
    http://www.aclweb.org/anthology/I05-1039
    */
}
