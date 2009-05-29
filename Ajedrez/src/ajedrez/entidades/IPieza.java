/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.entidades;

import java.io.Serializable;

/**
 *
 * @author albertolemus
 */
public abstract class IPieza implements Serializable {

    protected String color;
    protected TransformarCaracter tc = new TransformarCaracter();

    public abstract boolean mover(char posX, int posY, char posX2, int posY2);

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    //letra-numero
    //<Piece> <Square> to|takes <Square>

}
