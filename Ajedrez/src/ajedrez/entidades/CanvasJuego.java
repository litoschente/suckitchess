/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.entidades;


import ajedrez.entidades.IPieza;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author albertolemus
 */
public class CanvasJuego extends JPanel{

    private Tablero bf;
    public final static int TAMANIO_SLOT = 32;

    public CanvasJuego(Tablero c)
    {
        bf=c;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int x;
        int y;
        y=(TAMANIO_SLOT/2);
        for (int i = 0; i < 8; i++) {
            int[] yc = {y-(TAMANIO_SLOT/2),y+(TAMANIO_SLOT/2),y+(TAMANIO_SLOT/2),y-(TAMANIO_SLOT/2)};
            x=(TAMANIO_SLOT/2);
            for (int j = 0; j < 8; j++) {
                IPieza e = bf.getTablero()[i][j];
                //cada cuadro de 32px...
                //
                int[] xc = {x-(TAMANIO_SLOT/2),x-(TAMANIO_SLOT/2),x+(TAMANIO_SLOT/2),x+(TAMANIO_SLOT/2)};
                g.setColor(Color.BLACK);
                g.drawPolygon(xc,yc,4);
                if (((((i%2)==0)&&((j%2)==0)))||((((i%2)!=0)&&((j%2)!=0))))
                {
                    g.setColor(Color.WHITE);
                }else
                {
                    g.setColor(Color.DARK_GRAY);
                }
                //g.setColor((((i%2)==0)&&((j%2)==0))?Color.WHITE:Color.BLACK);
                g.fillPolygon(xc,yc,4);
                if (!(e==null))
                {
                 //   g.fillPolygon(xc,yc,4);
                    g.drawImage(Toolkit.getDefaultToolkit().getImage(e.getImagen()), x-(TAMANIO_SLOT/2), y-(TAMANIO_SLOT/2), this);
                }else
                {
                  //  g.fillPolygon(xc,yc,4);
                }
                x+=TAMANIO_SLOT;
            }
            y+=TAMANIO_SLOT;
        }
        g.setColor(Color.BLACK);
        int r=0;
        for (int i = 0; i < 8; i++) {
            g.drawRect(0, r,(8*TAMANIO_SLOT) , TAMANIO_SLOT);
            r+=TAMANIO_SLOT;
        }
        r=0;
        for (int i = 0; i < 8; i++) {
            g.drawRect(r, 0, TAMANIO_SLOT, (8*TAMANIO_SLOT));
            r+=TAMANIO_SLOT;
        }

    }

}
