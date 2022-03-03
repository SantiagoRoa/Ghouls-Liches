/*
 * Autor: Nelson Santiago Roa Garzón
 * 29/03/2020 10:55:47 AM
 */
package Modelo.MaquinaEstados.Estados.MenuPausa;

import Modelo.Controles.Raton;
import Modelo.Elementos.Personajes.Jugador.Jugador;
import Modelo.MaquinaEstados.EstadoJuego;
import Vista.InterfazUsuario.EstructuraMenuPausa;
import Vista.Pantalla;
import java.awt.Graphics;

public class GestorMenuPausa implements EstadoJuego {

    Jugador jugador;

    private final EstructuraMenuPausa estructuraMenuPausa;

    private final SeccionMenuPausa[] secciones;

    private final SeccionMenuPausa seccionActual;

    Pantalla pantalla;

    public GestorMenuPausa(final Pantalla pantalla) {
        inicializar();

        estructuraMenuPausa = new EstructuraMenuPausa(jugador, pantalla);

        secciones = new MenuAyuda[1];

        seccionActual = secciones[0];

    }

    public void inicializar() {
        iniciarRaton();
    }

    public void iniciarRaton() {
        Raton raton = Raton.getInstancia(pantalla);
    }

    @Override
    public void actualizar() {

        estructuraMenuPausa.actualizar();
    }

    @Override
    public void dibujar(Graphics g) {
        estructuraMenuPausa.dibujar(g, jugador);
    }

}
