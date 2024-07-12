package com.alura.literalura.principal;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();

    //Trae todos los libros
    private final String URL_BASE = "http://gutendex.com/books/?search=";

    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;

    //Esta lista de libros servira para mostrar los libros guardados
    private List<Libro> libros;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        int flag = 1;
        do {
            var menu = """
                    \n
                    1 - Buscar libro por titulo
                    2 - Listar libros buscados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma              
                    0 - Salir
                    """;
            System.out.println(menu);
            var opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresBuscados();
                    break;
                case 4:
                    mostrarAutoresPorDeterminadoAno();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("SALIENDO");
                    flag = 0;
                    break;
            }
        } while (flag == 1);
    }

    public Resultados getDatosLibro() {
        System.out.println("Escribe el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();

        //Aqui se obtine el json
        var jsonDeLibro = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        //Se muestra el json
        //System.out.println(jsonDeLibro);

        //Aqui pasamos el json a objeto
        DatosLibro datosDeLibro = conversor.obtenerDatos(jsonDeLibro, DatosLibro.class);

        //Guardamos solo el primer libro
        //Codigo viejo -> Resultados primerLibroEncontrado = datosDeLibro.resultados()[0];
        //System.out.println(primerLibroEncontrado);

        //El Resultados primerLibro se retorna a buscarSerieWeb();
        return datosDeLibro.resultados()[0];
    }

    private void buscarSerieWeb() {
        // Se pueden instanciar objetos con el mismo nombre que otros, simpre y
        // cuando esten en diferentes metodos. Como las variables

        //getDatosLibro() trae consigo la atributos que nosotros queremos de la API
        Resultados primerLibroEncontrado = getDatosLibro();

        //Creamos la un objeto de la clase - entidad, y le pasamos todos lo datoos de  Resultados primerLibro
        // Este trae consigo los datos que necesitamos de la API

        Libro libro = new Libro(primerLibroEncontrado);

        //Spring automáticamente maneja la creación de la tabla correspondiente en tu
        // gestor de base de datos si esta no existe aún

        repositorio.save(libro);

        //datosSeries.add(datos);
        System.out.println(primerLibroEncontrado);
    }

    private void mostrarLibrosBuscados() {
        libros = repositorio.findAll();
        System.out.println(libros);
    }

    private void mostrarAutoresBuscados() {
        //Esta es una manera NO OPTIMA de hacerlo
        List<Libro> autores = repositorio.findAll();
        System.out.println("\n AUTORES: ");
        autores.forEach(x ->
                System.out.println(x.getAutores().replace("[", " ").replace("]", " ")));
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("""
                \nIngrese el idioma para buscar los libros:
                 es- español
                 en- ingles
                 fr- frances
                 pt- portugues
                """);
        String idiomaSeleccionado = teclado.nextLine();
        String idiomaSeleccionado2 = "[" + idiomaSeleccionado + "]";
        List<Libro> libros = repositorio.findAll();

        List<Libro> librosFiltrados = libros.stream()
                .filter(libro -> Objects.equals(libro.getIdiomas(), idiomaSeleccionado2))
                .toList();

        if (librosFiltrados.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma seleccionado.");
        } else {
            System.out.println("Libros encontrados en el idioma seleccionado: \n");
            librosFiltrados.forEach(System.out::println);
        }
    }

    private void mostrarAutoresPorDeterminadoAno() {
        System.out.println("Ingrese el año a buscar: ");
        int anio = Integer.parseInt(teclado.nextLine());

        List<Libro> libros = repositorio.findAll();
        libros.stream()
                .filter(libro -> libro.getNacimiento() <= anio || libro.getMuerte() >= anio)
                .forEach(System.out::println);

    }
}