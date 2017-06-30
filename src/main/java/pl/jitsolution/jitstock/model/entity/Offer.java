package pl.jitsolution.jitstock.model.entity;

import pl.jitsolution.jitstock.model.Category;
import pl.jitsolution.jitstock.model.OfferType;
import pl.jitsolution.jitstock.model.Quality;
import pl.jitsolution.jitstock.model.Unit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Offer implements Serializable{

    private static final long serialVersionUID = 6953249277206142508L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private OfferType offerType;

    private Category category;

    private Quality quality;

    private float price;

    private Unit unit;

    private LocalDateTime publishDate;

    public Offer() {}

    public Offer(String name, OfferType offerType, Category category, Quality quality, float price, Unit unit) {
        this.name = name;
        this.offerType = offerType;
        this.category = category;
        this.quality = quality;
        this.price = price;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
}

