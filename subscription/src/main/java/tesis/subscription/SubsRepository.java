package tesis.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsRepository extends JpaRepository<Subscription, Long> {
    @Query(
            value = "select us.email from offers \n" +
                    " inner join products pr on pr.id=offers.product_id\n" +
                    " inner join subscriptions sub on sub.product_id = pr.id\n" +
                    " inner join users us on us.id = sub.user_id\n" +
                    " where now()>=offers.from_date and now()<=offers.to_date\n" +
                    " group by(us.email)",
            nativeQuery = true)
    List<String> takeMailInformation();
}

/* ;

 */
