package com.jsdroid.bean;

public class SourceCode {
    public SourceCode(String sourceCode, String url) {
        this.sourceCode = sourceCode;
        this.url = url;
    }

    private String sourceCode;
    private String url;

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
