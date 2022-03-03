/*
 * Autor: Nelson Santiago Roa Garzón
 * 28/03/2020 01:26:46 AM
 */
package Modelo.Elementos.Personajes.Jugador;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Controlador.Configuracion;
import Modelo.Controles.GestorControles;
import Modelo.Elementos.Animacion;
import Modelo.Elementos.Mapa;
import Modelo.Herramientas.AdaptadorDibujo;
import Vista.Sprites.HojaSprites;
import java.awt.Rectangle;

public abstract class Jugador implements Animacion {

    protected String clase;
    protected Mapa mapa;
    protected HojaSprites hoja;
    protected BufferedImage sprite;

    protected Rectangle LIMITE_ARRIBA;
    protected Rectangle LIMITE_ABAJO;
    protected Rectangle LIMITE_IZQUIERDA;
    protected Rectangle LIMITE_DERECHA;

    protected double posicionX;
    protected double posicionY;
    protected double velocidad;
    protected double vigor;
    protected double mana;
    protected double manaMaximo;

    protected boolean guardado = false;
    protected boolean enMovimiento;
    protected boolean recuperado;
    protected boolean restaurado;
    protected boolean dañado;
    protected boolean usandoMagia;
    protected boolean vivo;

    protected int anchoPersonaje;
    protected int altoPersonaje;
    protected int posicionInicial;
    protected int animacion;
    protected int estado;
    protected int sentido;
    protected int nivel;
    protected int resistencia;
    protected int resistenciaMaxima;
    protected int recuperacionMaxima;
    protected int recuperacion;
    protected int restauracion;
    protected int restauracionMaxima;
    protected int tasaRestauracion;
    protected int vitalidad;
    protected int vitalidadMaxima;
    protected int tiempodeRegeneracion;

    public Jugador(double posicionX, double posicionY, Mapa mapa) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.mapa = mapa;

        vivo = true;
        recuperado = true;
        restaurado = true;
        enMovimiento = false;
        dañado = false;
        usandoMagia = false;

        hoja = new HojaSprites(Configuracion.RUTA_MOVIMIENTO_HEROE, Configuracion.LADO_SPRITE, false);

        if (!guardado) {
            resetearPersonaje();
        } else {
            cargarPersonaje();
        }

