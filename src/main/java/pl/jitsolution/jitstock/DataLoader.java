package pl.jitsolution.jitstock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public void run(ApplicationArguments arg) throws Exception {
    offerRepository.save(new Offer("offer1", " description of offer1"));
    offerRepository.save(new Offer("offer2", " description of offer2"));
    offerRepository.save(new Offer("offer3", " description of offer3"));
    }
}
