/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2jeutennistcp.Utilitaire;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONException;
import org.json.JSONObject;
import projet2jeutennistcp.ServeurCentral.ServeurCentralTCP;

/**
 *
 * @author çpc
 */
public class ServeurCentralTCPUtilitaire {

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
