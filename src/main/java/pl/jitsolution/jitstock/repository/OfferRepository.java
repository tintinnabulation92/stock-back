package pl.jitsolution.jitstock.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jitsolution.jitstock.model.entity.Offer;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public interface OfferRepository extends CrudRepository<Offer, Long> {
}
