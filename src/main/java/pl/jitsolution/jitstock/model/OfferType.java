package pl.jitsolution.jitstock.model;

/**
 * Created by JIT on 30.06.2017.
 */
public enum OfferType {
    BUY("Buy"),
    SELL("Sell"),
    EXCHANGE("Exchange");

    private String name;

    OfferType(String name) {
        this.name = name;
    }

    public String getLabel() {
        return name;
    }
}
