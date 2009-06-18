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
public class Caballo extends IPieza{

    public String toString()
    {
        return " C["+color.charAt(0)+"] ";
    }

    public boolean mover(char posX, int posY, char posX2, int posY2) {
        PosChar posx = tc.transformarCaracter(posX);
        PosChar posx2 = tc.transformarCaracter(posX2);
        if ((posx!=null)&&(posx2!=null))
        {
            if (((posx2.ordinal() == posx.ordinal()+2)||(posx2.ordinal() == posx.ordinal()-2)) &&
                    ((posY2==(posY+1))||(posY2==(posY-1))))
            {
                return true;
            }
            if (((posx2.ordinal() == posx.ordinal()+1)||(posx2.ordinal() == posx.ordinal()-1)) &&
                    ((posY2==(posY+2))||(posY2==(posY-2))))
            {
                return true;
            }

        }
        return false;
    }
}
