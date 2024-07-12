package com.alura.literalura.principal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Resultados(
        @JsonAlias("title") String titulo,
        //Primero va el nombre y despues el apellido
        @JsonAlias("authors") Autores[] autores,
        @JsonAlias("languages") String[] idiomas,
        @JsonAlias("download_count") Integer totalDeDescargas){

    @Override
    public String toString() {
        var idiomaLibro = Arrays.toString(idiomas).replace("["," ").replace("]"," ");
        var autoresLibros =  Arrays.toString(autores).replace("["," ").replace("]"," ");
        return "--------- L I B R O --------- \n" +
                "\n Titulo = " + titulo  +
                "\n Autores = " + autoresLibros +
                "\n Idiomas = " + idiomaLibro +
                "\n Total De Descargas = "  + totalDeDescargas +
                "\n\n";
    }

}
