/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2jeutennistcp.Joueurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.json.JSONObject;
import projet2jeutennistcp.ObjetaEchanger.Jeu;
import projet2jeutennistcp.ObjetaEchanger.PortEcoute;


/**
 *
 * @author çpc
 */
class ServeurJoueur1TCPLectureEnvoi {
    
        private static Scanner scanner;
    
        public static void jouer(BufferedReader input, PrintWriter output) {
        // Lecture de 'Bonjour'
        
        String message = "";
        JSONObject j = null;
        
      
     //=========ECHANGE JSONOBJET =LECTURE =====================================//    
        try {
            message = input.readLine(); 
          //  System.err.println(message);
            j = new JSONObject(message);
            int portEcoutJoueur1 = j.getInt("port");
            PortEcoute p = new PortEcoute(portEcoutJoueur1);
            
             output.println(message);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture : " + e);
            System.exit(0);
        } 
     
     //====DEBUT JEU ==========================================================//
     System.err.println("|D E B U T   D E  L A  P A R T I E  !!!!!!!!!|");
     
     do{
     System.out.print( "[JOUEUR1 :] Veuillez definir ta position : " );
     scanner = new Scanner( System.in );
     
     int pos1 = scanner.nextInt();
     if(pos1<1 || pos1>5) 
     {
         System.err.println("La position doit etre entre 1 et 5, redefinissez la!");
         pos1= scanner.nextInt();
     }
     System.out.print( "[JOUEUR1 :] Sur quelle position est dirigée ton TIRE : " );
            int tire1 = scanner.nextInt();
             if(tire1<1 || tire1>5)
            {
            System.err.println("Le tire doit etre entre 1 et 5, redefinissez le!");
            tire1= scanner.nextInt();
            }
            Jeu jj1 = new Jeu(pos1, tire1);
            JSONObject jjj1 = new JSONObject(jj1);
            message = jjj1.toString();
            System.err.println("[JOEUR1 A JOUÉ :] COMME POSITION ET TIRE]"+message);
            output.println(message);
            
            
            
           //====LECTURE JEU DU JOUEUR 2 ======//
           try {
            message = input.readLine();
            JSONObject j2 = new JSONObject(message);
           int position = j2.getInt("position");
         //  int tire = j2.getInt("tire");
         //  int conteurPoint = j2.getInt("conteurPoint");
           
           System.err.println("[JOUEUR2 A JOUÉ]: " + j2);
          // System.out.println("[position]: " + position);
         
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture : " + e);
            System.exit(0);
        }
     }while(true);      
    }
     
    
        
        
    /*
        public static void jouer(BufferedReader input, PrintWriter output) {
        // Lecture de 'Bonjour'
        Scanner scanner = new Scanner( System.in );
        String message = "";
        JSONObject j = null;
        
     //=========ECHANGE JSONOBJET =LECTURE =====================================// 
            System.err.println("Antes de ler o que ai vem1");
        try {
            message = input.readLine();
            System.err.println("Antes de ler o que vem depois");
            System.err.println(message);
            j = new JSONObject(message);
            int portEcoutJoueur1 = j.getInt("port");
          //  PortEcoute p = new PortEcoute(portEcoutJoueur1);
          
            System.out.println("Port de l autre joueur: " + j);
            output.println(message);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture : " + e);
            System.exit(0);
        }    
        
    //    output.println(message);
        
        
        //ENVOI CONFIRMATION DEBUT JEU
      /*      JSONObject objet = acederObjetJson("ServeurJoueur1TCP.json"); 
            int portEcouteServeurJ1 = objet.getInt("port");
            PortEcoute pesj1 = new PortEcoute(portEcouteServeurJ1);
            JSONObject jpesj1 = new JSONObject(pesj1);
            message = jpesj1.toString();
            System.out.println("[Port de de mon serveur J1]: " + message);
            output.println(message);*/
            
 /*           System.err.println("|D E B U T   D E  L A  P A R T I E  !!!!!!!!!|");
            System.out.print( "[JOUEUR1 :] Veuillez definir ta position : " );
            int pos1 = scanner.nextInt();
            System.out.print( "[JOUEUR1 :] Sur quelle position est dirigée ton TIRE : " );
            int tire1 = scanner.nextInt();
            Jeu jj1 = new Jeu(pos1, tire1);
            JSONObject jjj1 = new JSONObject(jj1);
            message = jjj1.toString();
            System.err.println("[JOEUR1 A JOUÉ COMME POSITION ET TIRE]"+message);
            output.println(message);
   
        
    }
        */
    
}
