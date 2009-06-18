/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez.entidades;

import java.io.Serializable;

/**
 *
 * @author Aurelio
 */
public class Mensaje implements Serializable {

    private String tipoMensaje;
    private String mensaje;
    private String tablero;

    public Mensaje(String tipo, String msj, String tab){
        this.tipoMensaje = tipo;
        this.mensaje = msj;
        this.tablero = tab;
    }

    /**
     * @return the tipoMensaje
     */
    public String getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * @param tipoMensaje the tipoMensaje to set
     */
    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the tablero
     */
    public String getTablero() {
        return tablero;
    }

    /**
     * @param tablero the tablero to set
     */
    public void setTablero(String tablero) {
        this.tablero = tablero;
    }

}
