package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {
    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public void add(Donation donation) {
        donationRepository.save(donation);
    }

    public List<Donation> getDonations() {
        return donationRepository.findAll();
    }

    public Optional<Donation> get(Long id) {
        return donationRepository.findById(id);
    }

    public void delete(Long id) {
        donationRepository.deleteDonationById(id);
    }

    public Long countAll() {
        return donationRepository.countAll();
    }

    public Long countBags() {
        return donationRepository.countQuantity();
    }


}
