package com.winify.cvsi.core.dto.templates.request;


public class ChatRequest {
    private String name;

    public ChatRequest(){}

    public ChatRequest(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
