package nl.capgemini.festival.service;

import lombok.RequiredArgsConstructor;
import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.repository.MusicSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MusicSetService {

    public MusicSetService(MusicSetRepository musicSetRepository) {
        this.musicSetRepository = musicSetRepository;
    }

    private final MusicSetRepository musicSetRepository;

    public ArrayList<MusicSet> getAllMusicSets(){
        ArrayList<MusicSet> musicSets = new ArrayList<>();
        musicSetRepository.findAll().forEach(musicSets::add);
        return musicSets;
    }
    public MusicSet getMusicSet(Long id) {
        Optional<MusicSet> optional = musicSetRepository.findById(id);
        return optional.orElse(null);
    }


    public void postNewMusicSet(MusicSet musicSet) {
        musicSetRepository.save(musicSet);
    }

    public void removeMusicSet(Long id) {
        musicSetRepository.deleteById(id);
    }
}
