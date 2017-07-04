package pl.jitsolution.jitstock.model.entity;

import pl.jitsolution.jitstock.model.Category;
import pl.jitsolution.jitstock.model.OfferType;
import pl.jitsolution.jitstock.model.Quality;
import pl.jitsolution.jitstock.model.Unit;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Offer implements Serializable{

    private static final long serialVersionUID = 4884352045932862292L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Quality quality;

    @Column(nullable= false, scale=6)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private LocalDateTime publishDate;

    public Offer() {}

    public Offer(String name, OfferType offerType, Category category, Quality quality, BigDecimal price, Unit unit) {
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

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    @PrePersist
    protected void onCreate() {
        if (publishDate == null) {
            publishDate = LocalDateTime.now();
        }
    }

}

