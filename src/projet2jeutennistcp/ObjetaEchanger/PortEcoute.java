/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2jeutennistcp.ObjetaEchanger;

import java.io.Serializable;

/**
 *
 * @author çpc
 */
public class PortEcoute implements Serializable{
    
    private int port;
    private String adresse;
    
    public PortEcoute(int port) {
    this.port=port;
    }
    
    public PortEcoute(int port, String adresse) {
    this.port=port;
    this.adresse=adresse;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
    
}
