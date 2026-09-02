package com.example.proy2p_chavez_luna_martnezgutirrez;

/**
 * Utilitario para homologar la comparación de nombres de fases del torneo.
 *
 * @author Samantha Luna
 * @version 1.0
 */
public class NormalizadorFases {

    /**
     * Compara dos nombres de fases omitiendo sufijos como "de final", espacios y guiones bajos.
     */
    public static boolean sonMismaFase(String fase1, String fase2) {
        if (fase1 == null || fase2 == null) return false;
        String f1 = limpiar(fase1);
        String f2 = limpiar(fase2);
        return f1.equals(f2) || f1.startsWith(f2) || f2.startsWith(f1);
    }

    private static String limpiar(String fase) {
        return fase.toLowerCase()
                .replace("_", " ")
                .replace("de final", "")
                .replace("partido por el", "")
                .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u")
                .trim();
    }
}