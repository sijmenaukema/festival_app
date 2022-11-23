package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.service.MusicSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicsets")
public class MusicSetRestController {

    @Autowired
    MusicSetService musicSetService;

    @GetMapping("/")
    private ResponseEntity<List<MusicSet>> getAllMusicSets() {
        List<MusicSet> musicSets  = musicSetService.getAllMusicSets();
        return ResponseEntity.ok(musicSets);
    }

    @GetMapping("/id")
    private ResponseEntity<MusicSet> getMusicSets(@RequestBody long id) {
        MusicSet musicSet = musicSetService.getMusicSet(id);
        if (musicSet == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(musicSet);
        }
    }

    @PostMapping("/")
    private ResponseEntity<String> postMusicSet(@RequestBody MusicSet newMusicSet) {
        musicSetService.postNewMusicSet(newMusicSet);
        return ResponseEntity.ok("music set has been added");
    }

    @DeleteMapping("/id")
    private ResponseEntity<String> deleteMusicSet(@RequestBody long id) {
        musicSetService.removeMusicSet(id);
        return ResponseEntity.ok("Music set has been removed");

    }
}
