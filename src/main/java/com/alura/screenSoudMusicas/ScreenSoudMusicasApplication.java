package com.alura.screenSoudMusicas;

import com.alura.screenSoudMusicas.principal.Principal;
import com.alura.screenSoudMusicas.repository.ArtistaRepository;
import com.alura.screenSoudMusicas.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenSoudMusicasApplication implements CommandLineRunner {

	@Autowired
	private MusicaRepository musicaRepository;

	@Autowired
	private ArtistaRepository artistaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenSoudMusicasApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository, musicaRepository);
		principal.exibeMenu();
	}
}
