/*
 * Autor: Nelson Santiago Roa Garzón
 * 30/03/2020 02:45:52 AM
 */
package Modelo.MaquinaEstados.Estados.MenuPausa;

import Modelo.Herramientas.AdaptadorDibujo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class SeccionMenuPausa {

    protected final String nombreSeccion;
    protected final Rectangle etiquetaMenu;

    public SeccionMenuPausa(final String nombreSeccion, final Rectangle etiquetaMenu) {
        this.nombreSeccion = nombreSeccion;
        this.etiquetaMenu = etiquetaMenu;
    }

    public abstract void actualizar();

    public abstract void dibujar(final Graphics g);

    public void dibujarEtiquetaInactiva(final Graphics g) {
        AdaptadorDibujo.dibujarRectanguloRelleno(g, etiquetaMenu, Color.black);
        AdaptadorDibujo.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.BLACK);
    }

    public void dibujarEtiquetaActiva(final Graphics g) {
        AdaptadorDibujo.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        final Rectangle marcaActiva = new Rectangle(etiquetaMenu.x, etiquetaMenu.y, 5, etiquetaMenu.height);
        final Color colorActivo = new Color(0xff6700);
        AdaptadorDibujo.dibujarRectanguloRelleno(g, marcaActiva, colorActivo);

        AdaptadorDibujo.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.black);

    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

}
