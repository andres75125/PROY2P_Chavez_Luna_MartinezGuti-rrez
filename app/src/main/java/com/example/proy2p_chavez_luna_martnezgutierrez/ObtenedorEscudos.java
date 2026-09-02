package com.example.proy2p_chavez_luna_martnezgutirrez;

import android.content.Context;

/**
 * Utilitario para mapear las banderas de los países desde res/drawable.
 *
 * @author Andres Martínez
 * @version 1.0
 */
public class ObtenedorEscudos {

    public static int getEscudoResource(Context context, String nombreEquipo) {
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            return R.drawable.ic_launcher_background;
        }

        String temp = nombreEquipo.trim().toLowerCase()
                .replace("á", "a").replace("é", "e").replace("í", "i")
                .replace("ó", "o").replace("ú", "u").replace("ñ", "n");

        switch (temp) {
            case "alemania":
                return R.drawable.de;
            case "argentina":
                return R.drawable.ar;
            case "bosnia y herzegovina":
            case "bosnia":
                return R.drawable.ba;
            case "brasil":
                return R.drawable.br;
            case "canada":
                return R.drawable.ca;
            case "chequia":
                return R.drawable.cz;
            case "colombia":
                return R.drawable.co;
            case "corea del sur":
                return R.drawable.kr;
            case "croacia":
                return R.drawable.hr;
            case "curazao":
                return R.drawable.cw;
            case "ecuador":
                return R.drawable.ec;
            case "escocia":
                return R.drawable.sct;
            case "espana":
                return R.drawable.es;
            case "estados unidos":
            case "usa":
                return R.drawable.us;
            case "francia":
                return R.drawable.fr;
            case "haiti":
                return R.drawable.ht;
            case "inglaterra":
                return R.drawable.eng;
            case "japon":
                return R.drawable.jp;
            case "marruecos":
                return R.drawable.ma;
            case "mexico":
                return R.drawable.mx;
            case "noruega":
                return R.drawable.no;
            case "paises bajos":
                return R.drawable.nl;
            case "paraguay":
                return R.drawable.py;
            case "portugal":
                return R.drawable.pt;
            case "sudafrica":
                return R.drawable.za;
            case "turquia":
                return R.drawable.tr;
            default:
                return R.drawable.ic_launcher_background;
        }
    }
}
