/*
 * Autor: Nelson Santiago Roa Garzón
 * 9/03/2020 06:35:04 PM
 */
package Modelo.Controles;

import Controlador.Configuracion;
import Modelo.Herramientas.CargadorRecursos;
import Vista.Pantalla;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;

public class Raton extends MouseAdapter {

    private final Cursor cursor;
    private Point posicion;
    private boolean click;

    private static Raton instancia;

    private Raton(final Pantalla pantalla) {
        Toolkit configuracion = Toolkit.getDefaultToolkit();

        BufferedImage icono = CargadorRecursos.cargarImagenCompatibleTranslucida(Configuracion.RUTA_CURSOR_1);

        Point punta = new Point(3, 3);

        this.cursor = configuracion.createCustomCursor(icono, punta, "Cursor por defecto");

        posicion = new Point();

        click = false;

        actualizarPosicion(pantalla);
    }

    public static Raton getInstancia(final Pantalla pantalla) { //Singleton
        if (instancia == null) {
            instancia = new Raton(pantalla);
        }
        return instancia;
    }

    public void MouseClicked(MouseEvent e) {
        if (!click) {
            click = true;
        }

    }

    public void actualizar(Pantalla pantalla) {
        actualizarPosicion(pantalla);
    }

    private void actualizarPosicion(final Pantalla pantalla) {
        final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();

        SwingUtilities.convertPointFromScreen(posicionInicial, pantalla);

        posicion.setLocation(posicionInicial.getX(), posicionInicial.getY());
    }

    public boolean isClicked() {
        return click;
    }

    public void unClick() {
        if (click) {
            click = false;
        }
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    public Point getPosicion() {
        return posicion;
    }

}
