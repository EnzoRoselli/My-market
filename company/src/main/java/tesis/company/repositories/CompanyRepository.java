package tesis.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tesis.company.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
