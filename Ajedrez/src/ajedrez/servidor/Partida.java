/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.servidor;

import ajedrez.entidades.Tablero;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albertolemus
 */
public class Partida implements Runnable{

    private Socket jugador1;
    private Socket jugador2;
    private Tablero tablero;
    private ObjectOutputStream oOutput1;
    private ObjectOutputStream oOutput2;
    private ObjectInputStream oInput1;
    private ObjectInputStream oInput2;
    private String turno = "blanco";

    boolean control=true;
    public void run()
    {
        tablero = new Tablero();
        try {
            oOutput1.writeObject(new String("LISTO"));
            oOutput2.writeObject(new String("LISTO"));
            //en vez de mandar listo, enviar el tablero XD
        } catch (IOException ex) {
        //    Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (control)
        {
            try {
                atenderPeticion();
            } catch (IOException ex) {
            //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void atenderPeticion() throws IOException, ClassNotFoundException {
        ObjectInputStream in = turno.equals("blanco")?oInput1:oInput2;
        ObjectOutputStream out = turno.equals("blanco")?oOutput1:oOutput2;
        ObjectOutputStream out2 = turno.equals("blanco")?oOutput2:oOutput1;
        Object msg = in.readObject();
        if (msg instanceof String)
        {
            String msj = (String)msg;
            System.out.println(msj);
            if (msj.equals("getTablero"))
            {
                System.out.println("aja");
                out.writeObject(getTablero());

            }
        }

    }

    private Socket getJugador1() {
        return jugador1;
    }

    public void setJugador1(Socket jugador1) throws IOException {
        this.jugador1 = jugador1;
        oOutput1 = new ObjectOutputStream(this.jugador1.getOutputStream());
        oInput1 = new ObjectInputStream(this.jugador1.getInputStream());
        oOutput1.writeObject(new String("blanco"));
    }

    private Socket getJugador2() {
        return jugador2;
    }

    public void setJugador2(Socket jugador2) throws IOException {
        this.jugador2 = jugador2;
        oOutput2 = new ObjectOutputStream(this.jugador2.getOutputStream());
        oInput2 = new ObjectInputStream(this.jugador2.getInputStream());
        oOutput2.writeObject(new String("negro"));
    }

    private Tablero getTablero() {
        System.out.println(tablero.toString());
        return tablero;
    }

}
