package com.ilyamur.espresso.data.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class EchoMessage implements Serializable {

    @Id
    private String id;

    private String text;

    public EchoMessage() {
    }

    public EchoMessage(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
