package com.programmingfree.springservice.greek.model;

import com.programmingfree.springservice.Phrase;
import com.programmingfree.springservice.Word;

import java.util.List;

/**
 * Created by markcbordelon on 12/21/14.
 */
public class GreekPhrase extends Phrase {
    List<GreekWord> words;
}