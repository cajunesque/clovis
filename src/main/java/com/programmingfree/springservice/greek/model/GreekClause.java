package com.programmingfree.springservice.greek.model;

import com.programmingfree.springservice.Clause;


import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by markcbordelon on 12/28/14.
 */
//@Entity
//@Table(name="letters", catalog="greek")
public class GreekClause extends Clause {
    GreekWord finiteVerb;
    // tree of all word relationships instead of the above, which would be the root node
}