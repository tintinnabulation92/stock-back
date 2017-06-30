package pl.jitsolution.jitstock.model;

/**
 * Created by JIT on 30.06.2017.
 */
public enum Unit {

    KG("kg"),
    T("t");

    private String name;

    Unit(String name) {
        this.name = name;
    }

    public String getLabel() {
        return name;
    }
}
