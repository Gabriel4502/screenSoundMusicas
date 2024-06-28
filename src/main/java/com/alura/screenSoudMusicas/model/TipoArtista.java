package com.alura.screenSoudMusicas.model;


public enum TipoArtista {

    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipo;

    TipoArtista(String tipo) {
        this.tipo = tipo;
    }

    public static TipoArtista fromString(String texto){
        for (TipoArtista tipoArtista: TipoArtista.values()){
            if(tipoArtista.tipo.equalsIgnoreCase(texto)){
                return tipoArtista;
            }
        }
        throw new IllegalArgumentException("Este tipo de artista não foi encontrado.");
    }
}
