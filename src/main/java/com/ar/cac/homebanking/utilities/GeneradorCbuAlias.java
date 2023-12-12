package com.ar.cac.homebanking.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneradorCbuAlias {
    private static final List<String> PALABRAS = Arrays.asList("sol", "luna", "estrella", "rio", "montaña");
    //defino los metodos generarAlias y generarCBU como estáticos para que puedan ser accedidos desde AccountService
    //y desde UserService
    public static String generarAliasAleatorio() {
        Random random = new Random();
        StringBuilder alias = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int indicePalabra = random.nextInt(PALABRAS.size());
            alias.append(PALABRAS.get(indicePalabra));

            if (i < 2) {
                alias.append(".");
            }
        }

        return alias.toString();
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
