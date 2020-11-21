/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2jeutennistcp.ServeurCentral;

import projet2jeutennistcp.Utilitaire.ServeurCentralTCPUtilitaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import projet2jeutennistcp.Utilitaire.Config;

/**
 *
 * @author çpc
 */
public class ServeurCentralTCP {
 
       public static int portEcoute;
       public static int oui = 1; 
       public static List<Integer> i = new ArrayList<>();
    
     public static Config creerFichierConfiguation(String nomFichier) {
        Config config = new Config(nomFichier, true);
         config.ajouterValeur("adresse", "127.0.0.1");
        config.ajouterValeur("port", 8080);
        // Sauvegarde du fichier de configuration
        config.sauvegarder();
        
        return config;
    }
   
   
   public static void main(String[] args) {
  //  public void dem() { 
    //    String[] args = {"ServeurCentralTCP.json"};
      //  args[0] = "ServeurCentralTCP.class";
       // args[0]="ServeurCentralTCP";
        
        //Recuperer la port dans config
        
        ServeurCentralTCPUtilitaire.gererConfigClassJson(args);
       // gererConfigClassJson(args);
        JSONObject objet = ServeurCentralTCPUtilitaire.acederObjetJson("ServeurCentralTCP.json");
       // JSONObject objet = acederObjetJson("ServeurCentralTCP.json"); 
        portEcoute = objet.getInt("port");
        
        // Création de la socket serveur
        ServerSocket socketServeur = null;
        Socket socketClient = null;
        BufferedReader input = null;
        PrintWriter output = null;
        
        
        
        try {    
            socketServeur = new ServerSocket(portEcoute);
            System.err.println("[ServeurCentral: ] En attente de connection sur la port "+portEcoute+"...");
              // Attente d'une connexion d'un client
            try {
                while (true) { 
                            socketClient = socketServeur.accept();
                            System.err.println("Nouveau client connecté sur: "+socketClient.getRemoteSocketAddress());
                              // Association d'un flux d'entrée et de sortie

                        try {
                            input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
                        } catch(IOException e) {
                            System.err.println("Association des flux impossible : " + e);
                            System.exit(0);
                        }

                        ServeurCentralTCPLectureEnvoi.traitementMsg(input, output);
                      //  ServeurCentralTCPUtilitaire.traitementMsg(input, output);
                }

            } catch(IOException e) {
                System.err.println("Erreur lors de l'attente d'une connexion : " + e);
                System.exit(0);
            }
        } catch(IOException e) {
            System.err.println("Création de la socket impossible : " + e);
            System.exit(0);
        }

        fermetureFlux(input, output, socketClient, socketServeur);
    }

    private static void fermetureFlux(BufferedReader input, PrintWriter output, Socket socketClient, ServerSocket socketServeur) {
        // Fermeture des flux et des sockets
        try {
            input.close();
            output.close();
            socketClient.close();
            socketServeur.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(0);
        }
    }
    
   

    
}
