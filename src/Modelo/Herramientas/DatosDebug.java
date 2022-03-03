/*
 * Autor: Nelson Santiago Roa Garzón
 * 29/03/2020 06:29:30 AM
 */
package Modelo.Herramientas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class DatosDebug {

    private static ArrayList<String> datos = new ArrayList<String>();

    public static Font fuente = new Font("arial", Font.BOLD, 8);

    public static void enviarDato(final String dato) {
        datos.add(dato);
    }

    public static void vaciarDatos() {
        datos.clear();
    }

    public static void dibujarDatos(final Graphics g) {
        g.setFont(fuente);
        for (int i = 0; i < datos.size(); i++) {

            AdaptadorDibujo.dibujarString(g, datos.get(i), 20, 30 + i * 10, Color.white);
        }

        datos.clear();
    }
}
