/*
 * Autor: Nelson Santiago Roa Garzón
 * 28/03/2020 01:41:37 AM
 */
package Modelo.Elementos.Personajes.Jugador;

import Modelo.Elementos.Mapa;

public class Mago extends Jugador {

    public Mago(double posicionX, double posicionY, Mapa mapa) {
        super(posicionX, posicionY, mapa);
        this.clase = "Mago";
        this.anchoPersonaje = 42;
        this.altoPersonaje = 62;
        this.posicionInicial = 1;
        this.sentido = 0;

        inicializarLimites(this.anchoPersonaje, this.altoPersonaje);
    }

    @Override
    public void resetearPersonaje() {
        this.vigor = 0.5;
        this.tasaRestauracion = 3;
        this.manaMaximo = 1800;
        this.resistenciaMaxima = 240;
        this.vitalidadMaxima = 400;
        this.restauracionMaxima = 75;
        this.recuperacionMaxima = 100;
    }

    @Override
    public void cambiarDireccion(final int velocidadX, final int velocidadY) {
        if (velocidadX == -1) {
            sentido = 1;
        } else if (velocidadX == 1) {
            sentido = 2;
        }
        if (velocidadY == -1) {
            sentido = 3;
        } else if (velocidadY == 1) {
            sentido = 0;
        }
    }

    @Override
    public void cambiarAnimacionEstado() {
        if (animacion < Integer.MAX_VALUE) {
            animacion++;
        } else {
            animacion = 0;
        }
        int resto = animacion % 40;
        if (resto > 10 && resto <= 20) {
            estado = 1;
        } else if (resto > 20 && resto <= 30) {
            estado = 0;
        } else if (resto > 30) {
            estado = 1;
        } else {
            estado = 2;
        }
    }

}
