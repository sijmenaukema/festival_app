package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.service.DiscJockeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/discjockeys")
public class DiscJockeyRestController {

    @Autowired
    DiscJockeyService discJockeyService;

    @GetMapping("/")
    private ResponseEntity<List<DiscJockey>> getAllDiscJockeys() {
        List<DiscJockey> discJockeys  = discJockeyService.getAllDiscJockeys();
        return ResponseEntity.ok().body(discJockeys);
    }

    @GetMapping("/namelength")
    private ResponseEntity<List<DiscJockey>> getAllDiscJockeysWithNameLongerThenSixChar(@RequestBody int length) {
        List<DiscJockey> discJockeys  = discJockeyService.getAllDiscJockeysWithNameLength(length);
        return ResponseEntity.ok().body(discJockeys);
    }

    @GetMapping("/id")
    private ResponseEntity<DiscJockey> getDiscJockey(@RequestBody @Valid long id) {
        DiscJockey discJockey = discJockeyService.getDiscJockey(id);
        if (discJockey == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(discJockey);
        }
    }

    @PostMapping("/")
    private ResponseEntity<String> postDiscJockey(@RequestBody @Valid DiscJockey newDiscJockey) {
        discJockeyService.postNewDiscJockey(newDiscJockey);
        return ResponseEntity.ok("Disc jockey has been added");
    }

    @DeleteMapping("/id")
    private ResponseEntity<String> deleteDiscJockey(@RequestBody @Valid long id) {
        discJockeyService.removeDiscJockey(id);
        return ResponseEntity.ok("Disc jockey has been removed");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions( MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
