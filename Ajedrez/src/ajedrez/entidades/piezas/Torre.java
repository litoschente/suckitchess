/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.entidades.piezas;

import ajedrez.entidades.IPieza;
import ajedrez.entidades.PosChar;

/**
 *
 * @author albertolemus
 */
public class Torre extends IPieza {

    public Torre(String color) {
        setColor(color);
    imagen="src/recursos/icons/torre"+color+".gif";
    }


    public String toString()
    {
        return "T_["+color.charAt(0)+"]";
    }

    public boolean mover(char posX, int posY, char posX2, int posY2) {
        PosChar posx = tc.transformarCaracter(posX);
        PosChar posx2 = tc.transformarCaracter(posX2);
        if ((posx!=null)&&(posx2!=null))
        {
            if ((posx.equals(posx2))&&(posY2!=posY))
            {
                return true;
            }
            if ((!(posx.equals(posx2)))&&(posY2==posY))
            {
                return true;
            }
        }
        return false;
    }
}
