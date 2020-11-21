/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2jeutennistcp.Utilitaire;

import java.io.Serializable;

/**
 *
 * @author çpc
 */
public enum TypeErreur implements Serializable{
    
     SYSTEME("Systeme"),
    JEU("jeu");
    
    final String description; 
    
    TypeErreur(String description) {
    this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
