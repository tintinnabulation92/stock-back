package pl.jitsolution.jitstock.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.jitsolution.jitstock.model.Category;
import pl.jitsolution.jitstock.model.OfferType;
import pl.jitsolution.jitstock.model.Quality;
import pl.jitsolution.jitstock.model.Unit;
import pl.jitsolution.jitstock.model.entity.Offer;
import pl.jitsolution.jitstock.repository.OfferRepository;
import java.math.BigDecimal;

@Component
public class OfferProvider implements ApplicationRunner {


    @Autowired
    private OfferRepository offerRepository;
    @Override
    public void run(ApplicationArguments arg) throws Exception {

        Offer o1 = new Offer();
        o1.setName("ABC1");
        o1.setOfferType(OfferType.SELL);
        o1.setCategory(Category.FRUITS);
        o1.setPrice(new BigDecimal(12));
        o1.setQuality(Quality.I);
        o1.setUnit(Unit.KG);

        Offer o2 = new Offer();
        o2.setName("ABC2");
        o2.setOfferType(OfferType.BUY);
        o2.setCategory(Category.VEGETABLES);
        o2.setPrice(new BigDecimal(4.5));
        o2.setQuality(Quality.II);
        o2.setUnit(Unit.T);

        Offer o3 = new Offer();
        o3.setName("ABC2");
        o3.setOfferType(OfferType.BUY);
        o3.setCategory(Category.VEGETABLES);
        o3.setPrice(new BigDecimal(9.1));
        o3.setQuality(Quality.II);
        o3.setUnit(Unit.T);

        offerRepository.save(o1);
        offerRepository.save(o2);
        offerRepository.save(o3);
    }
}
