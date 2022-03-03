/*
 * Autor: Nelson Santiago Roa Garzón
 * 26/03/2020 05:56:08 PM
 */
package Modelo.Elementos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Controlador.Configuracion;
import Modelo.Herramientas.CargadorRecursos;
import Modelo.Herramientas.AdaptadorDibujo;
import Vista.Sprites.HojaSprites;
import Vista.Sprites.Sprite;
import java.awt.Rectangle;

public class Mapa {

    private String[] partes;

    private final int ancho;
    private final int alto;

    private final Sprite[] paleta;

    private final boolean[] colisiones;

    private static Mapa instancia;

    public ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();

    private final int[] sprites;

    private final int MARGEN_X = Configuracion.RESOLUCION_HORIZONTAL / 2 - Configuracion.LADO_SPRITE / 2;
    private final int MARGEN_Y = Configuracion.RESOLUCION_VERTICAL / 2 - Configuracion.LADO_SPRITE / 2;

    private Mapa(final String ruta) {

        String contenido = CargadorRecursos.leerArchivoTexto(ruta);

        partes = contenido.split("\\*");

        ancho = Integer.parseInt(partes[0]);
        alto = Integer.parseInt(partes[1]);

        String hojasUtilizadas = partes[2];
        String[] hojasSeparadas = hojasUtilizadas.split(",");

        String paletaEntera = partes[3];
        String[] partesPaleta = paletaEntera.split("#");

        paleta = asignarSprites(partesPaleta, hojasSeparadas);

        String colisionesEnteras = partes[4];
        colisiones = extraerColisiones(colisionesEnteras);

        String spritesEnteros = partes[5];
        String[] cadenasSprites = spritesEnteros.split(" ");

        sprites = extraerSprites(cadenasSprites);

    }

    public static Mapa getInstance() {
        if (instancia == null) {
            return new Mapa(Configuracion.RUTA_MAPA_1);
        }
        return instancia;
    }

    private Sprite[] asignarSprites(final String[] partesPaleta, final String[] hojasSeparadas) {
        Sprite[] paleta = new Sprite[partesPaleta.length];

        HojaSprites hoja = new HojaSprites("/Recursos/Tiles/" + hojasSeparadas[0] + ".png", 64, true);

        for (int i = 0; i < partesPaleta.length; i++) {
            String spriteTemporal = partesPaleta[i];
            String[] partesSprite = spriteTemporal.split("-");
            int indicePaleta = Integer.parseInt(partesSprite[0]);
            int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);

            paleta[indicePaleta] = hoja.getSprite(indiceSpriteHoja);
        }

        return paleta;
    }

    private boolean[] extraerColisiones(final String cadenaColisiones) {
        boolean[] colisiones = new boolean[cadenaColisiones.length()];

        for (int i = 0; i < cadenaColisiones.length(); i++) {
            if (cadenaColisiones.charAt(i) == '0') {
                colisiones[i] = true;
            } else {
                colisiones[i] = false;
            }
        }
        return colisiones;
    }

    private int[] extraerSprites(final String[] cadenaSprites) {
        ArrayList<Integer> sprites = new ArrayList<Integer>();

        for (int i = 0; i < cadenaSprites.length; i++) {
            if (cadenaSprites[i].length() == 2) {
                sprites.add(Integer.parseInt(cadenaSprites[i]));
            } else {
                String uno = "";
                String dos = "";

                String error = cadenaSprites[i];

                uno += error.charAt(0);
                uno += error.charAt(1);

                dos += error.charAt(2);
                dos += error.charAt(3);

                sprites.add(Integer.parseInt(uno));
                sprites.add(Integer.parseInt(dos));

            }
        }

        int[] vectorSprites = new int[sprites.size()];

        for (int i = 0; i < sprites.size(); i++) {
            vectorSprites[i] = sprites.get(i);
        }

        return vectorSprites;
    }

    public void actualizar(final int posicionX, final int posicionY) {
        actualizarAreasColision(posicionX, posicionY);
    }

    private void actualizarAreasColision(int posicionX, int posicionY) {
        if (!areasColision.isEmpty()) {
            areasColision.clear();
        }

        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                int puntoX = x * Configuracion.LADO_SPRITE - posicionX + MARGEN_X;
                int puntoY = y * Configuracion.LADO_SPRITE - posicionY + MARGEN_Y;

                if (colisiones[x + y * this.ancho]) {
                    final Rectangle r = new Rectangle(puntoX, puntoY, Configuracion.LADO_SPRITE, Configuracion.LADO_SPRITE);
                    areasColision.add(r);
                }
            }
        }
    }

    public void dibujar(Graphics g, final int posicionX, final int posicionY) {

        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                BufferedImage imagen = paleta[sprites[x + y * this.ancho]].getImagen();

                int puntoX = x * Configuracion.LADO_SPRITE - posicionX + MARGEN_X;
                int puntoY = y * Configuracion.LADO_SPRITE - posicionY + MARGEN_Y;

                AdaptadorDibujo.dibujarImagen(g, imagen, puntoX, puntoY);

                //Debugging: HitBox tiles (precaución: Pérdida severa de rendimiento)
                /*
                g.setColor(Color.yellow);
                for (int r = 0; r < areasColision.size(); r++) {
                    Rectangle area = areasColision.get(r);
                    g.drawRect(area.x, area.y, area.width, area.height);
                }
                 */
            }
        }
    }

    public Rectangle getBordes(final int posicionX, final int posicionY, int anchoJugador, int altoJugador) {

        int x = MARGEN_X - posicionX + anchoJugador;
        int y = MARGEN_Y - posicionY + altoJugador;
        int ancho = this.ancho * Configuracion.LADO_SPRITE - anchoJugador * 2;
        int alto = this.alto * Configuracion.LADO_SPRITE - altoJugador * 2;

        return new Rectangle(x, y, ancho, alto);
    }

}
