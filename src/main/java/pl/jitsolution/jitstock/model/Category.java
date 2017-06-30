package pl.jitsolution.jitstock.model;

/**
 * Created by JIT on 30.06.2017.
 */
public enum Category {

    FRUITS("Fruits"),
    VEGETABLES("Vegetables");

    String name;

    Category(String name) {
        this.name = name;
    }

    public String getLabel() {
        return name;
    }
}
