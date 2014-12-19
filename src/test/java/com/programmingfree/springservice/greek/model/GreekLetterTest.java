package com.programmingfree.springservice.greek.model;

import com.programmingfree.springservice.greek.GreekLetterRepository;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;

public class GreekLetterTest extends TestCase {


    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }


    public void testInstance() throws Exception {
        System.out.println("");
        GreekString str = new GreekString("test");

    }
}