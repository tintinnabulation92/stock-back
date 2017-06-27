package pl.jitsolution.jitstock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @RequestMapping("/api/offers")
    public List<Offer> getAllOffers() {
        int integer =5;
        List<Offer> offerList = new ArrayList<>();
        offerRepository.findAll().forEach(offerList::add);
        return offerList;
    }
}
