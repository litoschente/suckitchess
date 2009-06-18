/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author albertolemus
 */
public class Servidor{

    private final static int        NRO_PUERTO  = 9189;
    private static ServerSocket     serv        = null;

    public static void main(String args[]) {
        try {
            serv = new ServerSocket(NRO_PUERTO);
            System.out.println("servidor iniciado");
            List<Thread> hilos = new ArrayList<Thread>();
            while(true)
            {
                Partida p = new Partida();
                Socket j1 = serv.accept();
                p.setJugador1(j1);
                Socket j2 = serv.accept();
                p.setJugador2(j2);
                Thread hilo = new Thread(p);
                hilo.start();
                hilos.add(hilo);
            }
        } catch (IOException ex) {
            
        }
    }

}
