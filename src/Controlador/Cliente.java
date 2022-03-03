/*
 * Universidad Distrital Francisco José de Caldas
 * Ingeniería de Sistemas
 * Autor: Nelson Santiago Roa Garzón
 * 9/03/2020 06:32:14 PM
 */
package Controlador;

public class Cliente {

    public static void main(String[] args) {

        Juego juego = new Juego(Configuracion.NOMBRE, Configuracion.ANCHO_PANTALLA_COMPLETA, Configuracion.ALTO_PANTALLA_COMPLETA);

        juego.iniciarJuego();
        juego.iniciarBuclePrincipal();
    }

}
