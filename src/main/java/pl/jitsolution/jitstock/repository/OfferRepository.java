package pl.jitsolution.jitstock.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jitsolution.jitstock.entity.Offer;

public interface OfferRepository extends CrudRepository<Offer, Long> {
}
