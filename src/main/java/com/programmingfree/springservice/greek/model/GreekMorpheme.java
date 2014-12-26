package com.programmingfree.springservice.greek.model;

import com.programmingfree.springservice.Lexeme;
import com.programmingfree.springservice.Morpheme;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="morphology", catalog="clovisdb") // rename catalog to greek , i.e. <LANG>
public class GreekMorpheme extends Morpheme {

}

