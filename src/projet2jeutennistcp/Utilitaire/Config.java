package projet2jeutennistcp.Utilitaire;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

/**
 * Classe permettant de créer/gérer un fichier de configuration.
 * @author Cyril Rabat
 */
public class Config {

    private String nomFichier;      // Nom du fichier de configuration
    private JSONObject config;      // La configuration 
    
    /**
     * Ouverture d'un fichier de configuration.
     * @param nomFichier le nom du fichier de configuration
     */
    public Config(String nomFichier) {
        this.nomFichier = nomFichier;
        charger();
    }

    /**
     * Ouverture/création d'un fichier de configuration.
     * @param nomFichier le nom du fichier de configuration
     * @param creation si 'true', crée un nouveau fichier vide
     */
    public Config(String nomFichier, boolean creation) {
        if(!creation) {
            this.nomFichier = nomFichier;
            charger();
        }
        else {
            this.nomFichier = nomFichier;
            config = new JSONObject();
        }
    }
    
    /**
     * Indique si un fichier existe.
     * @param nomFichier le nom du fichier
     * @return 'true' s'il existe
     */
    public static boolean fichierExiste(String nomFichier) {
        File f = new File(nomFichier);

        return f.exists();       
    }
    
    /**
     * Retourne la valeur associée à une clef.
     * @param clef le nom de la clef
     * @return la valeur de la clef
     */
    public String getString(String clef) {
        /**
         * #TODO#
         * Récupère la donnée dont la clef est spécifiée dans l'objet
         * JSON (attribut 'config').
         */  
        clef = config.getString("clef");
        return clef;
    }
    
    /**
     * Retourne la valeur associée à une clef.
     * @param clef le nom de la clef
     * @return la valeur de la clef
     */
    public int getInt(int clef) {
        /**
         * #TODO#
         * Récupère la donnée dont la clef est spécifiée dans l'objet
         * JSON (attribut 'config').
         */
        clef = config.getInt("clef");
        return clef;
    }
    
    /**
     * Ajoute une valeur de type entier dans la configuration.
     * @param clef le nom de la clef
     * @param valeur la valeur de la clef
     */
    public void ajouterValeur(String clef, int valeur) {
        /**
         * #TODO#
         * Ajouter les données dans l'objet JSON (attribut 'config')
         * 
         */
        config.put(clef, valeur);
    }

    /**
     * Ajoute une valeur de type chaîne de caractères dans la configuration.
     * @param clef le nom de la clef
     * @param valeur la valeur de la clef
     */
    public void ajouterValeur(String clef, String valeur) {
        /**
         * #TODO#
         * Ajouter les données dans l'objet JSON (attribut 'config')
         */
        config.put(clef, valeur);
    }
    
    /**
     * Charge un fichier de configuration en mémoire.
     */
    private void charger() {
        // Ouverture du fichier
        
        /**
         * #TODO#
         * Charger le fichier JSON dont le nom correspond à l'attribut
         * 'nomFichier' dans l'attribut 'config' (un objet JSONObject).
         */
        
         // Récupération de la chaîne JSON depuis le fichier
        String json = "";
        try {
            byte[] contenu = Files.readAllBytes(Paths.get(nomFichier));
          json = new String(contenu); 
          config=new JSONObject(json);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture du fichier '");
            System.exit(0);
        }
        //System.err.println(json);
        
        
    }
    
    /**
     * Sauvegarde la configuration dans le fichier.
     */
    public void sauvegarder() {
        // Création du fichier de sortie
        
        /**
         * #TODO#
         * Sauvegarder le fichier JSON dans le fichier dont le nom
         * correspond à l'attribut 'nomFichier'.
         */
     
           // Création du fichier de sortie
        FileWriter fs = null;
        try {
           // fs = new FileWriter(new File("livre.json"));
           fs = new FileWriter(nomFichier);
        } catch(IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier '" + nomFichier + "'.");
            System.err.println(e);
            System.exit(0);
        }
        
        // Sauvegarde dans le fichier
        try {
            config.write(fs, 3, 0);
            fs.flush();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier.");
            System.err.println(e);
            System.exit(0);
        }
        
        // Fermeture du fichier
        try {
            fs.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture du fichier.");
            System.err.println(e);
            System.exit(0);
        }
      //  System.err.println(config);
      //  afficher(); 
    
    }
    
    }

 