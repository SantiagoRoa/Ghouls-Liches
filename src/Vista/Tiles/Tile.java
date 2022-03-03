/*
 * Autor: Nelson Santiago Roa Garzón
 * 23/03/2020 02:50:51 PM
 */
package Vista.Tiles;

import Vista.Sprites.Sprite;
import java.awt.Rectangle;

public class Tile {

    private final Sprite sprite;
    private final int id;
    private boolean colicionable;

    public Tile(final Sprite sprite, final int id) {
        this.sprite = sprite;
        this.id = id;
        colicionable = false;
    }

    public Tile(final Sprite sprite, final int id, final boolean colicionable) {
        this.sprite = sprite;
        this.id = id;
        this.colicionable = colicionable;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }

    public void setColicionable(final boolean colicionable) {
        this.colicionable = colicionable;
    }

    public Rectangle getLimits(final int x, final int y) {
        return new Rectangle(x, y, sprite.getAncho(), sprite.getAlto());
    }
}
