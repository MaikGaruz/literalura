package com.alura.literalura.principal;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private String autores;
    private String idiomas;
    private Integer totalDeDescargas;

    @Transient
    private Integer nacimiento;

    @Transient
    private Integer muerte;

    public Libro(){
    }

    public Libro(Resultados primerLibroEncontrado) {
        this.titulo = primerLibroEncontrado.titulo();
        this.autores = Arrays.toString(primerLibroEncontrado.autores());
        this.idiomas = Arrays.toString(primerLibroEncontrado.idiomas());
        this.totalDeDescargas = primerLibroEncontrado.totalDeDescargas();
    }

    @Override
    public String toString() {
        return "--------- L I B R O --------- \n" +
                "\n Id = " + id +
                "\n Titulo = " + titulo  +
                "\n Autores = " + autores.replace("["," ").replace("]"," ")+
                "\n Idiomas = " + idiomas.replace("["," ").replace("]"," ")+
                "\n Total De Descargas = "  + totalDeDescargas +
                "\n\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getTotalDeDescargas() {
        return totalDeDescargas;
    }

    public void setTotalDeDescargas(Integer totalDeDescargas) {
        this.totalDeDescargas = totalDeDescargas;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getMuerte() {
        return muerte;
    }

    public void setMuerte(Integer muerte) {
        this.muerte = muerte;
    }
}
