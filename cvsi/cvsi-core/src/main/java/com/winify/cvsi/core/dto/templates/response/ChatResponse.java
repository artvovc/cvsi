package com.winify.cvsi.core.dto.templates.response;

public class ChatResponse {
    private String content;

    public ChatResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
