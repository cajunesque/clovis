package com.cloviscorp.grammar.greek.model;

import com.cloviscorp.grammar.Letter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="letters", catalog="greek")
public class GreekLetter extends Letter {
    public GreekLetter() {}
    public GreekLetter(String name, String translit, String present, String types) { super(name,translit,present,types); }

}


