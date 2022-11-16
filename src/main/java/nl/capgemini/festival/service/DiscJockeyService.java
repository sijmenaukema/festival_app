package nl.capgemini.festival.service;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.repository.DiscJockeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscJockeyService {

    @Autowired
    DiscJockeyRepository discJockeyRepository;

    public ResponseEntity getAllDiscJockeys(){
        List discJockeys = new ArrayList();
        discJockeyRepository.findAll().forEach(discJockeys::add);
        return new ResponseEntity<>(discJockeys, HttpStatus.OK);
    }

    public ResponseEntity getDiscJockey(Long id) {
        Optional<DiscJockey> optional = discJockeyRepository.findById(id);
                if (optional.isPresent()) {
                    return new ResponseEntity<>(
                            optional.get(), HttpStatus.OK);
                }else{
                        return new ResponseEntity<>(
                    null, HttpStatus.NOT_FOUND);
                }
    }

    public ResponseEntity postNewDiscJockey(DiscJockey discJockey) {
        discJockeyRepository.save(discJockey);
        return new ResponseEntity<>(
                HttpStatus.CREATED);
    }

    public ResponseEntity removeDiscJockey(Long id) {
        discJockeyRepository.deleteById(id);
        return new ResponseEntity<>(
                HttpStatus.OK);
    }
}
