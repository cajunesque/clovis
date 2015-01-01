package com.programmingfree.springservice;

import java.util.List;

/**
 * Created by markcbordelon on 12/21/14.
 */
public class Context {
    public int textid;
    public int clauseid;

    @Override
    public String toString() {
        return "Context{" +
                "textid=" + textid +
                ", clauseid=" + clauseid +
                ", wordid=" + wordid +
                '}';
    }

    public int wordid;
}
