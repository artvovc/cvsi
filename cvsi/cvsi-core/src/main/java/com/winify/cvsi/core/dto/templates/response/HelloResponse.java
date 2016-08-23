package com.winify.cvsi.core.dto.templates.response;

public class HelloResponse{
    private String content;

    public HelloResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
