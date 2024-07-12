package com.alura.literalura.principal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public record Autores (
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer muerte){

    @Override
    public String toString() {
        String[] partes = nombre.split(", ");

        String apeAutor = partes[0].trim();
        String nomAutor = partes[1].trim();

        var nombreAutor = nomAutor + " " +apeAutor;
        return nombreAutor + nacimiento + muerte;
    }
}
