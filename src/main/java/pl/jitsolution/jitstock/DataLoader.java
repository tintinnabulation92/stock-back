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
    offerRepository.save(new Offer("Banany", "3.50zł/kg"));
    offerRepository.save(new Offer("Marchew", "2.20zł/kg"));
    offerRepository.save(new Offer("Pomarańcze", "5.99zł/kg"));
    }
}
