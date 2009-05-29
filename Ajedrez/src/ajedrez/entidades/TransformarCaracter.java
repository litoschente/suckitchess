/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.entidades;

/**
 *
 * @author albertolemus
 */
public class TransformarCaracter {

    public PosChar transformarCaracter(char x)
    {
        switch(x)
        {
            case 'a': return PosChar.a;
            case 'b': return PosChar.b;
            case 'c': return PosChar.c;
            case 'd': return PosChar.d;
            case 'e': return PosChar.e;
            case 'f': return PosChar.f;
            case 'g': return PosChar.g;
            case 'h': return PosChar.h;
        }
        return null;
    }

}
