/*
 * Autor: Nelson Santiago Roa Garzón
 * 9/03/2020 06:34:35 PM
 */
package Controlador;

import Modelo.Controles.GestorControles;
import Modelo.MaquinaEstados.GestorEstados;
import Vista.Pantalla;
import Vista.Ventana;

public class Juego {

    private boolean funcionando = false;
    private String titulo;
    private int ancho;
    private int alto;

    private Pantalla pantalla;
    private Ventana ventana;
    private GestorEstados gestor;

    public static int contador_FPS = 0; //debug
    public static int contador_APS = 0; //debug

    public Juego(final String titulo, final int ancho, final int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void iniciarJuego() {
        funcionando = true;
        inicializar();
    }

    private void actualizar() {
        if (GestorControles.teclado.menuActivo) {
            gestor.cambiarEstadoActual(1);
        } else {
            gestor.cambiarEstadoActual(0);
        }
        gestor.actualizar();
        pantalla.actualizar();
    }

    private void dibujar() {
        pantalla.dibujar(gestor);
    }

    public void inicializar() {
        pantalla = new Pantalla(ancho, alto);
        ventana = new Ventana(titulo, pantalla);
        gestor = new GestorEstados(pantalla);
    }

    public void iniciarBuclePrincipal() {
        int aps = 0;
        int fps = 0;

        final int NS_POR_SEGUNDO = 1000000000;
        final int APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0;

        while (funcionando) {
            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1) {
                actualizar();
                aps++;

                delta--;
            }

            dibujar();
            fps++;

            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                contador_FPS = fps;
                contador_APS = aps;

                aps = 0;
                fps = 0;

                referenciaContador = System.nanoTime();
            }
        }
    }
}
