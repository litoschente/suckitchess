/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.cliente;

import ajedrez.entidades.Mensaje;
import ajedrez.entidades.Tablero;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author albertolemus
 */
public class Jugador implements Runnable{

    Socket socket;
    private String serverIP;
    int serverPort;
    ObjectOutputStream oOutput;
    ObjectInputStream  oInput;
    String comando;
    private String color;
    private VtnJuego game;
    private VtnConfig conf;
    private String tablero;

    boolean die=false;
    String tst = "hola";

    public Jugador(VtnConfig conf,String ip, int port)
    {
        this.conf=conf;
        this.serverIP=ip;
        this.serverPort=port;
    }

    public boolean conectar()
    {
        try {
            socket = new Socket(serverIP, serverPort);
        } catch (UnknownHostException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }

        Object response = null;

        try {
            System.out.println("conectado");
            oOutput = new ObjectOutputStream(socket.getOutputStream());
            oInput = new ObjectInputStream(socket.getInputStream());
            System.out.println("esperando respuesta del servidor");
            response = oInput.readObject();
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }

        if (response instanceof Mensaje)
        {
            System.out.println("mensaje recibido");
            Mensaje resp = (Mensaje) response;

            if (resp.getTipoMensaje().equals("color"))
            {
                color = resp.getMensaje();
                this.tablero = resp.getTablero();
                return true;
            }
        }
        return false;
    }

    public void setTablero(String tab){
        this.game.dibujarTablero(tab);
    }

    public Tablero getTablero() throws IOException, ClassNotFoundException
    {
        oOutput.writeObject(new String("getTablero"));
        Object o = oInput.readObject();
        if (o instanceof Tablero)
        {
            Tablero t = (Tablero)o;
            System.out.println(t.toString());
            return t;
        }
        return null;
    }

    public boolean mover(char posX, int posY, char posX2, int posY2)
    {
        boolean ok = false;

        try {
            //Aqui iria el WriteObject
            Mensaje mens = new Mensaje("mover", posX + ":" + posY + ":" + posX2
                    + ":" + posY2, null);
            this.oOutput.writeObject(mens);
            ok = true;

        } catch (IOException ex) {
            ok = false;
            //Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ok;
    }

    /**
     * @param IP the serverIP to set
     */
    public void setServerData(String IP, int port) {
        this.serverIP = IP;
        this.serverPort = port;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

//    public boolean esperar() throws IOException, ClassNotFoundException {
//        Object o = oInput.readObject();
//        if (o instanceof String)
//        {
//            String msg = (String)o;
//            if (msg.equals("LISTO"))
//            {
//                return true;
//            }
//        }
//        return false;
//    }

    public void run() {
        while ((!(conectar()))&&(!(die)))
        {
            System.out.println("intentando conectar");
        }

        if (die)
        {
        }
        else
        {
            conf.setVisible(false);
            game=new VtnJuego(this);
            game.setVisible(true);
            game.dibujarTablero(this.tablero);

            while(true)
            {
                try {
                    Object o = oInput.readObject();

                    if (o instanceof Mensaje)
                    {
                        if ( ((Mensaje)o).getTipoMensaje().equals("turno"))
                        {
                            game.listo();
                            game.dibujarTablero(((Mensaje)o).getTablero());
                        }
                        else if ( ((Mensaje)o).getTipoMensaje().equals("mover") )
                        {
                            //Aqui se dibuja el tablero
                            //Si es true dibuja el tablero y bloquea
                            //Si es false sigue desbloqueado y mandar mensaje de error
                            //para que sepa que no se puede mover

                            if (((Mensaje)o).getMensaje().equals("true")){
                                //Dibujar el tablero
                                this.game.dibujarTablero(((Mensaje)o).getTablero());
                                this.game.bloquearJugador();
                            }
                            else{
                                //Movimiento invalido
                                this.game.mensajeError("Movimiento Invalido.");
                            }
                        }
                        else if ( ((Mensaje)o).getTipoMensaje().equals("fin") )
                        {
                            //Fin del juego
                        }

                    }

                } catch (IOException ex) {
                    die = true;
                } catch (ClassNotFoundException ex) {
                    die = true;
                }

            }



        }
    }

}
