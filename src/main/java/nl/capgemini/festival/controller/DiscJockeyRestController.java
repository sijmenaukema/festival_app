package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.service.DiscJockeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscJockeyRestController {

    @Autowired
    DiscJockeyService discJockeyService;

    @GetMapping("/discjockeys")
    private ResponseEntity getAllDiscJockeys() {
        List<DiscJockey> discJockeys  = discJockeyService.getAllDiscJockeys();
        return new ResponseEntity(discJockeys, HttpStatus.OK);
    }

    @GetMapping("/discjockeys/id")
    private ResponseEntity getDiscJockeys(@RequestBody long id) {
        DiscJockey discJockey = discJockeyService.getDiscJockey(id);
        if (discJockey == null) {
            return new ResponseEntity(
                    null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(
                    discJockey, HttpStatus.OK);
        }
    }

    @PostMapping("/discjockeys")
    private ResponseEntity postDiscJockey(@RequestBody DiscJockey newDiscJockey) {
        discJockeyService.postNewDiscJockey(newDiscJockey);
        return new ResponseEntity(
            null, HttpStatus.OK);
    }

    @DeleteMapping("/discjockeys/id")
    private ResponseEntity deleteDiscJockey(@RequestBody long id) {
        discJockeyService.removeDiscJockey(id);
        return new ResponseEntity(
                null, HttpStatus.OK);
    }
}
