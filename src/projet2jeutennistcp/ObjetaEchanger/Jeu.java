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
public class Jeu implements Serializable{
    
    private int position = 3; 
    private int tire;
    private int conteurPoint; 
    
    public Jeu() {}
    public Jeu(int position) {
    this.position=position;
    }
    
    public Jeu(int position, int tire) {
    this.tire=tire;
    this.position=position;
    }
    
    public Jeu(int position, int tire, int conteurPoint) {
    this.position=position;
    this.tire=tire;
    this.conteurPoint=conteurPoint;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTire() {
        return tire;
    }

    public void setTire(int tire) {
        this.tire = tire;
    }

    public int getConteurPoint() {
        return conteurPoint;
    }

    public void setConteurPoint(int conteurPoint) {
        this.conteurPoint = conteurPoint;
    }
    
    
    
}
