package com.alura.literalura.principal;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
