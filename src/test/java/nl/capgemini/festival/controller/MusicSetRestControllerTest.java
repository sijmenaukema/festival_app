package nl.capgemini.festival.controller;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.service.MusicSetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.Null;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class MusicSetRestControllerTest {

    @Mock
    MusicSetService musicSetService;

    @InjectMocks
    MusicSetRestController musicSetRestController;

    ArrayList<MusicSet> musicSets = new ArrayList<>();
    DiscJockey discJockey = new DiscJockey("TESTER", "TESTER");
    MusicSet musicSet = new MusicSet("TESTER", discJockey, "TESTER");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        musicSets.add(musicSet);
        musicSets.add(musicSet);
    };

    @Test
    public void WhenGetMusicSet_ThenGetMusicSets(){
        Mockito.when(musicSetService.getAllMusicSets()).thenReturn(musicSets);
        ResponseEntity<List<MusicSet>> response = musicSetRestController.getAllMusicSets();
        Mockito.verify(musicSetService, times(1)).getAllMusicSets();
        assertEquals(response.getBody(), musicSets );
    }

    @Test
    public void WhenMusicSetNotFound_ThenReturn404(){
        ResponseEntity<MusicSet> musicSetNotFound = ResponseEntity.notFound().build();
        Mockito.when(musicSetService.getMusicSet(1L)).thenReturn(musicSetNotFound.getBody());
        ResponseEntity<MusicSet> response = musicSetRestController.getMusicSet(1L);
        Mockito.verify(musicSetService, times(1)).getMusicSet(1L);
        assertEquals(response.getBody(), musicSetNotFound.getBody() );
    }

    @Test
    public void WhenGetMusicSetByID_ThenGetMusicSet(){
        Mockito.when(musicSetService.getMusicSet(1L)).thenReturn(musicSet);
        ResponseEntity<MusicSet> response = musicSetRestController.getMusicSet(1L);
        Mockito.verify(musicSetService, times(1)).getMusicSet(1L);
        assertEquals(response.getBody(), musicSet);
    }

    @Test
    public void WhenPostMusicSet_ThenPutNewMusicSet(){
        Mockito.when(musicSetService.postNewMusicSet(musicSet)).thenReturn(musicSet);
        ResponseEntity<String> response = musicSetRestController.postMusicSet(musicSet);
        Mockito.verify(musicSetService, times(1)).postNewMusicSet(musicSet);
        assertEquals(response.getBody(), String.format("%s Music set has been added", musicSet.toString()));
    }

    @Test
    public void WhenDeleteMusicSet_ThenRemoveMusicSet(){
        Mockito.when(musicSetService.removeMusicSet(1L)).thenReturn(musicSet);
        ResponseEntity<String> response = musicSetRestController.deleteMusicSet(1L);
        Mockito.verify(musicSetService, times(1)).removeMusicSet(1L);
        assertEquals(response.getBody(), String.format("%s Music set has been removed", musicSet.toString()));
    }
}
