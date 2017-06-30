package pl.jitsolution.jitstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jitsolution.jitstock.model.entity.Offer;
import pl.jitsolution.jitstock.repository.OfferRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @RequestMapping("/api/offers")
    public List<Offer> getAllOffers() {
        List<Offer> offerList = new ArrayList<>();
        offerRepository.findAll().forEach(offerList::add);
        return offerList;
    }
}
