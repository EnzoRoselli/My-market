package tesis.offer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tesis.offer.models.Offer;
import tesis.offer.models.OfferTypes;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByProductID(Integer id);

    List<Offer> findByBranchID(Integer id);

    @Query(
            value = "SELECT * FROM offers o " +
                    "inner join branches br on br.id=o.branch_id " +
                    "inner join companies cc on cc.id=br.company_id " +
                    "WHERE cc.id = ?1",
            nativeQuery = true)
    List<Offer> findByCompanyID(Integer id);

    List<Offer> findByOfferTypeAndAvaliableTrue(OfferTypes type);

    List<Offer> findByFromDateGreaterThanEqualAndAvaliableTrue(LocalDateTime date);

    List<Offer> findAllByFromDateLessThanEqualAndToDateGreaterThanEqualAndAvaliableTrueAndOfferTypeIn(LocalDateTime dateStart,LocalDateTime dateEnd,List<OfferTypes> offers);

    List<Offer> findByFromDateLessThanEqualAndToDateGreaterThanEqualAndAvaliableTrue(LocalDateTime start, LocalDateTime end);

    @Query(
            value = "SELECT * FROM offers o " +
                    "WHERE ?1 between o.from_date and o.to_date",
            nativeQuery = true)
    List<Offer> findBySpecificDate(LocalDateTime specificDate);

    @Query(
            value = "select offers.* from offers \n" +
                    " inner join products pr on pr.id=offers.product_id\n" +
                    " inner join subscriptions sub on sub.product_id = pr.id\n" +
                    " inner join users us on us.id = sub.user_id\n" +
                    " where now()>=offers.from_date and now()<=offers.to_date\n" +
                    " and offers.available=true\n" +
                    " and us.id=?1\n" +
                    " group by(offers.id)",
            nativeQuery = true)
    List<Offer> findAvailableOfferForUser(Long idUser);

    @Query(
            value = "SELECT * FROM offers o " +
                    "inner join branches br on br.id=o.branch_id " +
                    "WHERE br.city = ?1",
            nativeQuery = true)
    List<Offer> findByCity(String city);


}
