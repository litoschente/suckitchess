/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.entidades.piezas;

import ajedrez.entidades.*;

/**
 *
 * @author albertolemus
 */
public class Peon extends IPieza{

    public String toString()
    {
        return " P."+color.charAt(0);
    }

    public boolean mover(char posX, int posY, char posX2, int posY2) {
        PosChar posx = tc.transformarCaracter(posX);
        PosChar posx2 = tc.transformarCaracter(posX2);
        if ((posx!=null)&&(posx2!=null))
        {
            if (color.equals("blanco"))
            {
                if ((posx.equals(posx2))&&(posY2==(posY-1)))
                {
                    return true;
                }
            }else if(color.equals("negro"))
            {
                if ((posx.equals(posx2))&&(posY2==(posY+1)))
                {
                    return true;
                }
            }
        }
        return false;
    }

}
