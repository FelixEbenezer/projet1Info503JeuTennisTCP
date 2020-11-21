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
public class GestionErreur implements Serializable{
    
    private TypeErreur type = TypeErreur.JEU;
    int code;
    String description; 
    
    public GestionErreur(TypeErreur type, int code, String description) 
    {
    this.type=type;
    this.code=code;
    this.description=description; 
    }

    public TypeErreur getType() {
        return type;
    }

    public void setType(TypeErreur type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
