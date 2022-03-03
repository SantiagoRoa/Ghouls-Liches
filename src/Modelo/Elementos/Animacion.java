/*
 * Autor: Nelson Santiago Roa Garzón
 * 28/03/2020 03:58:30 PM
 */
package Modelo.Elementos;

import java.awt.Graphics;

public interface Animacion {

    public abstract void mover();

    public abstract void caminar();

    public abstract void darPaso(int velocidadX, int velocidadY);

    public abstract int evaluarVelocidadX();

    public abstract int evaluarVelocidadY();

    public abstract void cambiarDireccion(final int velocidadX, final int velocidadY);

    public abstract void cambiarAnimacionEstado();

    public abstract void animar();

    public abstract void dibujar(Graphics g);

}
