/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.entidades;

import ajedrez.entidades.piezas.*;
import java.io.Serializable;

/**
 *
 * @author albertolemus
 */
public class Tablero implements Serializable{

    private final int TAMANO_TABLERO = 8;

    IPieza grid[][] = new IPieza[TAMANO_TABLERO][TAMANO_TABLERO];
    private TransformarCaracter tc = new TransformarCaracter();


    public Tablero()
    {
        grid[0][0]=new Torre("negro");
        grid[0][1]=new Caballo("negro");
        grid[0][2]=new Alfil("negro");
        grid[0][3]=new Reina("negro");
        grid[0][4]=new Rey("negro");
        grid[0][5]=new Alfil("negro");
        grid[0][6]=new Caballo("negro");
        grid[0][7]=new Torre("negro");
        for (int i = 0; i < grid.length; i++) {
            grid[1][i]=new Peon("negro");
            grid[6][i]=new Peon("blanco");
        }
        grid[7][0]=new Torre("blanco");
        grid[7][1]=new Caballo("blanco");
        grid[7][2]=new Alfil("blanco");
        grid[7][3]=new Reina("blanco");
        grid[7][4]=new Rey("blanco");
        grid[7][5]=new Alfil("blanco");
        grid[7][6]=new Caballo("blanco");
        grid[7][7]=new Torre("blanco");
    }

    public boolean mover(char posY, int posX, char posY2, int posX2, String turno) {
        PosChar posy = tc.transformarCaracter(posY);
        PosChar posy2 = tc.transformarCaracter(posY2);
        if (grid[8-posX][posy.ordinal()]!=null)
        {
            if (grid[8-posX][posy.ordinal()].getColor().equals(turno))
            {
                if (grid[8-posX][posy.ordinal()].mover(posY, posX, posY2, posX2))
                {
                    grid[8-posX2][posy2.ordinal()]=grid[8-posX][posy.ordinal()];
                    grid[8-posX][posy.ordinal()]=null;
                    System.out.println(grid[8-posX2][posy2.ordinal()].toString()+": "+posY+""+posX+" -> "+posY2+""+posX2);
                    return true;
                }
            }
        }
        return false;
    }



    public String toString()
    {
        String ret="";
        for (int i = 0; i < grid.length; i++) {
            ret+="    ----- ----- ----- ------ ------ ----- ----- ----- \n";
            for (int j = 0; j < grid.length; j++) {
                if (j==0)
                {
                    ret+=8-i+"  ";
                }
                ret+="|";

                if (grid[i][j]!=null)
                {
                    if ((j==3)||(j==4))
                    {
                        ret+=" ";
                    }
                    ret+=" "+grid[i][j].toString()+" ";
                }else
                {
                    ret+="  ---  ";
                }
            }
            ret+="|\n";
        }
        ret+="    ----- ----- ----- ------ ------ ----- ----- ----- ";
        ret+="\n       A         B        C         D         E           F         G         H      ";
        return ret;
    }

    public IPieza[][] getTablero()
    {
        return grid;
    }

}
