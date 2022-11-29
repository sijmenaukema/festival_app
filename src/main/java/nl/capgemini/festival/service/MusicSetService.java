package nl.capgemini.festival.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.repository.MusicSetRepository;
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


    public MusicSet postNewMusicSet(MusicSet musicSet) {
        musicSetRepository.save(musicSet);
        return musicSet;
    }

    public MusicSet removeMusicSet(Long id) {
        MusicSet musicSetToDelete = getMusicSet(id);
        musicSetRepository.delete(musicSetToDelete);
        return musicSetToDelete;
    }
}
