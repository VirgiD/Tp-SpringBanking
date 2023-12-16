package com.ar.cac.homebanking.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneradorCbuAlias {
    private static final List<String> PALABRAS = Arrays.asList("sol", "luna", "estrella", "rio", "montaña", "cabaña", "foco", "pais",
            "brasil", "locro", "camino");

    private static final Set<String> PALABRAS_UTILIZADAS = new HashSet<>();

    public static String generarAliasAleatorio() {
        Random random = new Random();
        StringBuilder alias = new StringBuilder();

        List<String> palabrasDisponibles = new ArrayList<>(PALABRAS);

        for (int i = 0; i < 3; i++) {
            if (palabrasDisponibles.isEmpty()) {
                // Reinicia las palabras disponibles si se agotan
                palabrasDisponibles.addAll(PALABRAS);
                PALABRAS_UTILIZADAS.clear();
            }

            String palabraAleatoria = obtenerPalabraAleatoriaNoUtilizada(random, palabrasDisponibles);

            alias.append(palabraAleatoria);

            PALABRAS_UTILIZADAS.add(palabraAleatoria);

            palabrasDisponibles.remove(palabraAleatoria);

            if (i < 2) {
                alias.append(".");
            }
        }

        return alias.toString();
    }

    private static String obtenerPalabraAleatoriaNoUtilizada(Random random, List<String> palabrasDisponibles) {
        String palabraAleatoria = palabrasDisponibles.get(random.nextInt(palabrasDisponibles.size()));
        return palabraAleatoria;
    }

    private static String obtenerPalabraAleatoriaNoUtilizada(Random random) {
        List<String> palabrasNoUtilizadas = PALABRAS.stream()
                .filter(palabra -> !PALABRAS_UTILIZADAS.contains(palabra))
                .collect(Collectors.toList());

        return palabrasNoUtilizadas.get(random.nextInt(palabrasNoUtilizadas.size()));
    }

    public static String generarCbuAleatorio() {
        StringBuilder cbu = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 22; i++) {
            cbu.append(random.nextInt(10));
        }

        return cbu.toString();
    }

}