        this.resistencia = resistenciaMaxima;
        this.recuperacion = recuperacionMaxima;
        this.mana = manaMaximo;
        this.restauracion = restauracionMaxima;
        this.vitalidad = vitalidadMaxima;
    }

    public void cargarPersonaje() {
        this.vigor = vigor;
        this.tasaRestauracion = tasaRestauracion;
        this.manaMaximo = manaMaximo;
        this.resistenciaMaxima = resistenciaMaxima;
        this.vitalidadMaxima = vitalidadMaxima;
        this.restauracionMaxima = restauracionMaxima;
        this.recuperacionMaxima = recuperacionMaxima;
    }

    public abstract void resetearPersonaje();

    public void inicializarLimites(int anchoPersonaje, int altoPersonaje) {
        LIMITE_ARRIBA = new Rectangle(Configuracion.CENTRO_VENTANA_X - anchoPersonaje / 2,
                Configuracion.CENTRO_VENTANA_Y, anchoPersonaje, 1);
        LIMITE_ABAJO = new Rectangle(Configuracion.CENTRO_VENTANA_X - anchoPersonaje / 2,
                Configuracion.CENTRO_VENTANA_Y + altoPersonaje / 2, anchoPersonaje, 1);
        LIMITE_IZQUIERDA = new Rectangle(Configuracion.CENTRO_VENTANA_X - anchoPersonaje / 2,
                Configuracion.CENTRO_VENTANA_Y, 1, altoPersonaje / 2);
        LIMITE_DERECHA = new Rectangle(Configuracion.CENTRO_VENTANA_X + anchoPersonaje / 2,
                Configuracion.CENTRO_VENTANA_Y, 1, altoPersonaje / 2);
    }

    public void Actualizar() {
        actualizarVida();
        actualizarMana();
        mover();
    }

    @Override
    public void mover() {
        cambiarAnimacionEstado();
        enMovimiento = false;
        caminar();
        correr();
        animar();
    }

    @Override
    public void caminar() {
        final int velocidadX = evaluarVelocidadX();
        final int velocidadY = evaluarVelocidadY();

        if (velocidadX == 0 && velocidadY == 0) {
            return;
        }
        if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
            darPaso(velocidadX, velocidadY);
        } else {
            if (velocidadX == -1 && velocidadY == -1) {
                if (GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.arriba.getUltimaPulsacion()) {
                    darPaso(velocidadX, 0);
                } else {
                    darPaso(0, velocidadY);
                }
            }
        }
        if (velocidadX == -1 && velocidadY == 1) {
            if (GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.abajo.getUltimaPulsacion()) {
                darPaso(velocidadX, 0);
            } else {
                darPaso(0, velocidadY);
            }
        }
        if (velocidadX == 1 && velocidadY == -1) {
            if (GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.arriba.getUltimaPulsacion()) {
                darPaso(velocidadX, 0);
            } else {
                darPaso(0, velocidadY);
            }
        }
        if (velocidadX == 1 && velocidadY == 1) {
            if (GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.abajo.getUltimaPulsacion()) {
                darPaso(velocidadX, 0);
            } else {
                darPaso(0, velocidadY);
            }
        }
    }

    @Override
    public void darPaso(int velocidadX, int velocidadY) {
        enMovimiento = true;

        cambiarDireccion(velocidadX, velocidadY);
        detectarFueraMapa(velocidadX, velocidadY);

    }

    public void seguirCaminando(int id, int rapidez) {
        switch (id) {
            case 1:
                posicionX += velocidad * rapidez;
                restarResistencia();
                break;
            case 2:
                posicionY += velocidad * rapidez;
                restarResistencia();
        }
    }

    public void detectarFueraMapa(int velocidadX, int velocidadY) {
        if (!fueraMapa(velocidadX, velocidadY)) {
            if ((velocidadX == -1 && !enColisionIzquierda(velocidadX)) || (velocidadX == 1 && !enColisionDerecha(velocidadX))) {
                seguirCaminando(1, velocidadX);
            }
            if ((velocidadY == -1 && !enColisionArriba(velocidadY)) || (velocidadY == 1 && !enColisionAbajo(velocidadY))) {
                seguirCaminando(2, velocidadY);
            }

        }
    }

    public void restarResistencia() {
        if (GestorControles.teclado.corriendo && resistencia > 0) {
            resistencia--;
        }
    }

    public void actualizarVida() {
        if (vitalidad > 0) {
            if (dañado) {
                vitalidad -= 50;
            }
        } else {
            vivo = false;
        }
    }

    public void actualizarMana() {
        if (GestorControles.teclado.usandoMagia && mana >= 2) {
            usandoMagia = false;
            if (GestorControles.teclado.usandoMagia) {
                usarMagia();
            }
        } else {
            restaurar();
        }
    }

    public void usarMagia() {
        if (mana > 0) {
            mana = mana - 2;
            restaurado = false;
            restauracion = 0;
        } else {
            return;
        }
    }

    public void correr() {
        if (GestorControles.teclado.corriendo && resistencia > 0) {
            esprintar();
        } else {
            recuperarse();
        }
    }

    public void esprintar() {
        if (resistencia > 0) {
            velocidad = vigor * 2;
            recuperado = false;
            recuperacion = 0;
        } else {
            return;
        }
    }

    public void restaurar() {
        if (!restaurado && restauracion < restauracionMaxima) {
            restauracion++;
        }
        if (restauracion == restauracionMaxima && mana < manaMaximo) {
            mana = mana + 0.2 * tasaRestauracion;
        }
    }

    public void recuperarse() {
        velocidad = vigor;
        if (!recuperado && recuperacion < recuperacionMaxima) {
            recuperacion++;
        }
        if (recuperacion == recuperacionMaxima && resistencia < resistenciaMaxima) {
            resistencia++;
        }
    }

    @Override
    public int evaluarVelocidadX() {
        int velocidadX = 0;

        if (GestorControles.teclado.izquierda.isPulsada() && !GestorControles.teclado.derecha.isPulsada()) {
            velocidadX = -1;
        } else if (GestorControles.teclado.derecha.isPulsada() && !GestorControles.teclado.izquierda.isPulsada()) {
            velocidadX = 1;
        }

        return velocidadX;
    }

    @Override
    public int evaluarVelocidadY() {
        int velocidadY = 0;

        if (GestorControles.teclado.arriba.isPulsada() && !GestorControles.teclado.abajo.isPulsada()) {
            velocidadY = -1;
        } else if (GestorControles.teclado.abajo.isPulsada() && !GestorControles.teclado.arriba.isPulsada()) {
            velocidadY = 1;
        }

        return velocidadY;
    }

    @Override
    public void animar() {
        if (!enMovimiento) {
            estado = posicionInicial;
        }
        sprite = hoja.getSprite(estado, sentido).getImagen();
    }

    @Override
    public void dibujar(Graphics g) {
        final int centroX = Configuracion.CENTRO_VENTANA_X - Configuracion.CENTRO_SPRITE;
        final int centroY = Configuracion.CENTRO_VENTANA_Y - Configuracion.CENTRO_SPRITE;

        AdaptadorDibujo.dibujarImagen(g, sprite, centroX, centroY);

        //Debugging: HitBox personaje
        /*
        g.setColor(Color.red);
        g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y, LIMITE_ARRIBA.width, LIMITE_ARRIBA.height);
        g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y, LIMITE_ABAJO.width, LIMITE_ABAJO.height);
        g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y, LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height);
        g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y, LIMITE_DERECHA.width, LIMITE_DERECHA.height);
         */
    }

    private boolean fueraMapa(final int velocidadX, final int velocidadY) {

        int posicionFuturaX = (int) posicionX + velocidadX * (int) velocidad;
        int posicionFuturaY = (int) posicionY + velocidadY * (int) velocidad;

        final Rectangle bordesMapa = mapa.getBordes(posicionFuturaX, posicionFuturaY, anchoPersonaje, altoPersonaje / 2);

        return detectarColision(bordesMapa);

    }

    private boolean detectarColision(Rectangle bordesMapa) {
        final boolean fuera;

        if (LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa)
                || LIMITE_IZQUIERDA.intersects(bordesMapa) || LIMITE_DERECHA.intersects(bordesMapa)) {
            fuera = false;
        } else {
            fuera = true;
        }

        return fuera;
    }

    private boolean enColisionArriba(int velocidadY) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            final Rectangle area = mapa.areasColision.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad + 2 * (int) velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Configuracion.LADO_SPRITE, Configuracion.LADO_SPRITE);

            if (LIMITE_ARRIBA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean enColisionAbajo(int velocidadY) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            final Rectangle area = mapa.areasColision.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad - 2 * (int) velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Configuracion.LADO_SPRITE, Configuracion.LADO_SPRITE);

            if (LIMITE_ABAJO.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean enColisionIzquierda(int velocidadX) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            final Rectangle area = mapa.areasColision.get(r);

            int origenX = area.x + velocidadX * (int) velocidad + 2 * (int) velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Configuracion.LADO_SPRITE, Configuracion.LADO_SPRITE);

            if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean enColisionDerecha(int velocidadX) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            final Rectangle area = mapa.areasColision.get(r);

            int origenX = area.x + velocidadX * (int) velocidad - 2 * (int) velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Configuracion.LADO_SPRITE, Configuracion.LADO_SPRITE);

            if (LIMITE_DERECHA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public double getVigor() {
        return vigor;
    }

    public boolean isVivo() {
        return vivo;
    }

    public int getAnchoPersonaje() {
        return anchoPersonaje;
    }

    public int getAltoPersonaje() {
        return altoPersonaje;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getVitalidad() {
        return this.vitalidad;
    }

    public double getMana() {
        return mana;
    }

    public int getResistenciaMaxima() {
        return resistenciaMaxima;
    }

    public int getVitalidadMaxima() {
        return vitalidadMaxima;
    }

    public double getManaMaximo() {
        return manaMaximo;
    }

}
