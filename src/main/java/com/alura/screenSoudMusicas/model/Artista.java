package com.alura.screenSoudMusicas.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;

    public Artista() {

    }

    public Artista(String nome, TipoArtista tipoArtista) {
        this.tipoArtista = tipoArtista;
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoArtista=" + tipoArtista +
                ", musicas=" + musicas +
                '}';
    }
}
