package com.alura.literalura.principal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
//        Integer count,
//        String next,
//        String previous,
        @JsonAlias("results") Resultados[] resultados
){


    @Override
    public String toString() {

        return Arrays.toString(resultados);
    }
}
