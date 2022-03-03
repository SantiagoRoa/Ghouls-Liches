/*
 * Autor: Nelson Santiago Roa Garzón
 * 27/03/2020 06:37:13 PM
 */
package Controlador;

import Modelo.Herramientas.CargadorRecursos;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

public class Configuracion {

    public static final String NOMBRE = "Ghouls & Liches";

    public static int APS = 0;

    public static int RESOLUCION_HORIZONTAL = 1280;
    public static int RESOLUCION_VERTICAL = 720;

    public static int ANCHO_PANTALLA_COMPLETA = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int ALTO_PANTALLA_COMPLETA = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) RESOLUCION_HORIZONTAL;
    public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) RESOLUCION_VERTICAL;

    public static int CENTRO_VENTANA_X = RESOLUCION_HORIZONTAL / 2;
    public static int CENTRO_VENTANA_Y = RESOLUCION_VERTICAL / 2;

    public static final int LADO_SPRITE = 64;

    public static int CENTRO_SPRITE = LADO_SPRITE / 2;

    public static String RUTA_MOVIMIENTO_HEROE = "/Recursos/Sprites/HEROE_MOVIMIENTO.png";
    public static String RUTA_MAPA_1 = "/Recursos/Mapas/BOSQUE.map";
    public static String RUTA_ICONO_VENTANA = "/Recursos/Icono/ICON.png";
    public static String RUTA_CURSOR_1 = "/Recursos/Cursor/CURSOR1.png";

    public static Font FUENTE_KNIGHT = CargadorRecursos.cargarFuente("/Recursos/Fuentes/questknighta.ttf");
    public static Font FUENTE_MAGISTIC = CargadorRecursos.cargarFuente("/Recursos/Fuentes/Magicstics.ttf");
    public static Font FUENTE_OVERLORD = CargadorRecursos.cargarFuente("/Recursos/Fuentes/Cardinal.ttf");

    public static Point SPAWN = new Point(249, 325);
}
