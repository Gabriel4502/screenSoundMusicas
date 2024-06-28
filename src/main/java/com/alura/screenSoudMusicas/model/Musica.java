package com.alura.screenSoudMusicas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musica")
public class Musica {

    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id
    private long id;

    private String titulo;
    private String album;
    @ManyToOne
    private Artista artista;

    public Musica(){
    }

    public Musica( String titulo, String album, Artista artista) {
        this.id = id;
        this.titulo = titulo;
        this.album = album;
        this.artista = artista;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return
                "\n" +
                " titulo: " + titulo + '\n' +
                " album: " + album + '\n' +
                " artista: " + artista.getNome();
    }
}
