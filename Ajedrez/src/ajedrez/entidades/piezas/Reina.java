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
public class Reina extends IPieza{

    public Reina(String color) {
                setColor(color);
        imagen="src/recursos/icons/reina"+color+".gif";
    }



    public String toString()
    {
        return "Ra["+color.charAt(0)+"]";
    }

    public boolean mover(char posX, int posY, char posX2, int posY2) {
        PosChar posx = tc.transformarCaracter(posX);
        PosChar posx2 = tc.transformarCaracter(posX2);
        int dx = posx2.ordinal() - posx.ordinal();
        int dy = posY2 - posY;
        if ((posx!=null)&&(posx2!=null))
        {
            if (Math.abs(dy)==Math.abs(dx))
            {
                return true;
            }
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
