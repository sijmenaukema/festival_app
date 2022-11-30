package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.service.DiscJockeyService;
import nl.capgemini.festival.service.MusicSetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MusicSetRestControllerTest {

    @Mock
    MusicSetService musicSetService;

    @Mock
    DiscJockeyService discJockeyService;

    @InjectMocks
    MusicSetController musicSetController;

    ArrayList<MusicSet> musicSets = new ArrayList<>();
    DiscJockey discJockey = new DiscJockey("TESTER", "TESTER");
    MusicSet musicSet = new MusicSet("TESTER", discJockey, "TESTER");

    HashMap<String,Object> body = new HashMap<>();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        musicSets.add(musicSet);
        musicSets.add(musicSet);
        body.put("id", "1");
        body.put("discjockeyname", "TESTER");
        body.put("title", "TESTER");
        body.put("genre", "TESTER");
    };

    @Test
    public void WhenGetMusicSet_ThenGetMusicSets(){
        Mockito.when(musicSetService.getAllMusicSets()).thenReturn(musicSets);
        ResponseEntity<List<MusicSet>> response = musicSetController.getAllMusicSets();
        Mockito.verify(musicSetService, times(1)).getAllMusicSets();
        assertEquals(response.getBody(), musicSets );
    }

    @Test
    public void WhenMusicSetNotFound_ThenReturn404(){
        ResponseEntity<MusicSet> musicSetNotFound = ResponseEntity.notFound().build();
        Mockito.when(musicSetService.getMusicSet(1L)).thenReturn(musicSetNotFound.getBody());
        ResponseEntity<MusicSet> response = musicSetController.getMusicSet(body);
        Mockito.verify(musicSetService, times(1)).getMusicSet(1L);
        assertEquals(response.getBody(), musicSetNotFound.getBody() );
    }

    @Test
    public void WhenGetMusicSetByID_ThenGetMusicSet(){
        Mockito.when(musicSetService.getMusicSet(1L)).thenReturn(musicSet);
        ResponseEntity<MusicSet> response = musicSetController.getMusicSet(body);
        Mockito.verify(musicSetService, times(1)).getMusicSet(1L);
        assertEquals(response.getBody(), musicSet);
    }

    @Test
    public void WhenDeleteMusicSet_ThenRemoveMusicSet(){
        Mockito.when(musicSetService.removeMusicSet(1L)).thenReturn(musicSet);
        ResponseEntity<String> response = musicSetController.deleteMusicSet(body);
        Mockito.verify(musicSetService, times(1)).removeMusicSet(1L);
        assertEquals(response.getBody(), String.format("%s Music set has been removed", musicSet.toString()));
    }
}
