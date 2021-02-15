package tesis.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.company.models.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
}
