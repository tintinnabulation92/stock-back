package pl.jitsolution.jitstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.jitsolution.jitstock.model.entity.Offer;
import pl.jitsolution.jitstock.repository.OfferRepository;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OfferController {

    public static final String GET_OFFER = "/offer/{offerId}";
    public static final String GET_ALL_OFFERS = "/offers";
    public static final String CREATE_OFFER = "/offer/create";

    @Autowired
    private OfferRepository offerRepository;

    @RequestMapping(value= GET_ALL_OFFERS)
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offerList = new ArrayList<>();
        offerRepository.findAll().forEach(offerList::add);
        if (offerList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(offerList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = CREATE_OFFER)
    public ResponseEntity<?> createOffer(@RequestBody Offer offer, UriComponentsBuilder ucBuilder) {
        if(offer.getId()!=null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        offerRepository.save(offer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api"+GET_OFFER).buildAndExpand(offer.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET, value = GET_OFFER, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOffer(@PathVariable("offerId") long offerId) {
        Offer offer = offerRepository.findOne(offerId);
        if(offer==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }




}
