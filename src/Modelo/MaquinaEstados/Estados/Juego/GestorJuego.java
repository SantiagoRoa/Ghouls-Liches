/*
 * Autor: Nelson Santiago Roa Garzón
 * 9/03/2020 07:09:26 PM
 */
package Modelo.MaquinaEstados.Estados.Juego;

import java.awt.Graphics;
import Modelo.MaquinaEstados.EstadoJuego;
import Modelo.Controles.Raton;
import Modelo.Elementos.Mapa;
import Modelo.Elementos.Personajes.Jugador.Jugador;
import Modelo.Elementos.Personajes.Jugador.CreadorPersonaje;
import Modelo.Herramientas.DatosDebug;
import Vista.InterfazUsuario.HUD;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class GestorJuego implements EstadoJuego {

    Mapa mapa;
    HUD hud;
    Raton raton;

    CreadorPersonaje creador;

    Jugador jugador;

    public GestorJuego() {
        inicializar();
    }

    public void inicializar() {
        iniciarMapa();
        iniciarJugador(mapa);
        iniciarHud();
    }

    public void recargarJuego() {
        iniciarMapa();
        iniciarJugador(mapa);
    }

    public void iniciarMapa() {
        mapa = Mapa.getInstance();
    }

    public void iniciarJugador(Mapa mapa) {
        String seleccion = preguntar();
        creador = new CreadorPersonaje();
        jugador = creador.seleccionarPersonaje(seleccion, mapa);
    }

    public String preguntar() {
        String[] personajes = {"Valkyria", "Mago", "Aventurera"};
        String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione la el Personaje deseado",
                "Ghouls & Liches ", JOptionPane.PLAIN_MESSAGE, null, personajes, personajes[0]);
        return opcion;
    }

    public void iniciarHud() {
        hud = new HUD(jugador);
    }

    @Override
    public void actualizar() {
        jugador.Actualizar();
        mapa.actualizar((int) jugador.getPosicionX(), (int) jugador.getPosicionY());
    }

    @Override
    public void dibujar(Graphics g) {
        mapa.dibujar(g, (int) jugador.getPosicionX(), (int) jugador.getPosicionY());
        jugador.dibujar(g);
        hud.dibujarHUD(g, jugador);

        DatosDebug.enviarDato("Jugador: X = " + jugador.getPosicionX() + " Y = " + jugador.getPosicionY());
        DatosDebug.enviarDato("Velocidad: " + jugador.getVelocidad());
        DatosDebug.enviarDato("Resistencia: " + jugador.getResistencia());
        DatosDebug.enviarDato("Vitalidad: " + jugador.getVitalidad());
        DatosDebug.enviarDato("Mana: " + jugador.getMana());
    }

    private void seleccionarPersonaje() {     //Temporal

    }

}
