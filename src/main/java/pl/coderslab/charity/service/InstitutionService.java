package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public void add(Institution institution) {
        institutionRepository.save(institution);
    }

    public List<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }

    public Optional<Institution> get(Long id) {
        return institutionRepository.findById(id);
    }

    public void delete(Long id) {
        institutionRepository.deleteInstitutionById(id);
    }
}
