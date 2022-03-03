/*
 * Autor: Nelson Santiago Roa Garzón
 * 28/03/2020 11:17:46 PM
 */
package Vista.InterfazUsuario;

import Controlador.Configuracion;
import Modelo.Elementos.Personajes.Jugador.Jugador;
import Modelo.Herramientas.AdaptadorDibujo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HUD {

    private Rectangle areaHUD;
    private Rectangle bordeAreaHUD;

    private Color grisOscuro;
    private Color grisClaro;
    private Color amarilloOscuro;
    private Color rojoOscuro;
    private Color azulOscuro;

    public HUD(Jugador jugador) {
        int altoHUD = 128;
        areaHUD = new Rectangle(0, Configuracion.RESOLUCION_VERTICAL - altoHUD, Configuracion.RESOLUCION_HORIZONTAL, altoHUD);
        bordeAreaHUD = new Rectangle(areaHUD.x, areaHUD.y - 1, areaHUD.width, 1);
        grisOscuro = new Color(25, 25, 25);
        grisClaro = new Color(97, 92, 92);
        amarilloOscuro = new Color(207, 179, 39);
        rojoOscuro = new Color(128, 10, 10);
        azulOscuro = new Color(12, 8, 120);
    }

    public void dibujarHUD(Graphics g, Jugador jugador) {
        dibujarAreaHUD(g);
        dibujarBarras(g, jugador);
        dibujarTexto(g);
    }

    private void dibujarAreaHUD(final Graphics g) {
        AdaptadorDibujo.dibujarRectanguloRelleno(g, areaHUD, grisOscuro);
        AdaptadorDibujo.dibujarRectanguloRelleno(g, bordeAreaHUD, grisClaro);
    }

    private void dibujarTexto(Graphics g) {
        Font fuente = Configuracion.FUENTE_OVERLORD;
        fuente = fuente.deriveFont(24f);
        g.setFont(fuente);

        AdaptadorDibujo.dibujarString(g, "Vitalidad", 40, 625, Color.white);
        AdaptadorDibujo.dibujarString(g, "Maná", 40, 660, Color.white);
        AdaptadorDibujo.dibujarString(g, "Energía", 40, 695, Color.white);
    }

    private void dibujarBarras(Graphics g, Jugador jugador) {
        dibujarBarraResistencia(g, jugador);
        dibujarBarraMP(g, jugador);
        dibujarBarraHP(g, jugador);

    }

    private void dibujarBarraResistencia(Graphics g, Jugador jugador) {
        final int anchoBarra = 300;
        final int altoBarra = 15;
        final int ubicacionX = 130;
        final int ubicacionY = 680;
        final Color colorBarra = Color.yellow;
        final Color colorFondo = amarilloOscuro;

        int largoBarra = anchoBarra * jugador.getResistencia() / jugador.getResistenciaMaxima();

        dibujarBarra(g, colorBarra, colorFondo, largoBarra, anchoBarra, altoBarra, ubicacionX, ubicacionY);

    }

    private void dibujarBarraHP(Graphics g, Jugador jugador) {
        final int anchoBarra = 500;
        final int altoBarra = 15;
        final int ubicacionX = 130;
        final int ubicacionY = 610;
        final Color colorBarra = Color.red;
        final Color colorFondo = rojoOscuro;

        int largoBarra = anchoBarra * jugador.getVitalidad() / jugador.getVitalidadMaxima();

        dibujarBarra(g, colorBarra, colorFondo, largoBarra, anchoBarra, altoBarra, ubicacionX, ubicacionY);

    }

    private void dibujarBarraMP(Graphics g, Jugador jugador) {
        final int anchoBarra = 200;
        final int altoBarra = 15;
        final int ubicacionX = 130;
        final int ubicacionY = 645;
        final Color colorBarra = Color.BLUE;
        final Color colorFondo = azulOscuro;

        int largoBarra = anchoBarra * (int) jugador.getMana() / (int) jugador.getManaMaximo();

        dibujarBarra(g, colorBarra, colorFondo, largoBarra, anchoBarra, altoBarra, ubicacionX, ubicacionY);

    }

    private void dibujarBarra(final Graphics g, final Color colorBarra, final Color colorFondo, final int largoBarra, final int anchoBarra, final int altoBarra, final int ubicacionX, final int ubicacionY) {
        AdaptadorDibujo.dibujarRectanguloContorno(g, ubicacionX - 1, ubicacionY - 1, anchoBarra + 1, altoBarra + 1, grisClaro);
        AdaptadorDibujo.dibujarRectanguloRelleno(g, ubicacionX, ubicacionY, anchoBarra, altoBarra, colorFondo);
        AdaptadorDibujo.dibujarRectanguloRelleno(g, ubicacionX, ubicacionY, largoBarra, altoBarra, colorBarra);
    }
}
