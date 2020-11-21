/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2jeutennistcp.Joueurs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author çpc
 */
public class Joueur2TCP {
 //   private static ServeurJoueur2TCP serveurJoueur2TCP = new ServeurJoueur2TCP();
 //  private static int portServeurJoueur2TCP = serveurJoueur2TCP.retournerPortServeur();
   
   private static int portEcouteServeurCentral; 
   private static String message;
   
   public static int portEcoutJoueur1;
  
   
    public static void main(String[] args) {
  //  public static void demarrerJ2() {
        
        enregistrerPortServeurCentral();
        //System.err.println("PortServeurJoueur2: "+portServeurJoueur2TCP);
       Joueur2LectureEnvoi.jouer(portEcoutJoueur1);
    }
    
    private static int obtenirPortServeurCentral () {
   // JSONObject objet = ServeurCentralTCPUtilitaire.acederObjetJson("ServeurCentralTCP.json");
      JSONObject objet = acederObjetJson("ServeurCentralTCP.json");
   
        portEcouteServeurCentral = objet.getInt("port");
        return portEcouteServeurCentral;
    }
    
    private static void enregistrerPortServeurCentral() {
     // Création de la socket
        portEcouteServeurCentral = obtenirPortServeurCentral();
        Socket socket = null;
        try {
            socket = new Socket("localhost", portEcouteServeurCentral);
            System.err.println("Je suis le joueur2 ");
        } catch(UnknownHostException e) {
            System.err.println("Erreur sur l'hôte : " + e);
            System.exit(0);
        } catch(IOException e) {
            System.err.println("Création de la socket impossible : " + e);
            System.exit(0);
        }
        
         // Association d'un flux d'entrée et de sortie
        BufferedReader input = null;
        PrintWriter output = null;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch(IOException e) {
            System.err.println("Association des flux impossible : " + e);
            System.exit(0);
        }
        
               
        //LECTURE DE LA PORT DE L AUTRE JOUEUR VENANR DU SERVEUR CENTRAL
        //=========ECHANGE JSONOBJET =LECTURE =====================================//
        JSONObject j = null;
        try {
            message = input.readLine(); 
            System.err.println(message);
            j = new JSONObject(message);
            portEcoutJoueur1 = j.getInt("port");
          //  PortEcoute portVenantServeur = new PortEcoute(portEcoutJoueur1);
          //  System.out.println("portVenantServeur: " + portVenantServeur.toString());
            System.out.println("[Port de l´autre joueur]: " + j);
          output.println(message);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture : " + e);
            System.exit(0);
        } 
        
        
        //voir si pour mettre celle methode dans main
     //   Joueur2LectureEnvoi.jouer(portEcoutJoueur1);
     //  Joueur2LectureEnvoi.jouer(portEcoutJoueur1);
      
        

        //ENVOIE DE LA PORT AU SERVEUR CENTRAL
     /*   int port = portServeurJoueur2TCP ;
        PortEcoute p = new PortEcoute(port);
        
        j = new JSONObject(p);
        System.out.println("[La port de mon serveur est ]: " + j);
        output.println(j.toString());*/
    }
    
    public final static JSONObject acederObjetJson(String nomFichier) throws JSONException {
        String json = "";
        try {
            byte[] contenu = Files.readAllBytes(Paths.get(nomFichier));
            json = new String(contenu);
            
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture du fichier 1'");
            //    System.err.println(json);
            System.exit(0);
        }
        // Création d'un objet JSON
        JSONObject objet = new JSONObject(json);
        //    System.out.println("Contenu JSON : ");
        return objet;
    }
}
