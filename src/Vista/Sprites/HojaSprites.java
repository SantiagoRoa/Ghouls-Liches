/*
 * Autor: Nelson Santiago Roa Garzón
 * 23/03/2020 03:30:49 PM
 */
package Vista.Sprites;

import Modelo.Herramientas.CargadorRecursos;
import java.awt.image.BufferedImage;

public class HojaSprites {

    final private int anchoHojaPixeles;
    final private int altoHojaPixeles;

    final private int anchoHojaSprites;
    final private int altoHojaSprites;

    final private int anchoSprites;
    final private int altoSprites;

    //private BufferedImage imagen;
    private Sprite[] sprites;

    public HojaSprites(final String ruta, final int tamañoSprite, final boolean hojaOpaca) {
        final BufferedImage imagen;

        if (hojaOpaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
        }

        anchoHojaPixeles = imagen.getWidth();
        altoHojaPixeles = imagen.getHeight();

        anchoHojaSprites = anchoHojaPixeles / tamañoSprite;
        altoHojaSprites = altoHojaPixeles / tamañoSprite;

        anchoSprites = tamañoSprite;
        altoSprites = tamañoSprite;

        sprites = new Sprite[anchoHojaSprites * altoHojaSprites];

        rellenarSpritesImagen(imagen);
    }

    public HojaSprites(final String ruta, final int anchoSprite, final int altoSprite, final boolean hojaOpaca) {
        final BufferedImage imagen;

        if (hojaOpaca) {
            imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
        }

        anchoHojaPixeles = imagen.getWidth();
        altoHojaPixeles = imagen.getHeight();

        anchoHojaSprites = anchoHojaPixeles / anchoSprite;
        altoHojaSprites = altoHojaPixeles / altoSprite;

        this.anchoSprites = anchoSprite;
        this.altoSprites = altoSprite;

        sprites = new Sprite[anchoHojaSprites * altoHojaSprites];

        rellenarSpritesImagen(imagen);
    }

    private void rellenarSpritesImagen(final BufferedImage imagen) {
        for (int y = 0; y < altoHojaSprites; y++) {
            for (int x = 0; x < anchoHojaSprites; x++) {
                final int posicionX = x * anchoSprites;
                final int posicionY = y * altoSprites;

                sprites[x + y * anchoHojaSprites] = new Sprite(imagen.getSubimage(posicionX, posicionY, anchoSprites, altoSprites));
            }
        }
    }

    public Sprite getSprite(final int indice) {
        return sprites[indice];
    }

    public Sprite getSprite(final int x, final int y) {
        return sprites[x + y * anchoHojaSprites];
    }
}
