package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.service.MusicSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/musicsets")
public class MusicSetRestController {

    final
    MusicSetService musicSetService;

    public MusicSetRestController(MusicSetService musicSetService) {
        this.musicSetService = musicSetService;
    }

    @GetMapping("/")
    protected ResponseEntity<List<MusicSet>> getAllMusicSets() {
        List<MusicSet> musicSets  = musicSetService.getAllMusicSets();
        return ResponseEntity.ok(musicSets);
    }

    @GetMapping("/id")
    protected ResponseEntity<MusicSet> getMusicSet(@RequestBody long id) {
        MusicSet musicSet = musicSetService.getMusicSet(id);
        if (musicSet == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(musicSet);
        }
    }

    @PostMapping("/")
    protected ResponseEntity<String> postMusicSet(@RequestBody MusicSet newMusicSet) {
        MusicSet musicSet = musicSetService.postNewMusicSet(newMusicSet);
        return ResponseEntity.ok(String.format("%s Music set has been added", musicSet.toString()));
    }

    @DeleteMapping("/id")
    protected ResponseEntity<String> deleteMusicSet(@RequestBody long id) {
        MusicSet musicSet = musicSetService.removeMusicSet(id);
        return ResponseEntity.ok(String.format("%s Music set has been removed", musicSet.toString()));

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String, String>>  handleValidationExceptions( MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
