/*
 * Autor: Nelson Santiago Roa Garzón
 * 9/03/2020 06:45:41 PM
 */
package Vista;

import Controlador.Configuracion;
import Controlador.Juego;
import Modelo.Controles.GestorControles;
import Modelo.Controles.Raton;
import Modelo.Herramientas.DatosDebug;
import Modelo.Herramientas.AdaptadorDibujo;
import Modelo.MaquinaEstados.GestorEstados;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class Pantalla extends Canvas {

    private static final long serialVersionUID = 164331L;

    private int ancho;
    private int alto;

    private Raton raton;

    public Pantalla(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;

        this.raton = Raton.getInstancia(this);

        setIgnoreRepaint(true);
        setCursor(raton.getCursor());
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(GestorControles.teclado);
        addMouseListener(raton);
        setFocusable(true);
        requestFocus();
    }

    public void actualizar() {
        raton.actualizar(this);
    }

    public void dibujar(final GestorEstados ge) {
        final BufferStrategy buffer = getBufferStrategy();

        if (buffer == null) {
            createBufferStrategy(3);
            return;
        }

        final Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

        AdaptadorDibujo.reiniciarObjetosDibujados();

        AdaptadorDibujo.dibujarRectanguloRelleno(g, 0, 0, Configuracion.ANCHO_PANTALLA_COMPLETA, Configuracion.ALTO_PANTALLA_COMPLETA, Color.BLACK);

        if (Configuracion.FACTOR_ESCALADO_X != 1.0 || Configuracion.FACTOR_ESCALADO_Y != 1.0) {
            g.scale(Configuracion.FACTOR_ESCALADO_X, Configuracion.FACTOR_ESCALADO_Y);
        }

        DatosDebug.enviarDato("FPS: " + Juego.contador_FPS);
        DatosDebug.enviarDato("APS: " + Juego.contador_APS + " Hz");
        DatosDebug.enviarDato("Mouse: X = " + raton.getPosicion().getX() + " Y = " + raton.getPosicion().getY());

        ge.dibujar(g);

        DatosDebug.enviarDato("Objetos en pantalla: " + AdaptadorDibujo.getObjetosDibujados());

        if (GestorControles.teclado.debug) {
            DatosDebug.dibujarDatos(g);
        } else {
            DatosDebug.vaciarDatos();
        }

        Toolkit.getDefaultToolkit().sync();

        g.dispose();

        buffer.show();
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
