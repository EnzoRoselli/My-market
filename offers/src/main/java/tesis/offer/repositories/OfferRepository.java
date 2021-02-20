package tesis.offer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tesis.offer.models.Offer;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByProductID(Integer id);

    @Query(
            value = "SELECT * FROM offers o " +
                    "inner join companies cc on cc.id=o.company_id " +
                    "inner join branches br on br.company_id=cc.id " +
                    "WHERE cc.id = ?1",
            nativeQuery = true)
    List<Offer> findByCompanyID(Integer id);

    List<Offer> findByClasificationAndAvaliableTrue(String clasification);

    List<Offer> findByFromDateGreaterThanEqualAndAvaliableTrue(LocalDateTime date);

    List<Offer> findByFromDateLessThanEqualAndToDateGreaterThanEqualAndAvaliableTrue(LocalDateTime start, LocalDateTime end);

    @Query(
            value = "SELECT * FROM offers o " +
                    "WHERE ?1 between o.from_date and o.to_date",
            nativeQuery = true)
    List<Offer> findBySpecificDate(LocalDateTime specificDate);

    @Query(
            value = "SELECT * FROM offers o " +
                    "inner join companies cc on cc.id=o.company_id " +
                    "inner join branches br on br.company_id=cc.id " +
                    "WHERE br.city = ?1",
            nativeQuery = true)
    List<Offer> findByCity(String city);
}
