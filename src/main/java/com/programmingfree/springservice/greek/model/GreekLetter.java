package com.programmingfree.springservice.greek.model;

import com.programmingfree.springservice.Letter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="letters", catalog="clovisdb") // rename catalog to greek , i.e. <LANG>
public class GreekLetter extends Letter {}

