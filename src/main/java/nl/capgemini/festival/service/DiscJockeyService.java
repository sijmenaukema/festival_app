package nl.capgemini.festival.service;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.repository.DiscJockeyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DiscJockeyService {

    public DiscJockeyService(DiscJockeyRepository discJockeyRepository) {
        this.discJockeyRepository = discJockeyRepository;
    }

    private final DiscJockeyRepository discJockeyRepository;

    public ArrayList<DiscJockey>  getAllDiscJockeys(){
        ArrayList<DiscJockey> discJockeys = new ArrayList<>();
        discJockeyRepository.findAll().forEach(discJockeys::add);
        return discJockeys;
    }

    public ArrayList<DiscJockey> getAllDiscJockeysWithNameLength(int length){
        ArrayList<DiscJockey> discJockeys = new ArrayList<>();
        discJockeyRepository.findAllByNameLength(length).forEach(discJockeys::add);
        return discJockeys;
    }

    public DiscJockey getDiscJockey(Long id) {
        Optional<DiscJockey> optional = discJockeyRepository.findById(id);
        return optional.orElse(null);
    }

    public void postNewDiscJockey(DiscJockey discJockey) {
        discJockeyRepository.save(discJockey);
    }

    public void removeDiscJockey(Long id) {
        discJockeyRepository.deleteById(id);
    }
}
