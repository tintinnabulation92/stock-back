package pl.jitsolution.jitstock.model;

/**
 * Created by JIT on 30.06.2017.
 */
public enum Quality {

    I("I"),
    II("II"),
    III("III");

    private String name;

    Quality(String name) {
        this.name = name;
    }

    public String getLabel() {
        return name;
    }
}
