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
import java.lang.invoke.MethodHandles;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONException;
import org.json.JSONObject;
import projet2jeutennistcp.ServeurCentral.ServeurCentralTCP;
import projet2jeutennistcp.Utilitaire.Config;
import projet2jeutennistcp.Utilitaire.ServeurCentralTCPUtilitaire;

/**
 *
 * @author çpc
 */
public class ServeurJoueur1TCP {
    
       //   public Scanner scanner = new Scanner( System.in );
  
           public static int portEcoute;
    
     public static Config creerFichierConfiguation(String nomFichier) {
        Config config = new Config(nomFichier, true);
         config.ajouterValeur("adresse", "127.0.0.2");
        config.ajouterValeur("port", 8081);
        // Sauvegarde du fichier de configuration
        config.sauvegarder();
        
        return config;
    }

    public static void main(String[] args) {
   //   public void demSJ1() { 
    //    String[] args = {"ServeurJoueur1TCP.json"};  
        
        //Recuperer la port dans config
        
       // ServeurCentralTCPUtilitaire.gererConfigClassJson(args);
        gererConfigClassJson(args);
       // JSONObject objet = ServeurCentralTCPUtilitaire.acederObjetJson("ServeurJoueur1TCP.json");
        JSONObject objet = acederObjetJson("ServeurJoueur1TCP.json"); 
        portEcoute = objet.getInt("port");
        
        // Création de la socket serveur
        ServerSocket socketServeur = null;
        Socket socketClient = null;
        BufferedReader input = null;
        PrintWriter output = null;
        
        try {    
            socketServeur = new ServerSocket(portEcoute);
            System.err.println("En attente de connection sur la port "+portEcoute+"...");
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

                      ServeurJoueur1TCPLectureEnvoi.jouer(input, output);
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
    
     public static int retournerPortServeur() {
     JSONObject objet = ServeurCentralTCPUtilitaire.acederObjetJson("ServeurJoueur1TCP.json");
       // JSONObject objet = acederObjetJson("ServeurJoueur1TCP.json"); 
       int portEcoute = objet.getInt("port");
       return portEcoute; 
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
     
      public static void gererConfigClassJson(String[] args) {
        // Vérification des arguments
        Config config;
        // String className ="";
        if(args.length == 0) {
            // Pas d'argument : on ouvre le fichier json par défaut (nom de la classe)
            
            String className = MethodHandles.lookup().lookupClass().getSimpleName() + ".json";
            if(Config.fichierExiste(className))
                config = new Config(className);
            else
                config = ServeurCentralTCP.creerFichierConfiguation(className);
        }
        else {
            // Un argument : c'est le nom du fichier JSON de config à ouvrir/créer
            if(Config.fichierExiste(args[0]))
                config = new Config(args[0]);
            else
                config = ServeurCentralTCP.creerFichierConfiguation(args[0]);
        }
    }

    
}

