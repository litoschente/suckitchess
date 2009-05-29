/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.entidades;

import ajedrez.entidades.piezas.*;

/**
 *
 * @author albertolemus
 */
public class test {

    public static void main(String args[])
    {
        Tablero t = new Tablero();
        System.out.println(t.toString());
        t.mover('e', 2, 'e', 3);
        System.out.println(t.toString());
        t.mover('c',1,'f',4);
        System.out.println(t.toString());

    }
}