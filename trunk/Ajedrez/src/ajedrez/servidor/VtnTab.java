/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.servidor;

//import entidades.CampoBatalla;
//import entidades.Obstaculo;
//import entidades.Robot;
import ajedrez.entidades.CanvasJuego;
import ajedrez.entidades.Tablero;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author albertolemus
 */
public class VtnTab implements Runnable{

    public static int numJuegos=0;
    CanvasJuego cj;
    JFrame f;
    private GridBagConstraints gbc;
    public final static int TAMANIO_BARRA_MENU = 23;

    @SuppressWarnings("static-access")
    public VtnTab(Tablero cb)
    {
        numJuegos++;
        f=new JFrame("Ajedrez: "+numJuegos);
        cj = new CanvasJuego(cb);
        f.setSize(new Dimension((8*cj.TAMANIO_SLOT)+1,8*cj.TAMANIO_SLOT+TAMANIO_BARRA_MENU));
        f.setMaximumSize(new Dimension((8*cj.TAMANIO_SLOT)+1,8*cj.TAMANIO_SLOT+TAMANIO_BARRA_MENU));
        //f.pack();
        f.setMinimumSize(new Dimension((8*cj.TAMANIO_SLOT)+1,8*cj.TAMANIO_SLOT+TAMANIO_BARRA_MENU));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setNewCanvas(Tablero t)
    {
        f.repaint();
     //   cj = new CanvasJuego(t);
//        f.removeAll();
//        this.launchFrame();
    }

    public void launchFrame()
    {
        f.setLayout(new GridBagLayout());
        gbc=new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx=0; //columna 0
        gbc.gridy=0; //fila 0
        gbc.weightx=7; //ancho
        gbc.weighty=0.5; //alto
//        gbc.gridheight=3; //span
        gbc.ipady=5; //alturaminima?
        f.add(cj,gbc); //agregar componente con restricciones gbc

//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.gridx=1;
//        gbc.gridy=0;
//        gbc.ipady=0;
//        gbc.weightx=1;
//        gbc.weighty=0.1;
//        gbc.gridheight=1;
//        send.addActionListener(new SendHandler());
//        f.add(send,gbc);
//
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.gridx=1;
//        gbc.gridy=1;
//        gbc.weightx=1;
//        //gbc.weighty=0.1;
//        quit.addActionListener(new ActionListener(){
//
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(e.getActionCommand());
//                System.exit(0);
//            }
//        });
//        f.add(quit,gbc);
//
//        gbc.fill=GridBagConstraints.BOTH;
//        gbc.gridx=1;
//        gbc.gridy=2;
//        gbc.weightx=1;
//        //gbc.weighty=0.1;
//        //gbc.ipady=5;
//        f.add(userNames, gbc);
//
//        gbc.fill = GridBagConstraints.BOTH;
//        //gbc.ipady=0;
//        gbc.gridx=0;
//        gbc.gridy=3;
//        gbc.gridwidth=2;
//        gbc.weightx=8;
//        gbc.weighty=0.1;
//        txtField.addActionListener(new SendHandler());
//        f.add(txtField,gbc);
//        f.addWindowListener(new CloseHandler());
        f.pack();

        f.setSize(500, 320);
        f.setLocationByPlatform(true);
        f.setVisible(true);

    }

//    public static void main(String args[])
//    {
/*        CampoBatalla bf=new CampoBatalla(15,10);
        List<Obstaculo> obs = new ArrayList<Obstaculo>();
        obs.add(new Obstaculo(5,5));
        obs.add(new Obstaculo(3,7));
        obs.add(new Obstaculo(8,1));
        obs.add(new Obstaculo(4,2));
        obs.add(new Obstaculo(1,1));
        obs.add(new Obstaculo(6,6));
        bf.addObstaculos(obs);
        bf.addRobot(new Robot(0,0,10));
        bf.addRobot(new Robot(9,9,10));
        bf.addRobot(new Robot(7,8,5));
        bf.addRobot(new Robot(2,6,5));*/
//    }

    public void run() {
        this.launchFrame();
    }
}
