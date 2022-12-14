package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.service.DiscJockeyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

public class DiscJockeyRestControllerTest {

    @Mock
    DiscJockeyService discJockeyService;

    @InjectMocks
    DiscJockeyController discJockeyRestController;

    ArrayList<DiscJockey> discJockeys = new ArrayList<>();
    DiscJockey discJockey = new DiscJockey("TESTER");
    final String id = "1";
    HashMap<String,Object> body = new HashMap<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        discJockeys.add(discJockey);
        discJockeys.add(discJockey);
        body.put("id", "1");
        body.put("name", "TESTER");

    };

    @Test
    public void WhenGetDiscJockey_ThenGetDiscJockeys() {
        Mockito.when(discJockeyService.getAllDiscJockeys()).thenReturn(discJockeys);
        ResponseEntity<List<DiscJockey>> response = discJockeyRestController.getAllDiscJockeys();
        Mockito.verify(discJockeyService, times(1)).getAllDiscJockeys();
        assertEquals(response.getBody(), discJockeys );
    }

    @Test//WhenDiscJockeyNotFound_ThenReturn404
    public void WhenDiscJockeyNotFound_ThenReturn404(){
        ResponseEntity<DiscJockey> discJockeyNotFound = ResponseEntity.notFound().build();
        Mockito.when(discJockeyService.getDiscJockey(1L)).thenReturn(discJockeyNotFound.getBody());
        ResponseEntity<DiscJockey> response = discJockeyRestController.getDiscJockey(id);
        Mockito.verify(discJockeyService, times(1)).getDiscJockey(1L);
        assertEquals(response.getBody(), discJockeyNotFound.getBody() );
    }

    @Test
    public void WhenGetDiscJockeyByID_ThenGetDiscJockey(){
        Mockito.when(discJockeyService.getDiscJockey(1L)).thenReturn(discJockey);
        ResponseEntity<DiscJockey> response = discJockeyRestController.getDiscJockey(id);
        Mockito.verify(discJockeyService, times(1)).getDiscJockey(1L);
        assertEquals(response.getBody(), discJockey );
    }

    @Test
    public void WhenDeleteDiscJockey_ThenRemoveDiscJockey(){
        Mockito.when(discJockeyService.removeDiscJockey(1L)).thenReturn(discJockey);
        ResponseEntity<String> response = discJockeyRestController.deleteDiscJockey(id);
        Mockito.verify(discJockeyService, times(1)).removeDiscJockey(1L);
        assertEquals(response.getBody(), String.format("%s Disc jockey has been removed", discJockey.toString()));
    }
}
