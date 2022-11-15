package com.capgemini.festival;

import com.capgemini.festival.model.DiscJockey;
import com.capgemini.festival.model.MusicSet;
import com.capgemini.festival.repository.DiscJockeyRepository;
import com.capgemini.festival.repository.MusicSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseRunner.class);
    private final DiscJockeyRepository discJockeyRepository;

    private final MusicSetRepository musicSetRepository;
    public DatabaseRunner(DiscJockeyRepository DJRepository, MusicSetRepository MSRepository) {
        this.discJockeyRepository = DJRepository;
        this.musicSetRepository = MSRepository;
    }

    @Override
    public void run (String[] args) throws Exception {

        discJockeyRepository.deleteAll();

        discJockeyRepository.save(new DiscJockey("Alfred", "Techno"));
        discJockeyRepository.save(new DiscJockey("Bert", "Electro"));
        discJockeyRepository.save(new DiscJockey("Jaap", "Hardcore"));
        discJockeyRepository.save(new DiscJockey("Henk", "Deep House"));

        musicSetRepository.deleteAll();

        musicSetRepository.save(new MusicSet("42", "Alfred" ,"Techno"));
        musicSetRepository.save(new MusicSet("Berlin Calling", "Bert" ,"Electro"));
        musicSetRepository.save(new MusicSet("Break Time", "Jaap" ,"Hardcore"));
        musicSetRepository.save(new MusicSet("Chillout", "Henk" ,"Deep House"));
    }
}
