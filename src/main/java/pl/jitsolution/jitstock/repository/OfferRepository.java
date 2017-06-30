package pl.jitsolution.jitstock.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jitsolution.jitstock.model.entity.Offer;

public interface OfferRepository extends CrudRepository<Offer, Long> {
}
