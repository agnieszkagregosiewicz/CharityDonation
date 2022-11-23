package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.Institution;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface InstitutionRepository extends JpaRepository <Institution, Long> {
    Optional<Institution> findById (Long id);
    void deleteInstitutionById(Long id);
    List<Institution> findAll();

}
