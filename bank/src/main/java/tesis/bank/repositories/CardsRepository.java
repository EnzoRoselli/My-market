package tesis.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.bank.models.Card;

@Repository
public interface CardsRepository extends JpaRepository<Card, Long> {
}
