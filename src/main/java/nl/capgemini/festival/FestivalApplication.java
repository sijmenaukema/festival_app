package nl.capgemini.festival;

import nl.capgemini.festival.model.DiscJockey;
import nl.capgemini.festival.model.MusicSet;
import nl.capgemini.festival.repository.DiscJockeyRepository;
import nl.capgemini.festival.repository.MusicSetRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FestivalApplication {

	@Autowired
	private DiscJockeyRepository discJockeyRepository;
	@Autowired
	private MusicSetRepository musicSetRepository;

	public static void main(String[] args) {
		SpringApplication.run(FestivalApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			discJockeyRepository.save(new DiscJockey("Piet", "Mondharmonika"));
			discJockeyRepository.save(new DiscJockey("Kees", "Ukelele"));
			discJockeyRepository.save(new DiscJockey("Jan", "Doedelzak"));
			musicSetRepository.save(new MusicSet("flutMuziek", "jan", "Doedelzak"));
			musicSetRepository.save(new MusicSet("Herrie", "Kees", "Ukelele"));
			musicSetRepository.save(new MusicSet("Klaagzang", "Piet", "Mondharmonika"));
		};
	}

}