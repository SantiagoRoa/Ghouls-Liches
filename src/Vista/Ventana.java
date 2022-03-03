/*
 * Autor: Nelson Santiago Roa Garzón
 * 9/03/2020 06:47:00 PM
 */
package Vista;

import Controlador.Configuracion;
import Modelo.Herramientas.CargadorRecursos;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Ventana extends JFrame {

    public static final long serialVersionUID = 35463L;

    private String titulo;
    private final ImageIcon icono;

    public Ventana(final String titulo, final Pantalla lienzo) {
        this.titulo = titulo;

        BufferedImage imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(Configuracion.RUTA_ICONO_VENTANA);
        this.icono = new ImageIcon(imagen);

        configurarVentana(lienzo);
    }

    private void configurarVentana(final Pantalla lienzo) {
        setTitle(titulo);
        setIconImage(icono.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        add(lienzo, BorderLayout.CENTER);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
