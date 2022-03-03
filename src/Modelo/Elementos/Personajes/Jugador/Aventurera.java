/*
 * Autor: Nelson Santiago Roa Garzón
 * 28/03/2020 01:41:10 AM
 */
package Modelo.Elementos.Personajes.Jugador;

import Modelo.Elementos.Mapa;

public class Aventurera extends Jugador {

    public Aventurera(double posicionX, double posicionY, Mapa mapa) {
        super(posicionX, posicionY, mapa);
        this.clase = "Aventurera";
        this.posicionInicial = 1;
        this.sentido = 4;
        this.anchoPersonaje = 46;
        this.altoPersonaje = 62;

        inicializarLimites(this.anchoPersonaje, this.altoPersonaje);
    }

    @Override
    public void resetearPersonaje() {
        this.vigor = 1;
        this.tasaRestauracion = 2;
        this.manaMaximo = 1200;
        this.resistenciaMaxima = 240;
        this.vitalidadMaxima = 700;
        this.restauracionMaxima = 150;
        this.recuperacionMaxima = 150;
    }

    @Override
    public void cambiarDireccion(final int velocidadX, final int velocidadY) {
        if (velocidadX == -1) {
            sentido = 5;
        } else if (velocidadX == 1) {
            sentido = 6;
        }
        if (velocidadY == -1) {
            sentido = 7;
        } else if (velocidadY == 1) {
            sentido = 4;
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
