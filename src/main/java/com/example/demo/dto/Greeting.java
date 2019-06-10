package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Greeting {
    private long id;
    private String greet;

    public Greeting() {}

    public Greeting(long id, String greet) {
        this.id = id;
        this.greet = greet;
    }

}
