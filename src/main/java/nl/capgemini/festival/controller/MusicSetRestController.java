package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.service.DiscJockeyService;
import nl.capgemini.festival.service.MusicSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class MusicSetRestController {

    @Autowired
    MusicSetService musicSetService;

    @GetMapping("/musicsets")
    private ResponseEntity getAllMusicSet() {
        return musicSetService.getAllMusicSets();
    }

    @GetMapping("/musicsets/{id}")
    private ResponseEntity getDiscJockeys(@PathVariable("id") long id) {
        return musicSetService.getMusicSet(id);
    }

    @PostMapping("/musicsets")
    private ResponseEntity PostMusicSet(@RequestBody MusicSet newMusicSet) {
        return musicSetService.postNewMusicSet(newMusicSet);
    }

    @DeleteMapping("/musicsets/{id}")
    private ResponseEntity deleteMusicSet(@PathVariable("id")long id) {
        return musicSetService.removeMusicSet(id);
    }
}
