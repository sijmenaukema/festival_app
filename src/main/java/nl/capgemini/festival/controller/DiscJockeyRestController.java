package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.service.DiscJockeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiscJockeyRestController {

    @Autowired
    DiscJockeyService discJockeyService;

    @GetMapping("/discjockeys")
    private ResponseEntity getAllDiscJockeys() {
        return discJockeyService.getAllDiscJockeys();
    }

    @GetMapping("/discjockeys/{id}")
    private ResponseEntity getDiscJockeys(@PathVariable("id") long id) {
        return discJockeyService.getDiscJockey(id);
    }

    @PostMapping("/discjockeys")
    private ResponseEntity postDiscJockey(@RequestBody DiscJockey newDiscJockey) {
        return discJockeyService.postNewDiscJockey(newDiscJockey);
    }

    @DeleteMapping("/discjockeys/{id}")
    private ResponseEntity deleteDiscJockey(@PathVariable("id")long id) {
        return discJockeyService.removeDiscJockey(id);
    }
}
