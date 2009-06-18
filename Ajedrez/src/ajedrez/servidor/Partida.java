/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.servidor;

import ajedrez.entidades.Mensaje;
import ajedrez.entidades.Tablero;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    private VtnTab tab;

    boolean control=true;

    public void run()
    {
        //tablero = new Tablero();
        try {
            tab = new VtnTab(tablero);
            Thread v = new Thread(tab);
            v.start();
            oOutput1.writeObject(new Mensaje("turno",null,this.getTablero().toString()));

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

        if (msg instanceof Mensaje)
        {
            Mensaje msj = (Mensaje) msg;

            if (msj.getTipoMensaje().equals("mover"))
            {
                String movimiento = msj.getMensaje();
                String[] tokens = movimiento.split(":");
                System.out.println(movimiento);
                for (String string : tokens) {
                    System.out.println(string);
                }
                boolean ok = false;
                ok = this.tablero.mover(tokens[0].toCharArray()[0],
                        Integer.parseInt(tokens[1]),
                        tokens[2].toCharArray()[0],
                        Integer.parseInt(tokens[3]), turno);

                Mensaje salida = null;
                if (ok){
                    tab.setNewCanvas(tablero);
                    //Mando el mensaje y el tablero nuevo
                    salida = new Mensaje("mover","true",this.tablero.toString());
                    out.writeObject(salida);
                    salida = new Mensaje("turno",null,this.tablero.toString());
                    out2.writeObject(salida);
                    this.turno = this.turno.equals("blanco")?"negro":"blanco";
                }
                else
                {
                    //No puede mover
                    salida = new Mensaje("mover","false",null);
                    out.writeObject(salida);
                }
            }
            else if (msj.getTipoMensaje().equals("fin")){
                //FIN
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
        System.out.println("usuario con el ip: "+jugador1.getInetAddress()+" -> blanco");
        oOutput1.writeObject(new Mensaje("color","blanco",this.tablero.toString()));
    }

    private Socket getJugador2() {
        return jugador2;
    }

    public void setJugador2(Socket jugador2) throws IOException {
        this.jugador2 = jugador2;
        oOutput2 = new ObjectOutputStream(this.jugador2.getOutputStream());
        oInput2 = new ObjectInputStream(this.jugador2.getInputStream());
        System.out.println("usuario con el ip: "+jugador1.getInetAddress()+" -> negro");
        oOutput2.writeObject(new Mensaje("color","negro",this.tablero.toString()));
    }

    private Tablero getTablero() {
        System.out.println(tablero.toString());
        return tablero;
    }

    public Partida(){
        tablero = new Tablero();
    }
}
