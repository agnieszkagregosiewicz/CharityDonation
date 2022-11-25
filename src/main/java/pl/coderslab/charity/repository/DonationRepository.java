package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DonationRepository extends JpaRepository<Donation, Long> {
    Optional<Donation> findById(Long id);
    void deleteDonationById(Long id);
    List<Donation> findAll();

    @Query("select count(id) FROM Donation")
    Long countAll();
//    int countAll();
    @Query("select sum(quantity) FROM Donation")
    Long countQuantity();

}
