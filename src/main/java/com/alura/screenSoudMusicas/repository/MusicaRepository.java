package com.alura.screenSoudMusicas.repository;

import com.alura.screenSoudMusicas.model.Artista;
import com.alura.screenSoudMusicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica, Long> {


//    Optional<Artista>  findByArtistaNomeContainingIgnorecase(String artista);


    @Query(" select a from Artista a WHERE a.nome ILIKE %:artistaNome%")
    Optional<Artista> buscarArtistaDaMusica(String artistaNome);

    @Query("select m from Artista a JOIN a.musicas m WHERE a.nome ILIKE %:artistaNome%")
    List<Musica> buscarMusicasDeArtista(String artistaNome);
}
