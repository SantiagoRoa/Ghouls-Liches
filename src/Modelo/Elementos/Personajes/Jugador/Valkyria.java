/*
 * Autor: Nelson Santiago Roa Garzón
 * 27/03/2020 06:46:24 PM
 */
package Modelo.Elementos.Personajes.Jugador;

import Modelo.Elementos.Mapa;

public class Valkyria extends Jugador {

    public Valkyria(double posicionX, double posicionY, Mapa mapa) {
        super(posicionX, posicionY, mapa);
        this.clase = "Valkyria";
        this.anchoPersonaje = 60;
        this.altoPersonaje = 62;
        this.posicionInicial = 4;
        this.sentido = 4;

        inicializarLimites(this.anchoPersonaje, this.altoPersonaje);
    }

    @Override
    public void resetearPersonaje() {
        this.vigor = 1.5;
        this.tasaRestauracion = 1;
        this.manaMaximo = 800;
        this.resistenciaMaxima = 360;
        this.vitalidadMaxima = 1000;
        this.restauracionMaxima = 250;
        this.recuperacionMaxima = 300;
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
            estado = 4;
        } else if (resto > 20 && resto <= 30) {
            estado = 3;
        } else if (resto > 30) {
            estado = 4;
        } else {
            estado = 5;
        }
    }

}
