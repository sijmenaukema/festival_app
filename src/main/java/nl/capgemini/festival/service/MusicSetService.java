package nl.capgemini.festival.service;

import nl.capgemini.festival.model.DiscJockey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
    public MusicSet[] getMusicSetByDiscJockeyId(long id){
        Optional<MusicSet[]> optional = musicSetRepository.findByDiscJockeyId(id);
        MusicSet[] musicSets;
        if (optional.isPresent()){
            musicSets = optional.get();
            return musicSets;
        }else{
            return null;
        }
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
