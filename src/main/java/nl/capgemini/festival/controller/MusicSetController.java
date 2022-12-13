package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.service.DiscJockeyService;
import nl.capgemini.festival.service.MusicSetService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/musicset")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class MusicSetController {

    final MusicSetService musicSetService;

    final DiscJockeyService discJockeyService;

    public MusicSetController(MusicSetService musicSetService, DiscJockeyService discJockeyService) {
        this.musicSetService = musicSetService;
        this.discJockeyService = discJockeyService;
    }

    @GetMapping("/")
    protected ResponseEntity<List<MusicSet>> getAllMusicSets() {
        List<MusicSet> musicSets = musicSetService.getAllMusicSets();
        return ResponseEntity.ok(musicSets);
    }

    @GetMapping("/{id}")
    protected ResponseEntity<MusicSet> getMusicSet(@PathVariable String id) {
        MusicSet musicSet = musicSetService.getMusicSet(Long.parseLong(id));
        if (musicSet == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(musicSet);
        }
    }

    @PostMapping("/")
    protected ResponseEntity<String> postMusicSet(@RequestBody @Valid Map<String, Object> body) {
        DiscJockey discJockey = discJockeyService.getDiscJockey(Long.parseLong(body.get("id").toString()));
        MusicSet musicSet = musicSetService.postNewMusicSet(new MusicSet(body.get("title").toString(), discJockey, body.get("genre").toString()));
        if (musicSet == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(String.format("%s Music set has been added", musicSet));
        }
    }

    @GetMapping("/discjockeyid/{id}")
    protected ResponseEntity<MusicSet[]> getMusicSetsByDiscJockeyId(@PathVariable @Valid String id) {
        MusicSet[] musicSet = musicSetService.getMusicSetByDiscJockeyId(Long.parseLong(id));
        return ResponseEntity.ok().body(musicSet);
    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<String> deleteMusicSet(@PathVariable @Valid String id) {
        MusicSet musicSet = musicSetService.removeMusicSet(Long.parseLong(id));
        return ResponseEntity.ok(String.format("%s Music set has been removed", musicSet.toString()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}