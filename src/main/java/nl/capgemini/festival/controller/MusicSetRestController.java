package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.service.MusicSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MusicSetRestController {

    @Autowired
    MusicSetService musicSetService;

    @GetMapping("/musicsets")
    private ResponseEntity getAllMusicSets() {
        List<MusicSet> musicSets  = musicSetService.getAllMusicSets();
        return new ResponseEntity(musicSets, HttpStatus.OK);
    }

    @GetMapping("/musicsets/id")
    private ResponseEntity getMusicSets(@RequestBody long id) {
        MusicSet musicSet = musicSetService.getMusicSet(id);
        if (musicSet == null) {
            return new ResponseEntity(
                    null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(
                    musicSet, HttpStatus.OK);
        }
    }

    @PostMapping("/musicsets")
    private ResponseEntity postMusicSet(@RequestBody MusicSet newMusicSet) {
        musicSetService.postNewMusicSet(newMusicSet);
        return new ResponseEntity(
                null, HttpStatus.OK);
    }

    @DeleteMapping("/musicsets/id")
    private ResponseEntity deleteMusicSet(@RequestBody long id) {
        musicSetService.removeMusicSet(id);
        return new ResponseEntity(
                null, HttpStatus.OK);
    }
}
