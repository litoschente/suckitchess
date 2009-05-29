/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.cliente;

import ajedrez.entidades.Tablero;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            oOutput = new ObjectOutputStream(socket.getOutputStream());
            oInput = new ObjectInputStream(socket.getInputStream());
            response = oInput.readObject();
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }

        if (response instanceof String)
        {
            String resp = (String)response;
            if (resp.equals("blanco")||resp.equals("negro"))
            {
                color = resp;
                return true;
            }
        }
        return false;
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
        return true;
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

    public boolean esperar() throws IOException, ClassNotFoundException {
        Object o = oInput.readObject();
        if (o instanceof String)
        {
            String msg = (String)o;
            if (msg.equals("LISTO"))
            {
                return true;
            }
        }
        return false;
    }

    public void run() {
        while ((!(conectar()))&&(!(die)))
        {
            System.out.println(tst);
        }
        if (die)
        {}else
        {
            conf.setVisible(false);
            game=new VtnJuego(this);
            game.setVisible(true);
            try {
                Object o = oInput.readObject();
                if (o instanceof String)
                {
                    String x = (String) o;
                    if (x.equals("LISTO"))
                    {
                        game.listo(getColor());
                    }
                    game.dibujarTablero(getTablero());
                }
            } catch (IOException ex) {
                die = true;
            } catch (ClassNotFoundException ex) {
                die = true;
            }

        }
    }

}
