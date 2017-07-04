package pl.jitsolution.jitstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jitsolution.jitstock.model.entity.Offer;
import pl.jitsolution.jitstock.repository.OfferRepository;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @RequestMapping("/api/offers")
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offerList = new ArrayList<>();
        offerRepository.findAll().forEach(offerList::add);
        if (offerList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(offerList, HttpStatus.OK);
    }
}
