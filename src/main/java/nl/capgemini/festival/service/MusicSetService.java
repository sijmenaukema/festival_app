package nl.capgemini.festival.service;

import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.repository.MusicSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MusicSetService {

    @Autowired
    MusicSetRepository musicSetRepository;

    public ResponseEntity getAllMusicSets(){
        List musicSets = new ArrayList();
        musicSetRepository.findAll().forEach(musicSets::add);
        return new ResponseEntity<>(musicSets, HttpStatus.OK);
    }
    public ResponseEntity getMusicSet(Long id) {
        Optional<MusicSet> optional = musicSetRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(
                    optional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(
                    null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity postNewMusicSet(MusicSet musicSet) {
        musicSetRepository.save(musicSet);
        return new ResponseEntity<>(
                HttpStatus.CREATED);
    }

    public ResponseEntity removeMusicSet(Long id) {
        musicSetRepository.deleteById(id);
        return new ResponseEntity<>(
                HttpStatus.OK);
    }
}
