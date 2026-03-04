package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n=== LITER-ALURA ===
                    1 - Buscar libro por título (y guardar)
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibroWeb();
                case 2 -> mostrarLibrosGuardados();
                case 3 -> mostrarAutoresRegistrados();
                case 4 -> mostrarAutoresVivos();
                case 5 -> mostrarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void mostrarAutoresRegistrados() {
        autorRepository.findAll().forEach(a -> 
            System.out.println("Autor: " + a.getNombre() + " | Nacimiento: " + a.getFechaDeNacimiento()));
    }

    private void mostrarAutoresVivos() {
        System.out.println("Ingrese el año que desea consultar:");
        var anio = teclado.nextInt();
        teclado.nextLine();
        var autoresVivos = autorRepository.buscarAutoresVivosEnDeterminadoAnio(anio);
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            autoresVivos.forEach(a -> System.out.println("Autor vivo: " + a.getNombre()));
        }
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma para buscar (es, en, fr, pt):");
        var idioma = teclado.nextLine();
        var librosPorIdioma = libroRepository.findByIdioma(idioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma.");
        } else {
            System.out.println("Libros en " + idioma + ":");
            librosPorIdioma.forEach(l -> System.out.println("- " + l.getTitulo()));
        }
    }

    private void buscarLibroWeb() {
        System.out.println("Ingrese el nombre del libro:");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        var datos = conversor.obtenerDatos(json, DatosResultados.class);

        if (datos.resultados().isEmpty()) {
            System.out.println("Libro no encontrado.");
        } else {
            DatosLibro datosLibro = datos.resultados().get(0);
            DatosAutor datosAutor = datosLibro.autores().get(0);

            // Verificamos si el autor ya existe para no duplicarlo
            Autor autor = autorRepository.findByNombreIgnoreCase(datosAutor.nombre());
            if (autor == null) {
                autor = new Autor(datosAutor);
                autorRepository.save(autor);
            }

            Libro libro = new Libro(datosLibro, autor);
            libroRepository.save(libro);
            System.out.println("¡Libro guardado: " + libro.getTitulo() + "!");
        }
    }

    private void mostrarLibrosGuardados() {
        libroRepository.findAll().forEach(l -> 
            System.out.println("Libro: " + l.getTitulo() + " | Autor: " + l.getAutor().getNombre()));
    }
}