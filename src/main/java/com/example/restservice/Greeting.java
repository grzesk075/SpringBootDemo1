package com.example.restservice;

public class Greeting {

    private final long id;

    //@JsonIgnore
    private final String content;

    public Greeting(long id) {
        this.id = id;
        this.content = this.toString();
    }

    public G1 getG() {
        return g;
    }

    private final G1 g = new G1();

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
