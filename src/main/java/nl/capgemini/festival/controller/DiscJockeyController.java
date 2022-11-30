package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.service.DiscJockeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discjockey")
public class DiscJockeyController {

    final DiscJockeyService discJockeyService;

    public DiscJockeyController(DiscJockeyService discJockeyService) {
        this.discJockeyService = discJockeyService;
    }

    @GetMapping("/")
    protected ResponseEntity<List<DiscJockey>> getAllDiscJockeys() {
        List<DiscJockey> discJockeys  = discJockeyService.getAllDiscJockeys();
        return ResponseEntity.ok().body(discJockeys);
    }

    @GetMapping("/namelength")
    protected ResponseEntity<List<DiscJockey>> getAllDiscJockeysWithNameLongerThenGivenChar(@RequestBody Map<String,Object> body) {
        int length = Integer.parseInt(body.get("length").toString());
        List<DiscJockey> discJockeys  = discJockeyService.getAllDiscJockeysWithNameLength(length);
        return ResponseEntity.ok().body(discJockeys);
    }

    @GetMapping("/id")
    protected ResponseEntity<DiscJockey> getDiscJockey(@RequestBody @Valid Map<String,Object> body) {
        long id =  Long.parseLong(body.get("id").toString());
        DiscJockey discJockey = discJockeyService.getDiscJockey(id);
        if (discJockey == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(discJockey);
        }
    }

    @PostMapping("/")
    protected ResponseEntity<String> postDiscJockey(@RequestBody @Valid Map<String,Object> body) {
        DiscJockey newDiscJockey = new DiscJockey(body.get("name").toString(), body.get("genre").toString());
        DiscJockey createdDiscJockey = discJockeyService.postNewDiscJockey(newDiscJockey);
        if (createdDiscJockey != null) {
            return ResponseEntity.ok(String.format("%s Disc jockey has been added", createdDiscJockey));
        }else{
            return ResponseEntity.internalServerError().body("something went wrong");
        }
    }

    @DeleteMapping("/id")
    protected ResponseEntity<String> deleteDiscJockey(@RequestBody @Valid Map<String,Object> body) {
        long id =  Long.parseLong(body.get("id").toString());
        DiscJockey discJockey = discJockeyService.removeDiscJockey(id);
        return ResponseEntity.ok(String.format("%s Disc jockey has been removed", discJockey.toString()));
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
