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
import java.util.Scanner;
import org.json.JSONObject;
import projet2jeutennistcp.ObjetaEchanger.Jeu;
import projet2jeutennistcp.ObjetaEchanger.PortEcoute;

/**
 *
 * @author çpc
 */
class Joueur2LectureEnvoi {
    private static Joueur2TCP j2 = new Joueur2TCP();
 //   private ServeurJoueur2TCP sj2 = new ServeurJoueur2TCP();
    private static int portEcouteServeurJoueur1;
    private static String message = "";
     private static Scanner scanner;
    
    public static void jouer(int portEcoutJoueur1) {
     // Création de la socket
        portEcouteServeurJoueur1 = portEcoutJoueur1; 
        Socket socket = null;
        try {
            socket = new Socket("localhost", portEcouteServeurJoueur1);
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
        
        
        //ENVOIE DE LA PORT DU SERVEUR DU JOUEUR2 POUR SE CONNECTER AU SERVEUR JOUEUR1
        
        
     //   JSONObject objet = ServeurJoueur2TCP.acederObjetJson("ServeurJoueur2TCP.json");
     //   int port2 = objet.getInt("port");
        PortEcoute p = new PortEcoute(8082);
        JSONObject j = new JSONObject(p);
        System.out.println("Connecting to Serveur du joueur1: " + j);
        output.println(j.toString());  
       
               
        //LECTURE DE LA PORT DE L AUTRE JOUEUR VENANR DU SERVEUR CENTRAL
        //=========ECHANGE JSONOBJET =LECTURE =====================================//
       // JSONObject j = null;
     //  scanner = new Scanner( System.in );
     //  System.err.println("Tapez 0 si vous voulez abandonner le jeu");
     //  int pos1 = scanner.nextInt();
       
       do {
        try {
        //==========LECTURE DU JEU DE JOUEUR1 =========//
        message = input.readLine();
        JSONObject j2 = new JSONObject(message);
         //  int position = j2.getInt("position");
         //  int tire = j2.getInt("tire");
         //  int conteurPoint = j2.getInt("conteurPoint");
           
           System.err.println("[JOUEUR1 A JOUÉ]: " + j2);
          // System.out.println("[position]: " + position);
         
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture : " + e);
            System.exit(0);
        }
        
        //====ENVIO DU JEU DE JOUEUR2 A J1==============//
        System.out.print( "[JOUEUR2 :] Veuillez definir ta position : " );
        scanner = new Scanner( System.in );
     
        int pos1 = scanner.nextInt();
        if(pos1<1 || pos1>5) 
        {
         System.err.println("La position doit etre entre 1 et 5, redefinissez la!");
         pos1= scanner.nextInt();
        }
        System.out.print( "[JOUEUR2 :] Sur quelle position est dirigée ton TIRE : " );
            int tire1 = scanner.nextInt();
             if(tire1<1 || tire1>5)
            {
            System.err.println("Le tire doit etre entre 1 et 5, redefinissez le!");
            tire1= scanner.nextInt();
            }
            Jeu jj1 = new Jeu(pos1, tire1);
            JSONObject jjj1 = new JSONObject(jj1);
            message = jjj1.toString();
            System.err.println("[JOEUR2 A JOUÉ COMME POSITION ET TIRE]"+message);
            output.println(message);
           
           

       }while (true);
       //while (pos1==0);        
        

    }
}
