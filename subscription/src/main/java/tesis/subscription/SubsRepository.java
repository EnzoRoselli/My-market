package tesis.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsRepository extends JpaRepository<Subscription,Long> {
}
