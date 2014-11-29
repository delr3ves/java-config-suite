package io.delr3ves.config.parser.dummy;

import java.util.List;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class DummyConfig {

    private String anyString;
    private Boolean anyBoolean;
    private Integer anyNumber;
    private List<String> anyList;

    public String getAnyString() {
        return anyString;
    }

    public void setAnyString(String anyString) {
        this.anyString = anyString;
    }

    public Boolean getAnyBoolean() {
        return anyBoolean;
    }

    public void setAnyBoolean(Boolean anyBoolean) {
        this.anyBoolean = anyBoolean;
    }

    public Integer getAnyNumber() {
        return anyNumber;
    }

    public void setAnyNumber(Integer anyNumber) {
        this.anyNumber = anyNumber;
    }

    public List<String> getAnyList() {
        return anyList;
    }

    public void setAnyList(List<String> anyList) {
        this.anyList = anyList;
    }
}
