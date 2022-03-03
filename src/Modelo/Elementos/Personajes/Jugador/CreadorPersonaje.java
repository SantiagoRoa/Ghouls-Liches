/*
 * Autor: Nelson Santiago Roa Garzón
 * 29/03/2020 11:53:50 PM
 */
package Modelo.Elementos.Personajes.Jugador;

import Controlador.Configuracion;
import Modelo.Elementos.Mapa;

public class CreadorPersonaje {

    int x = Configuracion.SPAWN.x;
    int y = Configuracion.SPAWN.y;

    public Jugador seleccionarPersonaje(String id, Mapa m) { //Factory method

        switch (id) {
            case "Valkyria":
                return new Valkyria(x, y, m);
            case "Aventurera":
                return new Aventurera(x, y, m);
            case "Mago":
                return new Mago(x, y, m);
            default:
                System.out.println("No se ha iniciado el jugador");
                return null;
        }
    }

}
