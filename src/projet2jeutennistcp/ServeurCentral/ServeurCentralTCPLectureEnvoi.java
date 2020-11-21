/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2jeutennistcp.ServeurCentral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import projet2jeutennistcp.ObjetaEchanger.PortEcoute;

/**
 *
 * @author çpc
 */
public class ServeurCentralTCPLectureEnvoi {
    
    List<PortEcoute> client = new ArrayList<>();
   
    
     public static void traitementMsg(BufferedReader input, PrintWriter output) {
        // Lecture de 'Bonjour'
        ServeurCentralTCPLectureEnvoi s = new ServeurCentralTCPLectureEnvoi();
        ServeurCentralTCP sc = new ServeurCentralTCP();
        String message = "";
        JSONObject j = null;
        
      
    //  System.err.println("Lu5"+sc.i);
         for(Integer bb : sc.i) {
            int portJoeur1 = bb ;
            PortEcoute pe = new PortEcoute(portJoeur1);
            JSONObject jpe = new JSONObject(pe);
            message = jpe.toString();
            System.out.println("Envoi Port Joeur1: " + message);
            output.println(message);
         }
     //=========ECHANGE JSONOBJET =LECTURE =====================================//    
        try {
            message = input.readLine(); 
            System.err.println(message);
            j = new JSONObject(message);
            int portEcoutJoueur1 = j.getInt("port");
            PortEcoute p = new PortEcoute(portEcoutJoueur1);
            // s.client.add(p);
             sc.i.add(portEcoutJoueur1);
            System.out.println("Port du joueur 1 a envoyer a Joueur2: " + j);
             output.println(message);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture : " + e);
            System.exit(0);
        }    
        
       // System.out.println("Envoi, Le numero envoyé est: " + message);
       
        
        
     //=========ECHANGE JSONOBJET =ENVOIE =====================================//    
      //On a atribué a la varible message, o objetoJson j
   /*     message = j.toString();
        System.out.println("Envoi1: " + message);
        output.println(message);
     */   
    //OUVRIR ICI UNE DEUXIEME CONNECTION OU ON ENVOIE LE MSG EN BAS //    
        
        //On a atribué a la varible message, o objetoJson j
        
       // JSONObject bb = new JSONObject();
      //  System.err.println("Lu5"+sc.i);
         for(Integer bbb : sc.i) {
            int portJoeur11 = bbb ;
            PortEcoute pe1 = new PortEcoute(portJoeur11);
            JSONObject jpe1 = new JSONObject(pe1);
            message = jpe1.toString();
            System.out.println("[Port de l´autre joueur]: " + message);
            output.println(message);
       
        }
         
       
        
        
    }
     }

    



/*
//=========ECHANGE JSONOBJET =ENVOIE =====================================//    
      //On a atribué a la varible message, o objetoJson j
       // message = j.toString();
       JSONObject objet = ServeurCentralTCPUtilitaire.acederObjetJson("ServeurCentralTCP.json");
       int portEcouteServeurCentral = objet.getInt("port");
      // j = new JSONObject("8089");
      // message = j.toString();
      message = objet.toString();
        System.out.println("Envoi: " + message);
        output.println(message);

        JSONObject objet = ServeurCentralTCPUtilitaire.acederObjetJson("ServeurCentralTCP.json");
       int portEcouteServeurCentral = objet.getInt("port");
        message = objet.toString();
        System.out.println("Envoi: " + message);
        output.println(message);


*/