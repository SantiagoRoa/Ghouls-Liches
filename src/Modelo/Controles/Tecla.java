/*
 * Autor: Nelson Santiago Roa Garzón
 * 28/03/2020 12:38:15 PM
 */
package Modelo.Controles;

public class Tecla {

    private boolean pulsada = false;
    private long ultimaPulsacion = System.nanoTime();

    public void teclaPulsada() {
        pulsada = true;
        ultimaPulsacion = System.nanoTime();
    }

    public void teclaLiberada() {
        pulsada = false;
    }

    public boolean isPulsada() {
        return pulsada;
    }

    public long getUltimaPulsacion() {
        return ultimaPulsacion;
    }

}
