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
public class MusicSetController {

    final MusicSetService musicSetService;

    final DiscJockeyService discJockeyService;

    public MusicSetController(MusicSetService musicSetService, DiscJockeyService discJockeyService) {
        this.musicSetService = musicSetService;
        this.discJockeyService = discJockeyService;
    }

    @GetMapping("/")
    protected ResponseEntity<List<MusicSet>> getAllMusicSets() {
        List<MusicSet> musicSets  = musicSetService.getAllMusicSets();
        return ResponseEntity.ok(musicSets);
    }

    @PostMapping("/id")
    protected ResponseEntity<MusicSet> getMusicSet(@RequestBody Map<String,Object> body) {
        long id = Long.parseLong(body.get("id").toString());
        MusicSet musicSet = musicSetService.getMusicSet(id);
        if (musicSet == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(musicSet);
        }
    }

    @PostMapping("/")
    protected ResponseEntity<String> postMusicSet(@RequestBody @Valid Map<String,Object> body) {
        DiscJockey discJockey = discJockeyService.getDiscJockey(body.get("discjockeyname").toString());
        MusicSet musicSet = musicSetService.postNewMusicSet(new MusicSet(body.get("title").toString(), discJockey, body.get("genre").toString()));
        if (musicSet == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(String.format("%s Music set has been added", musicSet));
        }
    }

    @DeleteMapping("/id")
    protected ResponseEntity<String> deleteMusicSet(@RequestBody @Valid Map<String,Object> body) {
        long id = Long.parseLong(body.get("id").toString());
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
