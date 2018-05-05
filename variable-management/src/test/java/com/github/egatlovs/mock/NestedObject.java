package com.github.egatlovs.mock;

public class NestedObject {

    private String nestedString = "nestedString";

    public NestedObject(){

    }

    public NestedObject(String nestedString) {
        this.nestedString = nestedString;
    }

    public String getNestedString() {
        return nestedString;
    }

    public void setNestedString(String nestedString) {
        this.nestedString = nestedString;
    }
}
