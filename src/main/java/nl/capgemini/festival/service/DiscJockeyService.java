package nl.capgemini.festival.service;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.repository.DiscJockeyRepository;
import nl.capgemini.festival.repository.MusicSetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class DiscJockeyService {

    public DiscJockeyService(DiscJockeyRepository discJockeyRepository, MusicSetRepository musicSetRepository) {
        this.discJockeyRepository = discJockeyRepository;
        this.musicSetRepository = musicSetRepository;
    }

    private final DiscJockeyRepository discJockeyRepository;

    private final MusicSetRepository musicSetRepository;

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

    public DiscJockey getDiscJockey(String name) {
        Optional<DiscJockey> optional = discJockeyRepository.findByName(name);
        return optional.orElse(null);
    }

    public DiscJockey postNewDiscJockey(DiscJockey discJockey) {
        discJockeyRepository.save(discJockey);
        return discJockey;
    }

    public DiscJockey removeDiscJockey(Long id) {
        DiscJockey discJockeyToDelete = getDiscJockey(id);
        discJockeyRepository.delete(discJockeyToDelete);
        return discJockeyToDelete;
    }
}
