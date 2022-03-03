/*
 * Autor: Nelson Santiago Roa Garzón
 * 9/03/2020 06:49:11 PM
 */
package Modelo.MaquinaEstados;

import Modelo.MaquinaEstados.Estados.Juego.GestorJuego;
import Modelo.MaquinaEstados.Estados.MenuPausa.GestorMenuPausa;
import Vista.Pantalla;
import java.awt.Graphics;

public class GestorEstados {

    Pantalla pantalla;

    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;

    public GestorEstados(Pantalla pantalla) {
        iniciarEstados();
        iniciarEstadoActual();
    }

    public void iniciarEstados() {
        estados = new EstadoJuego[2];
        estados[0] = new GestorJuego();
        estados[1] = new GestorMenuPausa(pantalla);

    }

    private void iniciarEstadoActual() {
        estadoActual = estados[0];
    }

    public void actualizar() {
        estadoActual.actualizar();

    }

    public void dibujar(final Graphics g) {
        estadoActual.dibujar(g);
    }

    public void cambiarEstadoActual(final int nuevoEstado) {
        estadoActual = estados[nuevoEstado];
    }

    public EstadoJuego obtenerEstadoActual() {
        return estadoActual;
    }
}
