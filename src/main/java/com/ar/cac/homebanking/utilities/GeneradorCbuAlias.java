package com.ar.cac.homebanking.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GeneradorCbuAlias {
    private static final List<String> PALABRAS = Arrays.asList("sol", "luna", "estrella", "rio", "montaña", "cabaña", "foco", "pais",
            "brasil", "locro", "camino");

    private static final Set<String> PALABRAS_UTILIZADAS = new HashSet<>();

    public static String generarAliasAleatorio() {
        Random random = new Random();
        StringBuilder alias = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            String palabraAleatoria = obtenerPalabraAleatoriaNoUtilizada(random);

            alias.append(palabraAleatoria);

            PALABRAS_UTILIZADAS.add(palabraAleatoria);

            if (i < 2) {
                alias.append(".");
            }
        }

        // Reinicia las palabras utilizadas si se han agotado todas
        if (PALABRAS_UTILIZADAS.size() == PALABRAS.size()) {
            PALABRAS_UTILIZADAS.clear();
        }

        return alias.toString();
    }

    private static String obtenerPalabraAleatoriaNoUtilizada(Random random) {
        List<String> palabrasNoUtilizadas = new ArrayList<>(PALABRAS);
        palabrasNoUtilizadas.removeAll(PALABRAS_UTILIZADAS);
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
