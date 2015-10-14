package com.github.vendigo.charon;

import org.springframework.stereotype.Component;

@Component("sout")
public class Sout {
    public void print(Object body) {
        System.out.println(body);
    }
}
