/*
 * Autor: Nelson Santiago Roa Garzón
 * 29/03/2020 11:28:38 AM
 */
package Vista.InterfazUsuario;

import Controlador.Configuracion;
import Modelo.Controles.Raton;
import Modelo.Elementos.Personajes.Jugador.Jugador;
import Modelo.Herramientas.CargadorRecursos;
import Modelo.Herramientas.AdaptadorDibujo;
import Vista.Pantalla;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class EstructuraMenuPausa {

    Font fuenteOverlord = Configuracion.FUENTE_OVERLORD;
    Pantalla pantalla;
    Raton raton = Raton.getInstancia(pantalla);

    public final Color COLOR_PANEL_LATERAL;
    public final Color COLOR_PANEL_CENTRAL;

    public final Rectangle PANEL_LATERAL;
    public final Rectangle PANEL_CENTRAL;

    private Rectangle BOTON_RESUMIR;
    private Rectangle BOTON_AYUDA;
    private Rectangle BOTON_GUARDAR;
    private Rectangle BOTON_VOLVER_MENU_INICIO;
    private Rectangle BOTON_SALIR;

    public Rectangle BOTON_RESUMIR_HITBOX;
    public Rectangle BOTON_AYUDA_HITBOX;
    public Rectangle BOTON_GUARDAR_HITBOX;
    public Rectangle BOTON_VOLVER_MENU_INICIO_HITBOX;
    public Rectangle BOTON_SALIR_HITBOX;

    private Point PUNTO_BOTON_RESUMIR;
    private Point PUNTO_BOTON_AYUDA;
    private Point PUNTO_BOTON_GUARDAR;
    private Point PUNTO_BOTON_VOLVER_MENU_INICIO;
    private Point PUNTO_BOTON_SALIR;

    private final int divisionHorizontal;
    private final int altoMenu;
    private final int anchoMenu;
    private final int divisionParteAnchaBoton;
    private final int divisionParteAltaBoton;

    CargadorRecursos cargar;

    public EstructuraMenuPausa(Jugador jugador, Pantalla pantalla) {
        this.pantalla = pantalla;
        anchoMenu = Configuracion.RESOLUCION_HORIZONTAL;
        altoMenu = Configuracion.RESOLUCION_VERTICAL;

        divisionHorizontal = anchoMenu - anchoMenu / 3;
        divisionParteAnchaBoton = (anchoMenu - divisionHorizontal) / 4;
        divisionParteAltaBoton = (altoMenu) / 64;

        PANEL_LATERAL = new Rectangle(0, 0, divisionHorizontal, altoMenu);
        PANEL_CENTRAL = new Rectangle(divisionHorizontal, 0, anchoMenu / 3, altoMenu);

        COLOR_PANEL_CENTRAL = Color.lightGray;//new Color(97, 92, 92);
        COLOR_PANEL_LATERAL = new Color(25, 25, 25);

        fuenteOverlord = fuenteOverlord.deriveFont(56f);

        inicializarBotones();

    }

    public void detectarBotonOprimido(Raton raton) {

        if ((raton.getPosicion().x >= BOTON_RESUMIR_HITBOX.x) && (raton.getPosicion().x
                <= BOTON_RESUMIR_HITBOX.x + BOTON_RESUMIR_HITBOX.width)) {

            if ((raton.getPosicion().y >= BOTON_RESUMIR_HITBOX.y) && (raton.getPosicion().y
                    <= BOTON_RESUMIR_HITBOX.y + BOTON_RESUMIR_HITBOX.height)) {
                //System.exit(0);

            }

        }

    }

    public void actualizar() {
        detectarBotonOprimido(raton);
    }

    public void inicializarBotones() {

        BOTON_RESUMIR = new Rectangle();
        BOTON_RESUMIR.setSize(divisionParteAnchaBoton * 2, divisionParteAltaBoton * 5);
        BOTON_RESUMIR.setLocation(divisionHorizontal + divisionParteAnchaBoton, divisionParteAltaBoton * 15);
        BOTON_RESUMIR_HITBOX = new Rectangle(BOTON_RESUMIR.x, BOTON_RESUMIR.y,
                BOTON_RESUMIR.width, BOTON_RESUMIR.height);
        PUNTO_BOTON_RESUMIR = new Point(BOTON_RESUMIR.x, BOTON_RESUMIR.y + BOTON_RESUMIR.height);

        BOTON_AYUDA = new Rectangle();
        BOTON_AYUDA.setSize(divisionParteAnchaBoton * 2, divisionParteAltaBoton * 5);
        BOTON_AYUDA.setLocation(divisionHorizontal + divisionParteAnchaBoton, divisionParteAltaBoton * 24);
        BOTON_AYUDA_HITBOX = new Rectangle(BOTON_AYUDA.x, BOTON_AYUDA.y,
                BOTON_AYUDA.width, BOTON_AYUDA.height);
        PUNTO_BOTON_AYUDA = new Point(BOTON_AYUDA.x, BOTON_AYUDA.y + BOTON_AYUDA.height);

        BOTON_GUARDAR = new Rectangle();
        BOTON_GUARDAR.setSize(divisionParteAnchaBoton * 2, divisionParteAltaBoton * 5);
        BOTON_GUARDAR.setLocation(divisionHorizontal + divisionParteAnchaBoton, divisionParteAltaBoton * 33);
        BOTON_GUARDAR_HITBOX = new Rectangle(BOTON_GUARDAR.x, BOTON_GUARDAR.y,
                BOTON_GUARDAR.width, BOTON_GUARDAR.height);
        PUNTO_BOTON_GUARDAR = new Point(BOTON_GUARDAR.x, BOTON_GUARDAR.y + BOTON_GUARDAR.height);

        BOTON_VOLVER_MENU_INICIO = new Rectangle();
        BOTON_VOLVER_MENU_INICIO.setSize(divisionParteAnchaBoton * 2, divisionParteAltaBoton * 5);
        BOTON_VOLVER_MENU_INICIO.setLocation(divisionHorizontal + divisionParteAnchaBoton, divisionParteAltaBoton * 42);
        BOTON_VOLVER_MENU_INICIO_HITBOX = new Rectangle(BOTON_VOLVER_MENU_INICIO.x, BOTON_VOLVER_MENU_INICIO.y,
                BOTON_VOLVER_MENU_INICIO.width, BOTON_VOLVER_MENU_INICIO.height);
        PUNTO_BOTON_VOLVER_MENU_INICIO = new Point(BOTON_VOLVER_MENU_INICIO.x, BOTON_VOLVER_MENU_INICIO.y + BOTON_VOLVER_MENU_INICIO.height);

        BOTON_SALIR = new Rectangle();
        BOTON_SALIR.setSize(divisionParteAnchaBoton * 2, divisionParteAltaBoton * 5);
        BOTON_SALIR.setLocation(divisionHorizontal + divisionParteAnchaBoton, divisionParteAltaBoton * 51);
        BOTON_SALIR_HITBOX = new Rectangle(BOTON_SALIR.x, BOTON_SALIR.y,
                BOTON_SALIR.width, BOTON_SALIR.height);
        PUNTO_BOTON_SALIR = new Point(BOTON_SALIR.x, BOTON_SALIR.y + BOTON_SALIR.height);

    }

    public void dibujar(Graphics g, Jugador jugador) {
        dibujarPaneles(g);
        dibujarTexto(g, jugador);
        //dibujarHitBoxBotones(g); // Debug
    }

    public void dibujarPaneles(Graphics g) {
        dibujarPanelLateral(g);
        dibujarPanelCentral(g);
    }

    public void dibujarPanelLateral(Graphics g) {
        AdaptadorDibujo.dibujarRectanguloRelleno(g, PANEL_LATERAL, COLOR_PANEL_LATERAL);
    }

    public void dibujarPanelCentral(Graphics g) {
        AdaptadorDibujo.dibujarRectanguloRelleno(g, PANEL_CENTRAL, COLOR_PANEL_CENTRAL);
        g.setColor(Color.white);
        AdaptadorDibujo.dibujarRectanguloRelleno(g, divisionHorizontal - 2, 0, 8, altoMenu);
    }

    public void dibujarTexto(Graphics g, Jugador jugador) {

        int x = (anchoMenu - divisionHorizontal) / 32 * 12;
        int y = this.altoMenu / 512 * 80;
        int z = anchoMenu - divisionHorizontal + (divisionHorizontal / 32) * 19;

        g.setFont(fuenteOverlord);
        g.setColor(Color.LIGHT_GRAY);
        AdaptadorDibujo.dibujarString(g, "Estadísticas del jugador", x, y);
        g.setColor(COLOR_PANEL_LATERAL);
        AdaptadorDibujo.dibujarString(g, "Menu de pausa", z, y);
        dibujarTextoBotones(g);

    }

    public void dibujarTextoBotones(Graphics g) {
        AdaptadorDibujo.dibujarString(g, "Resumir", PUNTO_BOTON_RESUMIR);
        AdaptadorDibujo.dibujarString(g, "Ayuda", PUNTO_BOTON_AYUDA);
        AdaptadorDibujo.dibujarString(g, "Guardar", PUNTO_BOTON_GUARDAR);
        AdaptadorDibujo.dibujarString(g, "Inicio", PUNTO_BOTON_VOLVER_MENU_INICIO);
        AdaptadorDibujo.dibujarString(g, "Salir", PUNTO_BOTON_SALIR);
    }

    public void dibujarHitBoxBotones(Graphics g) {
        g.setColor(Color.RED);
        AdaptadorDibujo.dibujarRectanguloContorno(g, BOTON_RESUMIR);
        AdaptadorDibujo.dibujarRectanguloContorno(g, BOTON_AYUDA);
        AdaptadorDibujo.dibujarRectanguloContorno(g, BOTON_GUARDAR);
        AdaptadorDibujo.dibujarRectanguloContorno(g, BOTON_VOLVER_MENU_INICIO);
        AdaptadorDibujo.dibujarRectanguloContorno(g, BOTON_SALIR);

    }

    public void dibujarAtributos() {

    }

}
