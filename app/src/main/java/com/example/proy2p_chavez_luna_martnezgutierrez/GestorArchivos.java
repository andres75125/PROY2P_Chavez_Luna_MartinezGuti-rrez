package main.java.com.example.proy2p_chavez_luna_martnezgutierrez;

import android.content.Context;
import java.io.*;
import java.util.*;

public class GestorArchivos {

    public static void copiarAssetsAInterno(Context context) {
        String[] archivos = {"usuarios.txt", "participantes.txt", "administradores.txt", "partidos.txt", "resultados.txt"};
        for (String nombre : archivos) {
            File f = new File(context.getFilesDir(), nombre);
            // Quitamos la condicion !f.exists() para que durante el desarrollo siempre copie los datos actualizados
            try (InputStream is = context.getAssets().open(nombre);
                 OutputStream os = new FileOutputStream(f)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Usuario> cargarUsuarios(Context context) {
        List<Usuario> lista = new ArrayList<>();
        Map<String, Integer> puntajes = cargarPuntajes(context);
        Map<String, String> cargos = cargarCargos(context);

        File f = new File(context.getFilesDir(), "usuarios.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea = br.readLine(); // Omite la cabecera
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 5) {
                    String id = datos[0].trim();
                    String user = datos[1].trim();
                    String pass = datos[2].trim();
                    String nombre = datos[3].trim();
                    String tipo = datos[4].trim();

                    if (tipo.equalsIgnoreCase("PARTICIPANTE")) {
                        int pts = puntajes.getOrDefault(id, 0);
                        lista.add(new Participante(id, user, pass, nombre, tipo, pts));
                    } else {
                        String cargo = cargos.getOrDefault(id, "General");
                        lista.add(new Administrador(id, user, pass, nombre, tipo, cargo));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private static Map<String, Integer> cargarPuntajes(Context context) {
        Map<String, Integer> map = new HashMap<>();
        File f = new File(context.getFilesDir(), "participantes.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                if (p.length >= 2) map.put(p[0].trim(), Integer.parseInt(p[1].trim()));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return map;
    }

    private static Map<String, String> cargarCargos(Context context) {
        Map<String, String> map = new HashMap<>();
        File f = new File(context.getFilesDir(), "administradores.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";");
                if (p.length >= 2) map.put(p[0].trim(), p[1].trim());
            }
        } catch (Exception e) { e.printStackTrace(); }
        return map;
    }
}