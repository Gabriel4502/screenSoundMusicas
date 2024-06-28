package com.alura.screenSoudMusicas.principal;

import com.alura.screenSoudMusicas.model.Artista;
import com.alura.screenSoudMusicas.model.Musica;
import com.alura.screenSoudMusicas.model.TipoArtista;
import com.alura.screenSoudMusicas.repository.ArtistaRepository;
import com.alura.screenSoudMusicas.repository.MusicaRepository;
import com.alura.screenSoudMusicas.service.ConsultaGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scan = new Scanner(System.in);
    private Optional<Musica> musicaBusca;
    private MusicaRepository musicaRepository;
    private ArtistaRepository artistaRepository;
    private Optional <Artista> artistaOptional;
    private Artista artista;

    public Principal(ArtistaRepository artistaRepository, MusicaRepository musicaRepository){
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarArtistas() {
        System.out.println("Insira o nome do artista que deseja cadastrar: ");
        var artistaNome = scan.nextLine();
        System.out.println("Qual o tipo do artista? ");
        var tipo = scan.nextLine();
        TipoArtista tipoArtista = TipoArtista.fromString(tipo);
        Artista artista1 = new Artista(artistaNome, tipoArtista);

        artistaRepository.save(artista1);


    }

    private boolean verificarArtista(String artistaNome){
        this.artistaOptional = musicaRepository.buscarArtistaDaMusica(artistaNome);

        if(artistaOptional.isPresent()){
            this.artista = artistaOptional.get();
            return true;
        }else{
            System.out.println("Artista não encontrado");
            return false;
        }
    }

    private void cadastrarMusicas() {
        System.out.println("Qual o artista da música? ");
        var artistaNome = scan.nextLine();

        if(verificarArtista(artistaNome)){
            System.out.println("Qual o nome da música? ");
            var musicaNome = scan.nextLine();
            System.out.println("Qual o álbum da música? ");
            var albumNome = scan.nextLine();

            Artista artistaEncontrado = artistaOptional.get();

            Musica musica = new Musica(musicaNome, albumNome, artistaEncontrado);
            musica.setArtista(artistaEncontrado);
            artistaEncontrado.getMusicas().add(musica);
            musicaRepository.save(musica);

        }



    }

    private void listarMusicas() {
        List<Musica> musicas = musicaRepository.findAll();
        musicas.forEach(System.out::println);
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Qual o nome do artista? ");
        var artistaNome = scan.nextLine();

        if(verificarArtista(artistaNome)){
            List <Musica> musicasDeArtista = musicaRepository.buscarMusicasDeArtista(artistaNome);
            musicasDeArtista.forEach(e -> System.out.printf("\n Musica: Titulo %s \n Album: %s \n",
                    e.getTitulo(), e.getAlbum()));
        }

    }

    private void pesquisarDadosDoArtista() {
        System.out.println("Qual artista o que deseja saber sobre");
        var artistaNome = scan.nextLine();
        if (verificarArtista(artistaNome)){
           var resposta =ConsultaGPT.obterInfo(artistaNome);
            System.out.println(resposta);
        }
    }







    }




